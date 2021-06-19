package Coupons.exception;

public class CouponException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CouponException() {
		super();
	}

	public CouponException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CouponException(String message, Throwable cause) {
		super(message, cause);
	}

	public CouponException(String message) {
		super(message);
	}

	public CouponException(Throwable cause) {
		super(cause);
	}
	
	

}
