package com.gtmworks.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="assets")
@Getter @Setter @NoArgsConstructor
public class Asset {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="asset_id")
	private Integer assetId;
    
  	@Column(name="asset_name")
	private String assetName;
    
  	@Column(name="purchase_date")
	private Date purchaseDate;
    
  	@Column(name="value")
	private double value;
    
	




}
