import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
	public static void main(String[] args) throws NumberFormatException, Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Fila filaJogadores = new Fila();

		Jogador[] players = preencherJogadores();

		String idInformado = new String();
		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				filaJogadores.enfileirar(players[Integer.parseInt(idInformado)]);
				System.out.println(((int) filaJogadores.obterMediaAltura()));

			}

		} while (!(idInformado.equals("FIM")));

		String[] dadosAcao;
		Jogador desenfilierado = new Jogador();

		int i = 0, id;
		int qtd = Integer.parseInt(in.readLine());

		while (i < qtd) {

			String acao = in.readLine();

			if (acao.charAt(0) == 'I') {
				dadosAcao = acao.split(" ", 2);
				id = Integer.parseInt(dadosAcao[1].toString());
				filaJogadores.enfileirar(players[id]);
				System.out.println(((int) filaJogadores.obterMediaAltura()));
			} else if (acao.charAt(0) == 'R') {
				desenfilierado = filaJogadores.desenfileirar();
				System.out.println("(R) " + desenfilierado.getNome());
			}

			i++;
		}

		filaJogadores.mostrar();

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

	public static Jogador[] preencherJogadores() throws Exception {
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();
		int qtdJogadores = qtdLinhas(leitura);

		Jogador atual = new Jogador();
		Jogador[] todosJogadores = new Jogador[qtdJogadores];
		for (int i = 0; i < qtdJogadores; i++)
			todosJogadores[i] = new Jogador();

		leitura.abrirArquivo("players.csv");

		leitura.ler(); // Remove o cabecalho
		for (int i = 0; i < qtdJogadores; i++) {

			String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha

			// Caso necessite de remover os asterisco só tirar o comenário das linhas abaixo
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

			todosJogadores[i] = atual;
			atual = new Jogador();
		}

		leitura.fecharArquivo();

		return todosJogadores;
	}

}
