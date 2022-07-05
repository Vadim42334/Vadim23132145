package DEX;

public class quotes{ // выставляем цену на токены с учетом котировок
	
	public static double vthPrice() 
	{ 
		double price = rnd(100,300);
		return price;
		
	}
	public static double vcoinPrice() 
	{
		double price = rnd(20000, 35000);
		return price;
		
	}
	public static double usdtPrice() 
	{
		double price = 1;
		return price;
	}

	public static double rnd(double min, double max)
	{
		max -= min;
		return (double) (Math.random() * ++max) + min;
	}
}