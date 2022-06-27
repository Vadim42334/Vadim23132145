package KeyPairSign;

import java.security.PublicKey;

class operationData
     {
    	 PublicKey sender = null;
    	 PublicKey recipient = null;
    	 int amount = 0;
    	 byte[] signature = null;
    	 String message = "";
     }
class transactionData
{
	 static String transactionID = "";
	 operationData[] operationData  = null;
}