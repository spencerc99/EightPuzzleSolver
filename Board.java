package day4EightPuzzle;

import java.util.ArrayList;
public class Board {
	public static int rows=5;
	public static int columns=3;
	private Board parent = null; /* only initial board's parent is null */
	public int[][] tiles;
	private int f; 

	public Board(int[][] cells)                 //Populate states
	{
	  tiles = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
			{
				tiles[i][j] = cells[i][j];
			}
		f= 0;
	}
	public boolean equals(Object x)         //Can be used for repeated state checking
	{
		Board another = (Board)x;
		if (columns != another.columns || rows != another.rows) return false;
		for (int i = 0; i<rows; i++)
			for (int j = 0; j<columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}
	public ArrayList<Board> getSuccessors()     //Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	{
		/* TODO */
		ArrayList<Board> successors = new ArrayList<Board>();
		int x = 0; int y = 0;
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<columns; j++)
				if(this.tiles[i][j]==0) {
					y=i; x=j;
					break;
				}
		}
		int[][] board = new int[rows][columns];
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<columns; j++) {
				board[i][j] = tiles[i][j];
			}
		}
		
		//right
		if(x!=2 && !(x==0 && y==1) && !(x==0 && y==3)) {
			board[y][x] = board[y][x+1];
			board[y][x+1] = 0;
			add(new Board(board), successors);
			for(int i = 0; i<rows; i++) {
				for(int j = 0; j<columns; j++) {
					board[i][j] = this.tiles[i][j];
				}
			}
		}
		//down
		if(y!=4 && !(x==1 && y==0) && !(x==1 && y==2)) {
			board[y][x] = board[y+1][x];
			board[y+1][x] = 0;
			add(new Board(board), successors);
			for(int i = 0; i<rows; i++) {
				for(int j = 0; j<columns; j++) {
					board[i][j] = this.tiles[i][j];
				}
			}
		}
		//left
		for(int i = 0; i<rows; i++) {
			for(int j = 0; j<columns; j++) {
				board[i][j] = this.tiles[i][j];
			}
		}
		if(x!=0 && !(x==2 && y==1) && !(x==2 && y==3)) {
			board[y][x] = board[y][x-1];
			board[y][x-1] = 0;
			add(new Board(board), successors);
			for(int i = 0; i<rows; i++) {
				for(int j = 0; j<columns; j++) {
					board[i][j] = this.tiles[i][j];
				}
			}
		}
		//up
		if(y!=0 && !(x==1 && y==2) && !(x==1 && y==4)) {
			board[y][x] = board[y-1][x];
			board[y-1][x] = 0;
			add(new Board(board), successors);
			for(int i = 0; i<rows; i++) {
				for(int j = 0; j<columns; j++) {
					board[i][j] = this.tiles[i][j];
				}
			}
		}
		return successors;
	}
	
	public void add(Board newBoard, ArrayList<Board> successors) {
		newBoard.setParent(this);
		successors.add(newBoard);
	}
	
	
	public void print()
	{
		for (int i = 0; i<rows; i++)
		{
			for (int j = 0 ;j<columns; j++)
			{
				if (j > 0) System.out.print("\t");
				System.out.print(tiles[i][j]);
			}
			System.out.println();
		}
	}
	
	public void setParent(Board parentBoard)
	{
		parent = parentBoard;
	}
	
	public Board getParent()
	{
		return parent;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public int getF() {
		return this.f;
	}
	
}
