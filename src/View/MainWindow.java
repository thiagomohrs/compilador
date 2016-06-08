package View;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.StringReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import Gals.Lexico;

public class MainWindow {

	private static FileDialog Fsalvar;
	private static JTextArea textArea = new JTextArea();

	public static void main(String[] args) {
		final JFrame janela = new JFrame("Compilador");
		final JMenuBar menubar = new JMenuBar();
		final JMenu menuArquivo = new JMenu("Arquivo");
		final JMenu menuLexico = new JMenu("Lexico");
		final JMenu menuSintatico = new JMenu("Sintatico");
		final JMenu menuSemantico = new JMenu("Semantico");
		final JMenu menuCodigo = new JMenu("Código");
		final JMenu menuAjuda = new JMenu("Ajuda");

		menuLexico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO fazer por esse método
			}
		});
		menuLexico.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				System.out.println("menu " + menuLexico.getText() + " Selected");

				String stringToBeParsed = textArea.getText();
				StringReader reader = new StringReader(stringToBeParsed);
				new Lexico(reader);
			}

			@Override
			public void menuDeselected(MenuEvent e) {
				System.out.println("menu " + menuLexico.getText() + " Deselected");

			}

			@Override
			public void menuCanceled(MenuEvent e) {
				System.out.println("menu " + menuLexico.getText() + " Canceled");

			}
		});

		final JMenuItem menuItemSalvar = new JMenuItem("Salvar", KeyEvent.VK_S);
		final JMenuItem menuItemAbrir = new JMenuItem("Abrir", KeyEvent.VK_O);
		final JMenuItem menuItemSair = new JMenuItem("Sair", KeyEvent.VK_E);

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
					StringBuffer stringBuffer = new StringBuffer();
					while ((linha = this.file.readLine()) != null) {
						stringBuffer.append(linha + "\n");
					}
					textArea.setText(stringBuffer.toString());
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
					Fsalvar.setVisible(true);
					if (Fsalvar.getFile() == null) {
						return;
					}
					String nome = Fsalvar.getDirectory() + Fsalvar.getFile();
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

		final JMenuItem menuItemDocumentacao = new JMenuItem("Documentação");
		final JMenuItem menuItemSobre = new JMenuItem("Sobre");

		menuItemDocumentacao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "\nDocumentação...\n\n");
			}
		});

		menuItemSobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						new JLabel(
								"Compilador desenvolvido por Raphael Martins e Thiago Mohr para a matéria de INE5622 - Introdução a compiladores - Universidade Federal de Santa Catarina - Sistemas de Informação",
								JLabel.CENTER));
			}
		});

		menuArquivo.add(menuItemSalvar);
		menuArquivo.add(menuItemAbrir);
		menuArquivo.addSeparator();
		menuArquivo.add(menuItemSair);

		menuAjuda.add(menuItemDocumentacao);
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

		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setSize(700, 500);
		janela.setVisible(true);
		janela.add(scrollPane);
	}
}
