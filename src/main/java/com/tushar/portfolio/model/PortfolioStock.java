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
    private Integer stock_id;
    private Float current_price;
    private Date txn_date;
    private Float other_cost;
    private Integer available_quantity;
    private Float invested_amount;
    private Float current_amount;
    private Float profit_loss;
    private Float avg_buy_price;
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
