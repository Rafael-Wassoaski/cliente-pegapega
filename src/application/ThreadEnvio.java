package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadEnvio implements Runnable {
	
	private Socket socket;
	
	TelaGameController tela;
	public ThreadEnvio (Socket socket, TelaGameController game) {
		this.socket = socket;
		tela = game;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			PrintWriter print = new PrintWriter(socket.getOutputStream(), true);
			
			while(TelaGameController.game) {
				TelaGameController.tempoFloat++;
				Thread.currentThread().sleep(1000);
				print.println(TelaGameController.tempoFloat+";"+TelaGameController.x+";"+TelaGameController.y);
				tela.atualizaTempo();
				
				
			}
			
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
