package Coupons.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Coupons.entities.Category;
import Coupons.entities.Coupon;
import Coupons.entities.Customer;
import Coupons.exception.CouponException;
import Coupons.repository.CouponRepository;
import Coupons.repository.CustomerRepository;

@Service
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService{
	
	private static Long cusId;
	
	@PersistenceContext
	private EntityManager em;
	
	private java.util.Date date = new java.util.Date();
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	public CustomerService() {}

	public CustomerService(Long cusId) {
		CustomerService.cusId = cusId;
	}

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	@Override
	public boolean login(String email, String password) throws CouponException {
		Optional<Customer> pot = customerRepository.findByEmailAndPassword(email, password);
		if(pot.isPresent()) {
			Customer c = pot.get();
			cusId = c.getId();
			return true;
		}else {
			throw new CouponException("wrong input");
		}
	}
	
	public Coupon purchaseCoupon(Long id) throws CouponException {
		try {
		Customer customer = customerRepository.getById(cusId);
		List<Coupon> customerCoupons = customer.getCoupons();
		Coupon coup = couponRepository.getOne(id);
		if(customerCoupons.contains(coup)) {
			throw new CouponException("You own this coupon already.");
		}
		if(coup.getAmount() <= 0) {
			throw new CouponException("Coupon is out of stock.");
		}
		if(coup.getEnd_date().before(date)) {
			throw new CouponException("Coupon is already expired.");
		}
		customer.addCoupon(coup);
		customerRepository.save(customer);
		int upAmount = coup.getAmount() - 1;
		coup.setAmount(upAmount);
		couponRepository.save(coup);
		return coup;
		}catch (Exception e) {
			e.printStackTrace();
			throw new CouponException("Coupon was not purchased. try again later" , e.getCause());
		}
	}
	
	public List<Coupon> getCustomerCoupons() {
		List<Coupon> customerCoupons = couponRepository.findByCustomersId(cusId);
		return customerCoupons;
	}
	
	public List<Coupon> getCustomerCoupons(Category category) throws CouponException{
		try {
		List<Coupon> customerCoupons = couponRepository.findByCustomersId(cusId);
		List<Coupon> couponBycategory = new ArrayList<Coupon>();
		for (Coupon coupon : customerCoupons) {
			if(coupon.getCategoryId().equals(category)) {
				couponBycategory.add(coupon);
			}
		}
		return couponBycategory;
		}catch (Exception e) {
			e.printStackTrace();
			throw new CouponException(e.getMessage(), e.getCause());
		}
	}
	
	public List<Coupon> getCustomerCoupons(Double max) {
		List<Coupon> customerCoupons = couponRepository.findByCustomersId(cusId);
		List<Coupon> couponByPrice = new ArrayList<Coupon>();
		for (Coupon coupon : customerCoupons) {
			if(coupon.getPrice() < max) {
				couponByPrice.add(coupon);
			}
		}
		return couponByPrice;
	}
	
	public Customer getCustomerDetails() {
		Customer systemCom = customerRepository.getById(cusId);
		if (systemCom != null) {
			return systemCom;
		}
		return null;
	}
	
	public List<Coupon> getAllCoupons (){
		List<Coupon> all = couponRepository.getAllCoupons();
		return all;
	}
	
	public Coupon getCouponDetails(Long id) {
		Coupon sysCou = couponRepository.getOne(id);
		if(sysCou != null) {
			return sysCou;
		}
		return null;
	}

}
