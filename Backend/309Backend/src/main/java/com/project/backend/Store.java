package com.project.backend;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Stores")
public class Store {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private int manager;
    private double latitude;
    private double longitude;
    private Time opens_sunday;
    private Time opens_monday;
    private Time opens_tuesday;
    private Time opens_wednesday;
    private Time opens_thursday;
    private Time opens_friday;
    private Time opens_saturday;
    private Time closes_sunday;
    private Time closes_monday;
    private Time closes_tuesday;
    private Time closes_wednesday;
    private Time closes_thursday;
    private Time closes_friday;
    private Time closes_saturday;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int managerID) {
        this.manager = managerID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Time getopens_sunday() {return opens_sunday;}
    public void setopens_sunday(Time hours) {this.opens_sunday = hours;}
    public Time getopens_monday() {return opens_monday;}
    public void setopens_monday(Time hours) {this.opens_monday = hours;}
    public Time getopens_tuesday() {return opens_tuesday;}
    public void setopens_tuesday(Time hours) {this.opens_tuesday = hours;}
    public Time getopens_wednesday() {return opens_wednesday;}
    public void setopens_wednesday(Time hours) {this.opens_wednesday = hours;}
    public Time getopens_thursday() {return opens_thursday;}
    public void setopens_thursday(Time hours) {this.opens_thursday = hours;}
    public Time getopens_friday() {return opens_friday;}
    public void setopens_friday(Time hours) {this.opens_friday = hours;}
    public Time getopens_saturday() {return opens_saturday;}
    public void setopens_saturday(Time hours) {this.opens_saturday = hours;}

    public Time getcloses_sunday() {return closes_sunday;}
    public void setcloses_sunday(Time hours) {this.closes_sunday = hours;}
    public Time getcloses_monday() {return closes_monday;}
    public void setcloses_monday(Time hours) {this.closes_monday = hours;}
    public Time getcloses_tuesday() {return closes_tuesday;}
    public void setcloses_tuesday(Time hours) {this.closes_tuesday = hours;}
    public Time getcloses_wednesday() {return closes_wednesday;}
    public void setcloses_wednesday(Time hours) {this.closes_wednesday = hours;}
    public Time getcloses_thursday() {return closes_thursday;}
    public void setcloses_thursday(Time hours) {this.closes_thursday = hours;}
    public Time getcloses_friday() {return closes_friday;}
    public void setcloses_friday(Time hours) {this.closes_friday = hours;}
    public Time getcloses_saturday() {return closes_saturday;}
    public void setcloses_saturday(Time hours) {this.closes_saturday = hours;}

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", manager id='" + manager + '\'' +
                ", lat/long=('" + latitude + "','" + longitude + "')" +
                ", opens_sunday='" + opens_sunday + '\'' +
                ", opens_monday='" + opens_monday + '\'' +
                ", opens_tuesday='" + opens_tuesday + '\'' +
                ", opens_wednesday='" + opens_wednesday + '\'' +
                ", opens_thursday='" + opens_thursday + '\'' +
                ", opens_friday='" + opens_friday + '\'' +
                ", opens_saturday='" + opens_saturday + '\'' +
                ", closes_sunday='" + closes_sunday + '\'' +
                ", closes_monday='" + closes_monday + '\'' +
                ", closes_tuesday='" + closes_tuesday + '\'' +
                ", closes_wednesday='" + closes_wednesday + '\'' +
                ", closes_thursday='" + closes_thursday + '\'' +
                ", closes_friday='" + closes_friday + '\'' +
                ", closes_saturday='" + closes_saturday + '\'' +
                '}';
    }
}