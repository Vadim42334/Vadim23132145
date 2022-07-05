package DEX;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class mining {


	public static void mine(int value, long value2) 					// ФУНКЦИЯ ВЫВОДЯЩАЯ ЗАДАЧУ
	{

		String bb = " ";
		String[] ArrayString = new String[(int) value2 + 1];

		for (int i = 1; i <= value2; i++) {

			long m = System.currentTimeMillis(); 							// Включаем таймер

			bb = test(value);
			ArrayString[i] = bb; 											// Записываем ключи в массив строк
			System.out.println("Task " + i + ": " + ArrayString[i]);
			findAnswer(bb); 												// Вызываем функцию поиска решения задачи
			System.out.println("Time: " + (long) (System.currentTimeMillis() - m) + "ms"); // Время завершения брута

		}

	}

	public static long pow(int value, int powValue) 						// ФУНКЦИЯ ВОЗВОДЯЩАЯ В СТЕПЕНЬ
	{
		BigInteger a = new BigInteger(String.valueOf(value));
		return a.pow(powValue).longValue();
	}

	public static String test(int value)									// ФУНКЦИЯ СОЗДАЮЩАЯ ЗАДАЧУ
	{
		String otherString = "0x"; 											// начало
		for (int a = 0; a < 999999999; a++) { 								// Прогоняем цикл максимальное количество раз пока не получим нужный результат
												
			String s = String.format("%x", (int) (Math.random() * 15)); 	// 15 - максимальное число 16 ричной системы

			if (otherString.length() < value) 								// Добавляем рандомные символы в строку пока ее длинна не станет равна  заданной
												
			{
				otherString = otherString + s;
			} else {
				break;
			}
		}
		return otherString;
	}

	public static void findAnswer(String pass) {

		final int MAX_KEY_SIZE = pass.length();
		StringBuilder password = new StringBuilder(pass); 
		
		List symbols = generateSymbols();

		
		miningLink link = initLinks(symbols, MAX_KEY_SIZE); // инициализируем цепочку символов

		String symbol = "0x";
		StringBuilder symb = new StringBuilder(symbol);
		StringBuilder key1 = new StringBuilder();

		while (!key1.toString().equals(password.toString())) // Перебираем ключи
		{
			link.next();
			key1.setLength(0);
			String addsymbol = key1.toString() + symb.toString(); // добавили символы 0x для сокращения времени подбора.
			key1 = new StringBuilder(addsymbol); 			
			link.getKey(key1);
			
		}
		System.out.println("Solution: " + key1);
		}

	private static miningLink initLinks(List symbols, int level) { 			// инициализация цепочки
		if (level > 0) {
			return new miningLink(symbols, initLinks(symbols, level - 1));
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

