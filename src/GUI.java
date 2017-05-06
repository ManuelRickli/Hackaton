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
		numberLeft.setStyle("-fx-font-size: 30");

		Hyperlink suggestion1 = new Hyperlink("https://www.google.com");
		suggestion1.setOnAction(e -> openURL(suggestion1.getText()));

		Hyperlink suggestion2 = new Hyperlink("https://www.google.com");
		suggestion2.setOnAction(e -> openURL(suggestion2.getText()));

		Hyperlink suggestion3 = new Hyperlink("https://www.google.com");
		suggestion3.setOnAction(e -> openURL(suggestion3.getText()));

		Hyperlink suggestion4 = new Hyperlink("https://www.google.com");
		suggestion4.setOnAction(e -> openURL(suggestion4.getText()));

		Hyperlink suggestion5 = new Hyperlink("https://www.google.com");
		suggestion5.setOnAction(e -> openURL(suggestion5.getText()));


		TextField inputField = new TextField();
		inputField.textProperty().addListener((observableValue, oldValue, newValue) -> {
			//TODO: get number of url's that start with the newValue text and update the suggestions
			System.out.println(newValue);
		});

		VBox pane = new VBox(15);
		pane.setPadding(new Insets(15, 15, 15, 15));
		pane.setMinWidth(300);

		pane.getChildren().addAll(numberLeft, inputField, suggestion1, suggestion2, suggestion3, suggestion4, suggestion5);

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