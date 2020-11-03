public class FilaCircular {

	private int tamanho;
	private int frente;
	private int tras;
	private Jogador fila[];

	FilaCircular() {
		this.tamanho = 5;
		this.frente = this.tras = -1;
		this.fila = new Jogador[5];
	}

	FilaCircular(int tamanho) {
		this.tamanho = tamanho;
		this.frente = this.tras = -1;
		this.fila = new Jogador[tamanho];
	}

	public boolean filaVazia() {
		boolean res;
		if (frente == -1)
			res = true;
		else
			res = false;

		return res;
	}

	public boolean filaCheia() {
		boolean res;
		if ((frente == 0 && tras == tamanho - 1) || (tras == (frente - 1) % (tamanho - 1)))
			res = true;
		else
			res = false;

		return res;
	}

	public void enfileirar(Jogador novo) {

		if (filaCheia()) {
			desenfileirar(); // Ja que e circular, tem que ficar apagando o primeiro pra colocar o ultimo
		}

		if (filaVazia()) {
			frente = 0;
			tras = 0;
			fila[tras] = novo;
		}

		else if (tras == tamanho - 1 && frente != 0) {
			tras = 0;
			fila[tras] = novo;
		}

		else {
			tras = (tras + 1);

			fila[tras] = novo;
		}
	}

	public Jogador desenfileirar() {
		Jogador retirado;

		retirado = fila[frente];

		if (frente == tras) {
			frente = -1;
			tras = -1;
		}

		else if (frente == tamanho - 1) {
			frente = 0;
		} else {
			frente = frente + 1;
		}

		return retirado;
	}

	public void mostrar() {

		if (!filaVazia()) {

			if (tras >= frente) {

				// Para printar os jogadores do frente para o tras
				for (int i = frente; i <= tras; i++) {
					System.out.printf("[%d] ## ", i - 1);

					System.out.printf("%d ## ", fila[i].getId());

					System.out.printf("%s ## ", fila[i].getNome());

					System.out.printf("%d ## ", fila[i].getAltura());

					System.out.printf("%d ## ", fila[i].getPeso());

					System.out.printf("%d ## ", fila[i].getAnoNascimento());

					if (fila[i].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getUniversidade());
					}

					if (fila[i].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getCidadeNascimento());
					}

					if (fila[i].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ##\n");
					} else {
						System.out.printf("%s ##\n", fila[i].getEstadoNascimento());
					}

				}

				// Ja que e circular a fila, o tras pode ultrapassar a capacidade
				// dai, o tras fica atras do index frente
			} else {

				// Para printar os jogadores do frente ate a capacidade maxima
				for (int i = frente; i < tamanho; i++) {
					System.out.printf("[%d] ## ", i - 1);

					System.out.printf("%d ## ", fila[i].getId());

					System.out.printf("%s ## ", fila[i].getNome());

					System.out.printf("%d ## ", fila[i].getAltura());

					System.out.printf("%d ## ", fila[i].getPeso());

					System.out.printf("%d ## ", fila[i].getAnoNascimento());

					if (fila[i].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getUniversidade());
					}

					if (fila[i].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getCidadeNascimento());
					}

					if (fila[i].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ##\n");
					} else {
						System.out.printf("%s ##\n", fila[i].getEstadoNascimento());
					}
				}

				// E agora caso tenha, acabar de printar os jogadores, do
				// index 0 até o tras, que vai estar atras do frente
				for (int i = 0; i <= tras; i++) {
					System.out.printf("[%d] ## ", i - 1);

					System.out.printf("%d ## ", fila[i].getId());

					System.out.printf("%s ## ", fila[i].getNome());

					System.out.printf("%d ## ", fila[i].getAltura());

					System.out.printf("%d ## ", fila[i].getPeso());

					System.out.printf("%d ## ", fila[i].getAnoNascimento());

					if (fila[i].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getUniversidade());
					}

					if (fila[i].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ## ");
					} else {
						System.out.printf("%s ## ", fila[i].getCidadeNascimento());
					}

					if (fila[i].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
						System.out.printf("nao informado ##\n");
					} else {
						System.out.printf("%s ##\n", fila[i].getEstadoNascimento());
					}
				}

			}

		}

	}

	public double obterMediaAltura() {

		double somatorio = 0;
		int cont = 0;

		if (tras >= frente) {

			for (int i = frente; i <= tras; i++) {
				somatorio += fila[i].getAltura();
				cont++;
			}

		}

		else {
			for (int i = frente; i < tamanho; i++) {
				somatorio += fila[i].getAltura();
				cont++;
			}

			for (int i = 0; i <= tras; i++) {
				somatorio += fila[i].getAltura();
				cont++;
			}

		}

		double media = somatorio / cont;

		return Math.round(media);
	}

}
