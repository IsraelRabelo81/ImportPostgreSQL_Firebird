package repository;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Product> buscarTodos() throws Exception {
        List<Product> lista = new ArrayList<>();

        String sql = """
            SELECT
                        m.mat_001                           AS codigo,
                        m.mat_003                           AS descricao,
                        COALESCE(m.mat_004,      '')        AS descricao_complementar,
                        COALESCE(u.uni_002,    'UN')        AS unidade,
                        COALESCE(m.id_fornecedor,0)         AS cod_fornecedor,
                        COALESCE(m.ipi,          0)         AS ipi,
                        COALESCE(m.mat_014,      0)         AS estoque_minimo,
                        COALESCE(m.mat_015,      0)         AS estoque_maximo,
                        COALESCE(m.cat_001,      0)         AS categoria,
                        COALESCE(m.b_peso_balanca, false)   AS fracionado,
                        COALESCE(m.b_atacarejo,    false)   AS atacado,
                        COALESCE(m.b_peso_balanca, false)   AS balanca,
                        COALESCE(m.peso_liquido,   0)       AS peso_liquido,
                        COALESCE(m.peso_bruto,     0)       AS peso_bruto,
                        m.dat_001_1                         AS data_cadastro,
                        COALESCE(m.mat_011,       '')       AS comentario,
                        COALESCE(m.marca,         '')       AS marca,
                        COALESCE(m.mat_019,       '')       AS ref_fabricante,
                        COALESCE(m.ncm,           '')       AS ncm,
                        COALESCE(m.dias_validade,  0)       AS dias_validade,
                        COALESCE(m.nut_001,        0)       AS cod_nutricional,
                        COALESCE(m.b_exige_alterar_preco_venda, false) AS preco_aberto,
                        COALESCE(m.b_combustivel,  false)   AS combustivel,
                        COALESCE(m.utiliza_kit,    false)   AS produto_composto,
                        COALESCE(m.gen_codigo,    '0')      AS gen_codigo_ncm,
                        COALESCE(m.mat_008,        0)       AS preco_venda,
                        COALESCE(m.valor_atacado,  0)       AS valor_atacado
                    FROM materiais m
                    LEFT JOIN unidades u ON u.uni_001 = m.uni_001
                    ORDER BY m.mat_001
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();

                p.setCodigo(rs.getInt("codigo"));
                p.setDescricao(rs.getString("descricao"));
                p.setDescricaoComplementar(rs.getString("descricao_complementar"));
                p.setUnidade(rs.getString("unidade"));
                int codForncedor = rs.getInt("cod_fornecedor");
                p.setCodigoFornecedor(rs.wasNull() ? 0 : codForncedor);
                p.setIpi(rs.getDouble("ipi"));
                p.setEstoqueMinimo(rs.getDouble("estoque_minimo"));
                p.setEstoqueMaximo(rs.getDouble("estoque_maximo"));
                int categoria = rs.getInt("categoria");
                p.setCategoria(rs.wasNull() ? 0 : categoria);
                p.setFracionado(rs.getBoolean("fracionado"));
                p.setAtacado(rs.getBoolean("atacado"));
                p.setBalanca(rs.getBoolean("balanca"));
                p.setPesoLiquido(rs.getDouble("peso_liquido"));
                p.setPesoBruto(rs.getDouble("peso_bruto"));

                var ts = rs.getTimestamp("data_cadastro");
                if (ts != null) {
                    p.setDataCadastro(ts.toLocalDateTime());
                }

                p.setComentario(rs.getString("comentario"));
                p.setMarca(rs.getString("marca"));
                p.setRefFabricante(rs.getString("ref_fabricante"));
                p.setNcm(rs.getString("ncm"));
                p.setDiasValidade(rs.getInt("dias_validade"));
                p.setCodNutricional(rs.getInt("cod_nutricional"));
                p.setPrecoAberto(rs.getBoolean("preco_aberto"));
                p.setCombustivel(rs.getBoolean("combustivel"));
                p.setProdutoComposto(rs.getBoolean("produto_composto"));
                p.setGenCodigoNcm(rs.getString("gen_codigo_ncm"));
                p.setPrecoVenda(rs.getDouble("preco_venda"));
                p.setValorAtacado(rs.getDouble("valor_atacado"));

                lista.add(p);
            }
        }

        return lista;
    }

}
