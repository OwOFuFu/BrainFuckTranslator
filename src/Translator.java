import java.util.Scanner;

/**
 * Translator Created 4/26/2022
 * By YumaHisai at 5:26 AM
 */

class Translator
{
    private static final Scanner oggetto = new Scanner(System.in);
    private static int ptr; // Puntatore dati
    private static final int lunghezza = 65535;

    // Array di tipo di byte che simula una memoria massima
    // 65535 bit da 0 a 65534.
    private static final byte[] memoria = new byte[lunghezza];

    // Inizio codice Interpreter
    private static void interpret(String s)
    {
        int c = 0;

        // Analisi di ogni carattere del codice
        for (int i = 0; i < s.length(); i++)
        {


            // > sposta a destra
            if (s.charAt(i) == '>')
            {
                if (ptr == lunghezza - 1)// Se la memoria è piena
                    ptr = 0;// viene riportato a zero
                else
                    ptr ++;
            }

            // < sposta a sinistra
            else if (s.charAt(i) == '<')
            {
                if (ptr == 0) //

                    ptr = lunghezza - 1;
                else
                    ptr --;
            }

            // + incrementa il valore della memoria
            else if (s.charAt(i) == '+')
                memoria[ptr] ++;

                // - decrementa il valore della cella di memoria
            else if (s.charAt(i) == '-')
                memoria[ptr] --;

                // . restituisce il carattere indicato dalla
                // cella
            else if (s.charAt(i) == '.')
                System.out.print((char)(memoria[ptr]));

                // , inserisce un carattere e lo memorizza in
                // cella
            else if (s.charAt(i) == ',')
                memoria[ptr] = (byte)(oggetto.next().charAt(0));

                // [ salta oltre la corrispondenza ] se la cella
                // è 0
            else if (s.charAt(i) == '[')
            {
                if (memoria[ptr] == 0)
                {
                    i++;
                    while (c > 0 || s.charAt(i) != ']')
                    {
                        if (s.charAt(i) == '[')
                            c++;
                        else if (s.charAt(i) == ']')
                            c--;
                        i ++;
                    }
                }
            }

            // ] torna alla corrispondenza [ se il
            // la cella è diversa da zero
            else if (s.charAt(i) == ']')
            {
                if (memoria[ptr] != 0)
                {
                    i --;
                    while (c > 0 || s.charAt(i) != '[')
                    {
                        if (s.charAt(i) == ']')
                            c ++;
                        else if (s.charAt(i) == '[')
                            c --;
                        i --;
                    }
                    i --;
                }
            }
        }
    }

    // Codice Primario
    public static void main(String args[])
    {
        System.out.println("Inserisci il codice:");
        String code = oggetto.nextLine();
        System.out.println("Risultato:");
        interpret(code);
    }
}
