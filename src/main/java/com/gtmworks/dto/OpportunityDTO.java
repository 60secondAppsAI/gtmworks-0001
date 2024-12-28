package com.gtmworks.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class OpportunityDTO {

	private Integer opportunityId;

	private String opportunityName;

	private Date closeDate;

	private String stage;

	private double amount;






}
