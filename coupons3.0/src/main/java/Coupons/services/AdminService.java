package Coupons.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Coupons.entities.Company;
import Coupons.entities.Customer;
import Coupons.exception.CouponException;
import Coupons.repository.CompanyRepository;
import Coupons.repository.CustomerRepository;

@Service
@Transactional
@Scope("prototype")
public class AdminService extends ClientService{
	
	@PersistenceContext
	private EntityManager em;
	
	private String user = "admin@admin.com";
	private String pass = "admin";
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public AdminService() {}

	public AdminService(CompanyRepository companyRepository, CustomerRepository customerRepository) {
		super();
		this.companyRepository = companyRepository;
		this.customerRepository = customerRepository;
	}


	@Override
	public boolean login(String email, String password) throws CouponException {
		if(user.equalsIgnoreCase(email) && pass.equalsIgnoreCase(password)) {
			System.out.println("welcome admin");
			return true;
		}
	return false;
	}
	
	public Company addCompany(Company company) {
		return companyRepository.save(company);
	}
	
	public Company updateCompany(Company company) {
		Company updated = companyRepository.getById(company.getId());
		updated.setName(company.getName());
		updated.setEmail(company.getEmail());
		updated.setPassword(company.getPassword());
		return updated;
	}
	
	public void deleteCompany(Long id) {
		companyRepository.deleteById(id);
		System.out.println("company deleted");
	}
	
	public ArrayList<Company> getAllCompanies() {
		ArrayList<Company> company = companyRepository.getAllCompanies();
		System.out.println(company);
		return company;
	}
	
	public Optional<Company> getCompany(Long id) {
		Optional<Company> com =  companyRepository.findById(id);
		System.out.println(com);
		return com;
	}
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer updateCustomer(Customer customer) {
		Customer updated = customerRepository.getById(customer.getId());
		updated.setFirstName(customer.getFirstName());
		updated.setLastName(customer.getLastName());
		updated.setEmail(customer.getEmail());
		updated.setPassword(customer.getPassword());
		return updated;
	}
	
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
		System.out.println("customer deleted");
	}
	
	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}
	
	public Customer getCustomer(Long id) {
		return customerRepository.getById(id);
	}

}
