public class ABB {

	private NodoABB raiz;

	public ABB() {
		raiz = null;
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

}
