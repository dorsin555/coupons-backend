package Coupons.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;
	@ManyToMany(cascade = {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	@JoinTable(name = "customer_coupons",
	joinColumns = @JoinColumn(name = "coupon_id"),
	inverseJoinColumns = @JoinColumn(name = "id"))
	@JsonIgnore
	private List<Customer> customers;
	@Enumerated(EnumType.STRING)
	private Category categoryId;
	private String title;
	private String description;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date start_date;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date end_date;
	private Integer amount;
	private Double price;
	private String image;
	@ManyToOne(cascade = {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.REFRESH})
			@JoinColumn(name = "company_id")
	@JsonIgnore
	private Company company;
	
	public Coupon() {}
	
	public Coupon(Coupons.entities.Category categoryId, String title, String description,
			Date start_date, Date end_date, Integer amount, Double price, String image, Company company) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.amount = amount;
		this.price = price;
		this.image = image;
		this.company = company;
	}

	public Long getId() {
		return couponId;
	}
	public void setId(Long id) {
		this.couponId = id;
	}
	
	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Long getCoupon_id() {
		return couponId;
	}

	public void setCoupon_id(Long coupon_id) {
		this.couponId = coupon_id;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + couponId + ", categoryId=" + categoryId + ", title=" + title
				+ ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}


}
