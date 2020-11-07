#include "Fila.h"

//celula
Celula* newCelula(){
  Celula* cell = (Celula*) malloc(sizeof(Celula));
  cell->item = newJogadorV();
  cell->proximo = NULL;

  return cell;
}

//Fila
Fila* newFila(){
  Fila* line = (Fila*) malloc(sizeof(Fila));
  line->frente = line->traz = newCelula();
  line->quantidade = 0;

  return line;
}

void enfileirar (Fila* line,Jogador* player){
  
  if (line->quantidade == 5)
    desenfileirar(line);

  Celula *aux = newCelula();

  aux = line->traz;

  line->traz = newCelula();
  line->traz->item = player;
  line->traz->proximo = NULL;

  aux->proximo = line->traz;

  line->quantidade++;
  
}

Jogador* desenfileirar (Fila* line){
  if (line->frente == line->traz)//linea vazia
    return NULL;

  Celula *aux = newCelula();
  aux = line->frente->proximo;

  line->frente->proximo = line->frente->proximo->proximo;
  line->quantidade--;

  Jogador *player = clone(aux->item);
  free(aux);

  return player;
}

void mostrarFila (Fila* line){
  int i;
  Celula *atual;
  for ( atual = line->frente->proximo , i=0 ; atual!=NULL ; atual = atual->proximo , i++){
    printf("[%d] ", i);
    imprimir(atual->item);
  }

}

double  obterMediaAltura(Fila* line){
  int somaAltura=0;

  for ( Celula *atual = line->frente->proximo ; atual!=NULL ; atual = atual->proximo ){
    somaAltura += atual->item->altura;
  }

  double mediaAltura = line->quantidade!=0 ? (double) somaAltura/line->quantidade : 0;
  return mediaAltura;
}