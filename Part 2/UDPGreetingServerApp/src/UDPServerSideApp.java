import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import serverside.SentenceProcessor;

public class UDPServerSideApp {

    public static void main(String[] args) {

        System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side Application.");

        // Permissible port for this application
        int portNo = 1234;

        try {

            // 1. Bind a DatagramSocket object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);

            // Continually listen for requests
            while (true) {

                // 2. Variable to receive data from the port
                // 65535 is the maximum size for a UDP packet
                byte[] receivedData = new byte[65535];

                // 3. Object representing the packet from the client
                DatagramPacket receivedPacket =
                        new DatagramPacket(receivedData, receivedData.length);

                // 4. Receive the packet
                datagramSocket.receive(receivedPacket);

                // 5. Extract data from the packet
                receivedData = receivedPacket.getData();

                // 6. Further processing
                SentenceProcessor processor =
                        new SentenceProcessor(receivedData);
                String sentence = processor.getSentence();
                System.out.println("\nMessage received: " + sentence + ".\n");

                // More processing
                int totalCharacters = processor.countCharacters();
                byte[] characterData = processor.convertToByteArray(totalCharacters);

                int totalVowels = processor.countVowel();
                byte[] vowelData = processor.convertToByteArray(totalVowels);

                int totalConsonants = processor.countConsonant();
                byte[] consonantData = processor.convertToByteArray(totalConsonants);

                int totalPunctuation = processor.countPunctuation();
                byte[] punctuationData = processor.convertToByteArray(totalPunctuation);

                // 7. Get the client information
                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();
                int sizeOutData = characterData.length;
                int sizeVowelData = vowelData.length;
                int sizeConsonantData = consonantData.length;
                int sizePunctuationData = punctuationData.length;

                // 8. Wrap data into datagram packets
                DatagramPacket characterPacket = new DatagramPacket(characterData,
                        sizeOutData, clientAddress, clientPort);

                DatagramPacket vowelPacket = new DatagramPacket(vowelData,
                        sizeVowelData, clientAddress, clientPort);

                DatagramPacket consonantPacket = new DatagramPacket(consonantData,
                        sizeConsonantData, clientAddress, clientPort);

                DatagramPacket punctuationPacket = new DatagramPacket(punctuationData,
                        sizePunctuationData, clientAddress, clientPort);

                // 9. Reply to the client
                datagramSocket.send(characterPacket);
                datagramSocket.send(vowelPacket);
                datagramSocket.send(consonantPacket);
                datagramSocket.send(punctuationPacket);

                System.out.println("Message sent (totalCharacters): "
                        + totalCharacters + ".\n");

                System.out.println("Message sent (totalVowels): "
                        + totalVowels + ".\n");

                System.out.println("Message sent (totalConsonants): "
                        + totalConsonants + ".\n");

                System.out.println("Message sent (totalPunctuation): "
                        + totalPunctuation + ".\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("UDPServerSideApp: End of program.");
        }
    }
}
