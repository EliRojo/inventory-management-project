package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "report_detail")
public class ReportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_code", nullable = false) //quiero que me traiga tambien el nombre
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", nullable = false)
    private SummaryReport summaryReport;

    public ReportDetail(){}

    public ReportDetail(Integer id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }
}
