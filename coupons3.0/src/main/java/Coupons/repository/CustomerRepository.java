package Coupons.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import Coupons.entities.Coupon;
import Coupons.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query(value = "SELECT * from customer",nativeQuery = true)
	List<Customer> getAllCustomers();
	
	@Query(value = "SELECT * from customer where id = ?",nativeQuery = true)
	Customer getCustomerById(Long customerId);
	
	Customer getById(Long customerId);
	
	@Query(value = "SELECT * from coupon join customer on customer.id = coupon.coupon_id "
			+ "where customer.id = ?",nativeQuery = true)
	List<Coupon> getCustomerCoupons(Long customerId);
	
	Optional<Customer> findByEmailAndPassword(String Email, String Password);
}
