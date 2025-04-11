package keypad;

/**
 * File: KeyPadPaneCustom.java
 * Class: CSCI 1302
 * Author: Cayden Gosseck
 * Created on: Apr 09, 2025
 * Last Modified: Apr 11, 2025
 * Description: Extended KeypadPane that allows for button styling and outputs a code
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.Objects;

public class KeyPadPaneCustom extends KeyPadPane {

    // data
    public  StringBuilder currentCode = new StringBuilder();

    public KeyPadPaneCustom() {
        registerEventHandlers();
    }

    // set all button sizes
    public void setButtonSize(int v, int v1) {
        for(int i=0; i <listButtons.size(); i++) {
            listButtons.get(i).setPrefSize(v,v1);
        }
    }

    // style all buttons
    public void setCustomButtonStyle(String fontFamily, int fontSize, String textColor, String backgroundColor) {
        for(int i=0; i <listButtons.size(); i++) {
            listButtons.get(i).setStyle("-fx-font-family: " + "'" + fontFamily + "';"
            + " -fx-font-size: " + fontSize + "px;" + " -fx-font-weight: bold;" + " -fx-text-fill: " + textColor +";" +" -fx-background-color: " + backgroundColor + ";" + "-fx-padding: 10px;");

        }
    }

    @Override
    protected void registerEventHandlers() {

        // check if we are using phone layout
        ArrayList<Button> currList = new ArrayList<Button>();
        if (copyListButtons != null) {
            currList = copyListButtons;
        } else {
            currList = listButtons;
        }

        // set event handlers for all Buttons. clear buttons will clear entered code, while the other buttons append the code with their value
        for (int i = 0; i < currList.size(); i++) {
            currList.get(i).setOnAction(e -> {

                System.out.println("Button was clicked: " + ((Button) e.getSource()).getText());
                if(((Button) e.getSource()).getText().equals("  ")) {
                   currentCode.setLength(0);
                } else {
                    currentCode.append(((Button) e.getSource()).getText());
                }
                System.out.println("Current user code: " + currentCode);
            });
        }
    }

    public StringBuilder getCurrentCode() {
        return currentCode;
    }

    public void setCurrentCode(String code) {
        this.currentCode.setLength(0);
        this.currentCode.append(code);
    }
}