import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        try {
            // Se crea una dirección IP y un puerto para el servidor
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);


            // Se crea el servidor de sockets
            ServerSocket servidor = new ServerSocket();

            // Se enlaza el servidor a la dirección y puerto especificados
            servidor.bind(dir);

            System.out.println("Esperando conexiones...");

            // Se bloquea la ejecución hasta que un cliente se conecte
            Socket socket = servidor.accept();
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("Cliente conectado");

            // Se obtiene el flujo de salida del socket para enviar datos al cliente
            OutputStream salida = socket.getOutputStream();
            PrintWriter escritor = new PrintWriter(salida, true);

            // Se usa un Scanner para leer la entrada del usuario desde la consola
            Scanner scanner = new Scanner(System.in);

            // Se envían tres mensajes al cliente
            for (int i = 0; i <4; i++) {
                System.out.print("Introduce el mensaje " + (i + 1) + " para el cliente: ");
                String mensaje = scanner.nextLine(); // Se lee el mensaje desde la consola
                escritor.println(mensaje); // Se envía el mensaje al cliente
                String mensajeCliente = lector.readLine(); // Leer mensaje del cliente
                System.out.println("Cliente dice: " + mensajeCliente);

                String respuesta = "Mensaje recibido: " + mensajeCliente;
                escritor.println(respuesta); // Enviar respuesta al cliente
            }


            // Se cierra el socket del cliente y el servidor
            socket.close();
            servidor.close();
        } catch (Exception e) {
            // En caso de error, se imprime la traza de la excepción
            e.printStackTrace();
        }
    }
}
