import java.util.Scanner;

// [M1S05] Exercício 6 - Crie o jogo
public class Jogo {

    // Atributos
    private String melhorJogador;
    private int numeroJogadas;
    private static Jogador jogadorAtual;

    // Construtor de Jogo
    public Jogo(String melhorJogador, int numeroJogadas) {
        this.melhorJogador = melhorJogador;
        this.numeroJogadas = numeroJogadas;
    }

    // Getters
    public String getMelhorJogador() {
        return melhorJogador;
    }

    public int getNumeroJogadas() {
        return numeroJogadas;
    }

    public static Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    // Setters
    public void setNumeroJogadas(int numeroJogadas) {
        this.numeroJogadas = numeroJogadas;
    }

    public static void setJogadorAtual(Jogador jogadorAtual) {
        Jogo.jogadorAtual = jogadorAtual;
    }

    // [M1S05] Exercício 6 - Método jogar Pedra, Papel e Tesoura
    public static void jogar(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo("", 0);

        System.out.println("\nIniciando o jogo...");
        // Adiciona número de jogadas (A cada jogo), mas não imprime
        jogo.setNumeroJogadas(jogo.getNumeroJogadas() + 1);

        // Jogada do usuário
        System.out.println("Digite a sua jogada desejada: 1. Pedra | 2. Papel | 3. Tesoura");
        System.out.print("Minha escolha: ");
        int jogadaUsuario = scanner.nextInt();

        // Converte a jogada do usuário
        String jogadaUsuarioTexto = converterJogada(jogadaUsuario);
        System.out.println("Sua jogada: " + jogadaUsuarioTexto);

        // Jogada do sistema: gera um número random entre 1 e 3 e converte para pedra, papel ou tesoura
        int jogadaSistema = (int) ((Math.random() * 3) + 1);
        String jogadaSistemaTexto = converterJogada(jogadaSistema);

        System.out.print("\nPreparando jogada do sistema...\n");
        System.out.print("Jogada do sistema: " + jogadaSistemaTexto + "\n");

        // Condições
        if(jogadaUsuario == jogadaSistema){
            System.out.print("\nResultado: Empate!\n");
            jogador.adicionaTentativa();
        } else if((jogadaUsuario == 1 && jogadaSistema == 3) ||
                  (jogadaUsuario == 2 && jogadaSistema == 1) ||
                  (jogadaUsuario == 3 && jogadaSistema == 2)) {
            System.out.print("\nResultado: Você venceu! Parabéns!\n");
            // Adiciona tentativa e pontos a cada vitória
            jogadorAtual.adicionaTentativa();
            jogadorAtual.adicionaPontos(jogadorAtual);

        } else {
            System.out.print("\nResultado: Você perdeu!\n");
            // Adiciona tentativa e tira pontos a cada derrota
            jogadorAtual.perdePontos(jogadorAtual);
            jogadorAtual.adicionaTentativa();
        }
    }

    // Método conversor de Jogadas Pedra, Papel e Tesoura (Sistema e Usuário)
    public static String converterJogada(int jogada) {
        switch (jogada) {
            case 1:
                return "Pedra";
            case 2:
                return "Papel";
            case 3:
                return "Tesoura";
            default:
                return "Jogada inválida!";
        }
    }

    // [M1S05] Exercício 7 - Sobrecarga no método jogar para jogo de adivinhação de número (com parâmetro int num)
    public static void jogar(int num) {
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo("", 0);

        System.out.println("\nIniciando o jogo...");
        // Adiciona número de jogadas, mas não imprime
        jogo.setNumeroJogadas(jogo.getNumeroJogadas() + 1);

        System.out.print("Você setou o número máximo para: " + num);

        // Gera um número aleatório após o usuário setar no MainJogo
        System.out.println("\nGerando um número aleatório...");
        int numeroRandom = (int) (Math.random() * (num + 1));

        // Usuário digita o seu palpite pelo console
        System.out.print("Digite o seu palpite: ");
        int palpite = scanner.nextInt();

        // Jogada do sistema
        System.out.println("Jogada do sistema: " + numeroRandom);

        // Condições
        if(palpite == numeroRandom) {
            System.out.println("\nParabéns! Você acertou!");
            // Adiciona tentativa e pontos a cada vitória
            jogadorAtual.adicionaPontos(jogadorAtual);
            jogadorAtual.adicionaTentativa();
        } else {
            System.out.println("\nVocê errou!");
            // Adiciona tentativa e tira pontos a cada derrota
            jogadorAtual.perdePontos(jogadorAtual);
            jogadorAtual.adicionaTentativa();
        }
    }
}
