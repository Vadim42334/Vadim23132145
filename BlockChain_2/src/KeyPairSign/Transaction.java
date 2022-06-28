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


public class Transaction {
 
    
	public static void main(String args[])
     {
    	 try{/*
         SignerUser signer= new SignerUser ();												// пользователь отправитель
         PublicKey pubKey = signer.getPubKey(); 
         String message = "trans";
         SignerUser.setBalance(10);										 					// устанавливаем баланс пользователя				
         byte[] sign = KeyPairSigns.signData(message.getBytes(), signer.getPrivateKey());	 
         
         SignerUser signer2= new SignerUser ();												// пользователь получатель
         PublicKey pubKey2 = signer2.getPubKey(); 	 	
         String message2 = "trans2";
         byte[] sign2 = KeyPairSigns.signData(message2.getBytes(), signer2.getPrivateKey());	
    	 
         
         int amount = 5;
         int amount2 = 12; // сумма перевода
    	 
    	 
    	 
    	 operationData gg = Operation.createOperation(signer.getPubKey(), signer2.getPubKey(), amount, sign, message);   // Создание операции
    	 operationData gg2 = Operation.createOperation(signer.getPubKey(), signer2.getPubKey(), amount2, sign2, message2); // Создание операции
    	 Operation.verifyOperation(gg, sign, signer, message.getBytes()); //проверка операции
    	 
    	 Operation.printOperation(gg); 										// Вывод всей операции
    	 
    	 operationData [] transactionsData = new operationData[10];          // создаем массив транзакций
    	 transactionsData = transactions(gg,transactionsData); 				// заносим операцию gg в массив транзакций
    	 transactionsData = transactions(gg2, transactionsData);			// заносим операцию gg2 в массив транзакций
    	 
    	 System.out.println("test_1: "+ transactionsData[0].amount); 		
    	 System.out.println("test_2: "+ transactionsData[1].signature); 		// вынимаем значения из транзакций для проверки работы
    	 
    	 System.out.println("Transaction id: "+ createTransaction(transactionsData).transactionID); //выводим id транзакции
 
    	 operationData [] test = new operationData[10];
    	 test = createTransaction(transactionsData).operationData;
    	 System.out.println("Operation data: "+ test[0].amount); 			// Получаем доступ из транзакции к значению из операции (amount)
    	 */
    	 }catch(Exception e)
    	 {
         e.printStackTrace();
    	 }
     	}
		public static transactionData createTransaction(ArrayList <operationData> op) throws IOException
    	{
		
		 String SHA3 = "SHA3-512"; 								// алгоритм хеширования для создания id транзакции
	   	 transactionData tx = new transactionData(); 			// создаем объект состоящий из данных класса
 	     int hashCode = op.hashCode();					
 	     byte[] hashCodeInBytes =  ByteBuffer.allocate(4).putInt(hashCode).array();
 	     
 	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		outputStream.write( hashCodeInBytes );					//hash в байтах поля с массивом значений
		outputStream.write( nounce().getBytes());				//hash в байтах поля с nounce
		
		byte transactionID[] = outputStream.toByteArray();		// соединям значения в одну транзакцию
		
		tx.transactionID = hash.bytesToHex(hash.hashFunction(transactionID, SHA3)); // хешируем байты и переводим их в String
        tx.operationData = op;
        
        return tx; // Возвращаем объект набором операций
      
    	}
	
	public static ArrayList<operationData> transactions(operationData op, ArrayList <operationData> od) 	// добавляем операцию в массвив транзакции.
    {
		od.add(op);
		return od; 
    }
	public static String nounce() 						// Задаем рандомное значение nounce
	{
	    byte[] array = new byte[77]; 
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
		return generatedString;
	}
}