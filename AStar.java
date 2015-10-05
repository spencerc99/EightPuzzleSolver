package day4EightPuzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
public class AStar {
	 
	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;

	public AStar(Board initial, Board goal, AStarHeuristic heur)
	{
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}

	public void search()
	{
      	/* Declare and initialize Frontier and Explored data structures */ 
		LinkedList<Board> frontier = new LinkedList<Board>();
		ArrayList<Board> explored = new ArrayList<Board>();
		/* Put start node in Fringe list Frontier */
		frontier.add(initialState);
		while (!frontier.isEmpty())
		{
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/
			int min = Integer.MAX_VALUE; int f = 0; int minIndex = 0;
			for(int i = 0; i<frontier.size(); i++) {
				Board board = frontier.get(i);
				f = heuristic.getCost(board,  this.goalState);
				while(board.getParent()!=null) {
					f+=heuristic.getCost(board.getParent(), board);
					board = board.getParent();
				}
				if(f<min) {
					min = f;
					minIndex = i;
				}
			}
			Board n = frontier.get(minIndex);
			frontier.remove(n);
			explored.add(n);
			//n.print();
			//System.out.println("\n");
			if (n.equals(goalState))
			{
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
				ArrayList<Board> boards = new ArrayList<Board>();
				Board board = n;
				while(board.getParent()!=null) {
					boards.add(board);
					board = board.getParent();
				}
				int num = 1;
				for(int i = boards.size()-1; i>=0; i--) {
					System.out.println("Step " + num + ":\n" );
					boards.get(i).print();
					System.out.println("\n");
					num++;
				}
				return;
			}

			ArrayList<Board> successors = n.getSuccessors();
			for (int i = 0 ;i<successors.size(); i++)
			{
				Board n1 = successors.get(i);
				//n1.print();
				//System.out.println("\n");
				if(!(frontier.contains(n1)) && !(explored.contains(n1))) {
					int h = heuristic.getCost(n1, this.goalState);
					Board board = n; int sum = 0;
					while(board.getParent()!=null) {
						sum+=heuristic.getCost(board.getParent(), board);
						board = board.getParent();
					}
					int g = sum + heuristic.getCost(n, n1);
					f = h + g; 
					n1.setF(f);
					frontier.add(n1);
				}
				else {
					int fIndex = frontier.indexOf(n1); int eIndex = explored.indexOf(n1);
					int h = heuristic.getCost(n1, this.goalState);
					Board board = n; int sum = 0;
					while(board.getParent()!=null) {
						sum+=heuristic.getCost(board.getParent(), board);
						board = board.getParent();
					}
					int g = sum + heuristic.getCost(n, n1);
					f = g+h;
					if(frontier.contains(n1)) {
						if(f<frontier.get(fIndex).getF()) {
							frontier.set(fIndex, n1);
							n1.setParent(n);
							if(explored.contains(n1)) {
								explored.remove(n1);
								frontier.add(n1);
							}
						}
					}
					else {
						if(f<explored.get(eIndex).getF()) {
							explored.set(eIndex, n1);
							n1.setParent(n);
							if(explored.contains(n1)) {
								explored.remove(n1);
								frontier.add(n1);
							}
						}
					}
				}
				/* if n1 is not already in either Frontier or Explored
				      Compute h(n1), g(n1) = g(n)+c(n, n1), f(n1)=g(n1)+h(n1), place n1 in Frontier
				   if n1 is already in either Frontier or Explored
				      if g(n1) is lower for the newly generated n1
				          Replace existing n1 with newly generated g(n1), h(n1), set parent of n1 to n
				          if n1 is in Explored list
				              Move n1 from Explored to Frontier list*/
			}
		}
		System.out.println("No Solution");
	}

}
