package Coupons.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Coupons.session.Session;
import Coupons.session.SessionContext;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LogoutController {
	
	@Autowired
	private SessionContext sessionContext;
	
	@PostMapping(path = "/logout")
	public String logout(@RequestHeader String token) {
	Session curr = sessionContext.getSession(token);
	if(curr != null){
	sessionContext.invalidate(curr);
	return "You have logged out! See you later!";	
	}
	throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Client is not recognized");
	}
	

}
