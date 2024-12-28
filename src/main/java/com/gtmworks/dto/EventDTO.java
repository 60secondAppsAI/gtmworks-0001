package com.gtmworks.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class EventDTO {

	private Integer eventId;

	private String eventName;

	private Date startDate;

	private Date endDate;

	private String location;






}