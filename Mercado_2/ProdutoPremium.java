class ProdutoPremium extends Produto {
    private boolean aplicarDesconto;

    public ProdutoPremium(String nome, double preco) {
        super(nome, preco);
        this.aplicarDesconto = false;
    }

    @Override
    public double calcularPreco() {
        if (aplicarDesconto) {
            return getPreco() * 0.8;
        } else {
            return getPreco();
        }
    }

    @Override
    public double calcularPreco(Cliente cliente) {
        if (aplicarDesconto && cliente instanceof ClientePremium) {
            return getPreco() * 0.8;
        }
        return getPreco();
    }

    public void habilitarDesconto() {
        this.aplicarDesconto = true;
    }
    public void envioDeBrinde(double totalCompra) {
        if (aplicarDesconto && totalCompra > 40) {
            System.out.println("Parabéns! Você ganhou um brinde especial.");
        }
    }
    @Override
    public String toString() {
        return "ProdutoPremium - " + getNome() + " - R$" + calcularPreco();
    }
}
