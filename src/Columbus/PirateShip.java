package Columbus;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
/**
 * @author Julio Lama<br/>
 */
public class PirateShip implements Observer {
	Point coordinatesOfPirate;
	Point coordinatesOfShip;
	Random rand;		// to generate a random location in the grid.
	OceanMap oceanMap;
	
	/**
	 * Places two pirate ships on the grid as long as it is not a 1 that is an island.
	 * @param oceanGrid
	 */
	public PirateShip(OceanMap oceanGrid) {
		this.oceanMap = oceanGrid;
		rand = new Random();
		int rows = oceanGrid.dimension;
		int cols = oceanGrid.dimension;
		int randRows = rand.nextInt(rows - 1);
		int randCols = rand.nextInt(cols - 1);
		
		
		
		int counter = 0;
		while(counter < 2) {
			int randRow = rand.nextInt(rows - 1);
			int randCol = rand.nextInt(cols - 1);
			// the pirate can be placed at a 0, which is ocean but not at a 1 because that is an island
			if(oceanGrid.getMap()[randRow][randCol] >= 0 && oceanGrid.getMap()[randRow][randCol] != 1) {
				coordinatesOfPirate = new Point(randRow,randCol);	// get the location where it was placed
			}
			counter++;
		}
	}

	/**
	 * The pirates move when columbus's ship moves.
	 */
	public void update(Observable columbusShip, Object arg) {
		if(columbusShip instanceof Ship) {
			coordinatesOfShip = ((Ship) columbusShip).getShipLocation();
			movePirates();
		}	
	}
	
	
	/**
	 * 
	 * @return the coordinates of the pirate
	 */
	public Point getPirateLocation() {
		return coordinatesOfPirate;
	}
	
	
	/**
	 * Moves the pirate on the grid.<br/>
	 */
	public void movePirates() {
		if(coordinatesOfPirate.x - coordinatesOfShip.x < 0) {
			if(coordinatesOfPirate.x < 10 && oceanMap.getMap()[coordinatesOfPirate.x + 1][coordinatesOfPirate.y] != 1) {
				coordinatesOfPirate.x++;
			}
		}
		else if(coordinatesOfPirate.x > 0 && oceanMap.getMap()[coordinatesOfPirate.x - 1][coordinatesOfPirate.y] != 1) {
			coordinatesOfPirate.x--;
		}

		if(coordinatesOfPirate.y - coordinatesOfShip.y < 0 ) {
			if(coordinatesOfPirate.y < 10 && oceanMap.getMap()[coordinatesOfPirate.x][coordinatesOfPirate.y + 1] != 1) {
				coordinatesOfPirate.y++;
			}
		}
		else if(coordinatesOfPirate.y > 0 && oceanMap.getMap()[coordinatesOfPirate.x][coordinatesOfPirate.y - 1] != 1) {
			coordinatesOfPirate.y--;
		}
	}
	
}
