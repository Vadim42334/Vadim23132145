package Task_1;

import java.math.BigInteger;

public class Task1 {

	public static void main(String[] args) {

		int test = 6;

		int keylength_8 = 8;
		int keylength_16 = 16;
		int keylength_32 = 32;
		int keylength_64 = 64;
		int keylength_128 = 128;
		int keylength_256 = 256;
		int keylength_512 = 512;
		int keylength_1024 = 1024;
		int keylength_2048 = 2048;
		int keylength_4096 = 4096;
		long keys_28 = pow(2, keylength_8);
		long keys_216 = pow(2, keylength_16);
		long keys_232 = pow(2, keylength_32);  
		long keys_264 = pow(2, keylength_64);
		long keys_2128 = pow(2, keylength_128);
		long keys_2256 = pow(2, keylength_256);
		long keys_2512 = pow(2, keylength_512);
		long keys_21024 = pow(2, keylength_1024);
		long keys_22048 = pow(2, keylength_2048);
		long keys_24096 = pow(2, keylength_4096);

		long m = System.currentTimeMillis(); // Включаем таймер
		generate(test, 5); // Тестовый пример работы программы, создается 5 ключей на 6 символов
		System.out.println("Total time to brute all keys: " + (long) (System.currentTimeMillis() - m) + "ms"); // Время завершения брута
																												 
																												 
		/*
		 * generate(keylength_8, keys_28);				//Генерация и брутфорс всех остальных ключей
		 * generate(keylength_16, keys_216); 
		 * generate(keylength_32, keys_232);            // Кол-во ключей которое больше 2^32 не влазит в массив.
		 * generate(keylength_64, keys_264); 
		 * generate(keylength_128, keys_2128);
		 * generate(keylength_256, keys_2256); 
		 * generate(keylength_512, keys_2512);
		 * generate(keylength_1024, keys_21024); 
		 * generate(keylength_2048, keys_22048);
		 * generate(keylength_4096, keys_24096); */

	}

	public static void generate(int value, long value2) 					// ФУНКЦИЯ ВЫВОДЯЩАЯ КЛЮЧИ
	{

		String bb = " ";
		String[] ArrayString = new String[(int) value2 + 1];

		for (int i = 1; i <= value2; i++) {

			long m = System.currentTimeMillis(); 							// Включаем таймер

			bb = test(value);
			ArrayString[i] = bb; 											// Записываем ключи в массив строк
			System.out.println("Key " + i + ": " + ArrayString[i]);
			BruteForce.BF(bb); 												// Вызываем функцию брутфорса ключей
			System.out.println("Time: " + (long) (System.currentTimeMillis() - m) + "ms"); // Время завершения брута

		}

	}

	public static long pow(int value, int powValue) 						// ФУНКЦИЯ ВОЗВОДЯЩАЯ В СТЕПЕНЬ
	{
		BigInteger a = new BigInteger(String.valueOf(value));
		return a.pow(powValue).longValue();
	}

	public static String test(int value)									// ФУНКЦИЯ СОЗДАЮЩАЯ КЛЮЧИ
	{
		String otherString = "0x"; // начало
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
}
