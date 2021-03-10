import java.util.Scanner;



public class Battleship {

private final int HEIGHT=5, WIDTH=5;
	
	private String[][] board;
	
	//The constructor should allocate space for the board array with the
	//dimensions HEIGTH and WIDTH.
	
	//It should then set-up the game by placing a ship and 2 bombs on the board.
	public Battleship(){
		
		board=new String[HEIGHT][WIDTH];
		
		placeShip();
		for(int i=0;i<2;i++)
			placeBomb();
		
//		for(int r=0;r<HEIGHT;r++) {// --------------------------->USED FOR TESTING.
//			for(int c=0;c<WIDTH;c++) {// --------------------------->USED FOR TESTING.
//				if(board[r][c]==null) //--------------------------->USED FOR TESTING.
//					System.out.print("& "); //----------------------------------------->USED FOR TESTING.
//				else if(board[r][c].equals("*")) //------------------------------------>USED FOR TESTING.
//					System.out.print(" "+board[r][c]+" ");// -------------------------->USED FOR TESTING.
//				else //--------------------------->USED FOR TESTING.
//					System.out.print(board[r][c]+" "); //--------------------------->USED FOR TESTING.
//			} //--------------------------->USED FOR TESTING.
//			System.out.println(); //--------------------------->USED FOR TESTING.
//		} //--------------------------->USED FOR TESTING.
//		System.out.println("\n");// --------------------------->USED FOR TESTING.
	}
	
	/*This method should randomly pick a location on the board to place a ship that takes up three linear spots in the array.
	Randomly pick the starting position of the ship on the board,and then decide if the ship is vertical or horizontal.
	50% of the time it should be horizontal.
	After deciding whether the ship is vertical or not, place the ship on the board by putting * on the board to represent the ship.*/
	private void placeShip(){
		
		//picks a random position on the board.
		int randRow=(int)(Math.random()*HEIGHT);
		int randCol=(int)(Math.random()*WIDTH);
		
		//Decides if the ship should be in a horizontal position or in a vertical position.
		boolean hori=false;
		int horizontal=(int)(Math.random()*10+1);
		if(horizontal>5)
			hori=true;
	
		//place the ship in horizontal position.
		if(hori==true) {
			if(randCol>WIDTH-3) 
				for(int i=0;i<3;i++) 
					board[randRow][randCol-i]="*";
			else {
				for(int i=0;i<3;i++)
					board[randRow][randCol+i]="*";
			}
		}
		//place the ship in vertical position.
		else {
			if(randRow>HEIGHT-3) 
				for(int i=0;i<3;i++)
					board[randRow-i][randCol]="*";
			else
				for(int i=0;i<3;i++)
					board[randRow+i][randCol]="*";
		}
		
	}
	
	//This method should randomly pick an empty location on the board and 
	//place a bomb, B, in it.
	private void placeBomb(){
		
		int randRow=(int)(Math.random()*HEIGHT);
		int randCol=(int)(Math.random()*WIDTH);
		
		while(board[randRow][randCol]!=null) {
			randRow=(int)(Math.random()*HEIGHT);
			randCol=(int)(Math.random()*WIDTH);
		}
		board[randRow][randCol]="B";
		
	}
	
	/*This method displays the board to the player as if he were playing Battleship--he see an & for any spot that he hasn't chosen yet, 
	an H for any spot on the board that represents a "hit and an "M" for any spot on the board he has chosen but did not hit a ship.*/
	public void showBoard(){
		
		for(int r=0;r<HEIGHT;r++) {
			for(int c=0;c<WIDTH;c++) {
				if(board[r][c]==null||board[r][c].equals("*")||board[r][c].equals("B")) 
					System.out.print("& ");
				else
					System.out.print(board[r][c]+" ");
			}
			System.out.println();
		}
		
	}//closing showBoard.
	
	/*This method should run the game.  The player has 10 turns to sink the ship without hitting a bomb.  
	 * If he hits a bomb or doesn't sink the complete ship, he loses.  To sink a ship, he needs 3 "HITS".
	 * Each time a player takes a turn, the board will update just like it does when you really play BattleShip.
	 * Show the board, after each turn.*/
	public void playGame(){
		
		int hits=0;
		int count =0;
		
		while (count<10) {
			
			showBoard();
			System.out.println("Choose a spot in the board in to format row-column starting from 1. For example if you want to choose row 3 column 2 you would type: \"3 2\". Be mindful of the space between the numbers. You have only 10 tryes");
			Scanner kb= new Scanner(System.in);
			int row=kb.nextInt()-1;
			int col=kb.nextInt()-1;
			
			while(row<0||row>=5||col<0||col>=5) {
				
				System.out.println("Please enter you desire spot in the format row-column. Be aware that your numbers have to be in the range 1-5 inclusive.  For example if you want to choose row 1 column 5 you would type: \"1 5\". Be mindful of the space between the numbers.\"");
				row=kb.nextInt()-1;
				col=kb.nextInt()-1;
				
			}
				
			if(board[row][col]==null)
				board[row][col]="M";
			else if(board[row][col].equals("B")) {
				System.out.println("You have hit a bomb! You Lost!");
				count=10;
			}
			else if(board[row][col].equals("*"))
				board[row][col]="H";
			
			for(int r=0;r<HEIGHT;r++)
				for(int c=0;c<WIDTH;c++)
					if(board[r][c]!=null&&board[r][c].equals("H"))
						hits++;
			
			if(hits==3) {
				showBoard();
				System.out.println("You sank the ship! You won!");
				count=10;
			}
			else
				hits=0;
			
			count++;
		}
		if(count<10)
			System.out.println("You run out of tryes, you lost!");
		
	}//closing playGame().
	
}//closing class.
