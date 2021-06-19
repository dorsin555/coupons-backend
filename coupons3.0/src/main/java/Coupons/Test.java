package Coupons;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import Coupons.DailyJob.CouponExpirationDailyJob;
import Coupons.LoginManager.ClientType;
import Coupons.LoginManager.LoginManager;
import Coupons.entities.Category;
import Coupons.entities.Company;
import Coupons.entities.Coupon;
import Coupons.entities.Customer;
import Coupons.exception.CouponException;
import Coupons.repository.CouponRepository;
import Coupons.services.AdminService;
import Coupons.services.CompanyService;
import Coupons.services.CustomerService;

@Component
@Scope("singleton")
public class Test {
	
	 @Autowired
	 private CouponExpirationDailyJob dailyJob;
	 
	 @Autowired
	 private LoginManager login;
	 
	 @Autowired
	 private AdminService as;
	 
	 @Autowired
	 private CouponRepository couponRepository;
	 
	 public void startAll() {
		 System.out.println("==========================");
		 System.out.println("========= Welcome ========");
		dailyJob.run();;
	 }
	 
	 public void endAll() {
		 System.out.println("==========================");
		 System.out.println("========= Goodbye ========");
		 dailyJob.stopTask();
	 }
	 
	 public void TestAdmin() throws CouponException {
			 AdminService ads = (AdminService) login.login("admin@admin.com", "admin", ClientType.ADMIN);
			 System.out.println("Admin is logged in");
			 
			 System.out.println("-Adding companies-");
			 Company company = new Company("Park", "Park@gmail.com", "parker");
			 ads.addCompany(company);
			 Company company2 = new Company("Ebay", "Ebay@gmail.com", "ebay");
			 ads.addCompany(company2);
			 
			 System.out.println("===================");
			 System.out.println("-Updating company-");
			 Company companyUpdate = new Company("Parker", "Parker@gmail.com", "parker");
			 ads.updateCompany(companyUpdate);
			 
			 System.out.println("===================");
			 System.out.println("-Getting company-");
			 ads.getCompany(1L);
			 
			 System.out.println("===================");
			 System.out.println("-Getting All companies-");
			 List<Company> companies = as.getAllCompanies();
			 System.out.println(companies);
			 
			 System.out.println("===================");
			 System.out.println("-Deleting company-");
			 as.deleteCompany(1L);
			 
			 System.out.println("===================");
			 System.out.println("-Adding customers-");
			 Customer customer = new Customer("Mark", "Grogs", "Mark55@gmail.com", "MarkG");
			 Customer customer2 = new Customer("Ben", "Grogs", "Ben55@gmail.com", "BenG");
			 ads.addCustomer(customer);
			 ads.addCustomer(customer2);
			 
			 System.out.println("===================");
			 System.out.println("-Updating customers-");
			 Customer customerUpdate = new Customer("Ben", "Bob", "BenBob@gmail.com", "BenB");
			 ads.updateCustomer(customerUpdate);
			 
			 System.out.println("===================");
			 System.out.println("-Getting customer-");
			 ads.getCustomer(1L);
			 
			 System.out.println("===================");
			 System.out.println("-Getting All customers-");
			 List<Customer> customers = as.getAllCustomers();
			 System.out.println(customers); 
	 }
	 
	 public void TestCompany() throws CouponException {
			 CompanyService compS = (CompanyService) login.login("Park@gmail.com", "Park", ClientType.COMPANY);
			 System.out.println("Company logged in!");
			 
			 Company logged = compS.getCompanyDetails();
			 
			 LocalDate startDate1 = LocalDate.of(2021, 12, 8);
			 Date startsqlDate = Date.valueOf(startDate1);
			 LocalDate endDate1 = LocalDate.of(2021, 12, 28);
			 Date endsqlDate = Date.valueOf(endDate1);
			 
			 System.out.println("Adding coupon!");
			 Coupon c1 = new Coupon(Category.FOOD, "Pork", "Beacon", startsqlDate, endsqlDate,
					 15, 5.5, "Joicy Beacon", logged);
			 compS.addCoupon(c1);
			 
			 LocalDate startDate2 = LocalDate.of(2021, 12, 8);
			 Date startsqlDate2 = Date.valueOf(startDate2);
			 LocalDate endDate2 = LocalDate.of(2021, 12, 29);
			 Date endsqlDate2 = Date.valueOf(endDate2);
			 Coupon c2 = new Coupon(Category.SPROTS, "2in1", "2 yoga classes for the price of one",
					 startsqlDate2, endsqlDate2, 10, (double) 20, "Best Gym Ever", logged);
			 System.out.println("Updating coupon");
			 compS.updateCoupon(c2);
			 
			 System.out.println("Get company coupons");
			 List<Coupon> compCoupons = compS.getCompanyCoupons();
			 System.out.println(compCoupons);
			 
			 System.out.println("Get company coupons by category");
			 List<Coupon> comCouponsByCategory = compS.getCompanyCoupons(Category.FOOD);
			 System.out.println(comCouponsByCategory);
			 
			 System.out.println("Get company Coupons by Max price");
			 List<Coupon> comCouponsByPrice = compS.getCompanyCoupons(6.5);
			 System.out.println(comCouponsByPrice);
			 
			 System.out.println("Get company details");
			 Company comp = compS.getCompanyDetails();
			 System.out.println(comp);
			 
			 System.out.println("Deleting Coupon");
			 compS.deleteCoupon(1L);
	 }
	 
	 public void TestCustomer() throws CouponException {
		 if(login.login("dor@walla.com", "12345dor", ClientType.CUSTOMER) != null) {
			 CustomerService custS = (CustomerService) login.login("admin@admin.com", "admin", ClientType.ADMIN);
			 System.out.println("Customer is logged in");
			 
			 System.out.println("Purchasing coupon");
			 Coupon c = couponRepository.getOne(1L);
			 if(c != null) {
				 custS.purchaseCoupon(1L);
			 } 
			 
			 System.out.println("Get customer coupons");
			 List<Coupon> custCoupons = custS.getCustomerCoupons();
			 System.out.println(custCoupons);
			 
			 System.out.println("Get customer coupons by category");
			 List<Coupon> custCouponsByCategory = custS.getCustomerCoupons(Category.FOOD);
			 System.out.println(custCouponsByCategory);
			 
			 System.out.println("Get customer coupons by Max price");
			 List<Coupon> custCouponsByPrice = custS.getCustomerCoupons(6D);
			 System.out.println(custCouponsByPrice);
			 
			 System.out.println("Get customer Details");
			 Customer cust = custS.getCustomerDetails();
			 System.out.println(cust);
		 }
	 }

	 
}
