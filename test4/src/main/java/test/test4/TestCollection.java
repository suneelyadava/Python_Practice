package test.test4;

import java.util.*;

public class TestCollection implements TestMap{
	
	public void TestHashMap() {
		// TODO Auto-generated method stub
		
		Map<Integer, String> map =new HashMap<Integer, String>();
		map.put(101, "Mr. Suneel");
		map.put(null, "Nitin");
		
		
	}
	public void TestHashTable() {
		// TODO Auto-generated method stub
		
		Hashtable<Integer, String> table=new Hashtable<Integer, String>();
		table.put(555, "Mr. Sanu");
		table.put(888, "test");
		
	}
	public static void main(String[] args) {
		
		new TestCollection().TestHashTable();
		
	}

}
