package Columbus;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
/**
 * @author Julio Lama
 */
public class PirateShip implements Observer {
	Point coordinatesOfPirate;
	Point coordinatesOfShip;
	Random rand;		
	OceanMap oceanMap;
	

	public PirateShip(OceanMap oceanGrid) {
		this.oceanMap = oceanGrid;
		rand = new Random();
		int rows = oceanGrid.dimension;
		int cols = oceanGrid.dimension;

		
		int counter = 0;
		while(counter < 2) {
			int randRow = rand.nextInt(rows - 1);
			int randCol = rand.nextInt(cols - 1);
	
			if(oceanGrid.getMap()[randRow][randCol] >= 0 && oceanGrid.getMap()[randRow][randCol] != 1) {
				coordinatesOfPirate = new Point(randRow,randCol);
			}
			counter++;
		}
	}

	/**
	 * The pirates move when ship moves.
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
	 * Moves the pirate on the grid.
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
