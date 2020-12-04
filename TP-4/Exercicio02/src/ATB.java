//Arvore Ternaria de Busca
public class ATB {

	private NodoATB raiz;

	public ATB() {
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
			else{
				if (raizArvore.meio == null){
					raizArvore.meio = new ABB();
				}
				raizArvore.meio.inserir(jogadorNovo);
			}
		}
		return raizArvore;
	}

	public void inserir(Jogador jogadorNovo) {
		this.raiz = adicionar(this.raiz, jogadorNovo);
	}

	public Jogador buscar(Jogador buscado) {
		Jogador pesquisado;

		NodoATB resultado = pesquisar(raiz, buscado);

		if (resultado == null)
			pesquisado = null;
		else
			pesquisado = resultado.item;

		return pesquisado;
	}

	private NodoATB pesquisar(NodoATB raizArvore, Jogador buscado) {

		NodoATB pesquisado = null;
		int alturaPesquisada = buscado.getAltura() % 17;
		String nomePesquisado = buscado.getNome();

		if (raizArvore == null)
			pesquisado = null;
		else {
			if (raizArvore.chave == alturaPesquisada) {
				System.out.print(raizArvore.chave + " ");
				System.out.print(raizArvore.item.getNome() + " ");
				if ( nomePesquisado.equals(raizArvore.item.getNome()) )
					pesquisado = new NodoATB(buscado);
				else if ( raizArvore.meio == null )
					pesquisado = null;
				else{
					Jogador resBusca = raizArvore.meio.buscar(nomePesquisado);
					if (resBusca == null)
						pesquisado = null;
					else
						pesquisado = new NodoATB(resBusca);

				}
				
			
			} 
			else if ( alturaPesquisada < raizArvore.chave ) {
				System.out.print(raizArvore.chave + " ");
				pesquisado = pesquisar(raizArvore.esquerda, buscado);
			} 
			else {
				System.out.print(raizArvore.chave + " ");
				pesquisado = pesquisar(raizArvore.direita, buscado);
			}
		}

		return pesquisado;
	}

}
