package Coupons;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import Coupons.LoginManager.ClientType;
import Coupons.LoginManager.LoginManager;
import Coupons.entities.Category;
import Coupons.entities.Company;
import Coupons.entities.Coupon;
import Coupons.exception.CouponException;
import Coupons.services.CompanyService;

public class TestCompany {

	public static void main(String[] args) throws CouponException {
		ApplicationContext ctx = SpringApplication.run(CouponsApplication.class, args);
		 
		 LoginManager login = ctx.getBean(LoginManager.class);
		 
		 try {
		 CompanyService cs = (CompanyService) login.login("Park@gmail.com", "parker",ClientType.COMPANY);
		 System.out.println("Company logged in!");
//		 
		 Company logged = cs.getCompanyDetails();
//		 
		 LocalDate startDate1 = LocalDate.of(2021, 12, 8);
		 Date startsqlDate = Date.valueOf(startDate1);
		 LocalDate endDate1 = LocalDate.of(2021, 12, 28);
		 Date endsqlDate = Date.valueOf(endDate1);
//		 
		 System.out.println("Adding coupon!");
		 Coupon c1 = new Coupon(Category.FOOD, "Cookiy", "cookie", startsqlDate, endsqlDate,
				 15, 6.3, "Juicy Cookie",logged);
		 cs.addCoupon(c1);
		 
//		 LocalDate startDate2 = LocalDate.of(2021, 12, 8);
//		 Date startsqlDate2 = Date.valueOf(startDate2);
//		 LocalDate endDate2 = LocalDate.of(2021, 12, 29);
//		 Date endsqlDate2 = Date.valueOf(endDate2);
//		 Coupon c2 = new Coupon(Category.Sports, "2in1", "2 yoga classes for the price of one",
//				 startsqlDate2, endsqlDate2, 10, (double) 20, "Best Gym Ever",logged);
//		 System.out.println("Updating coupon");
//		 cs.updateCoupon(1L, c2); //works
		 
//		 System.out.println("Get company coupons");
//		 List<Coupon> compCoupons = cs.getCompanyCoupons();//works
//		 System.out.println(compCoupons);
		 
//		 System.out.println("Get company coupons by category");
//		 List<Coupon> comCouponsByCategory = cs.getCompanyCoupons(Category.FOOD);//works
//		 System.out.println(comCouponsByCategory);
		 
//		 System.out.println("Get company Coupons by Max price");
//		 List<Coupon> comCouponsByPrice = cs.getCompanyCoupons(6.5);//works
//		 System.out.println(comCouponsByPrice);
		 
//		 System.out.println("Deleting Coupon");
//		 cs.deleteCoupon(1L);//works
		 
//		 System.out.println("Get company details");
//		 Company comp = cs.getCompanyDetails();//works
//		 System.out.println(comp);
		 
		 Thread.sleep(10000);

			} catch (Exception e) {
				throw new CouponException("Apptest failed", e);
				
			
		}

	}

}
