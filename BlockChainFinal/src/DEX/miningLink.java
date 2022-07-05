package DEX;

import java.util.ArrayList;
import java.util.List;

public class miningLink {

	
	private int currentIndex = -1;					// '-1'- пустое значение
	private List<Character> symbols;
	private miningLink next;

	public miningLink(List<Character> symbols, miningLink link) {
		this.symbols = symbols;
		this.next = link;
	}

	
	public void next() {							// Переключатель на следующий символ
		currentIndex++;
		if (currentIndex == symbols.size()) {
			currentIndex = 0;
			if (next != null) {
				next.next();
			}
		}
	}

	
	public void getKey(StringBuilder generateKey) { // Записываем сгенерированный пароль в generateKey
		if (next != null) {
			next.getKey(generateKey);
		}
		if (currentIndex > -1) {
			generateKey.append(symbols.get(currentIndex));
		}
	}

}