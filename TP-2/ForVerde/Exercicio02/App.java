import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

	/*
	 * Considerações: 1- Para o log, colocamos 3 matrículas dos integrantes do nosso
	 * grupo separadas por vírgulas, em seguidas os outros dados
	 * 
	 * 2- Não preenchemos nosso vetor que possuia todos os jogadores removendo o
	 * asterisco do nome, criamos o código para fazer isso, mas deixamos comentado.
	 * Para verificar se o nome informado existia e printar SIM/NAO nós verificamos
	 * o nome com e sem o asterisco. Com isso não alteramos a tabela original,
	 * apenas o que era solicitado, diante disso, algumas comparações a mais foram
	 * necessárias para chegar em 100%.
	 * 
	 * 3- Em relação ao número de comparações, foi contado apenas as comparações
	 * dentro da main, pois nas as outras funcoes sao as genericas vindas da questão
	 * 1.
	 * 
	 */

	public static void main(String[] args) throws IOException {
		int comparacoes = 0; // Para o log

		long inicio = System.currentTimeMillis(); // Para o log

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String entrada = new String();

		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura); // Cada linha é um jogador, procurar o número de linhas pra facilitar
		int i = 0; // Utilizado para selecionar posicoes do vetor com alguns jogadores, e no final
					// saber quantos jogadores tem no vetor
		int z = 0;
		int qtdJogadoresVetor;

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores);
		Jogador[] vetor = new Jogador[qtdJogadores];

		do {
			entrada = in.readLine();

			if (!(entrada.equals("FIM"))) {

				vetor[i] = new Jogador();
				vetor[i] = players[Integer.parseInt(entrada)].clone(); // Clona os dados do jogador com ID informado
				i++;

			}
		} while (!(entrada.equals("FIM")));

		qtdJogadoresVetor = i; // Agora ja se sabe quantos jogadores existem nesse vetor

		do {
			entrada = in.readLine();
			boolean res = false; // Quando for verdadeiro vai mostrar SIM, existe esse jogador no vetor
									// preenchido

			if (!(entrada.equals("FIM"))) {

				char ultimaLetra = entrada.charAt(entrada.length() - 1);
				String entradaSemAsterisco = new String("");
				if (ultimaLetra == '*') { // + Uma comparacao
					for (z = 0; z < entrada.length() - 1; z++) {
						entradaSemAsterisco += entrada.charAt(z); // Se existir vai reescrever o nome sem asterisco
					}
				} else {
					entradaSemAsterisco = entrada;
				}

				// Para diminuir custo, caso encontre o jogador pode finalizar o for
				for (i = 0; i < qtdJogadoresVetor && !res; i++) { // + Uma comparacao de

					// Verificacao para saber se o nome informado existe na tabela com ou sem
					// asterisco
					String nome = vetor[i].getNome();
					char ultima = nome.charAt(nome.length() - 1);
					String nomeSemAsterisco = new String("");
					// Detecta se na ultima posicao do nome do jogador atual existe um asterisco
					if (ultima == '*') { // + Uma comparacao
						for (z = 0; z < nome.length() - 1; z++) {
							nomeSemAsterisco += nome.charAt(z); // Se existir vai reescrever o nome sem asterisco
						}
					} else {
						nomeSemAsterisco = vetor[i].getNome();
					}

					// Se exister o nome imprime SIM
					if (entradaSemAsterisco.equals(nomeSemAsterisco)) {
						res = true;
					}
					comparacoes++;
				}

				if (res)
					System.out.println("SIM");
				else
					System.out.println("NAO");

			}
		} while (!(entrada.equals("FIM")));

		long fim = System.currentTimeMillis();
		gerarLog(inicio, fim, comparacoes);

	}

	public static void gerarLog(long inicio, long fim, int comparacoes) {
		long mili = fim - inicio;

		ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
		String log = new String("705903,692669,689603\t" + mili + "\t" + comparacoes);

		escrita.abrirArquivo("matricula_sequencial.txt");
		escrita.escrever(log); // Escreve no arquivo criado o log.
		escrita.fecharArquivo();
	}

	public static int qtdLinhas(ArquivoTextoLeitura leitura) {
		int qtd = 0;
		String linhaLida = new String();
		leitura.abrirArquivo("/tmp/players.csv");

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

		leitura.abrirArquivo("/tmp/players.csv");

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

class Jogador {

	private int id;
	private String nome = new String();
	private int altura;
	private int peso;
	private String universidade = new String();
	private int anoNascimento;
	private String cidadeNascimento = new String();
	private String estadoNascimento = new String();

	public Jogador() {

	}

	public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
			String cidadeNascimento, String estadoNascimento) {
		this.id = id;
		this.nome = nome;
		this.altura = altura;
		this.peso = peso;
		this.universidade = universidade;
		this.anoNascimento = anoNascimento;
		this.cidadeNascimento = cidadeNascimento;
		this.estadoNascimento = estadoNascimento;
	}

	// Início GETS
	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getAltura() {
		return this.altura;
	}

	public int getPeso() {
		return this.peso;
	}

	public String getUniversidade() {
		return this.universidade;
	}

	public int getAnoNascimento() {
		return this.anoNascimento;
	}

	public String getCidadeNascimento() {
		return this.cidadeNascimento;
	}

	public String getEstadoNascimento() {
		return this.estadoNascimento;
	}
	// Fim GETS

	// Início SETS
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public void setEstadoNascimento(String estadoNascimento) {
		this.estadoNascimento = estadoNascimento;
	}
	// Fim SETS

	public Jogador clone() {

		Jogador jogador = new Jogador();
		jogador.id = this.id;
		jogador.nome = this.nome;
		jogador.altura = this.altura;
		jogador.peso = this.peso;
		jogador.universidade = this.universidade;
		jogador.anoNascimento = this.anoNascimento;
		jogador.cidadeNascimento = this.cidadeNascimento;
		jogador.estadoNascimento = this.estadoNascimento;

		return jogador;
	}

	public void imprimir() {

		System.out.printf("[%d ## ", this.id);

		System.out.printf("%s ## ", this.nome);

		System.out.printf("%d ## ", this.altura);

		System.out.printf("%d ## ", this.peso);

		System.out.printf("%d ## ", this.anoNascimento);

		if (this.universidade.trim().length() == 0) { // Se o dado esta vazio
			System.out.printf("nao informado ## ");
		} else {
			System.out.printf("%s ## ", this.universidade);
		}

		if (this.cidadeNascimento.trim().length() == 0) { // Se o dado esta vazio
			System.out.printf("nao informado ## ");
		} else {
			System.out.printf("%s ## ", this.cidadeNascimento);
		}

		if (this.estadoNascimento.trim().length() == 0) { // Se o dado esta vazio
			System.out.printf("nao informado]\n");
		} else {
			System.out.printf("%s]\n", this.estadoNascimento);
		}

	}

}

class ArquivoTextoLeitura {

	private BufferedReader entrada;

	public void abrirArquivo(String nomeArquivo) {

		try {
			entrada = new BufferedReader(new FileReader(nomeArquivo));
		} catch (FileNotFoundException excecao) {
			System.out.println("Arquivo nÃ£o encontrado");
		}
	}

	public void fecharArquivo() {

		try {
			entrada.close();
		} catch (IOException excecao) {
			System.out.println("Erro no fechamento do arquivo de leitura: " + excecao);
		}
	}

	public String ler() {

		String textoEntrada;

		try {
			textoEntrada = entrada.readLine();
		} catch (EOFException excecao) { // ExceÃ§Ã£o de final de arquivo.
			return null;
		} catch (IOException excecao) {
			System.out.println("Erro de leitura: " + excecao);
			return null;
		}
		return textoEntrada;
	}
}

class ArquivoTextoEscrita {

	private BufferedWriter saida;

	public void abrirArquivo(String nomeArquivo) {

		try {
			saida = new BufferedWriter(new FileWriter(nomeArquivo));
		} catch (FileNotFoundException excecao) {

		} catch (IOException excecao) {

		}
	}

	public void fecharArquivo() {

		try {
			saida.close();
		} catch (IOException excecao) {

		}
	}

	public void escrever(String textoEntrada) {

		try {
			saida.write(textoEntrada);
			saida.newLine();
		} catch (IOException excecao) {

		}
	}
}
