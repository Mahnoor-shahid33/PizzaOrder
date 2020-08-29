package sample;

import com.sun.javafx.charts.ChartLayoutAnimator;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;



import java.util.concurrent.Flow;


public class Main extends Application {


    private TextField customerName, customerPhone , customerAddress;
    private RadioButton smallRadioButton , largeRadioButton , mediumRadioButton;
    private RadioButton thinRadioButton , thickRadioBtn;
    private CheckBox  pepperonich , sausagech , linguicach ,
            olivesch , mushroomsch , tomatoesch , anchoviesch;



    @Override
    public void start(Stage primaryStage) throws Exception{
        Label textHeading = new Label("Order Your Pizza Now!");
        HBox paneTop = new HBox(20,textHeading);
        paneTop.setPadding(new Insets(20));


        Label  cName = new Label("Name : ");
        customerName = new TextField();
        customerName.setPrefColumnCount(20);
        customerName.setPromptText("Enter the customer's name here");
        customerName.setMaxWidth(Double.MAX_VALUE);
        Label cPhone = new Label("Phone Number : " + " ");
        cPhone.setPrefWidth(100);
        customerPhone = new TextField();
        customerPhone.setPrefColumnCount(20);
        customerPhone.setPromptText("Enter the customer's phone number here");
        customerPhone.setMaxWidth(Double.MAX_VALUE);
        Label cAddress = new Label("Address : ");
        cAddress.setPrefWidth(100);
        customerAddress = new TextField();
        customerAddress.setPrefColumnCount(20);
        customerAddress.setPromptText("Enter the customer's address here");
        VBox labelField=new VBox(13,cName,cPhone,cAddress);
        VBox textField=new VBox(5,customerName,customerPhone,customerAddress);
        HBox pane=new HBox(labelField,textField);
        Label lSize = new Label("Size ");
        smallRadioButton = new RadioButton("Small ");
        mediumRadioButton = new RadioButton("Medium");
        largeRadioButton = new RadioButton("Large");
        mediumRadioButton.setSelected(true);
        ToggleGroup groupSize = new ToggleGroup();
        smallRadioButton.setToggleGroup(groupSize);
        mediumRadioButton.setToggleGroup(groupSize);
        largeRadioButton.setToggleGroup(groupSize);


        VBox paneSize = new VBox(lSize , smallRadioButton , mediumRadioButton , largeRadioButton);
        paneSize.setSpacing(10);

        Label lCurst = new Label("Crust");
        thinRadioButton = new RadioButton("Thin");
        thickRadioBtn = new RadioButton("Thick");
        thinRadioButton.setSelected(true);
        ToggleGroup groupCrust = new ToggleGroup();
        thinRadioButton.setToggleGroup(groupCrust);
        thickRadioBtn.setToggleGroup(groupCrust);

        VBox paneCrust = new VBox(lCurst , thinRadioButton , thickRadioBtn);
        paneCrust.setSpacing(10);


        Label lTopping  = new Label("Toppings");
        pepperonich = new CheckBox("Pepperoni");
        sausagech = new CheckBox("Sausage");
        linguicach = new CheckBox("Linguica");
        olivesch = new CheckBox("Olives");
        mushroomsch = new CheckBox("Mushrooms");
        tomatoesch = new CheckBox("Tomatoes");
        anchoviesch = new CheckBox("Anchovies");

        FlowPane paneToppings = new FlowPane(Orientation.VERTICAL ,
                pepperonich , sausagech , linguicach ,
                olivesch , mushroomsch , tomatoesch, anchoviesch);
        paneToppings.setPadding(new Insets(10,0,10,0));
        paneToppings.setHgap(20);
        paneToppings.setVgap(10);
        paneToppings.setPrefWrapLength(100);

        VBox paneTopping = new VBox(lTopping , paneToppings);

        HBox paneOrder = new HBox(50 , paneSize , paneCrust ,
                paneTopping);

        VBox paneCenter = new VBox(20, pane, paneOrder);
        paneCenter.setPadding(new Insets(0,10, 0, 10));
        Button btnOK = new Button("OK");
        btnOK.setPrefWidth(80);
        btnOK.setOnAction(e -> btnOK_Click() );
        Button btnCancel = new Button("Cancel");
        btnCancel.setPrefWidth(80);
        btnCancel.setOnAction(e ->
                primaryStage.close()
        );
        Region spacer = new Region();
        HBox paneBottom = new HBox(10, spacer, btnOK, btnCancel);
        paneBottom.setHgrow(spacer, Priority.ALWAYS);
        paneBottom.setPadding(new Insets(20, 10, 20, 10));
        BorderPane paneMain = new BorderPane();
        paneMain.setTop(paneTop);
        paneMain.setCenter(paneCenter);
        paneMain.setBottom(paneBottom);
        Scene scene = new Scene(paneMain);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pizza Order");
        primaryStage.show();


    }




    private void btnOK_Click() {
        String msg = "Customer:\n\n";
        msg += "\t" + customerName.getText() + "\n";
        msg += "\t" + customerAddress.getText() + "\n";
        msg += "\t" + customerPhone.getText() + "\n\n";
        msg += "You have ordered a ";
        if (smallRadioButton.isSelected())
            msg += "small ";
        if (mediumRadioButton.isSelected())
            msg += "medium ";
        if (largeRadioButton.isSelected())
            msg += "large ";
        if (thinRadioButton.isSelected())
            msg += "thin crust pizza with ";
        if (thickRadioBtn.isSelected())
            msg += "thick crust pizza with ";

        String toppings = "";
        toppings = buildToppings(pepperonich,toppings);
        toppings = buildToppings(sausagech,toppings);
        toppings = buildToppings(linguicach,toppings);
        toppings = buildToppings(olivesch,toppings);
        toppings = buildToppings(tomatoesch,toppings);
        toppings = buildToppings(mushroomsch,toppings);
        toppings = buildToppings(anchoviesch,toppings);


        if (toppings.equals(""))
            msg += "no toppings.";
        else
            msg += "the following toppings:\n"
                    + toppings;

        Alert alert = new Alert(Alert.AlertType.INFORMATION ,"Order Details ");
        alert.setContentText(msg);
        alert.show();
    }

    public String buildToppings(CheckBox chk, String msg)
    {
        if (chk.isSelected())
        {
            if (!msg.equals(""))
            {
                msg += ", ";
            }
            msg += chk.getText();
        }
        return msg;
    }

    public void btnCancel_Click()
    {

    }




    public static void main(String[] args) {
        launch(args);
    }
}
