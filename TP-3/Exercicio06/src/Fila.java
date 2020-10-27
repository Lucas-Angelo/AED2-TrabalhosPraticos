public class Fila {

	private Celula frente;
	private Celula tras;
	int filaTamanho;

	public Fila() {
		Celula sentinela;

		sentinela = new Celula();
		frente = sentinela;
		tras = sentinela;
		filaTamanho = 0;
	}

	public boolean filaVazia() {

		boolean resp;

		if (frente == tras)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public boolean filaCheia() {
		boolean resp;

		if (filaTamanho == 5)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void enfileirar(Jogador novo) {

		Celula novaCelula;

		if (!filaCheia()) {

			novaCelula = new Celula(novo);
			tras.proximo = novaCelula;
			tras = novaCelula; // ou: tras = tras.proximo;
			filaTamanho++;

		} else {

			desenfileirar();

			novaCelula = new Celula(novo);
			tras.proximo = novaCelula;
			tras = novaCelula; // ou: tras = tras.proximo;
			filaTamanho++;

		}

	}

	public Jogador desenfileirar() {

		Celula aux;

		aux = frente.proximo; // Na frente da sentinela
		frente.proximo = aux.proximo; // Sentinela pula uma celula no apontamento
		aux.proximo = null; // Anula celula desenfileirada

		if (aux == tras)
			tras = frente;

		filaTamanho--;

		return (aux.item);
	}

	public void mostrar() {

		int cont = 0;
		if (!filaVazia()) {
			Celula aux;

			aux = frente.proximo;
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

	public double obterMediaAltura() {
		double media;

		int cont = 0;
		double somatorio = 0.0;
		if (!filaVazia()) {
			Celula aux;

			aux = frente.proximo;
			while (aux != null) {

				somatorio += aux.item.getAltura();

				cont++;
				aux = aux.proximo;
			}
		}
		media = somatorio / cont;

		return Math.round(media);
	}

}
