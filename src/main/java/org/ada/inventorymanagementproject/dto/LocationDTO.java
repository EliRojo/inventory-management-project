package org.ada.inventorymanagementproject.dto;

public class LocationDTO {

    private Integer id;
    private char section;

    private short Column;
    private short Row;


    public LocationDTO(Integer id, char section, short column, short row) {
        this.id = id;
        this.section = section;
        Column = column;
        Row = row;
    }

    public Integer getId() {
        return id;
    }

    public char getSection() {
        return section;
    }

    public short getColumn() {
        return Column;
    }

    public short getRow() {
        return Row;
    }
}
