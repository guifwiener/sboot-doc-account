# Documento de Ordem de Crédito - Conta

<p align="center">
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge" #vitrinedev/>
</p>

## Descrição do projeto

Projeto em desenvolvimento para fins de estudo autônomos. O processo consiste em dois microsserviços apartados: bank-transfer e bank-order.

Bank-Transfer é responsável por solicitar a transação e Bank-Order é responsável por verificar a conta e aprovar/reprovar a transação.

## Funcionalidades

- `Funcionalidade 1:` Servir como listener de fila de RabbitMQ
- `Funcionalidade 2:` Salvar log em banco de dados PostgreSQL

## Ferramentas utilizadas

- `Java Spring Boot`
- `PostgreSQL`
- `RabbitMQ`
- `Docker Compose`

## Requisitos

Para execução deste projeto, é necessário a instalação de:

- `Java 19+`
- `Maven`
- `Docker Compose`

## Instalação

Para instalação dos módulos, certifique-se de que maven esteja instalado e utilize o comando:

```bash
    mvn clean install
```

## Execução

É necessário primeiro executar as instâncias do docker. Certifique-se de que docker esteja instalado em sua máquina e execute:

```bash
    docker-compose up
```

Dada a execução do container, é possível então executar o microsserviço.
Com a execução do serviço, caso o componente doc-transfer envie uma mensagem, doc-account será ativado.