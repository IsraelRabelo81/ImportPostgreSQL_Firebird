package service;

import util.FormatTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExportCodBarrasService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {

        String caminhoArquivo = pastaSaida + "\\codaux.txt";
        int total = 0;


        String sql = """
            SELECT
                mat_001     AS codigo_produto,
                mat_004     AS codigo_barras
            FROM materiais
            WHERE mat_004 IS NOT NULL
            ORDER BY mat_001
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(caminhoArquivo, false))) {
            while (rs.next()) {
                String codigoBarras = rs.getString("codigo_barras");
                int codigoProduto = rs.getInt("codigo_produto");

                if (codigoBarras == null || codigoBarras.isBlank()) {
                    continue;
                }
                String linha = montarLinha(codigoBarras, codigoProduto);
                writer.write(linha);
                writer.write("\r\n");
                total++;
            }
        }


        /* Aqui teste simples de consulta e Debug
        String sql = "SELECT mat_001, mat_004 FROM materiais LIMIT 20";
        try (PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String cod = rs.getString("mat_004");
                int matCod = rs.getInt("mat_001");

                System.out.println(
                        "DEBUG → mat_001: [" + matCod + "]" +
                                " | mat_004: [" + cod + "]" +
                                " | isNull: " + (cod == null) +
                                " | isEmpty: " + (cod != null && cod.isEmpty()) +
                                " | isBlank: " + (cod != null && cod.isBlank()) +
                                " | length: " + (cod != null ? cod.length() : "NULL")
                );
            }
        }

         */

        log.append("Total de códigos de barras exportados: ")
                .append(total)
                .append("\n");

        return total;
    }

    private static String montarLinha(String codigoBarras,
                                      Integer codigoProduto) {
        StringBuilder sb = new StringBuilder();

        sb.append(FormatTxt.formatarCodigoBarras(codigoBarras));
        sb.append("|");
        sb.append(FormatTxt.codigoChar5(codigoProduto));
        sb.append("|");
        sb.append("N");

        return sb.toString();
    }
}
