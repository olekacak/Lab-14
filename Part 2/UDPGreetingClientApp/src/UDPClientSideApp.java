import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientSideApp {

	public static void main(String[] args) {
		
		System.out.println("\n\tUDPClientSideApp: Demonstration of UDP "
				+ "Client-Side Application.");
		
		
		
		try {
			
			// 1. Define server port number and address
			int portNo = 1234;
			InetAddress ip = InetAddress.getLocalHost();
			
			// 2. Prepare and transform data into bytes
			String text = "Hai nama saya Upin dan ini adik kembar saya Ipin.\n"
					+ "			Umur kami 5 tahun.\n"
					+ "			Kami tinggal dengan Opah dan seorang kakak yang sangat kami sayangi.";
			
			
			byte buf[] = text.getBytes();

			// 3. Wrap data in datagram packet (constructor no 5)
			DatagramPacket outPacket = 
					new DatagramPacket(buf, buf.length, ip, portNo);
			
			// 4. Create the socket object to transmit the data.
			DatagramSocket datagramSocket = new DatagramSocket();

			// 5. Send datagram packet
			datagramSocket.send(outPacket);
			System.out.println("\n\tSending '" + text + "' (" + text.length() 
				+ ") out on network.");
			
			// 6.New buffer to extract the received data
			byte[] inData = new byte[65535];
			
			// 7. Packet to receive data
			DatagramPacket characterPacket = new DatagramPacket(inData, inData.length);
			DatagramPacket vowelPacket = new DatagramPacket(inData, inData.length);
			DatagramPacket consonantPacket = new DatagramPacket(inData, inData.length);
			DatagramPacket punctuationPacket = new DatagramPacket(inData, inData.length);
			
			// 8. Receive data
			datagramSocket.receive(characterPacket);
			datagramSocket.receive(vowelPacket);
			datagramSocket.receive(consonantPacket);
			datagramSocket.receive(punctuationPacket);
			
			// 9. Extract data
			byte[] characterData = characterPacket.getData();
			byte[] vowelData = vowelPacket.getData();
			byte[] consonantData = consonantPacket.getData();
			byte[] punctuationData = punctuationPacket.getData();
			
			// 10. Further processing
			// Transform data into human readable text
			String lengthCharacter = new String(characterData, 0, characterPacket.getLength());
			String lengthVowel = new String(vowelData, 0, vowelPacket.getLength());
			String lengthConsonant = new String(consonantData, 0, consonantPacket.getLength());
			String lengthPunctuation = new String(punctuationData, 0, punctuationPacket.getLength());
			
			// Display the data received from the server
			System.out.println("\tLength of character from the server is : " + lengthCharacter);
			System.out.println("\tLength of vowel from the server is : " + lengthVowel);
			System.out.println("\tLength of consonant from the server is : " + lengthConsonant);
			System.out.println("\tLength of punctuation from the server is : " + lengthPunctuation);
			
			
			datagramSocket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("\n\tUDPClientSideApp: End of program.");

	}

}
