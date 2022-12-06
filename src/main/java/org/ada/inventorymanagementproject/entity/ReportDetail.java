package org.ada.inventorymanagementproject.entity;

import javax.persistence.*;

@Entity
@Table(name = "report_detail")
public class ReportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_code", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "summary_report_id", nullable = false)
    private SummaryReport summaryReport;

    public ReportDetail() {

    }

    public ReportDetail(Integer quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

    public ReportDetail(int quantity, SummaryReport summaryReport) {
        this.quantity = quantity;
        this.summaryReport = summaryReport;
    }

    public ReportDetail(int quantity, Item item, SummaryReport summaryReport) {
        this.quantity = quantity;
        this.item = item;
        this.summaryReport = summaryReport;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void modifyAttributeValue(String attributeName, Object newValue) {
        switch (attributeName) {
            case "quantity":
                this.quantity = (Integer) newValue;
                break;

        }
    }

    public Item getItem() {
        return item;
    }

    public SummaryReport getSummaryReport() {
        return summaryReport;
    }
}