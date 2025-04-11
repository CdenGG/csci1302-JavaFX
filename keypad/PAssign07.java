package keypad;

/**
 * File: PAssign07.java
 * Class: CSCI 1302
 * Author: Cayden Gosseck
 * Created on: Apr 09, 2025
 * Last Modified: Apr 11, 2025
 * Description: A functional program that models a nuclear launch device. Save a launch code then
 * enter the code in the keypad. Use !Commands in the console for more.
 * GitHub Hello World Assignment Repo: https://github.com/CdenGG/hello-world
 * This GitHub Repo: https://github.com/CdenGG/csci1302-JavaFX
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PAssign07 extends Application {

    // static data
    public static int code = -1;
    public static int attempts = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // initialize and style nodes
        String boldStyle = "-fx-font-weight: bold; -fx-font-size: 12px;";

        Label label = new Label("Set Launch Codes: ");
        label.setStyle(boldStyle);

        Label consoleTitle = new Label("Console: ");
        consoleTitle.setStyle(boldStyle);

        TextField setLaunchCodes = new TextField();
        TextField console = new TextField();

        Button saveText = new Button("Save");
        saveText.setStyle(boldStyle);

        Button launchNukeButton = new Button("LAUNCH");
        launchNukeButton.setStyle(boldStyle);

        Button clearButton = new Button("CLEAR");
        clearButton.setStyle(boldStyle);

        // add launch and clear buttons to HBox for precise positioning
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(launchNukeButton, clearButton);

        // initialize, style, and position logTitle label
        Label logTitle = new Label("Event Log");
        logTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;");
        logTitle.setPadding(new Insets(18));

        // initialize log, set size and methods
        TextArea log = new TextArea();
        log.setEditable(false);
        log.setWrapText(true);
        log.setPrefSize(100, 100);
        log.setFocusTraversable(false);

        // initialize custom KeyPadPane and set the button size and style
        KeyPadPaneCustom paneCustom = new KeyPadPaneCustom();

        paneCustom.setButtonSize(100, 100);
        paneCustom.setCustomButtonStyle("Verdana", 12, " #000000", " #d9d9d9");

        paneCustom.setHgap(10);
        paneCustom.setVgap(10);

        // add nodes to gridPane and position them
        GridPane gridPane = new GridPane();
        gridPane.add(label, 0, 0);
        gridPane.add(setLaunchCodes, 0, 1);
        gridPane.add(saveText, 1, 1);
        gridPane.add(paneCustom, 0, 2);
        gridPane.add(buttonBox, 0, 3);
        gridPane.add(consoleTitle, 0, 4);
        gridPane.add(console, 0, 5);
        gridPane.add(logTitle, 3, 1);
        gridPane.add(log, 3, 2);

        // gridPane insets and positioning
        gridPane.setHgap(5.0);
        gridPane.setVgap(5.0);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.setStyle("-fx-background-color: #F5F5F5;");

        // Event Handlers
        saveText.setOnAction(e -> {
            processSaveText(setLaunchCodes, log);
            clearUserCode(paneCustom, log);
        });

        launchNukeButton.setOnAction(e -> {
            processLaunchNuke(paneCustom, log);
        });

        clearButton.setOnAction(e -> {
            clearUserCode(paneCustom, log);
        });

        console.setOnAction(e -> {
                    processConsoleCommand(console.getText(), paneCustom, setLaunchCodes, log);
                    console.clear();
                }
        );

        // create scene, set stage
        Scene scene = new Scene(gridPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Top Secret Device");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Methods

    // outputs console command results to the log and error checking with commands
    public void processConsoleCommand(String command, KeyPadPaneCustom paneCustom, TextField setLaunchCodes, TextArea log) {
        switch (command) {
            case "!Commands":
                log.setText("\nCommands:\n!Commands \nlists all commands\n!ClearCode \nClears user code\n" +
                        "!Launch \nLaunches nuke\n!ClearLog \nclears log\n!Save \nsaves launch code\n");
                break;

            case "!ClearCode":
                clearUserCode(paneCustom, log);
                break;


            case "!Launch":
                processLaunchNuke(paneCustom, log);
                break;

            case "!ClearLog":
                clearLog(log);
                break;


            case "!Save":
                processSaveText(setLaunchCodes, log);
                clearUserCode(paneCustom, log);
                break;

            default:
                log.appendText("\nUnknown Command: " + command);
        }
    }

    // launch nuke only if launch code equals user code and no more than three attempts
    public void processLaunchNuke(KeyPadPaneCustom paneCustom, TextArea log) {

        if (attempts >= 3) {
            System.out.println("Error: Four or more attempts. Unable to Launch.");
            log.appendText("\nError: Four or more attempts. Unable to Launch.\n");
            return;
        }

        try {
            if (Integer.parseInt(paneCustom.getCurrentCode().toString()) == code) {
                System.out.println("Successfully Entered Code. Launching Nuke!");
                log.appendText("\nSuccessfully Entered Code. Launching Nuke!\n");
                resetAttempts();

            } else {
                System.out.println("Error: Wrong Code");
                log.appendText("\nError: Wrong Code\n");
                attempts++;
                log.appendText("\nCurrent Attempts: " + attempts + "\n");
            }
        } catch(NumberFormatException e) {
            System.out.println("Error: No User Code");
            log.appendText("\nError: No User Code\n");
        }

    }

    public void resetAttempts() {
        attempts = 0;
    }

    // save the entered launch code if it's an integer
    public void processSaveText(TextField setLaunchCodes, TextArea log) {
        try {
            System.out.println(setLaunchCodes.getText());
            code = Integer.parseInt(setLaunchCodes.getText());
            System.out.println("Current Launch Code: " + code);
            log.appendText("\nLaunch Code Saved!\n");
        } catch (NumberFormatException e) {
            System.out.println("Error, must enter a number.");
            log.appendText("\nError, must enter a number.\n");
        }
    }

    // clear the current entered user code and update the log
    public void clearUserCode(KeyPadPaneCustom paneCustom, TextArea log) {
        paneCustom.setCurrentCode("");
        log.appendText("\nCleared User Code\n");
    }

    // method to clear log. Maybe update later to save log before clearing.
    public void clearLog(TextArea log) {
        log.clear();
    }
}