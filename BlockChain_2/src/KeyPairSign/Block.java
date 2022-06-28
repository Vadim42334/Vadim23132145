package KeyPairSign;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.Struct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;


import KeyPairSign.KeyPairSigns.SignerUser;
import KeyPairSign.KeyPairSigns;


public class Block {
 

		public static blockData createBlock(ArrayList <transactionData> txd, String previousBlockHash) throws IOException	 // создание блока
    	{
		 // id блока
		 // хэш прошлого блока
		 // список транзакций из этого блока
		 String SHA3 = "SHA3-512"; 								// алгоритм хеширования для создания id транзакции
	   	 blockData bd = new blockData(); 			// создаем объект состоящий из данных класса
 	     int hashCode = txd.hashCode();					
 	     byte[] hashCodeInBytes =  ByteBuffer.allocate(4).putInt(hashCode).array();
 	     
 	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		outputStream.write( hashCodeInBytes );					//hash в байтах поля с массивом значений

		
		byte blockID[] = outputStream.toByteArray();		// соединям значения в одну транзакцию
		
		bd.blockID = hash.bytesToHex(hash.hashFunction(blockID, SHA3)); // хешируем байты и переводим их в String
        bd.transactionData = txd;
        
        return bd; // Возвращаем объект набором операций
      
    	}
	
	public static ArrayList<transactionData> setBlock(transactionData tx, ArrayList <transactionData> bd) 	// добавляем транзакции в блок
    {
		bd.add(tx);
		return bd; 
    }

	public static void transactionDataOutput(ArrayList<transactionData> bd) 	// добавляем транзакции в блок
    {	
			for(int i = 0; i<bd.size(); i++)
			{
			System.out.println("Transaction " + i + ": " + bd.get(i).transactionID);

			}
	}
}