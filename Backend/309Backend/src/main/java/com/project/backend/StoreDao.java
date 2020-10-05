package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StoreDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Store store) {
        String sql = "insert into Stores (name, ) values (?, )";
        jdbcTemplate.update(sql, store.getName());
    }

    public List<Store> loadAll() {
        return jdbcTemplate.query("select * from Stores", (resultSet, i) -> {
            return toStore(resultSet);
        });
    }

    private Store toStore(ResultSet resultSet) throws SQLException {
        Store store = new Store();
        store.setId(resultSet.getLong("ID"));
        store.setName(resultSet.getString("NAME"));
        store.setAddress(resultSet.getString("ADDRESS"));
        store.setPhone(resultSet.getString("PHONE"));
        store.setLatitude(resultSet.getDouble("LATITUDE"));
        store.setLongitude(resultSet.getDouble("LONGITUDE"));
        return store;
    }
}