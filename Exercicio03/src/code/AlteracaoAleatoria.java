/**
 * 
 */
package code;
import java.io.*;
import java.util.Random;
import java.util.Scanner;


/**
 * @author josem
 *
 */
public class AlteracaoAleatoria {

	/**
	 * @param args
	 */
    public static void sorteio(String f1,String f2)
    {
        Random gerador =  new Random();

        char s1;
        char s2;
        int valor = gerador.nextInt(26) + 1;

        char frase1 [] = new char [f1.length()];
        char frase2 [] = new char [f2.length()];

        gerador.setSeed(4);
        //System.out.println((char)('a' + Math.abs(gerador.nextInt())% 26));
        s1 = (char)('a' + Math.abs(gerador.nextInt())% 26);
        gerador.setSeed(valor);
        s2 = (char)('a' + Math.abs(gerador.nextInt())% 26);

        System.out.println("A letra sorteada foi: " + s2);

           for(int i=0;i < f1.length() ;i++)
           {
               if(s1 == f1.charAt(i))
                    frase1[i] = s2;
               else
               {
                   frase1[i] = f1.charAt(i);
               }
           }

        for(int i=0; i < f1.length() ;i++)
            System.out.print(frase1[i]);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String f1;
        String f2;

        System.out.print("Digite uma frase: ");
        f1 = entrada.readLine();
        System.out.print("Digite outa frase: ");
        f2 = entrada.readLine();

        // char l =f2.charAt(0);
        //System.out.println("arwr"+l);

        sorteio(f1,f2);

    }

}

