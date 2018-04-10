package Columbus;
import java.awt.Point;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.*;
/**
 * @author Julio Lama<br/>
 */
public class OceanExplorer extends Application {
	int[][] islandMap;
	Pane root;	// root of the scene graph.
	final int dimension = 10;	//10x10 grid.
	final int islandCount = 10;	// number of islands
	final int scalingFactor = 50;


	ImageView shipImageView;		

	ImageView islandImageView;

	ImageView pirateImageView, pirateImageView2;

	OceanMap oceanMap;
	Scene scene;


	Ship ship;
	PirateShip pirate, pirate2;

	/**
	 * Main.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage oceanStage) throws Exception {
		oceanMap = new OceanMap(dimension,islandCount);
		islandMap = oceanMap.getMap();

		root = new AnchorPane();
		drawMap();	// draws the map on the Pane


		ship = new Ship(oceanMap);


		// places a pirate ship on the grid.
		pirate = new PirateShip(oceanMap);
		pirate2 = new PirateShip(oceanMap);
		ship.addObserver(pirate);
		ship.addObserver(pirate2);

		// loads the images
		islandImage();
		loadShipImage();
		loadPirateImage();
		loadPirateImage2();


		scene = new Scene(root, 500, 500);
		oceanStage.setScene(scene);
		oceanStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		oceanStage.show();
		startSailing();
	}

	/**
	 * Makes the Ship and the pirates move.
	 */
	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				switch(event.getCode()) {

				case RIGHT:
					ship.goRight();

					break;
				case LEFT:
					ship.goLeft();

					break;
				case UP:
					ship.goUp();


					break;
				case DOWN:
					ship.goDown();

					break;

				default:
					break;
				}
				shipImageView.setX(ship.getShipLocation().x * scalingFactor);
				shipImageView.setY(ship.getShipLocation().y * scalingFactor);	

	
				pirateImageView.setX(pirate.getPirateLocation().x * scalingFactor);
				pirateImageView.setY(pirate.getPirateLocation().y * scalingFactor);
				pirateImageView2.setX(pirate2.getPirateLocation().x * scalingFactor);
				pirateImageView2.setY(pirate2.getPirateLocation().y * scalingFactor);
			}

		});
	}

	
	private void drawMap() {
		this.islandMap = this.oceanMap.getMap();
		for(int x = 0; x < dimension; x++) {
			for(int y = 0; y < dimension; y++) {
				Rectangle rect = new Rectangle(x * scalingFactor,y *scalingFactor, scalingFactor, scalingFactor);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
	}



	public void loadShipImage() {
		Image shipImage = new Image("File:src/Columbus/ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(ship.getShipLocation().x * scalingFactor);
		shipImageView.setY(ship.getShipLocation().y * scalingFactor);
		root.getChildren().add(shipImageView);
	}


	public void loadPirateImage(){
		Image pirateShipImage = new Image("File:src/Columbus/pirateShip.png", 50, 50, true, true);
		pirateImageView = new ImageView(pirateShipImage);
		pirateImageView.setX(pirate.getPirateLocation().x * scalingFactor);
		pirateImageView.setY(pirate.getPirateLocation().y * scalingFactor);
		root.getChildren().add(pirateImageView);
	}


	public void loadPirateImage2() {
		Image pirateShipImage = new Image("File:src/Columbus/pirateShip.png", 50,50 , true, true);
		pirateImageView2 = new ImageView(pirateShipImage);
		pirateImageView2.setX(pirate.getPirateLocation().x * scalingFactor);
		pirateImageView2.setY(pirate.getPirateLocation().y * scalingFactor);
		root.getChildren().add(pirateImageView2);
	}


	public void islandImage() {    	
		Image islandImage = new Image("File:src/Columbus/island2.jpg", 50, 50, true, true);
		List<Point> islands = oceanMap.getIslands();
		for(Point island : islands) {
			islandImageView = new ImageView(islandImage);
			islandImageView.setX(island.x * scalingFactor);
			islandImageView.setY(island.y * scalingFactor);
			root.getChildren().add(islandImageView);
		}
	}
}
	
	
	
