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
	public static void sorteio(String p1,String p2)
    {
        char s1;
        char s2;

        Random gerador =  new Random();
        gerador.setSeed(4);
        //System.out.println((char)('a' + Math.abs(gerador.nextInt())% 26));
        s1 = (char)('a' + Math.abs(gerador.nextInt())% 26);
        gerador.setSeed(4);
        s2 = (char)('a' + Math.abs(gerador.nextInt())% 26);

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
