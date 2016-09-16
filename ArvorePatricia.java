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

        //numero de bits por chave
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

        private PatNo insere(char k, PatNo t, int i)
        {
            PatNoInt aux = null;

            if(!this.eExterno(t)) aux = (PatNoInt)t;

            if(this.eExteno(t) || (i < aux.index))
            {
                //Cria um novo nó eExterno
                PatNo p = this.criaNoExt(k);
                if(this.bit(i, k) == 1) return this.criaNoInt(i, t, p);
                else return this.criaNoInt(i, p, t);
            }
            else
            {
                if(this.bit(aux.index, k) == 1)
                    aux.dir = this.insereEntre(k, aux.dir, i);
                else
                    aux.esq = this.insereEntre(k, aux.esq, i);

                return aux;
            }

        }

        private PatNo insere(char k, PatNo t)
        {
            if(t == null)
                return this.criaNoExt(k);
            else
            {
                PatNo p =t;
                while(!this.eExterno(p))
                {
                    PatNoInt aux = (PatNoInt)p;
                    if(this.bit(aux.index, k) == 1)
                        p = aux.dir;
                    else p = aux.esq;
                }

                PatNoExt aux = (PatNoExt)p;
                int i = 1; //acha o primeiro bit diferente

                while((i <= this.nbitsChave)
                && (this.bit(i, k) == this.bit(i, aux.chave))) i++;

                if(i > this.nbitsChave)
                {
                    System.out.println("Erro: chave ja existe nessa arvore");
                    return t;
                }
                else return this.insereEntre(k, t, i);
            }
        }


}
