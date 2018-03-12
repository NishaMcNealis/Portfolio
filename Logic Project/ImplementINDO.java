package completed;
import java.util.ArrayList;

public class ImplementINDO {
	static String prev = "";
static String next = "";
static String b = "";
/* CONSTRUCTOR
The constructor runs all four steps of INDO on the String parameter it is given.
*/
static public String INDO(String s){
		//ArrayList ResList, an arrayList of arrayLists
		return operators(distribution(negation(implication(s))));
		}
/*FIND WORDS
This method takes a string and, using the 
space as a delimiter, makes each word an element of an ArrayList.
 */
public static ArrayList findWords(String s){
	ArrayList<String> lst = new ArrayList<String>();
	String temp = "";
	for (int i = 0; i < s.length(); i ++){
		temp += s.substring(i, i+1);
		if (Character.isWhitespace(s.charAt(i))){
			lst.add(temp);
			temp = "";
		}
		
		//else if (s.substring(i, i+1) != "(" && s.substring(i, i+1) != ")") 
		//else temp += s.substring(i, i+1);
		 
		
	}
	lst.add(temp);
	//System.out.println(lst);
	return lst;
}
	/******************************IMPLICATION****************************************
	 * 
	 * @param s
	 * The string that represents the original input from the user.
	 * @return the string with any implication symbols translated into operators (| or &),
	 * with ~ signs placed before the appropriate variables according to INDO laws.
	 */
	public static String implication(String s){
		ArrayList<String> lst = findWords(s);
		for (String word : lst){
			if (!Character.isLetter(word.charAt(0))){
			//b is a symbol
			b = word;
			int i = lst.indexOf(b);
			if (lst.size() == 1) return s;
			if (i > 0) prev = lst.get(i - 1);
			if (0 <= i & i < b.length()) next = lst.get(i + 1);
			if (b.contains("<=>")){
				return biconditional(lst, s, i);
			}
			else if (b.contains("=>")){
				return notPrev(lst, s, i);
			}
			else if (b.contains("<=")){
				return notNext(lst, s, i);
			}		
		}
		}
		return s;
		
		}
	/* BICONDITIONAL
	 * Input: an ArrayList lst representing words in String s, String s, and int i
	 * representing the index of the biconditional symbol.
	 * Output: a new string translating the biconditional into an | symbol
	 * and adding ~ before the appropriate variables.
	 */
	public static String biconditional(ArrayList<String> lst, String s, int i){
		String left = "";
		for (int k = lst.indexOf(next) + 1; k < lst.size(); k ++){
			left += lst.get(k);
	  }
		return s.substring(0, i - 1) + " (~" + prev 
				+ "|" + next + ") & (" + prev + "| ~" + next + ")"+ left;
	}
	/* NOT PREV
	 * Input: an ArrayList lst representing words in String s, String s, and int i
	 * representing the index of the implication symbol.
	 * Output: a new string with the previous variable as ~ + itself and the =>
	 * sign as an |.
	 */
	public static String notPrev(ArrayList<String> lst, String s, int i){
		lst.set(i-1, "~" + prev);
		return implies(lst, s, i);
	}
	/* NOT NEXT
	 * Input: an ArrayList lst representing words in String s, String s, and int i
	 * representing the index of the implication symbol.
	 * Output: a new string with the next variable as ~ + itself and the =>
	 * sign as an |.
	 */
	public static String notNext(ArrayList<String> lst, String s, int i){
		lst.set(i + 1,  "~" + next);
		return implies(lst, s, i);
	}
	/* IMPLIES
	 * Input: an ArrayList lst representing words in String s, String s, and int i
	 * representing the index of the implication symbol.
	 * Output: a new string with the implication sign changed to |.
	 */
	public static String implies(ArrayList<String> lst, String s, int i){
		String retval = "";
		lst.set(i, "|");
		for (int k = 0; k < lst.size() - 1; k ++){
			retval += lst.get(k) + " ";
		  }
		return retval + lst.get(lst.size() - 1);
	}
/******************************NEGATION****************************************
 * 
 * @param s
 * The string that represents the output of implication(), which has just run.
 * @return the string with...
 * 1. any double negation symbols ("~~") removed
 * 2. any negation symbols outside the parenthesis distributed through
 * the parenthesis. 
 */
public static String negation(String s){
	String retval = "";
	String nextChar = "";
	ArrayList<String> lst = findWords(s);
	for (String word : lst){
		int i = lst.indexOf(word);
		if (word.contains("~")){
			nextChar = word.substring(word.indexOf('~') + 1, word.indexOf('~') + 2);
			
			if (nextChar.equals("~")){
				word =  word.substring(0, word.indexOf('~')) + word.substring(word.indexOf('~') + 2);
				lst.set(i, word);
				retval += word;
			}
			else if (nextChar.equals("(")){
				retval = distNeg(retval, nextChar, word, lst);
				return negation(retval);
			}
		else{
			retval += word;
		}
		
		}
		else{
			retval += word;
		}
	}
	
	return retval;
}
/* DIST NEG (distributes the negation symbol)
 * Input: Strings retval, nextChar and word, which correspond to 
 * variables of the same name in negation(). An ArrayList lst representing words in 
 * the original string.
 * Output: a new string with the ~ sign distributed across each variable
 * and each operator changed to the opposite one.
 */
	public static String distNeg(String retval, String nextChar, String word, ArrayList<String> lst){
		retval += nextChar + " ";
		for (int k = lst.indexOf(word) + 1; k < lst.size(); k ++){
			String word2 = lst.get(k);
			//System.out.println(word2);
			if (word2.contains("|")){
				retval += word2.substring(0, word2.indexOf("|")) + "&" + word2.substring(word2.indexOf("|") + 1);
			}
			else if (word2.contains("&")){
				retval += word2.substring(0, word2.indexOf("&")) + "|" + word2.substring(word2.indexOf("&") + 1);
			}
			
			else if (!word2.contains(")")){
				retval += "~" + word2;
			}
			else if (word2.contains(")")){
				retval +=  word2;
				break;
			}
			
		}
		return retval;
	}
	
/******************************DISTRIBUTION****************************************
 * 
 * @param s
 * The string that represents the output of negation(), which has just run.
 * @return the string with any operations outside the parenthesis distributed through
 * the parenthesis and any unecessary parethesis removed. 
 */
	public static String distribution(String s){
		String retval = "";
		
		//If there is no parenthesis or no operations, the string can be immediately returned.
		if (!s.contains("(")) return s;
		if (!s.contains("|") & !s.contains("&")) return s;
		
		ArrayList<String> lst = findWords(s);
		String outerOp = "";
		String innerOp = "";
		for (String word : lst){
			if (word.contains("(") & outerOp == "") return s; //there is no outer operation
			else if (outerOp == ""){
				if ((word.contains("|") || word.contains("&"))){
					outerOp = word;
					
				}
			}
			else if (innerOp == ""){
				if (word.contains("|") || word.contains("&")){
					innerOp = word;
					
				}
			}
			else if (outerOp.equals(innerOp)){
				return sameOp(retval, lst);
			}
			else{
				return diffOp(retval, lst, outerOp, innerOp);
			}
		}
		return retval;
	}
	/* SAME OP (runs if the outer and inner operations are the same)
	 * Input: String retval,which corresponds to the variable of the same name in distribution()
	 * and will be returned. An ArrayList lst representing words in the original string.
	 * Output: a new string without the parenthesis
	 */
	public static String sameOp(String retval, ArrayList<String> lst){
		for (String w : lst){
			if (!w.contains("(") & !w.contains(")")){
				retval += w;
			}
		}
		return retval;
	}
	/* DIFF OP (runs if the outer and inner operations are not the same)
	 * Input: Strings retval,outerOp, and innerOp which correspond to the variables of the same name in distribution().
	 * Retval will be returned. An ArrayList lst representing words in the original string.
	 * Output: a new string with the first term distributed to all terms in the parenthesis.
	 * The inner operation and outer operation effectively switch because the original operation can be found
	 * inside the parenthesis while the inner operation is outside.
	 */
	public static String diffOp(String retval, ArrayList<String> lst, String outerOp, String innerOp){
		String prev = "";
		String a = "";
		String b = "";
		Boolean done = false;
		int p = 2;
			for (String w : lst){
				if (w.contains("(")){
					p = lst.indexOf(w);
					for (int i = lst.indexOf(w) + 1; i <= lst.size(); i ++){
						if (lst.get(i).contains(innerOp)){
							lst.set(i, outerOp);
						}
						if (lst.get(i).contains(")")) break;
					}
					lst.set(lst.indexOf(w) - 1, innerOp);
					
				a = "( " + lst.get(p-2);
				b = lst.get(p+1);
				
				retval += a + outerOp + b +  ") " + innerOp + a + outerOp + lst.get(p + 3);
				for (int q = p + 4; q < lst.size(); q ++){					
					retval += lst.get(q);
				}
				
			   }
			}
		return retval;
	}
/******************************OPERATORS****************************************
 * 
 * @param s
 * The string that represents the output of distribution(), which has just run.
 * @return the string in set form. The symbol & signifies a new set while | signifies
 * a new term in the same set.
 */
	public static String operators(String s){
			String retval = "{";
			for (int i = 0; i < s.length(); i ++){ 
				b = s.substring(i, i + 1);
				if (b .equals("|")){
					retval += ", " ;
				}
				else if (b .equals("&")){
					retval += "},{";
				}
				else if (!b .equals("(") & !b .equals(")")){
					retval += b;
				}
			}
			return retval + "}";
			
	}
		

/*MAIN
 * The main method sets up the table heading and invokes testCase
 * for many different test cases.
*/
public static void main (String[] args){
	System.out.println("*****************TEST CASE TABLE*****************\n");

	System.out.println("Input" + "\t \t \t| Output");
	System.out.println("------------------------------------------------------");
	testCase("a", 3);
	testCase("~~a",3);
	testCase("~( ~a )",3);
	testCase("(a | b | c) | d", 2);
	testCase("a => b", 3);
	testCase("~a => b", 3);
	testCase("a <=> ~b", 2);
	testCase("a | ( b => c )", 2);
	testCase("a |  b & c", 2);
	testCase("a => ( ~b | c ))", 1);


}

/*TESTCASE
 * This method runs INDO for the test cases it is given from main.
 * It also sets up the formatting of the table.
 */
public static void testCase(String s, int tabs){
	String tabNum = "";
	for (int i = 0; i < tabs; i ++){
		tabNum += "\t";
	}
	System.out.println((s) + tabNum + "| " + INDO(s));
}
}
