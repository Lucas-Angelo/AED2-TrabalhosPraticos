public class ListaJogador {

	private Celula primeiro;
	private Celula ultimo;
	private int tamanho;
	private int comparacoes;
	private int movimentacoes;

	public ListaJogador() {
		Celula sentinela = new Celula();
		primeiro = ultimo = sentinela;
		tamanho = comparacoes = movimentacoes = 0;
	}

	public void inserirFinal(Jogador novo) throws Exception {

		Celula nova = new Celula(novo); // Criar nova celula com dados do jogador
		ultimo.proximo = nova; // Colocar essa nova com o final da lista
		nova.anterior = ultimo; // O anterior da nova apontar pra lista
		ultimo = nova; // Mover o ultimo pra novo adicionada
		tamanho++;

	}

	public Jogador remover(int posicao) throws Exception {

		Jogador retirado = null;

		if (!listaVazia()) {

			if (posicao < tamanho) {

				retirado = ultimo.item; // Pegar dados do ultimo jogador
				ultimo = ultimo.anterior; // Colocar o ultimo uma celula atras
				ultimo.proximo.anterior = null; // Zerar o dado anterior da celula retirada
				ultimo.proximo = null; // Colocar o ultimo atualizado apontando pra nulo, nao mais pra celula retirada

			} else {
				new Exception("Erro ao tentar remover da lista: posição inválida!");
			}

		} else {
			new Exception("Erro ao tentar remover da lista: lista vazia!");
		}

		return retirado;

	}

	public boolean listaVazia() {
		boolean res;

		if (primeiro == ultimo) { // ou: if(tamanho==0)
			res = true;
		} else {
			res = false;
		}

		return res;
	}

	public void imprimir() {
		Celula aux;

		aux = primeiro.proximo;
		while (aux != null) {

			System.out.printf("[%d ## ", aux.item.getId());

			System.out.printf("%s ## ", aux.item.getNome());

			System.out.printf("%d ## ", aux.item.getAltura());

			System.out.printf("%d ## ", aux.item.getPeso());

			System.out.printf("%d ## ", aux.item.getAnoNascimento());

			if (aux.item.getUniversidade().trim().length() == 0) { // Se o dado esta vazio
				System.out.printf("nao informado ## ");
			} else {
				System.out.printf("%s ## ", aux.item.getUniversidade());
			}

			if (aux.item.getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
				System.out.printf("nao informado ## ");
			} else {
				System.out.printf("%s ## ", aux.item.getCidadeNascimento());
			}

			if (aux.item.getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
				System.out.printf("nao informado]\n");
			} else {
				System.out.printf("%s]\n", aux.item.getEstadoNascimento());
			}

			aux = aux.proximo;
		}
	}

	public int obterNumJogadores() {
		return this.tamanho;
	}

	public ListaJogador copiar() {
		ListaJogador copia = new ListaJogador();

		copia.primeiro = primeiro;
		copia.ultimo = ultimo;

		return copia;
	}

	public int getMovimentacoes() {
		return movimentacoes;
	}

	public int getComparacoes() {
		return comparacoes;
	}

	private Celula partition(Celula esquerda, Celula direita) {
		// Capturar o pivo, sendo o ultimo da esquerda para direita
		Jogador playerPivot = direita.item;

		Celula i = esquerda.anterior;

		// Comeca na esquerda e vai andando a parte da lista ate chegar na direita
		for (Celula j = esquerda; j != direita; j = j.proximo) {

			// Todas as condicoes de cidade e nome
			if ((!(j.item.getEstadoNascimento().trim().length() == 0)
					&& (playerPivot.getEstadoNascimento().trim().length() == 0))
					|| (!(j.item.getEstadoNascimento().trim().length() == 0)
							&& (j.item.getEstadoNascimento().compareTo(playerPivot.getEstadoNascimento()) < 0))
					|| ((j.item.getEstadoNascimento().compareTo(playerPivot.getEstadoNascimento()) == 0)
							&& (j.item.getNome().compareTo(playerPivot.getNome()) < 0))) {
				// Para ir pra frente
				i = (i == null) ? esquerda : i.proximo; // Se o i for null pega a esquerda, caso nao pega o proximo dele
				Jogador temp = i.item; // Altera
				i.item = j.item;
				j.item = temp;
				movimentacoes++;
				comparacoes++;
			}

		}
		i = (i == null) ? esquerda : i.proximo; // Se o i for null pega a esquerda, caso nao pega o proximo dele
		Jogador temp = i.item;
		i.item = direita.item;
		direita.item = temp;
		movimentacoes++;
		return i;
	}

	/* A implementacao recursiva do quicksort na lista duplamente encadeada */
	private void _quickSort(Celula esquerda, Celula direita) {
		if (direita != null && esquerda != direita && esquerda != direita.proximo) {
			Celula temp = partition(esquerda, direita);
			_quickSort(esquerda, temp.anterior);
			_quickSort(temp.proximo, direita);
		}
	}

	// A funcao que sera chamada na main
	public void quickSort() {
		// Chamar a funcao recursiva passando o inicio da lista e o fim (Depois vira
		// esquerda e direita)
		_quickSort(primeiro.proximo, ultimo); // Tem que comecar do proximo do primeiro, pois o primeiro e a sentinela
	}

}
