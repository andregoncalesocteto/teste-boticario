package com.andregoncales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoPersistenceService produtoPersistenceService;

    @GetMapping
    public Page<Produto> consultarProdutos(
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return produtoPersistenceService.consultarProdutos(product, minPrice, maxPrice, page, size);
    }

    @PostMapping
    public Produto inserirProduto(@RequestBody Produto produto) {
        return produtoPersistenceService.inserirProduto(produto);
    }
}