public class NodoJogador {

	NodoJogador esquerda;
	NodoJogador direita;
	NodoJogador pai;
	Jogador item;
	int balanceamento;

	public NodoJogador(Jogador item) {
		setEsquerda(setDireita(setPai(null)));
		setBalanceamento(0);
		setChave(item);
	}

//	public String toString() {
//		return Integer.toString(getChave());
//	}

	public Jogador getChave() {
		return item;
	}

	public void setChave(Jogador item) {
		this.item = item;
	}

	public int getBalanceamento() {
		return balanceamento;
	}

	public void setBalanceamento(int balanceamento) {
		this.balanceamento = balanceamento;
	}

	public NodoJogador getPai() {
		return pai;
	}

	public NodoJogador setPai(NodoJogador pai) {
		this.pai = pai;
		return pai;
	}

	public NodoJogador getDireita() {
		return direita;
	}

	public NodoJogador setDireita(NodoJogador direita) {
		this.direita = direita;
		return direita;
	}

	public NodoJogador getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(NodoJogador esquerda) {
		this.esquerda = esquerda;
	}
}