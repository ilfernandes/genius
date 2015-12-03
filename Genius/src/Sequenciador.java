// classe que cont�m a sequ�ncia aleat�ria e a sequ�ncia do usuario

import java.util.Random;

public class Sequenciador {
	private static Sequenciador instance = null;
	// n�mero m�ximo de bot�es que piscar�o
	private int tam = 21;
	// quantidade de bot�es coloridos, serve para informar ao programa qual o intervalo de n�meros randomicos
	private int range;
	// n�mero da rodada atual
	private int rodada;
	// sequ�ncia aleat�ria gerada pelo programa
	private String sequencia[] = new String[tam];
	// sequ�ncia gerada pelo usu�rio (apagada a cada nova rodada)
	private String sequenciaUsuario[] = new String[tam];
	// objeto que cria a randomiza��o de acordo com o range
	private Random randomizador = new Random();
	
	private Sequenciador(int range){
		this.range = range;
		// inicia na rodada 0
		this.rodada = 0;
		// gera toda a sequ�ncia randomica
		gerarSequencia();
		// inicia a sequencia do usuario
		apagarSequenciaUsuario();
	}
	
	// implementa��o do Singleton
	public static Sequenciador getInstance(int range){
		if (instance == null){
			instance = new Sequenciador(range);
			return instance;
		}	
		return instance;
	}
	
	// gera a sequencia randomica
	private void gerarSequencia(){
		// preenche todo o vetor
		for (int i=0; i<this.tam-1; i++){
			// gera o n�mero rand�mico e converte para o nome do bot�o correspondente
			this.sequencia[i] = converterString(this.randomizador.nextInt(this.range));
		}
	}
	
	// m�todo que captura o �ndice do �ltimo valor indicado pelo usu�rio
	public int pegarUltimo(){
		for (int i=0; i<tam; i++)
			if (this.sequenciaUsuario[i] == "")
				return i;
		return (-1);
	}
	
	// compara as duas sequ�ncia
	public boolean compararSequencia(){
		int i = pegarUltimo()-1;
		// compara SOMENTE o �ltimo valor da sequ�nciaUsuario com o seu valor correspondente na sequ�ncia aleat�rio 
		if (this.sequencia[i].equals(this.sequenciaUsuario[i]) == true){
			// verifica se essa � a �ltima compara��o da sequ�ncia
			if (this.rodada == i){
				this.rodada++;
				// apaga toda a sequ�ncia do usu�rio
				apagarSequenciaUsuario();
			}
			return (true);
		}
		return (false);
	}
	
	// apaga toda a sequ�ncia do usu�rio
	public void apagarSequenciaUsuario(){
		for (int i=0; i<tam; i++)
			this.sequenciaUsuario[i] = "";
	}
	
	// adiciona o bot�o clicado pelo usu�rio na ultima posi��o da sequenciaUsuario
	public boolean adicionarJogada(String valor){
		int i = pegarUltimo();
		this.sequenciaUsuario[i] = valor;
		return compararSequencia();
	}
	
	public int getRodada(){
		return (this.rodada);
	}
	
	public int getTam(){
		return (this.tam-1);
	}
	
	// pegar o valor do bot�o de uma determinada posi��o recebida por par�metro
	public int getBotao(int i){
		return converterNumero(this.sequencia[i]);
	}

	// converte o n�mero rand�mico para o nome do bot�o correspondente
	public String converterString(int num){
		switch (num){
		case 0:
			return ("vermelho");
		case 1:
			return ("azul");
		case 2:
			return ("violeta");
		case 3:
			return ("verde");
		case 4:
			return ("laranja");
		case 5:
			return ("amarelo");
		}
		return null;
	}
	
	// converte o nome contida no vetor sequ�ncia para um n�mero (para a classe jogo poder realizar um switch case com esse valor)
	public int converterNumero(String nome){
		if (nome.equals("vermelho"))
			return(0);
		if (nome.equals("azul"))
			return(1);
		if (nome.equals("violeta"))
			return(2);
		if (nome.equals("verde"))
			return(3);
		if (nome.equals("laranja"))
			return(4);
		if (nome.equals("amarelo"))
			return(5);
		return (0);
	}
}