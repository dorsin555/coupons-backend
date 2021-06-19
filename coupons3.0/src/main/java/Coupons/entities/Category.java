package Coupons.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Category {
	
	@Enumerated(EnumType.STRING)
	@JsonProperty("FOOD")
	FOOD(1),
	@Enumerated(EnumType.STRING)
	@JsonProperty("ELECTRICITY")
	ELECTRICITY(2),
	@Enumerated(EnumType.STRING)
	@JsonProperty("VACATION")
	VACATION(3),
	@Enumerated(EnumType.STRING)
	@JsonProperty("SPORTS")
	SPROTS(4);
	
	private int id;
	
	private Category(int id) {
		this.id = id;
	}
	
	public static Category getCategoryId(int id) {
		for (Category c : Category.values()) {
			if(c.id==id) {
				return c;
			}
		}
		return null;
	}
	
	public static Category getCategoryId(Category category) {
		for (Category c : Category.values()) {
			if(c == category) {
				return c;
			}
		}
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
	
