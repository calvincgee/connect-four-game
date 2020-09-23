package GamePackage;

public class CFGame{
	//state[i][j]= 0 means the i,j slot is empty
	//state[i][j]= 1 means the i,j slot has red
	//state[i][j]=-1 means the i,j slot has black
	private final int[][] state;
	private boolean isRedTurn;
	//lastPlay[0] stores the last played column
	//lastPlay[1] stores the last played row
	//lastPlay[2] stores the last played value (1 or -1)
	public int[] lastPlay = new int[3];

	{
		state = new int[7][6];
		for (int i=0; i<7; i++)
			for (int j=0; j<6; j++)
				state[i][j] = 0;
		isRedTurn = true; //red goes first
		lastPlay[0] = 0;
		lastPlay[1] = 0;
		lastPlay[2] = 1;
	}
    
	//returns a copy of state
	public int[][] getState() {
		int[][] ret_arr = new int[7][6];
		for (int i=0; i<7; i++)
		  for (int j=0; j<6; j++)
			ret_arr[i][j] = state[i][j];
		return ret_arr;
	}
	
	//returns isRedTurn
	public boolean isRedTurn() {
		return isRedTurn;
	}
	
	//prints the board state
	public void printBoard(){
		for (int i = 5; i >= 0; i--){
			for (int j = 0; j < 7; j++){
				System.out.print(state[j][i] + "\t");
			}
			System.out.println();
		}
	}
	
	//checks if a move can be played, if it can return true
	public boolean canBePlayed(int column){
		//checks if column is too small or too big
		if (column <1 || column > 7){
			return false;
		}
		//checks if column is full
		if (state[column-1][5] != 0){
			return false;
		}
		return true;
	}
	
	//play column
	//if it can be played, return true
	public boolean play(int column) {
		if (column <1 || column > 7){
			return false;
		}
		if (state[column-1][5] != 0){
			return false;
		}
		//change the lowest row in the given column to be 1 or -1
		for (int i = 0; i < 6; i++){
			if (state[column-1][i] == 0){
				if (isRedTurn){
					state[column-1][i] = 1;
					lastPlay[2] = 1;
				} else {
					state[column-1][i] = -1;
					lastPlay[2] = -1;
				}
				lastPlay[0] = column - 1;
				lastPlay[1] = i;
				isRedTurn = !isRedTurn;
				break;
			}
		}
		return true;
	}
	
	//checks if there is a move that wins the game
	//if there is, return it
	//otherwise return 0
	public int winnableMove(){
		int[][] myArr = new int[7][6];
		int[] myPlays = new int[3];
		//copy state
		for (int y=0; y<7; y++){
			for (int z=0; z<6; z++){
				myArr[y][z] = state[y][z];
			}
		}
		//checks all playable moves for one that results in a win
		for (int j = 1; j <=7; j++){
			for (int i = 0; i < 6; i++){
				if (myArr[j-1][i] == 0){
					if (isRedTurn){
						myArr[j-1][i] = 1;
						myPlays[2] = 1;
					} else {
						myArr[j-1][i] = -1;
						myPlays[2] = -1;
					}
					myPlays[0] = j - 1;
					myPlays[1] = i;
					break;
				}
			}
			//if it results in a win, return the column
			if (checkCol(myArr, myPlays) || checkRow(myArr, myPlays) || checkDiagonal(myArr, myPlays)){
				return j;
			}
			//if it does not result in a win, reset the copied state
			myArr[myPlays[0]][myPlays[1]] = 0;
		}
		return 0;
	}
	
	//checks if there is a move that can prevent an opponents win
	//if there is, return it
	//otherwise return 0
	public int preventableWin(){
		int[][] myArr = new int[7][6];
		int[] myPlays = new int[3];
		//copy state
		for (int y=0; y<7; y++){
			for (int z=0; z<6; z++){
				myArr[y][z] = state[y][z];
			}
		}
		//checks all columns for a preventable move
		for (int j = 1; j <=7; j++){
			for (int i = 0; i < 6; i++){
				if (myArr[j-1][i] == 0){
					if (!isRedTurn){
						myArr[j-1][i] = 1;
						myPlays[2] = 1;
					} else {
						myArr[j-1][i] = -1;
						myPlays[2] = -1;
					}
					myPlays[0] = j - 1;
					myPlays[1] = i;
					break;
				}
			}
			//if the opponents next move would cause them to win, return the column that could prevent it
			if (checkCol(myArr, myPlays) || checkRow(myArr, myPlays) || checkDiagonal(myArr, myPlays)){
				return j;
			}
			//if the column does not prevent a win, reset the copied state
			myArr[myPlays[0]][myPlays[1]] = 0;
		}
		return 0;
	}
	
	//checks the row for a given board, based on the last move
	//if the row has four in a row, return true
	private boolean checkRow(int[][] board, int[]moveSet){
		int fourCount = 0;
		final int lastRow = moveSet[1];
		final int lastTurn = moveSet[2];
		//go through the desired row
		for (int hor = 0; hor < 7; hor++){
			//if the space is equal to the desired value, add to fourCount
			if (board[hor][lastRow] == lastTurn){
				fourCount++;
			} else{//if the space is not equal, reset fourCount
				fourCount = 0;
			}
			if (fourCount == 4){
				return true;
			}
		}
		return false;
	}
	
	//checks the column for a given board, based on the last move
	//if the column has four in a row, return true
	private boolean checkCol(int[][] board, int[] moveSet){
		int fourCount = 0;
		final int lastCol = moveSet[0];
		final int lastRow = moveSet[1];
		final int lastTurn = moveSet[2];
		//if the move was not made higher than 3 spaces up, return false
		if (lastRow >=3){
			//check just the four spaces below the last space played, all must be of the desired type
			for (int ver = lastRow; ver >= lastRow - 3; ver--){
				if(board[lastCol][ver] == lastTurn){
					fourCount++;
				}
			}
			if (fourCount == 4){
				return true;
			}
		}
		return false;
	}
	
	//check the diagonals for a given board, based on the last move
	//if one of the diagonals has four in a row, return true
	private boolean checkDiagonal(int[][] board, int[] moveSet){
		final int lastCol = moveSet[0];
		final int lastRow = moveSet[1];
		final int lastTurn = moveSet[2];
		int fourCount = 0;
		int tempCol = lastCol;
		int tempRow = lastRow;
		
		//go to the most bottom left spot that corresponds with last move
		while(tempCol != 0 && tempRow !=0){
			tempCol--;
			tempRow--;
		}
		
		//checks the diagonal up to the right
		while(tempCol < 7 && tempRow < 6){
			//if the space is equal to the desired value, add to fourCount
			if (board[tempCol][tempRow] == lastTurn){
				fourCount++;
			} else {//if the space is not equal, reset fourCount
				fourCount = 0;
			}
			if (fourCount == 4){
				return true;
			}
			tempCol++;
			tempRow++;
		}
		tempCol = lastCol;
		tempRow = lastRow;
		fourCount = 0;
		
		//go to the most top left spot, that corresponds with the last move
		while (tempCol!=0 && tempRow != 5){
			tempCol--;
			tempRow++;
		}
		
		//checks the diagonal down to the right
		while (tempCol < 7 && tempRow >= 0){
			//if the space is equal to the desired value, add to fourCount
			if (board[tempCol][tempRow] == lastTurn){
				fourCount++;
			} else {//if the space is not equal, reset fourCount
				fourCount = 0;
			}
			if (fourCount ==4){
				return true;
			}
			tempCol++;
			tempRow--;
		}
		return false;
	}
	
	//check if the game is a draw
	//if it is, return true
	public boolean isDraw(){
		int topCount = 0;
		//checks if all the columns are full
		for (int i = 0; i < 7; i++){
			if (this.state[i][5] !=0){
				topCount++;
			}
		}
		if (topCount == 7){
			return true;
		} else {
			return false;
		}
	}
	
	//checks if the game is over
	//if it is, return true
	public boolean isGameOver() {
		if (this.checkCol(state, lastPlay) || this.checkRow(state, lastPlay) || this.checkDiagonal(state, lastPlay) || this.isDraw()){
			return true;
		} else{
			return false;
		}
		
	}
	
	//checks for a winner
	//if it is a draw return 0
	//otherwise return either 1 or -1
	public int winner() {
		if (!this.checkCol(state, lastPlay) && !this.checkRow(state, lastPlay) && !this.checkDiagonal(state, lastPlay)){
			return 0;
		} else {
			return lastPlay[2];
		}
	}
}