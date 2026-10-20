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

System.out.println("Qual produto deseja buscar? ");
String nomeBusca = scanner.nextLine();

buscarProduto(produtos, nomeBusca);

System.out.println("Digite o nome do novo produto: ");
String novoProduto = scanner.nextLine();

novoProduto = novoProduto.trim();

System.out.println("Digite o preço do produto: ");
BigDecimal preco = new BigDecimal(scanner.nextLine());

System.out.println("Digite a quantidade do produto: ");
int quantidade = scanner.nextInt();

scanner.nextLine();


if(novoProduto.trim().isEmpty()){
            System.out.println("Nome inválido");
    }
    else if (preco.compareTo(BigDecimal.ZERO) < 0){
    System.out.println("Preço inválido");
        } else if (quantidade < 0) {
        System.out.println("Quantidade inválida");
            } else {

            Produto produtoNovo = new Produto();

            produtoNovo.setNome(novoProduto);
            produtoNovo.setPreco(preco);
            produtoNovo.setQuantidade(quantidade);


            produtos.add(produtoNovo);

            System.out.println("Produto Cadastrado: " + produtoNovo.getNome());
}      

for (Produto elemento : produtos){
    System.out.println("Nome: " + elemento.getNome() 
    + "," + " Preço: " + elemento.getPreco() + "," 
    + " Quantidade: " + elemento.getQuantidade());
}
    

boolean encontrado = false;

while (!encontrado){

    System.out.println("Qual produto atualizar? ");
        String nomeAtualizar = scanner.nextLine();

for (Produto elemento : produtos){
    if(elemento.getNome().equalsIgnoreCase(nomeAtualizar)){
        encontrado = true;
        System.out.println("Quantas unidades deseja adicionar? ");
        
        int quantidadeAdicionada;

        while(true){
            
            if(!scanner.hasNextInt()){
            System.out.println("Entrada inválida. Digite um número inteiro");
                scanner.nextLine();
                continue;
            }
            quantidadeAdicionada = scanner.nextInt();
            scanner.nextLine();

            if (quantidadeAdicionada < 0){
            System.out.println("Quantidade adicionada inválida. Digite zero ou um número positivo.");
                continue;

            } 
        
                break;    
            }

            if (quantidadeAdicionada == 0){
                System.out.println("Nenhuma alteração no estoque");
            } else {

            int quantidadeAtual = elemento.getQuantidade();

            int novaQuantidade = quantidadeAtual + quantidadeAdicionada;

            elemento.setQuantidade(novaQuantidade);

            System.out.println("Estoque atualizado! Nova quantidade: " + novaQuantidade);

            }
        }


    }
        if(!encontrado){
        System.out.println("Produto não encontrado para atualização");
    }
}

for(Produto elemento : produtos){
    BigDecimal totalProduto = calcularTotal(
        elemento.getPreco(),
        elemento.getQuantidade()
    );
    System.out.println("Total do " + elemento.getNome() + ": " + totalProduto);
}
    
    BigDecimal valorTotalEstoque = calcularValorTotalEstoque(produtos);

    System.out.println("Valor total do estoque: " + valorTotalEstoque);

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
        }
    }

    if(!produtoRemovido){
        System.out.println("Produto não encontrado para remoção");
    }

    for(Produto elemento : produtos){
        System.out.println("Nome: " + elemento.getNome() 
    + "," + " Preço: " + elemento.getPreco() + "," 
    + " Quantidade: " + elemento.getQuantidade());
    }

    BigDecimal novoValorTotalEstoque = calcularValorTotalEstoque(produtos);

    System.out.println("Novo valor total do estoque: " + novoValorTotalEstoque);


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
            

}    


    
