/* Author: 林展翔 Dept: EE(junior) StudentID: E24019067 */

import java.io.*;
import java.net.*;	// for downloading file
import java.util.*;
import org.json.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TocHw4
{
	public static void main(String[] args)
    {
		/* arg[0]: URL */
		try
		{
			String url = args[0];

			//JSONArray jsonRealPrice = new JSONArray(new JSONTokener(new FileReader(new File(args[0]))));
			
			TocHw4 hw4 = new TocHw4();
			hw4.parseData(url);
			JSONArray jsonRealPrice = new JSONArray(new JSONTokener(new FileReader(new File("Data.txt"))));
			
			ArrayList<Road> roadList = new ArrayList<Road>();
			ArrayList<String> list = new ArrayList<String>();
			String theRoad;
			int count = 0;
			int	highestPrice, lowestPrice;
			
			for(int i=0; i<jsonRealPrice.length(); i++)
			{			
				JSONObject object = jsonRealPrice.getJSONObject(i);
				
				String town = object.getString("鄉鎮市區");
				String address = object.getString("土地區段位置或建物區門牌");
				int month = object.getInt("交易年月");
				int price = object.getInt("總價元");
				
				String road = getRoad(address);
				if(road.equals("Invalid Address"))
					continue;

				if(!list.contains(road))
				{
					list.add(road);
					roadList.add(new Road(road,month,price));					
				}
				else
				{
					int idx = roadIndex(road,roadList);
					//System.out.println(road + "  " + idx);
					//update price & month
					roadList.get(idx).updatePrice(price);
					roadList.get(idx).updateMonth(month);
					if( count < roadList.get(idx).getNumOfMonths() )
						count = roadList.get(idx).getNumOfMonths();
				}				
			}
			
			Iterator<Road> it = roadList.iterator();
			while(it.hasNext())
			{
				Road obj = it.next();
				//Do something with obj
				//System.out.println(obj.getName());
				//System.out.println(obj.getHighestPrice());
				//System.out.println(obj.getLowestPrice());
				//System.out.println(obj.getNumOfMonths());
				//System.out.println();
				if( obj.getNumOfMonths() == count )
				{
					System.out.println( obj.getName() + ", 最高成交價: " + obj.getHighestPrice() + ", 最低成交價: " + obj.getLowestPrice());			
				}
			}
			
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Data File Not Found");
			ex.printStackTrace();
		}
		catch(JSONException ex)
		{
			System.out.println("JSON Exception");
		}
	
	}
	public void parseData(String url) 
	{
		try
		{
			URL pageUrl = new URL(url);
			// 讀入網頁(位元串流)
			BufferedReader br = new BufferedReader(new InputStreamReader(pageUrl.openStream(), "UTF-8"));
			BufferedWriter bw = new BufferedWriter(new FileWriter("Data.txt", false));
			String oneLine = null ;
		
			while ((oneLine = br.readLine()) != null) 
			{
				bw.write(oneLine);
				bw.flush();
				//System.out.println(oneLine);
			}
			br.close();
			bw.close();
		//System.out.println("parse Done");
		}
		catch (IOException ex) 
		{
			ex.printStackTrace();
		}
	}
	
	public static String getRoad(String address)
	{
		
		String re1 = ".*(路|大道|街)";
		String re2 = ".*巷";
		String re3 = ".*地號";
		
		Pattern pattern1 = Pattern.compile(re1);
		Pattern pattern2 = Pattern.compile(re2);
		Pattern pattern3 = Pattern.compile(re3);
		
		Matcher matcher1 = pattern1.matcher(address);
		Matcher matcher2 = pattern2.matcher(address);
		Matcher matcher3 = pattern3.matcher(address);
		
		if(matcher1.find() && !matcher3.find())
			return matcher1.group();
		else if(matcher2.find())
			return matcher2.group();
		else
			return "Invalid Address";
	}
	
	public static int roadIndex(String road, ArrayList<Road> list)
	{
		int size = list.size();
		boolean find = false;
		int index = -1;
		for(int i=0; i<size; i++)
		{
			Road tempRoad = list.get(i);
			String name = tempRoad.getName();
			if(road.equals(name))
			{
				index = i;
				find = true;
				break;
			}
		}
		if(!find)
			System.out.println("Road Not Found");
		return index;
	}	
}	
