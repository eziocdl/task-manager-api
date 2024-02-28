# API de Gerenciamento de Tarefas

Este projeto consiste em uma API REST para gerenciamento de tarefas, desenvolvida em Java utilizando o framework Spring Boot. A API permite criar, atualizar, listar e excluir tarefas, além de realizar operações relacionadas a pessoas e departamentos. O banco de dados utilizado é o PostgreSQL, e foram implementados testes unitários com o Mockito.

## Estrutura do Projeto

```

src
└── main
    └── java
        └── com
            └── example
                └── taskmanager
                    ├── controller
                    │   ├── DepartamentoController.java
                    │   ├── PessoaController.java
                    │   └── TarefaController.java
                    ├── dao
                    │   ├── DepartamentoRepository.java
                    │   ├── PessoaRepository.java
                    │   └── TarefaRepository.java
                    ├── DTOs
                    │   ├── DepartamentoDTO.java
                    │   ├── GetPessoaDTO.java
                    │   └── TarefaDTO.java
                    ├── model
                    │   ├── Departamento.java
                    │   ├── Pessoa.java
                    │   └── Tarefa.java
                    ├── service
                    │   ├── DepartamentoService.java
                    │   ├── PessoaService.java
                    │   └── TarefaService.java
                    ├── advice
                    │   ├── DepartamentoControllerAdvice.java
                    │   ├── PessoaControllerAdvice.java
                    │   └── TarefaControllerAdvice.java
                    └── TaskManagerApiApplication.java
```


### Controllers

- `DepartamentoController`: Responsável por lidar com as requisições relacionadas a departamentos.
- `PessoaController`: Responsável por lidar com as requisições relacionadas a pessoas.
- `TarefaController`: Responsável por lidar com as requisições relacionadas a tarefas.

### DAOs (Data Access Objects)

- `DepartamentoRepository`: Interface de acesso aos dados dos departamentos no banco de dados.
- `PessoaRepository`: Interface de acesso aos dados das pessoas no banco de dados.
- `TarefaRepository`: Interface de acesso aos dados das tarefas no banco de dados.

### DTOs (Data Transfer Objects)

- `DepartamentoDTO`: Objeto de transferência de dados para departamentos.
- `GetPessoaDTO`: Objeto de transferência de dados para informações de pessoas.
- `TarefaDTO`: Objeto de transferência de dados para tarefas.

### Model

- `Departamento`: Representa um departamento.
- `Pessoa`: Representa uma pessoa.
- `Tarefa`: Representa uma tarefa.

### Service

- `DepartamentoService`: Responsável pela lógica de negócios relacionada a departamentos.
- `PessoaService`: Responsável pela lógica de negócios relacionada a pessoas.
- `TarefaService`: Responsável pela lógica de negócios relacionada a tarefas.

### Advice

- `DepartamentoControllerAdvice`: Trata os erros relacionados ao controller de departamentos.
- `PessoaControllerAdvice`: Trata os erros relacionados ao controller de pessoas.
- `TarefaControllerAdvice`: Trata os erros relacionados ao controller de tarefas.

## Testes

Os testes unitários são implementados em cada classe de serviço (*Service) utilizando o framework Mockito para simular o comportamento de dependências e garantir o funcionamento adequado das operações.

## Funcionalidades

- Criação, atualização, listagem e exclusão de departamentos, pessoas e tarefas.
- Alocar tarefas para pessoas.
- Finalizar tarefas.
- Listar tarefas pendentes.
- Cálculo de média de gastos com pessoas em um determinado período.

## Swagger

O Swagger é uma ferramenta poderosa para documentar e testar APIs REST. Com o Swagger, podemos visualizar e interagir com os endpoints da API de uma forma intuitiva e amigável.

Para acessar a documentação Swagger da API deste projeto, siga os passos abaixo:

1. Certifique-se de que a aplicação esteja em execução localmente.

2. Abra um navegador da web e insira o seguinte URL na barra de endereços:
http://localhost:8080/swagger-ui/index.html

3. A estrutura irá aparecer conforme a imagem:

<img width="952" alt="Captura de Tela 2024-02-28 às 09 57 38" src="https://github.com/eziocdl/task-manager-api/assets/25807617/386073d3-e606-4206-8c5d-3d6810f2dd90">


## Observações

- Certifique-se de configurar corretamente o banco de dados PostgreSQL antes de executar a aplicação.
- Os testes unitários garantem a integridade das operações da API.
- O tratamento de erros nos controllers assegura uma experiência consistente para os clientes da API.
