package com.tushar.portfolio.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;


	@Entity
	@Table(name="t_stock_core_data")
	@Getter
	@Setter
	public class StockCoreDataEntity {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		@Column(unique=true)
		private String company_name;
		@Column(unique=true)
		private String nse_code;
		@Column(unique=true)
		private int bse_code;
		@Column(unique=true)
		private String ISIN;
		private Date listed_date;
		private String industry;
		private String sector;
		private int face_value;
		@Column(name = "date_created")
	    @CreationTimestamp
	    private Date dateCreated;
	    @Column(name = "timestamp")
	    @UpdateTimestamp
	    private Date timestamp;
}