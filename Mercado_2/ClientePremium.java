class ClientePremium extends Cliente implements Credito{
    int idade;
    double credito;
    float troco;
    public ClientePremium(String nome) {
        super(nome);
    }

    public ClientePremium(String nome, int idade) {
    }

    @Override
    void realizarPagamento(double valor) throws PagarException {
        System.out.println("Pagamento realizado com desconto para cliente premium!");
    }
    public double aplicarDesconto(double valor) {
        double desconto = 0.20;
        return valor - (valor * desconto);
    }
    public String toString() {
        return "ClientePremium - "
                + getDetalhesCliente();
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public double verificarCredito() {
        return 0 + troco;
    }
}