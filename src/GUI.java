import javafx.application.Application;
import javafx.collections.MapChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by manu on 05.05.17.
 */
public class GUI extends Application {
	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("Auto Completion GUi");

		Label numberLeft = new Label("0");
		numberLeft.setStyle("-fx-font-size: 20");

		Hyperlink[] suggestions = new Hyperlink[5];

		TextField inputField = new TextField();
		inputField.textProperty().addListener((observableValue, oldValue, newValue) -> {
			//TODO: get number of url's that start with the newValue text and update the suggestions
			System.out.println(newValue);
		});

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(15, 15, 15, 15));

		pane.getChildren().addAll(numberLeft, inputField, suggestions[0], suggestions[1], suggestions[2], suggestions[3], suggestions[4]);

		Scene scene = new Scene(pane);
		window.setScene(scene);
		window.show();
	}

	private void openURL(String url) {
		new Thread(() -> {
			try {
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(new URI(url));
				}

			} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
