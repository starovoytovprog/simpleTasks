package mainpack;

import interfaces.desctop.MainFrame;
import interfaces.web.MainServer;

import static interfaces.web.Constants.PORT;

/**
 * Запуск приложения
 *
 * @author Starovoytov
 * @since 21.06.2018
 */
public class Main {

	private static final String WEB = "web";
	private static final String GUI = "gui";

	public static void main(String[] args) {
		Main main = new Main();

		if (args.length == 0) {
			main.startGUI();
			return;
		}

		switch (args[0]) {
			case WEB: {
				main.startWeb();
				break;
			}
			case GUI: {
				main.startGUI();
				break;
			}
		}
	}

	private void startGUI() {
		new MainFrame().setVisible(true);
	}

	private void startWeb() {
		MainServer server = new MainServer(PORT);
		server.start();
	}
}
