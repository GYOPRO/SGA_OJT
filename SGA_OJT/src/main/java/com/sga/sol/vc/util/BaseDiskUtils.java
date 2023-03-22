package com.sga.sol.vc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BaseDiskUtils {


  String os = System.getProperty("os.name").toLowerCase();
  boolean isWindows = os.contains("win");
  boolean isLinux = os.contains("linux");
  
  private static BaseDiskUtils instance;
  private final String serialNumber;

  private BaseDiskUtils(String serialNumber) {
      this.serialNumber = serialNumber;
  }
  
  public static synchronized BaseDiskUtils getInstance() {
      if (instance == null) {
          String os = System.getProperty("os.name").toLowerCase();
          System.out.println(os);
          String serialNumber = null;
          if (os.contains("win")) {
              serialNumber = getWindowsSerialNumber();

          } else if (os.contains("linux")) {
              serialNumber = getLinuxSerialNumber();
              System.out.println("linux");
          }
          if (serialNumber == null) {
              throw new RuntimeException("Failed to get hard drive serial number");
          }
          instance = new BaseDiskUtils(serialNumber);
      }
      return instance;
  }
  
  public String getSerialNumber() {
      return serialNumber;
  }
  
  private static String getWindowsSerialNumber() {
      String result = "";
      try {
          Process p = Runtime.getRuntime().exec(new String[]{"wmic", "bios", "get", "serialnumber"}); // 메인보드 정보 가져오기
          BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
          String line;
          while ((line = input.readLine()) != null) {
              result += line;
          }
          input.close();
      } catch (Exception e) {
          e.printStackTrace();
      }
      String s = result.trim();
      s = s.replaceAll(" ","");
      s = s.replaceAll("SerialNumber","");
      return s;
  }
  
  private static String getLinuxSerialNumber() {
      try {
          Process process = Runtime.getRuntime().exec(new String[] { "cat", "/proc/cpuinfo" });
          try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
              String line;
              while ((line = reader.readLine()) != null) {
                  line = line.trim();
                  if (line.startsWith("Serial")) {
                      return line.substring(line.indexOf(":") + 1).trim();
                  }
              }
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      return null;
  }
  
  public static String getBaseSerial() {
	  BaseDiskUtils hardDriveSerialNumber = BaseDiskUtils.getInstance();
	  String serial = hardDriveSerialNumber.getSerialNumber();
	  if(serial.equals("NotApplicable")) {
		  serial = "12345abcde";
	  }
	  return serial;
  }


}
