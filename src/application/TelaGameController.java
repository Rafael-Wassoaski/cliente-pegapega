package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class TelaGameController implements Initializable {

	public static int x, y;
	public static float tempoFloat;
	public static boolean game = true;
	@FXML
	Text campo;
	@FXML
	Text tempo;
	@FXML
	Text aviso;
	@FXML
	Button cima, baixo, esquerda, direita;
	
	public void setAviso(String msg, float tempo) {
		
		aviso.setText("No tempo " + tempo +" "+msg);
		
		
	}
	
	private void block() {
		
		cima.setDisable(true);
		baixo.setDisable(true);
		esquerda.setDisable(true);
		direita.setDisable(true);
		
		try {
			Thread.currentThread().sleep(1000);
			
			cima.setDisable(false);
			baixo.setDisable(false);
			esquerda.setDisable(false);
			direita.setDisable(false);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	private void setCampo() {
		String campoUnificado = "";
		for(int a = 0; a < 8 ;a ++) {
			for(int b = 0; b < 8 ;b ++) {
				if(a == x && b == y) {
					campoUnificado += " 1 ";
				}else {
					campoUnificado+= " 0 ";
				}
			}
			campoUnificado+="\n";
		}
		
		campo.setText(campoUnificado);
		
	}
	
	public void atualizaTempo() {
		tempo.setText(Float.toString(tempoFloat));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		x = y = 0;
		tempoFloat = 0;
		
		setCampo();
		
		cima.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (x - 1 >= 0) {
					x--;
					setCampo();
					block();
				}
				
			}
		});

		baixo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (x + 1 < 8) {
					x++;
					setCampo();
					block();
				}

			}
		});

		esquerda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (y - 1 >= 0) {
					y--;
					setCampo();
					block();
				}

			}
		});

		direita.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if (y + 1 < 8) {
					y++;
					setCampo();
					block();
				}

			}
		});

	}

}
