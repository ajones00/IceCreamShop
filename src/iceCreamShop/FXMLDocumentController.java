package iceCreamShop;

//Anthony
//CIS 1500
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import java.util.Scanner;

public class FXMLDocumentController implements Initializable {

    @FXML
    private RadioButton chocolateButton;

    @FXML
    private Button restoreButton;

    @FXML
    private HBox icecreamBox;

    @FXML
    private CheckBox nutsButton;

    @FXML
    private TitledPane flavorPane;

    @FXML
    private RadioButton strawberryButton;

    @FXML
    private TitledPane orderPane;

    @FXML
    private TitledPane toppingsPane;

    @FXML
    private CheckBox cherriesButton;

    @FXML
    private RadioButton vanillaButton;

    @FXML
    private ToggleGroup Flavors;

    @FXML
    private Button saveButton;

    @FXML
    private Button calculateCostButton;

    public static final double FLAVOR = 2.25;
    public static final double EXTRAS = 0.50;
    public static final double MICHIGAN_TAX = 0.06;
    public boolean fileMade = false;

    @FXML
    private void calculateIceCreamCost(ActionEvent event) {
        double order = 0;
        double tax = 0;
        double total = 0;
        order = order + FLAVOR;

        
        if (nutsButton.isSelected()) {
            order = order + EXTRAS;
        }

        if (cherriesButton.isSelected()) {
            order = order + EXTRAS;
        }
        
        tax = order * MICHIGAN_TAX;
        total = order + tax;       
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(String.format("Total: $%.2f", total));
        alert.setContentText(String.format("Order:  %.2f\nTax:      "
                + "%.2f\nTotal:"
                + "    %.2f", order, tax, total));
        alert.setTitle("Your Order");
        alert.showAndWait();
    }

    @FXML
    private void saveIceCreamOrder(ActionEvent event) {
        try {
            File file = new File("icecream.txt");
            try (PrintWriter orderlist = new PrintWriter(file)) {
                if (chocolateButton.isSelected()) {
                    orderlist.println("Chocolate");
                } else if (vanillaButton.isSelected()) {
                    orderlist.println("Vanilla");
                } else {
                    orderlist.println("Strawberry");
                }

                if (nutsButton.isSelected()) {
                    orderlist.println("With_Nuts");
                } else {
                    orderlist.println("Without_Nuts");
                }

                if (cherriesButton.isSelected()) {
                    orderlist.println("With_Cherries");
                } else {
                    orderlist.println("Without_Cherries");
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Saving File");
            alert.setHeaderText("Error encountered:");
            alert.setContentText(e.toString());
        }
    }

    @FXML
    private void restoreIceCreamOrder(ActionEvent event) {
        try {
            File f = new File(new File("icecream.txt").getAbsolutePath());
            Scanner in = new Scanner(System.in);
            in = new Scanner(f);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                switch (line) {
                    case "Vanilla":
                        vanillaButton.setSelected(true);
                        break;
                    case "Chocolate":
                        chocolateButton.setSelected(true);
                        break;
                    case "Strawberry":
                        strawberryButton.setSelected(true);
                        break;
                    case "With_Nuts":
                        nutsButton.setSelected(true);
                        break;
                    case "Without_Nuts":
                        nutsButton.setSelected(false);
                        break;
                    case "With_Cherries":
                        cherriesButton.setSelected(true);
                        break;
                    case "Without_Cherries":
                        cherriesButton.setSelected(false);
                        break;
                    default:
                        break;
                }
            }
            in.close();
            double order = 0;
            double tax = 0;
            double total = 0;
            order = order + FLAVOR;
            
            if (nutsButton.isSelected()) {
                order = order + EXTRAS;
            }

            if (cherriesButton.isSelected()) {
                order = order + EXTRAS;
            }
            
            tax = order * MICHIGAN_TAX;
            total = order + tax;
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(String.format("Total: $%.2f", total));
            alert.setContentText(String.format("Order:  %.2f\nTax:      "
                    + "%.2f\nTotal:"
                    + "    %.2f", order, tax, total));
            alert.setTitle("Your Order");
            alert.showAndWait();

        } catch (FileNotFoundException e) {
            System.out.println(" Not Found " ); 
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Restoring File");
            alert.setHeaderText("Error encountered:");
            alert.setContentText(e.toString());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
