package com.nara.marketapi.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import com.nara.marketapi.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProdutoService {

    private final List<Produto> produtos = new ArrayList<>();
    private Long proximoId = 4L;

    public ProdutoService(){
        
        Produto arroz = new Produto();
        Produto feijao = new Produto();
        Produto leite = new Produto();

        arroz.setId(1L);
        arroz.setNome("Arroz");
        arroz.setPreco(new BigDecimal("6.99"));
        arroz.setQuantidade(5);

        feijao.setId(2L);
        feijao.setNome("Feijão");
        feijao.setPreco(new BigDecimal("8.65"));
        feijao.setQuantidade(6);

        leite.setId(3L);
        leite.setNome("Leite");
        leite.setPreco(new BigDecimal("5.89"));
        leite.setQuantidade(3);

        produtos.add(arroz);
        produtos.add(feijao);
        produtos.add(leite);

    }

    public List<Produto> listar(){

        return produtos;

    }

    public Produto buscarPorId(Long id){

        for (Produto produto : produtos) {
            if (produto.getId().equals(id)){
                return produto;
            }

        }
        
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Produto não encontrado"
            );

    }

    public Produto cadastrar(Produto produto){

        produto.setId(proximoId);
        proximoId++;

        produtos.add(produto);

        return produto;

    }

    public Produto atualizar(Produto produto, Long id) {

        for (Produto produtoExistente : produtos) {
                if (produtoExistente.getId().equals(id)){

                produtoExistente.setNome(produto.getNome());
                produtoExistente.setPreco(produto.getPreco());
                produtoExistente.setQuantidade(produto.getQuantidade());    

                return produtoExistente;
            }
        }

            throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Produto não encontrado");

    }

    public void deletar (Long id) {

        Produto produto = buscarPorId(id);
        produtos.remove(produto);


    }

}
