package paintFX;


import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;

public class AppController {

    @FXML
    private Canvas canvas;
    private GraphicsContext gc;


    @FXML
    public void handleExit() {
        // Close the application
        Platform.exit();
    }

    @FXML
    public void handleAbout() {
        // Create a new stage for the about window
        Stage aboutStage = new Stage();
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setTitle("About");

        // Create labels for names and year
        Label classLabel = new Label("V4CPROG3");
        Label nameLabel1 = new Label("Creators:");
        Label nameLabel2 = new Label("Petr Dolejs");
        Label nameLabel3 = new Label("Petr Marek");
        Label nameLabel4 = new Label("Vojtech Ondracek");
        Label nameLabel5 = new Label("Jan Otevrel");
        Label yearLabel = new Label("2024");

        // Create a layout and add the labels
        VBox aboutLayout = new VBox(10, classLabel, nameLabel1, nameLabel2, nameLabel3, nameLabel4, nameLabel5, yearLabel);
        aboutLayout.setPadding(new Insets(20));

        // Set the scene for the about window
        Scene aboutScene = new Scene(aboutLayout, 300, 300);
        aboutStage.setScene(aboutScene);

        // Show the about window
        aboutStage.show();
    }

    @FXML
    private ColorPicker colorPicker;  // Reference to the ColorPicker

    @FXML
    // Initialize method for setting up the canvas
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.WHITE);  // Set default stroke color to black
        gc.setLineWidth(5);
        gc.setFill(javafx.scene.paint.Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Set up mouse event for drawing
        canvas.setOnMousePressed(this::startDrawing);
        canvas.setOnMouseDragged(this::draw);
    }

  

    // Event handler for the "Save" button
    public void handleSave(ActionEvent event) {
        System.out.println("Save button clicked");
        // Implement the saving logic here
    }

    // Event handler for the "Load" button
    public void handleLoad(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        
        File file = fileChooser.showOpenDialog(new Stage());
        
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());  // Clear the canvas
            gc.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }

    // Event handler for the "New" button
    public void handleNewCanvas(ActionEvent event) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    // Event handler for the "Color Picked" button
    public void handleColorPicked(ActionEvent event) {
        // Get the selected color from the ColorPicker
        Color selectedColor = colorPicker.getValue();
        
        // Update the drawing color to the selected color
        gc.setStroke(selectedColor);
    }

    // Handle drawing when the mouse is pressed
    private void startDrawing(MouseEvent event) {
        gc.beginPath();
        gc.moveTo(event.getX(), event.getY());
    }

    // Handle drawing as the mouse is dragged
    private void draw(MouseEvent event) {
        gc.lineTo(event.getX(), event.getY());
        gc.stroke();
    }
    public void handleClearCanvas() {
        // Assuming you have a reference to the canvas
        Canvas canvas = this.canvas;  // Replace 'this.canvas' with your actual canvas reference
    
        // Get the GraphicsContext from the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();
    
        // Clear the entire canvas by filling it with white (or transparent if you prefer)
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
}
