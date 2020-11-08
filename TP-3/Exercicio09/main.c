#include <stdio.h>
#include <stdlib.h>
#include "Fila.h"
#include "Jogador.h"

int qtdLinhas();
void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
void criarJogadores(Jogador jogadores[]);

int qtdJogadores;

int main(void) {
    qtdJogadores = qtdLinhas();
    Jogador players[qtdJogadores];

    criarJogadores(players);

    int finaliza;
    char texto[50];

    Fila *line = newFila();

    do {
        fgets(texto, sizeof(texto), stdin);
        finaliza = strcmp(texto, "FIM\n");

        if(finaliza){
            enfileirar(line, &players[atoi(texto)]);
            printf("%.0f\n", obterMediaAltura(line));
        }

    } while (finaliza);

    int qtdcomandos;
    int toLineUpId;

    scanf("%d", &qtdcomandos);

    char comando[10];
    int i;
    for (i=0;i<qtdcomandos;i++){

        fgets(comando, sizeof(comando), stdin);

        if (comando[0] == 'I'){

            toLineUpId = atoi(strpbrk(comando, " "));
            enfileirar(line, &players[toLineUpId]);
            printf("%.0f\n", obterMediaAltura(line));
        }
        else if (comando[0] == 'R')
            printf("(R) %s\n", desenfileirar(line)->nome);
        else
          i--;
            
    }

    mostrarFila(line);
    
    return 0;
}

int qtdLinhas(){
    FILE *arquivo = fopen("players.csv", "r");
    int qtd=0;
    char linha[255];
    char *informacoes_linha[8];
    while(fgets(linha, sizeof(linha), arquivo)){
        strSplit(linha, informacoes_linha, ',',8);
        if(strcmp(informacoes_linha[0], "id")!=0)
            qtd++;
    }
    return qtd;
}

void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr)
{
    /*(strTOsplit[j] != strSeparet || i==nArr-1) && (strTOsplit[j] != '\n' && strTOsplit[j] != '\0')*/
    int i, j = 0;
    for (i=0;i<nArr;i++){
        strArr[i] = (char *) calloc( sizeof(char), 150 );
        strcpy(strArr[i],"");
        for (;(strTOsplit[j] != strSeparet || i==nArr-1) && (strTOsplit[j] != '\n' && strTOsplit[j] != '\0');j++){
            int tamanho = strlen(strArr[i]);
            strArr[i][tamanho] = strTOsplit[j];
            strArr[i][tamanho+1] = '\0';
        }
        j++;
    }
}

void criarJogadores(Jogador jogadores[])
{
    FILE *arquivo;
    arquivo = fopen("players.csv", "r");

    char linha[255];
    char *informacoes_linha[8];
    int i=0;
    while (fgets(linha, sizeof(linha), arquivo))  // Enquando não chegar no fim do arquivo..
    {

        strSplit(linha, informacoes_linha, ',', 8); //Separa os campos e os armazena no vetor de 3 posições chamado informacoes_linha
        if(strcmp(informacoes_linha[0], "id")!=0){
            //isso é uma função eveline :)
            //Cada posição do vetor VetorEmpregados guarda não so uma mas tres informações.
            jogadores[i].id = atoi(informacoes_linha[0]);
            strcpy(jogadores[i].nome, ((const char*)(informacoes_linha[1])));
            jogadores[i].altura = atoi(informacoes_linha[2]);
            jogadores[i].peso = atoi(informacoes_linha[3]);
            strcpy(jogadores[i].universidade, ((const char*)informacoes_linha[4]));
            jogadores[i].anoNascimento = atoi(informacoes_linha[5]);
            strcpy(jogadores[i].cidadeNascimento, ((const char*)informacoes_linha[6]));
            strcpy(jogadores[i].estadoNascimento, ((const char*)informacoes_linha[7]));
            i++;
        }

    }
}