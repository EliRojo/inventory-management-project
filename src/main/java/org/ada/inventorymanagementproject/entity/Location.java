package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "location")

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private char section;

    @Column(nullable = false)
    private short column;

    @Column(nullable = false)
    private short row;

    public Location(){}

    public Location(Integer id, char section, short column, short row) {
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
