package com.sga.sol.vc.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DiskUtils {
  private DiskUtils() {  }

 /**

  public static String getHardSerialNumber(String drive) {
    String result = "";
    try {
    	//�ӽ����� ����, vbsȮ����
      File file = File.createTempFile("realhowto",".vbs");
      file.deleteOnExit(); // ���α׷� ����� ����
      FileWriter fw = new java.io.FileWriter(file);

      //VBS ��ũ��Ʈ �ۼ�
      String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" //COM��ü ���� ���� �ý��� �׼���
                  +"Set colDrives = objFSO.Drives\n" // ��� ����̺� ���� ��������
                  +"Set objDrive = colDrives.item(\"" + drive + "\")\n " // ���ϴ� ����̺� ���� ��������
                  +"Wscript.Echo objDrive.SerialNumber";   // �ش� ����̺� �ø��� ��ȣ ��������
      fw.write(vbs); //����
      fw.close(); //����
      
      //VBS Script ����
      Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
      
      //Bufferd�� �ø��� �ѹ� ���
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
  
  //�ϵ��ũ �ø��� �ѹ� ��������
  public static String getHardSerialNumber() {
	    String result = "";
	    try {
	      Process p = Runtime.getRuntime().exec("wmic diskdrive get serialnumber"); //����̺� ���� ��������
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
