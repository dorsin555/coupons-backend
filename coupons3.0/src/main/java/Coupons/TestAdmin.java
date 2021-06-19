package Coupons;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import Coupons.Controllers.LoginController;
import Coupons.LoginManager.ClientType;
import Coupons.LoginManager.LoginManager;
import Coupons.entities.Company;
import Coupons.entities.Customer;
import Coupons.exception.CouponException;
import Coupons.services.AdminService;

public class TestAdmin {

	public static void main(String[] args) throws CouponException {
		ApplicationContext ctx = SpringApplication.run(CouponsApplication.class, args);

		 AdminService as = ctx.getBean(AdminService.class);
		 
//		 LoginManager login = ctx.getBean(LoginManager.class);
		 
		 LoginController login = ctx.getBean(LoginController.class);

		try {
//			 login.login("admin@admin.co.il", "admin", ClientType.ADMIN);
//			 System.out.println("Admin is logged in");// works!
			
//			login.login("admin@admin.co.il", "admin", ClientType.ADMIN);
			 
//			 System.out.println("-Adding companies-");
//			 Company company = new Company("Park", "Park@gmail.com", "parker");
//			 as.addCompany(company);
//			 Company company2 = new Company("Ebay", "Ebay@gmail.com", "ebay");
//			 as.addCompany(company2); /// works!
//			 
//			 System.out.println("===================");
//			 System.out.println("-Updating company-");
//			 Company companyUpdate = new Company("None", "None@gmail.com", "none123");
//			 as.updateCompany(companyUpdate);// works!
//			 
//			 System.out.println("===================");
//			 System.out.println("-Getting company-");
//			 as.getCompany(1L);// works!
//			 
//			 System.out.println("===================");
//			 System.out.println("-Getting All companies-");
//			 List<Company> companies = as.getAllCompanies();
//			 System.out.println(companies);// works!
//			 
//			 System.out.println("===================");
//			 System.out.println("-Deleting company-");
//			 as.deleteCompany(1L);// works!
			 
//			 System.out.println("===================");
//			 System.out.println("-Adding customers-");
//			 Customer customer = new Customer("Mark", "Grogs", "Mark55@gmail.com", "MarkG");
//			 Customer customer2 = new Customer("Ben", "Grogs", "Ben55@gmail.com", "BenG");
//			 as.addCustomer(customer);// works!
//			 as.addCustomer(customer2);
			 
//			 System.out.println("===================");
//			 System.out.println("-Updating customers-");
//			 Customer customerUpdate = new Customer("Ben", "Bob", "BenBob@gmail.com", "BenB");
//			 as.updateCustomer(customerUpdate);// works!
			 
//			 System.out.println("===================");
//			 System.out.println("-Getting customer-");
//			 System.out.println(as.getCustomer(1L));// works!
//			 
//			 System.out.println("===================");
//			 System.out.println("-Getting All customers-");
//			 List<Customer> customers = as.getAllCustomers();
//			 System.out.println(customers);// works!
			
		Thread.sleep(10000);

		} catch (Exception e) {
			e.printStackTrace();
			
		
	}

}
}