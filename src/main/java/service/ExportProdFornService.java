package service;

import util.FormatTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ExportProdFornService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {
        String caminhoArquivo = pastaSaida + "\\prodforn.txt";
        int total = 0;

        String sql = """
                SELECT
                                m.mat_001                           AS codigo_produto,
                                COALESCE(ml.id_fornecedor,  0)      AS codigo_fornecedor,
                                ml.data_compra,
                                COALESCE(ml.preco_compra,   0)      AS preco_compra,
                                COALESCE(m.icms,            0)      AS icms,
                                COALESCE(m.pis,             0)      AS pis,
                                COALESCE(m.cofins,          0)      AS cofins,
                                COALESCE(m.iva,             0)      AS iva,
                                COALESCE(m.pis_codigo_entrada, 0)   AS pis_cst,
                                COALESCE(m.cof_codigo_entrada, 0)   AS cofins_cst,
                                COALESCE(m.mat_012,         0)      AS preco_medio,
                                COALESCE(u.uni_002,        'UN')    AS unidade,
                                COALESCE(mf.codigo_fornecedor, '')  AS ref_fornecedor
                            FROM materiais m
                            LEFT JOIN materiais_lista_fornecedores ml
                                ON  ml.id_material  = m.mat_001
                                AND ml.id_empresa   = 1
                            LEFT JOIN unidades u
                                ON  u.uni_001       = m.uni_001
                            LEFT JOIN materiais_fornecedor mf
                                ON  mf.id_material  = m.mat_001
                                AND mf.id_fornecedor = ml.id_fornecedor
                                AND mf.id_empresa   = 1
                            ORDER BY m.mat_001, ml.id_fornecedor
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();
             BufferedWriter writer = new BufferedWriter(
                     new FileWriter(caminhoArquivo, false))) {
            while (rs.next()) {

                int codProduto = rs.getInt("codigo_produto");
                int codFornecedor = rs.getInt("codigo_fornecedor");
                String refFornecedor = rs.getString("ref_fornecedor");
                double precoCompra = rs.getDouble("preco_compra");
                double icms = rs.getDouble("icms");
                double pis = rs.getDouble("pis");
                double cofins = rs.getDouble("cofins");
                double iva = rs.getDouble("iva");
                int pisCst = rs.getInt("pis_cst");
                int cofinsCst = rs.getInt("cofins_cst");
                double precoMedio = rs.getDouble("preco_medio");
                String unidade = rs.getString("unidade");

                LocalDate dataCompra = null;
                java.sql.Date sqlDate = rs.getDate("data_compra");
                if (sqlDate != null) {
                    dataCompra = sqlDate.toLocalDate();
                }

                String linha = montarLinha(
                        codProduto, codFornecedor, refFornecedor,
                        precoCompra, icms, pis, cofins, iva,
                        pisCst, cofinsCst, precoMedio, unidade, dataCompra
                );

                writer.write(linha);
                writer.write("\r\n");
                total++;
            }
        }

        log.append("Total de produto/fornecedor exportados: ")
                .append(total)
                .append("\n");

        return total;
    }

    private static String montarLinha(
            int codProduto, int codFornecedor,
            String refFornecedor, double precoCompra,
            double icms, double pis, double cofins,
            double iva, int pisCst, int cofinsCst,
            double precoMedio, String unidade, LocalDate dataCompra) {

        StringBuilder sb = new StringBuilder();

        sb.append(FormatTxt.codigoChar5(codProduto));
        sb.append("|");
        sb.append(FormatTxt.codigoChar5(codFornecedor));
        sb.append("|");
        sb.append(FormatTxt.numerico52(icms));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.numerico152(precoCompra));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.numerico152(precoCompra));
        sb.append("|");
        sb.append("   ");
        sb.append("|");
        sb.append(FormatTxt.varchar(refFornecedor, 20));
        sb.append("|");
        sb.append("T");
        sb.append("|");
        sb.append(FormatTxt.dataLocalDate(dataCompra));
        sb.append("|");
        sb.append(FormatTxt.numerico152(precoCompra));
        sb.append("|");
        sb.append(FormatTxt.numerico52(precoMedio));
        sb.append("|");
        sb.append("1");
        sb.append("|");
        sb.append(FormatTxt.unidade(unidade));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("S");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.numerico153(iva));
        sb.append("|");
        sb.append(String.format("%02d", pisCst));
        sb.append("|");
        sb.append(FormatTxt.numerico52(pis));
        sb.append("|");
        sb.append("100,00");
        sb.append("|");
        sb.append(String.format("%02d", cofinsCst));
        sb.append("|");
        sb.append(FormatTxt.numerico52(cofins));
        sb.append("|");
        sb.append("100,00");
        sb.append("|");
        sb.append("0");

        return sb.toString();
    }
}
