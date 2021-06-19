package Coupons.services;

import java.util.ArrayList;
import Coupons.repository.CompanyRepository;
import Coupons.repository.CouponRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import Coupons.entities.Category;
import Coupons.entities.Company;
import Coupons.entities.Coupon;
import Coupons.exception.CouponException;

@Service
@Transactional(noRollbackFor = Exception.class)
@Scope("prototype")
public class CompanyService extends ClientService {

	private static Long compId;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CouponRepository couponRepository;

	public CompanyService() {
	}

	public CompanyService(Long id) {
		CompanyService.compId = id;
	}

	public CompanyService(CompanyRepository companyRepository, CouponRepository couponRepository) {
		super();
		this.companyRepository = companyRepository;
		this.couponRepository = couponRepository;
	}

	@Override
	public boolean login(String email, String password) throws CouponException {
		Optional<Company> opt = companyRepository.findByEmailAndPassword(email, password);
		if (opt.isPresent()) {
			Company c = opt.get();
			compId = c.getId();
			return true;
		} else {
			throw new CouponException("wrong input");
		}
	}

	public Coupon addCoupon(Coupon coupon) throws CouponException {
			if (coupon == null) {
				throw new CouponException("coupon is null.");
			}
			Company com = companyRepository.getById(compId);
			List<Coupon> companyCoupons = com.getCoupon();
			for (Coupon curr : companyCoupons) {
				System.out.println("*************" + curr.getTitle());
				if (curr.getTitle() != null && curr.getTitle().equals(coupon.getTitle())) {
					throw new CouponException("Your company already has a coupon with this title: " + coupon.getTitle());
				}
			}
			coupon.setCompany(com);
			return couponRepository.save(coupon);
	}

	public Coupon updateCoupon(Coupon coupon) {
			Coupon cou = couponRepository.findBycouponId(coupon.getId());
			cou.setAmount(coupon.getAmount());
			cou.setCategoryId(coupon.getCategoryId());
			cou.setCoupon_id(coupon.getCoupon_id());
			cou.setDescription(coupon.getDescription());
			cou.setEnd_date(coupon.getEnd_date());
			cou.setImage(coupon.getImage());
			cou.setPrice(coupon.getPrice());
			cou.setStart_date(coupon.getStart_date());
			cou.setTitle(coupon.getTitle());
			couponRepository.save(cou);
			return cou;
	}

	public void deleteCoupon(Long id) {
		couponRepository.deleteById(id);
	}

	public List<Coupon> getCompanyCoupons() {
		List<Coupon> companyCoupons = couponRepository.findByCompanyId(compId);
		return companyCoupons;
	}

	public List<Coupon> getCompanyCoupons(Category category) throws CouponException {
		try {
		List<Coupon> companyCoupons = couponRepository.findByCompanyId(compId);
		List<Coupon> couponByCat = new ArrayList<Coupon>();
		for (Coupon coupon : companyCoupons) {
			if (coupon.getCategoryId().equals(category)) {
				couponByCat.add(coupon);
			}
		}
		return couponByCat;
		}catch (Exception e) {
			e.printStackTrace();
			throw new CouponException(e.getMessage(), e.getCause());
		}
	}

	public List<Coupon> getCompanyCoupons(Double max) {
		List<Coupon> companyCoupons = couponRepository.findByCompanyId(compId);
		List<Coupon> couponByPrice = new ArrayList<Coupon>();
		for (Coupon coupon : companyCoupons) {
			if (coupon.getPrice() < max) {
				couponByPrice.add(coupon);
			}
		}
		return couponByPrice;
	}

	public Company getCompanyDetails() {
		Company systemCom = companyRepository.getById(compId);
		if (systemCom != null) {
			return systemCom;
		}
		return null;
	}

	public Coupon getCoupon(Long coupon) {
		Coupon syscou = couponRepository.getOne(coupon);
		return syscou;
	}

}
