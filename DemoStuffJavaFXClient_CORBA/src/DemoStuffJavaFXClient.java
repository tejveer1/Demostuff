import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import entity.Stuff;
import business.StuffFacadeRemote;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class DemoStuffJavaFXClient extends Application {
        private Label stuffLabel = new Label();
        private Label moreStuffLabel = new Label();
        private Label omega = new Label();
        private Label delta = new Label();
        private Label theta = new Label();
        private TextField stuffTextField = new TextField();
        private TextField moreStuffTextField = new TextField();
        private TextField omegaTextField = new TextField();
        private TextField deltaTextField = new TextField();
        private TextField thetaTextField = new TextField();

        private TextArea viewStuffTextArea = new TextArea();
        private Button viewButton = new Button();
        private Button addButton = new Button();
        private static StuffFacadeRemote remoteStuff = null;
        @Override
        public void start(Stage primaryStage) {
            stuffLabel.setText("recordnumber");
            moreStuffLabel.setText("Lambda");
            omega.setText("Omega");
            delta.setText("Delta");
            theta.setText("Theta");
            viewButton.setText("Show Tuna");
            viewButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (remoteStuff != null) {
                    viewStuffTextArea.clear();
                        for (Stuff s : remoteStuff.findAll()) {
                            viewStuffTextArea.appendText(
                        String.format("id:%d stuff:%s morestuff:%s Tuna:%s "
                                + "Delta:%s Lambda:%s%n",
                        s.getId(), s.getStuff(), s.getMoreStuff(), s.getTuna(),
                        s.getDelta(),s.getLambda()));
                        }
                    }else {
                            showAlert(AlertType.ERROR, "No remote object available");
                    }
                } catch (Exception ex) {
                    showAlert(AlertType.ERROR, ex.getMessage());
                }
            }
        });
            addButton.setText("Add Tuna");
            addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (remoteStuff != null) {
            Stuff stuff = new Stuff();
            stuff.setStuff(stuffTextField.getText());
            stuff.setMoreStuff(moreStuffTextField.getText());
            stuff.setDelta(deltaTextField.getText());
            stuff.setTuna(omegaTextField.getText());
            stuff.setLambda(thetaTextField.getText());

            remoteStuff.create(stuff);
            } else {
                    showAlert(AlertType.ERROR, "No remote object available");
            }
            } catch (Exception ex) {
            showAlert(AlertType.ERROR, ex.getMessage());
            }
            }
            });
            HBox buttonsHBox = new HBox();
            buttonsHBox.setSpacing(10);
            buttonsHBox.setPadding(new Insets(10, 10, 10, 10));
            buttonsHBox.setAlignment(Pos.CENTER_RIGHT);
            buttonsHBox.getChildren().addAll(addButton, viewButton);
            GridPane root = new GridPane();
            root.add(stuffLabel, 1, 1); // column 1, row 1
            root.add(stuffTextField, 2, 1); // column 2, row 1
            root.add(moreStuffLabel, 1, 2); // column 1, row 2
            root.add(moreStuffTextField, 2, 2); // column 2, row 2
            
            root.add(delta, 1, 3); // column 1, row 1
            root.add(deltaTextField, 2, 3); // column 2, row 1
            root.add(omega, 1, 4); // column 1, row 2
            root.add(omegaTextField, 2, 4); // column 2, row 2

            root.add(theta, 1, 5); // column 1, row 2
            root.add(thetaTextField, 2, 5); // column 2, row 2
            
            root.add(buttonsHBox, 1, 6); // column 2, row 3
            root.add(viewStuffTextArea, 1, 7); // column 2, row 4
            root.setHgap(10);
            root.setVgap(10);
            root.setMargin(viewStuffTextArea, new Insets(0, 10, 10, 0)); // top, right, bottom, left
            
            root.setMargin(stuffTextField, new Insets(0, 10, 0, 0));
root.setMargin(moreStuffTextField, new Insets(0, 10, 0, 0));
Scene scene = new Scene(root, 2000, 1000);
primaryStage.setTitle("JavaFX Client Demo - CORBA");
primaryStage.setScene(scene);
primaryStage.show();
showAlert(AlertType.INFORMATION, "Trying for a session...");
remoteStuff = getRemoteSession();
if(remoteStuff != null){
showAlert(AlertType.INFORMATION, "Got a session :)");
}
else{
showAlert(AlertType.ERROR, "No remote object available");
}
}
public void showAlert(AlertType alertType, String message) {
Alert alert = new Alert(alertType);
alert.setTitle("Information");
alert.setHeaderText(null);
alert.setContentText(message);
alert.showAndWait();
}
private static StuffFacadeRemote getRemoteSession() {
StuffFacadeRemote session = null;
// CORBA properties and values and lookup taken after earlier work provided by
// Todd Kelley (2016) Personal Communication
System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
System.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
try {
InitialContext ic = new InitialContext();
session = (StuffFacadeRemote)
ic.lookup("java:global/DemoStuff/DemoStuff-ejb/StuffFacade");
} catch (NamingException e) {
System.out.println("Problem Cause: \n" + e.getMessage());
} catch (Exception e) {
System.out.println("Problem Cause: \n" + e.getMessage());
}
return session;
}
public static void main(String[] args) {
Application.launch(DemoStuffJavaFXClient.class);
}
}
