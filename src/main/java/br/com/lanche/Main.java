package br.com.lanche;

import br.com.lanche.applications.LancheApplication;
import br.com.lanche.facades.LancheFacade;
import br.com.lanche.models.Lanche;
import br.com.lanche.repositories.LancheRepository;
import br.com.lanche.services.LancheService;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static LancheRepository lancheRepository;
    private static LancheService lancheService;
    private static LancheApplication lancheApplication;
    private static LancheFacade lancheFacade;
    private static Scanner scanner;

    public static void injetarDependencias() {
        lancheRepository = new LancheRepository();
        lancheService = new LancheService();
        lancheApplication = new LancheApplication(lancheRepository, lancheService);
        lancheFacade = new LancheFacade(lancheApplication);
        scanner = new Scanner(System.in);

    }
    public static void exibirMenu(){
        System.out.println("\n=== MENU DO SISTEMA ===");
        System.out.println("1 - Listar Produtos");
        System.out.println("2 - Cadastrar Produto");
        System.out.println("3 - Editar Produto");
        System.out.println("4 - Excluir Produto");
        System.out.println("5 - Vender");
        System.out.println("0 - Sair do sistema");

    }

    public static int solicitaOpcaoMenu(){
        System.out.println("Informe a opção escoliha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }

    public static void listarLanches() throws Exception{
        System.out.println("\nLista de Produtos:\n(ID -- Nome -- Preço)\n");
        lancheFacade.buscarTodos().forEach(l -> {
            System.out.println(l);
        });
    }

    public static void cadastrarLanche() throws Exception{
        System.out.println("ID do produto: ");
        int id = scanner.nextInt();

        System.out.println("Nome do produto: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        System.out.println("Valor do produto: ");
        double preco = scanner.nextFloat();

        String caminhoImagem;
        while (true) {
            System.out.print("Digite o caminho completo da imagem (ou deixe vazio para cancelar): ");
            caminhoImagem = scanner.nextLine();

            if (caminhoImagem.isBlank()) {
                System.out.println("Cadastro cancelado.");
                return;
            }

            if (new File(caminhoImagem).exists()) {
                break;
            } else {
                System.out.println("Arquivo não encontrado! Digite novamente.");
            }
        }

        Lanche lanche = new Lanche(id, nome, preco, caminhoImagem);
        lancheFacade.adicionar(lanche);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void editarLanche()throws Exception{
        System.out.println("Digite o ID do produto para editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Lanche lanche = lancheFacade.buscarPorId(id);
        if (lanche == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.println("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.println("Novo preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Novo caminho da imagem: ");
        String caminhoImagem = scanner.nextLine();

        Lanche atualizado = new Lanche(id, nome, preco, caminhoImagem);
        lancheFacade.atualizar(id, atualizado);

        System.out.println("Produto atualizado com sucesso!");

    }

    public static void excluirLanche()throws Exception{
        System.out.println("Digite o ID do produto para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removido = lancheFacade.remover(id);
        if (removido) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    public static void  venderLanche()throws Exception{
        System.out.println("Digite o ID do produto para vender: ");
        int id = scanner.nextInt();


        Lanche lanche = lancheFacade.buscarPorId(id);
        if (lanche == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        System.out.println("Produto vendido: " + lanche.getNome() + " por Preço: " + lanche.getPreco());

    }


    public static void iniciarSistema() {
        int opcaoMenu = -1;

        do {
            try {
                exibirMenu();
                opcaoMenu = solicitaOpcaoMenu();

                switch (opcaoMenu) {
                    case 1:
                        listarLanches();
                        break;
                    case 2:
                        cadastrarLanche();
                        break;
                    case 3:
                        editarLanche();
                        break;
                    case 4:
                        excluirLanche();
                        break;
                    case 5:
                        venderLanche();
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcaoMenu != 0);
    }


    public static void main(String[] args){
        injetarDependencias();
        iniciarSistema();
    }

}