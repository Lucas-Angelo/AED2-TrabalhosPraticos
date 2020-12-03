public class ABBum {

	private NodoInt raiz;

	public ABBum() {
		raiz = null;
	}

	public Boolean arvoreVazia() {
		if (this.raiz == null)
			return true;
		else
			return false;
	}

	private NodoInt adicionar(NodoInt raizArvore, Jogador jogadorNovo) {
		if (raizArvore == null)
			raizArvore = new NodoInt(jogadorNovo);
		else {
			if ((raizArvore.chaveAltura) > (jogadorNovo.getAltura() % 17))
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			else {
				if ((raizArvore.chaveAltura) < (jogadorNovo.getAltura() % 17))
					raizArvore.direita = adicionar(raizArvore.direita, jogadorNovo);
				else
					System.out.println("O jogador " + jogadorNovo.getAltura() % 17 + ", cuja id e "
							+ jogadorNovo.getId() + ", ja foi inserido anteriormente na arvore.");
			}
		}
		return raizArvore;
	}

	public void inserir(Jogador jogadorNovo) {
		this.raiz = adicionar(this.raiz, jogadorNovo);
	}

	public Jogador buscar(String nomePesquisado) {
		Jogador pesquisado;

		NodoInt resultado = pesquisar(raiz, nomePesquisado);

		if (resultado == null)
			pesquisado = null;
		else
			pesquisado = resultado.item;

		return pesquisado;
	}

	private NodoInt pesquisar(NodoInt raizArvore, String nomePesquisado) {

		NodoInt pesquisado;

		if (raizArvore == null)
			pesquisado = null;
		else {
			if (raizArvore.chaveAltura.equals(nomePesquisado)) {
				System.out.print(raizArvore.chaveAltura + " ");
				pesquisado = raizArvore;
			} else if ((raizArvore.chaveAltura).compareTo(nomePesquisado) > 0) {
				System.out.print(raizArvore.chaveAltura + " ");
				pesquisado = pesquisar(raizArvore.esquerda, nomePesquisado);
			} else {
				System.out.print(raizArvore.chaveAltura + " ");
				pesquisado = pesquisar(raizArvore.direita, nomePesquisado);
			}
		}

		return pesquisado;
	}

}
