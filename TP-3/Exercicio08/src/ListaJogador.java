public class ListaJogador {

	private Celula primeiro;
	private Celula ultimo;
	private int tamanho;

	public ListaJogador() {
		Celula sentinela = new Celula();
		primeiro = ultimo = sentinela;
		tamanho = 0;
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

	public void concaternar(ListaJogador lista2) {

		ListaJogador nova = new ListaJogador();
		Celula auxNova;

		nova.primeiro = primeiro;
		nova.ultimo = ultimo;
		nova.ultimo.proximo = lista2.primeiro.proximo;
		nova.ultimo = lista2.ultimo;

		int cont = 0;
		auxNova = nova.primeiro.proximo;
		while (auxNova != null) {

			cont++;

			auxNova = auxNova.proximo;
		}

		nova.tamanho = cont;

		primeiro = nova.primeiro;
		tamanho = cont;

	}

}
