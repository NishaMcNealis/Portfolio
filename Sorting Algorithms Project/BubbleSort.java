package completed;
/* Author: Nisha McNealis
 * Instructor: Mr. Kuszmaul
 * Date: 1/18/17
 * 
 * Bubble Sort:
 * 
 * This is a class that models the bubble sorting algorithm that we went over in class.
 * Using the bubble sort algorithm, the program compares adjacent numbers in a list
 * If appropriate, the program switches the numbers. 
 * 
 * I used the following article to find out about bubble sorting. 
 * https://www.tutorialspoint.com/data_structures_algorithms/bubble_sort_algorithm.htm 

 */
import java.util.ArrayList;

import test.readScript;

public class BubbleSort {
	int[] numsToSort;
	
	public BubbleSort(){
		numsToSort = new int[]{3, 4, 6, 2, 1};
	}
	/* Input: No input, but numsToSort must be defined by the constructor first
	 * Output: A fully sorted numsToSort
	 * Effect: Changes numsToSort 
	 * 
	 * This method iterates through the ArrayList and compares adjacent elements.
	 * It then switches the numbers if the first one is greater than the second.
	 * It repeats the process for every element in the ArrayList.
	 */
	public int[] sort(){
		 int n = numsToSort.length;
         int temp = 0;
        
         for(int i=0; i < n; i++){
                 for(int j=1; j < (n-i); j++){
                    if(numsToSort[j-1] > numsToSort[j]){
                         //swap the elements
                         temp = numsToSort[j-1];
                         numsToSort[j-1] = numsToSort[j];
                         numsToSort[j] = temp;
                         }
                        
                 }
         }
		return numsToSort;
	}
	public static void main (String args[]){
		BubbleSort b = new BubbleSort();
		for (int x : b.sort()){
			System.out.print(x + " ");
		}
		}
}
	
