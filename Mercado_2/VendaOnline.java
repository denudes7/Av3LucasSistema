class VendaOnline extends Venda implements Frete {
    public VendaOnline(Cliente cliente) {
        super(cliente);
    }
    @Override
    public double calcularFrete(double descontoFrete) {
        return 5.0 - descontoFrete;
    }

    @Override
    public String toString() {
        return "Venda Online - Cliente: " + cliente.getNome() + ", Total: R$" + calcularTotal();
    }

    @Override
    public double calcularFrete() {
        return 0;
    }

    @Override
    double calcularTotal() {
        return 0;
    }
}
