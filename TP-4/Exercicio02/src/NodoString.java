public class NodoString {

	String chaveNome;
	Jogador item; // contem os dados do jogador armazenado no nodo da arvore.
	NodoString direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoString esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.

	public NodoString(Jogador registro) {
		this.chaveNome = new String(registro.getNome());
		this.item = registro;
		this.direita = this.esquerda = null;
	}

}
