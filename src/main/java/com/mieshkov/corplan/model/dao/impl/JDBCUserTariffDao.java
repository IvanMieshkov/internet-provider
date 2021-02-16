package com.mieshkov.corplan.model.dao.impl;

import com.mieshkov.corplan.model.dao.UserTariffDao;
import com.mieshkov.corplan.model.dao.mapper.UserTariffMapper;
import com.mieshkov.corplan.model.entities.UserTariff;

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
public class JDBCUserTariffDao implements UserTariffDao {
    private Connection connection;
    private UserTariffMapper userTariffMapper = new UserTariffMapper();
    private List<UserTariff> userTariffs = new ArrayList<>();


    JDBCUserTariffDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method for inserting new userTariff
     *
     * @param entity
     */
    @Override
    public void create(UserTariff entity) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_TARIFF)) {
            statement.setLong(1, entity.getUserId());
            statement.setLong(2, entity.getTariffId());
            statement.setString(3, entity.getService());
            statement.setLong(4, entity.getUserId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method, for searching userTariff by id
     *
     * @param id
     * @return userTariff found entity
     */
    @Override
    public UserTariff findById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_TARIFF_BY_ID)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            UserTariff userTariff = null;

            if (rs.next()) {
                userTariff = userTariffMapper.extractFromResultSet(rs);
            }
            rs.close();
            return userTariff;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserTariff> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_TARIFF)) {

            return listFinder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method for updating userTariffs
     * @param entity
     */
    @Override
    public void update(UserTariff entity) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_TARIFF)) {
            statement.setLong(1, entity.getUserId());
            statement.setLong(2, entity.getTariffId());
            statement.setString(3, entity.getService());
            statement.setLong(4, entity.getUserId());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for deleting userTariff by id
     * @param id
     */
    @Override
    public void delete(int id) {

    }

    /**
     * Method for closing connection to db
     */
    @Override
    public void close() {

    }

    /**
     * Method, that search for userTariffs by user
     * @param id of user
     * @return list of userTariffs found
     */
    @Override
    public List<UserTariff> findTariffsByUserIdAndService(Long id, String service) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_TARIFFS_BY_USER_ID_AND_SERVICE)) {
            statement.setLong(1, id);
            statement.setString(2, service);

            return listFinder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserTariff> findTariffsByUserId(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_TARIFFS_BY_USER_ID)) {
            statement.setLong(1, id);

            return listFinder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method, that search for userTariffs by tariff
     * @param id of tariff
     * @return list of userTariffs found
     */
    @Override
    public List<UserTariff> findUsersByTariffId(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USERS_BY_TARIFF_ID)) {
            statement.setLong(1, id);

            return listFinder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<UserTariff> findServicesByUserId(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_SERVICES_BY_USER_ID)) {
            statement.setLong(1, id);

            return listFinder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Method for executing statement and fetching users data from result set.
     * Method to eliminate duplicate code
     * @param statement to execute
     * @return list of userTariffs found
     * @throws SQLException
     */
    public List<UserTariff> listFinder(PreparedStatement statement) throws SQLException {
        List<UserTariff> userTariffs = new ArrayList<>();
        ResultSet rs = statement.executeQuery();
        while(rs.next()) {
            UserTariff userTariff = userTariffMapper.extractFromResultSet(rs);
            userTariffs.add(userTariff);
        }
        rs.close();
        return userTariffs;
    }
}
