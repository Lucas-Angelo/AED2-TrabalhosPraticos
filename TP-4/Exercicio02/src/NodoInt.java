public class NodoInt {

	int chaveAltura;
	NodoInt direita; // referencia ao nodo armazenado, na arvore, a direita do jogador em questao.
	NodoInt esquerda; // referencia ao nodo armazenado, na arvore, a esquerda do jogador em questao.
	NodoString nodoJogador;

	public NodoInt(Jogador registro) {
		this.chaveAltura = registro.getAltura() % 17;
		this.direita = this.esquerda = null;
		this.nodoJogador = new NodoString(registro);
	}

}
