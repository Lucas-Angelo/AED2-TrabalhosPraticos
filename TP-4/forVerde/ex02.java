import java.io.*;

public class ex02 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		String idInformado = new String();

		int qtdJogadores = qtdLinhas(leitura); // Procurar quantas linhas/jogadores existem para deixar mais limpo o
												// codigo

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores); // Primeiro preencher o vetor com os dados da
																			// tabela

		ATB arvore = new ATB();

		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				arvore.inserir(players[Integer.parseInt(idInformado)]);

			}

		} while (!(idInformado.equals("FIM")));

		String nome;
		Jogador buscado;
		long inicio = System.currentTimeMillis(); // Para o log
		do {
			nome = in.readLine(); // Qual jogador deseja procurar

			if (!(nome.equals("FIM"))) {

				buscado = null;
				for (int i = 0; i < players.length && buscado == null ; i++)
					if (players[i].getNome().equals(nome))
						buscado = players[i];

				if (arvore.buscar(buscado) == null)
					System.out.print("NAO\n");
				else
					System.out.print("SIM\n");

			}

		} while (!(nome.equals("FIM")));
		long fim = System.currentTimeMillis();
		int comparacoes = arvore.getComparacoes();
		gerarLog(inicio, fim, comparacoes);
	}

	public static void gerarLog(long inicio, long fim, int comparacoes) {
		long mili = fim - inicio;

		ArquivoTextoEscrita escrita = new ArquivoTextoEscrita();
		String log = new String("705903,692669,689603\t" + mili + "\t" + comparacoes);

		escrita.abrirArquivo("matricula_arvoreArvore.txt");
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

			// Caso necessite de remover os asterisco s� tirar o comen�rio das linhas abaixo
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

//Arvore Ternaria de Busca
class ATB {

	private NodoATB raiz;
	private int comparacoes;

	public ATB() {
		comparacoes = 0;
		raiz = null;
	}

	public Boolean arvoreVazia() {
		if (this.raiz == null)
			return true;
		else
			return false;
	}

	private NodoATB adicionar(NodoATB raizArvore, Jogador jogadorNovo) {

		if (raizArvore == null)
			raizArvore = new NodoATB(jogadorNovo);
		else {
			if ((raizArvore.chave) > (jogadorNovo.getAltura() % 17))
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			else if ((raizArvore.chave) < (jogadorNovo.getAltura() % 17))
				raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
			else {
				if (raizArvore.meio == null)
					raizArvore.meio = new ABB(jogadorNovo);
				else
					raizArvore.meio.inserir(jogadorNovo);
			}
		}
		return raizArvore;
	}

	public void inserir(Jogador jogadorNovo) {
		this.raiz = adicionar(this.raiz, jogadorNovo);
	}

	public Jogador buscar(Jogador buscado) {
		return pesquisar(raiz, buscado);
	}

	private Jogador pesquisar(NodoATB raizArvore, Jogador buscado) {

		Jogador pesquisado = null;
		int alturaPesquisada = buscado.getAltura() % 17;
		String nomePesquisado = buscado.getNome();
		comparacoes++;

		if (raizArvore == null)
			pesquisado = null;
		else {
			System.out.print(raizArvore.chave + " ");
			if (raizArvore.chave == alturaPesquisada) {
				pesquisado = raizArvore.meio.buscar(nomePesquisado);
				comparacoes += raizArvore.meio.getComparacoes();
			} else if (alturaPesquisada < raizArvore.chave) 
				pesquisado = pesquisar(raizArvore.esquerda, buscado);
			else
				pesquisado = pesquisar(raizArvore.direita, buscado);
		}

		return pesquisado;
	}

	public int getComparacoes() {
		return comparacoes;
	}

}

class NodoATB {

	int chave; // altura do jogador
	NodoATB direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoATB esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.
	ABB meio;

	public NodoATB(Jogador registro) {
		chave = registro.getAltura() % 17;
		direita = null;
		esquerda = null;
		meio = new ABB(registro);
	}
}

class ABB {

	private NodoABB raiz;
	private int comparacoes;

	public ABB() {
		raiz = null;
		comparacoes = 0;
	}
	public ABB (Jogador registro){
		raiz = new NodoABB(registro);
		comparacoes = 0;
	}

	public Boolean arvoreVazia() {
		if (this.raiz == null)
			return true;
		else
			return false;
	}

	private NodoABB adicionar(NodoABB raizArvore, Jogador jogadorNovo) {
		if (raizArvore == null)
			raizArvore = new NodoABB(jogadorNovo);
		else {
			if ((raizArvore.chave).compareTo(jogadorNovo.getNome()) > 0)
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			else {
				if ((raizArvore.chave).compareTo(jogadorNovo.getNome()) < 0)
					raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
				else
					System.out.println("O jogador " + jogadorNovo.getNome() + ", cuja id e " + jogadorNovo.getId()
							+ ", ja foi inserido anteriormente na arvore.");
			}
		}
		return raizArvore;
	}

	public void inserir(Jogador jogadorNovo) {
		this.raiz = adicionar(this.raiz, jogadorNovo);
	}

	public Jogador buscar(String nomePesquisado) {
		Jogador pesquisado;

		NodoABB resultado = pesquisar(raiz, nomePesquisado);

		if (resultado == null)
			pesquisado = null;
		else
			pesquisado = resultado.item;

		return pesquisado;
	}

	private NodoABB pesquisar(NodoABB raizArvore, String nomePesquisado) {

		NodoABB pesquisado;
		comparacoes++;

		if (raizArvore == null)
			pesquisado = null;
		else {
			System.out.print(raizArvore.chave + " ");
			if (raizArvore.chave.equals(nomePesquisado)) {
				pesquisado = raizArvore;
			} else if ((raizArvore.chave).compareTo(nomePesquisado) > 0) {
				pesquisado = pesquisar(raizArvore.esquerda, nomePesquisado);
			} else {
				pesquisado = pesquisar(raizArvore.direita, nomePesquisado);
			}
		}

		return pesquisado;
	}

	public int getComparacoes() {
		return comparacoes;
	}

}

class NodoABB  {

	Jogador item; // contem os dados do jogador armazenado no nodo da arvore.
	String chave; //altura do jogador
	NodoABB direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoABB esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.

	public NodoABB(Jogador registro){
		item = registro;
		chave = registro.getNome();
		direita = null;
		esquerda = null;
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
			System.out.println("Arquivo não encontrado");
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
		} catch (EOFException excecao) { // Exceção de final de arquivo.
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
