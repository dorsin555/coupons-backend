package Coupons.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import Coupons.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	Optional<Company> findByEmailAndPassword(String email, String pass);
	
	@Query(value = "SELECT * from company where email = ?",nativeQuery = true)
	Long getCompanyIdByEmail(String email);
	
	@Query(value = "SELECT * from company",nativeQuery = true)
	ArrayList<Company> getAllCompanies();
	
	Company getById(Long id);
	
	List<Company> getByName(String name);
	
	Company getByEmail(String name);
	
	@Query(value = "select * company.id, coupon.title from company join coupon where coupon.title = ?", nativeQuery = true)
	String existsByTitleAndId(String title);
	
}
