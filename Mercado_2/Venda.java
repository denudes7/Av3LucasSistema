import java.util.ArrayList;
import java.util.List;

abstract class Venda {
    private List<Produto> produtos;
    protected Cliente cliente;

    public Venda(Cliente cliente) {
        this.produtos = new ArrayList<>();
        this.cliente = cliente;
    }
    private double aplicarDesconto(double valor) {
        double desconto = 0.20;
        return valor - (valor * desconto);
    }

    void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    double calcularTotal() {
        double total = 0;

        for (Produto produto : produtos) {
            double precoProduto = produto.calcularPreco(cliente);

            if (cliente instanceof ClientePremium) {
                precoProduto = ((ClientePremium) cliente).aplicarDesconto(precoProduto);
            }

            total += precoProduto;
        }

        return total;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return "Venda - Cliente: " + cliente.toString() + ", Total: R$" + calcularTotal();
    }
    public double calcularTotalComDesconto(ClientePremium clientePremium) {
        double total = calcularTotal();
        return clientePremium.aplicarDesconto(total);
    }
    public String resumoDaCompra() {
        String resumo = "Resumo da compra:\n";

        for (Produto produto : produtos) {
            double precoComDesconto = produto.calcularPreco(cliente);
            resumo += produto.toString() + " - Preço: R$" + String.format("%.2f", precoComDesconto);
        }

        double totalComDesconto = calcularTotalComDesconto();
        resumo += "Total a pagar: R$" + String.format("%.2f", totalComDesconto);

        return resumo;
    }

    private double calcularTotalComDesconto() {
        double total = calcularTotal();
        if (cliente instanceof ClientePremium) {
            total = ((ClientePremium) cliente).aplicarDesconto(total);
        }
        return total;
    }



}