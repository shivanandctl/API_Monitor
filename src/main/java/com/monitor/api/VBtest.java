package com.monitor.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VBtest {

	public static void main(String[] args) {
		
		 System.out.println(System.getProperty("user.dir"));

		   try {
	            ProcessBuilder pb = new ProcessBuilder("cscript", "//NoLogo", "C:\\Users\\AC70068\\eclipse-workspace\\SBA\\ApiMonitor\\src\\main\\resources\\apiMonitor.vbs");
	            Process p = pb.start();
	            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                System.out.println(line);
	            }
	           
	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}