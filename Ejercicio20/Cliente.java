import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        // InetSocketAddress nos permite encapsular dirección y puerto en un único punto
        // en caso de que nos sea útil, pero necesitamos una linea más
        // que utilizando directamente el constructor del socket
        InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
        try {
            // Creamos un nuevo socket
            Socket socket = new Socket();
            // Conectamos el socket a la dirección y puerto especificados
            socket.connect(dir);
            System.out.println("Conectado al servidor");

            // Obtenemos el flujo de entrada del socket para leer datos del servidor
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Leemos tres mensajes del servidor
            for(int i = 0; i < 3; i++) {
                String mensaje = lector.readLine();
                System.out.println("Servidor dice: " + mensaje);
            }

            // Cerramos el socket después de terminar la comunicación
            socket.close();
        } catch (Exception e) {
            // Imprimimos cualquier excepción que ocurra
            e.printStackTrace();
        }
    }
}