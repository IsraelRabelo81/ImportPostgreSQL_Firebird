package ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class MainController {

    @FXML private TextField pgHost;
    @FXML private TextField pgPort;
    @FXML private TextField pgDatabase;
    @FXML private TextField pgUser;
    @FXML private PasswordField pgPassword;

    @FXML private TextField fbPath;
    @FXML private TextField fbUser;
    @FXML private PasswordField fbPassword;

    @FXML private CheckBox chkProduct;
    @FXML private CheckBox chkSupplier;
    @FXML private CheckBox chkClient;

    @FXML
    public void onTestConnection() {
        System.out.println("Testando conexão...");
        System.out.println("Postgres: " + pgHost.getText() + ":" + pgPort.getText());
        System.out.println("Firebird: " + fbPath.getText());
    }

    @FXML
    public void onImport() {
        System.out.println("Importação iniciada...");

        if (chkProduct.isSelected()) {
            System.out.println("Importar Produtos");
        }

        if (chkSupplier.isSelected()) {
            System.out.println("Importar Fornecedores");
        }

        if(chkClient.isSelected()) {
            System.out.println("Importar Clientes");
        }
    }
}