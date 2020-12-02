public class ABB {

	private NodoInteiro raiz;

	public ABB() {
		raiz = null;
	}

	public Boolean arvoreVazia() {
		if (this.raiz == null)
			return true;
		else
			return false;
	}

	private NodoInteiro adicionar(NodoInteiro raizArvore, Jogador jogadorNovo) {
		if (raizArvore == null) {
			raizArvore = new NodoInteiro(jogadorNovo);
		} else {
			if (raizArvore.chaveAltura > jogadorNovo.getAltura() % 17) {
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			} else {
				if (raizArvore.chaveAltura < jogadorNovo.getAltura() % 17) {
					raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
				} else {
					System.out.println("O jogador " + jogadorNovo.getNome() + ", cuja id e " + jogadorNovo.getId()
							+ ", ja foi inserido anteriormente na arvore.");
				}
			}
		}
		return raizArvore;
	}

	private NodoString adicionarProximo(NodoString raizArvore, Jogador novo) {
		if (raizArvore == null) {
			raizArvore = new NodoString(jogadorNovo);
		} else {
			if (raizArvore.chaveAltura > jogadorNovo.getAltura() % 17) {
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			} else {
				if (raizArvore.chaveAltura < jogadorNovo.getAltura() % 17) {
					raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
				} else {
					System.out.println("O jogador " + jogadorNovo.getNome() + ", cuja id e " + jogadorNovo.getId()
							+ ", ja foi inserido anteriormente na arvore.");
				}
			}
		}
		return raizArvore;
	}

	public void inserir(Jogador jogadorNovo) {
		this.raiz = adicionar(this.raiz, jogadorNovo);
	}

	public int buscar(int numPesquisado) {
		int pesquisado;
		// Jogador pesquisado;

		NodoInteiro resultado = pesquisar(raiz, numPesquisado);

		if (resultado == null)
			pesquisado = -1;
		else
			pesquisado = resultado.chaveAltura;

		return pesquisado;
	}

	private NodoInteiro pesquisar(NodoInteiro raizArvore, int numPesquisado) {

		NodoInteiro pesquisado;

		if (raizArvore == null)
			pesquisado = null;
		else {
			if (raizArvore.chaveAltura == numPesquisado % 17) {
				System.out.print(raizArvore.chaveAltura + " ");
				pesquisado = raizArvore;
			} else if (raizArvore.chaveAltura > numPesquisado % 17) {
				System.out.print(raizArvore.chaveAltura + " ");
				pesquisado = pesquisar(raizArvore.esquerda, numPesquisado);
			} else {
				System.out.print(raizArvore.chaveAltura + " ");
				pesquisado = pesquisar(raizArvore.direita, numPesquisado);
			}
		}

		return pesquisado;
	}

}
