# 📋 To-Do List

Bem-vindo ao To-Do List! Esta é uma aplicação desenvolvida em Java e Javascript e que ajudará você a gerenciar suas tarefas de forma eficaz.

![image](https://github.com/victornathanael/todo-list-zg/assets/99601659/c6cac54c-12ae-41db-8758-2dff93165539)


**OBS:** No momento a aplicação possui backend e frontend independentes mas em breve serão conectados entre si. O frontend possui quase todas as funcionalidades presentes no backend, com exceção da listagem por categoria e prioriadade. 

Abaixo descrevo por completo os pontos principais já implementados no backend e disponiveis via console.

## ✨ Funcionalidades 

- **Criar Tarefa:** Adicione uma nova tarefa com nome, descrição, data de conclusão, nível de prioridade, categoria e status.
- **Listar Tarefas:** Veja uma lista de todas as tarefas juntamente com seus detalhes.
- **Listar Tarefas por Categoria, Prioridade ou Status:** Filtre as tarefas com base na categoria, nível de prioridade ou status.
- **Definir Status:** Atualize o status de uma tarefa para 'todo', 'doing' ou 'done'.
- **Excluir Tarefa:** Remova uma tarefa da lista.

## ▶️ Primeiros Passos

### 🔧 Pré-requisitos 

É necessário ter o Java Development Kit (JDK) instalado em sua máquina para compilar e executar o código Java.

### 🎯 Instalação 

1. Clone o repositório para o seu computador local:

```
git clone https://github.com/victornathanael/todo-list-zg.git
```

2. Navegue até o diretório do projeto:

```
cd todo-list-zg
```

## 🚀 Como Executar o Projeto

Para executar o projeto, você tem duas opções: através de uma IDE ou diretamente pelo terminal. Siga as instruções abaixo para ambas as abordagens.

### Executando através de uma IDE

Você pode usar uma IDE como o [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/) ou o [Eclipse](https://www.eclipse.org/downloads/) para executar o projeto mais facilmente.

### Executando pelo Terminal

Se você preferir executar o projeto pelo terminal, siga estas etapas:

1. Abra um terminal ou prompt de comando.
2. Navegue até o diretório raiz do projeto, onde estão os arquivos fonte.
3. Compile o código com o seguinte comando:

```
javac -d bin -cp src src/*.java
```

4. Execute o aplicativo:

```
java -cp bin App
```

## 💻 Uso 

Ao executar o aplicativo, você será apresentado a um menu com as seguintes opções:

1. **Criar Tarefa:** Permite a criação de tarefas, onde você pode fornecer as seguintes propriedades:
   - Nome da Tarefa
   - Descrição da Tarefa
   - Data de Conclusão (no formato dd/MM/yyyy)
   - Nível de Prioridade (1 - 5)
   - Categoria da Tarefa
   - Status da Tarefa (Todo, Doing ou Done)

2. **Listar Tarefas:** Exibe a lista de tarefas existentes, apresentando informações relevantes de cada tarefa.

3. **Listar Tarefas por Categoria, Prioridade ou Status:** Permite a listagem de tarefas com base em um critério escolhido. Após selecionar o critério, você pode escolher um valor específico para filtrar as tarefas.

4. **Setar o Status da Atividade:** Permite a alteração do status de uma tarefa pelo seu ID. O novo status pode ser definido como "Todo", "Doing" ou "Done".

5. **Deletar Tarefa:** Permite a exclusão de uma tarefa através do seu ID.

## 💡 Pontos Interessantes

- O programa utiliza um arquivo **.csv** como sua base de dados. Se o arquivo não existir, ele será criado automaticamente. A primeira linha do arquivo é reservada para um cabeçalho que descreve as colunas.

- A lista de tarefas é sempre mantida em ordem de acordo com a prioridade, que varia de 1 a 5.

- O arquivo **currentId.txt** é usado para atribuir IDs únicos às tarefas. Cada vez que uma tarefa é adicionada, o programa atualiza o próximo ID disponível no arquivo.

## 📋 Validações

- O programa possui validações para os campos inseridos:
  - A data de conclusão deve estar no formato **dd/MM/yyyy**.
  - O status da tarefa só pode ser "Todo", "Doing" ou "Done".
  - O nível de prioridade é validado para garantir que seja um número entre 1 e 5. Caso contrário, é ajustado automaticamente para 1 ou 5, conforme necessário.

**OBS:** Explore o programa e você encontrará outras validações que contribuem para uma experiência interativa e segura. Divirta-se usando o aplicativo e gerenciando suas tarefas de maneira eficaz!

## 🌹 Agradecimento

Obrigado por explorar este aplicativo de gerenciamento de tarefas! Espero que seja útil para você organizar suas atividades diárias.

Desenvolvido por [Victor Nathanael](https://www.linkedin.com/in/victornathanael/)

