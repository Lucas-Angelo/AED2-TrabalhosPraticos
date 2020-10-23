public class Lista {

	private Jogador[] listaJogador;

	private int primeiro;
	private int ultimo;

	private int tamanho;

	public Lista() {
		listaJogador = new Jogador[3922];

		primeiro = ultimo = 0;
	}

	public Lista(int tamanho) {
		listaJogador = new Jogador[tamanho];

		this.tamanho = tamanho;
		primeiro = ultimo = 0;
	}

	public boolean listaCheia() {
		boolean resp;

		if (ultimo == tamanho)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public boolean listaVazia() {
		boolean resp;

		if (primeiro == ultimo)
			resp = true;
		else
			resp = false;

		return resp;
	}

	public void inserir(Jogador jogador, int posicao) throws Exception {

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaCheia()) {

				for (int j = ultimo; j > posicao; j--) {
					listaJogador[j] = listaJogador[j - 1]; // Chegando todos uma posição para frente
				}

				listaJogador[posicao] = jogador;
				ultimo++;

			} else {
				new Exception("Erro ao tentar inserir na lista: a lista está cheia");
			}

		} else {
			new Exception("Erro ao tentar inserir na lista: posição inválida! Lista ficaria com buracos");
		}

	}

	public void inserirInicio(Jogador jogador) throws Exception {

		int posicao = 0;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaCheia()) {

				for (int j = ultimo; j > posicao; j--) {
					listaJogador[j] = listaJogador[j - 1]; // Chegando todos uma posição para frente
				}

				listaJogador[posicao] = jogador;
				ultimo++;

			} else {
				new Exception("Erro ao tentar inserir na lista: a lista está cheia");
			}

		} else {
			new Exception("Erro ao tentar inserir na lista: posição inválida! Lista ficaria com buracos");
		}

	}

	public void inserirFim(Jogador jogador) throws Exception {

		int posicao = ultimo;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaCheia()) {

				listaJogador[posicao] = jogador;
				ultimo++;

			} else {
				new Exception("Erro ao tentar inserir na lista: a lista está cheia");
			}

		} else {
			new Exception("Erro ao tentar inserir na lista: posição inválida! Lista ficaria com buracos");
		}

	}

	public Jogador remover(int posicao) throws Exception {

		Jogador retirado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				retirado = listaJogador[posicao].clone();
				ultimo--;
				for (int j = posicao; j < ultimo; j++) {
					listaJogador[j] = listaJogador[j + 1];
				}

			} else {
				new Exception("Erro ao tentar remover na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar remover na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return retirado;

	}

	public Jogador removerInicio() throws Exception {

		int posicao = primeiro;
		Jogador retirado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				retirado = listaJogador[posicao].clone();
				ultimo--;
				for (int j = posicao; j < ultimo; j++) {
					listaJogador[j] = listaJogador[j + 1];
				}

			} else {
				new Exception("Erro ao tentar remover na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar remover na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return retirado;

	}

	public Jogador removerFim() throws Exception {

		int posicao = ultimo - 1;
		Jogador retirado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				retirado = listaJogador[posicao].clone();
				ultimo--;
				for (int j = posicao; j < ultimo; j++) {
					listaJogador[j] = listaJogador[j + 1];
				}

			} else {
				new Exception("Erro ao tentar remover na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar remover na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return retirado;

	}

	public Jogador getJogador(int posicao) {
		Jogador encontrado = null;

		if ((posicao >= primeiro) && (posicao <= ultimo)) {

			if (!listaVazia()) {

				encontrado = listaJogador[posicao];

			} else {
				new Exception("Erro ao tentar encontrar na lista: a lista está vazia");
			}

		} else {
			new Exception("Erro ao tentar encontrar na lista: posição inválida! Lista não possui valor nessa posição");
		}

		return encontrado;
	}

	public void mostrar() {

		if (!listaVazia()) {

			for (int aux = primeiro; aux < ultimo; aux++) {
				System.out.print("[" + aux + "] ## ");

				System.out.printf("%d ## ", listaJogador[aux].getId());

				System.out.printf("%s ## ", listaJogador[aux].getNome());

				System.out.printf("%d ## ", listaJogador[aux].getAltura());

				System.out.printf("%d ## ", listaJogador[aux].getPeso());

				System.out.printf("%d ## ", listaJogador[aux].getAnoNascimento());

				if (listaJogador[aux].getUniversidade().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## ");
				} else {
					System.out.printf("%s ## ", listaJogador[aux].getUniversidade());
				}

				if (listaJogador[aux].getCidadeNascimento().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## ");
				} else {
					System.out.printf("%s ## ", listaJogador[aux].getCidadeNascimento());
				}

				if (listaJogador[aux].getEstadoNascimento().trim().length() == 0) { // Se o dado esta vazio
					System.out.printf("nao informado ## \n");
				} else {
					System.out.printf("%s ## \n", listaJogador[aux].getEstadoNascimento());
				}

			}

		}

	}

}
