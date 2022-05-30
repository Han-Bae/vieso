package com.viseo.controller.kth;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class myIp {
	public String myIp() {
		return getServerIp();
	}
	
	public static String getServerIp() {
		
		InetAddress local = null;
		try {
			local = InetAddress.getLocalHost();
		}
		catch ( UnknownHostException e ) {
			e.printStackTrace();
		}
			
		if( local == null ) {
			return "";
		}
		else {
			String ip = local.getHostAddress();
			System.out.println(ip);
			return ip;
		}
			
	}
}
