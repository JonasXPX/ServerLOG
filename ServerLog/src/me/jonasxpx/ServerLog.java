package me.jonasxpx;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerLog {

	private Socket socket;
	
	public ServerLog(String nick, String server, int port){
		try{
			socket = new Socket(server, port);
			new Thread(new Leitor(nick, socket)).start();
			/*			while(true){
				Thread t = new Thread(new Leitor(sc.accept()));
				t.start();
			}
			*/
		}catch(IOException e){System.out.println("erro"); e.printStackTrace();} 
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Digite o IP");
		String ip = new Scanner(System.in).nextLine();
		System.out.println("Digite a porta");
		int port = Integer.parseInt(new Scanner(System.in).nextLine());
		System.out.println("Digite seu NICK:");
		String nick = new Scanner(System.in).nextLine();
		new ServerLog(nick,ip, port);
	}
	
}
