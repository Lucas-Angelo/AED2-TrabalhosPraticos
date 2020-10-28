import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

        int qtdJogadores = qtdLinhas(leitura);

        Jogador allPlayers[] = preencherJogadores(leitura, qtdJogadores);
        Lista list = new Lista();
        int id;

        String read;
        do{
            read = in.readLine();

            if (!read.equals("FIM")){
                id = Integer.parseInt(read);
                list.inserirFim(allPlayers[id]);
            }

        }while(!read.equals("FIM"));

        int qtdcomandos = Integer.parseInt(in.readLine());
		String split[] = new String[3];
		int tolistUpId;
		int position=1;
		for (int i=0;i<3;i++)
			split[i] = new String();

        String comando = new String();
        for (int i=0;i<qtdcomandos;i++){

            comando = in.readLine();

            if (comando.charAt(0) == 'I'){

				split = comando.split(" ");
				if ( split.length == 2 )
					tolistUpId = Integer.parseInt(split[1]);
				else{
					tolistUpId = Integer.parseInt(split[2]);
					position = Integer.parseInt(split[1]);
				}
					

				if (comando.charAt(1) == 'I')
					list.inserirInicio(allPlayers[tolistUpId]);

				else if (comando.charAt(1) == 'F')
					list.inserirFim(allPlayers[tolistUpId]);

				else if (comando.charAt(1) == '*')
					list.inserir(allPlayers[tolistUpId], position);

            }
			else if (comando.charAt(0) == 'R'){
				
				if (comando.charAt(1) == 'I')
					System.out.println("(R) " + list.removerInicio().getNome());

				else if (comando.charAt(1) == 'F')
					System.out.println("(R) " + list.removerFim().getNome());

				else if (comando.charAt(1) == '*'){
					position = Integer.parseInt(comando.substring(3, comando.length()));
					System.out.println("(R) " + list.remover(position).getNome());
				} 

			}
                
		}

        list.mostrar();

    }

    public static int qtdLinhas(ArquivoTextoLeitura leitura) {
		int qtd = 0;
		String linhaLida = new String();
		leitura.abrirArquivo("players.csv");

		leitura.ler(); // O cabecalho, tem que pular a primeira linha
		linhaLida = leitura.ler();
		while (linhaLida != null) {
			qtd++;
			linhaLida = leitura.ler();
		}

		leitura.fecharArquivo();

		return qtd; // Retorna a quantidade de jogadores/Quantidade de linhas
	}

	public static Jogador[] preencherJogadores(ArquivoTextoLeitura leitura, int qtdJogadores) throws Exception {
		Jogador atual = new Jogador();
		Jogador listaTodosJogadores[] = new Jogador[qtdJogadores]; // Reserva o armazenamento

		leitura.abrirArquivo("players.csv");

		leitura.ler(); // Remove o cabecalho
		for (int i = 0; i < qtdJogadores; i++) {

			String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha

			// Caso necessite de remover os asterisco s� tirar o comen�rio das linhas abaixo
			/*
			 * String nome = dadosDaLinha[1].toString(); char ultima =
			 * nome.charAt(nome.length()-1);
			 * 
			 * if(ultima=='*') { dadosDaLinha[1]=""; for(int z=0; z<nome.length()-1; z++) {
			 * dadosDaLinha[1] += nome.charAt(z); } }
			 */

			atual.setId(Integer.parseInt((dadosDaLinha[0].toString())));
			atual.setNome(dadosDaLinha[1].toString());
			atual.setAltura(Integer.parseInt((dadosDaLinha[2].toString())));
			atual.setPeso(Integer.parseInt((dadosDaLinha[3].toString())));
			atual.setUniversidade(dadosDaLinha[4].toString());
			atual.setAnoNascimento(Integer.parseInt((dadosDaLinha[5].toString())));
			atual.setCidadeNascimento(dadosDaLinha[6].toString());
			atual.setEstadoNascimento(dadosDaLinha[7].toString());

            listaTodosJogadores[i] = atual.clone();
			atual = new Jogador();
		}

		leitura.fecharArquivo();

		return listaTodosJogadores;
	}
}
