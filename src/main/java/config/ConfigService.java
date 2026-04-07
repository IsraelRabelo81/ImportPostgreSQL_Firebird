package config;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigService {

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

    private static void writeStandardContent(File file) throws Exception {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("[POSTGRES]\n");
            writer.write("host=localhost\n");
            writer.write("port=5433\n");
            writer.write("database=\n");
            writer.write("user=postgres\n");
            writer.write("password=\n");
            writer.write("\n");
            writer.write("[FIREBIRD]\n");
            writer.write("path=C:/dados/DB_SGE.FDB\n");
            writer.write("user=SYSDBA\n");
            writer.write("password=masterkey\n");

        }
    }

}