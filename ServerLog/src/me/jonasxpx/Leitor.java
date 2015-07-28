package me.jonasxpx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Leitor implements Runnable{

	private Scanner sc;
	FileWriter fw;
	BufferedWriter bw;
	private PrintWriter pw;
	
	public Leitor(String nick, Socket socket){
		try {
			sc = new Scanner(socket.getInputStream());
			pw = new PrintWriter(socket.getOutputStream());
			pw.println(nick); pw.flush();
			//pw.close();
			fw = new FileWriter(new SimpleDateFormat("dd-MM-yyy-HH-mm-ss").format(new Date())+".log");
			bw = new BufferedWriter(fw);
			System.out.println("Lendo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void run() {
		String st;
			while(sc.hasNextLine()){
				System.out.println(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()) +" : "+ (st = sc.nextLine()));
				save(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new java.util.Date()) +" : " + st);
				if(st.equalsIgnoreCase("Cliente invalido!")){
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {}
					System.exit(-1);
				}
			}
	}
	
	public void save(String string){
		try {
			bw.write(string);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
