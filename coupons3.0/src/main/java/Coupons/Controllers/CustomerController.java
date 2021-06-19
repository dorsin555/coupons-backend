package Coupons.Controllers;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import Coupons.entities.Category;
import Coupons.entities.Coupon;
import Coupons.services.CustomerService;
import Coupons.session.Session;
import Coupons.session.SessionContext;

@RestController
@RequestMapping("/api/customer")
@Transactional
@CrossOrigin
public class CustomerController extends LoginController{
	
	@Autowired
	private SessionContext sessionContext;
	
	public CustomerController(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}

	private CustomerService CustomerSession(String token) {
		try {
			Session ses = this.sessionContext.getSession(token);
			CustomerService com = (CustomerService) ses.getAttribute("CustomerService");
			return com;
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No session");
		}
	}
	
	@PostMapping(path = "/add/purchase/{couponId}") // works!
	public ResponseEntity<?> purchaseCoupon(@RequestHeader String token, @RequestParam(name = "couponId") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CustomerSession(token).purchaseCoupon(id));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/coupons") // works!
	public ResponseEntity<?> getCustomerCoupons(@RequestHeader String token){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CustomerSession(token).getCustomerCoupons());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/couponsCategory/{categoryId}") // works!
	public ResponseEntity<?> getCustomerCouponsCategory(@RequestHeader String token, @RequestParam(value="categoryId") Category category){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CustomerSession(token).getCustomerCoupons(category));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/couponsPrice/{price}") // works!
	public ResponseEntity<?> getCustomerCouponsPrice(@RequestHeader String token, @RequestParam(value="price") Double price){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CustomerSession(token).getCustomerCoupons(price));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/customerD") // works!
	public ResponseEntity<?> getCustomerDetails(@RequestHeader String token){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CustomerSession(token).getCustomerDetails());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/allCoupons")
	public ResponseEntity<?> getAllCoupons(@RequestHeader String token){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CustomerSession(token).getAllCoupons());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/couponD/{couponId}") // works!
	public ResponseEntity<?> getCouponDetails(@RequestHeader String token, @PathVariable(value="couponId") Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CustomerSession(token).getCouponDetails(id));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
