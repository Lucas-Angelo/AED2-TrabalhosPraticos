import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ex5 {

	public static void main(String[] args) throws IOException {
		int comparacoes; // Para o log
		int movimentacoes; // Para o log

		long inicio = System.currentTimeMillis(); // Para o log

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String entrada = new String();

		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura); // Cada linha é um jogador, procurar o número de linhas pra facilitar
		int i = 0; // Utilizado para selecionar posicoes do vetor com alguns jogadores, e no final
					// saber quantos jogadores tem no vetor
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

		Heap ordenar = new Heap(); // Objeto da classe que possui o metodo de ordenar

		vetor = ordenar.sort(vetor, qtdJogadoresVetor); // Vetor recebe de jogadores recebe ele mesmo ordenado por nome

		for (i = 0; i < qtdJogadoresVetor; i++) {
			vetor[i].imprimir(); // Imprime os dados do vetor ordenado por nome
		}

		comparacoes = ordenar.getComparacoes();
		movimentacoes = ordenar.getMovimentacoes();

		long fim = System.currentTimeMillis();
		gerarLog(inicio, fim, comparacoes, movimentacoes);

	}

	public static void gerarLog(long inicio, long fim, int comparacoes, int movimentacoes) {
		long mili = fim - inicio;

		ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
		String log = new String("705903,692669,689603\t" + mili + "\t" + comparacoes + "\t" + movimentacoes);

		escrita.abrirArquivo("matricula_heapsort.txt");
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

class Heap {

	/**
	 * @param args
	 */

	private int comparacoes = 0;
	private int movimentacoes = 0;

	public Jogador[] sort(Jogador[] array, int n) {
		return method(array, n);
	}

	private Jogador[] method(Jogador[] array, int n) {

		// Criando outro vetor, com todos os elementos do vetor anterior reposicionados
		// (uma posição a frente)
		// de forma a ignorar a posição zero
		Jogador[] tmp = new Jogador[n + 1];
		for (int i = 0; i < n; i++) {
			tmp[i + 1] = array[i];
		}
		array = tmp;

		// Contrução do heap
		for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
			constroi(array, tamHeap);
		}

		// Ordenação propriamente dita
		int tamHeap = n;
		while (tamHeap > 1) {
			troca(array, 1, tamHeap--);
			reconstroi(array, tamHeap);
		}

		// Alterar o vetor para voltar à posição zero
		tmp = array;
		array = new Jogador[n];
		for (int i = 0; i < n; i++) {
			array[i] = tmp[i + 1];
		}

		return array;
	}

	private void constroi(Jogador[] array, int tamHeap) {

		for (int i = tamHeap; i > 1 && (array[i].getAltura() > array[i / 2].getAltura()
				|| (array[i].getNome().compareTo(array[i / 2].getNome()) > 0
						&& array[i].getAltura() == array[i / 2].getAltura())); i /= 2) {
			troca(array, i, i / 2);
			this.comparacoes++;
		}
	}

	private void reconstroi(Jogador[] array, int tamHeap) {

		int i = 1;

		while (i <= (tamHeap / 2)) {
			int filho = getMaiorFilho(array, i, tamHeap);
			if (array[i].getAltura() < array[filho].getAltura()
					|| ((array[i].getNome().compareTo(array[filho].getNome()) < 0
							&& array[i].getAltura() == array[filho].getAltura()))) {
				troca(array, i, filho);
				i = filho;
			} else {
				i = tamHeap;
			}
			this.comparacoes++;
		}
	}

	private int getMaiorFilho(Jogador[] array, int i, int tamHeap) {

		int filho;

		if ((2 * i == tamHeap || array[2 * i].getAltura() > array[2 * i + 1].getAltura())
				|| (((array[2 * i].getNome().compareTo(array[2 * i + 1].getNome()) > 0
						&& array[2 * i].getAltura() == array[2 * i + 1].getAltura())))) {
			filho = 2 * i;
		} else {
			filho = 2 * i + 1;
		}
		this.comparacoes++;
		return filho;
	}

	private void troca(Jogador[] array, int i, int j) {
		Jogador aux;

		aux = array[i];
		array[i] = array[j];
		array[j] = aux;
		this.movimentacoes++;
	}

	public int getComparacoes() {
		return this.comparacoes;
	}

	public int getMovimentacoes() {
		return this.movimentacoes;
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
