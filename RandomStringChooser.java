package completed;

import java.util.ArrayList;

public class RandomStringChooser
{
	  private ArrayList<String> wordsList;
	  
/* Creates an ArrayList called words and copies strings
 * from the given list ("values") into the ArrayList.
 * @param values- a list of strings
*/
	  public RandomStringChooser(String[] values)
	  {
	    wordsList = new ArrayList<String>();
	    
	    for(String value : values)
	    	wordsList.add(value);
	  }
  /* Returns a random string from wordsList, 
   * then removes the string from wordsList. 
   * If wordsList is empty, returns "NONE".
  */
	  public String getNext()
	  {
	    if(wordsList.size() == 0) //there are no more unused words
	      return("NONE");
	    else{
	    int randomNumber = (int) (Math.random() * wordsList.size());
	    String retval = wordsList.get(randomNumber);
	    wordsList.remove(randomNumber);
	    return(retval);
	    }
	  }
  public static void main (String[] args){
	  String[] test = {"these", "strings", "are", "mixed","up"};
	  RandomStringChooser r = new RandomStringChooser(test);
	  for (int i = 0; i < 5; i++){
		  System.out.println(r.getNext());
	  }
	  System.out.println(r.getNext()); //should print 'NONE' since there are no more unused strings

  }
}