package Task_1;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class BruteForce {


	public static void BF(String pass) {

		final int MAX_KEY_SIZE = pass.length();
		StringBuilder password = new StringBuilder(pass);
		// инициализируем словарь символов
		List symbols = generateSymbols();

		// инициализируем цепочку символов
		Link link = initLinks(symbols, MAX_KEY_SIZE);

		// начинаем перебор ключей
		String symbol = "0x";
		StringBuilder symb = new StringBuilder(symbol);
		StringBuilder key1 = new StringBuilder();

		while (!key1.toString().equals(password.toString()))
		{
			link.next();
			key1.setLength(0);
			String addsymbol = key1.toString() + symb.toString(); // добавили символы 0x для сокращения времени подбора.
			key1 = new StringBuilder(addsymbol); 			
			link.getKey(key1);
			
			// System.out.println("Try: "+key);
		}
		System.out.println("Brutted key= " + key1);
		}



	
	private static Link initLinks(List symbols, int level) { 			// инициализация цепочки
		if (level > 0) {
			return new Link(symbols, initLinks(symbols, level - 1));
		}
		return null;
	}

	private static List generateSymbols() { // Все возможные символы из ключа
		List symbols = new ArrayList();
		for (char c = '0'; c <= '9'; c++)
			symbols.add(c);
		for (char c = 'a'; c <= 'z'; c++)
			symbols.add(c);
		return symbols;
	}

}