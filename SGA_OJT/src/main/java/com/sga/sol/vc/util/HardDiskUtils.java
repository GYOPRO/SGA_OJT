package com.sga.sol.vc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HardDiskUtils {


  String os = System.getProperty("os.name").toLowerCase();
  boolean isWindows = os.contains("win");
  boolean isLinux = os.contains("linux");
  
  private static HardDiskUtils instance;
  private final String serialNumber;

  private HardDiskUtils(String serialNumber) {
      this.serialNumber = serialNumber;
  }
  
  public static String getHardSerial() {
	  HardDiskUtils hardDriveSerialNumber = HardDiskUtils.getInstance();
	  String serial = hardDriveSerialNumber.getSerialNumber();
	  if(serial.equals("NotApplicable")) {
		  serial = "12345abcde";
	  }
	  return serial;
  }
  
  //window linux 확인하기
  private static synchronized HardDiskUtils getInstance() {
      if (instance == null) {
          String os = System.getProperty("os.name").toLowerCase();
          String serialNumber = null;
          if (os.contains("win")) {
              serialNumber = getWindowsHardSerialNumber();

          } else if (os.contains("linux")) {
              serialNumber = getLinuxHardSerialNumber();
          }
          if (serialNumber == null) {
              throw new RuntimeException("Failed to get hard drive serial number");
          }
          instance = new HardDiskUtils(serialNumber);
      }
      return instance;
  }
  
  public String getSerialNumber() {
      return serialNumber;
  }
  
  //serial번호 runtime으로 가져오기
  private static String getWindowsHardSerialNumber() {
	  String result = "";
	    try {
	      Process p = Runtime.getRuntime().exec(new String[] { "wmic", "diskdrive", "get", "serialnumber" }); //드라이브 정보 가져오기
	      BufferedReader input =
	        new BufferedReader(new InputStreamReader(p.getInputStream()));
	      String line;
	      while ((line = input.readLine()) != null) {
	         result += line;
	      }
	      input.close();
	      String s = result.trim();
		  s = s.replaceAll(" ","");
		  s = s.replace("SerialNumber", "");
	      return s;
	      
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	    
	  }
  
  private static String getLinuxHardSerialNumber() {
	  try {
	        Process process = Runtime.getRuntime().exec(new String[] { "ls", "-l", "/dev/disk/by-id" });
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                line = line.trim();
	                if (line.startsWith("lrwxrwxrwx")) {
	                    String[] tokens = line.split("\\s+");
	                    String path = tokens[8];
	                    if (path.startsWith("wwn-") || path.startsWith("ata-")) {
	                        String[] parts = path.split("-");
	                        String serialNumber = parts[parts.length - 1];
	                        return serialNumber;
	                    }
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
  }
  



}
