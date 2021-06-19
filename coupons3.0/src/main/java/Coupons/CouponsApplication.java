package Coupons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import Coupons.DailyJob.CouponExpirationDailyJob;
import Coupons.exception.CouponException;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CouponsApplication {

	public static void main(String[] args) throws CouponException {
		ApplicationContext ctx = SpringApplication.run(CouponsApplication.class, args);
		
//		Test test = ctx.getBean(Test.class);
		CouponExpirationDailyJob daily = ctx.getBean(CouponExpirationDailyJob.class);

		try {
//			test.startAll();
//			test.TestAdmin();
//			test.TestCompany();
//			test.TestCustomer();
			
		Thread.sleep(10000);

		} catch (Exception e) {
			throw new CouponException("Apptest failed", e);
			
		}

	}
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public FilterRegistrationBean<Coupons.filter.LoginFilter> filterRegistration(Coupons.session.SessionContext sessionContext) {
		FilterRegistrationBean<Coupons.filter.LoginFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		Coupons.filter.LoginFilter loginFilter = new Coupons.filter.LoginFilter(sessionContext);
		filterRegistrationBean.setFilter(loginFilter);
		filterRegistrationBean.addUrlPatterns("/api/admin/*","/api/company/*","/api/customer/*");
		return filterRegistrationBean;

	}

}
