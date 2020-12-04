public class HashEmAberto {

	private int M;
	private Jogador[] tabela;
	private int comparacoes;

	public HashEmAberto(int tamanho) {
		this.comparacoes = 0;
		this.M = tamanho;
		this.tabela = new Jogador[tamanho];

		for (int i = 0; i < tamanho; i++)
			this.tabela[i] = null;
	}

	public int funcaoHash(int x, int k) {
		return ((x % M) + k * (x % 23)) % M;
	}

	public Jogador pesquisar(Jogador informado) {
		int k = 0;
		int posicao;
		Jogador pesquisado = null;
		while (k < this.M) {
			this.comparacoes++;

			posicao = funcaoHash(informado.getAltura(), k);

			if (tabela[posicao] == null) {
				pesquisado = null;
				k = M;
			} else if (tabela[posicao].getNome().equals(informado.getNome())) {
				System.out.print(posicao + " ");
				pesquisado = tabela[posicao];
				k = M;
			}

			k++;
		}

		return pesquisado;

	}

	public void inserir(Jogador novo) {
		int k = 0;
		int posicao;

		while (k < this.M) {
			posicao = funcaoHash(novo.getAltura(), k);

			if (tabela[posicao] == null) {
				tabela[posicao] = novo;
				k = M;
			} else if (tabela[posicao].getNome().equals(novo.getNome())) {
				System.out.println("Chave ja inserida");
				k = M;
			}

			k++;
		}

	}

	public int getComparacoes() {
		return this.comparacoes;
	}

}
