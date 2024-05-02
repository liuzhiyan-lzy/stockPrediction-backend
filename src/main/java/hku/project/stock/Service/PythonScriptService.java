package hku.project.stock.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yann
 * @date 2024/05/02
 **/
@Service
public class PythonScriptService {

    @Value("${hku.project.python_file_path}")
    private String pythonFilePath;

    public String runPythonScript(String propertyJson) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", pythonFilePath, propertyJson);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                // 处理非零退出代码
                return "Error executing script";
            }

            return output.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
