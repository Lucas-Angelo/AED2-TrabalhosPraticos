#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct Jogador {
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[150];
    int anoNascimento;
    char cidadeNascimento[150];
    char estadoNascimento[150];
} Jogador;

int qtdLinhas();
void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
void lerJogadores(Jogador jogadores[]);
void imprimirTodos(Jogador jogadores[]);

int qtdJogadores;

int main( )
{
    qtdJogadores = qtdLinhas();
    Jogador players[qtdJogadores];

    criarJogadores(players);
    imprimirTodos(players);
    /*char *informacoes_linha[8];
    char info[100];
    strcpy(info,"ola,tudo,bem,com,voce, ,meu,amigo,paulo");
    strSplit(info, &informacoes_linha, ',', 8);

    for (int i=0;i<8;i++){
        printf("%s\n", informacoes_linha[i]);
    }*/

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
    /*int i = 0;
    char * pch;

    pch = strtok (strTOsplit,strSeparet);
    for(i = 0;i < nArr;i++)
    {
        //printf ("%s\n",pch);
        if (pch==NULL)
            strcpy (strArr[i], "");
        else
            strArr[i] = pch;
        printf("%s\n", strArr[i]);
        pch = strtok (NULL,strSeparet);
    }*/
}

void criarJogadores(Jogador jogadores[])
{
    FILE *arquivo;
    arquivo = fopen("players.csv", "r");

    char linha[255];
    char *result;
    char *informacoes_linha[8];
    int i=0, j;
    while (fgets(linha, sizeof(linha), arquivo))  // Enquando n�o chegar no fim do arquivo..
    {

        strSplit(linha, informacoes_linha, ',', 8); //Separa os campos e os armazena no vetor de 3 posi��es chamado informacoes_linha
        if(strcmp(informacoes_linha[0], "id")!=0){
            //isso � uma fun��o eveline :)
            //Cada posi��o do vetor VetorEmpregados guarda n�o so uma mas tres informa��es.
            char aux[50];
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
Jogador copiarJogador (Jogador jogador){
    return jogador;
}
void imprimirJogador(Jogador jogadores){
    printf("\n[%d ## ", jogadores.id);

    printf("%s ## ", jogadores.nome);

    printf("%d ## ", jogadores.altura);

    printf("%d ## ", jogadores.peso);

    printf("%d ## ", jogadores.anoNascimento);

    if (strcmp(jogadores.universidade,"")==0) { // Se o dado esta vazio
        printf("nao informado ## ");
    }
    else {
        printf("%s ## ", jogadores.universidade);
    }

    if (!strlen(jogadores.cidadeNascimento)> 0) { // Se o dado esta vazio
        printf("nao informado ## ");
    }
    else {
        printf("%s ## ", jogadores.cidadeNascimento);
    }

    if (!strlen(jogadores.estadoNascimento) > 0) { // Se o dado esta vazio
        printf("nao informado]");
    }
    else {
        printf("%s]", jogadores.estadoNascimento);
    }
}

void imprimirTodos(Jogador jogadores[])
{
    int i;
    for (i = 0; i < qtdJogadores; i++) {
        imprimirJogador(jogadores[i]);
    }
}
