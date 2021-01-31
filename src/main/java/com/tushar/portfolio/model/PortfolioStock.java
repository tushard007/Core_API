package com.tushar.portfolio.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="t_Portfolio_Stock")
@Getter
@Setter
public class PortfolioStock {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    private int stock_id;
    private String current_price;
    private Date txn_date;
    private float other_cost;
    private int available_quantity;
    private float invested_amount;
    private float current_amount;
    private float profit_loss;
    private String broker;
    private String txn_type;
    private String category;
    private String notes;
    @CreationTimestamp
    private Date date_created;
    @Column(name = "time_stamp")
    @UpdateTimestamp
    private Date timestamp;




}
