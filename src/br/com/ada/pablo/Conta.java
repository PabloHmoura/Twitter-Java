package br.com.ada.pablo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Conta {
    int quant = 1;
    String[] twitts = new String[50];

    Usuario usu = new Usuario();

    public void telaPrincipal() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("******** Bem vindo a It´s Now ********");
        System.out.println();
        System.out.println("     *************************");
        System.out.println("     ***Digite 1 para login***");
        System.out.println("     *************************");
        System.out.println();
        System.out.println();
        System.out.println("     *************************");
        System.out.println("     *Digite 2 para cadastrar*");
        System.out.println("     *************************");
        System.out.println("        Digite 3 para sair    ");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1 -> loginConta();
            case 2 -> criarConta();
            case 3 -> System.exit(0);
            default -> System.out.println("Opção inválida;-;");
        }
        scanner.close();
    }

    public void criarConta() throws IOException {

        Scanner scannerCriarConta = new Scanner(System.in);
        System.out.print("Digite o nome de usuário que deseja: ");
        String login = scannerCriarConta.nextLine();
        System.out.println("Nome de usuário " + login);
        System.out.println();
        System.out.print("Crie sua senha: ");
        String password = scannerCriarConta.nextLine();
        System.out.println("Repita a senha criada: ");
        String repitaPassword = scannerCriarConta.nextLine();
        this.usu.usuario = login;
        this.usu.senha = password;
        String conta = usu.usuario + usu.senha;
        int i = 0;
        while (i < usu.usuarios.length) {
            if (usu.usuarios[i] == null) {
                usu.usuarios[i] = conta;
            }
            i++;
            this.usu.qtd ++;
        }
        if (login.isEmpty() || password.isEmpty() || repitaPassword.isEmpty()){
            System.out.println("""
                    Houve algum problema na hora de cadastrar seu usuário :(\s
                     Tente novamente e verifique se as senhas são compatíveis\s
                    """);
            System.out.println("""
                    Digite 1 para refazer o cadastro\s
                    Digite 2 para sair\s
                    Digite 3 para ir a tela de login:\s""");
            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();
            switch (escolha) {
                case 1 -> criarConta();
                case 2 -> System.out.println("Tchau, até a próxima ♥");
                case 3 -> loginConta();
                default -> System.out.println("opção inválida.");
            }
        } else if (password.length() < 8){
            System.out.println("Você precisa criar uma senha com mais de 8 dígitos!");
            criarConta();
        } else {
            if (password.equals(repitaPassword)) {

                System.out.println("Conta cadastrada com sucesso!");
                System.out.println();
                System.out.println("Agora é apenas fazer o login ;)");
                System.out.println();
                telaPrincipal();
            } else{
                System.out.println("Senhas não compatíveis");
            }
        }
            scannerCriarConta.close();
    }

    public void loginConta() throws IOException {
        Scanner scannerLogin = new Scanner(System.in);
        System.out.print("Digite seu usuário: ");
        String login = scannerLogin.nextLine();
        System.out.println();
        System.out.println("Agora digite sua senha: ");
        String senha = scannerLogin.nextLine();
        String logar = login + senha;
        boolean naoEhValido = true;
        if (login.isEmpty() || senha.isEmpty()){
            System.out.println("Credenciais incorretas:( É você mesmo?:o");
            System.out.println();
        }else {
            //Validação de credênciais, tentei fazer de uma maneira diferente.
            for (int i = 0; i < usu.usuarios.length; i++) {
                if (usu.usuarios[i].equals(logar)){
                    System.out.println("Login realizado!!:D \n " +
                            "Bem vindo " + login +"\n \n");
                    naoEhValido = false;
                    telaPerfil();
                }
            } if (naoEhValido){
                System.out.println("Credenciais incorretas:( É você mesmo?:o");
                telaPrincipal();
            }
        }
        scannerLogin.close();
    }

    public void telaPerfil() throws IOException {
        Scanner scannerfeed = new Scanner(System.in);
        System.out.println("Olá " + this.usu.usuario);
        System.out.println();
        System.out.println("Ver feed[1]-------Publicar[2]-------Sair[3]");
        System.out.print("Digite o número do que deseja fazer:");
        int opcao = scannerfeed.nextInt();
        if (opcao == 1){
            System.out.println("*******Publicações*******");
            publicacoes();
        } else if (opcao == 2){
            System.out.println("Faça sua publicação");
            twittar();
        } else if (opcao == 3) {
            telaPrincipal();
        } else {
            telaPerfil();
        }
        scannerfeed.close();
    }

    public void twittar() throws IOException {

        System.out.println("Digite o que quer twittar\n");
        System.out.println("Aperte ENTER 3 vezes para enviar a postagem");

        Scanner scannerTwittar = new Scanner(System.in);
        //Criando o formato da data
        BufferedWriter escrever = new BufferedWriter(new FileWriter("twitters.txt", true));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        String data = dtf.format(now);
        String header = "@ " + this.usu.usuario + " | " + data;
        escrever.newLine();
        escrever.write("+++++++++++++++++++++++++++++++++++++++++");
        escrever.newLine();
        escrever.write(header);
        escrever.newLine();
        escrever.write("-----------------------------------------");
        escrever.newLine();
        String linha = scannerTwittar.nextLine();
        //Laço para ler o twitt e salvá-lo no arquivo e no array.
        while (linha != null && !linha.isEmpty()) {
            escrever.write(linha);
            escrever.newLine();
            escrever.write(scannerTwittar.nextLine());
            escrever.newLine();
            linha = scannerTwittar.nextLine();
            //Tive dificuldade nessa parte :( não consigo colocar a mensagem no array
        }
        escrever.write("+++++++++++++++++++++++++++++++++++++++++");
        escrever.newLine();
        escrever.newLine();
        System.out.println();
        System.out.println("Ver publicação[1]----Fazer um novo twit[2]----Sair[3]");
        int opcao = scannerTwittar.nextInt();
        if (opcao == 1){
            publicacoes();
        } else if (opcao == 2){
            escrever.newLine();
            twittar();
        }else if (opcao == 3){
            System.exit(0);
        }
        scannerTwittar.nextLine();

        escrever.close();
        scannerTwittar.close();
    }

    public void publicacoes() throws IOException {
        Scanner scannerPublicacao = new Scanner(System.in);
        //Lendo o arquivo para imprimir os twitts antigos.
        FileReader fis = new FileReader("twitters.txt");
        BufferedReader br = new BufferedReader(fis);
        String linha = br.readLine();
        //Mostrando o twitt que acabou de ser criado porquê só é salvo no arquivo após o programa ser encerrado.
        for (String twitt : twitts) {
            System.out.println(twitt);
        }
        while(linha != null) {
            System.out.println(linha);
            linha = br.readLine();
        }
        System.out.println("Ver feed[1]-------Publicar[2]-------Sair[3]");
        System.out.print("Digite o número do que deseja fazer:");
        int opcao = scannerPublicacao.nextInt();
        if (opcao == 1){
            System.out.println("*******Publicações*******");
            publicacoes();
        } else if (opcao == 2){
            System.out.println("Faça sua publicação");
            twittar();
        } else if (opcao == 3) {
            telaPrincipal();
        } else {
            telaPerfil();
        }
        scannerPublicacao.close();
        fis.close();
        br.close();
    }
}
