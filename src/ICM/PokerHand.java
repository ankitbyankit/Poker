package ICM;

import java.util.*;
import java.io.*;

public class PokerHand {
	
	//private static String cards = "23456789TJQKA";
	
	public static void main(String args[]){
	String Player1 = "2H 2D 4C 4D 4S";
	String Player2 = "3C 3D 3S 9S 9D";
	
	int pwin1 = 0, pwin2 = 0, split = 0;
	
	int rank1 = 0, rank2 = 0;
	
	HashMap <Character,Integer> count1 = new HashMap <Character,Integer>();
	HashMap <Character,Integer> count2 = new HashMap <Character,Integer>();
	
	Set <Character> temp;
	
	Poker P1 = new Poker();
	Poker P2 = new Poker();
	
	//HashMap <Character,Integer> tempCount1 = new HashMap <Character,Integer>();
	//HashMap <Character,Integer> tempCount2 = new HashMap <Character,Integer>();
	
	/*Set <Character> tempSet = new HashSet <Character>();
	Set <Character> RoyalFlush = new HashSet<Character>();
	
	RoyalFlush.add('T');
	RoyalFlush.add('J');
	RoyalFlush.add('Q');
	RoyalFlush.add('K');
	RoyalFlush.add('A');*/
	
	//boolean flush = false, straight = false;
	String cards = "23456789TJQKA";
	String fileName = "poker-hands.txt";
	String line = null;
	
	try{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			Player1 = line.substring(0,15);
			Player2 = line.substring(15, 29);
			
			System.out.println(Player1);
			System.out.println(Player2);
			
			//Code Pated below this line
			
			count1 = P1.checkHand(Player1);
			count2 = P2.checkHand(Player2);
			
			
			if(P1.getResult() == P2.getResult()){
				if(P1.getResult() == 10){
					split++;
					System.out.println("Both the players have Royal Flush, Split the money");
				}
				
				if(P1.getResult() == 9 || P1.getResult() == 5){
					if(P1.getRank(count1) == P2.getRank(count2)){
						split++;
						System.out.println("Same High card" + P1.getHighCard() +" Split the money");
					}
					else if(P1.getRank(count1) > P2.getRank(count2)){
						pwin1++;
					}else{
						pwin2++;
					}
				} 
				
				if(P1.getResult() == 8 || P1.getResult() == 7 || P1.getResult() == 4 ){
					if(P1.getRank(count1) > P2.getRank(count2)){
						pwin1++;
					}else{
						pwin2++;
					}
				}
				
				if(P1.getResult() == 6){
					temp = new HashSet(count1.keySet());
					if(!temp.isEmpty()){
						rank1 = P1.getRank(count1);
						rank2 = P2.getRank(count2);
						if(rank1 == rank2){
							count1.remove(cards.charAt(rank1));
							count2.remove(cards.charAt(rank2));
							//System.out.println("Same High card Split the money");
						}
						else if(rank1 > rank2){
							pwin1++;
						}else{
							pwin2++;
						}	
					}else{
						split++;
						System.out.println("Both the players have exactly same cards, split the money");
					}
					//temp.clear();
				}
				
				if(P1.getResult() == 3){
					temp = new HashSet(count1.keySet());
					while(!temp.isEmpty()){
						rank1 = P1.getRank(count1);
						rank2 = P2.getRank(count2);
						if(rank1 == rank2){
							count1.remove(cards.charAt(rank1));
							count2.remove(cards.charAt(rank2));
							temp = new HashSet(count1.keySet());
							//System.out.println("Same High card Split the money");
						}
						else if(rank1 > rank2){
							pwin1++;
							break;
						}else{
							pwin2++;
							break;
						}	

					}
					if(temp.isEmpty()){
						rank1 = P1.getRank(P1.tempCount);
						rank2 = P2.getRank(P2.tempCount);
						if(rank1 == rank2){
							split++;
							System.out.println("Same High card, Split the money");
						}
						else if(rank1 > rank2){
							pwin1++;
						}else{
							pwin2++;
						}	
					}
					
				}

				if(P1.getResult() == 2){
					rank1 = P1.getRank(count1);
					rank2 = P2.getRank(count2);
					if(rank1 == rank2){
						temp = new HashSet(P1.tempCount.keySet());
						while(!temp.isEmpty()){
							rank1 = P1.getRank(P1.tempCount);
							rank2 = P2.getRank(P2.tempCount);
							if(rank1 == rank2){
								P1.tempCount.remove(cards.charAt(rank1));
								P2.tempCount.remove(cards.charAt(rank2));
								temp = new HashSet(count1.keySet());
								//System.out.println("Same High card Split the money");
							}
							else if(rank1 > rank2){
								pwin1++;
								break;
							}else{
								pwin2++;
								break;
							}	
						}
						if(temp.isEmpty()){
							split++;
							System.out.println("Same Cards, Split the money");
						}
					}
					else if(rank1 > rank2){
						pwin1++;
					}else{
						pwin2++;
					}	
				}
				
				if(P1.getResult() == 1){
					temp = new HashSet(count1.keySet());
					System.out.println("I am null" + temp);

					while(!temp.isEmpty()){
						rank1 = P1.getRank(count1);
						rank2 = P2.getRank(count2);
												if(rank1 == rank2){
							count1.remove(cards.charAt(rank1));
							count2.remove(cards.charAt(rank2));
							temp = new HashSet(count1.keySet());

							//System.out.println("Same High card Split the money");
						}else if(rank1 > rank2){
							pwin1++;
							break;
						}else{
							pwin2++;
							break;
						}
					}
					
					if(temp.isEmpty()){
						split++;
						System.out.println("Same Cards-----, Split the money");
					}
					
				}
				
			}
			else if(P1.getResult() > P2.getResult())
				pwin1++;
			else{
				pwin2++;} /*System.out.println("Andar 3");*/
			
			System.out.println("Player 1: "+pwin1);
			System.out.println("Player 2: "+pwin2);
			System.out.println("Split: "+split);
			}
	}catch(IOException e){
		System.out.println("IO Exception in File: ");e.printStackTrace();
	}catch(ArrayIndexOutOfBoundsException e){
		System.out.println("Array Out of Bound: "); e.printStackTrace();
	}
	
}		
	
	
	
		
		/*if(P1.getResult() == 4){
			if(P1.getRank(count1) > P2.getRank(count2)){
				pwin1++;
			}else{
				pwin2++;
			}
		}*/
		
		}
	
	
	
	
	
	
	
	/*for(int k=0; k<Player1.length(); k=k+3){
		tempSet.add(Player1.charAt(k));
	}*/
	
	/*//System.out.println(tempSet);
	
	flush = p.checkSuit(Player1);
	straight = p.checkStraight(tempSet);
	
	if(flush && straight && tempSet.containsAll(RoyalFlush)){
		System.out.println("Royal Flush");
	}
	else if(flush && straight){
		System.out.println("Staright Flush");
	}
	else if(p.fourofAKind(count1)){
		System.out.println("Four of a kind");
	}
	else if(flush){
		System.out.println("Flush");
	}
	else if(straight){
		System.out.println("Straight");
	}
	else if(p.threeofAKind(count1)){
		System.out.println("Three of a kind");
	}
	else if(p.numberOfPair(count1) == 2){
		System.out.println("Two different pairs");
	}
	else if(p.numberOfPair(count1) == 1){
		System.out.println("One Pair");
	}
	else{
		int highCard = p.getRank(count1);
	}
	
	for(int i=0; i<Player1.length(); i=i+3){
		for(int j=i+3; j<Player1.length(); j=j+3){
			if(Player1.charAt(i) == Player1.charAt(j)){
				if(!count1.containsKey(Player1.charAt(i))){
					count1.put(Player1.charAt(i),1);
				}else{
					count1.put(Player1.charAt(i), count1.get(Player1.charAt(i))+1);
					
				}
			}
		}
	}
	
	for(int i=0; i<Player2.length(); i=i+3){
		for(int j=i+3; j<Player2.length(); j=j+3){
			if(Player2.charAt(i) == Player2.charAt(j)){
				if(!count2.containsKey(Player2.charAt(i))){
					count2.put(Player2.charAt(i),1);
				}else{
					count2.put(Player2.charAt(i), count2.get(Player2.charAt(i))+1);
					
				}
			}
		}
	}
	
	
		
	System.out.println("Counter1: " + count1);
	System.out.println("Counter2: " + count2);*
	
	if(p.fourofAKind(count1)){
		p1 = 4;
		System.out.println("Player 1: Four of a Kind");
	}
	
	if(p.fourofAKind(count2)){
		p2 = 4;
		System.out.println("Player 2: Four of a Kind");
	}

	/*if(p1 == p2){
		rank1 = p.getRank(count1);
		rank2 = p.getRank(count2);
	    
		if(rank1 > rank2)
			System.out.println("Player 1 wins this game");
		else
			System.out.println("Player 2 wins this game");
	}*/
	/*if(p.threeofAKind(count1))
		System.out.println("Three of a Kind");
	
	int pair = p.numberOfPair(count1);
	if(pair == 1){
		p1 = 1;
		System.out.println("Player 1: One pair");
	}
	
	if(pair == 2){
		System.out.println("Two pair");
	}
	
	pair = p.numberOfPair(count2);
	if(pair == 1){
		p2 = 1;
		System.out.println("Player 2: One pair");
	}
	
	if(p1 == p2){
		rank1 = p.getRank(count1);
			System.out.println("Rank 1: "+rank1 );
		rank2 = p.getRank(count2);
			System.out.println("Rank 2: "+rank2 );
	    
		if(rank1 == rank2){
			System.out.println("You need to find the highest card");
		
			for(int i=0; i<Player1.length(); i=i+3){
				if(!count1.containsKey(Player1.charAt(i)))
					tempCount1.put(Player1.charAt(i),0);
			}
			
			System.out.println("TempCount1: " +tempCount1 );
			
			for(int i=0; i<Player2.length(); i=i+3){
				if(!count2.containsKey(Player2.charAt(i)))
					tempCount2.put(Player2.charAt(i),0);
			}
			
			System.out.println("TempCount2: " +tempCount2 );
			
			rank1 = p.getRank(tempCount1);
			rank2 = p.getRank(tempCount2);
			
			System.out.println("Rank1-----------" + rank1);
			System.out.println("Rank2-----------" + rank2);
			
			if(rank1 == rank2)
				System.out.println("Split the money");
			else if(rank1 > rank2)
				System.out.println("Player 1 wins this game");
			else
				System.out.println("Player 2 wins this game");
			
		}
		else if(rank1 > rank2)
			System.out.println("Player 1 wins this game");
		else
			System.out.println("Player 2 wins this game");
		
	}
	
	p.getRank(count1);
	}*/



