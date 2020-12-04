import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

	public static void main(String[] args) throws NumberFormatException, Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		String idInformado = new String();

		int qtdJogadores = qtdLinhas(leitura); // Procurar quantas linhas/jogadores existem para deixar mais limpo o
												// codigo

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores); // Primeiro preencher o vetor com os dados da
																			// tabela

		HashEmAberto tabela = new HashEmAberto(79);

		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				tabela.inserir(players[Integer.parseInt(idInformado)]);

			}

		} while (!(idInformado.equals("FIM")));

		String nome;
		long inicio = System.currentTimeMillis(); // Para o log
		do {
			nome = in.readLine(); // Qual jogador deseja procurar

			if (!(nome.equals("FIM"))) {

				Jogador buscado = null;
				for (int i = 0; i < players.length; i++)
					if (players[i].getNome().equals(nome))
						buscado = players[i];

				if (tabela.pesquisar(buscado) == null)
					System.out.print("NAO\n");
				else
					System.out.print("SIM\n");

			}

		} while (!(nome.equals("FIM")));

		long fim = System.currentTimeMillis();
		int comparacoes = tabela.getComparacoes();
		gerarLog(inicio, fim, comparacoes);

	}

	public static void gerarLog(long inicio, long fim, int comparacoes) {
		long mili = fim - inicio;

		ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
		String log = new String("705903,692669,689603\t" + mili + "\t" + comparacoes);

		escrita.abrirArquivo("matrícula_hashRehashing.txt");
		escrita.escrever(log); // Escreve no arquivo criado o log.
		escrita.fecharArquivo();
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

	public static Jogador[] preencherVetorJogador(ArquivoTextoLeitura leitura, int qtdLinhas) {
		Jogador[] players = new Jogador[qtdLinhas]; // Reserva o armazenamento

		for (int i = 0; i < qtdLinhas; i++)
			players[i] = new Jogador(); // Cria o objeto para cada um

		leitura.abrirArquivo("players.csv");

		leitura.ler(); // Remove o cabecalho
		for (int i = 0; i < qtdLinhas; i++) {

			String[] dadosDaLinha = leitura.ler().split(",", 8); // Dividir os dados da linha

			// Caso necessite de remover os asterisco só tirar o comenário das linhas abaixo
			/*
			 * String nome = dadosDaLinha[1].toString(); char ultima =
			 * nome.charAt(nome.length()-1);
			 * 
			 * if(ultima=='*') { dadosDaLinha[1]=""; for(int z=0; z<nome.length()-1; z++) {
			 * dadosDaLinha[1] += nome.charAt(z); } }
			 */

			// Seta os dados de cada linha para cada atributo do player atual
			players[i].setId(Integer.parseInt((dadosDaLinha[0].toString()))); // Transforma array em string, para
																				// transformar em int
			players[i].setNome(dadosDaLinha[1].toString());
			players[i].setAltura(Integer.parseInt((dadosDaLinha[2].toString())));
			players[i].setPeso(Integer.parseInt((dadosDaLinha[3].toString())));
			players[i].setUniversidade(dadosDaLinha[4].toString());
			players[i].setAnoNascimento(Integer.parseInt((dadosDaLinha[5].toString())));
			players[i].setCidadeNascimento(dadosDaLinha[6].toString());
			players[i].setEstadoNascimento(dadosDaLinha[7].toString());

		}

		leitura.fecharArquivo();

		return players;
	}

}
