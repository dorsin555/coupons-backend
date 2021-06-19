package Coupons.LoginManager;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum ClientType {
	@Enumerated(EnumType.STRING)
ADMIN(1),
@Enumerated(EnumType.STRING)
COMPANY(2),
@Enumerated(EnumType.STRING)
CUSTOMER(3);
	
	private int id;

	private ClientType(int id) {
		this.id = id;
	}
	
	public ClientType getClientType(int id) {
		for (ClientType c : ClientType.values()) {
			if(c.id == id) {
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
