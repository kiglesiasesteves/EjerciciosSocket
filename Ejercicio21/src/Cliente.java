import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        // Dirección IP y puerto del servidor
        InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

        try {
            // Se crea el socket y se conecta al servidor
            Socket socket = new Socket();
            socket.connect(dir);
            System.out.println("Conectado al servidor");

            // Flujo de entrada para recibir mensajes del servidor
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Flujo de salida para enviar mensajes al servidor
            OutputStream salida = socket.getOutputStream();
            PrintWriter escritor = new PrintWriter(salida, true);

            // Scanner para leer mensajes desde la consola
            Scanner scanner = new Scanner(System.in);

            // Enviar y recibir mensajes
            for (int i = 0; i <4; i++) {
                System.out.print("Introduce el mensaje " + (i + 1) + " para el servidor: ");
                String mensaje = scanner.nextLine();  // Leer el mensaje del usuario
                escritor.println(mensaje);  // Enviar el mensaje al servidor

                // Leer la respuesta del servidor y mostrarla
                String respuesta = lector.readLine();
                System.out.println("Servidor dice: " + respuesta);
            }

            // Cerrar la conexión
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
