package completed;
/* Author: Nisha McNealis
 * Instructor: Mr. Kuszmaul
 * Date: 1/18/17
 * 
 * Selection Sort:
 * 
 * This is a class that models the selection sorting algorithm that we went over in class.
 * Using the selection sort algorithm, the program searches for the lowest element 
 * with the method findMin. It then puts that element at the beginning of the array and 
 * repeats the process.
 * 
 * I used the following video to find out about selection sorting. 
 * https://www.youtube.com/watch?v=f8hXR_Hvybo
 * 
 * The video was very useful- I would recommend it to anyone in the class who wants a 
 * model of the different types of sorting.
 */


import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort {
	ArrayList<Integer> numsToSort;
	int currentMin;
	int index;
	
	public SelectionSort(){
		numsToSort = new ArrayList<Integer>(Arrays.asList(3, 4, 6, 2, 1, 10, 2));

	}
	/* Input: No input, but numsToSort must be defined by the constructor first
	 * Output: A fully sorted numsToSort
	 * Effect: Changes numsToSort and currentMin
	 * 
	 * This method iterates through an ArrayList and finds the minimum value.
	 * It then adds this value to the beginning of the array, the sorted section.
	 * It repeats the process until every element in the ArrayList has been sorted.
	 */
	public ArrayList<Integer> sort(){
		index = 0;
		for (int i = 0; i < numsToSort.size(); i++){
			currentMin = numsToSort.get(numsToSort.size() - 1);
			int temp = findMin(numsToSort);
		   	numsToSort.add(index, temp);
		   	numsToSort.remove(numsToSort.lastIndexOf(temp));
			index ++;

		}
		return numsToSort;
	}
	/* Input: numsToSort, unsorted or partially sorted
	 * Output: The minimum value in the arrayList
	 * Effect: Changes currentMin
	 */
	public int findMin(ArrayList<Integer> numsToSort){
		for (int k = index; k < numsToSort.size(); k++){ 
			if(numsToSort.get(k) < currentMin){
				currentMin = numsToSort.get(k);
			}
            }
   	 return currentMin;        
		
	}
	public static void main (String args[]){
		SelectionSort b = new SelectionSort();
		for (int x : b.sort()){
			System.out.print(x + " ");
		}
		}
}
	

