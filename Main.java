import java.util.*;
import java.io.*;

class Extrair {
    String token;
    PushbackReader arqTxt;

    boolean isAlpha(char ch) {
        if(ch >= 'A' && ch <= 'z')
            return true;
        return false;
    }

    public Extrair(String arqName) throws FileNotFoundException {
        this.arqTxt = new PushbackReader(new FileReader(arqName));
    }

    public String nextToken() throws IOException {
        int estado = 0;
        String palavra = "";
        char ch;
        while(estado != -1) {
            ch = (char) this.arqTxt.read();
            switch(estado) {
                case 0:
                    if(ch == -1)
                        return null;
                    else if(!isAlpha(ch) && ch != '-')
                        continue;
                    estado = 1;
                    palavra += ch;
                    break;
                case 1:
                    if(!isAlpha(ch) && ch != '-') {
                        estado = -1;
                        this.arqTxt.unread(ch);
                    }
                    palavra += ch;
                    break;
            }
        }
        return palavra;
    }

}

public class Main {

     public static void main (String[] args){

     int op,arroz;
	 String digitada;
     ArvorePatricia ar = new ArvorePatricia(128);
     Scanner entrada = new Scanner(System.in);

	 try {
        //ExtraiPalavra palavras = new ExtraiPalavra(args [0], args[1]);
        Extrair palavras = new Extrair(args[0]);
	    String palavra = null;
	    int i = 1;
	    while ((palavra = palavras.nextToken()) != null){
                System.out.println("teste: " + palavra);

                 ar.insere(palavra);
                 /*
                    List
                 */
                 i++;
	    }
	    //palavras.fecharArquivos();
	 } catch (Exception e) {
	    System.out.println (e.getMessage ());
	    }
     ar.imprime();
     /*
     do{
	 	System.out.println("------------------------------MENU------------------------------");
	 	System.out.println("Digite 1 para escolher uma palavra para ser pesquisada");
	 	System.out.println("Digite 0 para sair da aplicação");
	 	op = entrada.nextInt();

	 	switch(op){

	 	    case '1':
	 		{
	 		  do{
	 		    System.out.println("Digite a palavra a ser pesquisada e imprimir suas aplicações");
	 		    digitada = entrada.nextString();
	 		            //Chama pesquisa

	 		    System.out.println("Digite 1 para pesquisar uma nova palavra ou digite qualquer número para encerrar sua aplicação");
	 		    arroz = entrada.nextInt();
	 		   }while(arroz == 1);
	 		   break;
	 		}
	 	    default:
	 		System.out.println("Aplicação terminada com sucesso");
	 		break;

	 	}
	 	System.out.println("Digite 1 para continuar na aplicação ou digite qualquer número para encerra-la");
	 	op = entrada.nextInt();

	 }while(op == 1);




    ar.insere("maria");
    ar.insere("predrin");
    ar.insere("natha");
    ar.insere("rodrigo");
    ar.insere("grafos");
    ar.imprime();
    */

    }
}
