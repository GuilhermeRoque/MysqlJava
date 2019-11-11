# Sistema para empréstimos de equipamentos
O curso de Engenharia de Telecomunicações do campus São José do IFSC tem como focoo desenvolvimento de produtos de telecomunicações, envolvendo assim atividades de desenvol-vimento de software e hardware.O campus possui diversos equipamentos que podem ser emprestados aos alunos, de formaque esses possam assim desenvolver atividades de disciplinas, projetos de pesquisa, extensãoou mesmo o trabalho de conclusão de curso.Os equipamentos a serem emprestados podem ser constituídos de um único componente,por exemplo, um cabo HDMI para DVI, ou por vários componentes, como por exemplo, um kitarduino que além da placa Arduino, tem um cabo USB, uma miniprotoboarde um conjunto com10 jumpers macho-macho.O empréstimo pode ser feito por qualquer aluno que esteja com a matrícula regular e que nãopossua débito com esse sistema de empréstimos.  Isto é, ao aluno é dado o direito de fazer umempréstimo por vez, mesmo que nesse empréstimo estejam registrados vários equipamentosoukits.   O  prazo  máximo  para  o  empréstimo  depende  da  atividade  na  qual  o  aluno  usará  oequipamento.  Por exemplo, atividades de ensino o prazo máximo é de 15 dias.  Atividades depesquisa, extensão ou TCC o prazo máximo é até o último dia letivo do semestre.Ao aluno é dada a possibilidade de renovar um empréstimo somente antes do mesmo ven-cer.  O empréstimo poderá ser renovado por no máximo 3 vezes e somente se não houver umareserva para o mesmo, feita por um outro aluno.  Para atividades de ensino, a data de entrega,mesmo para as renovações, não deve ultrapassar o último dia letivo do semestre. Para as ativi-dades de pesquisa, extensão e TCC o aluno poderá fazer uma renovação de forma que continuecom o item emprestado durante as férias acadêmicas.Um aluno que não devolveu o equipamento antes do vencimento será penalizado da seguinteforma.   O aluno ficará impossibilitado de emprestar equipamentos pelo período de 3 vezes onúmero de dias de atraso do último empréstimo. Por exemplo, o aluno devolveu um equipamento5 dias após a data estipulada para devolução. Sendo assim, esse aluno só poderá fazer um novoempréstimo após 15 dias

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
