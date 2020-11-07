#include "Jogador.h"

Jogador* newJogadorV(){
  Jogador *player = (Jogador *) malloc(sizeof(Jogador));
  return player;
}

Jogador* newJogador(int id, char* nome, int altura, int peso, char* universidade, int anoNascimento, char* cidadeNascimento, char* estadoNascimento){

  Jogador* player = (Jogador*) malloc(sizeof(Jogador));

  player->id = id;
  strcpy(player->nome, nome);
  player->altura = altura;
  player->peso = peso;
  strcpy(player->universidade, universidade);
  player->anoNascimento = anoNascimento;
  strcpy(player->cidadeNascimento, cidadeNascimento);
  strcpy(player->estadoNascimento, estadoNascimento);

  return player;
}

Jogador* clone( Jogador* toClone) {

		Jogador *jogador = newJogador(toClone->id,toClone->nome,toClone->altura,toClone->peso,toClone->universidade,toClone->anoNascimento,toClone->cidadeNascimento,toClone->estadoNascimento);

		return jogador;
	}

	void imprimir(Jogador* toPrint) {

		printf("## %d ## ", toPrint->id);

		printf("%s ## ", toPrint->nome);

		printf("%d ## ", toPrint->altura);

		printf("%d ## ", toPrint->peso);

		printf("%d ## ", toPrint->anoNascimento);

		if (strlen(toPrint->universidade) == 0) { // Se o dado esta vazio
			printf("nao informado ## ");
		} else {
			printf("%s ## ", toPrint->universidade);
		}

		if (strlen(toPrint->cidadeNascimento) == 0) { // Se o dado esta vazio
			printf("nao informado ## ");
		} else {
			printf("%s ## ", toPrint->cidadeNascimento);
		}

		if (strlen(toPrint->estadoNascimento) == 0) { // Se o dado esta vazio
			printf("nao informado ##\n");
		} else {
			printf("%s ##\n", toPrint->estadoNascimento);
		}

	}