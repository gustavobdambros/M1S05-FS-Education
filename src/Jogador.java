import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

// [M1S05] Exercício 1 - Criar classe Jogador com os atributos nome, idade, pontuacao, numeroTentativas
public class Jogador {

    // [M1S05] Exercício 2 - Atributos encapsulados com private
    // Atributos
    private String nome;
    private int idade;
    private int pontuacao;
    private int numeroTentativas;

    // [M1S05] Exercício 3 - Lista de melhores jogadores (que é a mesma do ranking)
    public static ArrayList<Jogador> melhoresJogadores = new ArrayList<>();

    // [M1S05] Exercício 1 - Criar os métodos adicionaPontos, perdePontos e adicionaTentativa
    // Método para adicionar pontos ao jogador
    public void adicionaPontos(Jogador jogador) {
        jogador.setPontuacao(getPontuacao() + 1);
        System.out.println("Você ganhou um ponto!");
    }

    // Método para tirar pontos do jogador
    public void perdePontos(Jogador jogador) {
        int novaPontuacao = getPontuacao() - 1;
        jogador.setPontuacao(novaPontuacao < 0 ? 0 : novaPontuacao);
        if (jogador.getPontuacao() == 0) {
            System.out.println("Você está com 0 pontos!");
        } else {
            System.out.println("Você perdeu um ponto!");
        }
    }

    // Método para adicionar tentativas a cada jogada ao jogador
    public void adicionaTentativa() {
        setNumeroTentativas(getNumeroTentativas() + 1);
    }

    // [M1S05] Exercício 2 - Construtor de Jogador, seguidos dos getters e setters de cada atributo
    public Jogador(String nome, int idade, int pontuacao, int numeroTentativas) {
        this.nome = nome;
        this.idade = idade;
        this.pontuacao = pontuacao;
        this.numeroTentativas = numeroTentativas;
    }

    // Getters
    public String getNome() {
        return this.nome;
    }

    public int getIdade() {
        return this.idade;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public int getNumeroTentativas() {
        return this.numeroTentativas;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setNumeroTentativas(int numeroTentativas) {
        this.numeroTentativas = numeroTentativas;
    }

    // [M1S05] Exercício 3 - Método auxiliar para ordenar os jogadores por ordem decrescente por pontuação na lista (ranking)
    public static void atualizarLista() {
        melhoresJogadores.sort(Comparator.comparingInt(Jogador::getPontuacao).reversed());
    }

    // [M1S05] Exercício 4
    // Método que exibe o ranking completo e os melhores jogadores, dependendo do tamanho da lista
    public static void exibirRanking(boolean mostrarCompleto, Jogador jogadorAtual) {
        System.out.println("\nRanking:");
        ArrayList<Jogador> ranking = Jogador.melhoresJogadores;
        int limite = mostrarCompleto ? ranking.size() : Math.min(ranking.size(), 10);

        for (int i = 0; i < limite; i++) {
            Jogador jogador = ranking.get(i);
            System.out.println(jogador.getNome() + " - " + (i + 1) + ", Pontuação: " + jogador.getPontuacao());
        }
        posicaoAtual(jogadorAtual);
    }

    // [M1S05] Exercício 4
    // Método que imprime a posição atual do jogador na lista do ranking
    public static void posicaoAtual(Jogador jogador) {
        int posicao = melhoresJogadores.indexOf(jogador);
        if (posicao >= 0) {
            System.out.println("\nSua posição e pontuação atual na lista: ");
            System.out.println(jogador.getNome() + " - " + (posicao + 1) + ", Pontuação: " + jogador.getPontuacao());
        } else {
            System.out.println("Você não está na lista de melhores jogadores.");
        }
    }

    // [M1S05] Exercício 5 - Método para criação ou busca de jogadores, com validação se já existe (rodando em loop)
    public static Jogador criarOuEscolherJogador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("O que você deseja executar?\n1. Criar novo jogador\n2. Buscar jogador existente ");
        System.out.print("Minha escolha: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                do {
                    System.out.print("Digite o nome do jogador a criar: ");
                    String novoNome = scanner.next();

                    boolean jogadorJaExiste = false;
                    for (Jogador jogador : melhoresJogadores) {
                        if (jogador.getNome().equals(novoNome)) {
                            System.out.println("Jogador já existente! Digite outro nome.");
                            jogadorJaExiste = true;
                            break;
                        }
                    }

                    if (!jogadorJaExiste) {
                        System.out.print("Digite a idade do jogador a criar: ");
                        int idade = scanner.nextInt();

                        Jogador novoJogador = new Jogador("", 0, 0, 0);
                        novoJogador.setNome(novoNome);
                        novoJogador.setIdade(idade);
                        melhoresJogadores.add(novoJogador);
                        System.out.println("Jogador adicionado com sucesso!");
                        atualizarLista();

                        return novoJogador;
                    }
                } while (true);

            case 2:
                atualizarLista();
                System.out.print("Digite o nome do jogador existente: ");
                String escolhaUsuario = scanner.next();

                for (int i = 0; i < melhoresJogadores.size(); i++) {
                    Jogador jogador = melhoresJogadores.get(i);
                    if (jogador.getNome().equals(escolhaUsuario)) {
                        System.out.println("Jogador encontrado!");
                        System.out.println("Nome: " + jogador.getNome() + " - " + " Posição: " + (i + 1) + "\n");
                        return jogador;
                    }
                }
                System.out.println("Jogador não encontrado! Tente novamente.\n");
                break;

            default:
                System.out.println("Opção inválida! Tente novamente.\n");
        }
        return criarOuEscolherJogador(); // Chamada recursiva
    }

    // Setup inicial para a lista ter alguns jogadores ao inciar qualquer jogo.
    public static void setupJogadores() {
        if (melhoresJogadores.isEmpty()) {
            melhoresJogadores.add(new Jogador("Isac", 25, 2, 2));
            melhoresJogadores.add(new Jogador("João", 28, 2, 2));
            melhoresJogadores.add(new Jogador("Junior", 22, 5, 2));
            melhoresJogadores.add(new Jogador("Amanda", 21, 3, 2));
            atualizarLista();
        }
    }
}