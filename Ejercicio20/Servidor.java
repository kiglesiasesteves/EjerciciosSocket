import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            // InetSocketAddress nos permite encapsular dirección y puerto en un único punto
            // en caso de que nos sea útil, pero necesitamos una línea más
            // que utilizando directamente el constructor del socket
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
            // Creamos un nuevo ServerSocket
            ServerSocket servidor = new ServerSocket();
            // Vinculamos el ServerSocket a la dirección y puerto especificados
            servidor.bind(dir);

            System.out.println("Esperando conexiones...");
            // Aceptamos una conexión entrante (bloqueante)
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado");

            // Obtenemos el flujo de salida del socket para enviar datos al cliente
            OutputStream salida = socket.getOutputStream();
            // Autoflush = true envía los datos inmediatamente para vaciar el buffer de PrintWriter
            PrintWriter escritor = new PrintWriter(salida, true);
            // Enviamos tres mensajes al cliente
            escritor.println("Mensaje 1 desde servidor");
            escritor.println("Mensaje 2 desde servidor");
            escritor.println("Mensaje 3 desde servidor");

            // Cerramos el socket y el servidor después de terminar la comunicación
            socket.close();
            servidor.close();
        } catch (Exception e) {
            // Imprimimos cualquier excepción que ocurra
            e.printStackTrace();
        }
    }
}