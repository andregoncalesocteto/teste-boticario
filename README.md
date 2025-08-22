# Estrutura do projeto

teste-boticario/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/andregoncales/
│   │   │       ├── Application.java               # Classe principal - Inicia a aplicação Spring Boot
│   │   │       ├── model/
│   │   │       │   ├── Produto.java               # Entidade JPA - Representa os dados no banco
│   │   │       │   └── ProdutoMapper.java         # Mapper entre DTO e entidade
│   │   │       ├── DTO/
│   │   │       │   ├── ProductDataDTO.java        # DTO para dados do produto (JSON)
│   │   │       │   └── ProductListDTO.java        # DTO para lista de produtos (JSON)
│   │   │       ├── repository/
│   │   │       │   └── IProdutoRepository.java    # Interface de acesso ao banco
│   │   │       ├── service/
│   │   │       │   ├── ProdutoPersistenceService.java # Lógica de negócio e validação
│   │   │       │   └── JsonReaderService.java     # Leitura automática dos arquivos JSON
│   │   │       └── controller/
│   │   │           └── ProdutoController.java     # Endpoints REST (consulta e inserção)
│   │   └── resources/
│   │       ├── application.properties             # Configurações da aplicação
│   │       └── data/
│   │           ├── data_1.json
│   │           ├── data_2.json
│   │           ├── data_3.json
│   │           └── data_4.json
├── pom.xml                                        # Dependências Maven
├── docker-compose.yml                             # Orquestração dos containers
├── .gitignore                                     # Ignora arquivos/desnecessários no git
└── README.md                                      # Instruções de uso e setup

# Observações

- O console do H2 web estará acessível em
  http://localhost:81
  JDBC URL: jdbc:h2:tcp://h2db:1521/opt/h2-data/teste-boticario
  User Name: sa
  Password: teste

- Para buildar e executar a aplicação:
  ```
  docker-compose up --build
  ```

- Para acessar o bash do container:
  ```
  docker exec -it teste-boticario-app bash
  ```

- Para recompilar usando Maven dentro do container:
  ```
  mvn compile
  ```

# Endpoints

## Consulta Produtos (GET)
- `http://localhost:8080/produtos`
- `http://localhost:8080/produtos?product=ALG1`
- `http://localhost:8080/produtos?minPrice=0.67&maxPrice=0.99`

  - Pelo menos um filtro é obrigatório.
  - Consulta paginada.
  - Range de preço considera o campo price como String no formato `$X.XX`.

## Cadastrar Produtos (POST)
- `http://localhost:8080/produtos`

  Payload de exemplo:
  ```json
  {
    "product": "ALG2",
    "quantity": 10,
    "price": "$49.50",
    "type": "G",
    "industry": "Roupas",
    "origin": "SP"
  }
  ```
