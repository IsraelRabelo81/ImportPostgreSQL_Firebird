package service;

import util.FormatTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExportEstoqueService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {
        String caminhoArquivo = pastaSaida + "\\estoque.txt";
        int total = 0;

        String sql = """
                SELECT
                                m.mat_001                               AS codigo,
                                GREATEST(
                                    COALESCE(SUM(sem.quantidade), 0), 0
                                )                                       AS estoque
                            FROM materiais m
                            LEFT JOIN setor_estoque_material sem
                                ON  sem.id_material = m.mat_001
                                AND sem.id_empresa  = 1
                            GROUP BY m.mat_001
                            ORDER BY m.mat_001
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(caminhoArquivo, false))) {

            while (rs.next()) {

                int codigo = rs.getInt("codigo");
                double estoque = rs.getDouble("estoque");

                String linha = montarLinha(codigo, estoque);
                writer.write(linha);
                writer.write("\r\n");
                total ++;
            }
        }

        log.append("Total de estoques exportados: ")
                .append(total)
                .append("\n");

        return total;
    }

    private static String montarLinha(int codigo, double estoque) {
        StringBuilder sb = new StringBuilder();

        sb.append("001");
        sb.append("|");
        sb.append(FormatTxt.codigoChar5(codigo));
        sb.append("|");
        sb.append(FormatTxt.numerico153(estoque));
        sb.append("|");
        sb.append(FormatTxt.numerico153(estoque));

        return sb.toString();
    }
}
