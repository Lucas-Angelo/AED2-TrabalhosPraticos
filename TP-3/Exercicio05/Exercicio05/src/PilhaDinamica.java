public class PilhaDinamica {

	private Celula fundo; // Tipo c�lula e guardam o endere�o de mem�ria onde se encontra uma c�lula,
							// endere�o onde est� o fundo da pilha
	private Celula topo; // Endere�o no topo

	public PilhaDinamica() { // Criar pilha vazia, criar fundo e topo apontando p/ Sentinela

		Celula sentinela = new Celula(); // Criando a nova celula
		fundo = topo = sentinela; // Fundo e topo apontando para sentinela
	}

	public void empilhar(Jogador novo) {

		Celula novaCelula = new Celula(novo); // Criando a nova celula com o item la dentro
		novaCelula.proximo = topo;// Encadeamento com a c�lula anterior
		topo = novaCelula;// Apontando o topo para a nova c�lula
	}

	public Jogador desempilhar() {

		Celula aux; // Criar a celula auxiliar para apontar para onde o topo est�, para poder mexer
					// com o topo
		Jogador player = null;
		if (!pilhaVazia()) {// Verificar se a pilha n�o est� vazia
			aux = topo;
			topo = topo.proximo;// Apontar para a proxima celula
			aux.proximo = null; // Tirando o lixo de memoria
			player = aux.item;
		}
		return player; // Retornando o item
	}

	public boolean pilhaVazia() {// fundo e topo apontando para o mesmo lugar

		boolean resp;
		if (fundo == topo)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void mostrar() {

		int cont = 0;
		if (!pilhaVazia()) {
			Celula aux;

			aux = fundo.proximo;
			while (aux != null) {

				System.out.print("[" + cont + "] ## ");

				System.out.printf("%d ## ", aux.item.getId());

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
					System.out.printf("nao informado ## \n");
				} else {
					System.out.printf("%s ## \n", aux.item.getEstadoNascimento());
				}

				cont++;
				aux = aux.proximo;
			}

		}
	}
}
