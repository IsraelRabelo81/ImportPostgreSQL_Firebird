package service;

import model.Product;
import repository.ProductRepository;
import util.FormatTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.List;

public class ExportProdutcService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {
        ProductRepository repository = new ProductRepository(connection);
        List<Product> products = repository.buscarTodos();

        String caminhoArquivo = pastaSaida + "\\produtos.txt";

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(caminhoArquivo, false))) {
            for (Product p : products) {
                String linha = montarLinha(p);
                writer.write(linha);
                writer.newLine();
            }
        }

        log.append("Total de produtos exportados: ")
                .append(products.size())
                .append("\n");

        return products.size();
    }

    private static String montarLinha(Product p) {

        StringBuilder sb = new StringBuilder();

        sb.append(FormatTxt.codigoChar5(p.getCodigo()));
        sb.append("|");
        sb.append(FormatTxt.varchar(p.getDescricao(), 50));
        sb.append("|");
        sb.append(FormatTxt.varchar(p.getDescricao(), 30));
        sb.append("|");
        sb.append(FormatTxt.unidade(p.getUnidade()));
        sb.append("|");
        sb.append(FormatTxt.codigoChar5(p.getCodigoFornecedor()));
        sb.append("|");
        sb.append(FormatTxt.numerico52(p.getIpi()));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.numerico153(p.getEstoqueMinimo()));
        sb.append("|");
        sb.append(FormatTxt.numerico153(p.getEstoqueMaximo()));
        sb.append("|");
        sb.append("1");
        sb.append("|");
        sb.append("1");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append(FormatTxt.codigoChar3(p.getCategoria()));
        sb.append("|");
        sb.append("000");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append("01");
        sb.append("|");
        sb.append("S");
        sb.append("|");
        sb.append(FormatTxt.simNao(p.getFracionado()));
        sb.append("|");
        sb.append("N");
        sb.append("|");
        sb.append(FormatTxt.simNao(p.getAtacado()));
        sb.append("|");
        sb.append(FormatTxt.simNao(p.getBalanca()));
        sb.append("|");
        sb.append(FormatTxt.numerico153(p.getPesoLiquido()));
        sb.append("|");
        sb.append(FormatTxt.numerico153(p.getPesoBruto()));
        sb.append("|");
        sb.append(FormatTxt.data(p.getDataCadastro()));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.simNao(p.getNumSerie()));
        sb.append("|");
        sb.append("  /  /    ");
        sb.append("|");
        sb.append("  /  /    ");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append(FormatTxt.unidade(p.getUnidade()));
        sb.append("|");
        sb.append(FormatTxt.varchar(p.getComentario(), 200));
        sb.append("|");
        sb.append(FormatTxt.varchar(p.getMarca(), 30));
        sb.append("|");
        sb.append(FormatTxt.varchar(p.getRefFabricante(), 20));
        sb.append("|");
        sb.append(FormatTxt.ncm(p.getNcm()));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("%");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.simNao(p.getPrecoAberto()));
        sb.append("|");
        sb.append(FormatTxt.simNao(p.getCombustivel()));
        sb.append("|");
        sb.append(" ");
        sb.append("|");
        sb.append(FormatTxt.simNao(p.getProdutoComposto()));
        sb.append("|");
        sb.append("N");
        sb.append("|");
        sb.append("N");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append("D");
        sb.append("|");
        sb.append(FormatTxt.numerico50(p.getDiasValidade()));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.numerico50(p.getCodNutricional()));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("P");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.ncm(p.getNcm()));
        sb.append("|");
        sb.append(p.getGenCodigoNcm() != null ? p.getGenCodigoNcm() : "0");
        sb.append("|");
        sb.append("000");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");

        return sb.toString();
    }
}
