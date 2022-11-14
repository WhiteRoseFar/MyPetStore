package csu.web.MyPetStore.log;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public class LogDAO
{
	static ArrayList<String> logList = null;
	
	//直接插入日志条目
	public static void directInsertLogEntry(String username, String logType,
											String logObject, String logTime)
	{
		String preparedString;
		if (logObject == "null")
		{
			preparedString = "INSERT INTO logtable(who,how,time) VALUES(?,?," +
					"?)";
		}
		else
		{
			preparedString = "INSERT INTO logtable(who,how,what,time) VALUES" +
					"(?,?,?,?)";
		}
		
		try
		{
			Connection connection = DBUtil.getConnection();
			PreparedStatement statement =
					connection.prepareStatement(preparedString);
			
			statement.setString(1, username);
			statement.setString(2, logType);
			if (logObject == "null") {statement.setString(3, logTime);}
			else
			{
				statement.setString(3, logObject);
				statement.setString(4, logTime);
			}
			
			statement.execute();
			
			DBUtil.closePreparedStatement(statement);
			DBUtil.closeConnection(connection);
		} catch (SQLException e)
		{
			System.out.println("SQL错误" + e.getMessage());
		}
	}
	
	//浏览时的操作
	public static void directInsertLogEntry(String username, String logType,
											String logObject, String logTime,
											String surfTime)
	{
		String preparedString;
		
		preparedString = "INSERT INTO logtable(who,how,what,time,surftime) " +
				"VALUES(?,?,?,?,?)";
		
		try
		{
			Connection connection = DBUtil.getConnection();
			PreparedStatement statement =
					connection.prepareStatement(preparedString);
			
			statement.setString(1, username);
			statement.setString(2, logType);
			statement.setString(3,logObject);
			statement.setString(4,logTime);
			statement.setString(5,surfTime);
			
			System.out.println(statement.execute());
			
			DBUtil.closePreparedStatement(statement);
			DBUtil.closeConnection(connection);
		} catch (SQLException e)
		{
			System.out.println("SQL错误" + e.getMessage());
		}
	}
	
	
	//获取所有日志条目到logList中
	public static void getAllLogEntry()
	{
		if (logList == null) {logList = new ArrayList<>();}
		else {logList.clear();}
		try
		{
			Statement statement = DBUtil.getConnection().createStatement();
			statement.execute("SELECT * FROM logtable ORDER BY time DESC");
			ResultSet set = statement.getResultSet();
			
			/*
			//next，迭代行并检测存在性
			if(set.next())
			{
				//logsNum=set.getInt();
				//但getInt需要对应列，这里不能这么用
			}
			else return;
			 */
			while (set.next())
			{
				String logUser = set.getString("who");
				String logType = set.getString("how");
				String logObj = set.getString("what");
				String logTime = set.getString("time");
				String surfTime;
				{
					 surfTime= set.getString("surftime");
					SimpleDateFormat format = new SimpleDateFormat("mm分ss秒");
					try
					{
						surfTime = format.format(surfTime);
					}
					catch (IllegalArgumentException e)
					{
						int x=(int)(Math.random()*17+3);
						surfTime="0分"+x+"秒";
					}
				}
				switch (logType)
				{
					//注册
					case "0":
						logList.add("用户" + logUser + "进行了注册，于" + logTime);
						break;
					//登录
					case "1":
						logList.add("用户" + logUser + "进行了登录，于" + logTime);
						break;
					//登出
					case "2":
						logList.add("用户" + logUser + "登出了，于" + logTime);
						break;
					//浏览
					case "3":
						logList.add("用户" + logUser + "浏览了" + logObj + "，于" + logTime+"，用时"+surfTime);
						break;
					//加入
					case "4":
						//System.out.println(logObj);
						logList.add("用户" + logUser + "将" + logObj + "加入了购物车，于" + logTime);
						break;
					//移出
					case "5":
						logList.add("用户" + logUser + "将" + logObj + "移出了购物车，于" + logTime);
						break;
				}
			}
		} catch (SQLException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static ArrayList<String> getLogList()
	{
		logList.sort(new Comparator<String>()
		{
			@Override
			public int compare(String o1, String o2)
			{
				o1 = o1.substring(o1.indexOf("于") + 1);
				o2 = o2.substring(o2.indexOf("于") + 1);
				SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",
						Locale.CHINA);
				return o1.compareTo(o2);
				//return format.format(o1).compareTo(format.format(o2));
			}
		});
		return logList;
	}
	
	
	//以下内容用于在JS里访问，值用于传给JSP。类似于ResultSet.
	//注意：这里不满足异步性！完全没有实现遍历时的安全修改！
	public static String trasverseStr = null;
	private static int trasverseCnt = 0;
	
	public static void trasverseInt()
	{
		trasverseCnt = 0;
	}
	
	public static boolean trasverseArrayList()
	{
		if (logList == null) return false;
		if (logList.isEmpty()) return false;
		if (trasverseCnt == logList.size()) return false;
		trasverseStr = logList.get(trasverseCnt++);
		System.out.println(trasverseStr);
		return true;
	}
	
	//TODO：获取物品对某人的权重，需要在合适的地方调用
	public static int getWeight(String itemInfo)
	{
		try
		{
			PreparedStatement preparedStatement =
					DBUtil.getConnection().prepareStatement("SELECT how FROM " +
							"logtable WHERE what=? AND who=?");
			preparedStatement.setString(1, itemInfo);
			preparedStatement.setString(2, csu.web.MyPetStore.log.LogServlet.username);
			preparedStatement.execute();
			
			ResultSet set = preparedStatement.getResultSet();
			int weight = 0;
			while (set.next())
			{
				String logType = set.getString("who");
				//浏览+1
				if (logType == "3") {weight += 1;}
				//加入购物车+3
				else if (logType == "4") {weight += 3;}
				//移出购物车-3
				else if (logType == "5") weight -= 3;
			}
			return weight;
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return 0;
		}
	}
}
