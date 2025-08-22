# Estrutura do projeto

seu-projeto/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/andregoncales/
│   │   │       ├── Application.java               # Classe principal - Inicia a aplicação Spring Boot
│   │   │       ├── model/
│   │   │       │   └── Registro.java              # Entidade JPA - Representa os dados no banco
│   │   │       ├── repository/
│   │   │       │   └── RegistroRepository.java    # Interface de acesso ao banco - Interface para salvar e consultar registros
│   │   │       ├── service/
│   │   │       │   └── RegistroService.java       # Lógica de negócio e validação - Valida e processa os dados dos arquivos e inserções manuais
│   │   │       ├── controller/
│   │   │       │   └── RegistroController.java    # Endpoints REST (consulta e inserção) - Permite consultar e inserir registros via HTTP
│   │   │       └── loader/
│   │   │           └── DataLoader.java            # Leitura automática dos arquivos - Lê os arquivos JSON ao iniciar a aplicação
│   │   └── resources/
│   │       ├── application.properties             # Configurações da aplicação - Define banco de dados, porta, etc.
│   │       └── data/                              # Arquivos de dados - Dados que serão lidos automaticamente
│   │           ├── data_1.json
│   │           ├── data_2.json
│   │           ├── data_3.json
│   │           └── data_4.json
├── pom.xml                                        # Dependências Maven - Define bibliotecas como Spring Boot, H2, Jackson, etc.
└── README.md                                      # Instruções de uso e setup

# Para buildar e executar a aplicação.
> docker-compose up --build

# Qualquer alteração que se faça no código para ter efeito no container

## Acessar o bash do container
> docker exec -it teste-boticario bash

## Recompilar usando Maven
> mvn compile

