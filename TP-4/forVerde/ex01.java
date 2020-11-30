import java.io.*;

public class ex01 {

	/*
	 * Considerações: 1- Deixamos comentado um código para caso for necessário
	 * remover o asterisco do nome do jogador.
	 */

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		String idInformado = new String();

		int qtdJogadores = qtdLinhas(leitura); // Procurar quantas linhas/jogadores existem para deixar mais limpo o
												// codigo

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores); // Primeiro preencher o vetor com os dados da
																			// tabela

		ABB arvore = new ABB();

		do {
			idInformado = in.readLine(); // Qual jogador deseja procurar

			if (!(idInformado.equals("FIM"))) {

				arvore.inserir(players[Integer.parseInt(idInformado)]);

			}

		} while (!(idInformado.equals("FIM")));

		String nome;
		do {
			nome = in.readLine(); // Qual jogador deseja procurar

			if (!(nome.equals("FIM"))) {

				if (arvore.buscar(nome) == null)
					System.out.print("NAO\n");
				else
					System.out.print("SIM\n");

			}

		} while (!(nome.equals("FIM")));

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

	private NodoJogador raiz; // referência à raiz da árvore.

	/// Construtor da classe.
	/// Esse construtor cria uma nova árvore binária de alunos vazia. Para isso,
	/// esse método atribui null à raiz da árvore.
	public ABB() {
		raiz = null;
	}

	/// Método booleano que indica se a árvore está vazia ou não.
	/// Retorna:
	/// verdadeiro: se a raiz da árvore for null, o que significa que a árvore
	/// está vazia.
	/// falso: se a raiz da árvore não for null, o que significa que a árvore
	/// não está vazia.
	public Boolean arvoreVazia() {
		/// Se a raiz da árvore for null, a árvore está vazia.
		if (this.raiz == null)
			return true;
		/// Caso contrário, a árvore não está vazia.
		else
			return false;
	}

	/// Método recursivo responsável por adicionar um aluno à árvore.
	/// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que o aluno será
	/// adicionado.
	/// Parâmetro "alunoNovo": aluno que deverá ser adicionado à árvore.
	/// Retorna a raiz atualizada da árvore ou sub-árvore em que o aluno foi
	/// adicionado.
	private NodoJogador adicionar(NodoJogador raizArvore, Jogador jogadorNovo) {
		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e então
		/// um novo aluno é inserido.
		if (raizArvore == null)
			raizArvore = new NodoJogador(jogadorNovo);
		else {
			/// Se o número de matrícula do aluno armazenado na raiz da árvore for maior
			/// do que o número de matrícula do aluno que deverá ser inserido na árvore:
			/// adicione esse aluno à sub-árvore esquerda; e atualize a referência para a
			/// sub-árvore esquerda modificada.
			if ((raizArvore.item.getNome()).compareTo(jogadorNovo.getNome()) > 0)
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			else {
				/// Se o número de matrícula do aluno armazenado na raiz da árvore for menor
				/// do que o número de matrícula do aluno que deverá ser inserido na árvore:
				/// adicione esse aluno à sub-árvore direita; e atualize a referência para a
				/// sub-árvore direita modificada.
				if ((raizArvore.item.getNome()).compareTo(jogadorNovo.getNome()) < 0)
					raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
				else
					/// O número de matrícula do aluno armazenado na raiz da árvore é igual ao
					/// número de matrícula do aluno que deveria ser inserido na árvore.
					System.out.println("O aluno " + jogadorNovo.getNome() + ", cuja matricula e " + jogadorNovo.getId()
							+ ", ja foi inserido anteriormente na arvore.");
			}
		}
		/// Retorna a raiz atualizada da árvore ou sub-árvore em que o aluno foi
		/// adicionado.
		return raizArvore;
	}

	/// Metodo que encapsula a adição recursiva de alunos à árvore.
	/// Parâmetro "alunoNovo": aluno que deverá ser inserido na árvore.
	public void inserir(Jogador alunoNovo) {
		/// Chama o método recursivo "adicionar", que será responsável por adicionar,
		/// o aluno passado como parâmetro, à árvore.
		/// O método "adicionar" receberá, como primeiro parâmetro, a raiz atual da
		/// árvore; e, como segundo parâmetro, o aluno que deverá ser adicionado à
		/// árvore.
		/// Por fim, a raiz atual da árvore é atualizada, com a raiz retornada pelo
		/// método "adicionar".
		this.raiz = adicionar(this.raiz, alunoNovo);
	}

	/// Método recursivo responsável por localizar na árvore ou sub-árvore o
	/// antecessor do nó que deverá ser retirado.
	/// O antecessor do nó que deverá ser retirado da árvore corresponde
	/// ao nó que armazena os dados do aluno cujo número de matrícula é o maior,
	/// dentre os números de matrícula menores do que o número de matrícula do
	/// nó que deverá ser retirado.
	/// Depois de ser localizado na árvore ou sub-árvore,
	/// o antecessor do nó que deverá ser retirado da árvore o substitui.
	/// Adicionalmente, a árvore ou sub-árvore é atualizada com a remoção do
	/// antecessor.
	/// Parâmetro "alunoRetirar": referência ao nó que armazena o aluno que
	/// deverá ser retirado da árvore.
	/// Parâmetro "raizArvore": raiz da árvore ou sub-árvore em que o antecessor
	/// do nó que deverá ser retirado deverá ser localizado.
	/// Retorna: raiz atualizada da árvore ou sub-árvore após a remoção do
	/// antecessor do nó que foi retirado da árvore.
	private NodoJogador antecessor(NodoJogador jogadorRetirar, NodoJogador raizArvore) {
		/// Se o antecessor do nó que deverá ser retirado da árvore ainda não foi
		/// encontrado...
		if (raizArvore.direita != null) {
			/// Pesquise o antecessor na sub-árvore direita.
			raizArvore.direita = antecessor(jogadorRetirar, raizArvore.direita);
			return raizArvore;
		}
		/// O antecessor do nó que deverá ser retirado da árvore foi encontrado.
		else {
			/// O antecessor do nó que deverá ser retirado da árvore foi localizado e
			/// deverá substitui-lo.
			jogadorRetirar.item.setId(raizArvore.item.getId());
			jogadorRetirar.item.setNome(raizArvore.item.getNome());
			jogadorRetirar.item.setAltura(raizArvore.item.getAltura());
			jogadorRetirar.item.setPeso(raizArvore.item.getPeso());
			jogadorRetirar.item.setUniversidade(raizArvore.item.getNome());
			jogadorRetirar.item.setAnoNascimento(raizArvore.item.getAnoNascimento());
			jogadorRetirar.item.setCidadeNascimento(raizArvore.item.getNome());
			jogadorRetirar.item.setEstadoNascimento(raizArvore.item.getNome());
			/// A raiz da árvore ou sub-árvore é atualizada com os descendentes à
			/// esquerda do antecessor.
			/// Ou seja, retira-se o antecessor da árvore.
			return raizArvore.esquerda;
		}
	}

	/// Método recursivo responsável por localizar um aluno na árvore e
	/// retirá-lo da árvore.
	/// Parâmetro "raizArvore": raiz da árvore ou sub-árvore da qual o aluno
	/// será retirado.
	/// Parâmetro "matricula": número de matrícula do aluno que deverá ser
	/// localizado e removido da árvore.
	/// Retorna a raiz atualizada da árvore ou sub-árvore da qual o aluno foi
	/// retirado; ou null, caso não tenha sido localizado aluno com o número de
	/// matrícula informado.
	private NodoJogador retirar(NodoJogador raizArvore, String nome) {
		/// Se a raiz da árvore ou sub-árvore for null, a árvore está vazia e o
		/// aluno, que deveria ser retirado dessa árvore, não foi encontrado.
		/// Nesse caso, deve-se retornar null.
		if (raizArvore == null) {
			System.out.println("O jogador, cuja matricula e " + nome + ", nao foi encontrado.");
			return raizArvore;
		} else {
			/// O número de matrícula do aluno armazenado na raiz da árvore é igual ao
			/// número de matrícula do aluno que deve ser retirado dessa árvore.
			/// Ou seja, o aluno que deve ser retirado da árvore foi encontrado.
			if (raizArvore.item.getNome().equals(nome)) {
				/// O nó da árvore que será retirado não possui descendentes à direita.
				/// Nesse caso, os descendentes à esquerda do nó que está sendo retirado da
				/// árvore passarão a ser descendentes do nó-pai do nó que está sendo
				/// retirado.
				if (raizArvore.direita == null)
					return (raizArvore.esquerda);
				else
				/// O nó da árvore que será retirado não possui descendentes à esquerda.
				/// Nesse caso, os descendentes à direita do nó que está sendo retirado da
				/// árvore passarão a ser descendentes do nó-pai do nó que está sendo
				/// retirado.
				if (raizArvore.esquerda == null)
					return (raizArvore.direita);
				else {
					/// O nó que está sendo retirado da árvore possui descendentes à esquerda e
					/// à direita.
					/// Nesse caso, o antecessor do nó que está sendo retirado é localizado na
					/// sub-árvore esquerda desse nó.
					/// O antecessor do nó que está sendo retirado da árvore corresponde
					/// ao nó que armazena o aluno cujo número de matrícula é o maior,
					/// dentre os números de matrícula menores do que o número de matrícula do
					/// nó que está sendo retirado.
					/// Depois de ser localizado na sub-árvore esquerda do nó que está sendo
					/// retirado,
					/// o antecessor desse nó o substitui.
					/// A sub-árvore esquerda do nó que foi retirado é atualizada com a remoção
					/// do antecessor.
					raizArvore.esquerda = antecessor(raizArvore, raizArvore.esquerda);
					/// Retorna a raiz atualizada da árvore ou sub-árvore da qual o aluno foi
					/// retirado.
					return (raizArvore);
				}
			} else {
				/// Se o número de matrícula do aluno armazenado na raiz da árvore for maior
				/// do que o número de matrícula do aluno que deverá ser localizado e
				/// retirado da árvore:
				/// pesquise e retire esse aluno da sub-árvore esquerda.
				if ((raizArvore.item.getNome()).compareTo(nome) > 0)
					raizArvore.esquerda = retirar(raizArvore.esquerda, nome);
				else
					/// Se o número de matrícula do aluno armazenado na raiz da árvore for menor
					/// do que o número de matrícula do aluno que deverá ser localizado e
					/// retirado da árvore:
					/// pesquise e retire esse aluno da sub-árvore direita.
					raizArvore.direita = retirar(raizArvore.direita, nome);
				/// Retorna a raiz atualizada da árvore ou sub-árvore da qual o aluno foi
				/// retirado.
				return raizArvore;
			}
		}
	}

	/// Método que encapsula a retirada recursiva de um aluno da árvore.
	/// Parâmetro "matriculaRemover": número de matrícula do aluno que deverá
	/// ser localizado e removido da árvore.
	public void remover(String nomeParaRemover) {
		/// Chama o método recursivo "retirar", que será responsável por pesquisar o
		/// aluno, cujo número de matrícula foi passado como parâmetro, na árvore e
		/// retirá-lo da árvore.
		/// O método "retirar" receberá, como primeiro parâmetro, a raiz atual da
		/// árvore; e, como segundo parâmetro, o número de matrícula do aluno que
		/// deverá ser localizado e retirado dessa árvore.
		this.raiz = retirar(this.raiz, nomeParaRemover);
	}

	public void imprimirEmOrdem() {
		imprimirEmOrdem(raiz);
	}

	private void imprimirEmOrdem(NodoJogador raizArvore) { // Caso queira imprirmir decrescente s󠴲ocar o .esquerda

		if (raizArvore != null) { // Verificar nao o no de agora nao esta nulo
			imprimirEmOrdem(raizArvore.esquerda);
			System.out.print(raizArvore.item.getNome() + " | ");
			imprimirEmOrdem(raizArvore.direita);
		}

	}

	public Jogador menorNumeroMatricula() {
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
