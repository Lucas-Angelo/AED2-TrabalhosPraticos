public class ABBavl {

	private NodoJogador raiz;
	private int comparacoes;

	public ABBavl() {
		raiz = null;
		this.comparacoes = 0;
	}

	public boolean arvoreVazia() {

		boolean resp;

		if (raiz == null)
			resp = true;
		else
			resp = false;

		return resp;
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
		this.comparacoes++;

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

	private NodoJogador adicionar(NodoJogador raizArvore, Jogador jogadorNovo) {
		if (raizArvore == null)
			raizArvore = new NodoJogador(jogadorNovo);
		else {
			if ((raizArvore.item.getNome()).compareTo(jogadorNovo.getNome()) > 0)
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			else {
				if ((raizArvore.item.getNome()).compareTo(jogadorNovo.getNome()) < 0)
					raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
				else
					System.out.println("O jogador " + jogadorNovo.getNome() + ", cuja id e " + jogadorNovo.getId()
							+ ", ja foi inserido anteriormente na arvore.");
			}
		}

		return balancear(raizArvore);
	}

	public void inserir(Jogador jogadorNovo) {
		this.raiz = adicionar(this.raiz, jogadorNovo);
	}

	private NodoJogador balancear(NodoJogador raizArvore) {
		int fatorBalanceamento;
		int fatorBalanceamentoFilho;

		fatorBalanceamento = raizArvore.getFatorBalanceamento();

		if (fatorBalanceamento >= 2) { // Desbalanceado a esquerda

			fatorBalanceamentoFilho = raizArvore.esquerda.getFatorBalanceamento();

			// Caso que necessita de rotacao simples para a direita, filho esta
			// desbalanceado para o mesmo lado que o pai, dai atualiza a raiz da subarvore
			if ((fatorBalanceamentoFilho) == 0 || (fatorBalanceamentoFilho == 1))
				raizArvore = rotacionarDireita(raizArvore);
			// Caso em que o filho esta desbalanceado para direita e o pai para esquerda,
			// dai precisa de rotacao dupla
			else if (fatorBalanceamentoFilho == -1) {
				raizArvore.esquerda = rotacionarEsquerda(raizArvore.esquerda); // Primeiro rotacionar o filho a esquerda
				raizArvore = rotacionarDireita(raizArvore); // Agora rotaciona o pai
			}
		} else if (fatorBalanceamento <= -2) { // Desbalanceado para a direita

			fatorBalanceamentoFilho = raizArvore.direita.getFatorBalanceamento();

			// Rotacao simples a esquerda
			if ((fatorBalanceamentoFilho == -1) || (fatorBalanceamentoFilho == 0)) {
				raizArvore = rotacionarEsquerda(raizArvore);
			}
			// Dai nesse caso precisa de rotacao dupla
			else if (fatorBalanceamentoFilho == 1) {
				raizArvore.direita = rotacionarDireita(raizArvore.direita); // Rotaciona filho
				raizArvore = rotacionarEsquerda(raizArvore); // Rotaciona o pai
			}

		} else
			raizArvore.setAltura();

		return raizArvore;
	}

	private NodoJogador rotacionarEsquerda(NodoJogador p) {

		NodoJogador z;
		NodoJogador filhoDireitaEsquerda; // Triangulo vermelho, o auxiliar

		z = p.direita;
		filhoDireitaEsquerda = z.esquerda;

		// p torna-se o filho a esquerda de z
		z.esquerda = p;

		// p antigo filho a esquerda de z torna-se o novo filho a direita de p
		p.direita = filhoDireitaEsquerda;

		// Quando faz essas rotacoes as alturas podem ter sido alteradas

		p.setAltura();
		z.setAltura();

		return z;
	}

	private NodoJogador rotacionarDireita(NodoJogador p) {

		NodoJogador u;
		NodoJogador filhoEsquerdaDireita; // Triangulo vermelho, usado com auxiliar para nao perder

		u = p.esquerda;
		filhoEsquerdaDireita = u.direita;

		// p torna-se o filho a direita de u
		u.direita = p;

		// o antigo filho a direita de u torna-se o novo filho a esquerda de p
		p.esquerda = filhoEsquerdaDireita;

		p.setAltura();
		u.setAltura();

		return u;
	}

	public void imprimirEmOrdem() {
		imprimirEmOrdem(raiz);
	}

	private void imprimirEmOrdem(NodoJogador raizArvore) { // Caso queira imprirmir decrescente só trocar o .esquerda

		if (raizArvore != null) { // Verificar nao o no de agora nao esta nulo
			imprimirEmOrdem(raizArvore.esquerda);
			System.out.println(raizArvore.item.getNome());
			imprimirEmOrdem(raizArvore.direita);
		}

	}

	public int getComparacoes() {
		return this.comparacoes;
	}
}