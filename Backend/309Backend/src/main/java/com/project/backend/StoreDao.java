package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class StoreDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Store store) {
        String sql = "insert into Stores (name, address, latitude, longitude) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, store.getName(), store.getAddress(), store.getLatitude(), store.getLongitude());
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
        store.setLatitude(resultSet.getDouble("LATITUDE"));
        store.setLongitude(resultSet.getDouble("LONGITUDE"));
        
        LinkedList<List<Time>> hours = new LinkedList<List<Time>>();
        List<Time> sunHours = new ArrayList<Time>();
        sunHours.add(resultSet.getTime("OPENS_SUNDAY"));
        sunHours.add(resultSet.getTime("CLOSES_SUNDAY"));
        List<Time> monHours = new ArrayList<Time>();
        monHours.add(resultSet.getTime("OPENS_MONDAY"));
        monHours.add(resultSet.getTime("CLOSES_MONDAY"));
        List<Time> tuesHours = new ArrayList<Time>();
        tuesHours.add(resultSet.getTime("OPENS_TUESDAY"));
        tuesHours.add(resultSet.getTime("CLOSES_TUESDAY"));
        List<Time> wedHours = new ArrayList<Time>();
        wedHours.add(resultSet.getTime("OPENS_WEDNESDAY"));
        wedHours.add(resultSet.getTime("CLOSES_WEDNESDAY"));
        List<Time> thursHours = new ArrayList<Time>();
        thursHours.add(resultSet.getTime("OPENS_THURSDAY"));
        thursHours.add(resultSet.getTime("CLOSES_THURSDAY"));
        List<Time> friHours = new ArrayList<Time>();
        friHours.add(resultSet.getTime("OPENS_FRIDAY"));
        friHours.add(resultSet.getTime("CLOSES_FRIDAY"));
        List<Time> satHours = new ArrayList<Time>();
        satHours.add(resultSet.getTime("OPENS_SATURDAY"));
        satHours.add(resultSet.getTime("CLOSES_SATURDAY"));
        hours.add(sunHours);
        hours.add(monHours);
        hours.add(tuesHours);
        hours.add(wedHours);
        hours.add(thursHours);
        hours.add(friHours);
        hours.add(satHours);        
        store.setHours(hours);
        
        return store;
    }
}