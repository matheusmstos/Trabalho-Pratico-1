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
            //no nosso caso, tranformamos um char em int(16bits)
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

        /*----------Métodos Auxiliares-----------*/

        //retorna o i-esimo bit da chave k a partir da esquerda
        private int bit(int i, char k)
        {
            if(i == 0)
            return 0;

            int c = (int)k;

            for(int j = 1; j = this.nbitsChave -i; j++)
            c = c/2;

            return c%2;
        }

        //verifica se p é nó externo
        private boolean eExterno(PatNo p)
        {
            Class classe = p.getClass();

            return classe.getName().equals(PatNoExt.class.getName());
        }

        //metodo para criar nó interno
        private PatNo criaNoInt(int i, PatNo esq, PatNo dir)
        {
            PatNoInt p = new PatNoInt();
            p.index = i;
            p.esq = esq;
            p.dir = dir;
            return p;
        }

        //metodo pra criar no eExterno
        private PatNo criaNoExt(char k)
        {
            PatNoExt p = new PatNoExt();
            p.chave = k;
            return p;
        }

        /*----------Pesquisa-----------*/

        public void pesquisa(char k)
        {
            this pesquisa(k, this.raiz);
        }

        private void pesquisa(char k, PatNo t)
        {
            if(this.eExterno(t))
            {
                PatNoExt aux = (PatNoExt)t;
                if(aux.chave == k) System.out.println("Elemento encontrado");
                else System.out.println("Elemento não encontrado")
            }
            else
            {
                PatNoInt aux = (PatNoInt)t;
                if(this.bit(aux.index, k) == 0) pesquisa(k, aux.esq);
                else pesquisa(k, aux.dir);
            }

        }

        /*----------Insere-----------*/

        public void insere(char k)
        {
            this.raiz = this.insere(k, this.raiz);
        }

    }
