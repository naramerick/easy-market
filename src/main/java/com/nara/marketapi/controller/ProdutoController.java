package com.nara.marketapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.nara.marketapi.service.ProdutoService;

import java.util.List;
import com.nara.marketapi.Produto;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        
        this.produtoService = produtoService;

    }
    

    @GetMapping
    public List<Produto> listar() {

        return produtoService.listar();

    }
    

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id){

        return produtoService.buscarPorId(id);

    }
    

    @PostMapping 
    public Produto cadastrar(@RequestBody Produto produto) {
        

        return produtoService.cadastrar(produto);

    }

    @PutMapping("/{id}")
    public Produto atualizar (
        @RequestBody Produto produto, 
        @PathVariable Long id) {

            return produtoService.atualizar(produto, id);

    }

    @DeleteMapping("/{id}")
    public void deletar (@PathVariable Long id){

        produtoService.deletar(id);

    }
}
