import java.util.*;

public class Battleship {

	public static void main(String[] args) {
		
		String [][] ocean = new String[10][10];
		int [] numShips = new int [2];
		numShips[0] = 5; //number of user ships
		numShips[1] = 5; //number of computer ships

		initializeOcean(ocean);
		drawOcean(ocean);
		getUserInput(ocean);
		getComputerInput(ocean);
		drawOcean(ocean);
		
		while (numShips[0] > 0 && numShips[1] > 0) {
			getUserGuess(ocean, numShips);
			getComputerGuess(ocean, numShips);
			drawOcean(ocean);
		}
		
		if (numShips[0] > 0 && numShips[1] == 0) System.out.println("Hooray!!! You Win!");
		else if (numShips[0] == 0 && numShips[1] > 0) System.out.println("Sorry, you lost, the Computer Won. Try again!");
		else System.out.println("OOOPS! Something went wrong");
	}		
	
	private static void initializeOcean(String[][] ocean) {
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++){
				ocean[i][j]=" ";
			}
		}
		System.out.println("Welcome to the Battleships Game");
		System.out.println("\n");
		System.out.println("Right now the sea is empty");
		System.out.println("\n");
	} //end of initializeOcean method		
	
	private static void drawOcean(String[][] ocean) {
		System.out.println("  0123456789");	
		for (int i=0; i<10; i++)
			{
			System.out.print(i+"|");	
			for (int j=0; j<10; j++){
				if (ocean[i][j] ==" ") System.out.print(" ");	
				if (ocean[i][j] == "1") System.out.print("@");
				if (ocean[i][j] == "x") System.out.print("X");
				if (ocean[i][j] == "*") System.out.print("*");
				if (ocean[i][j] == "2") System.out.print(" ");
				}
				System.out.print("|"+i+"\n");
			}
		System.out.println("  0123456789");
		} //end of drawOcean Method

	private static void getUserInput(String[][] ocean){
		int shipsInput = 1;
		int x = -1;
		int y = -1;
		Scanner input = new Scanner(System.in);
	
		while ( shipsInput<6){	
			boolean occupied = false;
			while (!occupied && shipsInput<6) {
				x = -1;
				y = -1;
				while ((x<0) || (x>9))  {
					System.out.print("Enter X coordinate: ");
					x = input.nextInt();
					if (x<0) System.out.println("\n coordinate must be greater than 0!");
					if (x>9) System.out.println("\n coordinate must be less than 10!");
				}			
				while (y<0 || y>9) {
					System.out.print("Enter Y coordinate: ");
					y = input.nextInt();
					if (y<0) System.out.println("\n coordinate must be greater than 0!");
					if (y>9) System.out.println("\n coordinate must be less than 10!");
				}
				if (ocean[x][y] !=" ") {
					System.out.println("Position already occupied by a ship, enter new coordinates!");
					occupied = true;
				}
				else {
					shipsInput++;
					ocean[x][y] = "1";
					occupied = false;
				}
			}
		}
	} // end of getUserInput method
	
	private static void getComputerInput(String[][] ocean){
		int shipsInput = 1;
		boolean occupied = false;
		Random random = new Random();
		
		System.out.println("\n\nComputer is deploying Ships");
		while (shipsInput<6) {
			while (!occupied && shipsInput<6) {
				int x = random.nextInt(10);
				int y = random.nextInt(10);
				if (ocean[x][y] !=" ") {
					occupied = true;
					}
				else {
					shipsInput++;
					ocean[x][y] = "2";
					System.out.println("Ship deployed");
					occupied = false;
				}
			}
		}
		System.out.println("\n");
	} // end of getComputerInput method

	private static void getUserGuess(String[][] ocean, int [] numShips){
		int x = -1;
		int y = -1;
		Scanner input = new Scanner(System.in);
	
		while ((x<0) || (x>9))  {
			System.out.print("Enter X coordinate guess: ");
			x = input.nextInt();
			if (x<0) System.out.println("\n coordinate must be greater than 0!");
			if (x>9) System.out.println("\n coordinate must be less than 10!");
		}			
		while (y<0 || y>9) {
			System.out.print("Enter Y coordinate guess: ");
			y = input.nextInt();
			if (y<0) System.out.println("\n coordinate must be greater than 0!");
			if (y>9) System.out.println("\n coordinate must be less than 10!");
		}
		if (ocean[x][y] == "2") {
			System.out.println("BOOM, you sunk the computers Battleship!");
			ocean[x][y] = "*";
			numShips[1]--;
		}
		else if (ocean[x][y] == "1") {
			System.out.println("BOOM, you sunk one of your own Battleships!");
			ocean[x][y] = "*";
			numShips[0]--;
		}
		else {
			ocean[x][y] = "x";
			System.out.println("Miss, try again");
		}
	} // end of getUserGuess method
	
	private static void getComputerGuess(String[][] ocean, int [] numShips){
		Random random = new Random();
		
		System.out.println("\n\nComputer is guessing!");
				int x = random.nextInt(10);
				int y = random.nextInt(10);
				if (ocean[x][y] == "2") {
					System.out.println("BOOM, Computer Sunk its own Battleship!");
					ocean[x][y] = "*";
					numShips[1]--;
				}
				else if (ocean[x][y] == "1") {
					System.out.println("BOOM, Users Battleship Sunk!");
					ocean[x][y] = "*";
					numShips[0]--;
				}
				else {
					ocean[x][y] = "x";
;					System.out.println("Miss, try again");
				}
		System.out.println("\n");
	} // end of getComputerguess method
	
} //End of Battleship Class