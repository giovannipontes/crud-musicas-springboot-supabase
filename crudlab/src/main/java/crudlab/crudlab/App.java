package crudlab.crudlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private MusicaRepo musicaRepo;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        while (true) {
            System.out.println("\n# CRUD de Músicas");
            System.out.println("(1) Cadastrar música");
            System.out.println("(2) Listar músicas");
            System.out.println("(3) Atualizar música");
            System.out.println("(4) Deletar música");
            System.out.println("(5) Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarMusica();
                case 2 -> listarMusicas();
                case 3 -> atualizarMusica();
                case 4 -> deletarMusica();
                case 5 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarMusica() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Compositor: ");
        String compositor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());

        Musica musica = new Musica(titulo, compositor, ano);
        musicaRepo.save(musica);
        System.out.println("Música cadastrada com sucesso!");
    }

    private void listarMusicas() {
        List<Musica> musicas = (List<Musica>) musicaRepo.findAll();
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
        } else {
            System.out.printf("%-5s %-30s %-20s %s\n", "ID", "Título", "Compositor", "Ano");
            for (Musica m : musicas) {
                System.out.printf("%-5d %-30s %-20s %d\n", m.getId(), m.getTitulo(), m.getCompositor(), m.getAno());
            }
        }
    }

    private void atualizarMusica() {
        System.out.print("ID da música a atualizar: ");
        Long id = Long.parseLong(scanner.nextLine());

        Musica musica = musicaRepo.findById(id).orElse(null);
        if (musica == null) {
            System.out.println("Música não encontrada.");
            return;
        }

        System.out.print("Novo título: ");
        musica.setTitulo(scanner.nextLine());
        System.out.print("Novo compositor: ");
        musica.setCompositor(scanner.nextLine());
        System.out.print("Novo ano: ");
        musica.setAno(Integer.parseInt(scanner.nextLine()));

        musicaRepo.save(musica);
        System.out.println("Música atualizada com sucesso!");
    }

    private void deletarMusica() {
        System.out.print("ID da música a deletar: ");
        Long id = Long.parseLong(scanner.nextLine());

        if (musicaRepo.existsById(id)) {
            musicaRepo.deleteById(id);
            System.out.println("Música deletada com sucesso!");
        } else {
            System.out.println("Música não encontrada.");
        }
    }
}
