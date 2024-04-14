package Swing.Componentes;

import javax.swing.JOptionPane;

public class Alert {
	
    public static void exibirAlerta(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Alerta", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    public static void exibirInformacao(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void exibirPergunta(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Pergunta", JOptionPane.QUESTION_MESSAGE);
    }


}