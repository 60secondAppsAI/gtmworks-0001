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
@Table(name="notes")
@Getter @Setter @NoArgsConstructor
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="note_id")
	private Integer noteId;
    
  	@Column(name="title")
	private String title;
    
  	@Column(name="content")
	private String content;
    
  	@Column(name="created_date")
	private Date createdDate;
    
	




}
