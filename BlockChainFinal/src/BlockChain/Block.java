package BlockChain;


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



public class Block {
 

		public static blockData createBlock(ArrayList <transactionData> txd, String previousBlockHash) throws IOException	 // создание блока
    	{								
	   	 blockData bd = new blockData(); 			
 	     int hashCode = txd.hashCode();					
 	     byte[] hashCodeInBytes =  ByteBuffer.allocate(4).putInt(hashCode).array();
 	     
 	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		outputStream.write( hashCodeInBytes );					  //hash в байтах поля с массивом значений

		
		byte blockID[] = outputStream.toByteArray();			  // соединям значения в одну транзакцию
		
		bd.blockID = hash.bytesToHex(hash.hashFunction(blockID)); // хешируем байты и переводим их в String
        bd.transactionData = txd;
        bd.previousBlockHash = previousBlockHash;
        
        return bd; // Возвращаем объект набором операций
      
    	}
	
	public static ArrayList<transactionData> setBlock(transactionData tx, ArrayList <transactionData> bd) // добавляем транзакции в блок
	throws InvalidKeyException, NoSuchAlgorithmException, SignatureException 	
    {	
		ArrayList <Boolean> trueCount = new ArrayList <Boolean>();
		boolean result = false;
		int opSize = tx.operationData.size();
		for(int b = 0; b< opSize; b++)
		{
			result = Operation.verifyOperation(tx.operationData.get(b));
			trueCount.add(result);
		}
		//System.out.println("Count "+ trueCount); 	// проверка операций
		if(trueCount.contains(false)) // если ни одна операция в блоке не была подтверждена то пустой блок не будет добавляться в историю
		{
			result = false; 
		}else {	result = true;}
		if(result == true)
			{
				bd.add(tx); // если транзакция составлена корректно то она добавляется в блок.
			} 
			return bd; 
    }

	public static void transactionDataOutput(ArrayList<transactionData> bd) 	// добавляем транзакции в блок
    {	
			for(int i = 0; i<bd.size(); i++)
			{
			System.out.println("Transaction " + i + ": " + bd.get(i).transactionID);

			}
	}
	public static ArrayList<transactionData> txSend(transactionData tx, ArrayList<transactionData> block)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException 	// добавляем транзакции в блок
    {	
   	 return block = Block.setBlock(tx, block);			    													
	}
}