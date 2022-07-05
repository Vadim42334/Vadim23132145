package DEX;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
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
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;


public class BlockChain {
 

		public static void initBlockChain() throws IOException , InvalidKeyException, NoSuchAlgorithmException, SignatureException
    	{
		 String usdt = "USDT";
   		 SignerUser signer= new SignerUser ();												// пользователь отправитель
         String message = "trans";
         signer.setBalanceUSDT(13333);										 					// устанавливаем баланс пользователя	
         byte[] sign = SignerUser.signData(message.getBytes());	 

    	 operationData op5 = Operation.createOperation(signer, signer, 20, sign, message, usdt);
    	 
    	 ArrayList <operationData> transactionsData = new ArrayList <operationData>();          // создаем массив транзакций							
    	 Transaction.addToTransaction(op5, transactionsData);									// заносим операции в транзакцию
    	 transactionData transaction1 = Transaction.createTransaction(transactionsData); 		// создаем транзакцию

    	 
    	 ArrayList <transactionData> blockData1 = new ArrayList <transactionData>();  				// создаем блок	

    	 
    	 blockData1 = Block.setBlock(transaction1, blockData1); 	
    	 blockData GenesisBlock = Block.createBlock(blockData1, "0");
    	 output.BlockChainHistory.add(GenesisBlock); // для валидации блока достаточно проверить правильность транзакции что было сделано в функции setBlock поэтому я просто добавил его 

    	}
		public static void coinDatabase( SignerUser signer) throws IOException , InvalidKeyException, NoSuchAlgorithmException, SignatureException
    	{		
				PublicKey trans = signer.getPublicKey();
				byte[] byte_pubkey = (trans).getEncoded();
				String str_key = Base64.getEncoder().encodeToString(byte_pubkey); // получаем публичные ключи
				
				Double.toString(signer.getBalanceVTH());
				Double.toString(signer.getBalanceVCOIN());
				String balances = String.join("; ", "USDT:" + Double.toString(signer.getBalanceUSDT()), "VTH:"+ Double.toString(signer.getBalanceVTH()), "VCOIN:"+ Double.toString(signer.getBalanceVCOIN()));
				output.coinDatabase.put(str_key, balances); // получаем балансы
    	}
		public static ArrayList<String> coinDatabaseMassive() throws IOException , InvalidKeyException, NoSuchAlgorithmException, SignatureException
    	{		
			ArrayList <String> accounts = new ArrayList <String>();
			int blockNumber = output.BlockChainHistory.size(); // количество блоков
			for(int i = 0; i< blockNumber; i++)
			{
			int txSize = output.BlockChainHistory.get(i).transactionData.size();
				for(int a = 0; a< txSize; a++)
				{
					int opSize = output.BlockChainHistory.get(i).transactionData.get(a).operationData.size();
					for(int b = 0; b< opSize; b++)
					{
						// перебираем ключи всех отправителей
						PublicKey trans = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(b).sender.getPublicKey();
						
						byte[] byte_pubkey = (trans).getEncoded();
						String str_key = Base64.getEncoder().encodeToString(byte_pubkey);
						if(accounts.contains(str_key))	
						{
							b++;
						}else 
						{	// Записываем ключи и балансы в массив и в coin database
							accounts.add(str_key);
							double transit1 = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(b).sender.getBalanceUSDT();
							double transit2 = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(b).sender.getBalanceVTH();
							double transit3 = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(b).sender.getBalanceVCOIN();
							
							accounts.add(Double.toString(transit1));
							accounts.add(Double.toString(transit2));
							accounts.add(Double.toString(transit3));
							coinDatabase(output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(b).sender); // заполнение coinDatabase данными
						
						}
						for(int c = 0; c< opSize; c++)
						{
						// перебираем ключи всех получателей
						PublicKey trans1 = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(c).recipient.getPublicKey();
						byte[] byte_pubkey1 = (trans1).getEncoded();
						String str_key1 = Base64.getEncoder().encodeToString(byte_pubkey1);
						if(accounts.contains(str_key1))	
						{
							b++;
						}else 
						{	// Записываем ключи и балансы в массив и в coin database
							accounts.add(str_key1);
							double transit4 = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(c).recipient.getBalanceUSDT();
							double transit5 = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(c).recipient.getBalanceVTH();
							double transit6 = output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(c).recipient.getBalanceVCOIN();
							accounts.add(Double.toString(transit4));
							accounts.add(Double.toString(transit5));
							accounts.add(Double.toString(transit6));
							coinDatabase(output.BlockChainHistory.get(i).transactionData.get(a).operationData.get(c).recipient); // заполнение coinDatabase данными
						}
						}
						
					}
				}	
			}
			return accounts; 
    	}
		public static void validateBlock(blockData block) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException
    	{		
			// проверка 1
			ArrayList <Boolean> trueCount = new ArrayList <Boolean>();
			int lastBlockNumber = output.BlockChainHistory.size()-1;
			String lastBlockHash = output.BlockChainHistory.get(lastBlockNumber).blockID;
			String blockHash = block.previousBlockHash;
			boolean check_1 = (lastBlockHash.equals(blockHash));
			// проверка 2 
			boolean check_2 = false;
			for(int i = 0; i < block.transactionData.size(); i++) 	 // проверка повторяющихся в других блоках транзакций
			{
				check_2 = output.txDataBase.contains(block.transactionData.get(i).transactionID);
			}
			//проверка 4
			boolean check_4 = false;
			int count = 0;
			int txSize = block.transactionData.size(); 				// количество блоков
			for(int i = 0; i< txSize; i++){
				{
				int opSize = block.transactionData.get(i).operationData.size();
				for(int b = 0; b < opSize; b++)
					{
						check_4 = Operation.verifyOperationB(block.transactionData.get(i).operationData.get(b));
						trueCount.add(check_4); // здесь не может быть false т.к все не правильные транзакции были отсеяны на этапе добавления в блок
					}
				}
			}

			if(trueCount.isEmpty()) // если ни одна операция в блоке не была подтверждена то пустой блок не будет добавляться в историю
			{
				check_4 = false; 
			}else {	check_4 = true;} 

				// проверка 3  
			    // суть проверки конфликтующих транзакций заключается в невозможности их создания.
		        // Если все монеты в операциях потратились корректно - операция записывается в транзакцию, транзакция в блок, а блок в блокчейн
			    // Если пользователь решит отправить одни и те же монеты разным получателям, то уйдут они тому, чья транзакция подтвердится быстрее
			if(check_1 == true && check_4 == true && check_2 == false) 
			{
				output.BlockChainHistory.add(block); // если блок проходит валидацию то он присоединяется к блок чейну
			} else 
			{
			// если блок не проходит валидацию то блок не добавляется в блок чейн
			}
			
			/*System.out.println("1: " + check_1);
			System.out.println("2: " + check_2); // проверка блока
			System.out.println("4: " + check_4);*/
			
    	}
		public static ArrayList<transactionData> createTxDatabase()
    	{		
			output.txDataBase.clear(); // очищаем базу данных транзакций, для того чтобы повторный вызов функции обновлял базу данных, а не добавлял в нее транзакции повторно
			int blockNumber = output.BlockChainHistory.size(); 	
			for(int i = 0; i< blockNumber; i++)
			{
				int txSize = output.BlockChainHistory.get(i).transactionData.size();
				for(int a = 0; a< txSize; a++)
				{	
					output.txDataBase.add(output.BlockChainHistory.get(i).transactionData.get(a)); // добавляем все существующие транзакции в истории в базу данных
				}
			}
			return output.txDataBase;
    	}
		public static void mining(int difficulty)
		{
			
			int test = difficulty; // сложность поиска решения задачи

			long m = System.currentTimeMillis(); // Включаем таймер
			mining.mine(test, 1); 
			System.out.println("Total time to brute all keys: " + (long) (System.currentTimeMillis() - m) + "ms"); // Время завершения майнинга
    	}

		

    
}