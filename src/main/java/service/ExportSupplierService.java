package service;

import model.Supplier;
import repository.SupplierRepository;
import util.FormatTxt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.util.List;

public class ExportSupplierService {

    public static int exportar(Connection connection,
                               String pastaSaida,
                               StringBuilder log) throws Exception {
        String caminhoArquivo = pastaSaida + "\\fornecedor.txt";

        SupplierRepository repository = new SupplierRepository(connection);
        List<Supplier> list = repository.buscarTodos();

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(caminhoArquivo, false))) {
            for (Supplier f : list) {
                String linha = montarLinha(f);
                writer.write(linha);
                writer.write("\r\n");
            }
        }

        log.append("Total de fornecedores exportados: ")
                .append(list.size())
                .append("\n");

        return list.size();
    }

    private static String montarLinha(Supplier f) {
        StringBuilder sb = new StringBuilder();

        sb.append(FormatTxt.codigoChar5(f.getCodigo()));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getRazaoSocial(), 40));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getNomeFantasia(), 40));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getContato(), 40));
        sb.append("|");
        sb.append(FormatTxt.formatarTipoPessoa(f.getTipoPessoa()));
        sb.append("|");
        sb.append(FormatTxt.varchar(FormatTxt.limparDocumento(f.getCnpj()), 25));
        sb.append("|");
        sb.append(FormatTxt.varchar(FormatTxt.limparDocumento(f.getCpf()), 25));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getInscricaoEstadual(), 20));
        sb.append("|");
        sb.append(FormatTxt.formatarCep(f.getCep()));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getComplemento(), 40));
        sb.append("|");
        sb.append(FormatTxt.varchar(FormatTxt.limparTelefone(f.getTelefone1()), 15));
        sb.append("|");
        sb.append(FormatTxt.varchar(FormatTxt.limparTelefone(f.getTelefone2()), 15));
        sb.append("|");
        sb.append("   ");
        sb.append("|");
        sb.append("N");
        sb.append("|");
        sb.append(FormatTxt.data(f.getDataCadastro()));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getObservacoes(), 40));
        sb.append("|");
        sb.append("N");
        sb.append("|");
        sb.append("1");
        sb.append("|");
        sb.append("30");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("   ");
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getUf(), 2));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getCidade(), 40));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getBairro(), 40));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getLogradouro(), 60));
        sb.append("|");
        sb.append("01");
        sb.append("|");
        sb.append("1");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getSite(), 40));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getEmail(), 35));
        sb.append("|");
        sb.append(FormatTxt.varchar(f.getInscricaoMunicipal(), 20));
        sb.append("|");
        sb.append("0");
        sb.append("|");
        sb.append("");
        sb.append("|");
        sb.append("1058");
        sb.append("|");
        sb.append("0");

        return sb.toString();
    }
}
