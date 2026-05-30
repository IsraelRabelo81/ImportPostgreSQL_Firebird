package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatTxt {

    private static final DateTimeFormatter FMT_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String codigoChar5(Integer valor) {
        if (valor == null) return "00000";
        return String.format("%05d", valor);
    }

    public static String codigoChar3(Integer valor) {
        if (valor == null || valor == 0) return "000";
        return String.format("%03d", valor);
    }

    public static String varchar(String valor, int tamanho) {
        if (valor == null) return "";
        return valor.length() > tamanho
                ? valor.substring(0, tamanho)
                : valor;
    }

    public static String unidade(String valor) {
        if (valor == null) return "UN ";
        return String.format("%-3.2s", valor);
    }

    public static String numerico52(Double valor) {
        if (valor == null) return "0";
        return String.format("%.2f", valor).replace(".",",");
    }

    public static String numerico153(Double valor) {
        if (valor == null) return "0";
        return String.format("%.3f", valor).replace(".",",");
    }

    public static String numerico50(Integer valor) {
        if (valor == null) return "0";
        return String.valueOf(valor);
    }

    public static String data(LocalDateTime valor) {
        if (valor == null) return "  /  /    ";
        return valor.format(FMT_DATA);
    }

    public static String simNao(Boolean valor) {
        if (valor == null) return "N";
        return valor ? "S" : "N";
    }

    public static String ncm(String valor) {
        if (valor == null || valor.isBlank()) return "00000000";
        String limpo = valor.replaceAll("[^0-9]", "");
        return String.format("%-8s", limpo).substring(0, 8);
    }

    public static String formatarCodigoBarras(String valor) {
        if (valor == null || valor.isBlank()) {
            return "00000000000000";
        }

        String limpo = valor.trim().replaceAll("[^0-9]", "");
        if (limpo.isEmpty()) return "00000000000000";

        if (limpo.length() >= 14) return limpo.substring(0, 14);

        return String.format("%014d", Long.parseLong(limpo));
    }

    public static String dataLocalDate(LocalDate valor) {
        if (valor == null) return "  /  /    ";
        return valor.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String numerico152(Double valor) {
        if (valor == null) return "0";
        return String.format("%.2f", valor).replace(".", ",");
    }

    public static String definirTributacao(int csoCodigo) {
        return switch (csoCodigo) {
            case 500 -> "A";
            case 400,
                 300 -> "I";
            case 900 -> "N";
            default -> "T";
        };
    }

    public static String definirCst(int csoCodigo, int cstCons) {
        if (cstCons > 0) {
            return String.format("%03d", cstCons);
        }

        return String.format("%03d", csoCodigo);
    }

    public static String formatarTipoPessoa(String tipo) {
        if (tipo == null || tipo.isBlank()) return "J";
        return tipo.trim().toUpperCase().startsWith("F") ? "F" : "J";
    }

    public static String limparDocumento(String doc) {
        if (doc == null || doc.isBlank()) return "";
        return doc.trim().replaceAll("[^0-9]", "");
    }

    public static String formatarCep(String cep) {
        if (cep == null || cep.isBlank()) return "        ";
        String limpo = cep.trim().replaceAll("[^0-9]", "");
        if (limpo.length() >= 8) return limpo.substring(0, 8);
        return String.format("%-8s", limpo);
    }

    public static String limparTelefone(String tel) {
        if (tel == null || tel.isBlank()) return "";
        return tel.trim().replaceAll("[^0-9]", "");
    }
}
