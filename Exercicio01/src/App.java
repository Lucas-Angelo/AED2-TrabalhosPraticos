import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        String str = new String();
        boolean isFIM;

        do{
            str = entrada.readLine();
            isFIM = str.length()== 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M' ;
            if (!isFIM)
                System.out.println(cifrarDeCesar(str));
        }while (!isFIM);

    }
    private static String cifrarDeCesar (String str){
        String cifra = new String ("");
        char newChar;
        int i;
        boolean isLetter;

        for (i=0;i<str.length();i++){
            newChar = (char)( (int)str.charAt(i) + 3 );
            isLetter = (newChar > 'a' && newChar <'z') || (newChar > 'A' && newChar <'Z') ;
            if (!isLetter)
                newChar -= 26;
            cifra += newChar;
        }

        return cifra;

    }
}
