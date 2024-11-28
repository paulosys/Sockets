import java.io.*;
import java.net.*;
import java.util.Date;

public class DateServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(6013);

			System.out.println("=== Servidor iniciado ===\n");

			while (true) {
				Socket clientSocket = serverSocket.accept();

				System.out.println("Servidor recebeu comunicação do IP: "
						+ clientSocket.getInetAddress()
						+ " - Porta: " + clientSocket.getPort());

				new Thread(() -> {
					try {
						PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
						out.println(new Date() + " - Boa noite, alunos!");

						InputStream in = clientSocket.getInputStream();
						BufferedReader bin = new BufferedReader(new InputStreamReader(in));
						String clientMessage = bin.readLine();

						System.out.println("O cliente me disse: " + clientMessage);

						clientSocket.close();
					} catch (IOException e) {
						System.err.println("Erro ao comunicar com o cliente: " + e.getMessage());
					}
                }).start();
			}
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}
}
