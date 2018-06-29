package interfaces.desctop;

import document.Waybill;
import loader.StringLoader;
import processors.FinalProcessor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import static interfaces.desctop.Constants.*;

/**
 * Главное окно графического интерфейса
 *
 * @author Starovoytov
 * @since 22.06.2018
 */
public class MainFrame extends JFrame implements ActionListener {
	private final static String R = "\r";
	private final static String N = "\n";
	private final static String EMPTY = "";
	private final JPanel mainPanel = new JPanel();
	private final JTextArea waybillText = new JTextArea();
	private final JTextField summField = new JTextField();
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
		setTitle("Выполнение разбивки по суммам");

		initComponents();
	}

	/**
	 * Инициализация компонентов формы
	 */
	private void initComponents() {
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new MatteBorder(5, 5, 5, 5, Color.lightGray));

		summField.setText(DEFAULT_SUM);
		mainPanel.add(summField, BorderLayout.NORTH);

		waybillText.setTabSize(1);
		waybillText.setText(DEFAULT_STRING);
		waybillText.setLineWrap(true);
		waybillText.setToolTipText(WAYBILL_CONNTENT_HINT);
		mainPanel.add(new JScrollPane(waybillText), BorderLayout.CENTER);

		resultText.setEditable(false);
		resultText.setText("Здесь будет результат");
		mainPanel.add(resultText, BorderLayout.SOUTH);

		add(mainPanel);

		executionMenuItem.setText(COMMAND_CALCULATE);
		executionMenuItem.addActionListener(this);
		operationMenu.add(executionMenuItem);
		operationMenu.setText("Операции");
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
			case COMMAND_CALCULATE: {
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
	 * Выполнение рассчёта
	 *
	 * @throws Exception ошибка обработки
	 */
	private void execCode() throws Exception {
		Waybill waybill = getWaybillContent();
		resultText.setText(FinalProcessor.find(waybill, Integer.parseInt(summField.getText())).toString());
	}

	/**
	 * Формирование накладной из строки текстового поля
	 *
	 * @return накладная
	 */
	private Waybill getWaybillContent() {
		waybillText.setText(waybillText.getText().replaceAll(R, EMPTY).replaceAll(N, System.lineSeparator()));
		if (waybillText.getText().contains(System.lineSeparator())) {
			return StringLoader.load(waybillText.getText());
		}

		return StringLoader.loadSumOnly(waybillText.getText());
	}
}
