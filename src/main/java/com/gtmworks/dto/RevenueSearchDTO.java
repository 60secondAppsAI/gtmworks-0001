package com.gtmworks.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RevenueSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer revenueId;
	
	private Integer year;
	
	private Integer quarter;
	
	private double revenueAmount;
	
}
