package com.andregoncales;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ProdutoPersistenceService {

    private final IProdutoRepository produtoRepository;

    public ProdutoPersistenceService(IProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
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
                    boolean exists = produtoRepository.existsByProductAndType(produto.getProduct(), produto.getType());
                    if (!exists) {
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
}