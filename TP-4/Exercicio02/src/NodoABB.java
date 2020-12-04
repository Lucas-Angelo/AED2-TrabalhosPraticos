public class NodoABB  {

	Jogador item; // contem os dados do jogador armazenado no nodo da arvore.
	String chave; //altura do jogador
	NodoABB direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoABB esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.

	public NodoABB(Jogador registro){
		item = registro;
		chave = registro.getNome();
		direita = null;
		esquerda = null;
	}
}