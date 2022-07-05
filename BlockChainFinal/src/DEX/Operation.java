package DEX;

import java.io.IOException;
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


public class Operation {
 
     public static operationData createOperation( SignerUser sender,  SignerUser recipient,  double amount,  byte[] signature, String message, String vault)
     {
    	 	
    	 operationData op = new operationData(); 			// создаем объект состоящий из данных класса
    	            op.sender = sender;
    	            op.recipient = recipient;
    	            op.amount = amount;
    	            op.signature = signature; 				
    	            op.message = message;
    	            op.vault = vault;
    	            return op;
     }

     
     public static boolean verifyOperationB(operationData op)  // функция для проверки операций при добавлении блока в блокчейн, перевод средств в ней не происходит
     throws InvalidKeyException, NoSuchAlgorithmException, SignatureException
     {
    	 boolean a = false;
    	if(op.vault == "USDT")
    	{
    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
    	 {
    		 a = true;
    	 }else {a = false;}
    	}
    	if(op.vault == "VTH")
    	{
    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
    	 {
    		 a = true;
    	 }else {a = false;}
    	}
    	if(op.vault == "VCOIN")
    	{
    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
    	 {
    		 a = true;
    	 }else {a = false;}
    	}
    	return a;
     }
     public static boolean verifyOperation(operationData op) 
     throws InvalidKeyException, NoSuchAlgorithmException, SignatureException
     {
    	 boolean a = false;
    	 if(op.vault == "USDT") 
    	 {
	    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
	    	 {
	 	    	 op.sender.setBalanceUSDT(op.sender.getBalanceUSDT() - op.amount); 
	 	    	 op.recipient.setBalanceUSDT(op.recipient.getBalanceUSDT() + op.amount); // отнимаем монеты у отправителя и добавляем получателю
	    		 a= true;
	    	 }else {a =false;}
    	 }
    	 if(op.vault == "VTH") 
    	 {
	    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
	    	 {
	 	    	 op.sender.setBalanceVTH(op.sender.getBalanceVTH() - op.amount); 
	 	    	 op.recipient.setBalanceVTH(op.recipient.getBalanceVTH() + op.amount); // отнимаем монеты у отправителя и добавляем получателю
	    		 a= true;
	    	 }else {a= false;}
    	 }
    	 if(op.vault == "VCOIN") 
    	 {
	    	 if((verifySignature(op) == true) &&( balanceCheck(op) == true)) 
	    	 {
	 	    	 op.sender.setBalanceVCOIN(op.sender.getBalanceVCOIN() - op.amount); 
	 	    	 op.recipient.setBalanceVCOIN(op.recipient.getBalanceVCOIN() + op.amount); // отнимаем монеты у отправителя и добавляем получателю
	    		 a= true;
	    	 }else {a=false;}
    	 }
    	 return a;
     }
	 public static boolean balanceCheck(operationData op)  // если сумма транзакции больше или равна баллансу получателя - проверка пройдена
	 {
		 boolean a = false;
		 if(op.vault == "USDT") {
	     if(op.amount <= op.sender.getBalanceUSDT()) 
	     	{
	   	  		a=  true;
	     	}else {a = false;}
		 }
	     if(op.vault == "VTH") {
	     if(op.amount <= op.sender.getBalanceVTH()) 
	  	 {
		  		a= true;
	  	 }else {a = false;}
		 }
		 if(op.vault == "VCOIN") {
	     if(op.amount <= op.sender.getBalanceVCOIN()) 
	     {
		  		a=  true;
	  	 }else {a = false;}
		 }
		 return a;
	 }
	
     public static void printOperation(operationData op )  // вывод всех обьектов операции
     {
    	 System.out.println("Sender: " + op.sender);
    	 System.out.println("Recipier: " + op.recipient);
    	 System.out.println("Amount of coins: " + op.amount);
    	 System.out.println("Signature: " + op.signature);
    	 System.out.println("Message: " + op.message);
     }
     public static boolean verifySignature(operationData op ) throws 		// проверка подписи
     NoSuchAlgorithmException, InvalidKeyException, SignatureException {
     Signature clientSig = Signature.getInstance("DSA");
     clientSig.initVerify(op.sender.getPublicKey());
     clientSig.update(op.message.getBytes());
     if (clientSig.verify(op.signature))
     {
        return true;
     } else 
     {
    	
        return false;
     }
 }
     public static transactionData createTx(SignerUser dexUser, double amount, byte[]sign, String message, String vaultSend, String vaultGet) 
    throws InvalidKeyException, NoSuchAlgorithmException, SignatureException, IOException  
     {	
		SignerUser liquidityPool= new SignerUser ();												// пользователь отправитель
        String messageL = "hiwwdds";
        liquidityPool.setBalanceUSDT(9948939);		
        liquidityPool.setBalanceVTH(123000);	
        liquidityPool.setBalanceVCOIN(500000);												// устанавливаем баланс пользователя	
        byte[] signL = SignerUser.signData(messageL.getBytes());	 
        
        operationData op1 = Operation.createOperation(dexUser, liquidityPool, amount, sign, message, vaultSend); 	
        
        double amountSend = 0;
        double result = 0;
        if(vaultSend == "VTH") { amountSend = amount * quotes.vthPrice();}
    	if(vaultSend  == "VCOIN") { amountSend = amount * quotes.vcoinPrice();}
    	if(vaultSend  == "USDT") {amountSend = amount * quotes.usdtPrice();}
    	
    	if(vaultGet == "VTH") { result = amountSend  / quotes.vcoinPrice();}
    	if(vaultGet  == "VCOIN") { result = amountSend  / quotes.vthPrice();}
    	if(vaultGet  == "USDT") {result = amountSend  / quotes.usdtPrice();}
        // формула рассчета суммы для отправки (VS*Q) / (VG*Q)
    	
    	operationData op2 = Operation.createOperation(liquidityPool, dexUser, result, signL, messageL, vaultGet); 
    	
    	ArrayList <operationData> transactionsData = new ArrayList <operationData>(); 
    	Transaction.addToTransaction(op1,transactionsData); 
    	Transaction.addToTransaction(op2,transactionsData); 
    	transactionData transaction4 = Transaction.createTransaction(transactionsData); 
    	return transaction4;
    	
     }
}