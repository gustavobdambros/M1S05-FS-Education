import java.util.Scanner;

public class MainJogo {

    // [M1S05] Exercício 8 - Main e loop principal
    // Método que chama um menu para interação com o usuário após o fim de cada rodada dos jogos
    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        // Opções ao usuário
        System.out.println("\nO que você deseja fazer agora?\n1. Ver ranking completo\n2. Ver top 10\n3. Jogar Novamente\n0. Encerrar o Jogo");
        System.out.print("Minha escolha: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                // Atualiza a lista de jogadores e exibe o ranking completo
                Jogador.atualizarLista();
                Jogador.exibirRanking(true, Jogo.getJogadorAtual());
                break;
            case 2:
                // Atualiza a lista de jogadores e exibe o top 10
                Jogador.atualizarLista();
                Jogador.exibirRanking(false, Jogo.getJogadorAtual());
                break;
            case 3:
                System.out.println("\nRodando novamente...");
                return; // Roda novamente, pedindo ao usuário que jogo ele deseja jogar
            case 0:
                System.out.println("\nVocê saiu!");
                System.exit(0); // Encerra o programa
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
        menu(); // Chamada recursiva para o menu
    }

    // Main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Seta alguns jogadores na lista ao iniciar
        Jogador.setupJogadores();
        System.out.println("----------- Bem-vindo à lista de jogos -----------");

        // Loop infinito para a escolha dos jogos
        while (true) {
            // Escolha dos jogos
            System.out.println("Digite o jogo que deseja jogar:\n1. Pedra, Papel e Tesoura\n2. Adivinhar Número");
            System.out.print("Minha escolha: ");

            int novoJogoAtual = scanner.nextInt();

            switch (novoJogoAtual) {
                case 1:
                    // Pedra, Papel e Tesoura
                    System.out.println("---------- Bem-vindo ao jogo de Pedra, Papel e Tesoura! ----------\n");
                    Jogador jogadorJogo1 = Jogador.criarOuEscolherJogador();
                    Jogo.setJogadorAtual(jogadorJogo1);
                    Jogo.jogar(jogadorJogo1);
                    break;
                case 2:
                    // Jogo adivinhar número
                    System.out.println("---------- Bem-vindo ao jogo de Adivinhar Número! ----------\n");
                    Jogador jogadorJogo2 = Jogador.criarOuEscolherJogador();
                    Jogo.setJogadorAtual(jogadorJogo2);

                    // // [M1S05] Exercício 7 - Seta o número máximo para o jogo ao iniciar (console)
                    System.out.print("Digite o número máximo para o jogo (Início em 0): ");
                    int numMax = scanner.nextInt();
                    Jogo.jogar(numMax);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.\n");
                    continue; // Retorna ao início do loop
            }
            menu(); // Chama o menu após cada jogo
        }
    }
}