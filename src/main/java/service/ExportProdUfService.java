package service;

import util.FormatTxt;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExportProdUfService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {

        String caminhoArquivo = pastaSaida + "\\produf.txt";
        int total = 0;

        String sql = """
                SELECT
                                m.mat_001                       AS codigo,
                                COALESCE(m.icms,        0)      AS icms,
                                COALESCE(m.cso_codigo,  0)      AS cso_codigo,
                                COALESCE(m.cst_consumidor, 0)   AS cst_consumidor,
                                COALESCE(m.orm_codigo,  0)      AS orm_codigo,
                                COALESCE(m.redbasecalcicms, 0)  AS red_base_calc
                            FROM materiais m
                            ORDER BY m.mat_001
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(caminhoArquivo, false))) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo");
                double icms = rs.getDouble("icms");
                int csoCodigo = rs.getInt("cso_codigo");
                int cstCons = rs.getInt("cst_consumidor");
                int ormCodigo = rs.getInt("orm_codigo");
                double redBaseCalc = rs.getDouble("red_base_calc");

                String linha = montarLinha(
                        codigo, icms, csoCodigo,
                        cstCons, ormCodigo, redBaseCalc
                );

                writer.write(linha);
                writer.write("\r\n");
                total++;
            }
        }

        log.append("Total de produto/UF exportados: ")
                .append(total)
                .append("\n");

        return total;

    }

    private static String montarLinha(
            int codigo, double icms,
            int csoCodigo, int cstCons,
            int ormCodigo, double redBaseCalc) {

        StringBuilder sb = new StringBuilder();

        sb.append(FormatTxt.codigoChar5(codigo));
        sb.append("|");
        sb.append("PI");
        sb.append("|");
        sb.append(FormatTxt.definirTributacao(csoCodigo));
        sb.append("|");
        sb.append(FormatTxt.definirCst(csoCodigo, cstCons));
        sb.append("|");
        sb.append(FormatTxt.numerico52(icms));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.numerico52(redBaseCalc));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("C");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append(ormCodigo);
        sb.append("|");
        sb.append("C");

        return sb.toString();

    }
}
