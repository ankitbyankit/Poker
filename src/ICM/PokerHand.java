package ICM;

import java.util.*;
import java.io.*;

public class PokerHand {
	
	//private static String cards = "23456789TJQKA";
	
	public static void main(String args[]){
	String Player1;
	String Player2;
	String cards = "23456789TJQKA";
	String fileName = "poker-hands.txt";
	String line = null;
	
	int pwin1 = 0, pwin2 = 0, split = 0;
	
	int rank1 = 0, rank2 = 0;
	
	HashMap <Character,Integer> count1 = new HashMap <Character,Integer>();
	HashMap <Character,Integer> count2 = new HashMap <Character,Integer>();
	
	Set <Character> temp;
	
	Poker P1 = new Poker();
	Poker P2 = new Poker();
	
	
	try{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		while ((line = br.readLine()) != null) {
			Player1 = line.substring(0,15);
			Player2 = line.substring(15, 29);
			
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
				pwin2++;}
			}
		System.out.println("Player 1: "+pwin1);
		System.out.println("Player 2: "+pwin2);
		//System.out.println("Split: "+split);
	}catch(IOException e){
		System.out.println("IO Exception in File: ");e.printStackTrace();
	}catch(ArrayIndexOutOfBoundsException e){
		System.out.println("Array Out of Bound: "); e.printStackTrace();
	}
	
}		
}	
	
	
		
		



