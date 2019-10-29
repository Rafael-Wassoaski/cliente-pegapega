package application;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javafx.application.Platform;

public class ThreadRecebimento  implements Runnable{
	Socket a1;
	TelaGameController tela;
	TelaGame telaGame;
	public ThreadRecebimento (Socket socket, TelaGameController game, TelaGame telaGame) {
		a1 = socket;
		tela = game;
		this.telaGame = telaGame;
	}
	
	public void trocaTempo(String tempo) {
		TelaGameController.tempoFloat = Float.parseFloat(tempo);
	}
	
	
	
	public static String getData() {
		Calendar calendar = new GregorianCalendar();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		String formato = "Hora: "+ calendar.get(Calendar.HOUR_OF_DAY) +" Minuto: "+ calendar.get(Calendar.MINUTE) + " Segundo: "+calendar.get(Calendar.SECOND);
		return formato;
	}

	@Override
	public void run() {
		
		try {
			
			Scanner scanner = new Scanner(a1.getInputStream());
			
			while(TelaGameController.game) {
				
				if(scanner.hasNextLine()) {
				final String[] stringRPS = scanner.nextLine().toString().split(";");
				
				
				switch (stringRPS[0]) {
				case "tempo":
					TelaGameController.tempoFloat = Float.parseFloat(stringRPS[1]);
					tela.atualizaTempo();
					break;
				case "Encerrou":
					
					 Platform.runLater(new Runnable() {
		                 @Override public void run() {
		                     telaGame.gameEnd(stringRPS[1]);
		                 }
		             });
					break;

				default:
					break;
				}
				}
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
}
