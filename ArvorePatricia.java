public class ArvorePatricia
{
    private static abstract class PatNo {}

    private static class PatNoInt extends PatNo
    {
        int index;       //nosso nó, indicando bit diferentes
        PatNo esq, dir;  //e os nós a esq e dir
    }

    private static class PatNoExt extends PatNo
    {
        char chave; //palavra propriamente dita
        //no nosso caso, tranformamos um char em int
    }

    private PatNo raiz;
    private int nbitsChave;

    //metodos privados

    //quem eh nbitsChave??
    public ArvorePatricia(int nbitsChave)
    {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    public void pesquisa(char k)
    {
        this pesquisa(k, this.raiz);
    }

    public void insere(char k)
    {
        this.raiz = this.insere(k, this.raiz);
    }

}
