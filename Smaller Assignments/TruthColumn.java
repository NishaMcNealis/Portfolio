//TruthColumn class
//Tests various operations associated with truth tables.

package completed;


public class TruthColumn {

//an internal representation of an array of Booleans.
private boolean[] truthTable;

//the size of the truth column (number of rows)
//this is also the size of every parameter the user inputs
private final int TRUTH_COL_SIZE = 4;

//CONSTRUCTOR
//initializes truthTable
public TruthColumn(){
	truthTable = new boolean[TRUTH_COL_SIZE];
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return truthTable
//modifies truthTable
public boolean[] and(boolean[] p, boolean[] q){
	for (int i = 0; i < p.length; i ++){
		if (p[i] && q[i]) truthTable[i] = true;
		else truthTable[i] = false;
	}
	return truthTable;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return truthTable
//modifies truthTable
public boolean[] or(boolean[] p, boolean[] q){
	for (int i = 0; i < p.length; i ++){
		if (p[i] || q[i]) truthTable[i] = true;
		else truthTable[i] = false;
	}
	return truthTable;
}

//@param vals, a boolean array
//@return truthTable
//modifies truthTable
public boolean[] negate(boolean[] vals){
	for (int i = 0; i < vals.length; i ++){
		truthTable[i] = !vals[i];
	}
	return truthTable;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the truth table values are biconditional
//false if not
public boolean biconditional(boolean[] p, boolean[] q){
	for (int i = 0; i < p.length; i ++){
		if (p[i] && !q[i]) return false;
	}
	return true;
}
//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the first truth table value
//implies the second
//false if not
public boolean imply(boolean[] p, boolean[] q){
	for (int i = 0; i < p.length; i ++){
		if (p[i] && !q[i]) return false;
	}
	return true;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the truth table values are valid
//false if not
public boolean valid(boolean[] p){
	for (boolean val : p){
		if (!val) return false;
	}
	return true;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the truth table values are unsatisfiable
//false if not
public boolean unsatisfiable(boolean[] p){
	for (boolean val : p){
		if (val) return false;
	}
	return true;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the truth table values are contingent
//false if not
public boolean contingent(boolean[] p){
	if (!(valid(p)) && !(unsatisfiable (p))) return true;
	return false;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the truth table values are equivalent
//false if not
public boolean equivalent(boolean[] p, boolean[] q){
	for (int i = 0; i < p.length; i ++){
		if (p[i] != q[i]) return false;
	}
	return true;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the first truth table value
//entails the other, false if not
public boolean entails(boolean[] p, boolean[] q){
	for (int i = 0; i < p.length; i ++){
		if (p[i] && !q[i]) return false;
	}
	return true;
}

//precondition: p and q are the same length
//@param p and q, two boolean arrays
//@return true if the truth table values are consistent
//false if not
public boolean consistent(boolean[] p, boolean[] q){
	for (int i = 0; i < p.length; i ++){
		if (p[i] && q[i]) return true;
	}
	return false;
}
}
