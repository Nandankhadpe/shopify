
package com.example.sample.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.Data;


//
//@Entity will give table
@Entity
@Data
public class Coupons {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
     private Long id;
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Events getEvents() {
		return events;
	}
	public void setEvents(Events events) {
		this.events = events;
	}

	private String name;
    
    //many to one mapping col join
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="events_id" ,referencedColumnName ="events_id")
    
    //object
    //DTO-Data transfer object
    private Events events;
	
    
}
