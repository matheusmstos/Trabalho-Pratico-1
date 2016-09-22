import java.util.*;
public class Main {

    public static void main (String[] args){
	int op,arroz;
	String digitada
	Scanner entrada = new Scanner(System.in);	
	try {
	   ExtraiPalavra palavras = new ExtraiPalavra(args [0], args[1]);
	   String palavra = null;
	   int i = 1;
	   while ((palavra = palavras.proximaPalavra())!=null){
		//Magia negra acontece aqui
	   }
	   palavras.fecharArquivos();
	} catch (Exception e) {
	   System.out.println (e.getMessage ());
	   }
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
			    // Chama pesquisa
			    
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
      	
    }
















}
