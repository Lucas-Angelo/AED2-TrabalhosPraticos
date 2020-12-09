//Arvore Ternaria de Busca
public class ATB {

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
