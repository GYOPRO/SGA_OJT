package com.sga.sol.test;

import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.sha3;

public class sha3TestMain {
	
	public static void main(String[] args) {
		 sha3 sha3 = new sha3();
		 byte[] salt = sha3.generateSalt();
		 System.out.println("salt : " +ECBPasswordUtil.bytesToHex(salt));
		 String password = "asdlsdfk@3";
		 
		 byte[] hash = sha3.generateHash(password, salt);
		 System.out.println("hash : " +ECBPasswordUtil.bytesToHex(hash));
	
		 
	  
	  
	}
}



