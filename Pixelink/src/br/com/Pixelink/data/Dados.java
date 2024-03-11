package br.com.Pixelink.data;

import java.io.*;
import java.util.*;
import br.com.Pixelink.entidades.Usuario;

public class Dados {

    public static boolean verificarUsuarioExistente(String email, String senha) {
        List<Usuario> usuarios = Dados.lerUsuarios();
        for (Usuario usuario : usuarios) {
            if ((usuario.getEmail().equals(email) || usuario.getID().equals(email)) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
    
	  public static boolean verificarIDExiste(String ID) {
	  List<Usuario> usuarios = Dados.lerUsuarios();
	  for (Usuario usuario : usuarios) {
		  if(usuario.getID().equals(ID) ) return true;
	  }
	  return false;
  } 
    
	  public static  void CriarConta(Usuario novaConta) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\br\\com\\Pixelink\\data\\usuarios.csv", true))) {
	        String linha = novaConta.getName() + ";" + novaConta.getID() + ";" + novaConta.getEmail() + ";" + novaConta.getSenha() + ";";
	        writer.write(linha);
	        writer.newLine();
	        System.out.println("Dados do usuário foram escritos com sucesso no arquivo CSV.");
	        String caminhoPasta = "src\\br\\com\\Pixelink\\data\\usuarios\\." + "user_"+ novaConta.getID();
	        File pasta = new File(caminhoPasta);
	        if (!pasta.exists()) { 
	            if (pasta.mkdir()) { 
	            	criarArquivoCSV(new File(caminhoPasta, "PostText.csv"));
	                criarArquivoCSV(new File(caminhoPasta, "seguindo.csv"));
	                criarArquivoCSV(new File(caminhoPasta, "seguidores.csv"));
	                criarArquivoCSV(new File(caminhoPasta, "notificacoes.csv"));
	            } else {
	//                System.out.println("Não foi possível criar a pasta.");
	            }
	        } else {
	//            System.out.println("A pasta já existe.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
}

    private static void criarArquivoCSV(File arquivo) {
        try {
            if (arquivo.createNewFile()) {
                System.out.println("Arquivo " + arquivo.getName() + " criado com sucesso.");
            } else {
                System.out.println("O arquivo " + arquivo.getName() + " já existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao criar o arquivo " + arquivo.getName() + ": " + e.getMessage());
        }
    }

    public static void Logar(String email, String senha) {
        List<Usuario> usuarios = lerUsuarios();
        for (Usuario usuario : usuarios) {
            if ((usuario.getEmail().equals(email) || usuario.getID().equals(email)) && usuario.getSenha().equals(senha)) {
                escreverCache(usuario);
                return;
            }
        }
    }

    private static void escreverCache(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\br\\com\\Pixelink\\data\\cache.csv"))) {
			Dados.apagarTudo("src\\br\\com\\Pixelink\\data\\cache.csv");
            String linha = usuario.getName() + ";" + usuario.getID() + ";" + usuario.getEmail() + ";" + usuario.getSenha() + ";";
            writer.write(linha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void apagarTudo(String caminho) {
        try {
            FileWriter writer = new FileWriter(caminho);
            writer.close();
            System.out.println("Conteúdo do arquivo CSV apagado com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao apagar o conteúdo do arquivo CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Usuario> lerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String filePath = "src\\br\\com\\Pixelink\\data\\usuarios.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line == "") {
                	
                }
                String[] values = line.split(";");
                String nome = values[0];
                String username = values[1];
                String email = values[2];
                String senha = values[3];
                usuarios.add(new Usuario(nome, email, senha, username));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static Usuario getLogado() {
        Usuario logado = null;
        String filePath = "src\\br\\com\\Pixelink\\data\\cache.csv";
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(";");
                String nome = values[0];
                String username = values[1];
                String email = values[2];
                String senha = values[3];
                if (camposVazios(nome, username, email, senha)) {
                    return null;
                }
                logado = new Usuario(nome, email, senha, username);
            }
            scanner.close();
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return logado;
    }

    private static boolean camposVazios(String... campos) {
        for (String campo : campos) {
            if (campo.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
