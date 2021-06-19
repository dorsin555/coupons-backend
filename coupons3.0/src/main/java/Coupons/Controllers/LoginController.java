package Coupons.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import Coupons.LoginManager.ClientType;
import Coupons.LoginManager.LoginManager;
import Coupons.entities.LoginItem;
import Coupons.exception.CouponException;
import Coupons.services.AdminService;
import Coupons.services.ClientService;
import Coupons.services.CompanyService;
import Coupons.services.CustomerService;
import Coupons.session.SessionContext;
import Coupons.session.Session;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private LoginManager loginManager;
	@Autowired
	private SessionContext sessionContext;
	
	@PostMapping("/login")
	public LoginItem login(@RequestParam String userEmail, @RequestParam String userPass,
			@RequestParam ClientType clientType) throws CouponException {
		try {
			ClientService service = loginManager.login(userEmail, userPass, clientType);
			if(service instanceof AdminService) {
				Session session = sessionContext.createSession();
				session.setAttribute("AdminService", service);
				return new LoginItem(session.token);
			}
			if(service instanceof CompanyService) {
				Session session = sessionContext.createSession();
				session.setAttribute("CompanyService", service);
				return new LoginItem(session.token);
			}
			if(service instanceof CustomerService) {
				Session session = sessionContext.createSession();
				session.setAttribute("CustomerService", service);
				return new LoginItem(session.token);
			}
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed, client is not recognaized");
			
			
		}catch(CouponException e) {
			e.printStackTrace();
		}
		return (LoginItem) ResponseEntity.status(HttpStatus.BAD_REQUEST);
	}

}
