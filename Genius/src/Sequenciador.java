// classe que contém a sequência aleatória e a sequência do usuario

import java.util.Random;

public class Sequenciador {
	private static Sequenciador instance = null;
	// número máximo de botões que piscarão
	private int tam = 21;
	// quantidade de botões coloridos, serve para informar ao programa qual o intervalo de números randomicos
	private int range;
	// número da rodada atual
	private int rodada;
	// sequência aleatória gerada pelo programa
	private String sequencia[] = new String[tam];
	// sequência gerada pelo usuário (apagada a cada nova rodada)
	private String sequenciaUsuario[] = new String[tam];
	// objeto que cria a randomização de acordo com o range
	private Random randomizador = new Random();
	
	private Sequenciador(int range){
		this.range = range;
		// inicia na rodada 0
		this.rodada = 0;
		// gera toda a sequência randomica
		gerarSequencia();
		// inicia a sequencia do usuario
		apagarSequenciaUsuario();
	}
	
	// implementação do Singleton
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
			// gera o número randômico e converte para o nome do botão correspondente
			this.sequencia[i] = converterString(this.randomizador.nextInt(this.range));
		}
	}
	
	// método que captura o índice do último valor indicado pelo usuário
	public int pegarUltimo(){
		for (int i=0; i<tam; i++)
			if (this.sequenciaUsuario[i] == "")
				return i;
		return (-1);
	}
	
	// compara as duas sequência
	public boolean compararSequencia(){
		int i = pegarUltimo()-1;
		// compara SOMENTE o último valor da sequênciaUsuario com o seu valor correspondente na sequência aleatório 
		if (this.sequencia[i].equals(this.sequenciaUsuario[i]) == true){
			// verifica se essa é a última comparação da sequência
			if (this.rodada == i){
				this.rodada++;
				// apaga toda a sequência do usuário
				apagarSequenciaUsuario();
			}
			return (true);
		}
		return (false);
	}
	
	// apaga toda a sequência do usuário
	public void apagarSequenciaUsuario(){
		for (int i=0; i<tam; i++)
			this.sequenciaUsuario[i] = "";
	}
	
	// adiciona o botão clicado pelo usuário na ultima posição da sequenciaUsuario
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
	
	// pegar o valor do botão de uma determinada posição recebida por parâmetro
	public int getBotao(int i){
		return converterNumero(this.sequencia[i]);
	}

	// converte o número randômico para o nome do botão correspondente
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
	
	// converte o nome contida no vetor sequência para um número (para a classe jogo poder realizar um switch case com esse valor)
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