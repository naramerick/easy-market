package com.nara.marketapi;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TesteProduto{

public static void main (String[] args) {

Produto arroz = new Produto();
Produto feijao = new Produto();
Produto leite = new Produto();

arroz.setNome("Arroz");
arroz.setPreco(new BigDecimal("6.99"));
arroz.setQuantidade(5);

feijao.setNome("Feijão");
feijao.setPreco(new BigDecimal("8.65"));
feijao.setQuantidade(6);

leite.setNome("Leite");
leite.setPreco(new BigDecimal("5.89"));
leite.setQuantidade(3);

ArrayList<Produto> produtos = new ArrayList<>();

produtos.add(arroz);
produtos.add(feijao);
produtos.add(leite);

for (Produto elemento : produtos){
    System.out.println("Nome: " + elemento.getNome() 
    + "," + " Preço: " + elemento.getPreco() + "," 
    + " Quantidade: " + elemento.getQuantidade());
        }

Scanner scanner = new Scanner(System.in);

int opcao = -1;

while (opcao != 0){
    System.out.println("=== EASY MARKET ===");
    System.out.println(".......................");
    System.out.println();
    System.out.println("1 - Cadastrar produto");
    System.out.println("2 - Listar produtos");
    System.out.println("3 - Buscar produto");
    System.out.println("4 - Atualizar estoque");
    System.out.println("5 - Remover produto");
    System.out.println("6 - Calcular valor total do estoque");
    System.out.println("0 - Sair");
    opcao = scanner.nextInt();
    scanner.nextLine();

    switch (opcao){
        case 1:
    cadastrarProduto(produtos, scanner);
    break;

    case 2: 
        listarProdutos(produtos);
        break;

    case 3: 
        System.out.println("Qual produto deseja buscar? ");
        String nomeBusca = scanner.nextLine();

        buscarProduto(produtos, nomeBusca);
        break;

    case 4: {
        atualizarEstoque(produtos, scanner);
        break;
    }

    case 5: 
        removerProduto(produtos, scanner);
        break;
    
    case 6:
        BigDecimal valorTotalEstoque = calcularValorTotalEstoque(produtos);
        System.out.println("Valor total do estoque: " + valorTotalEstoque);     
        break;
    }

}

System.out.println("Encerrando o Easy Market");
scanner.close();

}

public static void buscarProduto(ArrayList<Produto> produtos, String nomeBusca){
        

    boolean encontrado = false;        
        
    for (Produto elemento : produtos) {
        if (elemento.getNome().equalsIgnoreCase(nomeBusca)){
            encontrado = true;
            System.out.println("Nome: " + elemento.getNome() + ", Preço: " + elemento.getPreco());
    }     
} 
    if (!encontrado) {
    System.out.println("Produto não encontrado");
        }
}

    public static BigDecimal calcularTotal(
        BigDecimal preco, int quantidade){
            return preco.multiply(new BigDecimal(quantidade));  
    }

    public static BigDecimal calcularValorTotalEstoque(
        ArrayList<Produto> produtos) {

            BigDecimal valorTotalEstoque = BigDecimal.ZERO;


            for (Produto elemento : produtos){
                BigDecimal totalProduto = calcularTotal(
                    elemento.getPreco(), 
                    elemento.getQuantidade());

                    valorTotalEstoque = valorTotalEstoque.add(totalProduto);
            }

            return valorTotalEstoque;

        }

        public static void cadastrarProduto(
            ArrayList<Produto> produtos,
            Scanner scanner
        ){
            String novoProduto;

        while(true){
            System.out.println("Digite o nome do novo produto: ");
                novoProduto = scanner.nextLine();

                novoProduto = novoProduto.trim();

                if (novoProduto.isEmpty()){
                    System.out.println("Preencha corretamente");
                    continue;
                }

                break;
        }
            BigDecimal preco;    

            while (true){
                System.out.println("Digite o preço do produto: ");
                String entradaPreco = scanner.nextLine();

                try {
                    entradaPreco = entradaPreco.replace(",", ".");
                    preco = new BigDecimal(entradaPreco);
                    if(preco.signum() <= 0) {
                        System.out.println("O preço deve ser maior que zero");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Preço inválido. Digite um número válido, por exemplo 7.50");
                }

            }   
            
            int quantidade;

            while(true){
                System.out.println("Digite a quantidade do produto: ");

                if(!scanner.hasNextInt()){
                    System.out.println("Entrada inválida. Digite novamente a quantidade");
                    scanner.nextLine();
                    continue;
                }

                quantidade = scanner.nextInt();
                scanner.nextLine();

                if (quantidade < 0) {
                    System.out.println("Quantidade inválida. Digite zero ou um número positivo.");
                    continue;
                }

                break;
            }

            Produto produtoNovo = new Produto();

            produtoNovo.setNome(novoProduto);
            produtoNovo.setPreco(preco);
            produtoNovo.setQuantidade(quantidade);


            produtos.add(produtoNovo);

            System.out.println("Produto Cadastrado: " + produtoNovo.getNome());
        }

        public static void listarProdutos(
            ArrayList<Produto> produtos
        ) {
            for (Produto elemento : produtos){
            System.out.println("Nome: " + elemento.getNome() 
            + "," + " Preço: " + elemento.getPreco() + "," 
            + " Quantidade: " + elemento.getQuantidade());
        }
    }

        public static void atualizarEstoque(
            ArrayList<Produto> produtos,
            Scanner scanner
        ) {
            boolean encontrado = false;

    while (!encontrado) {
        System.out.println("Qual produto atualizar?");
        String nomeAtualizar = scanner.nextLine();

        for (Produto elemento : produtos) {
            if (elemento.getNome().equalsIgnoreCase(nomeAtualizar)) {
                encontrado = true;

                System.out.println("Quantas unidades deseja adicionar?");
                int quantidadeAdicionada;

                while (true) {
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida. Digite um número inteiro.");
                        scanner.nextLine();
                        continue;
                    }

                    quantidadeAdicionada = scanner.nextInt();
                    scanner.nextLine();

                    if (quantidadeAdicionada < 0) {
                        System.out.println("Quantidade inválida. Digite zero ou um número positivo.");
                        continue;
                    }

                    break;
                }

                if (quantidadeAdicionada == 0) {
                    System.out.println("Nenhuma alteração no estoque.");
                } else {
                    int quantidadeAtual = elemento.getQuantidade();
                    int novaQuantidade = quantidadeAtual + quantidadeAdicionada;

                    elemento.setQuantidade(novaQuantidade);

                    System.out.println("Estoque atualizado! Nova quantidade: " + novaQuantidade);
                }

                break;
            }
        }

            if (!encontrado) {
                System.out.println("Produto não encontrado. Tente novamente.");
            }
        }
    }

            public static void removerProduto(
                ArrayList<Produto> produtos,
                Scanner scanner
            ) {
                System.out.println("Qual produto deseja remover?");
                String nomeRemover = scanner.nextLine();

                Iterator<Produto> iterador = produtos.iterator();

            boolean produtoRemovido = false;

                while (iterador.hasNext()) {
                Produto produtoAtual = iterador.next();

                    if (produtoAtual.getNome().equalsIgnoreCase(nomeRemover)){
                    iterador.remove();
                    produtoRemovido = true;
                    System.out.println("Produto removido com sucesso!");
                    break;
        }
    }

            if(!produtoRemovido){
            System.out.println("Produto não encontrado para remoção");
        }
    }
            
}    


    
