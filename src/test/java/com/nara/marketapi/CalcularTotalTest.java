package com.nara.marketapi;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalcularTotalTest {
    
    @Test 
    public void deveCalcularTotalCorretamente(){

        BigDecimal resultadoEsperado = new BigDecimal("34.95");

        BigDecimal preco = new BigDecimal("6.99");
        int quantidade = 5;

        BigDecimal resultadoObtido = TesteProduto.calcularTotal(preco, quantidade);
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test 
    public void deveRetornarZeroQuandoQuantidadeForZero(){

        BigDecimal resultadoEsperado = new BigDecimal("00.00");

        BigDecimal preco = new BigDecimal("6.99");
        int quantidade = 0;

        BigDecimal resultadoObtido = TesteProduto.calcularTotal(preco, quantidade);
        assertEquals(resultadoEsperado, resultadoObtido);
    }

    @Test 
    public void calcularValorTotalEstoque(){

        ArrayList<Produto> produtos = new ArrayList<>();

            Produto arroz = new Produto();
            Produto feijao = new Produto();

            BigDecimal resultadoEsperado = new BigDecimal("52.25");
        
            arroz.setNome("Arroz");
            arroz.setPreco(new BigDecimal("6.99"));
            arroz.setQuantidade(5);

            feijao.setNome("Feijão");
            feijao.setPreco(new BigDecimal("8.65"));
            feijao.setQuantidade(2);

            produtos.add(arroz);
            produtos.add(feijao);

            BigDecimal resultadoObtido = TesteProduto.calcularValorTotalEstoque(produtos);
            assertEquals(resultadoEsperado, resultadoObtido);

        }

        @Test
        public void deveRetornarZeroQuandoEstoqueEstiverVazio(){

            ArrayList<Produto> produtos = new ArrayList<>();
            BigDecimal resultadoEsperado = BigDecimal.ZERO;

            BigDecimal resultadoObtido = TesteProduto.calcularValorTotalEstoque(produtos);
            assertEquals(resultadoEsperado, resultadoObtido);
        }


    }


