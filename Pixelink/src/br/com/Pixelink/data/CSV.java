package br.com.Pixelink.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import br.com.Pixelink.entidades.Usuario;

public class CSV {

	  public static boolean verificarUsuarioExistente(String caminho, String separador ,String username,String email) {
	    	String filePath = caminho;
	        File file = new File(filePath);
	        
	        try {
	            Scanner scanner = new Scanner(file);
	            
	            // Lê cada linha do arquivo CSV
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                // Divide a linha em valores usando a vírgula como delimitador
	                String[] values = line.split(separador);
	                
	                // Faça o que desejar com os valores
	                String usuario = values[1];
	                String Email = values[2];
	                if(usuario.equals(username) || Email.equals(email)) { //verifica se ele esta dentro do banco de dados 
	                	return false;
	                }
	            }
	            scanner.close();
	            return true;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	  
	  public static  void escritaCSV(String caminho, String separador, Usuario novaConta) {
	    	String filePath = caminho;
	    	if(verificarUsuarioExistente(caminho, separador, novaConta.getID(), novaConta.getEmail())) {
	    		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
	                // Escreve os dados do usuário no formato CSV
	                String linha = novaConta.getName() + separador + novaConta.getID() + separador + novaConta.getEmail() + separador + novaConta.getSenha() + separador;
	                writer.write(linha);
	                writer.newLine();

	                System.out.println("Dados do usuário foram escritos com sucesso no arquivo CSV.");
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	    	}else {
	    		System.err.println("deu ruim se vira");
	    	}  
	    }
	  
	  public static void apagarTudo(String caminho) {
	        // Especifique o caminho para o arquivo CSV
	        String caminhoArquivo = caminho;

	        try {
	            // Cria um objeto FileWriter sem especificar o modo de escrita (irá sobrescrever)
	            FileWriter writer = new FileWriter(caminhoArquivo);
	            // Fecha o escritor para garantir que os dados anteriores sejam apagados
	            writer.close();
	            System.out.println("Conteúdo do arquivo CSV apagado com sucesso.");
	        } catch (IOException e) {
	            System.err.println("Erro ao apagar o conteúdo do arquivo CSV: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	  
	  public static void main(String[] agrs) {
		  CSV.apagarTudo("C:\\Users\\Raphael\\git\\Repository\\Pixelink\\src\\br\\com\\Pixelink\\data\\usuarios.csv");
		  System.err.println("ok");
	  }

}
