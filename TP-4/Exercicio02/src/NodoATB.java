public class NodoATB {

	Jogador item; // contem os dados do jogador armazenado no nodo da arvore.
	int chave; // altura do jogador
	NodoATB direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoATB esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.
	ABB meio;

	public NodoATB(Jogador registro) {
		item = registro;
		chave = registro.getAltura() % 17;
		direita = null;
		esquerda = null;
		meio = null;
	}
}