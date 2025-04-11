
# Description of this Fork
This fork is for PAssign07 by Cayden Gosseck.


# Orginal Implementations

# csci1302-JavaFX
 This repository contains public code files for GSU CSCI 1302 students.
 
 # Usage
 Clone this reposotory to your machine, Open in Github Desktop, or copy the code into files on your machine.  
 
 If using the KeyPadPane while it is still in the package, make sure that it is in the same directory, and import it:
 ```java
 import keypad.*
 ```
 
 You can examine the implementation of the `KeyPadPane.java`, but should **NOT** modify the contents of the file when using it in your assignment(s).

 # `TestKeyPadPane.java`
 The `TestKeyPadPane.java` file shows a quick demo of using this custom Pane.  This should not just be copied and pasted for the assignment.  It is only intended as an example.

 # Implementations by CdenGG

 # `KeyPadPaneCustom.java`
The `KeyPadPaneCustom.java` file extends the customability of KeyPadPane by having separate style and size methods for all buttons. It also implements a code that is appended by a buttons number when that button is clicked. This code is compared to the launch code.

# `PAssign07.java`
The `PAssign07.java` file is the main program that implements a KeyPadPaneCustom, a GridPane, the Launch and Clear Buttons, the Launch text field, the Console text field, the Log text area, and the primary scene / stage. This file also styles, sizes, and positions these nodes.
