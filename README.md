# Funções implementadas
1. Efetuar, renovar e finalizar emprestimos;
2. Relatório sobre todos os empréstimos em andamento;
3. Relatório sobre todos os alunos que já emprestaram um determinado equipamento;
4. Relatório sobre todos os equipamentos emprestados por um determinado aluno; 
5. Relatório sobre empréstimos em andamento e que estão vencidos, ou seja, o aluno ainda não devolveuo equipamento;

# Notas
1. O driver de conexão Java utilizado é o "mysql-connector-java" versao 8.0.18, este é compatível com o mysql Ver 14.14 Distrib 5.7.27;
2. A classe de conexão com o banco de dados irá conectar-se ao schema "mydb" através de um socket TCP com IP 127.0.0.1 porta 3306;
3. O usuário e senha do mysql é passado no início da execução do programa;
4. Não foi implementado uma entidade para "kits" estes são representados apenas como um conjunto de equipamentos;
5. Para utilização do programa é importante possuir as informações de matrícula dos alunos e identificadores dos equipamentos;
6. O arquivo para importação do banco de dados encontra-se no diretório raiz do projeto [aqui](https://github.com/GuilhermeRoque/MysqlJava/blob/master/bcd.sql).

**Modelagem banco de dados**
![diagrama](https://github.com/GuilhermeRoque/MysqlJava/blob/master/bcd_diagram.png)
