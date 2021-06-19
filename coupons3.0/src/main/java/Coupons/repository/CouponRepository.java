package Coupons.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import Coupons.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	@Query(value = "SELECT * from coupon", nativeQuery = true)
	List<Coupon> getAllCoupons();
	
	@Query(value = "SELECT * from coupon join cvc on coupon.coupon_id = cvc.coupon_id where company.id = :CompanyId", nativeQuery = true)
	List<Coupon> findCouponsByCompanyId(Long CompanyId);
	
	@Query(value = "SELECT * from coupon where End_date = :end_date", nativeQuery = true)
	List<Coupon> findCouponByEndDate(Date end_date);
	
	Coupon findByTitleIgnoreCase (String title);
	
	List<Coupon> findByCompanyId(Long companyId);
	
	List<Coupon> findByCustomersId(Long customerId);
	
	Coupon findBycouponId(Long couponId);

}
