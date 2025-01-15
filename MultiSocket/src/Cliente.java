import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        // Dirección IP y puerto del servidor al que se quiere conectar
        String host = "127.0.0.1";  // Dirección IP local del servidor
        int puerto = 6666;          // Puerto en el que el servidor está escuchando

        try (Socket socket = new Socket(host, puerto); // Crear conexión con el servidor
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Para leer los datos del servidor
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) { // Para enviar datos al servidor

            // Mostrar en consola que se ha conectado correctamente
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Enviar un mensaje al servidor
            output.println("¡Hola, servidor!");

            // Recibir la respuesta del servidor
            String respuesta = input.readLine();
            System.out.println("Respuesta del servidor: " + respuesta); // Mostrar la respuesta

        } catch (IOException e) {
            // En caso de error, mostrar la traza de la excepción
            e.printStackTrace();
            System.out.println("No se pudo conectar al servidor.");
        }
    }
}
