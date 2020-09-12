#include <iostream>

using namespace std;


class PalindromoIterativo {
PalindromoIterativo(){}
public:
    static void main(){
		string texto;
		bool finaliza;

		do {
			std::getline(cin, texto);

            finaliza = texto.at(0)=='F' && texto.at(1)=='I' && texto.at(2)=='M';

			if(!finaliza){
				if(isPalindromo(texto))
					cout << "SIM\n";
				else
					cout << "NAO\n";
			}

		} while (!finaliza);

	}

	static bool isPalindromo(string texto){
	    bool igual = true;
	    string invertida = "";

	    for(int i=0;i<texto.length();i++) {
            invertida = invertida + texto.at(texto.length()-i-1);
        }

        for(int i=0;i<texto.length();i++) {
            if(texto.at(i)!=invertida.at(i))
                igual = false;
        }

        return igual;
	}

};

int main()
{
    PalindromoIterativo::main();
    return 0;
}
