package com.gtmworks.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class NoteDTO {

	private Integer noteId;

	private String title;

	private String content;

	private Date createdDate;






}
