package lotteryd3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Controller {

    private final Model model;
    private final View view;

    /**
     * Constructor for Controller class Assigns model with LotteryModel object
     * Assigns view with LotteryView object
     */
    public Controller() {
        model = new Model();
        view = new View(this);
    }

    /**
     * drawLottery() method mediates between view and model classes 
     * 
     * Boolean validLottery stores validateLotteryNumbers output, supplies method with a string
     * Boolean validIteration stores validateIterations output, supplies method with a string
     * 
     * If not validLottery or not validIteration:
     *      returns empty string (invalid input)
     *  
     * HashMap<Integer, HashSet> lotteryDrawing  - Stores an HashSet (LotteryDrawing from Model) and assigns it a key (number of lottery drawings)
     * ArrayList<Integer> userLotteryNumbers - Stores user input for their lottery numbers
     * HashMap<Integer, ArrayList<Integer>> lotteryMatches - Stores a map of lottery matches
     * HashMap<Integer, Integer> numberOfLotteryMatches - Stores of map of number of lottery matches per value
     * 
     * Creates an array stringParts which splits the lotteryNumbers by one or more spaces
     * For-each loop iterates through array:
     *      Parses string into an integer; temporarily stores the integer
     *      Adds temporary integer to userLotterNumbers ArrayList
     *  
     * Reassigns the stringParts which splits the iterations by one or more spaces
     * Stores first part of stringParts to 
     *  
     * For loop iterates numberOfIterations:
     *      Creates a new HashSet temp and calls lotteryDrawing from model class
     *      Adds temp HashSet to HashMap lotteryDrawings with an integer representing the number of lottery drawings
     * 
     * For loop iterates through each value in lotteryDrawing:
     *      Stores an integer for the key
     *      Stores a HashSet for the value from key
     *      Creates a new ArrayList matchesForLottery for lottery matches
     *      For loop which iterates through each user number:
     *          If lotteryDrawing contains value of userLotteryNumbers:
     *               Adds userNumber to matchesForLottery (lottery match)
     * Adds ArrayList matchesForLottery to HashMap lotteryMatches
     *   
     * For loop iterates through each value of lotteryMatches:
     *      Creates an ArrayList to store the ArrayList from that lotteryMatch entry
     *      Stores an integer arraySize for the size of the ArrayList 
     *      Adds arraySize to numberOfLotteryMatches HashMap:
     *          Key -> arraySize (Ex: 1 match)
     *          Value -> Number of Matches Associated with Key (Ex: For 1 match, there are 10 cases)
     *              Uses .getOrDefault to obtain value for key. If key is empty, assign to 0. Increment value by 1.
     *  
     * Returns call of drawLotteryOutput(): 
     *      drawLotteryOutput returns a string for output
     *
     * @param lotteryNumbers String containing numbers entered by user from view
     * class
     * @param iterations String containing number of iterations
     * @return String output
     */
    public String drawLottery(String lotteryNumbers, String iterations) {
        boolean validLottery = validateLotteryNumbers(lotteryNumbers);
        boolean validIteration = validateIterations(iterations);

        if (!validLottery || !validIteration) {
            return "";
        }

        HashMap<Integer, HashSet> lotteryDrawings = new HashMap<>();
        ArrayList<Integer> userLotteryNumbers = new ArrayList<>();
        HashMap<Integer, ArrayList<Integer>> lotteryMatches = new HashMap<>();
        HashMap<Integer, Integer> numberOfLotteryMatches = new HashMap<>();

        String[] stringParts = lotteryNumbers.split("\\s+");
        for (String str : stringParts) {
            int temp = Integer.parseInt(str);
            userLotteryNumbers.add(temp);
        }

        stringParts = iterations.split("\\s+");
        final int numberOfIterations = Integer.parseInt(stringParts[0]);

        for (int i = 1; i <= numberOfIterations; i++) {
            HashSet<Integer> temp = new HashSet<>(model.lotteryDrawing());
            lotteryDrawings.put(i, temp);
        }

        for (Map.Entry<Integer, HashSet> entry : lotteryDrawings.entrySet()) {
            int drawingNumber = entry.getKey();
            HashSet lotteryDrawing = entry.getValue();

            ArrayList<Integer> matchesForLottery = new ArrayList<>();

            for (int userNumber : userLotteryNumbers) {
                if (lotteryDrawing.contains(userNumber)) {
                    matchesForLottery.add(userNumber);
                }
            }
            lotteryMatches.put(drawingNumber, matchesForLottery);
        }

        for (Map.Entry<Integer, ArrayList<Integer>> entry : lotteryMatches.entrySet()) {
            ArrayList<Integer> arrayList = entry.getValue();
            int arraySize = arrayList.size();
            numberOfLotteryMatches.put(arraySize, numberOfLotteryMatches.getOrDefault(arraySize, 0) + 1);
        }

        return drawLotteryOutput(userLotteryNumbers, numberOfLotteryMatches);
    }

    /**
     * validateLotteryNumbers() validates the user lottery input 
     * 
     * Method checks if input contains leading or trailing spaces:
     *      If lotteryNumbers.trim() equals lotteryNumbers:
     *          Method splits original string into parts and adds to a string array 
     *          Method uses a for-each loop to iterate through each element of the array: 
     *              Increments counter variable by 1 
     *              If stringParts[i] does not contain a digit:
     *                  nonDigitFlag (Not an integer; invalid input)
     *              Else:
     *                  Parses string into an integer, stores value temporarily
     *                  If integer does not range from 1 - 60:
     *                      digitInclusiveFlag (Not within range; invalid input)
     *      Else:
     *          Calls showErrorMsg method; Returns false (invalid input)
     * 
     * Method checks flags and counters:
     *      If counter does not equal 6:
     *          Calls showErrorMsg method; Returns false (invalid input)
     *      If nonDigitFlag equals true:
     *          Calls showErrorMsg method; Returns false (invalid input)
     *      If digitInclusiveFlag equal true:
     *          Calls showErrorMsg method; Returns false (invalid input)
     *    
     * Method returns true if no flags are raised (valid input)
     *
     * @param lotteryNumbers String containing lottery numbers input
     * @return True if no flags are raised, false is a flag is raised
     */
    public boolean validateLotteryNumbers(String lotteryNumbers) {
        boolean nonDigitFlag = false;
        boolean digitInclusiveFlag = false;
        int counter = 0;
        
        if(lotteryNumbers.trim().equals(lotteryNumbers)) {
            String[] stringParts = lotteryNumbers.split("\\s+");
            for (String str : stringParts) {
                counter++;
                if (!str.matches("\\d+")) {
                    nonDigitFlag = true;
                } else {
                    int temp = Integer.parseInt(str);
                    if (temp < 1 || temp > 60) {
                        digitInclusiveFlag = true;
                    }
                }
            }
        } else {
            view.showErrorMsg("Lottery numbers cannot contain leading or trailing whitespaces.", lotteryNumbers);
            return false;
        }
        

        if (counter != 6) {
            view.showErrorMsg("Lottery numbers must contain only 6 integers.", lotteryNumbers);
            return false;
        }

        if (nonDigitFlag) {
            view.showErrorMsg("Lottery numbers must contain only integers or spaces.", lotteryNumbers);
            return false;
        }

        if (digitInclusiveFlag) {
            view.showErrorMsg("Lottery numbers must be or between 1 and 60.", lotteryNumbers);
            return false;
        }

        return true;
    }

    /**
     * validateIterations() validates the user iteration input. 
     * 
     * Method checks if input contains leading or trailing spaces:
     *      If iterations.trim() equals iterations:
     *          Method splits original string into parts and adds to a string array 
     *          Method uses a for-each loop to iterate through each element of the array: 
     *              Increments counter variable by 1 
     *              If stringParts[i] does not contain a digit:
     *                  nonDigitFlag (Not an integer; invalid input) 
     *              Else: 
     *                  Parses string into an integer, stores value temporarily 
     *                  If integer does not range from 1 - 100000:
     *                      digitInclusiveFlag (Not within range; invalid input) 
     *      Else:
     *          Calls showErrorMsg method; Returns false (invalid input)
     * 
     * Method checks flags and counters: 
     *      If counter does not equal 1: 
     *          Calls showErrorMsg method; Returns false (invalid input) 
     *      If nonDigitFlag equals true: 
     *          Calls showErrorMsg method; Returns false (invalid input) 
     *      If digitInclusiveFlag equal true: 
     *          Calls showErrorMsg method; Returns false (invalid input)
     * 
     * Method returns true if no flags are raised (valid input)
     *
     * @param iterations String containing iteration number input
     * @return True if no flags are raised, false is a flag is raised
     */
    public boolean validateIterations(String iterations) {
        boolean nonDigitFlag = false;
        boolean digitInclusiveFlag = false;
        int counter = 0;
        
        if(iterations.trim().equals(iterations)) {
            String[] stringParts = iterations.split("\\s+");
            for (String str : stringParts) {
                counter++;
                if (!str.matches("\\d+")) {
                    nonDigitFlag = true;
                } else {
                    int temp = Integer.parseInt(str);
                    if (temp < 1 || temp > 100000) {
                        digitInclusiveFlag = true;
                    }
                }
            }
        } else {
            view.showErrorMsg("Iteration number cannot contain leading or trailing whitespaces.", iterations);
            return false;
        }

        if (counter != 1) {
            view.showErrorMsg("Number of iterations can only be one digit.", iterations);
            return false;
        }

        if (nonDigitFlag) {
            view.showErrorMsg("Number of iterations must contain only an integer.", iterations);
            return false;
        }

        if (digitInclusiveFlag) {
            view.showErrorMsg("Number of iterations must be or between 1 and 100,000.", iterations);
            return false;
        }

        return true;
    }

    /**
     * drawLotteryOutput produces a string output for the drawLottery method
     *
     * @param userLotteryNumbers ArrayList of integers containing numbers
     * inputted by user
     * @param lotteryDrawing HashMap of integers and hashsets containing numbers
     * from lottery drawing
     * @param lotteryMatches HashMap of integers and arraylist of integers
     * containing numbers that match
     * @return String output
     */
    private String drawLotteryOutput(ArrayList<Integer> userLotteryNumbers,
            HashMap<Integer, Integer> numberOfLotteryMatches) {
        String output = "You entered ";
        for (int num : userLotteryNumbers) {
            String temp = String.valueOf(num);
            output += temp + " ";
        }

        for (int i = 0; i <= 6; i++) {
            int values = numberOfLotteryMatches.getOrDefault(i, 0);
            output += "\n" + values + " drawings matched " + i + " of your numbers.";

        }
        return output;
    }

    /**
     * getView method returns view object
     *
     * @return View object
     */
    public View getView() {
        return view;
    }

}
