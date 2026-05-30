package service;

import util.FormatTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExportProdPrecService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {
        String caminhoArquivo = pastaSaida + "\\prodprec.txt";
        int total = 0;

        String sql = """
                SELECT
                                mat_001                         AS codigo,
                                COALESCE(mat_008,       0)      AS preco_varejo,
                                COALESCE(valor_promocao,0)      AS preco_promo_varejo,
                                COALESCE(valor_atacado, 0)      AS preco_atacado
                            FROM materiais
                            ORDER BY mat_001
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(caminhoArquivo, false))) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                double precoVarejo = rs.getDouble("preco_varejo");
                double precoPromoVarejo = rs.getDouble("preco_promo_varejo");
                double precoAtacado = rs.getDouble("preco_atacado");

                writer.write(montarLinha(codigo, "09", precoVarejo));
                writer.write("\r\n");
                writer.write(montarLinha(codigo, "10", precoPromoVarejo));
                writer.write("\r\n");
                writer.write(montarLinha(codigo, "11", precoAtacado));
                writer.write("\r\n");
                writer.write(montarLinha(codigo, "12", 0.0));
                writer.write("\r\n");

                total += 4;
            }
        }
        log.append("Total de precos exportados: ")
                .append(total)
                .append("\n");

        return total;
    }

    private static String montarLinha(int codigo,
                                      String codPreco,
                                      double preco) {

        StringBuilder sb = new StringBuilder();

        sb.append(FormatTxt.codigoChar5(codigo));
        sb.append("|");
        sb.append(codPreco);
        sb.append("|");
        sb.append(FormatTxt.numerico152(preco));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");

        return sb.toString();
    }
}
