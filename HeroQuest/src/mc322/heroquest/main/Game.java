package mc322.heroquest.main;

import java.util.Scanner;

public class Game {
    private boolean fim;

    public Game() {
      fim = false;
    }

    public void start() {
        System.out.println("Jogo iniciado.");
        Scanner key = new Scanner(System.in);
        Heroi personagem;
        System.out.println("Qual sera o nome do seu personagem?");
        String nome = key.nextLine();
        System.out.println("Qual sera sua classe (Digite o numero): 1.Anao, 2.Barbaro, 3.Elfo, 4.Feiticeiro.");
        int classe;
        do{
        	classe = key.nextInt();
        	if(classe == 1) {
        		personagem = new Anao(nome);
        	}
        	else if(classe == 2) {
        		personagem = new Barbaro(nome);
        	}
        	else if(classe == 3) {
        		personagem = new Elfo(nome);
        	}
        	else if(classe == 4) {
        		personagem = new Feiticeiro(nome);
        	}
        } while(classe > 4 && classe < 1);
        
        while(!fim) {
            drawBoard();
            lerInput(personagem);
            updateBoard();
        }
        System.out.println("Fim de jogo. Ate logo!");
    }

    public void lerInput(Heroi personagem) {
        int movimento = personagem.jogarDadosAndar();
        System.out.println("Voce pode andar " + movimento + " casas esse turno.");

        Scanner keyboard = new Scanner (System.in);
        boolean acaoDisponivel = true;
        boolean podeAndar = true;
        boolean passar = false;

        while (!passar && (acaoDisponivel || podeAndar) ) {
            System.out.println("Digite um dos seguintes comandos: \"w-a-s-d\" caso queira andar, \"acao\" caso queira tomar alguma acao com seu personagem ou \"passar\" para passar para a proxima rodada.") ;
            String comando = keyboard.nextLine() ;

            switch(comando) {
                case "w":
                case "a":
                case "s":
                case "d":
                    try {
                        personagem.mover(comando);
                    } //parede array obstaculo
                    catch(ArrayIndexOutOfBoundsException e) {
                        System.err.println(e.getMessage());
                    }
                    catch(ParedeNoCaminhoException e) {
                        System.err.println(e.getMessage());
                    }
                    catch(ObstaculoNoCaminhoException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                    
                case "passar":
                    passar = true;
                    break;
                case "acao":
                    if(!acaoDisponivel) {
                        System.out.println("Voce nao pode realizar uma acao agora.");
                        break;
                    }
                    else {
                        System.out.println("Escolha uma acao entre : atacar (a), usar magia (s), tomar pocao (q), procurar (f)");
                        String acao;
                        do {
                        	acao = keyboard.nextLine();
                        	switch(acao) {
                            	case "a":
                            		//chama a funcao de ataque
                            		break;
                            	case "s":
                            		//chama a funcao de usar magia
                            		break;
                            	case "q":
                            		if(personagem.temPocao()) {
                            			System.out.print(personagem.verificaMochila("Pocao"));
                            			System.out.println("Digite o numero da pocao desejada.");
                            			Scanner teclado;
                            			boolean valido = false;
                            			int opcao = teclado.nextInt();
                            			do {
                            				try {
                            					personagem.usarPocao(opcao);
                            					valido = true;
                            				}
                            				catch(ClassCastException e) {
                            					System.out.println("Opcao invalida. Digite uma das opcoes sugeridas.");
                            				}
                            			}while(!valido);
                            			acaoDisponivel = false;
                            			break;
                            			}
                            			else {
                            				System.out.println("Nao ha pocoes disponiveis");
                            				continue;
                            			}
                            	case "f":
                            		//chama a funcao de olhar armadilhas no mapa
                            		break;
                            	default:
                            		System.out.println("Acao invalida.");
                        	}
                        }while(acaoDisponivel);
                    }
                default:
                    System.out.println("Comando invalido.");
            }
        }
    }

}