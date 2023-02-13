package lotteryd1;

import java.util.ArrayList;
import java.util.Random;

public class Model {
    
    private final ArrayList<Integer> lotteryDrawing;
    
    /**
     * Constructor for Model class
     * Assigns lotteryDrawing to a new ArrayList
     */
    public Model() {
        this.lotteryDrawing = new ArrayList<>();
    }
    
    /**
     * lotteryDrawing method produces and returns an ArrayList of a simulated lottery
     * Method clears lotteryDrawin array to allow for multiple entries without creating a new object
     * Method creates a random class object
     * For loop iterates 6 times:
     *      Calls random next Int between bounds 1 to 60
     *      Adds random int to lotteryDrawing
     * Returns ArrayList of Model class
     * @return lotteryDrawing containing numbers from lottery
     */
    public ArrayList<Integer> lotteryDrawing() {
        lotteryDrawing.clear();
        Random rand = new Random();
        for(int i = 0; i < 6; i++) {
            lotteryDrawing.add(rand.nextInt(1, 60));
        }
        return lotteryDrawing;
    }
    
}
