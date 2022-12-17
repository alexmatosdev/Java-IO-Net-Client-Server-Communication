import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Server server = new Server();
		server.start(primaryStage);
		Client client = new Client();
		client.start(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}
}