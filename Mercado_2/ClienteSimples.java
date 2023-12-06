class ClienteSimples extends Cliente implements Credito {
    double credito;
    int idade;

    public ClienteSimples(String nome, double credito) {
        super(nome);
        this.credito = credito;
    }

    public ClienteSimples(String nome, int credito, int idade) {
        super();
    }


    @Override
    public void realizarPagamento(double valor) throws PagarException {
        if (valor > credito) {
            throw new PagarException("Crédito insuficiente para realizar o pagamento");
        }
        credito -= valor;
        System.out.println("Pagamento realizado com sucesso!");
    }

    @Override
    public double verificarCredito() {
        return credito;
    }
    public String toString() {
        return "ClienteSimples - " + getDetalhesCliente() +
                " - Crédito: R$" + verificarCredito();
    }

    public int getIdade() {
        return idade;
    }
}
