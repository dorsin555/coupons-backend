package Coupons.services;

import Coupons.exception.CouponException;

public abstract class ClientService{
	
	public abstract boolean login(String email, String password)throws CouponException;

}
