package lotteryd3;

import java.util.HashSet;
import java.util.Random;

public class Model {

    private final Random rand = new Random();
    private final HashSet<Integer> lotteryDrawing;

    /**
     * Constructor for Model class Assigns lotteryDrawing to a new HashSet
     */
    public Model() {
        this.lotteryDrawing = new HashSet<>();
    }

    /**
     * lotteryDrawing method produces and returns an hashSet of a simulated lottery 
     * Method clears lotteryDrawing set to allow for multiple entries without creating a new object 
     * While loop iterates until lotterDrawing size is 6: 
     *      Calls random next Int between bounds 1 to 60 
     * Adds random int to lotteryDrawing 
     * Returns HashSet of Model class
     * @return lotteryDrawing containing numbers from lottery
     */
    public HashSet<Integer> lotteryDrawing() {
        lotteryDrawing.clear();
        while (lotteryDrawing.size() < 6) {
            lotteryDrawing.add(rand.nextInt(1, 60));
        }
        return lotteryDrawing;
    }

}
