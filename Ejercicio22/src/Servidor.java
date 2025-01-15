import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        // Definir el puerto en el que el servidor escuchará conexiones
        int puerto = 6666;

        // Se utiliza try-with-resources para garantizar el cierre correcto del ServerSocket
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexión en el puerto " + puerto + "...");

            // Esperar y aceptar una conexión de un cliente
            try (Socket clienteSocket = serverSocket.accept();
                 // Flujo de entrada para recibir datos del cliente
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                 // Flujo de salida para enviar respuestas al cliente
                 PrintWriter salida = new PrintWriter(clienteSocket.getOutputStream(), true)) { // 'true' habilita autoflush

                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                // Leer la cadena de números enviada por el cliente
                String numerosStr = entrada.readLine();

                // Separar la cadena en un array de números usando la coma como delimitador
                String[] numerosArray = numerosStr.split(",");

                // Inicializar la suma en 0
                int suma = 0;

                // Recorrer el array de números y sumarlos
                for (String num : numerosArray) {
                    suma += Integer.parseInt(num.trim()); // Convertir el número a entero y sumarlo
                }

                // Enviar el resultado de la suma de vuelta al cliente
                salida.println("La suma de los números es: " + suma);

                System.out.println("Comunicación finalizada.");
            }
        } catch (IOException e) {
            // Manejo de excepciones en caso de error con los sockets
            e.printStackTrace();
            System.out.println("NO se ha podido conectar");
        }
    }
}
