import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        // Definir la dirección IP del servidor y el puerto
        String host = "127.0.0.1"; // Dirección localhost (servidor en la misma máquina)
        int puerto = 6666; // Puerto en el que escucha el servidor

        // Se utiliza try-with-resources para manejar la conexión de manera segura
        try (
                // Se crea un socket para conectarse al servidor
                Socket socket = new Socket(host, puerto);

                // Se crea un BufferedReader para recibir mensajes del servidor
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // Se crea un PrintWriter para enviar mensajes al servidor
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true) // 'true' activa el autoflush
        ) {
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Se define un mensaje con una serie de números en formato de cadena
            String numeros = "5,10,15,20";

            // Se envía la cadena al servidor
            salida.println(numeros);

            // Se espera una respuesta del servidor
            String respuesta = entrada.readLine();
            System.out.println("Servidor responde: " + respuesta);

            System.out.println("Comunicación finalizada.");
        } catch (IOException e) {
            // Manejo de errores en caso de que no se pueda conectar con el servidor
            e.printStackTrace();
            System.out.println("NO se ha podido conectar");
        }
    }
}
