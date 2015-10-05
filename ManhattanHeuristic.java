package day4EightPuzzle;

public class ManhattanHeuristic implements AStarHeuristic{
	public int getCost(Board state, Board goalState)
	{
		//Implement Manhattan heuristic
		int sum = 0;
		int x = 0; int y = 0; int ax = 0; int ay = 0;
		for(int i = 1; i<=12; i++) {
			for(int r = 0; r<5; r++) {
				for(int c = 0; c<3; c++) {
					if(state.tiles[r][c]==i) {
						y=r; x=c;
					}
				}
			}
			for(int r = 0; r<5; r++) {
				for(int c = 0; c<3; c++) {
					if(goalState.tiles[r][c]==i) {
						ay=r; ax=c;
					}
				}
			}
			sum+=Math.abs(x-ax) + Math.abs(y-ay); 
		}
		return sum;
	}
}

