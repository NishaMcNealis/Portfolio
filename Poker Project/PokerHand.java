package completed;
/*
 * Here is my code for PokerHand, an assignment in 
 * which we had to create a simulation of a poker game
 * and calculate the probability of various special
 * hands.
 */


import java.util.ArrayList;
import java.util.Arrays;

public class PokerHand extends Deck{
	Card[] myHand;
	static int numCards = 5; //can be changed to 7
	static boolean two = false;
	
	//Constructor that invokes dealCards()
	public PokerHand(Card[] cards){
		myHand = dealCards();
			
		}
    
    public Card[] dealCards()
    {
       Deck deck = new Deck();
       Card[] myHand = new Card[numCards];
       int i = 0;
       while(i < numCards) //use while instead of for so that if there's a 
    	   			    	// repeat card, it still works
       {
          deck.shuffle();
          Card temp = deck.cards[0];
          boolean equal = false;
          for (int j = 0; j < i; j++)
          {
             if (temp.equals(myHand[j])) //if we've already used temp, we don't want it again
             {
                equal = true;
                break; //go back, shuffle again
                //note: if we do break, the code never reaches i++
             }
          }
          if (!equal)
          {
             myHand[i] = temp;
             i++;
          }
       }
       return myHand;
    }
    
    
    //These are all of the types of special hands in poker
	public boolean hasFlush(){
		for (int i = 0; i < numCards; i++){
			if (myHand[0].suit != myHand[i].suit) return false;
		}
		return true;
	}
	public Boolean hasThreeKind(){
		for (int i = 0; i < numCards; i++){
			for (int j = 1; j < numCards; j++){
				for (int k = 2; k < numCards; k++){
					if (myHand[i].rank == myHand[j].rank && myHand[i].rank ==myHand[k].rank){
						if (i != k && i != j && k != j) return true;
					}
				}
			}
			
		}
		return false;
}
	public boolean hasStraightFlush(){
		if (!hasFlush()) return false;
		for (int i = 0; i < 6; i++){
			if (myHand[i].rank + 1 != myHand[i + 1].rank) return false;
		}
		return true;		
	
	}
	public boolean hasFourKind(){
		for (int i = 0; i < numCards; i++){
			for (int j = 1; j < numCards; j++){
				for (int k = 2; j < numCards; j++){
					for (int l = 3; j < numCards; j++){
					if (myHand[i].rank == myHand[j].rank && myHand[i].rank == myHand[k].rank &&
							myHand[i].rank == myHand[l].rank){
						if (i != j && i != k && i != l && j != l && j != k && l != k) return true;
					}
					}
				}
				}
			}

		return false;
	}
	public boolean hasOnePair(){
		for (int i = 0; i < numCards; i++){
			for (int j = 1; j < numCards; j++){
					if (myHand[i].rank == myHand[j].rank){
						if (i != j) return true;
					}
				}
			}

		return false;
	}
	public boolean hasTwoPair(){
		boolean one = false;
		ArrayList<Integer> used = new ArrayList<Integer>();
		for (int i = 0; i < numCards; i++){
			for (int j = 1; j < numCards; j++){
					if (myHand[i].rank == myHand[j].rank){
						if (i != j && !used.contains(myHand[i].rank)){
							if (one)return true;
							one = true;
							//System.out.println(myHand[i].rank + "\n");
							used.add(myHand[i].rank);
						}
						
					}
				}
			}
	
		return false;
	}
	
	//the toString
	@Override
	public String toString() {
		for (int i = 0; i < numCards; i++){
			System.out.println(myHand[i].toString());
		}
		return "\n";
	}
	public static void main(String[] args){
    	//define the variables that count how many of each type of hand
    	//are present in the sample
    	int flush = 0;
    	int straight = 0;
    	int three = 0;
    	int One = 0;
    	int four = 0;
    	int twoCount = 0;
    	int noPair = 0;
    	
    	//Part 1: print out 4 hands
    	for (int i = 0; i < 4; i++){
			Deck deck1 = new Deck();
			deck1.shuffle();
			PokerHand p = deck1.deal();
			System.out.println(p);
		}
    	
    	//Part 2: probabilities
		for (int i = 0; i < 4000; i++){
			Deck deck1 = new Deck();
			deck1.shuffle();
			PokerHand p = deck1.deal();
			if (p.hasFlush()) flush ++;
			if(p.hasThreeKind()) three++;
			if(p.hasOnePair()) One++;
			if(p.hasTwoPair()) twoCount++;
			if(p.hasStraightFlush()) straight++;
			
			//if it has none of these, it's a high card/no pair
			if(!(p.hasFlush() || p.hasThreeKind() || p.hasOnePair() || p.hasTwoPair() || p.hasFourKind())) noPair ++;
		}
		
		//printing out all the probabilities
		System.out.println("Three of a Kind Probability: "+ (three) + " out of 4000, or " + ((double)three/4000) * 100 + " percent");
		System.out.println("Flush Probability: "+ (flush) + " out of 4000, or " + ((double)flush/400) * 100 + " percent");
		System.out.println("Straight Flush Probability: "+ (straight) + " out of 4000, or " + ((double)straight/400) * 100 + " percent");
		System.out.println("One Pair Probability: "+ (One) + " out of 4000, or " + ((double)One/4000) * 100 + " percent");
		System.out.println("Two Pair Probability: "+ (twoCount) + " out of 4000, or " + ((double)twoCount/4000) * 100 + " percent");
		System.out.println("Four of a Kind Probability: "+ (four) + " out of 4000, or " + ((double)four/4000) * 100 + " percent");
		System.out.println("High Card Probability: "+ (noPair) + " out of 4000, or " + ((double)noPair/4000) * 100 + " percent");
    }
}
	

