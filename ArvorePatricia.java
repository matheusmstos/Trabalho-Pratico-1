import java.util.*;
import java.lang.*;

public class ArvorePatricia {

    private static abstract class PatNo { }

    private static class PatNoInt extends PatNo {
        int index;
        PatNo esq, dir;
    }
    private static class PatNoExt extends PatNo {
        String chave;
        Stack<Integer> ocorrer = new Stack<Integer> ();
    }

    private PatNo raiz;
    private int nbitsChave; //numero de bits de uma palavra



    public ArvorePatricia (int nbitsChave) {
        this.raiz = null; this.nbitsChave = nbitsChave;
    }

    /*----------METODOS AUXILIARES---------*/


    public String converteString(String converte){
        char c;
        String palavra = new String();

        for(int i = 0; i < converte.length(); i++){
            //pego o primeiro caracter da nossa string e armazeno em um char
            c = converte.charAt(i);

            int finalInt = (int)c;
            //System.out.println("Byte: " +finalInt);

            //converte um inteiro para uma string com o seu respectivo valor binario
            String f = Integer.toBinaryString(finalInt);
            //System.out.println("Binario: " +f);

            palavra = palavra + f;
        }
        //System.out.println("Nossa Palavra: " +palavra);
        return palavra;
    }

    // @{\it Retorna o i-\'esimo bit da chave k a partir da esquerda}@
    private int bit (int i, String k) {
        if(i == 0)
            return 0;
        i--;
        if(i >= k.length())
            return 0;
        else return k.charAt(i) - '0';

    }

    // @{\it Verifica se p \'e n\'o externo}@
    private boolean eExterno (PatNo p) {
        Class classe = p.getClass ();

        return classe.getName().equals(PatNoExt.class.getName());
    }

    private PatNo criaNoInt (int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt ();
        p.index = i;
        p.esq = esq;
        p.dir = dir;

        return p;
    }

    private PatNo criaNoExt (String k,int i) {
        PatNoExt p = new PatNoExt ();
        p.chave = k;
        p.ocorrer.push(i);
        return p;
    }

    /*----------PESQUISA---------*/


    public void pesquisa (String k) {
        this.pesquisa (k, this.raiz);
    }

    private void pesquisa (String k, PatNo t) {
        //System.out.println("Passei auqi");

        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt)t;

            // /aux.chave == k
            if (aux.chave.equals(k)){
              System.out.println ("Elemento encontrado");
              System.out.println ("A palavra encontrada: " + k);
              while(!aux.ocorrer.empty())
                {
                    System.out.println ("A ocorrência da palavra é: " + aux.ocorrer.pop());
                }
            }
            else System.out.println ("Elemento nao encontrado");
        }
        else {
            PatNoInt aux = (PatNoInt)t;

            if (this.bit (aux.index, k) == 0) pesquisa (k, aux.esq);
            else pesquisa (k, aux.dir);
        }
    }


    /*----------INSERE---------*/

    public void insere (String k,int i) {
        this.raiz = this.insere(k, this.raiz,i);
    }

    private PatNo insereEntre (String k, PatNo t, int i,int np) {
        PatNoInt aux = null;

        if (!this.eExterno(t)) aux = (PatNoInt)t;
        if (this.eExterno (t) || (i < aux.index)) { // @{\it Cria um novo n\'o externo}@
            PatNo p = this.criaNoExt(k,np);

            if (this.bit(i, k) == 1) return this.criaNoInt (i, t, p);
            else return this.criaNoInt (i, p, t);

        }else {
            if (this.bit(aux.index, k) == 1) aux.dir = this.insereEntre (k, aux.dir, i,np);
            else aux.esq = this.insereEntre (k, aux.esq, i,np);

            return aux;
        }
    }

    private PatNo insere (String s, PatNo t,int np) {

        String k = converteString(s);

        if (t == null)
            return this.criaNoExt (k,np);
        else {
            PatNo p = t;

            while (!this.eExterno (p)) {
                PatNoInt aux = (PatNoInt)p;

                if (this.bit (aux.index, k) == 1)  p = aux.dir;
                else p = aux.esq;
            }

            PatNoExt aux = (PatNoExt)p;
            int i = 1; // @{\it acha o primeiro bit diferente}@

            while ((i <= this.nbitsChave) && (this.bit(i, k) == this.bit (i, aux.chave)))
                i++;

            if (i > this.nbitsChave) {
                System.out.println ("Erro: chave ja esta na arvore");
                aux.ocorrer.push(np);
                return t;
            }

            else return this.insereEntre(k, t, i,np);
        }
    }

/*----------IMPRIME----------*/

public void imprime () {
    this.central (null, this.raiz, "RAIZ");
}

private void central (PatNo pai, PatNo filho, String msg) {
    if (filho != null) {
        if (!this.eExterno (filho)) {
            PatNoInt aux = (PatNoInt)filho;
            central (filho, aux.esq, "ESQ");
            if (pai != null)
            System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Int: " + aux.index);
            else System.out.println ("Pai: "+ pai + " " + msg+ " Int: " + aux.index);
            central (filho, aux.dir, "DIR");
        } else {
            PatNoExt aux = (PatNoExt)filho;
            if (pai != null)
            System.out.println ("Pai: "+ ((PatNoInt)pai).index + " " + msg+ " Ext: " + aux.chave);
            else System.out.println ("Pai: "+ pai + " " + msg+ " Ext: " + aux.chave);
        }
    }
}

}
