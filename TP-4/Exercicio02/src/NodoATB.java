public class NodoATB {

	int chave; // altura do jogador
	NodoATB direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoATB esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.
	ABB meio;

	public NodoATB(Jogador registro) {
		chave = registro.getAltura() % 17;
		direita = null;
		esquerda = null;
		meio = new ABB(registro);
	}
}