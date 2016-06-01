package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View {

	public static void main(String[] args) {
		JFrame janela = new JFrame("Compilador");
		JMenuBar menubar = new JMenuBar();
		JMenu menuArquivo = new JMenu("Arquivo");
		JMenu menuLexico = new JMenu("Lexico");
		JMenu menuSintatico = new JMenu("Sintatico");
		JMenu menuSemantico = new JMenu("Semantico");
		JMenu menuCodigo = new JMenu("Código");
		JMenu menuAjuda = new JMenu("Ajuda");

		JMenuItem menuItemSalvar = new JMenuItem("Salvar");
		JMenuItem menuItemAbrir = new JMenuItem("Abrir");
		menuItemAbrir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				Component parent = null;
				int returnVal = chooser.showOpenDialog(parent);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
				}
				System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			}
		});

		JMenuItem menuItemDocumentao = new JMenuItem("Documentação");
		JMenuItem menuItemSobre = new JMenuItem("Sobre");

		menuArquivo.add(menuItemSalvar);
		menuArquivo.add(menuItemAbrir);

		menuAjuda.add(menuItemDocumentao);
		menuAjuda.add(menuItemSobre);

		menubar.add(menuArquivo);
		menubar.add(menuLexico);
		menubar.add(menuSintatico);
		menubar.add(menuSemantico);
		menubar.add(menuCodigo);
		menubar.add(menuAjuda);

		janela.setJMenuBar(menubar);

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);

		JScrollPane scrollPane = new JScrollPane(textArea);

		janela.setSize(700, 500);
		janela.setVisible(true);
		janela.add(scrollPane);
	}
}
