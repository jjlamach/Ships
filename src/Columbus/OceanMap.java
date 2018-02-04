package Columbus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;
/**
 * @author Julio Lama<br/>
 */
public class OceanMap {
	int[][] oceanMap;
	Random rand;
	int dimension;
	int islandCount;
	
	/**
	 *  Builds an ocean map with a specified dimension and place the number of islands given. <br/>
	 * @param dimension
	 * @param islands
	 */
	public OceanMap(int dimension, int islands) {
		rand = new Random();
		this.dimension = dimension;
		this.islandCount = islands;
		oceanMap = new int[dimension][dimension];
		
		int rows = oceanMap.length;
		int cols = oceanMap[0].length;
		int counter = 0;
		while(counter < islands) {
			int randRow = rand.nextInt(rows - 1);
			int randCol = rand.nextInt(cols - 1);
			if(oceanMap[randRow][randCol] >= 0) {
				oceanMap[randRow][randCol] = 1;	// place a 1 that is an island
			}
			counter++;
		}
	}
	
	/**
	 * 
	 * @return The whole ocean grid.<br/>
	 */
	public int[][] getMap() {
		return oceanMap;
	}
	
	/**
	 * Looks for all the ones in the map.<br/>
	 * This helped me place them on the grid. So that I don't create imageViews many times.
	 * @return the list of all the coordinates of the islands.<br/>
	 */
	public List<Point> getIslands() {
		List<Point> islands = new ArrayList<Point>();
		for(int i = 0; i < oceanMap.length; i++) {
			for(int j = 0; j < oceanMap.length; j++) {
				if(oceanMap[i][j] == 1) {
					islands.add(new Point(i,j));
				}
			}
		}
		return islands;
	}
}
