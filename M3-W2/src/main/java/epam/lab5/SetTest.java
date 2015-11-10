package epam.lab5;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetTest {

	private static final int SIZE = 1000;

	public static long addTime(Set<String> set, String element) {
		long time = System.nanoTime();
		set.add(element);
		return System.nanoTime() - time;
	}

	public static long removeTime(Set<String> set, String element) {
		long time = System.nanoTime();
		set.remove(element);
		return System.nanoTime() - time;
	}
	
	public static long searchTime(Set<String> set, String element) {
		long time = System.nanoTime();
		set.contains(element);
		return System.nanoTime() - time;
	}

	public static void main(String[] args) {

		Set<String> hashSet = new HashSet<>();
		Set<String> treeSet = new TreeSet<>();
		
		String toAdd = "500";
		String toRemove = "100";		

		for (int i = 0; i < SIZE; i++) {
			hashSet.add(((Integer) i).toString());
		}
		
		for (int i = 0; i < SIZE; i++) {
			treeSet.add(((Integer) i).toString());
		}

		System.out.println(addTime(hashSet, toAdd));
		System.out.println(removeTime(hashSet, toRemove));
		System.out.println(searchTime(hashSet, toAdd));
		
		System.out.println("==============================");
		
		System.out.println(addTime(treeSet, toAdd));
		System.out.println(removeTime(treeSet, toRemove));
		System.out.println(searchTime(treeSet, toAdd));

	}

}
