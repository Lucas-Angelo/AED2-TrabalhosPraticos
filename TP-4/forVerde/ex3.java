import java.io.*;

public class ex3 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		ArquivoTextoLeitura leitura = new ArquivoTextoLeitura();

		String idInformado = new String();

		int qtdJogadores = qtdLinhas(leitura); // Procurar quantas linhas/jogadores existem para deixar mais limpo o
												// codigo

		Jogador[] players = preencherVetorJogador(leitura, qtdJogadores); // Primeiro preencher o vetor com os dados da
																			// tabela

		ABBavl arvore = new ABBavl();

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

class ABBavl {

	private NodoJogador raiz;

	public void inserir(Jogador novo) {
		NodoJogador novoJogador = new NodoJogador(novo);
		adicionar(this.raiz, novoJogador);
	}

	private void adicionar(NodoJogador aComparar, NodoJogador aInserir) {

		if (aComparar == null) { // Se achou o nodo vazio correto
			this.raiz = aInserir; // Insere

		} else { // Caso contrario verificar a esquerda e direita pra saber qual lado ir

			if ((aInserir.item.getNome()).compareTo(aComparar.item.getNome()) < 0) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					adicionar(aComparar.getEsquerda(), aInserir);
				}

			} else if ((aInserir.item.getNome()).compareTo(aComparar.item.getNome()) > 0) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					adicionar(aComparar.getDireita(), aInserir);
				}

			} else {
				System.out.println("O jogador " + aInserir.item.getNome() + ", cuja id e " + aInserir.item.getId()
						+ ", ja foi inserido anteriormente na arvore.");
			}
		}
	}

	private void verificarBalanceamento(NodoJogador atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(Jogador aRemover) {
		removerAVL(this.raiz, aRemover);
	}

	private void removerAVL(NodoJogador atual, Jogador aRemover) {
		if (atual == null) {
			return;

		} else {

			if ((atual.item.getNome()).compareTo(aRemover.getNome()) > 0) {
				removerAVL(atual.getEsquerda(), aRemover);

			} else if ((atual.item.getNome()).compareTo(aRemover.getNome()) < 0) {
				removerAVL(atual.getDireita(), aRemover);

			} else if ((atual.item.getNome()).equals(aRemover.getNome())) {
				removerNoEncontrado(atual);
			}
		}
	}

	private void removerNoEncontrado(NodoJogador aRemover) {
		NodoJogador r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		NodoJogador p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}

	private NodoJogador rotacaoEsquerda(NodoJogador inicial) {

		NodoJogador direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);

			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	private NodoJogador rotacaoDireita(NodoJogador inicial) {

		NodoJogador esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);

			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	private NodoJogador duplaRotacaoEsquerdaDireita(NodoJogador inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	private NodoJogador duplaRotacaoDireitaEsquerda(NodoJogador inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	private NodoJogador sucessor(NodoJogador q) {
		if (q.getDireita() != null) {
			NodoJogador r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			NodoJogador p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	private int altura(NodoJogador atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;

		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());

		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());

		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(NodoJogador no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	/* Usei esses imprimir para testes */
//	public void imprimirEmOrdem() {
//		imprimirEmOrdem(raiz);
//	}
//
//	private void imprimirEmOrdem(NodoJogador raizArvore) { // Caso queira imprirmir decrescente só trocar o .esquerda
//
//		if (raizArvore != null) { // Verificar nao o no de agora nao esta nulo
//			imprimirEmOrdem(raizArvore.esquerda);
//			System.out.print(raizArvore.item.getNome() + " | ");
//			imprimirEmOrdem(raizArvore.direita);
//		}
//
//	}
//
//	public void imprimirEmPreOrdem() {
//		imprimirEmPreOrdem(raiz);
//	}
//
//	void imprimirEmPreOrdem(NodoJogador node) {
//		if (node != null) {
//			System.out.print(node.item.getNome() + " ");
//			imprimirEmPreOrdem(node.esquerda);
//			imprimirEmPreOrdem(node.direita);
//		}
//	}

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

}

class NodoJogador {

	NodoJogador esquerda;
	NodoJogador direita;
	NodoJogador pai;
	Jogador item;
	int balanceamento;

	public NodoJogador(Jogador item) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setChave(item);
	}

	public Jogador getChave() {
		return item;
	}

	public void setChave(Jogador item) {
		this.item = item;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public NodoJogador getPai() {
		return pai;
	}

	public NodoJogador setPai(NodoJogador pai) {
		this.pai = pai;
		return pai;
	}

	public NodoJogador getDireita() {
		return direita;
	}

	public NodoJogador setDireita(NodoJogador direita) {
		this.direita = direita;
		return direita;
	}

	public NodoJogador getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(NodoJogador esquerda) {
		this.esquerda = esquerda;
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
