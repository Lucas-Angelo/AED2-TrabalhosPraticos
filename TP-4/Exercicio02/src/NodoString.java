public class NodoString {

	String chaveNome;
	Jogador item;
	NodoString direita;
	NodoString esquerda;

	public NodoString(Jogador item) {
		this.chaveNome = item.getNome();
		this.item = item;
		this.direita = this.esquerda = null;
	}

}
