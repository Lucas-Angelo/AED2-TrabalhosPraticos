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

typedef struct IDSEntrada {
    int id;
} IDSEntrada;

void imprimirJogador(Jogador jogadores);
void strSplit(char *strTOsplit,char *strArr[], char strSeparet,int nArr);
void criarJogadores(Jogador jogadores[], int ids[], int i);
Jogador copiarJogador (Jogador jogador);
void bubble(Jogador players[], int n);
int buscaBinariaRecursiva(Jogador players[], int menor, int maior, char procurado[]);

int main()
{
    char idString[10];
    int posicaoEntrada=0;
    int vetorIDs[150];

    do{
        fgets(idString, sizeof(idString), stdin);
        idString[strlen(idString)-1]='\0';

        if(strcmp(idString, "FIM")!=0){
            vetorIDs[posicaoEntrada]=atoi(idString);
            posicaoEntrada++;
        }

    } while (strcmp(idString, "FIM")!=0);

    Jogador playersEntrada[posicaoEntrada];
    criarJogadores(playersEntrada, vetorIDs, posicaoEntrada);

    bubble(playersEntrada, posicaoEntrada);

    char nome[50];
    int posicaoNome=0;
    Nomes vetNomes[150];

    do {
        fgets(nome, sizeof(nome), stdin);
        nome[strlen(nome)-1]='\0';

        if(strcmp(nome, "FIM")!=0){
            strcpy(vetNomes[posicaoNome].nome, nome);
            posicaoNome++;
        }
    } while(strcmp(nome, "FIM")!=0);

    int res;
    for(int i=0; i<posicaoNome; i++){
        res = buscaBinariaRecursiva(playersEntrada, 0, posicaoEntrada, vetNomes[i].nome);

        if(res!=-1)
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
    arquivo = fopen("/tmp/players.csv", "r");

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
int buscaBinariaRecursiva(Jogador players[], int menor, int maior, char procurado[]) {
    int meio = (maior + menor) / 2;
    Jogador doMeio = copiarJogador(players[meio]);
    char semAsterisco[50];
    strcpy(semAsterisco,  doMeio.nome);
    if(semAsterisco[strlen(semAsterisco)-1]=='*')
        semAsterisco[strlen(semAsterisco)-1]='\0';

    if (menor > maior)
        return -1;
    else if(strcmp(doMeio.nome, procurado)==0 || strcmp(semAsterisco, procurado)==0) {
        return 1;
        cont++;
    }
    else if (strcmp(doMeio.nome, procurado) < 0 )
        return buscaBinariaRecursiva(players, meio+1, maior, procurado);
    else
        return buscaBinariaRecursiva(players, menor, meio-1, procurado);
}
