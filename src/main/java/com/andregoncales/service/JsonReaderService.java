package com.andregoncales;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
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

        //obtém os arquivos de forma dinâmica
        List<String> files;
        try {
            Resource resource = new ClassPathResource("data");
            Path dataDir = resource.getFile().toPath();
            try (Stream<Path> paths = Files.list(dataDir)) {
                files = paths
                        .filter(Files::isRegularFile)
                        .map(path -> path.getFileName().toString())
                        .filter(name -> name.endsWith(".json"))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar arquivos JSON no diretório data", e);
        }

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