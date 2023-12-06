    class ProdutoSimples extends Produto implements Desconto {
        public ProdutoSimples(String nome, double preco) {
            super(nome, preco);
        }
        double calcularPreco(double descontoFixo) {
            double preco = 0;
            return preco - descontoFixo;
        }

        @Override
        public String toString() {
            return nome + " - R$" + preco;
        }

        @Override
        public double aplicarDesconto() {
            return 0;
        }

        @Override
        double calcularPreco() {
            return preco;
        }

        @Override
        public double calcularPreco(Cliente cliente) {
            return preco;
        }
    }
