package Task_4;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

	public class HelpFunction {
		
    public static byte[] getRandomValue(int numBytes) 						// Генерируем рандомное значение
    { 					
        byte[] bt = new byte[numBytes];
        new SecureRandom().nextBytes(bt);
        return bt;
    }

    public static String hex(byte[] bytes) { 								// перевод bytes в hex
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public static String hexOnBlocks(byte[] bytes, int blockSize) { 	//Разделение hex на блоки
        String hex = hex(bytes);
        blockSize = blockSize * 2;
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < hex.length())
        {
            result.add(hex.substring(index, Math.min(index + blockSize, hex.length())));
            index += blockSize;
        }
        return result.toString();
    }
}