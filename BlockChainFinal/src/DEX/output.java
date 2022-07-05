package DEX;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class output {
	
			static ArrayList <blockData> BlockChainHistory = new ArrayList <blockData>();		//  массив с историями блоков
			static Map <String, String> coinDatabase = new HashMap <String, String>(); 		// база данных с пользователями и их баллансами
			static ArrayList <transactionData> txDataBase = new ArrayList <transactionData>(); // массив данных со всеми транзакциями
			static String USDT = "USDT";
			static String VTH = "VTH";
			static String VCOIN = "VCOIN"; // Доступные валюты для обмена
public static void main(String args[])
     {
    	 try
    	 {
    		 BlockChain.initBlockChain(); 
													    
             SignerUser dexUser= new SignerUser ();												// пользователь получатель
             String message2 = "trans2";
             dexUser.setBalanceUSDT(500);
             dexUser.setBalanceVCOIN(10);														// устанавливаем балансы для разных токенов
             byte[] sign = SignerUser.signData(message2.getBytes());
 
        	 transactionData tx = Operation.createTx( dexUser, 10, sign, message2, USDT, VCOIN);   // создаем транзакцию по обмену токенрв
        	 
        	 ArrayList <transactionData> blockData1 = new ArrayList <transactionData>();		 // Начинаем новый блок 1
        	 
        	 Block.txSend(tx, blockData1); 	 													 // отправялем транзакцию в блок
        	 
        	  BlockChain.mining(6); //демо версия майнинга, где в функцию мы передаем длинну ключа который нужно подобрать ( сложность задачи)
        	 blockData block1 = Block.createBlock(blockData1, BlockChainHistory.get(0).blockID); // начинаем процесс присоединения созданного блока к блокчейну
        	 
        	 BlockChain.createTxDatabase(); 		// создаем базу данных с транзакциями
        	 
        	 BlockChain.validateBlock(block1);
        	 	
        	 BlockChain.createTxDatabase();  		// обновляем базу данных с учетом нового блока
        	
        	 System.out.println("BlockChain "+ BlockChainHistory); 							 //Выводим весь блокчейн (3 блока)
        	 System.out.println("Key & balance: " + BlockChain.coinDatabaseMassive()); 		 // вывод массива с ключами
        	 System.out.println("CoinDatabase: " + coinDatabase);							 // вывод таблицы coinDatabase
        	 System.out.println("Transaction database: " + txDataBase);						 // Вывод массива с транзакциями
        	 
    	 }catch(Exception e)
    	 {
         e.printStackTrace();
    	 }
    	 
     	}

}