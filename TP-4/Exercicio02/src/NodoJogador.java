public class NodoJogador {

	Jogador item; // contem os dados do jogador armazenado no nodo da arvore.
	NodoJogador direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoJogador esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.

	public NodoJogador(Jogador registro) {
		item = registro;
		direita = null;
		esquerda = null;
	}

}
