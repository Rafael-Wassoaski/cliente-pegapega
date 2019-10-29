package application;

import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaGame extends Application{
	
	Socket socket;
	static Stage primaryStage;
	public void gameEnd(String como) {
			try {
			EndGameController controller;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EndGame.fxml"));
			Parent root = (Parent) loader.load();
			

			        Scene scene = new Scene(root);
			        primaryStage.setScene(scene);
			        primaryStage.show();
			    controller = loader.getController();
			    controller.setComo(como);
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
	}
	
	public TelaGame(Socket socket) {
		this.socket = socket;
	}
	
	public TelaGameController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		this.primaryStage = primaryStage;
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaGame.fxml"));
			Parent root = (Parent) loader.load();
			this.controller = (TelaGameController) loader.getController(); //Essa é a linha importante, é onde você captura o objeto atual que representa seu controller

			        Scene scene = new Scene(root);
			        primaryStage.setScene(scene);
			        primaryStage.setTitle("Game");
			        primaryStage.show();
			    	
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
		ThreadEnvio threadEnvio = new ThreadEnvio(socket, controller);
		
		Thread thread = new Thread(threadEnvio);
		thread.start();
		
		ThreadRecebimento recebimento = new ThreadRecebimento(socket, controller, this);
		
		Thread thread2 = new Thread(recebimento);
		thread2.start();
		
	}

}
