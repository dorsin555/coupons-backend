package Coupons;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import Coupons.LoginManager.ClientType;
import Coupons.LoginManager.LoginManager;
import Coupons.entities.Category;
import Coupons.entities.Coupon;
import Coupons.entities.Customer;
import Coupons.exception.CouponException;
import Coupons.repository.CouponRepository;
import Coupons.services.CustomerService;

public class TestCustomer {

	public static void main(String[] args) throws CouponException {
		ApplicationContext ctx = SpringApplication.run(CouponsApplication.class, args);
		
		LoginManager login = ctx.getBean(LoginManager.class);
		
		CouponRepository couponRepository = ctx.getBean(CouponRepository.class);

		try {
			CustomerService custS = (CustomerService) login.login("Mark55@gmail.com", "MarkG", ClientType.CUSTOMER);
			 System.out.println("customer logged in!");
			 
			 System.out.println("Purchasing coupon");
			 Coupon c = couponRepository.getOne(1L);
			 if(c != null) { 
				 custS.purchaseCoupon(1L);  //works
			 }
			 
			 System.out.println("Get customer coupons");
			 List<Coupon> custCoupons = custS.getCustomerCoupons();//works
			 System.out.println(custCoupons);
			 
			 System.out.println("Get customer coupons by category");
			 List<Coupon> custCouponsByCategory = custS.getCustomerCoupons(Category.FOOD);//works
			 System.out.println(custCouponsByCategory);
			 
			 System.out.println("Get customer coupons by Max price");
			 List<Coupon> custCouponsByPrice = custS.getCustomerCoupons(6D);//works
			 System.out.println(custCouponsByPrice);
			 
			 System.out.println("Get customer Details");
			 Customer cus = custS.getCustomerDetails();//works
			 System.out.println(cus);
			 
			Thread.sleep(10000);
		}catch (Exception e) {
			e.printStackTrace();
			throw new CouponException("Apptest failed", e);	
	}
	}
}
