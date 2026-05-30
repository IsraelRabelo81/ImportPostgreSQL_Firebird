package ui;

import database.PostgresConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import service.*;

import java.io.File;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainController {


    @FXML private TextField     pgHost;
    @FXML private TextField     pgPort;
    @FXML private TextField     pgDatabase;
    @FXML private TextField     pgUser;
    @FXML private PasswordField pgPassword;
    @FXML private Label         lblStatusConexao;
    @FXML private Label         lblStatus;


    @FXML private TextField txtPastaSaida;


    @FXML private CheckBox chkProdutos;
    @FXML private CheckBox chkClientes;
    @FXML private CheckBox chkFornecedores;


    @FXML private TextArea txtLog;



    @FXML
    public void initialize() {
        // Valores padrão
        pgHost.setText("localhost");
        pgPort.setText("5432");
        pgDatabase.setText("SOFTMOBILE");
        pgUser.setText("postgres");

        // Selecionar todos por padrão
        chkProdutos.setSelected(true);
        chkClientes.setSelected(false);
        chkFornecedores.setSelected(true);

        log("Sistema iniciado. Configure a conexão e selecione a pasta de saída.");
    }


    @FXML
    public void onTestarConexao() {
        log("Testando conexão com PostgreSQL...");

        try (Connection conn = getConexao()){
            lblStatusConexao.setText("Conexão realizada com sucesso!");
            lblStatusConexao.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
            log("Conexão com PostgreSQL estabelecida com sucesso!");

        } catch (Exception e) {
            lblStatusConexao.setText("Falha na conexão: " + e.getMessage());
            lblStatusConexao.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            log("Erro na conexão: " + e.getMessage());
        }
    }


    @FXML
    public void onSelecionarPasta() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Selecione a pasta de destino dos arquivos .TXT");
        chooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        Stage stage = (Stage) txtPastaSaida.getScene().getWindow();
        File pastaSelecionada = chooser.showDialog(stage);

        if (pastaSelecionada != null) {
            txtPastaSaida.setText(pastaSelecionada.getAbsolutePath());
            log("Pasta selecionada: " + pastaSelecionada.getAbsolutePath());
        }
    }


    @FXML
    public void onGerarArquivos() {

        // Validações
        if (!validarCampos()) return;
        String pasta = txtPastaSaida.getText();

        log("Iniciando exportação...");
        log("─────────────────────────────────────");

        if (chkProdutos.isSelected()) {

            try (Connection conn = getConexao()) {
                log("Exportando Produtos...");

                StringBuilder logProduct = new StringBuilder();

                int totalProd = ExportProdutcService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logProduct
                );

                log(logProduct.toString());
                log("produtos.txt gerado com sucesso! " + totalProd + " registros.");

            } catch (Exception e) {
                log("Erro ao exportar Produtos: " + e.getMessage());
                e.printStackTrace();
            }

            try (Connection conn = getConexao()) {
                log("Exportando Códigos de Barras...");
                StringBuilder logCodBarras = new StringBuilder();

                int totalCodBarras = ExportCodBarrasService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logCodBarras
                );
                log(logCodBarras.toString());
                log("codaux.txt gerado com sucesso! " + totalCodBarras + " registros");

            } catch (Exception e) {
                log("Erro ao exportar Cod. Barras: " + e.getMessage());
                e.printStackTrace();
            }

            try (Connection conn = getConexao()) {
                log("Exportando Produto/Fornecedor...");
                StringBuilder logProdForn = new StringBuilder();

                int totalProdForn = ExportProdFornService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logProdForn
                );
                log(logProdForn.toString());
                log("prodforn.txt gerado com sucesso! " + totalProdForn + " registros");
            } catch (Exception e) {
                log("Erro ao exportar Prod. Fornecedor: " + e.getMessage());
                e.printStackTrace();
            }

            try (Connection conn = getConexao()) {
                log("Exportando Produto/UF...");
                StringBuilder logProdUf = new StringBuilder();

                int totalProdUf = ExportProdUfService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logProdUf
                );
                log(logProdUf.toString());
                log("produf.txt gerado com sucesso! " + totalProdUf + " registros");
            } catch (Exception e) {
                log("Erro ao exportar Prod. UF: " + e.getMessage());
                e.printStackTrace();
            }

            try (Connection conn = getConexao()) {
                log("Exportando Preços...");
                StringBuilder logProdPrec = new StringBuilder();

                int totalProdPrec = ExportProdPrecService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logProdPrec
                );
                log(logProdPrec.toString());
                log("prodprec.txt gerado com sucesso! " + totalProdPrec + " registros");
            } catch (Exception e) {
                log("Erro ao exportar Prod. Preços: " + e.getMessage());
                e.printStackTrace();
            }

            try (Connection conn = getConexao()) {
                log("Exportando Estoque...");
                StringBuilder logEstoque = new StringBuilder();

                int totalEstoque = ExportEstoqueService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logEstoque
                );

                log(logEstoque.toString().trim());
                log("estoque.txt gerado com sucesso! " + totalEstoque + " registros");
            } catch (Exception e) {
                log("Erro ao exportar Estoque: " + e.getMessage());
                e.printStackTrace();
            }

            try (Connection conn = getConexao()){
                log("Exportando Produtos Empresa...");
                StringBuilder logEmpMate = new StringBuilder();

                int totalEmpMate = ExportEmpMatService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logEmpMate
                );
                log(logEmpMate.toString());
                log("empmate.txt gerado com sucesso! " + totalEmpMate + " registros");
            } catch (Exception e) {
                log("Erro ao exportar Produtos Empresa: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (chkClientes.isSelected()) {
            log("Exportando Clientes...");
            // ExportacaoClienteService.exportar(conn, pasta);
            log("clientes.txt gerado com sucesso!");
        }

        if (chkFornecedores.isSelected()) {
            try (Connection conn = getConexao()) {
                log("Exportando Fornecedores...");
                StringBuilder logForn = new StringBuilder();

                int totalForn = ExportSupplierService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logForn
                );
                log(logForn.toString().trim());
                log("fornecedor.txt gerado com sucesso: " + totalForn + " registros");
            } catch (Exception e) {
                log("Erro ao exportar Fornecedores: " + e.getMessage());
                e.printStackTrace();
            }

            try (Connection conn = getConexao()) {
                log("Exportando Fornecedores/Empresas...");
                StringBuilder logEmpForn = new StringBuilder();

                int totalEmpForn = ExportEmpSupplierService.exportar(
                        conn,
                        txtPastaSaida.getText(),
                        logEmpForn
                );
                log(logEmpForn.toString().trim());
                log("empforn.txt gerado com sucesso: " + totalEmpForn + " registros");
            } catch (Exception e) {
                log("Erro ao exportar Empresas/Fornecedores: " + e.getMessage());
                e.printStackTrace();
            }

        }


        log("─────────────────────────────────────");
        log("Exportação concluída com sucesso!");
        log("Arquivos salvos em: " + pasta);
    }


    @FXML
    public void onLimparLog() {
        txtLog.clear();
        log("Log limpo.");
    }

    private Connection getConexao() throws Exception {
        return PostgresConnection.getConnection(
                pgHost.getText().trim(),
                pgPort.getText().trim(),
                pgDatabase.getText().trim(),
                pgUser.getText().trim(),
                pgPassword.getText()
        );
    }

    private boolean validarCampos() {

        if (pgHost.getText().isBlank()) {
            log("Informe o Host do PostgreSQL!");
            return false;
        }
        if (pgPort.getText().isBlank()) {
            log("Informe a Porta do PostgreSQL!");
            return false;
        }
        if (pgDatabase.getText().isBlank()) {
            log("Informe o Banco de Dados!");
            return false;
        }
        if (pgUser.getText().isBlank()) {
            log("Informe o Usuário!");
            return false;
        }
        if (txtPastaSaida.getText().isBlank()) {
            log("Selecione a pasta de destino dos arquivos!");
            return false;
        }
        if (!chkProdutos.isSelected() &&
                !chkClientes.isSelected() &&
                !chkFornecedores.isSelected()) {
            log("Selecione ao menos um item para exportar!");
            return false;
        }

        return true;
    }

    private void log(String mensagem) {
        String hora = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        txtLog.appendText("[" + hora + "] " + mensagem + "\n");

        lblStatus.setText(mensagem);
    }
}