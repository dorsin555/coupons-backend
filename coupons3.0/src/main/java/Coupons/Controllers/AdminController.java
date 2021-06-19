package Coupons.Controllers;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import Coupons.entities.Company;
import Coupons.entities.Customer;
import Coupons.services.AdminService;
import Coupons.session.Session;
import Coupons.session.SessionContext;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
@Transactional
public class AdminController extends LoginController{
	
	@Autowired
	private SessionContext sessionContext;
	
	public AdminController(SessionContext sessionContext) {
		super();
		this.sessionContext = sessionContext;
	}

	private AdminService AdminSession(String token) {
		try {
			Session ses = this.sessionContext.getSession(token);
			AdminService admin = (AdminService) ses.getAttribute("AdminService");
			return admin;
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No session");
		}
	}
	
	@PostMapping(path = "/add/company") // works!
	public ResponseEntity<?> addCompany(@RequestHeader String token, @RequestBody Company company){ 
		try {
			return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).addCompany(company));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(path = "/update/company") //works!
	public ResponseEntity<?> updateCompany(@RequestHeader String token, @RequestBody Company company){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).updateCompany(company));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/delete/company/{id}") // works!
	public ResponseEntity<?> deleteCompany(@RequestHeader String token, @PathVariable(value="id") Long id){
		try {
			AdminSession(token).deleteCompany(id);
			return ResponseEntity.status(HttpStatus.OK).body("Company has been deleted");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/companies") // works!
	public ResponseEntity<?> getAllCompanies(@RequestHeader String token){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).getAllCompanies());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/company/{id}") // works!
	public ResponseEntity<?> getOneCompany(@RequestHeader String token, @PathVariable(value="id") Long id){
		try {
		return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).getCompany(id));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(path = "/add/customer") // works!
	public ResponseEntity<?> addCustomer(@RequestHeader String token, @RequestBody Customer customer){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).addCustomer(customer));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(path = "/update/customer") // works!
	public ResponseEntity<?> updateCustomer(@RequestHeader String token, @RequestBody Customer customer){
		try {
			System.out.println(customer);
			return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).updateCustomer(customer));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(path = "/delete/customer/{id}") // works!
	public ResponseEntity<?> deleteCustomer(@RequestHeader String token, @PathVariable(value="id") Long id){
		try {
			AdminSession(token).deleteCustomer(id);
			return ResponseEntity.status(HttpStatus.OK).body("Customer has been deleted");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/customers") // works!
	public ResponseEntity<?> getAllCustomers(@RequestHeader String token){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).getAllCustomers());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(path = "/customer/{id}") // works!
	public ResponseEntity<?> getOneCustomer(@RequestHeader String token, @PathVariable(value="id") Long id){
		try {
		return ResponseEntity.status(HttpStatus.OK).body(AdminSession(token).getCustomer(id));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
