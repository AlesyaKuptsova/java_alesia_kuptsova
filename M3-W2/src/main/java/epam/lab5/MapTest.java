package epam.lab5;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
	
	private static final int SIZE = 500;
	
	public static long addTime(Map<Integer, String> map,  Integer key, String value){
		long time = System.nanoTime();
		map.put(key, value);
		return System.nanoTime() - time;
	}
	
	public static long removeTime(Map<Integer, String> map,  Integer key){
		long time = System.nanoTime();
		map.remove(key);
		return System.nanoTime() - time;
	}
	
	public static long searchTime(Map<Integer, String> map,  Integer key){
		long time = System.nanoTime();
		map.containsKey(key);
		return System.nanoTime() - time;
	}

	public static void main(String[] args) {
		Map<Integer, String> hashMap = new HashMap<>();	
		Map<Integer, String> treeMap = new TreeMap<>();
		
		for(int i = 0; i < SIZE; i++){
			hashMap.put(i, "simple-string"+i);
		}
		
		for(int i = 0; i < SIZE; i++){
			treeMap.put(i, "simple-string"+i);
		}
		
		System.out.println(addTime(hashMap, 600, "added value"));
		System.out.println(removeTime(hashMap, 4));
		System.out.println(searchTime(hashMap, 600));
		
		System.out.println("===================================");
		
		System.out.println(addTime(treeMap, 600, "added value"));
		System.out.println(removeTime(treeMap, 4));
		System.out.println(searchTime(treeMap, 600));
	}

}
