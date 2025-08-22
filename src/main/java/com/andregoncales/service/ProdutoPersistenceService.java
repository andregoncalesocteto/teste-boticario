package com.andregoncales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProdutoPersistenceService {

    private final IProdutoRepository produtoRepository;

    public ProdutoPersistenceService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    private boolean produtoExiste(Produto produto) {
        return produtoRepository.existsByProductAndType(produto.getProduct(), produto.getType());
    }

    public void salvarTodos(List<Produto> produtos) {
        final int BATCH_SIZE = 200;
        int total = produtos.size();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < total; i += BATCH_SIZE) {
            int fromIndex = i;
            int toIndex = Math.min(i + BATCH_SIZE, total);
            List<Produto> batch = produtos.subList(fromIndex, toIndex);

            executor.submit(() -> {
                for (Produto produto : batch) {
                    if (!produtoExiste(produto)) {
                        produtoRepository.save(produto);
                    }
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Page<Produto> consultarProdutos(String product, String minPrice, String maxPrice, int page, int size) {
        if ((product == null || product.isBlank()) && (minPrice == null || maxPrice == null)) {
            throw new IllegalArgumentException("Informe o nome do produto ou o range de preço.");
        }

        Pageable pageable = PageRequest.of(page, size);

        if (product != null && !product.isBlank()) {
            return produtoRepository.findByProductContainingIgnoreCase(product, pageable);
        }

        // Consulta por range de preço (assumindo que o campo price é String no formato "$X.XX")
        BigDecimal min = new BigDecimal(minPrice.replace("$", ""));
        BigDecimal max = new BigDecimal(maxPrice.replace("$", ""));

        Page<Produto> all = produtoRepository.findAll(pageable);
        List<Produto> filtrados = all.getContent().stream()
                .filter(p -> {
                    try {
                        BigDecimal price = new BigDecimal(p.getPrice().replace("$", ""));
                        return price.compareTo(min) >= 0 && price.compareTo(max) <= 0;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());

        return new PageImpl<>(filtrados, pageable, filtrados.size());
    }

    public Produto inserirProduto(Produto produto) {
        if (produtoExiste(produto)) {
            throw new IllegalArgumentException("Já existe um produto com o mesmo product e type.");
        }
        return produtoRepository.save(produto);
    }
}