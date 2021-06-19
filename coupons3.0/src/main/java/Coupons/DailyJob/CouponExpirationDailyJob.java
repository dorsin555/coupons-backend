package Coupons.DailyJob;

import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import Coupons.entities.Coupon;
import Coupons.repository.CouponRepository;

@Component
public class CouponExpirationDailyJob implements Runnable{
	
    @Autowired
    private CouponRepository couponRepository;
    
    private boolean quit;
    private Thread jobThread;
    
    public CouponExpirationDailyJob(){
    	super();
    	jobThread = new Thread(this.jobThread, "job");
    }
    
    @PostConstruct
    public void Start() {
    	jobThread.start();
    	System.out.println("daily job was started");
    }
    
    @PreDestroy
    public void destroyJob() {
    	jobThread.interrupt();
    	System.out.println("daily job was done");
    }

	@Override
	@Transactional
	public void run()  {
		while(quit != true) {
			List<Coupon> couponsEndDate = couponRepository.findCouponByEndDate(java.sql.Date.valueOf(LocalDate.now()));
			synchronized (couponsEndDate) {
				if(couponsEndDate != null) {
					for (Coupon coupon : couponsEndDate) {
						couponRepository.delete(coupon);
						System.out.println("An expired coupon has been deleted");
					}
				}
			}
		}
		System.out.println("Expiration Daily Job: No coupons to delete");
		try {
			Thread.sleep(86_400_400);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean stopTask() {
		System.out.println("Daily Job stoped. have a good day");
		return quit = true;
	}

	
}
