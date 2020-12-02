public class NodoInteiro {

	int chaveAltura;
	NodoInteiro esquerda;
	NodoInteiro direita;
	NodoString nodoJogador;

	public NodoInteiro(Jogador item) {
		this.chaveAltura = item.getAltura() % 17;
		this.esquerda = this.direita = null;
		this.nodoJogador = new NodoString(item);
	}

}
