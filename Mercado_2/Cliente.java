abstract class Cliente {
    protected String nome;
    protected int id;
    protected String dataNasc;

    public Cliente(String nome) {
        this.nome = nome;
    }
    public Cliente() {}
    abstract void realizarPagamento(double valor) throws PagarException;
    public String getDetalhesCliente() {
        return nome + " - ";
    }

    @Override
    public String toString() {
        return getDetalhesCliente();
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return getIdade();
    }
}