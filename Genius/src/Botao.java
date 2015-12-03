// classe utilizada para criar os bot�es coloridos do jogo

import javax.swing.*;

class Botao extends JButton{
	private static final long serialVersionUID = 1L;
	// atributo utilizado para saber se o bot�o pode ser clicado
	private boolean status;
	// atributo utilizado para facilitar o uso das imagens e m�sicas
	private String nome;
	
	// cria o botao com o construtor do JButton
	public Botao(String nome){
		super(new ImageIcon(Jogo.getImage(nome+"_apagado.jpg")));
		// indica a imagem do bot�o quando estiver sob o mouse
		setRolloverIcon(new ImageIcon(Jogo.getImage(nome+"_marcado.jpg")));
		// remove a borda do botao
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		this.nome = nome;
	}
	
	public void setStatus(boolean status){
		this.status = status;
	}
	
	public boolean getStatus(){
		return(this.status);
	}
	
	public String getNome(){
		return(this.nome);
	}
	
	// altera as imagens do bot�o recebido por parametro para aceso
	public void acenderBotao(Botao botao){
		botao.setIcon(new ImageIcon(Jogo.getImage(botao.getNome()+"_aceso.jpg")));
		botao.setRolloverIcon(new ImageIcon(Jogo.getImage(botao.getNome()+"_aceso.jpg")));
	}
	
	// altera as imagens do bot�o recebido por parametro para apagado
	public void apagarBotao(Botao botao){
		botao.setIcon(new ImageIcon(Jogo.getImage(botao.getNome()+"_apagado.jpg")));
		botao.setRolloverIcon(new ImageIcon(Jogo.getImage(botao.getNome()+"_marcado.jpg")));
	}
}
