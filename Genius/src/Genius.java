// classe que encapsula a função principal

import java.awt.event.*;

public class Genius {
    
	public static void main(String[] args){
		// cria a instancia de jogo
		Jogo jogo = new Jogo();
		
		// chama a função que monta a tela de abertura
		jogo.tela_abertura();
		
		// evento que finaliza o programa quando clicar no X da janela do programa
		jogo.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent windowEvent){
					// encerra o aplicativo
					System.exit(0);
				}
			}
		);
	}
}
