import dao.AlunoDAO;
import dao.CursoDAO;
import dao.EmprestimoDAO;
import dao.EquipamentoDAO;
import db.ConnectionFactory;
import pojo.Aluno;
import pojo.Curso;
import pojo.Emprestimo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private static Scanner in = new Scanner(System.in);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static void atualiza(){
        AlunoDAO daoA = new AlunoDAO();
        EmprestimoDAO daoE = new EmprestimoDAO();
        List<Aluno> alunos = daoA.buscaTodosAlunos();
        LocalDate dataAgora = LocalDate.now();
        alunos.forEach(aluno -> {
            List<Emprestimo> emprestimos = daoE.EmprestimoEstadoAluno(aluno.getIdAluno(), false);
            emprestimos.forEach(emprestimo -> {
                LocalDate dataAgendadaEntrega = LocalDate.parse(emprestimo.getDataAgendadaEntrega(), formatter);
                switch (emprestimo.getMotivo()){
                    case 1:
                        if(dataAgendadaEntrega.isAfter(dataAgora.plusDays(15))){
                            aluno.setEstado(false);
                            daoA.atualizaAluno(aluno);
                        }
                        break;
                    case 2:
                        CursoDAO daoC = new CursoDAO();
                        Curso curso = daoC.buscaCurso(aluno.getIdCurso());
                        LocalDate fimLetivo =  LocalDate.parse(curso.getFimLetivo(), formatter);

                        if(dataAgendadaEntrega.isAfter(fimLetivo)){
                            aluno.setEstado(false);
                            daoA.atualizaAluno(aluno);
                        }
                    default:
                        break;
                }
            });
        });
    }

    public static void escolha1(){
        atualiza();
        EmprestimoDAO daoE = new EmprestimoDAO();
        AlunoDAO daoA = new AlunoDAO();
        EquipamentoDAO daoEq = new EquipamentoDAO();
        List<Aluno> alunos = daoA.buscaTodosAlunos();

        alunos.forEach(aluno -> {
            aluno.setEmprestimos(daoE.EmprestimoEstadoAluno(aluno.getIdAluno(),false));
            if(aluno.getEmprestimos().size() > 0){
                System.out.println(aluno);
                aluno.getEmprestimos().forEach(emprestimo -> {
                    System.out.println(emprestimo);
                    emprestimo.setEquipamentos(daoEq.buscaEquipamentoEmprestado(emprestimo.getIdEmprestimo(),aluno.getIdAluno()));
                    emprestimo.getEquipamentos().forEach(equipamento -> {
                        System.out.println(equipamento);
                    });
                });
                System.out.print('\n');
            }
        });

    }

    public static void escolha2() {
        atualiza();
        EmprestimoDAO daoE = new EmprestimoDAO();
        AlunoDAO daoA = new AlunoDAO();
        EquipamentoDAO daoEq = new EquipamentoDAO();

        System.out.println("Matricula:");
        int idAluno =  Integer.valueOf(in.nextLine());
        Aluno aluno = daoA.buscaAluno(idAluno);
        if (aluno.isEstado() == false){
            System.err.println("Aluno bloqueado!");
            return;
        }

        if(daoE.EmprestimoEstadoAluno(idAluno,false).size() > 0){
            System.err.println("Aluno ja possui emprestimo aberto");
            return;
        }

        System.out.println("Finalidade:\n" +
                "\n" +
                "(1) - Ensino\n" +
                "(2) - Pesquisa, Extensão ou TCC");

        int motivo = Integer.valueOf(in.nextLine());
        System.out.print("\n");

        System.out.println("Data de Entrega: ");
        String dataString = in.nextLine();

        LocalDate dataEntrega = LocalDate.parse(dataString, formatter);
        LocalDate dataAgora = LocalDate.now();

        int idEmprestimo = 0;
        switch (motivo){
            case 1:
                if(dataEntrega.isAfter(dataAgora.plusDays(15))){
                    System.err.println("Erro: Data posterior ao permitido!");
                    return;
                }
                idEmprestimo = daoE.criaEmprestimo(new Emprestimo(idAluno,dataAgora.toString(),dataString,dataAgora.toString(),motivo,0));
                break;
            case 2:
                CursoDAO daoC = new CursoDAO();
                Curso curso = daoC.buscaCurso(aluno.getIdCurso());
                LocalDate fimLetivo =  LocalDate.parse(curso.getFimLetivo(), formatter);

                if(dataEntrega.isAfter(fimLetivo)){
                    System.err.println("Erro: Data posterior ao permitido!");
                    return;
                }
                idEmprestimo = daoE.criaEmprestimo(new Emprestimo(idAluno,dataAgora.toString(),dataString,dataAgora.toString(),motivo,0));
                break;
        }

        while(true){
            System.out.println("Equipamento:     (0 to quit)");
            int idEquipamento = Integer.valueOf(in.nextLine());
            if (idEquipamento ==0){
                break;
            }
            else{
                if(daoEq.verificaEquipamentoEmprestado(idEquipamento) == false){
                    daoEq.criaEmprestimoEquipamento(idEquipamento,idEmprestimo,idAluno);
                }else {
                    System.err.println("Equipamento ja esta emprestado");
                }

            }
        }

    }

    public static void escolha3() {
        LocalDate dataAgora = LocalDate.now();

        System.out.println("Matricula:");
        int idAluno =  Integer.valueOf(in.nextLine());
        EmprestimoDAO daoE = new EmprestimoDAO();
        List<Emprestimo> emprestimos = daoE.EmprestimoEstadoAluno(idAluno, false);
        emprestimos.get(0).setDataEntrega(dataAgora.toString());
        daoE.atualizaEmprestimo(emprestimos.get(0));

        LocalDate dataEntrega = LocalDate.parse(emprestimos.get(0).getDataEntrega(), formatter);
        if(dataAgora.isAfter(dataEntrega)){
            AlunoDAO daoA = new AlunoDAO();
            Aluno aluno = daoA.buscaAluno(idAluno);
            aluno.setEstado(false);
            daoA.atualizaAluno(aluno);
        }


    }

    public static void escolha4() {
        atualiza();
        EmprestimoDAO daoE = new EmprestimoDAO();
        AlunoDAO daoA = new AlunoDAO();

        System.out.println("Matricula:");
        int idAluno =  Integer.valueOf(in.nextLine());

        if (daoA.buscaAluno(idAluno).isEstado() == false){
            System.err.println("Aluno bloqueado!");
            return;
        }

        List<Emprestimo> emprestimos = daoE.EmprestimoEstadoAluno(idAluno, false);
        if(emprestimos.size() == 0){
            System.err.println("Aluno não possui emprestimo aberto");
            return;
        }

        int motivo = emprestimos.get(0).getMotivo();
        if(emprestimos.get(0).getCount() >= 3){
            System.err.println("Numero máximo de renovações alcançado");
            return;
        }
        System.out.print("\n");

        System.out.println("Data de Entrega: ");
        String dataString = in.nextLine();

        LocalDate dataEntrega = LocalDate.parse(dataString, formatter);
        LocalDate dataAgora = LocalDate.now();

        switch (motivo){
            case 1:
                if(dataEntrega.isAfter(dataAgora.plusDays(15))){
                    System.err.println("Erro: Data posterior ao permitido!");
                    return;
                }
                emprestimos.get(0).setDataAgendadaEntrega(dataString);
                emprestimos.get(0).setCount(emprestimos.get(0).getCount() +1);
                daoE.atualizaEmprestimo(emprestimos.get(0));
                break;
            case 2:
                CursoDAO daoC = new CursoDAO();
                Curso curso = daoC.buscaCurso(1);
                LocalDate fimLetivo =  LocalDate.parse(curso.getFimLetivo(), formatter);

                if(dataEntrega.isAfter(fimLetivo)){
                    System.err.println("Erro: Data posterior ao permitido!");
                    return;
                }
                emprestimos.get(0).setDataAgendadaEntrega(dataString);
                emprestimos.get(0).setCount(emprestimos.get(0).getCount() +1);
                daoE.atualizaEmprestimo(emprestimos.get(0));
                break;
            default:
                break;
        }
    }

    public static void escolha5() {
        AlunoDAO daoA = new AlunoDAO();

        System.out.println("Equipamento:");
        int idEquipamento = Integer.valueOf(in.nextLine());

        List<Aluno> alunos = daoA.buscaAlunosEquipamento(idEquipamento);

        alunos.forEach(aluno -> {
            System.out.println(aluno);
            aluno.getEmprestimos().forEach(emprestimo -> {
                System.out.println(emprestimo);
            });
            System.out.print('\n');
        });

    }

    public static void escolha6() {
        AlunoDAO daoA = new AlunoDAO();

        System.out.println("Matricula:");
        int idAluno = Integer.valueOf(in.nextLine());

        List<Emprestimo> emprestimos = daoA.buscaEquipamentosAluno(idAluno);
        emprestimos.forEach(emprestimo -> {
            System.out.println(emprestimo);
            emprestimo.getEquipamentos().forEach(equipamento -> {
                System.out.println(equipamento);
            });
        });

    }

    public static void escolha7() {
        atualiza();
        AlunoDAO daoA = new AlunoDAO();
        EmprestimoDAO daoE = new EmprestimoDAO();
        List<Aluno> alunos = daoA.buscaTodosAlunosInvalidos();

        if(alunos.size() >0){
            alunos.forEach(aluno -> {
                List<Emprestimo> emprestimos = daoE.EmprestimoEstadoAluno(aluno.getIdAluno(), false);
                if(emprestimos.size() >0){
                    System.out.println(aluno);
                    System.out.println(emprestimos.get(0));
                    emprestimos.get(0).getEquipamentos().forEach(equipamento -> System.out.println(equipamento));
                }
            });
        }
        else {
            System.out.println("Não há emprestimos atrasados\n");
        }
    }


    public static void main(String[] args) {
        System.out.println("Escreva usuario para conectar no mysql: ");
        ConnectionFactory.user = in.nextLine();
        System.out.println("Escreva senha para conectar no mysql: ");
        ConnectionFactory.pass = in.nextLine();
        System.out.println("\n\n");

        while (true){
            System.out.println("Bem vindo!\n" +
                    "\n" +
                    "Escolha uma opção:\n" +
                    "\n" +
                    "(1) - Consultar emprestimos em adamento\n" +
                    "(2) - Realizar um emprestimo\n" +
                    "(3) - Devolver um emprestimo\n" +
                    "(4) - Renovar um emprestimo\n" +
                    "(5) - Consultar histórico equipamento\n" +
                    "(6) - Consultar histórico aluno\n" +
                    "(7) - Consultar emprestimos em atraso");

            int escolha = Integer.valueOf(in.nextLine());

            System.out.print("\n");

            switch (escolha){
                case 1:
                    escolha1();
                    break;
                case 2:
                    escolha2();
                    break;
                case 3:
                    escolha3();
                    break;
                case 4:
                    escolha4();
                    break;
                case 5:
                    escolha5();
                    break;
                case 6:
                    escolha6();
                    break;
                case 7:
                    escolha7();
                    break;
                default:
                    break;
            }
        }
    }
}
