public class ABBdois {

	private NodoString raiz;

	public ABBdois() {
		raiz = null;
	}

	public Boolean arvoreVazia() {
		if (this.raiz == null)
			return true;
		else
			return false;
	}

	private NodoString adicionar(NodoString raizArvore, Jogador jogadorNovo) {
		if (raizArvore == null)
			raizArvore = new NodoString(jogadorNovo);
		else {
			if ((raizArvore.chaveNome).compareTo(jogadorNovo.getNome()) > 0)
				raizArvore.esquerda = adicionar(raizArvore.esquerda, jogadorNovo);
			else {
				if ((raizArvore.chaveNome).compareTo(jogadorNovo.getNome()) < 0)
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

		NodoString resultado = pesquisar(raiz, nomePesquisado);

		if (resultado == null)
			pesquisado = null;
		else
			pesquisado = resultado.item;

		return pesquisado;
	}

	private NodoString pesquisar(NodoString raizArvore, String nomePesquisado) {

		NodoString pesquisado;

		if (raizArvore == null)
			pesquisado = null;
		else {
			if (raizArvore.chaveNome.equals(nomePesquisado)) {
				System.out.print(raizArvore.chaveNome + " ");
				pesquisado = raizArvore;
			} else if ((raizArvore.chaveNome).compareTo(nomePesquisado) > 0) {
				System.out.print(raizArvore.chaveNome + " ");
				pesquisado = pesquisar(raizArvore.esquerda, nomePesquisado);
			} else {
				System.out.print(raizArvore.chaveNome + " ");
				pesquisado = pesquisar(raizArvore.direita, nomePesquisado);
			}
		}

		return pesquisado;
	}

}
