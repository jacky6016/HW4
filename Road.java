import java.util.*;

public class Road
{
	 //fields
	private String name;
	private Set<Integer> months = new HashSet<Integer>();
	private int numOfMonths;
	private int highestPrice;
	private int lowestPrice;
	
	//constructor
	public Road(String name, int month, int price)
	{
		this.name = name;
		this.months.add(month);
		this.numOfMonths = 1;
		this.highestPrice = price;
		this.lowestPrice = price;
	}
	
	
	//methods
	public String getName()
	{  
		return this.name;
	}
	
	public int getHighestPrice()
	{  
		return this.highestPrice;
	}
	
	public int getLowestPrice()
	{  
		return this.lowestPrice;
	}
	
	public int getNumOfMonths()
	{  
		return this.numOfMonths;
	}
	
	public void updatePrice(int price)
	{
		if(price > this.highestPrice)
			this.highestPrice = price;
		else if(price < this.lowestPrice)
			this.lowestPrice = price;
	}
	
	public void updateMonth(int month)
	{
		this.months.add(month);
		this.numOfMonths = this.months.size();
	}
}
