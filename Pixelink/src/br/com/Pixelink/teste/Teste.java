package br.com.Pixelink.teste;
import java.io.File;
import java.io.IOException;

public class Teste {
    public static void main(String[] args) {
        String comandoAbrirExplorer = null;

        String sistemaOperacional = System.getProperty("os.name").toLowerCase();
        if (sistemaOperacional.contains("win")) {
            // Windows
            comandoAbrirExplorer = "explorer.exe";
        } else if (sistemaOperacional.contains("mac")) {
            // macOS
            comandoAbrirExplorer = "open";
        } else if (sistemaOperacional.contains("nix") || sistemaOperacional.contains("nux") || sistemaOperacional.contains("aix")) {
            // Linux
            comandoAbrirExplorer = "xdg-open";
        }

        if (comandoAbrirExplorer != null) {
            try {
                Runtime.getRuntime().exec(comandoAbrirExplorer);
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao abrir o explorador de arquivos: " + e.getMessage());
            }
        } else {
            System.out.println("Explorador de arquivos não suportado neste sistema.");
            return;
        }

        // Pausa a execução para permitir que o usuário selecione o arquivo manualmente
        System.out.println("Selecione o arquivo de imagem desejado no explorador de arquivos e pressione Enter para continuar...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Após o usuário selecionar o arquivo e pressionar Enter, podemos acessar o arquivo selecionado
        File arquivoSelecionado = new File(System.getProperty("user.home") + "C:\\Users\\Raphael\\Pictures\\Screenshots\\Captura de tela 2023-09-03 160628.png");
        if (arquivoSelecionado.exists() && arquivoSelecionado.isFile()) {
            System.out.println("Arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());
            System.out.println("Informações sobre o arquivo:");
            System.out.println("Nome do arquivo: " + arquivoSelecionado.getName());
            System.out.println("Tamanho do arquivo: " + arquivoSelecionado.length() + " bytes");
            System.out.println("Caminho absoluto do arquivo: " + arquivoSelecionado.getAbsolutePath());
            System.out.println("Última modificação: " + arquivoSelecionado.lastModified());
        } else {
            System.out.println("Nenhum arquivo de imagem selecionado ou arquivo inválido.");
        }
    }
}


