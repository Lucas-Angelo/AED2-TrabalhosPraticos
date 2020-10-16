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

typedef struct Nomes {
    char nome[50];
} Nomes;

void imprimirJogador(Jogador jogadores);
void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
void criarJogadores(Jogador jogadores[], int ids[], int i);
Jogador copiarJogador (Jogador jogador);
void bubble(Jogador players[], int n);
int detectarPosicaoPorBuscaBinaria(Jogador players[], int menor, int maior, char procurado[]);

int qtdJogadores;

int main()
{
    int finaliza;
    char texto[50];
    int i=0;
    int ids[1000];

    do {
        fgets(texto, sizeof(texto), stdin);
        finaliza = strcmp(texto, "FIM\n");

        if(finaliza){
            ids[i] = atoi(texto);
            i++;
        }

    } while (finaliza);

    Jogador playersEntrada[i];

    criarJogadores(playersEntrada, ids, i);

    bubble(playersEntrada, i);

    Nomes vetNomes[qtdJogadores];

    int posicao;
    int x=0;
    char procurado[50];

    char part1[20], part2[20];
    do {
        fgets(procurado, sizeof(procurado), stdin);
        procurado[strlen(procurado)-1]='\0';
        finaliza = strcmp(procurado, "FIM");

        if(finaliza){
            strcpy(vetNomes[x].nome, procurado);
            x++;
        }

    } while (finaliza);

    /*char part1[20], part2[20];
    Jogador playersEntrada[3];
    for(int i=0; i<3; i++){
        scanf("%s %s", part1, part2);
        strcpy(playersEntrada[i].nome, "");
        strcat(playersEntrada[i].nome, part1);
        strcat(playersEntrada[i].nome, " ");
        strcat(playersEntrada[i].nome, part2);
    }

    bubble(playersEntrada, 3);

    Nomes vetNomes[3];

    for(int i=0; i<3; i++){
        scanf("%s %s", part1, part2);
        strcpy(vetNomes[i].nome, "");
        strcat(vetNomes[i].nome, part1);
        strcat(vetNomes[i].nome, " ");
        strcat(vetNomes[i].nome, part2);
    }*/

    for(int y=0; y<x; y++){
        int posicao = detectarPosicaoPorBuscaBinaria(playersEntrada, 0, i-1, vetNomes[y].nome);

        if(posicao != -1)
            printf("SIM\n");
        else
            printf("NAO\n");
    }

    return 0;
}

void imprimirJogador(Jogador jogadores){
    printf("[%d ## ", jogadores.id);

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
        printf("nao informado]\n");
    }
    else {
        printf("%s]\n", jogadores.estadoNascimento);
    }
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

void criarJogadores(Jogador jogadores[], int ids[], int tamVetor)
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

            for(int z=0; z<tamVetor; z++){
                if(atoi(informacoes_linha[0])==ids[z]){
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

    }
}

Jogador copiarJogador (Jogador jogador){
    return jogador;
}

void bubble(Jogador players[], int n) {
    for (int i = (n - 1); i > 0; i--) {
        for (int j = 0; j < i; j++) {
            if (strcmp(players[j].nome, (players[j + 1].nome)) > 0) {
                Jogador temp = copiarJogador(players[j]);
                players[j] = players[j + 1];
                players[j + 1] = temp;
            }
        }
    }
}

int cont=0;

int detectarPosicaoPorBuscaBinaria(Jogador players[], int menor, int maior, char procurado[]) {
    int meio = (maior + menor) / 2;
    Jogador numDoMeio = copiarJogador(players[meio]);

    if (menor > maior) // Chegou a fim e não achou
        return -1;
    else if(strcmp(numDoMeio.nome, procurado)==0) {  // Achou
        return 1;
        cont++;
    }
    else if (strcmp(numDoMeio.nome, procurado) < 0)
        return detectarPosicaoPorBuscaBinaria(players, meio+1, maior, procurado); // Caso esteja acima
    else
        return detectarPosicaoPorBuscaBinaria(players, menor, meio-1, procurado); // Caso esteja abaixo
}
