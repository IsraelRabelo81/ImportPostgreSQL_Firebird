# 🚀 ImportPostgreSQL_Firebird

Sistema desktop desenvolvido em Java com JavaFX para importação de dados de um banco PostgreSQL para um banco Firebird.

---

## 📌 Objetivo

Este sistema tem como objetivo facilitar a migração e sincronização de dados entre bases distintas, permitindo ao usuário configurar conexões e selecionar quais entidades deseja importar.

---

## 🛠️ Tecnologias utilizadas

* Java 17 (LTS)
* JavaFX
* Maven
* PostgreSQL
* Firebird

---

## 🖥️ Funcionalidades

* Configuração de conexão com PostgreSQL
* Configuração de conexão com Firebird
* Persistência de configuração via arquivo `.ini`
* Interface gráfica simples e intuitiva
* Seleção de dados para importação:

  * Produtos
  * Fornecedores
  * Clientes
* Preparado para expansão de novas entidades

---

## ⚙️ Configuração

Ao iniciar o sistema pela primeira vez:

* Um arquivo `config.ini` será criado automaticamente
* As configurações serão carregadas na tela
* O usuário pode alterar e salvar os dados

### Exemplo do arquivo `config.ini`:

```ini
[POSTGRES]
host=localhost
port=5433
database=
user=postgres
password=

[FIREBIRD]
path=C:/dados/DB_SGE.FDB
user=SYSDBA
password=masterkey
```

---

## ▶️ Como executar o projeto

### Pré-requisitos

* Java 17 instalado
* Maven configurado

### Executar via terminal:

```bash
mvn javafx:run
```

---

## 📁 Estrutura do projeto

```
src/
 ├── main/
 │   ├── java/
 │   │   ├── app/          # Classe principal
 │   │   ├── ui/           # Controllers JavaFX
 │   │   └── config/       # Leitura e escrita do config.ini
 │   └── resources/
 │       └── MainView.fxml
```

---

## 🔄 Fluxo do sistema

1. Sistema inicia
2. Verifica se existe `config.ini`
3. Caso não exista, cria automaticamente
4. Carrega configurações na interface
5. Usuário pode alterar os dados
6. Sistema salva as alterações
7. Importação é executada conforme seleção

---

## 🚧 Status do projeto

🟡 Em desenvolvimento

* [x] Interface gráfica
* [x] Leitura e escrita de configuração
* [ ] Conexão com PostgreSQL
* [ ] Conexão com Firebird
* [ ] Implementação da importação de dados

---

## 📌 Próximos passos

* Implementar teste de conexão com os bancos
* Desenvolver camada de serviço para importação
* Criar logs de execução
* Melhorar feedback visual para o usuário

---

## 👨‍💻 Autor

Desenvolvido por Israel Rabelo
