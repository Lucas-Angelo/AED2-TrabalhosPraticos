#ifndef JOGADOR_H
#define JOGADOR_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct Jogador{
  int id;
	char nome[75];
	int altura;
	int peso;
	char universidade[75];
	int anoNascimento;
	char cidadeNascimento[75];
	char estadoNascimento[75];
}Jogador;

Jogador* newJogadorV();

Jogador* newJogador(int id, char* nome, int altura, int peso, char* universidade, int anoNascimento, char* cidadeNascimento, char* estadoNascimento);

Jogador* clone( Jogador* toClone);

void imprimir(Jogador* toPrint);


#endif