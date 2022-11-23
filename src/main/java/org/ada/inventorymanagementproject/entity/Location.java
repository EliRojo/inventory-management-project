package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "location")

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Character section;

    @Column(name = "location_Column" , nullable = false)
    private Short locationColumn;

    @Column(name = "location_Row" , nullable = false)
    private Short locationRow;

    public Location(){

    }

    public Location(Integer id, Character section, Short locationColumn, Short locationRow) {
        this.id = id;
        this.section = section;
        this.locationColumn = locationColumn;
        this.locationRow = locationRow;
    }

    public Integer getId() {
        return id;
    }

    public Character getSection() {
        return section;
    }

    public Short getLocationColumn() {
        return locationColumn;
    }

    public Short getLocationRow() {
        return locationRow;
    }
}
