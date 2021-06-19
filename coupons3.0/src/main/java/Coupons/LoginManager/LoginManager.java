package Coupons.LoginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import Coupons.exception.CouponException;
import Coupons.services.AdminService;
import Coupons.services.ClientService;
import Coupons.services.CompanyService;
import Coupons.services.CustomerService;

@Component
public class LoginManager {
	
	@Autowired
	private ApplicationContext ctx;
	
	public ClientService login(String user,String pass,ClientType clientType) throws CouponException {
		try {
		if(clientType == ClientType.COMPANY ) {
			CompanyService css = ctx.getBean(CompanyService.class);
			if(css.login(user, pass)) {
				return css;
			}
		
		}else if (clientType == ClientType.CUSTOMER) {
			CustomerService csS = ctx.getBean(CustomerService.class);
			if(csS.login(user, pass)) {
				return csS;
			}
			
		}else if (clientType == ClientType.ADMIN) {
			AdminService adS = ctx.getBean(AdminService.class);
			if (adS.login(user, pass)) {
				return adS;
			}
		}
		throw new CouponException("Login failed! email/password is incorrect.");
	}catch (Exception e) {
		throw new CouponException("login failed", e);
	}

}
}
