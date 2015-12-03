
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Jogo extends JFrame{
	private static final long serialVersionUID = 1L;
	private Sons musica = new Sons();
	// cria o objeto que armazena as sequ�ncias
	private Sequenciador sequencia = Sequenciador.getInstance(6);
	// janela que armazena os demais componentes
	private Container janela = getContentPane();
	// imagens de fundo das telas
	private JLabel abertura = new JLabel(new ImageIcon(getImage("abertura.jpg")));
	private JLabel tela = new JLabel(new ImageIcon(getImage("tela.jpg")));

	// mensagem da tela do jogo
	private JLabel mensagem = new JLabel(new ImageIcon(getImage("aguarde.jpg")));
	// cada bot�o da tela inicial
	private JButton iniciar = new JButton(new ImageIcon(getImage("iniciar.jpg")));
	private JButton sair = new JButton(new ImageIcon(getImage("sair.jpg")));
	// cada bot�o colorido
	private Botao azul = new Botao("azul");
	private Botao amarelo = new Botao("amarelo");
	private Botao verde = new Botao("verde");
	private Botao vermelho = new Botao("vermelho");
	private Botao laranja = new Botao("laranja");
	private Botao violeta = new Botao("violeta");
	// saber se o usuario ainda pode jogar
	private boolean jogoAtivo = true;
	
	public Jogo(){
		// define o nome da barra de t�tulo da janela
		super("GENIUS - THE GAME");
		// define o Layout do container (null permite que eu mesmo posicione os graficos)
		setLayout(null);
		// define o tamanho da janela
		setSize(550,485);
		// informa que a posi��o inicial da janela � centralizada
		setLocationRelativeTo(null);
		// define que n�o ser� permitido redimencionar a tela
		setResizable(false);
		// define como vis�vel
		setVisible(true);
		// toca a m�sica da tela de abertura
		musica.escolheMusica("abertura");
		musica.iniciar();
	}

	// monta a tela de abertura do jogo
	public void tela_abertura(){
		
		// adiciona a imagem de fundo � janela
		janela.add(abertura);
		abertura.setBounds(1,1,542,464);
		
		// adiciona o bot�o iniciar � janela
		iniciar.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		iniciar.setRolloverIcon(new ImageIcon(getImage("iniciar_aceso.jpg")));
		janela.add(iniciar);
		iniciar.setBounds(197,255,150,35);
		
		// a��es a serem executadas ao clica em iniciar
		iniciar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// define com invisivel os itens que estavam na tela
					abertura.setVisible(false);
					iniciar.setVisible(false);
					sair.setVisible(false);
					// p�ra a m�sica de abertura
					musica.parar();
					// inicia o jogo em si
					tela_jogoAtivo();
				}
			}
		);
		
		// adiciona o bot�o sair � janela
		sair.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		sair.setRolloverIcon(new ImageIcon(getImage("sair_aceso.jpg")));
		janela.add(sair);
		sair.setBounds(197,310,150,35);

		// a��es a serem executadas ao clica em iniciar
		sair.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// encerra o aplicativo
					System.exit(0);
				}
			}
		);
	}
	
	// monta a tela de jogo
	public void tela_jogoAtivo(){
		
		// toca a m�sica de inicio de jogo
		musica.escolheMusica("start");
		musica.iniciar();
		
		// adiciona o painel que mostra as mensagens
		janela.add(mensagem);
		mensagem.setBounds(180,163,175,80);
		
		// adiciona a imagem de fundo � janela
		janela.add(tela);
		tela.setBounds(1,1,542,464);
		
		// adiciona os bot�es coloridos � tela
		janela.add(azul);
		azul.setBounds(339,22,159,157);

		janela.add(amarelo);
		amarelo.setBounds(38,34,159,157);
		
		janela.add(verde);
		verde.setBounds(191,300,159,157);
		
		janela.add(vermelho);
		vermelho.setBounds(188,1,159,131);
		
		janela.add(laranja);
		laranja.setBounds(23,199,159,157);
		
		janela.add(violeta);
		violeta.setBounds(369,196,159,157);
		
		// Thread que aguarda a m�sica de in�cio de jogo tocar um pouco e inicia o jogo
		new Thread(new Runnable(){
			public void run(){
				try{
					Thread.sleep(4800);
				} catch (Exception e){}
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						// p�ra a m�sica de in�cio de jogo
						musica.parar();
						// muda a mensagem de aguarde para jogue
						mensagem.setIcon(new ImageIcon(getImage("jogue.jpg")));
						// torna os bot�es "clic�veis"
						setStatusBotoes(true);
						// pisca a primeira sequ�ncia de bot�es
						piscarBotoes();
					}
				});
			}
		}).start();
		
		// a��es a serem executadas ao clica no bot�o vermelho
		vermelho.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					// verifica se seu status e o jogo s�o true, isto �, se n�o existe outro bot�o aceso e se o usu�rio ainda n�o perdeu ou ganhou
					if ((vermelho.getStatus()) && (jogoAtivo == true)){
						// pisca o bot�o
						piscarBotao(vermelho);
						// adiciona a jogada ao vetor sequenciaUsuario e verifica se est� correto
						jogoAtivo = sequencia.adicionarJogada(vermelho.getNome());
						// Thread que aguarda
						new Thread(new Runnable(){
							public void run(){
								try{
									Thread.sleep(800);
								} catch (Exception e){}
								EventQueue.invokeLater(new Runnable(){
									public void run(){
										// m�todo de verifica��o
										acao();
									}
								});
							}
						}).start();
					}
				}
			}
		);
		
		// �dem ao botao vermelho
		azul.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if ((azul.getStatus()) && (jogoAtivo == true)){
						piscarBotao(azul);
						jogoAtivo = sequencia.adicionarJogada(azul.getNome());
						new Thread(new Runnable(){
							public void run(){
								try{
									Thread.sleep(800);
								} catch (Exception e){}
								EventQueue.invokeLater(new Runnable(){
									public void run(){
										acao();
									}
								});
							}
						}).start();
					}
				}
			}
		);
		
		// �dem ao botao vermelho
		violeta.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if ((violeta.getStatus()) && (jogoAtivo == true)){
						piscarBotao(violeta);
						jogoAtivo = sequencia.adicionarJogada(violeta.getNome());
						new Thread(new Runnable(){
							public void run(){
								try{
									Thread.sleep(800);
								} catch (Exception e){}
								EventQueue.invokeLater(new Runnable(){
									public void run(){
										acao();
									}
								});
							}
						}).start();
					}
				}
			}
		);
		
		// �dem ao botao vermelho
		verde.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if ((verde.getStatus()) && (jogoAtivo == true)){
						piscarBotao(verde);
						jogoAtivo = sequencia.adicionarJogada(verde.getNome());
						new Thread(new Runnable(){
							public void run(){
								try{
									Thread.sleep(800);
								} catch (Exception e){}
								EventQueue.invokeLater(new Runnable(){
									public void run(){
										acao();
									}
								});
							}
						}).start();
					}
				}
			}
		);
		
		// �dem ao botao vermelho
		laranja.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if ((laranja.getStatus()) && (jogoAtivo == true)){
						piscarBotao(laranja);
						jogoAtivo = sequencia.adicionarJogada(laranja.getNome());
						new Thread(new Runnable(){
							public void run(){
								try{
									Thread.sleep(800);
								} catch (Exception e){}
								EventQueue.invokeLater(new Runnable(){
									public void run(){
										acao();
									}
								});
							}
						}).start();
					}
				}
			}
		);
		
		// �dem ao botao vermelho
		amarelo.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if ((amarelo.getStatus()) && (jogoAtivo == true)){
						piscarBotao(amarelo);
						jogoAtivo = sequencia.adicionarJogada(amarelo.getNome());
						new Thread(new Runnable(){
							public void run(){
								try{
									Thread.sleep(800);
								} catch (Exception e){}
								EventQueue.invokeLater(new Runnable(){
									public void run(){
										acao();
									}
								});
							}
						}).start();
					}
				}
			}
		);
	}
	
	// m�todo que pisca um bot�o que � recebido como par�metro
	public void piscarBotao(final Botao botao){
		// impede que os outros bot�es sejam clicados
		setStatusBotoes(false);
		// acende o bot�o, isto �, muda a imagem do bot�o para acesa
		botao.acenderBotao(botao);
		// escolhe e toca a m�sica correspondente ao bot�o
		musica.escolheMusica(botao.getNome());
		musica.iniciar();
		// Thread que faz o sistema aguardar um pouco 
		new Thread(new Runnable(){
			public void run(){
				try{
					Thread.sleep(800);
				} catch (Exception e){}
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						// apaga o bot�o, isto �, muda a imagem do bot�o para apagado
						botao.apagarBotao(botao);
						// p�ra a m�sica do bot�o
						musica.parar();
					}
				});
			}
		}).start();
	}
	
	// m�todo que pisca os bot�es na sequ�ncia rand�mica
	public void piscarBotoes(){
		new Thread(new Runnable(){
			public void run(){
				try{
					// muda a mensagem de jogue para aguarde
					mensagem.setIcon(new ImageIcon(getImage("aguarde.jpg")));
					// la�o para piscar todos os bot�es
					for (int i=0; i<sequencia.getRodada()+1; i++){
						switch (sequencia.getBotao(i)){
						case 0:
							// aguarda
							Thread.sleep(500);
							// pisca o bot�o vermelho
							piscarBotao(vermelho);
							// aguarda
							Thread.sleep(500);
						break;
						case 1:
							// aguarda
							Thread.sleep(500);
							// pisca o bot�o azul
							piscarBotao(azul);
							// aguarda
							Thread.sleep(500);
						break;
						case 2:
							// aguarda
							Thread.sleep(500);
							// pisca o bot�o violeta
							piscarBotao(violeta);
							// aguarda
							Thread.sleep(500);
						break;
						case 3:
							// aguarda
							Thread.sleep(500);
							// pisca o bot�o verde
							piscarBotao(verde);
							// aguarda
							Thread.sleep(500);
						break;
						case 4:
							// aguarda
							Thread.sleep(500);
							// pisca o bot�o laranja
							piscarBotao(laranja);
							// aguarda
							Thread.sleep(500);
						break;
						case 5:
							// aguarda
							Thread.sleep(500);
							// pisca o bot�o amarelo
							piscarBotao(amarelo);
							// aguarda
							Thread.sleep(500);
						break;
						}
					}
					// permite que os outros bot�es sejam clicados
					setStatusBotoes(true);
					// muda a mensagem de jogue para aguarde
					mensagem.setIcon(new ImageIcon(getImage("jogue.jpg")));
				}catch (Exception e){}
			}
		}).start();
	}
	
	// altera o status de todos os bot�es para o valor recebido como par�metro 
	public void setStatusBotoes(boolean status){
		azul.setStatus(status);
		amarelo.setStatus(status);
		verde.setStatus(status);
		vermelho.setStatus(status);
		laranja.setStatus(status);
		violeta.setStatus(status);
	}
	
	// m�todo de verifica��o
	public void acao(){
		// se usu�rio errou: mostra game over e impede de clicar em bot�es
		if (jogoAtivo == false){
			mensagem.setIcon(new ImageIcon(getImage("gameover.jpg")));
			setStatusBotoes(false);
		}
		// se usuario ganhou: mostra parab�ns e impede de clicar em bot�es
		if (sequencia.getRodada() == sequencia.getTam()){
			jogoAtivo = false;
			mensagem.setIcon(new ImageIcon(getImage("parabens.jpg")));
			setStatusBotoes(false);
		}
		// se o jogo estiver ativo, e a sequ�nciaUsuario estiver vazia (indica que mudou de rodada e tem que piscar os bot�es novamente) 
		if (jogoAtivo == true){
			if (sequencia.pegarUltimo() == 0)
				piscarBotoes();
			setStatusBotoes(true);
		}
	}
	public static URL getImage(String image) {
	    return Jogo.class.getResource("/"+image);
	}
}