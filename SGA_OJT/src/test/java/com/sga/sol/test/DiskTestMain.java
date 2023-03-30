package com.sga.sol.test;

import java.util.Arrays;

import com.sga.sol.vc.util.BaseDiskUtils;
import com.sga.sol.vc.util.ECBPasswordUtil;
import com.sga.sol.vc.util.HardDiskUtils;

public class DiskTestMain {
	  public static void main(String[] args){

		  String baseSerial = BaseDiskUtils.getBaseSerial();
	        String hardSerial = HardDiskUtils.getHardSerial();
	        String disk = baseSerial + hardSerial;

	        byte[] diskBytes = new byte[16];
	        byte[] diskBytesRaw = disk.getBytes();

	        // diskBytes 배열에 diskBytesRaw 배열의 내용 복사
	        System.arraycopy(diskBytesRaw, 0, diskBytes, 0, Math.min(diskBytesRaw.length, diskBytes.length));
	        
	        System.out.println("Base serial: " + baseSerial);
	        System.out.println("Hard serial: " + hardSerial);
	        System.out.println("Disk serial: " + disk);
	        System.out.println("Disk serial bytes: " + Arrays.toString(diskBytes));
	        
	        byte[] random = ECBPasswordUtil.getEcbKey(16);
	        System.out.println("random16key : " +Arrays.toString(random));
	  }	
}
