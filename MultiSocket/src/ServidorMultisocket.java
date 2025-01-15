import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServidorMultisocket {

    // Utilizamos un ExecutorService para gestionar múltiples hilos
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        int puerto = 6666;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexiones en el puerto " + puerto + "...");

            while (true) {
                // Aceptar una nueva conexión cliente
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                // Enviar la conexión a un hilo para gestionar la comunicación
                // Esta línea muestra que el servidor es multisocket, ya que maneja múltiples conexiones simultáneamente
                pool.execute(new ClienteHandler(clienteSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase para manejar la comunicación con un cliente
    static class ClienteHandler implements Runnable {
        private Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

                String mensaje;
                while ((mensaje = input.readLine()) != null) {
                    System.out.println("Mensaje del cliente: " + mensaje);
                    output.println("Echo: " + mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}