<h1 align="center">PEOPLE - API</h1>

<p align="center">
  <a href="https://github.com/jericgs/sosmovel/issues">
    <img src="https://img.shields.io/github/issues/jericgs/sosmovel?color=red" alt="GitHub issues." />
  </a>
  <a href="https://github.com/jericgs/sosmovel/network">
    <img src="https://img.shields.io/github/forks/jericgs/sosmovel?color=red" alt="GitHub forks." />
  </a>
  <a href="https://github.com/jericgs/sosmovel/stargazers">
    <img src="https://img.shields.io/github/stars/jericgs/sosmovel?color=red" alt="GitHub stars." />
  </a>
</p>

## Descrição

Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:

- Criar uma pessoa
- Editar uma pessoa
- Consultar uma pessoa
- Listar pessoas
- Criar endereço para pessoa
- Remover endereço da pessoa
- Listar endereços da pessoa
- Poder informar qual endereço é o principal da pessoa


Uma Pessoa deve ter os seguintes campos:

- Nome
- Data de nascimento
- Endereço:
  -> Logradouro
  -> CEP
  -> Número
  -> Cidade

Diferencial

- Todas as respostas da API devem ser JSON
- Banco de dados MySQL
- Testes
- Swagger
- Clean Code

Requisitos Garantidos:

- Polimorfismo na classe "ApiExceptionHandler"
- Tratamento de exceção com try / catch na classe "AddressService"
- Método que retorne um valor (não void), com exemplo a classe "AddressAssembler"
