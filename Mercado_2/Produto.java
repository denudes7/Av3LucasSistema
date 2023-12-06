import java.io.*;

abstract class Produto{
    protected String nome;
    protected double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    abstract double calcularPreco();

    public abstract double calcularPreco(Cliente cliente);

    protected double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " - R$" + preco;
    }
}
