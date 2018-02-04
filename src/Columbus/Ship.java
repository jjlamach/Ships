package Columbus;
import java.util.Observable;
import java.util.Random;
import java.awt.Point;
/**
 * @author Julio Lama<br/>
 * Professor: Mona Rahimi<br/>
 * SE350: Assignment#2
 */
public class Ship extends Observable {
	Point coordinates;
	Random rand;
	int[][] islandMap;
	OceanMap oceanMap;
	
	

	/**
	 * Creates a Christopher Columbus ship and places it on the grid.<br/>
	 * The ship is represented as a 2 on the grid.<br/>
	 * @param oceanGrid
	 */
	public Ship(OceanMap oceanGrid) {
		rand = new Random();
		islandMap = oceanGrid.getMap();
		int rows = islandMap.length;
		int cols = islandMap[0].length;
		int randRows = rand.nextInt(rows - 1);
		int randCols = rand.nextInt(cols - 1);
		islandMap[randRows][randCols] = 2;	// 2 is the ship.
	}
	

	/**
	 * 
	 * @return the location of the ship.<br/>
	 */
	public Point getShipLocation() {
		coordinates = new Point(0,0);
		for(int i = 0; i < islandMap.length; i++) {
			for(int j = 0; j < islandMap[i].length; j++) {
				if(islandMap[i][j] == 2) {
					coordinates = new Point(i,j);
				}
			}
		}
		return coordinates;
	}
	
	// this helped me see where the ship was moving
	private void printGrid() {
		for(int i = 0; i < islandMap.length; i++) {
			for(int j = 0; j < islandMap[i].length; j++) {
				System.out.print(islandMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Ship goes left.
	 */
	public void goLeft() {
		Point ship = getShipLocation();
		if(ship.x > 0) {	// if we are within the length of the array and not -1 by going too much to the lef (x-1) then move
			if(islandMap[ship.x - 1][ship.y] == 1) {	// if we hit an island, return
				return;
			}
			islandMap[ship.x- 1][ship.y] = 2;	// move to the left by setting that cell to a 2, which is a ship
			islandMap[ship.x][ship.y] = 0;	// set the previous cell to a 0, the ship is not there anymore.
		}
		else {
			System.out.println("Our map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();

	}
	/**
	 * Ship goes right.
	 */
	public void goRight() {
		Point ship = getShipLocation();
		// if we are within the length of the array and not too far to the right > 10
		if(ship.x + 1 < islandMap[ship.x].length) {
			if(islandMap[ship.x + 1][ship.y] == 1) {
				return;
			}
			islandMap[ship.x + 1][ship.y] = 2;// move to the right by setting that cell to a 2, which is a ship
			islandMap[ship.x][ship.y] = 0;// set the previous cell to a 0, the ship is not there anymore.
		}
		else {
			System.out.println("Our map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();

	}

	/**
	 * Ship goes up.
	 */
	public void goUp() {
		Point ship = getShipLocation();
		if(ship.y > 0) {
			if(islandMap[ship.x][ship.y - 1] == 1) {
				return;
			}
			islandMap[ship.x][ship.y - 1] = 2;  // move up by setting that cell to a 2, which is a ship
			islandMap[ship.x][ship.y] = 0; // set the cell to a 0.
		}
		else {
			System.out.println("Out map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();
	}
	
	/**
	 * Ship goes down.
	 */
	public void goDown() {
		Point ship = getShipLocation();
		if(ship.y + 1 < islandMap.length) {
			if(islandMap[ship.x][ship.y + 1] == 1) {
				return;
			}
			islandMap[ship.x][ship.y + 1] = 2;  // move down by setting that cell to a 2, which is a ship
			islandMap[ship.x][ship.y] = 0;// set the cell to a 0.
		}
		else {
			System.out.println("Our map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();

	}
	
	/**
	 * Notifies the two pirate ships that Christopher Columbus moved.
	 */
	public void columbusShipMoved() {
		setChanged();
		notifyObservers();
	}
}
