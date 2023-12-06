interface Desconto {
    double aplicarDesconto();
}

interface Credito {
    double verificarCredito();

}

interface Frete {
    double calcularFrete(double descontoFrete);
    double calcularFrete();
}
