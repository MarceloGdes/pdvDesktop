# PDV Desktop

## 📖 Sobre o Projeto

O **PDV Desktop** é uma aplicação de desktop para sistema de Ponto de Venda (PDV), construída em Java Swing. Ele funciona como um cliente para uma API RESTful ([web api](https://github.com/MarceloGdes/pdv-rest-api)), permitindo aos usuários realizar operações de venda de forma gráfica e intuitiva.

A aplicação permite:
* Lançar novas vendas, buscando e adicionando clientes e produtos.
* Consultar o histórico de vendas com filtros por período.
* Gerar relatórios detalhados de vendas utilizando JasperReports.

Este projeto demonstra a integração entre uma aplicação desktop e um backend via API, utilizando `HttpURLConnection` para a comunicação e `Gson` para a serialização/desserialização de dados JSON. Adicionalmente, possui uma camada de acesso a dados (DAO) para interações diretas com um banco de dados PostgreSQL.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (configurado para a versão 21 no `pom.xml`).
* **Interface Gráfica:** Java Swing.
* **Comunicação com API:** `java.net.HttpURLConnection`.
* **Manipulação de JSON:** Google Gson.
* **Geração de Relatórios:** JasperReports.
* **Banco de Dados:** PostgreSQL (JDBC Driver).
* **Gerenciamento de Dependências:** Maven.

## ✨ Funcionalidades

A aplicação é dividida em algumas telas principais:

### Tela Principal
* Menu de acesso para as funcionalidades de "Cadastro" (Cliente e Produto - não implementado nos menus) e "Vendas".

### Lançar Venda (`ViewLancarVenda`)
* **Seleção de Cliente:** Permite buscar um cliente pelo nome na API. Ao pressionar `Enter`, uma tela de listagem é exibida para seleção.
* **Seleção de Produtos:** Permite buscar um produto pela descrição na API. Ao pressionar `Enter`, uma tela de listagem é exibida para seleção.
* **Adicionar Itens:** Adiciona produtos à venda com a quantidade especificada.
* **Cálculo de Total:** O valor total da venda é calculado e atualizado automaticamente.
* **Finalizar Venda:** Envia os dados da venda para a API, registrando a transação.

### Consultar Vendas (`ViewConsultarVendas`)
* **Filtro por Data:** Permite ao usuário filtrar as vendas realizadas em um período específico.
* **Listagem de Vendas:** Exibe as vendas em uma tabela com ID, nome do cliente, data e valor total.
* **Geração de Relatórios:**
    * **Relatório Geral:** Gera um relatório de todas as vendas dentro do período filtrado.
    * **Relatório Detalhado:** Gera um relatório detalhado de uma venda específica, que pode ser selecionada na tabela ou informada através de um ID.

[## ⚙️ Configuração e Execução

### Pré-requisitos

* Java Development Kit (JDK) 21 ou superior.
* Maven 3.x.
* PostgreSQL.
* **API de PDV em execução** (o backend para o qual este cliente foi projetado), acessível em `http://localhost:8080`.

### Passos para Execução

1.  **Clone o repositório:**
    ```sh
    git clone https://github.com/MarceloGdes/pdv-rest-api
2.  **Configure o Banco de Dados:**
    * Crie um banco de dados no PostgreSQL com o nome `pdvDesktop`.
    * Ajuste as credenciais de acesso (usuário e senha) no arquivo `src/main/java/conexaoDB/ConexaoPostgres.java` se necessário.
    * Execute o script `crirar_tabelas.sql` para criar a estrutura de tabelas no banco de dados.

3.  **Configure os Relatórios:**
    * Certifique-se de que os arquivos de relatório compilados (`.jasper`) estejam no diretório `relatorio/` na raiz do projeto para que possam ser encontrados em tempo de execução.

4.  **Execute a aplicação:**
    * Verifique se a API backend está em execução.
    * Execute a classe principal `com.mycompany.pdvdesktop.PDVDesktop.java` a partir da sua IDE, ou compile e execute via Maven.
    ```sh
    mvn clean install
    mvn exec:java
    ```
---

**Observação:** O projeto utiliza uma camada de `Service` que se comunica com a API e uma camada `DAO` que se comunica diretamente com o banco de dados. A funcionalidade de `insert` da Venda na `VendaService` demonstra uma complexa lógica de serialização com `Gson` e também uma interação com a camada DAO local para persistir os dados recebidos da API no banco de dados do cliente desktop.
