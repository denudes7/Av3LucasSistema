import java.io.*;
import java.util.*;

public class SistemaVendas {
    protected static Scanner scanner = new Scanner(System.in);
    protected static Set<Cliente> clientes = new HashSet<>();
    protected static List<Produto> produtos = new ArrayList<>();
    protected static List<ClientePremium> clientesPremium = new ArrayList<>();
    protected static List<ClienteSimples> clientesSimples = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Bem-vindo AV3 Denis!");

        produtos.add(new ProdutoSimples("Livro Unifor", 5));
        produtos.add(new ProdutoSimples("Camiseta Unifor", 30.0));
        produtos.add(new ProdutoPremium("Kit Unifor (Premium)", 40.0));

        while (true) {
            System.out.println("\nMENU:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Realizar Compra");
            System.out.println("3. Exibir Produtos");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    Cliente clienteAtual = identificarUsuario();
                    try {
                        realizarCompra(clienteAtual);
                    } catch (PagarException e) {
                        System.err.println("Erro durante a compra: " + e.getMessage());
                    }
                    break;
                case 3:
                    exibirProdutos();
                    break;
                case 4:
                    System.out.println("Saindo do programa.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static Cliente identificarUsuario() {
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();
        System.out.println("Tipo de clinte:");
        System.out.println("Cliente Simples - DIGITE 1");
        System.out.println("Cliente Premium - DIGITE 2");
        System.out.println("Para sair       - DIGITE 0");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                return new ClienteSimples(nome, 0);
            case 2:
                return new ClientePremium(nome, 0);
            default:
                System.out.println("Opção inválida. Saindo do programa.");
                System.exit(0);
                return null;
        }
    }

    public static void cadastrarCliente()  {
        try {
            System.out.println("Cadastro de Cliente:");

            System.out.println("Digite seu nome :");
            String nome = scanner.nextLine();
            

            // Verifica se o nome contém apenas letras
            if (!nome.matches("[a-zA-Z]+")) {
                throw new NomeInvalidoException("Erro. O nome deve conter apenas letras.");
            }

            System.out.println("Tipo de cliente (Premium ou Simples):");
            String tipoCliente = scanner.nextLine().toLowerCase();

            if ("premium".equals(tipoCliente)) {
                ClientePremium novoCliente = new ClientePremium(nome, 0);
                clientesPremium.add(novoCliente);
                clientes.add(novoCliente);
                System.out.println("Cliente Premium cadastrado!");
            } else if ("simples".equals(tipoCliente)) {
                ClienteSimples novoCliente = new ClienteSimples(nome, 0);
                clientesSimples.add(novoCliente);
                clientes.add(novoCliente);
                System.out.println("Cliente Simples cadastrado!");
            } else {
                System.out.println("Tipo de cliente inválido.");
            }
        } catch (NomeInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void realizarCompra(Cliente cliente) throws PagarException {
        Venda venda = new VendaOnline(cliente);

        System.out.println("Lista de Produtos:");

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);

            if (produto instanceof ProdutoPremium && cliente instanceof ClientePremium) {
                ((ProdutoPremium) produto).habilitarDesconto();
            }

            double precoComDesconto = produto.calcularPreco(cliente);
            System.out.println((i + 1) + ". " + produto.toString() + " - Preço com Desconto: R$" + precoComDesconto);
        }

        System.out.println("Escolha os produtos (digite os números separados por vírgula ):");
        String escolhaProdutos = scanner.nextLine();
        String[] numeros = escolhaProdutos.split(",");

        double total = 0;

        for (String numero : numeros) {
            int index = Integer.parseInt(numero.trim()) - 1;
            if (index >= 0 && index < produtos.size()) {
                Produto produtoEscolhido = produtos.get(index);
                venda.adicionarProduto(produtoEscolhido);
                total += produtoEscolhido.calcularPreco(cliente);
            }
        }

        System.out.println("Nota fiscal: ");
        for (Produto produto : venda.getProdutos()) {
            double precoComDesconto = produto.calcularPreco(cliente);
            System.out.println(produto.toString() + " - Preço com Desconto: R$ " + precoComDesconto);
        }

        if (cliente instanceof ClientePremium) {
            total = ((ClientePremium) cliente).aplicarDesconto(total);
        }

        System.out.println("Total: R$ " + total);

        System.out.println("Valor à pagar : ");
        double valorPagamento = scanner.nextDouble();

        if (valorPagamento >= total) {
            double troco = valorPagamento - total;
            System.out.println("Pagamento realizado! Troco: R$" + troco);
        } else {
            throw new PagarException("Valor insuficiente.");
        }
    }

    private static void exibirProdutos() {
        System.out.println("\nLista de Produtos :");
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            System.out.println(i + 1 + ". " + produto.toString());
        }
        System.out.println();
    }
}
