import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
    public static void main(String[] args) {
        int puerto = 6666;
        byte[] buffer = new byte[1024]; // Buffer para recibir datos

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("Servidor UDP esperando mensajes en el puerto " + puerto + "...");

            // Crear un paquete para recibir datos del cliente
            DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteEntrada); // Esperar y recibir el paquete del cliente

            // Obtener los datos recibidos y convertirlos en String
            String numerosStr = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
            System.out.println("Mensaje recibido del cliente: " + numerosStr);

            // Procesar los números y calcular la suma
            String[] numerosArray = numerosStr.split(",");
            int suma = 0;

            for (String num : numerosArray) {
                suma += Integer.parseInt(num.trim()); // Convertir a entero y sumar
            }

            // Preparar la respuesta con la suma calculada
            String respuesta = "La suma de los números es: " + suma;
            byte[] bufferSalida = respuesta.getBytes();

            // Obtener la dirección y puerto del cliente para responder
            InetAddress direccionCliente = paqueteEntrada.getAddress();
            int puertoCliente = paqueteEntrada.getPort();

            // Crear un paquete para enviar la respuesta al cliente
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionCliente, puertoCliente);
            socket.send(paqueteSalida); // Enviar la respuesta

            System.out.println("Respuesta enviada al cliente: " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
