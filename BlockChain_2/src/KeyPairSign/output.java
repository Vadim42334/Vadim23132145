package KeyPairSign;

import java.security.PublicKey;
import java.util.ArrayList;

import KeyPairSign.KeyPairSigns.SignerUser;


public class output {
public static void main(String args[])
     {
    	 try
    	 {
    		 SignerUser signer= new SignerUser ();												// пользователь отправитель
             PublicKey pubKey = signer.getPubKey(); 
             String message = "trans";
             SignerUser.setBalance(10);										 					// устанавливаем баланс пользователя				
             byte[] sign = KeyPairSigns.signData(message.getBytes(), signer.getPrivateKey());	 
             
             SignerUser signer2= new SignerUser ();												// пользователь получатель
             PublicKey pubKey2 = signer2.getPubKey(); 	 	
             String message2 = "trans2";
             byte[] sign2 = KeyPairSigns.signData(message2.getBytes(), signer2.getPrivateKey());	
        	 
             
             int amount = 6;
             int amount2 = 12; // сумма перевода
        	 
        	 
             System.out.println("---------------------------OPERATIONS---------------------------"); 
        	 operationData gg = Operation.createOperation(signer.getPubKey(), signer2.getPubKey(), amount, sign, message);   // Создание операции
        	 operationData gg2 = Operation.createOperation(signer.getPubKey(), signer2.getPubKey(), amount2, sign2, message2); // Создание операции
        	 Operation.verifyOperation(gg, sign, signer, message.getBytes()); //проверка операции
        	 
        	 Operation.printOperation(gg); 										// Вывод всей операции
        	 
        	 System.out.println("---------------------------TRANSACTIONS---------------------------"); 
        	 ArrayList <operationData> transactionsData = new ArrayList <operationData>();          // создаем массив транзакций
        	 transactionsData = Transaction.transactions(gg,transactionsData); 				// заносим операцию gg в массив транзакций
        	 transactionsData = Transaction.transactions(gg2, transactionsData);			// заносим операцию gg2 в массив транзакций
        	 
        	 System.out.println("test_1: "+ transactionsData.get(0).amount); 		
        	 System.out.println("test_2: "+ transactionsData.get(0).signature); 		// вынимаем значения из транзакций для проверки работы
        	 
        	 transactionData newtransaction = Transaction.createTransaction(transactionsData);
        	 transactionData newtransaction2 = Transaction.createTransaction(transactionsData);
        	 System.out.println("Transaction id: "+ newtransaction.transactionID); //выводим id транзакции
        	 
     
        	 ArrayList<operationData> test = newtransaction.operationData;
        	 System.out.println("Operation data: "+ test.get(0).amount); 			// Получаем доступ из транзакции к значению из операции (amount)
        	 
        	 
        	 System.out.println("---------------------------BLOCK---------------------------"); 
        	 ArrayList <transactionData> blockData = new ArrayList <transactionData>();  // создаем блок	

        	 String prevBlockID = "82h8sh2821"; 								// пример id прошлого блока
        	 blockData = Block.setBlock(newtransaction, blockData);								 // заносим транзакцию в блок
        	 blockData = Block.setBlock(newtransaction2, blockData);
        	 
        	 blockData newblock = Block.createBlock(blockData, prevBlockID);
        	 System.out.println("Block id: "+ newblock.blockID); 								 // Выводим id блока
        	 ArrayList<transactionData> test2 = newblock.transactionData; 	 							 //Выводим массив с транзакциями из блока
        	 ArrayList<operationData> test3 = test2.get(0).operationData;									 //Выводим массив с операциями из транзакций
        	 System.out.println("OP amount "+ test3.get(0).amount); 									 // Выводим сумму перевода из операции


        	 Block.transactionDataOutput(blockData); 									// Вывод всех транзакций из блока
        	
        	 
        	 
        	 
    	 }catch(Exception e)
    	 {
         e.printStackTrace();
    	 }
     	}
}