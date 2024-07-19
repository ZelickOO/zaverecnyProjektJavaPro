package cz.itnetwork.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private Integer invoiceNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity buyer;

    @Column(nullable = false)
    private LocalDate issued;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private  String product;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private int vat;
    @Column
    private String note;
}
