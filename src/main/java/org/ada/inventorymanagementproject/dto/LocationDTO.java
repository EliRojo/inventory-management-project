package org.ada.inventorymanagementproject.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class LocationDTO {

    private Integer id;
    private char section;

    @JsonAlias("location_column")
    private short locationColumn;
    @JsonAlias("location_Row")
    private short locationRow;


    public LocationDTO(){}

    public LocationDTO(Integer id, char section, short locationColumn, short locationRow) {
        this.id = id;
        this.section = section;
        this.locationColumn = locationColumn;
        this.locationRow = locationRow;
    }

    public Integer getId() {
        return id;
    }

    public char getSection() {
        return section;
    }

    public short getLocationColumn() {
        return locationColumn;
    }

    public short getLocationRow() {
        return locationRow;
    }
}
