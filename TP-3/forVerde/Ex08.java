import java.io.*;

public class Ex08 {

	public static void main(String[] args) throws NumberFormatException, Exception {
		int comparacoes; // Para o log
		int movimentacoes; // Para o log

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String entrada = new String();

		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		int qtdJogadores = qtdLinhas(leitura); // Cada linha ï¿½ um jogador, procurar o nï¿½mero de linhas pra facilitar
		int i = 0; // Utilizado para selecionar posicoes do vetor com alguns jogadores, e no final
					// saber quantos jogadores tem no vetor
		int qtdJogadoresVetor;

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores);
		ListaJogador lista = new ListaJogador();

		do {
			entrada = in.readLine();

			if (!(entrada.equals("FIM"))) {

				lista.inserirFinal(players[Integer.parseInt(entrada)]); // Clona os dados do jogador com ID informado
				i++;

			}
		} while (!(entrada.equals("FIM")));

		qtdJogadoresVetor = i; // Agora ja se sabe quantos jogadores existem nesse vetor

		long inicio = System.currentTimeMillis();
		lista.quickSort();
		long fim = System.currentTimeMillis();

		lista.imprimir();

		comparacoes = lista.getComparacoes();
		movimentacoes = lista.getMovimentacoes();
		gerarLog(inicio, fim, comparacoes, movimentacoes);

	}

	public static void gerarLog(long inicio, long fim, int comparacoes, int movimentacoes) {
		long mili = fim - inicio;

		ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
		String log = new String("705903,692669,689603\t" + mili + "\t" + comparacoes + "\t" + movimentacoes);

		escrita.abrirArquivo("matricula_quicksort2.txt");
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

			// Caso necessite de remover os asterisco sï¿½ tirar o comenï¿½rio das linhas
			// abaixo
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

class Celula {

	Jogador item;
	Celula anterior;
	Celula proximo;

	public Celula(Jogador novo) {
		this.item = novo;
		this.proximo = null;
		this.anterior = null;
	}

	public Celula() {
		this.item = new Jogador();
		this.proximo = null;
		this.anterior = null;
	}

}

class ListaJogador {

	private Celula primeiro;
	private Celula ultimo;
	private int tamanho;
	private int comparacoes;
	private int movimentacoes;

	public ListaJogador() {
		Celula sentinela = new Celula();
		primeiro = ultimo = sentinela;
		tamanho = comparacoes = movimentacoes = 0;
	}

	public void inserirFinal(Jogador novo) throws Exception {

		Celula nova = new Celula(novo); // Criar nova celula com dados do jogador
		ultimo.proximo = nova; // Colocar essa nova com o final da lista
		nova.anterior = ultimo; // O anterior da nova apontar pra lista
		ultimo = nova; // Mover o ultimo pra novo adicionada
		tamanho++;

	}

	public Jogador remover(int posicao) throws Exception {

		Jogador retirado = null;

		if (!listaVazia()) {

			if (posicao < tamanho) {

				retirado = ultimo.item; // Pegar dados do ultimo jogador
				ultimo = ultimo.anterior; // Colocar o ultimo uma celula atras
				ultimo.proximo.anterior = null; // Zerar o dado anterior da celula retirada
				ultimo.proximo = null; // Colocar o ultimo atualizado apontando pra nulo, nao mais pra celula retirada

			} else {
				new Exception("Erro ao tentar remover da lista: posição inválida!");
			}

		} else {
			new Exception("Erro ao tentar remover da lista: lista vazia!");
		}

		return retirado;

	}

	public boolean listaVazia() {
		boolean res;

		if (primeiro == ultimo) { // ou: if(tamanho==0)
			res = true;
		} else {
			res = false;
		}

		return res;
	}

	public void imprimir() {
		Celula aux;

		aux = primeiro.proximo;
		while (aux != null) {

			System.out.printf("[%d ## ", aux.item.getId());

			System.out.printf("%s ## ", aux.item.getNome());

			System.out.printf("%d ## ", aux.item.getAltura());

			System.out.printf("%d ## ", aux.item.getPeso());

			System.out.printf("%d ## ", aux.item.getAnoNascimento());

			if (aux.item.getUniversidade().trim().length() == 0) { // Se o dado esta vazio
				System.out.printf("nao informado ## ");
			} else {
				System.out.printf("%s ## ", aux.item.getUniversidade());
			}

			if (aux.item.getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
				System.out.printf("nao informado ## ");
			} else {
				System.out.printf("%s ## ", aux.item.getCidadeNascimento());
			}

			if (aux.item.getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
				System.out.printf("nao informado]\n");
			} else {
				System.out.printf("%s]\n", aux.item.getEstadoNascimento());
			}

			aux = aux.proximo;
		}
	}

	public int obterNumJogadores() {
		return this.tamanho;
	}

	public ListaJogador copiar() {
		ListaJogador copia = new ListaJogador();

		copia.primeiro = primeiro;
		copia.ultimo = ultimo;

		return copia;
	}

	public int getMovimentacoes() {
		return movimentacoes;
	}

	public int getComparacoes() {
		return comparacoes;
	}

	private Celula partition(Celula esquerda, Celula direita) {
		// Capturar o pivo, sendo o ultimo da esquerda para direita
		Jogador playerPivot = direita.item;

		Celula i = esquerda.anterior;

		// Comeca na esquerda e vai andando a parte da lista ate chegar na direita
		for (Celula j = esquerda; j != direita; j = j.proximo) {

			// Todas as condicoes de cidade e nome
			if ((!(j.item.getEstadoNascimento().trim().length() == 0)
					&& (playerPivot.getEstadoNascimento().trim().length() == 0))
					|| (!(j.item.getEstadoNascimento().trim().length() == 0)
							&& (j.item.getEstadoNascimento().compareTo(playerPivot.getEstadoNascimento()) < 0))
					|| ((j.item.getEstadoNascimento().compareTo(playerPivot.getEstadoNascimento()) == 0)
							&& (j.item.getNome().compareTo(playerPivot.getNome()) < 0))) {
				// Para ir pra frente
				i = (i == null) ? esquerda : i.proximo; // Se o i for null pega a esquerda, caso nao pega o proximo dele
				Jogador temp = i.item; // Altera
				i.item = j.item;
				j.item = temp;
				movimentacoes++;
				comparacoes++;
			}

		}
		i = (i == null) ? esquerda : i.proximo; // Se o i for null pega a esquerda, caso nao pega o proximo dele
		Jogador temp = i.item;
		i.item = direita.item;
		direita.item = temp;
		movimentacoes++;
		return i;
	}

	/* A implementacao recursiva do quicksort na lista duplamente encadeada */
	private void _quickSort(Celula esquerda, Celula direita) {
		if (direita != null && esquerda != direita && esquerda != direita.proximo) {
			Celula temp = partition(esquerda, direita);
			_quickSort(esquerda, temp.anterior);
			_quickSort(temp.proximo, direita);
		}
	}

	// A funcao que sera chamada na main
	public void quickSort() {
		// Chamar a funcao recursiva passando o inicio da lista e o fim (Depois vira
		// esquerda e direita)
		_quickSort(primeiro.proximo, ultimo); // Tem que comecar do proximo do primeiro, pois o primeiro e a sentinela
	}

}

