package com.mieshkov.corplan.model.dao.impl;

import com.mieshkov.corplan.model.dao.ServiceDao;
import com.mieshkov.corplan.model.dao.mapper.ServiceMapper;
import com.mieshkov.corplan.model.entities.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mieshkov.corplan.containers.QueryContainer.FIND_ALL_SERVICES;

/**
 * @author Ivan Mieshkov
 */
public class JDBCServiceDao implements ServiceDao {
    private Connection connection;
    private ServiceMapper serviceMapper = new ServiceMapper();
    private Map<Long, Service> services = new HashMap<>();

    public JDBCServiceDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Service entity) {
    }

    @Override
    public Service findById(int id) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_SERVICES)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Service service = serviceMapper.extractFromResultSet(rs);
                serviceMapper.makeUnique(services, service);
            }
            rs.close();
            return new ArrayList<>(services.values());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

        @Override
    public void update(Service entity) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
