package Task_2;
import java.math.BigInteger;



public class Task2 {

public static void main(String[] args)
{
	 System.out.println( "VECTOR 1");  // Перевел все для 4 вектора т.к он самый объемный и сложный.
	 String value1 = "0xff00000000000000000000000000000000000000000000000000000000000000";
	 String BigEndian1 = "115339776388732929035197660848497720713218148788040405586178452820382218977280";
	 String littleEndian1 = "255";
	 
	 String decToLittleEndian1 = decToHex(littleEndian1); // переводим десятичное значение в шестнадцатиричное в порядке little endian
	 System.out.println( "Little Endian to HEX: " + hexToLittleEndian(decToLittleEndian1)); // переводим из little endian порядка в Big endian порядок 
	 System.out.println( "Big Endian to HEX: " + decToHex(BigEndian1)); 
	 System.out.println( "HEX to little endian: " + hexToLittleEndian(value1)); 
	 System.out.println( "HEX to big endian: " + value1); 
	 System.out.println( "Number of bytes: " + bytesCounter(value1)); 
	 
	 System.out.println( "VECTOR 2");  // Перевел все для 4 вектора т.к он самый объемный и сложный.
	 String value2 = "0xaaaa000000000000000000000000000000000000000000000000000000000000";
	 String BigEndian2 = "77193548260167611359494267807458109956502771454495792280332974934474558013440";
	 String littleEndian2 = "43690";
	 
	 String decToLittleEndian2 = decToHex(littleEndian2); // переводим десятичное значение в шестнадцатиричное в порядке little endian
	 System.out.println( "Little Endian to HEX: " + hexToLittleEndian(decToLittleEndian2)); // переводим из little endian порядка в Big endian порядок
	 System.out.println( "Big Endian to HEX: " + decToHex(BigEndian2)); 
	 System.out.println( "HEX to little endian: " + hexToLittleEndian(value2)); 
	 System.out.println( "HEX to big endian: " + value2); 
	 System.out.println( "Number of bytes: " + bytesCounter(value2)); 
	 
	 System.out.println( "VECTOR 3");  // Перевел все для 4 вектора т.к он самый объемный и сложный.
	 String value3 = "0xFFFFFFFF";
	 String BigEndian3 = "4294967295";
	 String littleEndian3= "4294967295";
	 
	 String decToLittleEndian3 = decToHex(littleEndian3); // переводим десятичное значение в шестнадцатиричное в порядке little endian
	 System.out.println( "Little Endian to HEX: " + hexToLittleEndian(decToLittleEndian3)); // переводим из little endian порядка в Big endian порядок т.к байт всего один ничего не изменится
	 System.out.println( "Big Endian to HEX: " + decToHex(BigEndian3)); 
	 System.out.println( "HEX to little endian: " + hexToLittleEndian(value3)); 
	 System.out.println( "HEX to big endian: " + value3); 
	 System.out.println( "Number of bytes: " + bytesCounter(value3)); 

	
	 System.out.println( "VECTOR 4");  
	 String value = "0xF000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	 String BigEndian = "979114576324830475023518166296835358668716483481922294110218890578706788723335115795775136189060210944584475044786808910613350098299181506809283832360654948074334665509728123444088990750984735919776315636114949587227798911935355699067813766573049953903257414411690972566828795693861196044813729172123152193769005290826676049325224028303369631812105737593272002471587527915367835952474124875982077070337970837392460768423348044782340688207323630599527945406427226264695390995320400314062984891593411332752703846859640346323687201762934524222363836094053204269986087043470117703336873406636573235808683444836432453459818599293667760149123595668832133083221407128310342064668595954073131257995767262426534143159642539179485013975461689493733866106312135829807129162654188209922755829012304582671671519678313609748646814745057724363462189490278183457296449014163077506949636570237334109910914728582640301294341605533983878368789071427913184794906223657920124153256147359625549743656058746335124502376663710766611046750739680547042183503568549468592703882095207981161012224965829605768300297615939788368703353944514111011011184191740295491255291545096680705534063721012625490368756140460791685877738232879406346334603566914069127957053440";
	 String littleEndian = "240";
	 
	 String decToLittleEndian = decToHex(littleEndian); // переводим десятичное значение в шестнадцатиричное в порядке little endian
	 System.out.println( "Little Endian to HEX: " + hexToLittleEndian(decToLittleEndian)); // переводим из little endian порядка в Big endian порядок т.к байт всего один ничего не изменится
	 System.out.println( "Big Endian to HEX: " + decToHex(BigEndian)); 
	 System.out.println( "HEX to little endian: " + hexToLittleEndian(value)); 
	 System.out.println( "HEX to big endian: " + value); 
	 System.out.println( "Number of bytes: " + bytesCounter(value)); 

}

public static String decToHex(String str) {            // Перевод из десятичного значения в 16 ричное 

	 BigInteger toHex=new BigInteger(str,10);
	    String s=toHex.toString(16);
	    StringBuffer str1 = new StringBuffer(s);
	    str1.insert(0,"0x");                           // Добавляем флаг 0х в начало.
	    s = str1.toString();
	    return s;
    }


public static String littleEndianToHex(String originalHex) {
	
	int lengthInBytes = originalHex.length() / 2;
    char[] chars = new char[lengthInBytes * 2];
    for (int index = 0; index < lengthInBytes; index++)
    {
        int reversedIndex = lengthInBytes - 1 - index;
        chars[reversedIndex * 2] = originalHex.charAt(index * 2);
        chars[reversedIndex * 2 + 1] = originalHex.charAt(index * 2 + 1);
    }
    StringBuffer str1 = new StringBuffer(new String(chars));
    str1.insert(0,"0x");
    originalHex = str1.toString();
	return originalHex;
}

public static String hexToLittleEndian(String originalHex) {
    StringBuffer str1 = new StringBuffer(originalHex);
    if(originalHex.contains("0x"))
    {
        str1.delete(0,2); 
    }
    originalHex = str1.toString();
	int lengthInBytes = originalHex.length() / 2;
    char[] chars = new char[lengthInBytes * 2];
    for (int index = 0; index < lengthInBytes; index++)
    {
        int reversedIndex = lengthInBytes - 1 - index;
        chars[reversedIndex * 2] = originalHex.charAt(index * 2);
        chars[reversedIndex * 2 + 1] = originalHex.charAt(index * 2 + 1);
    }
    StringBuffer str2 = new StringBuffer(new String(chars));
    str2.insert(0,"0x");
    originalHex = str2.toString();
	return originalHex;


}

public static String bytesCounter(String originalHex)  // счетчик байтов
    {
    int bytee = 0;
    StringBuffer str1 = new StringBuffer(originalHex);
    if(originalHex.contains("0x"))
    {
        str1.delete(0,2); 
    }
    originalHex = str1.toString();
    int lengthInBytes = originalHex.length() / 2;
    String str = Integer.toString(lengthInBytes);
	return str;
	}

}