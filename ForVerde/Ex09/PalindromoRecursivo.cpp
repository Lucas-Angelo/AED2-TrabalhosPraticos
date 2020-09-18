#include <iostream>
#include <stdbool.h>
#include <string.h>
#include <string>

using namespace std;


class PalindromoRecursivo {
PalindromoRecursivo(){}
public:
    static void main(){

		string str;
		bool finaliza;

		do {
			std::getline(cin, str);

			finaliza = str.at(0)=='F' && str.at(1)=='I' && str.at(2)=='M';

			if(!finaliza) {
				if(passarString(str))
					cout << "SIM\n";
				else
					cout << "NAO\n";
			}

		} while (!finaliza);

	}

    static bool passarString (string str) {
		return isRecursivo (str, 0);
	}

private:
	static bool isRecursivo (string str, int indice) {
		bool igual;

		if (str.length() == indice)
			igual = true;
		else if(str.at(indice)!=str.at(str.length()-indice-1))
			igual = false;
		else
			igual = isRecursivo (str, indice+1);

		return igual;
	}



};

int main()
{
    PalindromoRecursivo::main();
    return 0;
}
