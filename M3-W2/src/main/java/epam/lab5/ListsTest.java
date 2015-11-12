package epam.lab5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListsTest {

    //TODO: NOTE: Для наглядности рекомендуется использовать от 7000+ элементов в коллекции
	private static final int SIZE = 500;
	private static final int MIN = 0;
	private static final int MAX = 50;

	public static Integer randInt(int min, int max) {

        //TODO: Don't use random initializing of collection, we should compare the same data

        Random rand = new Random();
		Integer randomNum = (Integer) rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public static long addToEndTime(List<String> list, String element) {
		long time = System.nanoTime();
		list.add(element);
		return System.nanoTime() - time;
	}

	public static long addToMiddleTime(List<String> list, String element) {
		long time = System.nanoTime();
		list.add(list.size() / 2, element);
		return System.nanoTime() - time;
	}

	public static long addListToEndTime(List<String> list, List<String> addedList) {
		long time = System.nanoTime();
		list.addAll(addedList);
		return System.nanoTime() - time;
	}

	public static long addListToMiddleTime(List<String> list, List<String> addedList) {
		long time = System.nanoTime();
		list.addAll(list.size() / 2, addedList);
		return System.nanoTime() - time;
	}

	public static long removeFromCurrentPositionTime(List<String> list, int index){
		long time = System.nanoTime();
        //TODO IndexOutOfBoundsException
		list.remove(index);
		return System.nanoTime() - time;
	}
	
	public static long removeCurrentElementTime(List<String> list, String element){
		long time = System.nanoTime();
		list.remove(element);
		return System.nanoTime() - time;
	}
	
	public static long removeLastTime(LinkedList<String> list){
		long time = System.nanoTime();
		list.removeLast();
		return System.nanoTime() - time;
	}
	
	public static void main(String[] args) {


		
		List<String> arrayList = new ArrayList<>();
		List<String> linkedList = new LinkedList<>();

		String toAdd = "40";

		List<String> subArrayList = new ArrayList<>(10);

		for (int i = 0; i < SIZE; i++) {
			String elem = randInt(MIN, MAX).toString();
			arrayList.add(elem);
		}
		
		for (int i = 0; i < SIZE; i++) {
			String elem = randInt(MIN, MAX).toString();
			linkedList.add(elem);
		}

		for (int i = 0; i < subArrayList.size(); i++) {
			String elem = randInt(MIN, MAX).toString();
			subArrayList.add(elem);
		}

        //TODO: Compare adding also at the begging
        //TODO: Compare adding Search / Delete for 3 elements in collection: at the begging, at the middle and at the end
        //TODO Add more information about gotten numbers

        System.out.println(addToEndTime(arrayList, toAdd));
		System.out.println(addToMiddleTime(arrayList, toAdd));
		System.out.println(addListToEndTime(arrayList, subArrayList));
		System.out.println(addListToMiddleTime(arrayList, subArrayList));
        System.out.println("===================================");
		System.out.println(removeFromCurrentPositionTime(arrayList, 200));
		System.out.println(removeCurrentElementTime(arrayList, toAdd));
        System.out.println("===================================");
		System.out.println(addToEndTime(linkedList, toAdd));
		System.out.println(addToMiddleTime(linkedList, toAdd));
		System.out.println(addListToEndTime(linkedList, subArrayList));
		System.out.println(addListToMiddleTime(linkedList, subArrayList));
        System.out.println("===================================");
		System.out.println(removeFromCurrentPositionTime(linkedList, 200));
		System.out.println(removeCurrentElementTime(linkedList, toAdd));
		System.out.println(removeLastTime((LinkedList<String>)linkedList));

	}

}
