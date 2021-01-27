package com.example.demo1.model.dao.impl;

import com.example.demo1.model.dao.TariffDao;
import com.example.demo1.model.dao.mapper.TariffMapper;
import com.example.demo1.model.entities.Tariff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo1.containers.QueryContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class JDBCTariffDao implements TariffDao {
    private Connection connection;
    private TariffMapper tariffMapper = new TariffMapper();
    private Map<Integer, Tariff> tariffs = new HashMap<>();

    JDBCTariffDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for inserting new tariff
     * @param entity
     */
    @Override
    public void create(Tariff entity) {
        try(PreparedStatement statement = connection.prepareStatement(CREATE_TARIFF)) {
            statement.setString(1, entity.getTariffNameUkr());
            statement.setString(2, entity.getTariffNameEn());
            statement.setDouble(3, entity.getTariffPrice());
            statement.setString(4, entity.getTariffService());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method, for searching tariff by id
     * @param id
     * @return tariff found entity
     */
    @Override
    public Tariff findById(int id) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_TARIFF_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Tariff tariff = null;


            if(rs.next()) {
                tariff = tariffMapper.extractFromResultSet(rs);
            }
            rs.close();

            return tariff;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for fetching all tariffs from the table
     * @return list of all tariffs in db
     */
        @Override
    public List<Tariff> findAll() {
            List<Tariff> tariffs = new ArrayList<>();

            try(PreparedStatement statement = connection.prepareStatement(FIND_ALL_TARIFFS)){
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Tariff tariff = tariffMapper.extractFromResultSet(rs);
                tariffs.add(tariff);
            }
            rs.close();
            return tariffs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Tariff> findByServiceSorted(String tariffService, String language, String sortBy, String order) {
            List<Tariff> tariffs = new ArrayList<>();
            String query = "SELECT * FROM tariff WHERE tariff_service = ? ORDER BY " + sortBy + " " + order + ";";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tariffService);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Tariff tariff = tariffMapper.extractFromResultSet(rs);
                tariffs.add(tariff);
//                tariffMapper.makeUnique(tariffs, tariff);
            }
            rs.close();
            return tariffs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
        /**
     * Method for updating tariffs
     * @param entity
     */
    @Override
    public void update(Tariff entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TARIFF)) {
            statement.setDouble(1, entity.getTariffPrice());
            statement.setInt(2, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting tariff by id
     * @param id
     */
    @Override
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TARIFF)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        /**
     * Method for closing connection to db
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
