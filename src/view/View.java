package view;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View {

	private static FileDialog Fsalvar;

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
		JMenuItem menuItemSair = new JMenuItem("Sair");

		JTextArea textArea = new JTextArea();

		Fsalvar = new FileDialog(janela, "Salvar arquivo", FileDialog.SAVE);

		menuItemAbrir.addActionListener(new ActionListener() {
			private File f;
			private RandomAccessFile file;

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					this.f = chooser.getSelectedFile();
				}
				try {
					this.file = new RandomAccessFile(this.f, "rw");
					String linha = "";
					StringBuffer sTxt = new StringBuffer();
					while ((linha = this.file.readLine()) != null) {
						sTxt.append(linha + "\n");
					}
					textArea.setText(sTxt.toString());
					this.file.seek(0);
				} catch (FileNotFoundException ex) {
				} catch (IOException ex) {
				}
			}
		});

		menuItemSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (View.Fsalvar.getFile() == null) {
						return;
					}
					String nome = View.Fsalvar.getDirectory() + View.Fsalvar.getFile();
					FileWriter out = new FileWriter(nome);
					out.write(textArea.getText());
					out.close();
				} catch (java.io.IOException exc) {
				}
			}
		});

		menuItemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem menuItemDocumentao = new JMenuItem("Documentação");
		JMenuItem menuItemSobre = new JMenuItem("Sobre");

		menuArquivo.add(menuItemSalvar);
		menuArquivo.add(menuItemAbrir);
		menuArquivo.addSeparator();
		menuArquivo.add(menuItemSair);

		menuAjuda.add(menuItemDocumentao);
		menuAjuda.add(menuItemSobre);

		menubar.add(menuArquivo);
		menubar.add(menuLexico);
		menubar.add(menuSintatico);
		menubar.add(menuSemantico);
		menubar.add(menuCodigo);
		menubar.add(menuAjuda);

		janela.setJMenuBar(menubar);

		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(true);

		JScrollPane scrollPane = new JScrollPane(textArea);

		janela.setSize(700, 500);
		janela.setVisible(true);
		janela.add(scrollPane);
	}
}
