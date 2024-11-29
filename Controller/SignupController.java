package Controller;

import View.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Farid
 */
public class SignupController implements Initializable {

    @FXML
    private BorderPane bp;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField signup_username;

    @FXML
    private PasswordField signup_password;

    @FXML
    private Button signup_signup;

    @FXML
    private Hyperlink signup_hyperLink;

    @FXML
    private ChoiceBox<?> myChoiceBox;
    @FXML
    private Label myLabel;
    
    public void close(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
