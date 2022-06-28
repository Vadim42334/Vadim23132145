package KeyPairSign;

import java.security.PublicKey;
import java.util.ArrayList;

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
	 String transactionID = "";
	 ArrayList <operationData> operationData  = null;
}
class blockData
{
	  String blockID = "";
	  ArrayList <transactionData> transactionData  = null;
}