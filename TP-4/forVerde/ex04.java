import java.io.*;

public class ex04 {

	public static void main(String[] args) throws IOException {

		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		String idInformado = new String();

		int qtdJogadores = qtdLinhas(leitura); // Procurar quantas linhas/jogadores existem para deixar mais limpo o
												// codigo

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores); // Primeiro preencher o vetor com os dados da
																			// tabela

		ABB arvore = new ABB();

		arvore.inserirTodosJogadores(players);

		arvore.imprimirOrdenado();

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

class ABB {

	private NodoJogador raiz;

	public ABB() {
		raiz = null;
	}

	public Boolean arvoreVazia() {
		if (this.raiz == null)
			return true;
		else
			return false;
	}

	public void inserirTodosJogadores(Jogador jogadores[]) {
		for (int i = 0; i < jogadores.length; i++) {
			inserir(jogadores[i]);
		}

	}

	// Que chama o metodo recursivo com ordenacao tree sort
	public void inserir(Jogador novo) {
		raiz = adicionar(raiz, novo);
	}

	private NodoJogador adicionar(NodoJogador raizArvore, Jogador novo) {

		// Achou
		if (raizArvore == null) {
			raizArvore = new NodoJogador(novo);
			return raizArvore;
		}

		// Continuar procurando para o lugar certo
		if ((novo.getNome()).compareTo(raizArvore.item.getNome()) < 0)
			raizArvore.esquerda = adicionar(raizArvore.esquerda, novo);
		else if ((novo.getNome()).compareTo(raizArvore.item.getNome()) > 0)
			raizArvore.direita = adicionar(raizArvore.direita, novo);

		return raizArvore;
	}

	void imprimirOrdenado() {
		imprimirOrdenado(this.raiz);
	}

	// Impressao recursiva em ordem
	void imprimirOrdenado(NodoJogador raiz) {
		if (raiz != null) {
			imprimirOrdenado(raiz.esquerda);
			raiz.item.imprimir();
			imprimirOrdenado(raiz.direita);
		}
	}

	private NodoJogador antecessor(NodoJogador jogadorRetirar, NodoJogador raizArvore) {
		if (raizArvore.direita != null) {
			raizArvore.direita = antecessor(jogadorRetirar, raizArvore.direita);
			return raizArvore;
		} else {
			jogadorRetirar.item.setId(raizArvore.item.getId());
			jogadorRetirar.item.setNome(raizArvore.item.getNome());
			jogadorRetirar.item.setAltura(raizArvore.item.getAltura());
			jogadorRetirar.item.setPeso(raizArvore.item.getPeso());
			jogadorRetirar.item.setUniversidade(raizArvore.item.getNome());
			jogadorRetirar.item.setAnoNascimento(raizArvore.item.getAnoNascimento());
			jogadorRetirar.item.setCidadeNascimento(raizArvore.item.getNome());
			jogadorRetirar.item.setEstadoNascimento(raizArvore.item.getNome());
			return raizArvore.esquerda;
		}
	}

	private NodoJogador retirar(NodoJogador raizArvore, String nome) {
		if (raizArvore == null) {
			System.out.println("O jogador, cuja matricula e " + nome + ", nao foi encontrado.");
			return raizArvore;
		} else {
			if (raizArvore.item.getNome().equals(nome)) {
				if (raizArvore.direita == null)
					return (raizArvore.esquerda);
				else if (raizArvore.esquerda == null)
					return (raizArvore.direita);
				else {
					raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
					return (raizArvore);
				}
			} else {
				if ((raizArvore.item.getNome()).compareTo(nome) > 0)
					raizArvore.esquerda = retirar(raizArvore.esquerda, nome);
				else
					raizArvore.direita = retirar(raizArvore.direita, nome);
				return raizArvore;
			}
		}
	}

	public void remover(String nomeParaRemover) {
		this.raiz = retirar(this.raiz, nomeParaRemover);
	}

	public void imprimirEmOrdem() {
		imprimirEmOrdem(raiz);
	}

	private void imprimirEmOrdem(NodoJogador raizArvore) {

		if (raizArvore != null) {
			imprimirEmOrdem(raizArvore.esquerda);
			System.out.print(raizArvore.item.getNome() + " | ");
			imprimirEmOrdem(raizArvore.direita);
		}

	}

	public Jogador menorId() {
		Jogador menor = null;

		if (!arvoreVazia())
			menor = pesquisarMenor(raiz).item;

		return menor;
	}

	private NodoJogador pesquisarMenor(NodoJogador raizArvore) {

		if (raizArvore != null)
			if (raizArvore.esquerda == null)
				return raizArvore;
			else
				return pesquisarMenor(raizArvore.esquerda);
		else
			return null;

	}

	public Jogador buscar(String nomePesquisado) {
		Jogador pesquisado;

		NodoJogador resultado = pesquisar(raiz, nomePesquisado);

		if (resultado == null)
			pesquisado = null;
		else
			pesquisado = resultado.item;

		return pesquisado;
	}

	private NodoJogador pesquisar(NodoJogador raizArvore, String nomePesquisado) {

		NodoJogador pesquisado;

		if (raizArvore == null)
			pesquisado = null;
		else {
			if (raizArvore.item.getNome().equals(nomePesquisado)) {
				System.out.print(raizArvore.item.getNome() + " ");
				pesquisado = raizArvore;
			} else if ((raizArvore.item.getNome()).compareTo(nomePesquisado) > 0) {
				System.out.print(raizArvore.item.getNome() + " ");
				pesquisado = pesquisar(raizArvore.esquerda, nomePesquisado);
			} else {
				System.out.print(raizArvore.item.getNome() + " ");
				pesquisado = pesquisar(raizArvore.direita, nomePesquisado);
			}
		}

		return pesquisado;
	}

	public int numJogadores() {
		return contarNumJogadores(raiz);
	}

	private int contarNumJogadores(NodoJogador raizArvore) {

		if (raizArvore == null)
			return 0;
		else
			return 1 + contarNumJogadores(raizArvore.esquerda) + contarNumJogadores(raizArvore.direita);
	}

}


class NodoJogador {

	Jogador item; // contem os dados do jogador armazenado no nodo da arvore.
	NodoJogador direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoJogador esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.

	public NodoJogador(Jogador registro) {
		item = registro;
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
