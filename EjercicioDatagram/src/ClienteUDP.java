import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // Dirección del servidor
        int puerto = 6666; // Puerto del servidor
        String numeros = "5,10,15,20"; // Números a enviar
        byte[] bufferSalida = numeros.getBytes(); // Convertir a bytes para enviarlos

        try (DatagramSocket socket = new DatagramSocket()) {
            // Obtener la dirección IP del servidor
            InetAddress direccionServidor = InetAddress.getByName(host);

            // Crear un paquete con los datos y enviarlo al servidor
            DatagramPacket paqueteSalida = new DatagramPacket(bufferSalida, bufferSalida.length, direccionServidor, puerto);
            socket.send(paqueteSalida);
            System.out.println("Mensaje enviado al servidor: " + numeros);

            // Preparar un buffer para recibir la respuesta del servidor
            byte[] bufferEntrada = new byte[1024];
            DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);

            // Esperar la respuesta del servidor
            socket.receive(paqueteEntrada);

            // Convertir los datos recibidos en un String
            String respuesta = new String(paqueteEntrada.getData(), 0, paqueteEntrada.getLength());
            System.out.println("Servidor responde: " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
