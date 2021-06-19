package Coupons.Controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import Coupons.entities.Category;
import Coupons.entities.Company;
import Coupons.entities.Coupon;
import Coupons.services.CompanyService;
import Coupons.session.Session;
import Coupons.session.SessionContext;

@RestController
@RequestMapping("/api/company")
@Transactional
@CrossOrigin
public class CompanyController extends LoginController{
	
	@Autowired
	private SessionContext sessionContext;
	
	public CompanyController(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}

	private CompanyService CompanySession(String token) {
		try {
			Session ses = this.sessionContext.getSession(token);
			CompanyService com = (CompanyService) ses.getAttribute("CompanyService");
			return com;
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No session");
		}
	}
	
	@PostMapping(path = "/add/coupon") // works!
	public ResponseEntity<?> addCoupon(@RequestHeader String token, @RequestBody Coupon coupon){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CompanySession(token).addCoupon(coupon));
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(path = "/update/coupon") // works!!
	public ResponseEntity<?> updateCoupon(@RequestHeader String token, @RequestBody Coupon coupon){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CompanySession(token).updateCoupon(coupon));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/delete/coupon/{couponId}") // works!
	public ResponseEntity<?> deleteCoupon(@RequestHeader String token, @PathVariable(value="couponId") Long id){
		try {
			CompanySession(token).deleteCoupon(id);
			return ResponseEntity.status(HttpStatus.OK).body("Coupon has been deleted");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/coupons") // works!
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CompanySession(token).getCompanyCoupons());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/couponsCategory/{categoryId}") //  works!
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token, @PathVariable("categoryId") Category categoryId){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CompanySession(token).getCompanyCoupons(categoryId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/couponsPrice/{price}") // works!
	public ResponseEntity<?> getCompanyCoupons(@RequestHeader String token, @PathVariable("price") Double price){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CompanySession(token).getCompanyCoupons(price));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/companyD") // works!
	public ResponseEntity<?> getCompanyDetails(@RequestHeader String token){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(CompanySession(token).getCompanyDetails());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
