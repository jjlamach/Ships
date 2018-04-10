package Columbus;
import java.util.Observable;
import java.util.Random;
import java.awt.Point;
/**
 * @author Julio Lama
 */
public class Ship extends Observable {
	Point coordinates;
	Random rand;
	int[][] islandMap;
	OceanMap oceanMap;
	
	

	public Ship(OceanMap oceanGrid) {
		rand = new Random();
		islandMap = oceanGrid.getMap();
		int rows = islandMap.length;
		int cols = islandMap[0].length;
		int randRows = rand.nextInt(rows - 1);
		int randCols = rand.nextInt(cols - 1);
		islandMap[randRows][randCols] = 2;	// 2 is the ship.
	}
	

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
	

	public void goLeft() {
		Point ship = getShipLocation();
		if(ship.x > 0) {	
			if(islandMap[ship.x - 1][ship.y] == 1) {
				return;
			}
			islandMap[ship.x- 1][ship.y] = 2;	
			islandMap[ship.x][ship.y] = 0;	
		}
		else {
			System.out.println("Our map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();

	}

	
	public void goRight() {
		Point ship = getShipLocation();
		
		if(ship.x + 1 < islandMap[ship.x].length) {
			if(islandMap[ship.x + 1][ship.y] == 1) {
				return;
			}
			islandMap[ship.x + 1][ship.y] = 2;
			islandMap[ship.x][ship.y] = 0;
		}
		else {
			System.out.println("Our map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();

	}


	public void goUp() {
		Point ship = getShipLocation();
		if(ship.y > 0) {
			if(islandMap[ship.x][ship.y - 1] == 1) {
				return;
			}
			islandMap[ship.x][ship.y - 1] = 2;  
			islandMap[ship.x][ship.y] = 0; 
		}
		else {
			System.out.println("Out map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();
	}
	

	public void goDown() {
		Point ship = getShipLocation();
		if(ship.y + 1 < islandMap.length) {
			if(islandMap[ship.x][ship.y + 1] == 1) {
				return;
			}
			islandMap[ship.x][ship.y + 1] = 2;  
			islandMap[ship.x][ship.y] = 0;
		}
		else {
			System.out.println("Our map doesn't go that far.");
		}
//		printGrid();
		columbusShipMoved();

	}
	
	/**
	 * Notifies the two pirate ships that Ship moved.
	 */
	public void columbusShipMoved() {
		setChanged();
		notifyObservers();
	}
}
