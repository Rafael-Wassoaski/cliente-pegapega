package application;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ThreadRecebimento  implements Runnable{
	Socket a1;
	TelaGameController tela;
	public ThreadRecebimento (Socket socket, TelaGameController game) {
		a1 = socket;
		tela = game;
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
				String[] stringRPS = {""};
				if(scanner.hasNextLine()) {
				stringRPS = scanner.nextLine().toString().split(";");
				}
				
				if(stringRPS[0].equals("tempo")) {
					TelaGameController.tempoFloat = Float.parseFloat(stringRPS[1]);
					tela.atualizaTempo();
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
}
