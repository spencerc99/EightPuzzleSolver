package day4EightPuzzle;

public interface AStarHeuristic {
	public int getCost(Board state, Board goalState);
}
