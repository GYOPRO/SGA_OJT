package com.sga.sol.test;

import com.sga.sol.vc.util.BaseDiskUtils;
import com.sga.sol.vc.util.HardDiskUtils;

public class DiskTestMain {
	  public static void main(String[] args){

		    System.out.println(HardDiskUtils.getHardSerial());
		    
		    //ã�ƿ��� ���� �� 12345abcde
		    System.out.println(BaseDiskUtils.getBaseSerial());
	  }
}
