package config;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigService {

    private static final String PATH_CONFIG = "confi.ini";

    public static void inicializar() {
        createFile(PATH_CONFIG);
    }

    public static void createFile(String pathFile) {
        File file = new File(pathFile);

        try{
            if(!file.exists()) {
                if(file.createNewFile()){
                    writeStandardContent(file);
                    System.out.println("Arquivo config criado com sucesso!");
                } else {
                    System.out.println("Falha ao criar o arquivo!");
                }
            } else {
                readFile(pathFile);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> readFile(String path) throws Exception {
        Map<String, String> config = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String section = "";
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) continue;

                if (line.startsWith("[") && line.endsWith("]")) {
                    section = line.replace("[", "").replace("]","");
                    continue;
                }

                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    String key = section + "." + parts[0].trim();
                    String value = parts[1].trim();
                    config.put(key, value);
                }
            }
        }

        return config;

    }

    public static void saveFile(String path, Map<String, String> config) throws Exception {

        try (FileWriter writer = new FileWriter(path)){
            writer.write("[POSTGRES]\n");
            writer.write("host=" + config.getOrDefault("POSTGRES.host", "localhost") + "\n");
            writer.write("port=" + config.getOrDefault("POSTGRES.port", "5432") + "\n");
            writer.write("database=" + config.getOrDefault("POSTGRES.database", "database") + "\n");
            writer.write("user=" + config.getOrDefault("POSTGRES.user", "user") + "\n");
            writer.write("password=" + config.getOrDefault("POSTGRES.password", "") + "\n");
            writer.write("\n");
            writer.write("[EXPORTACAO]\n");
            writer.write("pasta_saida=" + config.getOrDefault("EXPORTACAO.pasta_saida", "") + "\n");

        }

    }

    public static String get(String key) {
        try {
            Map<String, String> config = readFile(PATH_CONFIG);
            return config.getOrDefault(key, "");
        } catch (Exception e) {
            return "";
        }
    }

    private static void writeStandardContent(File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("# Configurações do Exportador SGE\n\n");
            writer.write("[POSTGRES]\n");
            writer.write("host=localhost\n");
            writer.write("port=5433\n");
            writer.write("database=SOFTMOBILE\n");
            writer.write("user=postgres\n");
            writer.write("password=\n");
            writer.write("\n");
            writer.write("[EXPORTACAO]\n");
            writer.write("pasta_saida\n");

        }
    }

}