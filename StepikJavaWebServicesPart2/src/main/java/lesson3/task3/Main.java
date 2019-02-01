package lesson3.task3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Старт сервиса
 * Используются неблокирующие сокеты nio
 *
 * @author starovoytov
 * @since 2019.02.01
 */
public class Main {
	public static void main(String[] args) throws IOException {
		System.out.println("Server started");

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		serverSocketChannel.socket().bind(new InetSocketAddress(5060));
		serverSocketChannel.configureBlocking(false);

		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();

			if (socketChannel != null) {

				ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

				while (socketChannel.read(byteBuffer) > 0) {
					socketChannel.write(byteBuffer);
				}

				socketChannel.close();
			}
		}
	}
}
