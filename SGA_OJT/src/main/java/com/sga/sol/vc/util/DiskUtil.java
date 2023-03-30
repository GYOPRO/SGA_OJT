package com.sga.sol.vc.util;

import java.util.Arrays;

public class DiskUtil {

	
	public static byte[] getserialEcbKey() {

		String baseSerial = BaseDiskUtils.getBaseSerial();
		String hardSerial = HardDiskUtils.getHardSerial();
		String disk = baseSerial + hardSerial;

		byte[] diskBytes = new byte[16];
		byte[] diskBytesRaw = disk.getBytes();

		// diskBytes �迭�� diskBytesRaw �迭�� ���� ����
		System.arraycopy(diskBytesRaw, 0, diskBytes, 0, Math.min(diskBytesRaw.length, diskBytes.length));

		
		return diskBytes;

	}

}
