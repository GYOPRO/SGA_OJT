package com.sga.sol.vc.util;

import java.util.HashMap;

public class TypeDecryptUtil {
	
	public static String boardDecry(String inputPassword) {
			
			HashMap<String,String> hash = keyBoard();
			
			String result ="";
			for(int i = 0 ; i < inputPassword.length() ; i++) {
				result += (String)hash.get(String.valueOf(inputPassword.charAt(i)));
			}
			
	        
			return result;
		}
	
	public static HashMap<String, String> keyBoard(){
		HashMap<String, String> keyTable = new HashMap<String, String>();
		keyTable.put("0", "`");
		keyTable.put(")","~");
		keyTable.put("y","1");
		keyTable.put("`","!");
		keyTable.put("!","2");
		keyTable.put("Y","@");
		keyTable.put("#","3");
		keyTable.put("z","#");
		keyTable.put("m","4");
		keyTable.put("M","$");
		keyTable.put("5","5");
		keyTable.put("^","%");
		keyTable.put("t","6");
		keyTable.put("T","^");
		keyTable.put("k","7");
		keyTable.put("K","&");
		keyTable.put("]","8");
		keyTable.put("{","*");
		keyTable.put("g","9");
		keyTable.put("G","(");
		keyTable.put("f","0");
		keyTable.put("F",")");
		keyTable.put("_","-");
		keyTable.put("-","_");
		keyTable.put("=","=");
		keyTable.put("+","+");
		keyTable.put("6","q");
		keyTable.put(".","Q");
		keyTable.put("c","w");
		keyTable.put("D","W");
		keyTable.put("e","e");
		keyTable.put("E","E");
		keyTable.put("b","r");
		keyTable.put(">","R");
		keyTable.put("h","t");
		keyTable.put("P","T");
		keyTable.put("3","y");
		keyTable.put("B","Y");
		keyTable.put("p","u");
		keyTable.put("H","U");
		keyTable.put("8","i");
		keyTable.put("*","I");
		keyTable.put("d","o");
		keyTable.put("C","O");
		keyTable.put("\\","p");
		keyTable.put("A","P");
		keyTable.put("q","[");
		keyTable.put("X","{");
		keyTable.put("2","]");
		keyTable.put("@","}");
		keyTable.put("a","\\");
		keyTable.put("|","|");
		keyTable.put("[","a");
		keyTable.put("&","A");
		keyTable.put("%","s");//V
		keyTable.put("O","S");
		keyTable.put("7","d");
		keyTable.put("Z","D");
		keyTable.put("x","f");
		keyTable.put("Q","F");
		keyTable.put("~","g");
		keyTable.put("o","G");
		keyTable.put("9","h");
		keyTable.put("(","H");
		keyTable.put("\'","j"); //n
		keyTable.put("?","J");
		keyTable.put("j","k");
		keyTable.put("V","K");
		keyTable.put("l","l");
		keyTable.put("L","L");
		keyTable.put(";",";");
		keyTable.put(":",":");
		keyTable.put("/","'");
		keyTable.put("\"","\""); //N
		keyTable.put("1","z");
		keyTable.put("!","Z");
		keyTable.put(",","x");
		keyTable.put("<","X");
		keyTable.put("r","c");
		keyTable.put("S","C");
		keyTable.put("4","v");
		keyTable.put("$","V");
		keyTable.put("N","b"); //N 
		keyTable.put("I","B");
		keyTable.put("w","n");
		keyTable.put("W","N");
		keyTable.put("s","m");
		keyTable.put("R","M");
		keyTable.put("n",","); //n µ¿ÀÏ
		keyTable.put("i","<");
		keyTable.put("u",".");
		keyTable.put("U",">");
		keyTable.put("v","/"); 
		keyTable.put("J","?");
		
		return keyTable;
	}
}
