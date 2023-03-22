package com.sga.sol.test;

import com.sga.sol.vc.util.BaseDiskUtils;
import com.sga.sol.vc.util.HardDiskUtils;

public class DiskTestMain {
	  public static void main(String[] args){

		    System.out.println(HardDiskUtils.getHardSerial());
		    
		    //찾아오지 못할 때 12345abcde
		    System.out.println(BaseDiskUtils.getBaseSerial());
	  }
}
