package BlockChain;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPublicKeySpec;




class SignerUser 											
	{
	private int balance;

    private PublicKey publicKey;
    private static PrivateKey privateKey;
     
    public static byte[] signData(byte[] message) throws NoSuchAlgorithmException, 	// генерация подписи
    InvalidKeyException, SignatureException
    {
	    Signature sig = Signature.getInstance("DSA");
	    sig.initSign(privateKey);
	    sig.update(message);
	    byte[] sign= sig.sign();
	    return sign;
    }

    public SignerUser() throws NoSuchAlgorithmException
    { 					
	    KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
	    SecureRandom secRan = new SecureRandom();
	    kpg.initialize(512, secRan);
	    KeyPair keyPair = kpg.generateKeyPair();
	    publicKey = keyPair.getPublic();
	    privateKey = keyPair.getPrivate();
	    
    }
	public PublicKey getPublicKey()
	{ 
	    return publicKey;
	}
	public PrivateKey getPrivateKey()
	{
	    return privateKey;
	}
	public void setBalance( int balance)
	{
		 this.balance = balance;
	}
	public int getBalance()
	{
		return balance;
	};
}


class operationData
     {
    	 SignerUser sender = null;
    	 SignerUser recipient = null;
    	 int amount = 0;
    	 byte[] signature = null;
    	 String message = "";
     }
class transactionData
{
	 String transactionID = "";
	 ArrayList <operationData> operationData  = null;
}
class blockData
{
	  String blockID = "";
	  String previousBlockHash = "";
	  ArrayList <transactionData> transactionData  = null;
}