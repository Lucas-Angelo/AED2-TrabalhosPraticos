#ifndef FILA_H
#define FILA_H

#include <stdlib.h>
#include <stdio.h>
#include "Jogador.h"

//Célula
typedef struct Celula Celula;

typedef struct Celula {
  Jogador* item;
  Celula* proximo;
} Celula;

Celula* newCelula();

//Fila
typedef struct Fila{
  Celula* frente;
  Celula* traz;
  int quantidade;
} Fila;

Fila* newFila();

void enfileirar (Fila* line,Jogador* player);

Jogador* desenfileirar (Fila* line);

void mostrarFila (Fila* line);

double  obterMediaAltura(Fila* line);

#endif