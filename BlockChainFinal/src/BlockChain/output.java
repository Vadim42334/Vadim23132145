package BlockChain;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class output {
	
			static ArrayList <blockData> BlockChainHistory = new ArrayList <blockData>();		//  массив с историями блоков
			static Map <String, Integer> coinDatabase = new HashMap <String, Integer>(); 		// база данных с пользователями и их баллансами
			static ArrayList <transactionData> txDataBase = new ArrayList <transactionData>();  // массив данных со всеми транзакциями
			
public static void main(String args[])
     {
    	 try
    	 {
    		 BlockChain.initBlockChain(); 
    		 
    		 SignerUser signer= new SignerUser ();												// пользователь отправитель
             String message = "trans";
             signer.setBalance(50);										 					    // устанавливаем баланс пользователя	
             byte[] sign = SignerUser.signData(message.getBytes());	 
  																    
             SignerUser signer2= new SignerUser ();												// пользователь получатель
             String message2 = "trans2";
             signer2.setBalance(19);	
             byte[] sign2 = SignerUser.signData(message2.getBytes());	
      
             SignerUser signer3= new SignerUser ();												// пользователь получатель
             String message3 = "trans2";
             signer3.setBalance(19);	
             byte[] sign3 = SignerUser.signData(message3.getBytes());	 

             //---------------------------OPERATIONS---------------------------
        	 operationData op1 = Operation.createOperation(signer, signer2, 20, sign, message);   
        	 operationData op2 = Operation.createOperation(signer2, signer, 20, sign2, message2); 
        	 operationData op3 = Operation.createOperation(signer3, signer, 20, sign3, message3);				
        	 operationData op4 = Operation.createOperation(signer2, signer3, 20, sign2, message2);
        	 operationData op5 = Operation.createOperation(signer, signer3, 20, sign, message);
        	 operationData op6 = Operation.createOperation(signer2, signer2, 20, sign2, message2); 	// Создание операций
        	 

        	 //---------------------------TRANSACTIONS---------------------------
        	 ArrayList <operationData> transactionsData = new ArrayList <operationData>();      // создаем массив транзакций
        	 Transaction.addToTransaction(op1,transactionsData); 							
        	 Transaction.addToTransaction(op5, transactionsData);								// заносим операции в транзакцию
        	 transactionData transaction1 = Transaction.createTransaction(transactionsData); 	// создаем транзакцию 1
        	 
        	 ArrayList <operationData> transactionsData2 = new ArrayList <operationData>();
        	 Transaction.addToTransaction(op2,transactionsData2); 							
        	 Transaction.addToTransaction(op4, transactionsData2);								// заносим операции в транзакцию
        	 transactionData transaction2 = Transaction.createTransaction(transactionsData2); 	// создаем транзакцию 2
        	 
        	 ArrayList <operationData> transactionsData3 = new ArrayList <operationData>();
        	 Transaction.addToTransaction(op3,transactionsData3);  								// заносим операции в транзакцию
        	 transactionData transaction3 = Transaction.createTransaction(transactionsData3); 	// создаем транзакцию 3
        	 
        	 ArrayList <operationData> transactionsData4 = new ArrayList <operationData>();
        	 Transaction.addToTransaction(op6,transactionsData4); 								// заносим операции в транзакцию
        	 transactionData transaction4 = Transaction.createTransaction(transactionsData4);   // создаем транзакцию 4
        	 //---------------------------BLOCK---------------------------
        	 ArrayList <transactionData> blockData1 = new ArrayList <transactionData>();		// Начинаем новый блок 1
        	 ArrayList <transactionData> blockData2 = new ArrayList <transactionData>();		// Начинаем новый блок 2
        	 
        	 Block.txSend(transaction1, blockData1);
        	 Block.txSend(transaction2, blockData1);	//Записываем транзакции в новый блок 1
        	 Block.txSend(transaction3, blockData2);
        	 Block.txSend(transaction4, blockData2); 	//Записываем транзакции в новый блок 2.
        	 
        	 //---------------------------BLOCKCHAIN---------------------------
        	 blockData block1 = Block.createBlock(blockData1, BlockChainHistory.get(0).blockID); // начинаем процесс присоединения созданного блока к блокчейну
        	 blockData block2 = Block.createBlock(blockData2, block1.blockID);

        	 BlockChain.validateBlock(block1);
        	 BlockChain.validateBlock(block2); 													 // проходим валидацию блоков перед добавлением в блокчейн

        	 BlockChain.createTxDatabase(); 	
        	
        	 System.out.println("BlockChain "+ BlockChainHistory); 								 // выводим весь блокчейн (3 блока)
        	 System.out.println("Key & balance: " + BlockChain.coinDatabaseMassive()); 		 	 // вывод массива с ключами
        	 System.out.println("CoinDatabase: " + coinDatabase);							 	 // вывод таблицы coinDatabase
        	 System.out.println("Transaction database: " + txDataBase); 					 	 // Вывод массива с транзакциями

    	 }catch(Exception e)
    	 {
         e.printStackTrace();
    	 }
     	}
}