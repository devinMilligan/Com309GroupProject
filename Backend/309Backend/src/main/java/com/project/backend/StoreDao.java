package com.project.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class StoreDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Store store) {
        String sql = "insert into Stores (name, address, manager, latitude, longitude, opens_sunday, closes_sunday, opens_monday, closes_monday, opens_tuesday, closes_tuesday," 
        		+ "opens_wednesday, closes_wednesday, opens_thursday, closes_thursday, opens_friday, closes_friday, opens_saturday, closes_saturday)"
        				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, store.getName(), store.getAddress(), store.getManager(), store.getLatitude(), store.getLongitude(), store.getHours().get(0).get(0), store.getHours().get(0).get(1),
        		store.getHours().get(1).get(0),store.getHours().get(1).get(1), store.getHours().get(2).get(0),store.getHours().get(2).get(1),
        		store.getHours().get(3).get(0),store.getHours().get(3).get(1), store.getHours().get(4).get(0),store.getHours().get(4).get(1),
        		store.getHours().get(5).get(0),store.getHours().get(5).get(1), store.getHours().get(6).get(0),store.getHours().get(6).get(1));
    }
    
    public void update(Store store) {
        String sql = "UPDATE Stores SET name=?, address=?, manager=?, latitude=?, longitude=?, opens_sunday=?, closes_sunday=?, opens_monday=?, closes_monday=?, "
        		+ "opens_tuesday=?, closes_tuesday=?, opens_wednesday=?, closes_wednesday=?, opens_thursday=?, closes_thursday=?, opens_friday=?, closes_friday=?, "
        				+ "opens_saturday=?, closes_saturday=? WHERE id=?";
        jdbcTemplate.update(sql, store.getName(), store.getAddress(), store.getManager(), store.getLatitude(), store.getLongitude(), store.getHours().get(0).get(0), store.getHours().get(0).get(1),
        		store.getHours().get(1).get(0),store.getHours().get(1).get(1), store.getHours().get(2).get(0),store.getHours().get(2).get(1),
        		store.getHours().get(3).get(0),store.getHours().get(3).get(1), store.getHours().get(4).get(0),store.getHours().get(4).get(1),
        		store.getHours().get(5).get(0),store.getHours().get(5).get(1), store.getHours().get(6).get(0),store.getHours().get(6).get(1),
        		store.getId());
    }

    public List<Store> loadAll() {
        return jdbcTemplate.query("select * from Stores", (resultSet, i) -> {
            return toStore(resultSet);
        });
    }
    
    public List<Store> search(int manager) {
    	String sql = "SELECT * FROM Stores WHERE manager=(?)";
    	List<Store> list = jdbcTemplate.query(sql,
                new Object[]{manager},
                (resultSet, i) -> {return toStore(resultSet);}
        );
    	
    	return list;
    }

    private Store toStore(ResultSet resultSet) throws SQLException {
        Store store = new Store();
        store.setId(resultSet.getLong("ID"));
        store.setName(resultSet.getString("NAME"));
        store.setAddress(resultSet.getString("ADDRESS"));
        store.setManager(resultSet.getInt("MANAGER"));
        store.setLatitude(resultSet.getDouble("LATITUDE"));
        store.setLongitude(resultSet.getDouble("LONGITUDE"));
        
        LinkedList<List<Integer>> hours = new LinkedList<List<Integer>>();
        List<Integer> sunHours = new ArrayList<Integer>();
        sunHours.add(resultSet.getInt("OPENS_SUNDAY"));
        sunHours.add(resultSet.getInt("CLOSES_SUNDAY"));
        List<Integer> monHours = new ArrayList<Integer>();
        monHours.add(resultSet.getInt("OPENS_MONDAY"));
        monHours.add(resultSet.getInt("CLOSES_MONDAY"));
        List<Integer> tuesHours = new ArrayList<Integer>();
        tuesHours.add(resultSet.getInt("OPENS_TUESDAY"));
        tuesHours.add(resultSet.getInt("CLOSES_TUESDAY"));
        List<Integer> wedHours = new ArrayList<Integer>();
        wedHours.add(resultSet.getInt("OPENS_WEDNESDAY"));
        wedHours.add(resultSet.getInt("CLOSES_WEDNESDAY"));
        List<Integer> thursHours = new ArrayList<Integer>();
        thursHours.add(resultSet.getInt("OPENS_THURSDAY"));
        thursHours.add(resultSet.getInt("CLOSES_THURSDAY"));
        List<Integer> friHours = new ArrayList<Integer>();
        friHours.add(resultSet.getInt("OPENS_FRIDAY"));
        friHours.add(resultSet.getInt("CLOSES_FRIDAY"));
        List<Integer> satHours = new ArrayList<Integer>();
        satHours.add(resultSet.getInt("OPENS_SATURDAY"));
        satHours.add(resultSet.getInt("CLOSES_SATURDAY"));
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