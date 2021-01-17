package com.tushar.portfolio.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.attribute.standard.DateTimeAtProcessing;

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
		private String nse_code;

		private Integer bse_code;

		private String isin;
		private Date listed_date;
		private String industry;
		private String sector;
		private Integer face_value;
	    @CreationTimestamp
	    private Date dateCreated;
	    @Column(name = "timestamp")
	    @UpdateTimestamp
	    private Date timestamp;
}