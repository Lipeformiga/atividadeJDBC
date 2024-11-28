import java.util.Scanner;

public class Main {

    private static final BancoEvento crudEvento = new BancoEvento();
    private static final BancoParticipante crudParticipante = new BancoParticipante();
    private static final BancoInscricao crudInscricao = new BancoInscricao();
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        int opcao;
        while (true){
            System.out.println(opcoes());
            opcao = sc.nextInt();
            switchzada(opcao);
        }
    }

    public static String opcoes(){
        return """
                1 - Adicionar evento
                2 - pesquisar evento ( nome )
                3 - Remover evento
                4 - Adicionar participante 
                5 - Pesquisar participante ( email )
                6 - Remover participante
                7 - Inscrever participante em evento
                8 - Pesquisar inscrições de participante ( nome )
                9 - Remover inscrição
                0 - SAIR
                """;
    }
    public static void switchzada(int opcao){
        switch (opcao){
            case 1:
                addEvento();
                break;
            case 2:
                buscarEvento();
                break;
            case 3:
                removerEvento();
                break;
            case 4:
                addParticipante();
                break;
            case 5:
                buscarParticipante();
                break;
            case 6:
                removerParticipante();
                break;
            case 7:
                adicionarInscricao();
                break;
            case 8:
                buscarInscricaoPorNome();
                break;
            case 9:
                removerInscricao();
                break;
            case 0:
                System.out.println("Até mais!");
                System.exit(0);
        }
    }

    public static void addEvento(){
        System.out.println("Nome do evento:");
        String nome = sc.next();
        System.out.println("Local do evento:");
        String local = sc.next();
        System.out.println("Data do evento:");
        String data = sc.next();
        System.out.println("Descrição do evento:");
        String descricao = sc.next();
        crudEvento.adicionarEvento(new Evento(nome,local,data,descricao));
    }
    public static void buscarEvento(){
        System.out.println("Escreva o nome do evento que deseja pesquisar: ");
        String nome = sc.next();
        System.out.println(crudEvento.buscarEventoPorNome(nome));
    }

    public static void removerEvento(){
        System.out.println("Escreva o ID do evento que deseja excluir:");
        int id = sc.nextInt();
        crudEvento.removerEvento(id);
    }

    public static void addParticipante(){

        System.out.println("Nome do participante:");
        String nome = sc.next();
        System.out.println("Email do participante:");
        String email = sc.next();

        crudParticipante.adicionarParticipante(new Participante(nome,email));
    }
    public static void buscarParticipante(){
        System.out.println("Digite o email do participante:");
        String email = sc.next();
        System.out.println(crudParticipante.buscarparticipantePorEmail(email));

    }

    public static void removerParticipante(){
        System.out.println("Escreva o ID do participante que deseja remover:");
        int id = sc.nextInt();
        crudParticipante.removerParticipante(id);
    }
    public static void adicionarInscricao(){
        System.out.println("Digite o ID do evento que deseja adicionar o participante:");
        int idE = sc.nextInt();
        System.out.println("Digite o ID do participante que deseja adicionar:");
        int idP = sc.nextInt();

        crudInscricao.inscreverParticipante(idE,idP);
    }
    public static void removerInscricao(){
        System.out.println("Digite o ID da inscricao que deseja remover:");
        int id = sc.nextInt();
        crudInscricao.removerInscricao(id);
    }

    public static void buscarInscricaoPorNome(){
        System.out.println("Escreva o nome do participante que deseja ver as inscrições:");
        String nome = sc.next();
        System.out.println(crudInscricao.visualizarInscricoesNome(nome));
    }
}