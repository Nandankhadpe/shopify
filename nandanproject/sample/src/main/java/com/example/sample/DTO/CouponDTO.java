
package com.example.sample.DTO;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Data;

//DTO-Data transfer object

//data acts as holder properties
@Data
public class CouponDTO {
	private Long id;
	private String name;

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
	public int getEventsId() {
		return eventsId;
	}
	
	public void setEventsId(int i) {
		this.eventsId = i;
	}

	


	// many to one mapping col join

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", referencedColumnName = "event_Id")

	// object id
	// DTO-Data transfer objectid
	private int eventsId;


	
}
