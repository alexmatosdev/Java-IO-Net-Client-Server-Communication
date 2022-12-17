import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application{

	DataOutputStream out;
	DataInputStream in;
	ServerSocket serverSocket;
	Socket socket;
	int port = 8000;
	String local = "localHost";

	@Override
	public void start(Stage primaryStage) throws Exception {
		TextArea textArea = new TextArea();

		Scene scene = new Scene(textArea, 500, 200);
		primaryStage.setTitle("Server"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

		new Thread( () -> {
			try {
				serverSocket = new ServerSocket(port);
				socket = serverSocket.accept();

				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());

				while (true) {

					// Read in data from client 
					double aIR = in.readDouble();
					double nYrs = in.readDouble();
					double loanAm = in.readDouble();

					// Math computation to give results
					double a = (loanAm*aIR);
					double b = (nYrs*12);
					double monPayment = a / b;

					out.writeDouble(monPayment);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

