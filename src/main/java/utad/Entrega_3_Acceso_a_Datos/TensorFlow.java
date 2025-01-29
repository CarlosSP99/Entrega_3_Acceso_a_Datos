package utad.Entrega_3_Acceso_a_Datos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TensorFlow {
    public static void main(String[] args) throws IOException {
        // Ruta al archivo del modelo
        String modelPath = "ruta_a_tu_modelo.pb";

        // Leer el modelo en bytes
        byte[] graphDef = Files.readAllBytes(Paths.get(modelPath));


    }
}
