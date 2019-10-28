package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaAguardo extends Application{
	
	private String tipo;
	
	
	public TelaAguardo(String tipo) {
		this.tipo = tipo;
	}
	
	public TelaAguardoController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try {
		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAguardo.fxml"));
			Parent root = (Parent) loader.load();
			this.controller = (TelaAguardoController) loader.getController(); //Essa é a linha importante, é onde você captura o objeto atual que representa seu controller

			        Scene scene = new Scene(root);
			        primaryStage.setScene(scene);
			        primaryStage.show();
			    	
			    } catch (Exception ex) {
			        System.out.println(ex.getMessage());
			    }
		
		
		
		
		Socket a1;
		try {
			a1 = new Socket("localhost",4261);
		
		PrintWriter a = new PrintWriter(a1.getOutputStream(), true);
		
		a.println(tipo);
		
		Scanner a2 = new Scanner(a1.getInputStream());
		Integer tempo = 0;
		
		String f3;
		
	
		while(true) {
			
			f3 = a2.nextLine().toString();
			
		System.out.println(f3);
		
		if(f3.toString().equals("Iniciar;"+tipo)) {
			break;
		}
		
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		//acabou a espera, jogo comeca
		
		TelaGame game = new TelaGame(a1);
		game.start(primaryStage);
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}  
		
	}
	
	


