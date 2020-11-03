import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura);

		Jogador allPlayers[] = preencherJogadores(leitura, qtdJogadores);
		FilaCircular fila = new FilaCircular(5);
		int id;

		String read;
		do {
			read = in.readLine();

			if (!read.equals("FIM")) {
				id = Integer.parseInt(read);
				fila.enfileirar(allPlayers[id]);
				System.out.println((int) fila.obterMediaAltura());
			}

		} while (!read.equals("FIM"));

		int qtdcomandos = Integer.parseInt(in.readLine());
		int toStackUpId;

		String comando = new String();
		for (int i = 0; i < qtdcomandos; i++) {

			comando = in.readLine();

			if (comando.charAt(0) == 'I') {

				toStackUpId = Integer.parseInt(comando.substring(2, comando.length()));
				fila.enfileirar(allPlayers[toStackUpId]);

				System.out.println((int) fila.obterMediaAltura());

			} else if (comando.charAt(0) == 'R')
				System.out.println("(R) " + fila.desenfileirar().getNome());

		}

		fila.mostrar();

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

			// Caso necessite de remover os asterisco s� tirar o comen�rio das linhas
			// abaixo
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
