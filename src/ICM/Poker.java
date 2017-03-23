package ICM;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Poker {
	
	String cards = "23456789TJQKA";
	String hand;
	int result = 0, rank = 0, highCard = 0;
	boolean flush = false, straight = false;
	
	Set <Character> tempSet = new HashSet <Character>();
	Set <Character> RoyalFlush = new HashSet<Character>();
	HashMap <Character,Integer> count = new HashMap <Character,Integer>();
	HashMap <Character,Integer> tempCount = new HashMap <Character,Integer>();
	
	
	public HashMap <Character,Integer> checkHand(String hand){
		
		RoyalFlush.add('T');
		RoyalFlush.add('J');
		RoyalFlush.add('Q');
		RoyalFlush.add('K');
		RoyalFlush.add('A');
		
		tempSet.clear();
		tempCount.clear();
		count.clear();
		
		for(int k=0; k<hand.length(); k=k+3){
			tempSet.add(hand.charAt(k));
			tempCount.put(hand.charAt(k), 0);
		}
		
		//System.out.println("I am just after the tempcount is created: "+tempCount);
		for(int i=0; i<hand.length(); i=i+3){
			for(int j=i+3; j<hand.length(); j=j+3){
				if(hand.charAt(i) == hand.charAt(j)){
					if(!count.containsKey(hand.charAt(i))){
						count.put(hand.charAt(i),1);
					}else{
						count.put(hand.charAt(i), count.get(hand.charAt(i))+1);
						
					}
				}
			}
		}
		
		flush = checkSuit(hand);
		straight = checkStraight(tempSet);
		
		if(flush && straight && tempSet.containsAll(RoyalFlush)){
			System.out.println("Royal Flush");
			result =  10;
		}
		else if(flush && straight){
			System.out.println("Staright Flush");
			result = 9;
			return tempCount;
		}
		else if(fourofAKind(count)){
			System.out.println("Four of a kind");
			result = 8;
			return tempCount;
		}
		else if(threeofAKind(count) && (numberOfPair(count) == 1)){
			System.out.println("Full House");
			result = 7;
			System.out.println(tempCount);
			System.out.println("No. of Pairs: "+numberOfPair(count));
			return tempCount;
		}
		else if(flush){
			System.out.println("Flush");
			result = 6;
			return tempCount;
		}
		else if(straight){
			System.out.println("Straight");
			result = 5;
			return tempCount;
		}
		else if(threeofAKind(count)){
			System.out.println("Three of a kind");
			//System.out.println("In three of a Kind: "+tempCount);
			result = 4;
			return tempCount;
		}
		else if(numberOfPair(count) == 2){
			System.out.println("Two different pairs");
			//highCard = getRank(count);
			//tempCount.clear();
			for(int i=0; i<hand.length(); i=i+3){
				if(!count.containsKey(hand.charAt(i)))
					tempCount.put(hand.charAt(i),0);
			}
			result = 3;
			return count;
		}
		else if(numberOfPair(count) == 1){
			System.out.println("One Pair");
			//highCard = getRank(count);
			//tempCount.clear();
			for(int i=0; i<hand.length(); i=i+3){
				if(!count.containsKey(hand.charAt(i)))
					tempCount.put(hand.charAt(i),0);
			}
			result = 2;
			return count;
		}
		else{
			//highCard = getRank(tempCount);
			//System.out.println("High Card: "+highCard);
			result = 1;
			//System.out.println("I am in Poker: "+ tempCount);
			return tempCount;
		}
		return count;
	}
	
	
	
	public boolean fourofAKind(HashMap <Character,Integer> count1){
		
		for(Character key: count1.keySet()){
			if(count1.get(key) == 6){
				tempCount.clear();
				tempCount.put(key, 6);
				return true;
			}
		}
		return false;
	}

	public boolean threeofAKind(HashMap <Character,Integer> count1){
				for(Character key: count1.keySet()){
			if(count1.get(key) == 3){
				tempCount.clear();
				tempCount.put(key, 3);
				return true;
			}
		}
		return false;
	}
	
	public int numberOfPair(HashMap <Character,Integer> count1){
		int pair = 0;
		for(Character key: count1.keySet()){
			if(count1.get(key) == 1){
				pair++;
			}
		}
		return pair;
	}
	
	
	public boolean checkSuit(String Player){
		char suit = Player.charAt(1);
		for(int i=4; i<Player.length(); i=i+3){
			if(suit != Player.charAt(i))
				return false;
		}
		return true;
	}
	
	public boolean checkStraight(Set <Character> tempSet){
		int [] ranks = new int[5];
		int i = 0;
		String cards = "23456789TJQKA";
		for(Character key: tempSet){
			ranks[i] = cards.indexOf(key);
			i++;
		}
		
		Arrays.sort(ranks);
		//for(int n=0; n<ranks.length; n++)
		//System.out.println("Test Array: "+ranks[n]);
		
		if(ranks[4] == ranks[0]+4)
			return true;
		else
			return false;
	}
	
	
	public int getRank(HashMap <Character,Integer> count1){
		//System.out.println("Count 1 Size: "+ count1.size());
		int  [] tempArr = new int[count1.size()];
		int i= 0;
		int temp = 0;
		//String cards = "23456789TJQKA";
			for(Character key: count1.keySet()){
				tempArr[i] = cards.indexOf(key);
				i++;
			}
		
			
		if(tempArr.length == 1)
			return tempArr[temp];
		else{
			for(int j=0; j<tempArr.length-1; j++){
				if(tempArr[temp] < tempArr[j+1]){
					temp = j+1;
				}
			}
		}
		return tempArr[temp];
	}



	public int getResult() {
		return result;
	}
	
	public int getHighCard() {
		return highCard;
	}
	
	
}
