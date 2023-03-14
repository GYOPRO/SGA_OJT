package com.sga.sol.vc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DiskUtils {
  private DiskUtils() {  }

 /**

  public static String getHardSerialNumber(String drive) {
    String result = "";
    try {
    	//임시파일 생성, vbs확장자
      File file = File.createTempFile("realhowto",".vbs");
      file.deleteOnExit(); // 프로그램 종료시 삭제
      FileWriter fw = new java.io.FileWriter(file);

      //VBS 스크림트 작성
      String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" //COM객체 생성 파일 시스템 액세스
                  +"Set colDrives = objFSO.Drives\n" // 모든 드라이브 정보 가져오기
                  +"Set objDrive = colDrives.item(\"" + drive + "\")\n " // 원하는 드라이브 정보 가져오기
                  +"Wscript.Echo objDrive.SerialNumber";   // 해달 드라이브 시리얼 번호 가져오기
      fw.write(vbs); //저장
      fw.close(); //종료
      
      //VBS Script 실행
      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
      
      //Bufferd에 시리얼 넘버 담기
      BufferedReader input =
        new BufferedReader
          (new InputStreamReader(p.getInputStream()));
      String line;
      while ((line = input.readLine()) != null) {
         result += line;
      }
      input.close();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return result.trim();
  }**/
  
  //하드디스크 시리얼 넘버 가져오기
  public static String getHardSerialNumber() {
	    String result = "";
	    try {
	      Process p = Runtime.getRuntime().exec("wmic diskdrive get serialnumber"); //드라이브 정보 가져오기
	      BufferedReader input =
	        new BufferedReader(new InputStreamReader(p.getInputStream()));
	      String line;
	      while ((line = input.readLine()) != null) {
	         result += line;
	      }
	      input.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return result.trim();
	    
	  }

  public static void main(String[] args){
    String hard = DiskUtils.getHardSerialNumber();

    System.out.println("HardSerialNumber : "+ hard);

    	

  }
}
