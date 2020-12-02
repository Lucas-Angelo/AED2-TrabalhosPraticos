public class ABB {

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
