package uy.maly.rewards.exceptions;

import java.io.Serializable;

public class Rewards400Exception extends RuntimeException implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

    public Rewards400Exception(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
