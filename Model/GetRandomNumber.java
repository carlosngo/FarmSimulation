import java.lang.*;

/**
 * This class contains methods which generates random numbers to be used for 
 * determining the number of rocky tiles and the number of produce.
 * @author Carlos & Johanna
 */
public class GetRandomNumber{
	private int produce;
	private int[][] tiles;
	private int row;
	private int col;
	
	public GetRandomNumber(int row, int col){
		this.row = row;
		this.col = col;
		tiles = new int[row][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				tiles[i][j] = 1;
			}
		}
	}
	
        /**
         * Returns the number of products for plants that can produce multiple 
         * products; the possible values starts from the offset to the offset 
         * added with the range.
         * @param offset the starting integer
         * @param range the integer number of possible numbers
         * @return the number of products
         */
	public int getRandomProduce(int offset, int range){
		produce = (int) (Math.random() * range + offset);
		return produce;
	}
	
        /**
         * Sets some tiles into rocky tiles; the parameter's maxNumOfRockyTiles 
         * is the maximum number of tiles to be set to rocky.
         * @param maxNumOfRockyTiles the maximum number of rocky tiles
         */
	public void getRandomTiles(int maxNumOfRockyTiles){
		int rockyTiles  = (int) (Math.random() * maxNumOfRockyTiles + 1);
		for(int i=0;i<rockyTiles;i++){ 
			int row = (int) (Math.random() * 5);
			int col = (int) (Math.random() * 10);
			tiles[row][col] = 0;
		}
	}
	
        /*
	public void displayLot(){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				System.out.print(tiles[i][j]);
			}
			System.out.print("\n");
		}
	}
	
        
	public static void main(String[] args){
		GetRandomNumber test = new GetRandomNumber(5,10);
		
		//generate number of products
		int carrot = test.getRandomProduce(1,2); //range 1 - 2
		System.out.println("range 1 - 2 --- carrot: " + carrot);
		
		int tomato = test.getRandomProduce(1,3); //range 1 - 3
		System.out.println("range 1 - 3 --- tomato: " + tomato);
		
		int potato = test.getRandomProduce(1,6); //range 1 - 6
		System.out.println("range 1 - 6 --- potato: " + potato);
		
		int mango = test.getRandomProduce(5,5); //range 5 - 10
		System.out.println("range 5 - 10 --- mango: " + mango);
		
		int apple = test.getRandomProduce(7,3); //range 7 - 10
		System.out.println("range 7 - 10 --- apple: " + apple);
		
		int banana = test.getRandomProduce(10,5); //range 10 - 15
		System.out.println("range 10 - 15 --- banana: " + banana);
		
		int orange = test.getRandomProduce(13,2); //range 13 - 15
		System.out.println("range 13 - 15 --- orange: " + orange);
		
		//generate number of rocky tiles
		System.out.println("\nbefore generate rocks:");
		test.displayLot();
		test.getRandomTiles(10);
		System.out.println("\nafter generate rocks:");
		test.displayLot();
	}
        */
}
