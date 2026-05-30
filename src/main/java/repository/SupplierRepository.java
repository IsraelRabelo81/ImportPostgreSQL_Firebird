package repository;

import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepository {

    private final Connection connection;

    public SupplierRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Supplier> buscarTodos() throws Exception {
        List<Supplier> list = new ArrayList<>();

        String sql = """
                SELECT
                                id_fornecedor                           AS codigo,
                                COALESCE(razao_social,      '')         AS razao_social,
                                COALESCE(nome_fantasia,     '')         AS nome_fantasia,
                                COALESCE(nome_contato1,     '')         AS contato,
                                COALESCE(tipo_pessoa,       'J')        AS tipo_pessoa,
                                COALESCE(cnpj,              '')         AS cnpj,
                                COALESCE(cpf,               '')         AS cpf,
                                COALESCE(inscricao_estadual,'')         AS inscricao_estadual,
                                COALESCE(endereco_cep,      '')         AS cep,
                                COALESCE(endereco_complemento, '')      AS complemento,
                                COALESCE(telefone1,         '')         AS telefone1,
                                COALESCE(telefone2,         '')         AS telefone2,
                                data_cadastro,
                                COALESCE(observacoes,       '')         AS observacoes,
                                COALESCE(endereco_uf,       '')         AS uf,
                                COALESCE(endereco_cidade,   '')         AS cidade,
                                COALESCE(endereco_bairro,   '')         AS bairro,
                                COALESCE(endereco_logradouro, '')       AS logradouro,
                                COALESCE(site,              '')         AS site,
                                COALESCE(email,             '')         AS email,
                                COALESCE(inscricao_municipal, '')       AS inscricao_municipal
                            FROM fornecedor
                            ORDER BY id_fornecedor
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Supplier f = new Supplier();

                f.setCodigo(rs.getInt("codigo"));
                f.setRazaoSocial(rs.getString("razao_social"));
                f.setNomeFantasia(rs.getString("nome_fantasia"));
                f.setContato(rs.getString("contato"));
                f.setTipoPessoa(rs.getString("tipo_pessoa"));
                f.setCnpj(rs.getString("cnpj"));
                f.setCpf(rs.getString("cpf"));
                f.setInscricaoEstadual(rs.getString("inscricao_estadual"));
                f.setCep(rs.getString("cep"));
                f.setComplemento(rs.getString("complemento"));
                f.setTelefone1(rs.getString("telefone1"));
                f.setTelefone2(rs.getString("telefone2"));

                var ts = rs.getTimestamp("data_cadastro");
                if (ts != null) {
                    f.setDataCadastro(ts.toLocalDateTime());
                }

                f.setObservacoes(rs.getString("observacoes"));
                f.setUf(rs.getString("uf"));
                f.setCidade(rs.getString("cidade"));
                f.setBairro(rs.getString("bairro"));
                f.setLogradouro(rs.getString("logradouro"));
                f.setSite(rs.getString("site"));
                f.setEmail(rs.getString("email"));
                f.setInscricaoMunicipal(rs.getString("inscricao_municipal"));

                list.add(f);
            }
        }

        return list;
    }
}
