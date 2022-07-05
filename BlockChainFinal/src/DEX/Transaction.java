package DEX;

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



public class Transaction {

		public static transactionData createTransaction(ArrayList <operationData> op) throws IOException
    	{							// алгоритм хеширования для создания id транзакции
	   	 transactionData tx = new transactionData(); 			// создаем объект состоящий из данных класса
 	     int hashCode = op.hashCode();					
 	     byte[] hashCodeInBytes =  ByteBuffer.allocate(4).putInt(hashCode).array();
 	     
 	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		outputStream.write( hashCodeInBytes );					//hash в байтах поля с массивом значений
		outputStream.write( nounce().getBytes());				//hash в байтах поля с nounce
		
		byte transactionID[] = outputStream.toByteArray();		// соединям значения в одну транзакцию
		
		tx.transactionID = hash.bytesToHex(hash.hashFunction(transactionID)); // хешируем байты и переводим их в String
        tx.operationData = op;
        
        return tx; // Возвращаем объект набором операций
      
    	}
	
	public static ArrayList<operationData> addToTransaction(operationData op, ArrayList <operationData> opd) 	// добавляем операцию в массив транзакции.
    {
		opd.add(op);
		return opd; 
    }
	public static String nounce() 						// Задаем рандомное значение nounce
	{
	    byte[] array = new byte[77]; 
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
		return generatedString;
	}
}