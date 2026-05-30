package service;

import util.FormatTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExportEmpSupplierService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {
        String caminhoArquivo = pastaSaida + "\\empforn.txt";
        int total = 0;

        String sql = """
                SELECT id_fornecedor AS codigo
                FROM fornecedor
                ORDER BY id_fornecedor
                """;

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(caminhoArquivo, false))) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");

                String linha = montarLinha(codigo);
                writer.write(linha);
                writer.write("\r\n");
                total++;
            }
        }

        log.append("Total de empresa/fornecedor exportados: ")
                .append(total)
                .append("\n");

        return total;
    }

    private static String montarLinha(int codigo) {
        StringBuilder sb = new StringBuilder();

        sb.append("001");
        sb.append("|");
        sb.append(FormatTxt.codigoChar5(codigo));
        sb.append("|");
        sb.append("S");

        return sb.toString();
    }
}
