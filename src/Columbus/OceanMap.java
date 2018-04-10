package Columbus;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Point;
/**
 * @author Julio Lama
 */
public class OceanMap {
	int[][] oceanMap;
	Random rand;
	int dimension;
	int islandCount;
	

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
	

	public int[][] getMap() {
		return oceanMap;
	}
	

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
