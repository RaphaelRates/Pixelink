package br.com.Pixelink.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.Pixelink.entidades.Usuario;

public class Dados {

	  public static boolean verificarUsuarioExistente(String email, String senha) {
		  List<Usuario> usuarios = Dados.lerUsuarios();
		  for (Usuario usuario : usuarios) {
			  if((usuario.getEmail().equals(email) || usuario.getID().equals(email)) && usuario.getSenha().equals(senha)) return true;
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
	                String caminhoPasta = "src\\br\\com\\Pixelink\\data\\usuarios\\." + "user_"+ novaConta.getID();; // Caminho relativo ao diretório do projeto
	                File pasta = new File(caminhoPasta);

	                if (!pasta.exists()) { // Verifica se a pasta não existe
	                    if (pasta.mkdir()) { // Tenta criar a pasta
	                    	criarArquivoCSV(new File(caminhoPasta, "PostText.csv"));
	                        criarArquivoCSV(new File(caminhoPasta, "seguindo.csv"));
	                        criarArquivoCSV(new File(caminhoPasta, "seguidores.csv"));
	                        criarArquivoCSV(new File(caminhoPasta, "noticicacoes.csv"));
	                        System.out.println("Pasta criada com sucesso.");
	                    } else {
	                        System.out.println("Não foi possível criar a pasta.");
	                    }
	                } else {
	                    System.out.println("A pasta já existe.");
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
		  List<Usuario> usuarios = Dados.lerUsuarios();
		  for (Usuario usuario : usuarios) {
			  if((usuario.getEmail().equals(email) || usuario.getID().equals(email)) && usuario.getSenha().equals(senha)) {
				  Usuario novo = new Usuario(usuario.getName(), usuario.getEmail(), usuario.getSenha(), usuario.getID());
				  try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\br\\com\\Pixelink\\data\\cache.csv", true))) {
					  Dados.apagarTudo("src\\br\\com\\Pixelink\\data\\cache.csv");
		              String linha = novo.getName() + ";" + novo.getID() + ";" + novo.getEmail() + ";" + novo.getSenha() + ";";
		              writer.write(linha);
		              writer.newLine();
		          } catch (IOException e) {
		              e.printStackTrace();
		          }
			  }
		  }
	  }
	  
	  public static void apagarTudo(String caminho) {
	        String caminhoArquivo = caminho;
	        try {
	            FileWriter writer = new FileWriter(caminhoArquivo);
	            writer.close();
	            System.out.println("Conteúdo do arquivo CSV apagado com sucesso.");
	        } catch (IOException e) {
	            System.err.println("Erro ao apagar o conteúdo do arquivo CSV: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	  public static List<Usuario> lerUsuarios(){
		  List<Usuario> usuarios= new ArrayList<>();
		  String filePath = "src\\br\\com\\Pixelink\\data\\usuarios.csv"; // o caminho que ele irá verificar o arquivo csv onde estão guardados os usuarios
	      File file = new File(filePath);
	      try {
	          Scanner scanner = new Scanner(file); //cria um escanner para escanear o arquino no diretorio
	          while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] values = line.split(";");
	                String nome = values[0];
	                String username = values[1];
	                String email = values[2];
	                String senha = values[3];
	                usuarios.add(new Usuario(nome, email, senha, username));
	            }
	            scanner.close();
	            return usuarios;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return usuarios;
	        }
	  }
	  
	  public static Usuario getLogado() {
		  Usuario logado = null;
		  String filePath = "src\\br\\com\\Pixelink\\data\\cache.csv"; // o caminho que ele irá verificar o arquivo csv onde estão guardados os usuarios
	      File file = new File(filePath);
	      try {
	          Scanner scanner = new Scanner(file); //cria um escanner para escanear o arquino no diretorio
	          while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] values = line.split(";");
	                String nome = values[0];
	                String username = values[1];
	                String email = values[2];
	                String senha = values[3];
	                if(nome.isEmpty() || username.isEmpty() || email.isEmpty() || senha.isEmpty()) {
	                	return null;
	                }
	                logado = new Usuario(nome, email, senha, username);
	            }
	            scanner.close();
	           
	        } catch (FileNotFoundException e) {
	            return null;
	        }catch (ArrayIndexOutOfBoundsException e) {
	        	return null;
	        }
	      return logado;
	  }
}
