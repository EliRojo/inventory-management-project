package org.ada.inventorymanagementproject.dto;

public class LocationDTO {

    private Integer id;
    private char section;
    private short column;
    private short row;

    public LocationDTO(){}

    public LocationDTO(Integer id, char section, short column, short row) {
        this.id = id;
        this.section = section;
        this.column = column;
        this.row = row;
    }

    public Integer getId() {
        return id;
    }

    public char getSection() {
        return section;
    }

    public short getColumn() {
        return column;
    }

    public short getRow() {
        return row;
    }
}
