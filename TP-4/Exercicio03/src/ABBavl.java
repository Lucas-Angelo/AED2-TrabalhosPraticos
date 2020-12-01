public class ABBavl {

	private NodoJogador raiz;

	public void inserir(Jogador novo) {
		NodoJogador novoJogador = new NodoJogador(novo);
		adicionar(this.raiz, novoJogador);
	}

	private void adicionar(NodoJogador aComparar, NodoJogador aInserir) {

		if (aComparar == null) { // Se achou o nodo vazio correto
			this.raiz = aInserir; // Insere

		} else { // Caso contrario verificar a esquerda e direita pra saber qual lado ir

			if ((aInserir.item.getNome()).compareTo(aComparar.item.getNome()) < 0) {

				if (aComparar.getEsquerda() == null) {
					aComparar.setEsquerda(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					adicionar(aComparar.getEsquerda(), aInserir);
				}

			} else if ((aInserir.item.getNome()).compareTo(aComparar.item.getNome()) > 0) {

				if (aComparar.getDireita() == null) {
					aComparar.setDireita(aInserir);
					aInserir.setPai(aComparar);
					verificarBalanceamento(aComparar);

				} else {
					adicionar(aComparar.getDireita(), aInserir);
				}

			} else {
				System.out.println("O jogador " + aInserir.item.getNome() + ", cuja id e " + aInserir.item.getId()
						+ ", ja foi inserido anteriormente na arvore.");
			}
		}
	}

	private void verificarBalanceamento(NodoJogador atual) {
		setBalanceamento(atual);
		int balanceamento = atual.getBalanceamento();

		if (balanceamento == -2) {

			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);

			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}

		} else if (balanceamento == 2) {

			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);

			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}

		if (atual.getPai() != null) {
			verificarBalanceamento(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(Jogador aRemover) {
		removerAVL(this.raiz, aRemover);
	}

	private void removerAVL(NodoJogador atual, Jogador aRemover) {
		if (atual == null) {
			return;

		} else {

			if ((atual.item.getNome()).compareTo(aRemover.getNome()) > 0) {
				removerAVL(atual.getEsquerda(), aRemover);

			} else if ((atual.item.getNome()).compareTo(aRemover.getNome()) < 0) {
				removerAVL(atual.getDireita(), aRemover);

			} else if ((atual.item.getNome()).equals(aRemover.getNome())) {
				removerNoEncontrado(atual);
			}
		}
	}

	private void removerNoEncontrado(NodoJogador aRemover) {
		NodoJogador r;

		if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

			if (aRemover.getPai() == null) {
				this.raiz = null;
				aRemover = null;
				return;
			}
			r = aRemover;

		} else {
			r = sucessor(aRemover);
			aRemover.setChave(r.getChave());
		}

		NodoJogador p;
		if (r.getEsquerda() != null) {
			p = r.getEsquerda();
		} else {
			p = r.getDireita();
		}

		if (p != null) {
			p.setPai(r.getPai());
		}

		if (r.getPai() == null) {
			this.raiz = p;
		} else {
			if (r == r.getPai().getEsquerda()) {
				r.getPai().setEsquerda(p);
			} else {
				r.getPai().setDireita(p);
			}
			verificarBalanceamento(r.getPai());
		}
		r = null;
	}

	private NodoJogador rotacaoEsquerda(NodoJogador inicial) {

		NodoJogador direita = inicial.getDireita();
		direita.setPai(inicial.getPai());

		inicial.setDireita(direita.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}

		direita.setEsquerda(inicial);
		inicial.setPai(direita);

		if (direita.getPai() != null) {

			if (direita.getPai().getDireita() == inicial) {
				direita.getPai().setDireita(direita);

			} else if (direita.getPai().getEsquerda() == inicial) {
				direita.getPai().setEsquerda(direita);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(direita);

		return direita;
	}

	private NodoJogador rotacaoDireita(NodoJogador inicial) {

		NodoJogador esquerda = inicial.getEsquerda();
		esquerda.setPai(inicial.getPai());

		inicial.setEsquerda(esquerda.getDireita());

		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}

		esquerda.setDireita(inicial);
		inicial.setPai(esquerda);

		if (esquerda.getPai() != null) {

			if (esquerda.getPai().getDireita() == inicial) {
				esquerda.getPai().setDireita(esquerda);

			} else if (esquerda.getPai().getEsquerda() == inicial) {
				esquerda.getPai().setEsquerda(esquerda);
			}
		}

		setBalanceamento(inicial);
		setBalanceamento(esquerda);

		return esquerda;
	}

	private NodoJogador duplaRotacaoEsquerdaDireita(NodoJogador inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	private NodoJogador duplaRotacaoDireitaEsquerda(NodoJogador inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	private NodoJogador sucessor(NodoJogador q) {
		if (q.getDireita() != null) {
			NodoJogador r = q.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;
		} else {
			NodoJogador p = q.getPai();
			while (p != null && q == p.getDireita()) {
				q = p;
				p = q.getPai();
			}
			return p;
		}
	}

	private int altura(NodoJogador atual) {
		if (atual == null) {
			return -1;
		}

		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;

		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());

		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());

		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(NodoJogador no) {
		no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	/* Usei esses imprimir para testes */
//	public void imprimirEmOrdem() {
//		imprimirEmOrdem(raiz);
//	}
//
//	private void imprimirEmOrdem(NodoJogador raizArvore) { // Caso queira imprirmir decrescente só trocar o .esquerda
//
//		if (raizArvore != null) { // Verificar nao o no de agora nao esta nulo
//			imprimirEmOrdem(raizArvore.esquerda);
//			System.out.print(raizArvore.item.getNome() + " | ");
//			imprimirEmOrdem(raizArvore.direita);
//		}
//
//	}
//
//	public void imprimirEmPreOrdem() {
//		imprimirEmPreOrdem(raiz);
//	}
//
//	void imprimirEmPreOrdem(NodoJogador node) {
//		if (node != null) {
//			System.out.print(node.item.getNome() + " ");
//			imprimirEmPreOrdem(node.esquerda);
//			imprimirEmPreOrdem(node.direita);
//		}
//	}

	public Jogador buscar(String nomePesquisado) {
		Jogador pesquisado;

		NodoJogador resultado = pesquisar(raiz, nomePesquisado);

		if (resultado == null)
			pesquisado = null;
		else
			pesquisado = resultado.item;

		return pesquisado;
	}

	private NodoJogador pesquisar(NodoJogador raizArvore, String nomePesquisado) {

		NodoJogador pesquisado;

		if (raizArvore == null)
			pesquisado = null;
		else {
			if (raizArvore.item.getNome().equals(nomePesquisado)) {
				System.out.print(raizArvore.item.getNome() + " ");
				pesquisado = raizArvore;
			} else if ((raizArvore.item.getNome()).compareTo(nomePesquisado) > 0) {
				System.out.print(raizArvore.item.getNome() + " ");
				pesquisado = pesquisar(raizArvore.esquerda, nomePesquisado);
			} else {
				System.out.print(raizArvore.item.getNome() + " ");
				pesquisado = pesquisar(raizArvore.direita, nomePesquisado);
			}
		}

		return pesquisado;
	}

}