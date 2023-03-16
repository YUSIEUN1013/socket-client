package com.example.demotest;

public class App {
	
	public static void main( String[] args ) {
    	String ip = System.getenv("DSERVER_IP");
        int port = 4432;
        new Client1(ip, port);
    }

}
