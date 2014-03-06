package com.nullpointerexception.hackathon.app;

/**
 * Created by James on 06/03/2014.
 */
public class PotHole {
    private String name;
    private String longname;
    private String description;
    private double latitude;
    private double longitude;

    public PotHole(String name, String longname, String description, double latitude, double longitude) {
        this.name = name==null?"":name;
        this.longname = longname==null?"":longname;
        this.description = description==null?"":description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getLongname() {
        return longname;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
