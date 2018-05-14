package gui;

import compiler.Compiler;
import nodes.Node;
import nodes.Parser;
import tokens.LexicalAnalysis;
import tokens.TokenList;
import vm.VirtualMaschine;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

/**
 * Главное окно графического интерфейса
 *
 * @author Starovoytov
 * @since 15.12.2017
 */
public class MainFrame extends JFrame implements ActionListener {
	private static final LexicalAnalysis lexicalAnalysis = new LexicalAnalysis();
	private static final Parser parser = new Parser();
	private static final Compiler compiler = new Compiler();
	private static final VirtualMaschine virtualMaschine = new VirtualMaschine();

	private final static int N_WIDTH = 900;
	private final static int N_HEIGHT = 600;

	private final static String COMMAND_EXEC = "Exec";

	private final JPanel mainPanel = new JPanel();
	private final JTextArea codeText = new JTextArea();
	private final JTextArea resultText = new JTextArea();

	private final JMenuBar mainMenu = new JMenuBar();
	private final JMenu operationMenu = new JMenu();
	private final JMenuItem executionMenuItem = new JMenuItem();

	/**
	 * Конструктор по умолчанию
	 */
	public MainFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(((int) screenSize.getWidth() - N_WIDTH)/2, ((int) screenSize.getHeight() - N_HEIGHT)/2);
		setSize(N_WIDTH, N_HEIGHT);
		setTitle("Простой не нужный компилятор");

		initComponents();
		loadExample();
	}

	/**
	 * Загрузка примера кода из ресурсов
	 */
	private void loadExample() {
		try {
			ClassLoader cl = this.getClass().getClassLoader();
			InputStream is = cl.getResourceAsStream("gui/exampleCode.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;

			while ((line = br.readLine()) != null) {
				codeText.setText(codeText.getText() + line + System.lineSeparator());
			}

			br.close();
			is.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Инициализация компонентов формы
	 */
	private void initComponents() {
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new MatteBorder(5, 5, 5, 5, Color.lightGray));

		codeText.setTabSize(1);
		mainPanel.add(new JScrollPane(codeText), BorderLayout.CENTER);

		resultText.setEditable(false);
		resultText.setText("Здесь будет результат");
		mainPanel.add(resultText, BorderLayout.SOUTH);

		add(mainPanel);

		executionMenuItem.setText("Exec");
		executionMenuItem.addActionListener(this);
		operationMenu.add(executionMenuItem);
		operationMenu.setText("Operations");
		mainMenu.add(operationMenu);

		setJMenuBar(mainMenu);
	}

	/**
	 * Обработчик событий формы
	 *
	 * @param actionEvent событие
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		switch (actionEvent.getActionCommand()) {
			case COMMAND_EXEC: {
				try {
					execCode();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
	}

	/**
	 * Обработка и исполнение исходного кода
	 *
	 * @throws Exception ошибка обработки
	 */
	private void execCode() throws Exception {
		TokenList list = lexicalAnalysis.analys(codeText.getText());
		Node rootNode = parser.parse(list);
		String mashineCode = compiler.compile(rootNode);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);

		virtualMaschine.run(mashineCode);

		System.out.flush();
		System.setOut(old);

		resultText.setText(baos.toString());
	}
}
