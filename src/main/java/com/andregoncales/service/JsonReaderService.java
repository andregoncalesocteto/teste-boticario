package com.andregoncales;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class JsonReaderService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ProdutoPersistenceService persistenceService;

    public JsonReaderService(ProdutoPersistenceService persistenceService) {
        this.persistenceService = persistenceService;
    }

    public void loadAllJsonFiles() {
        System.out.println("*******************************************");
        System.out.println("JsonReaderService loadAllJsonFiles");
        System.out.println("*******************************************");

        List<String> files = List.of("data_1.json", "data_2.json", "data_3.json", "data_4.json");

        List<CompletableFuture<ProductListDTO>> futures = files.stream()
            .map(file -> CompletableFuture.supplyAsync(() -> readJson(file)))
            .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("*******************************************");
        System.out.println("INICIO CARGA");
        long start = System.currentTimeMillis();

        futures.forEach(future -> {
            ProductListDTO produtos = future.join();
            persistenceService.salvarTodos(produtos.getData().stream()
                .map(ProdutoMapper::toEntity)
                .toList()
            );
            System.out.println("Carregados: " + produtos.getData().size());
        });

        long end = System.currentTimeMillis();
        long durationMillis = end - start;
        long minutes = durationMillis / 60000;
        long seconds = (durationMillis % 60000) / 1000;
        System.out.printf("Tempo de execução: %02d:%02d%n", minutes, seconds);
        System.out.println("FIM CARGA");
        System.out.println("*******************************************");
    }

    private ProductListDTO readJson(String filename) {
        try (InputStream inputStream = new ClassPathResource("data/" + filename).getInputStream()) {
            return objectMapper.readValue(inputStream, new TypeReference<ProductListDTO>() {});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler " + filename, e);
        }
    }
}