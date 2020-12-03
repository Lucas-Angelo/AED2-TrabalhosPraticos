public class TabelaHash {

	private Lista[] tabela;
	private int M; // Bom ser numero primo e ser grande o suficiente pra caber tudo

	public TabelaHash(int tamanho) {
		this.M = tamanho; // Se quiser configurar pra ser numero primo
		this.tabela = new Lista[tamanho];
		for (int i = 0; i < tamanho; i++) {
			this.tabela[i] = new Lista();
		}
	}

	// h(x) = x % M
	private int funcaoHash(int x) {
		return (x % 37);
	}

	public int inserir(Jogador elemento) throws Exception {

		// Encontrar posicao da tabela por meio da funcao hash
		int posicao = funcaoHash(elemento.getAltura());

		// Verificar se esse elemento ja esta na lista
		if (tabela[posicao].localizar(elemento.getNome()) == null)
			// Elemento nao inserido ainda
			tabela[posicao].inserir(elemento, 0); // Nao sabe quantos elementos lista vai ter, sempre inserir no inicio
		else
			// Elemento ja inserido
			posicao = -1;

		return posicao;

	}

	public Jogador pesquisar(Jogador procurado) {
		int posicao = funcaoHash(procurado.getAltura());

		Jogador encontrado = tabela[posicao].localizar(procurado.getNome());
		if (encontrado != null)
			System.out.print(posicao + " ");

		return encontrado; // Se for null, nao tem na tabela
	}

	public Jogador remover(int chave) throws Exception {
		int posicao = funcaoHash(chave);
		return tabela[posicao].remover(chave); // Retorna o retirado
	}

	public void imprimir() {
		for (int i = 0; i < this.M; i++)
			tabela[i].mostrar(); // Imprimir toda a tabela
	}

}
