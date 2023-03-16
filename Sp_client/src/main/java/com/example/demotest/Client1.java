package com.example.demotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Client1 {
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	public Client1(String ip, int port) {
		try {
			// 서버에 요청 보내기
			socket = new Socket(ip, port);
			System.out.println(socket.getInetAddress().getHostAddress() + "에 연결됨");
			
			// 메시지 받기
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 응답 출력
			System.out.println(br.readLine());
			while(true) {
				LocalDateTime now = LocalDateTime.now();
				UUID one = UUID.randomUUID();
				pw = new PrintWriter(socket.getOutputStream());
				
				String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				// 메세지 전달
				pw.println(formatedNow + " " + one.toString());
				pw.flush();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
		    System.out.println(e.getMessage());
		} finally {
		    // 소켓 닫기 (연결 끊기)
		    try {
		    	if(socket != null) { socket.close(); }
		        if(br != null) { br.close(); }
		        if(pw != null) { pw.close(); }
		    } catch (IOException e) {
		        System.out.println(e.getMessage());
		    }
		}
	}
}