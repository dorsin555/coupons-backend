package Coupons.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinTable(name = "customer_coupons",
	joinColumns = @JoinColumn(name = "id"),
	inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	@JsonIgnore
	private List<Coupon> coupons;
	
	public Customer() {}
	
	public Customer(String FirstName, String LastName, String Email, String password) {
		this.firstName = FirstName;
		this.lastName = LastName;
		this.email = Email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_Name) {
		firstName = first_Name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_Name) {
		lastName = last_Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

	public void addCoupon(Coupon cou) {
		if(this.coupons == null) {
			this.coupons = new ArrayList<Coupon>();
		}
		this.coupons.add(cou);
	}
	
}
