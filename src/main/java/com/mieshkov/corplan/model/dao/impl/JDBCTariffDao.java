package com.mieshkov.corplan.model.dao.impl;

import com.mieshkov.corplan.model.dao.TariffDao;
import com.mieshkov.corplan.model.dao.mapper.TariffMapper;
import com.mieshkov.corplan.model.entities.Tariff;
import com.mieshkov.corplan.utils.TariffPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.mieshkov.corplan.containers.QueryContainer.*;

/**
 * @author Ivan Mieshkov
 */
public class JDBCTariffDao implements TariffDao {
    private final Connection connection;
    private final TariffMapper tariffMapper = new TariffMapper();
    private final List<Tariff> tariffs = new ArrayList<>();

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
            statement.setString(1, entity.getNameUkr());
            statement.setString(2, entity.getNameEn());
            statement.setDouble(3, entity.getPrice());
            statement.setString(4, entity.getService());

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
    public Tariff findById(Long id) {
        try(PreparedStatement statement = connection.prepareStatement(FIND_TARIFF_BY_ID)) {
            statement.setLong(1, id);
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
        try(PreparedStatement statement = connection.prepareStatement(FIND_ALL_TARIFFS)){
            return finder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TariffPage findByServiceSorted(String tariffService, String sortBy, String order,
                                          int page, int itemsPerPage) {
        List<Tariff> tariffs = new ArrayList<>();
        int count = 0;
        int fromItem = (page -1) * itemsPerPage;
        int toItem = ((page - 1) * itemsPerPage) + itemsPerPage;

        String query = "SELECT * ,(SELECT count(*) FROM tariff WHERE service = ?) AS count FROM tariff  WHERE service = ? ORDER BY " +
                        sortBy + " " + order + " LIMIT " + fromItem + " ," + toItem + ";";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tariffService);
            statement.setString(2, tariffService);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Tariff tariff = tariffMapper.extractFromResultSet(rs);
                count = rs.getInt("count");
                tariffs.add(tariff);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new TariffPage(count, tariffs);
    }

    @Override
    public List<Tariff> findAllByService(String service) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_TARIFFS_BY_SERVICE)) {
            statement.setString(1, service);
            return finder(statement);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Tariff> findByUserId(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_TARIFFS)) {
            statement.setLong(1, id);
            return finder(statement);

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
            statement.setString(1, entity.getNameEn());
            statement.setString(2, entity.getNameUkr());
            statement.setDouble(3, entity.getPrice());
            statement.setString(4, entity.getService());
            statement.setLong(5, entity.getId());

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
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_TARIFF)) {
            statement.setLong(1, id);

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

    public List<Tariff> finder(PreparedStatement statement) throws SQLException {
        ResultSet rs = statement.executeQuery();
        List<Tariff> tariffs = new ArrayList<>();
        while (rs.next()) {
            Tariff tariff = tariffMapper.extractFromResultSet(rs);
            tariffs.add(tariff);
        }
        rs.close();
        return tariffs;
    }
}
