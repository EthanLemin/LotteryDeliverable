package lotteryd1;

import java.util.ArrayList;

public class Controller {
    
    private final Model model;
    private final View view;

    /**
     * Constructor for Controller class
     * Assigns model with LotteryModel object
     * Assigns view with LotteryView object
     */
    public Controller() {
        model = new Model();
        view = new View(this);
    }
    
    /**
     * drawLottery method mediates between view and model classes
     * If statement calls validateInput and supplies the method with String numbers
     * validateInput Method returns a boolean:
     *      If true (Valid Input):
     *          Code continues
     *      If false (Invalid Input):
     *          drawLottery returns nothing
     * ArrayList lotteryDrawing - Stores numbers drawn by lottery
     * ArrayList userLotteryNumbers - Stores numbers supplied by user
     * ArrayList lotteryMatches - Stores numbers that match lottery (userNum == lotteryNum)
     * Creates an array stringParts which splits each integer containing one or more spaces
     * For-each loop iterates through array:
     *      Parses string into an integer; temporarily stores the integer
     *      Adds temporary integer to an ArrayList userLotterNumbers
     * For loop iterates through each value in lotteryDrawing
     * If userLotteryNumbers contains a value from lotteryDrawing:
     *      Adds number to lotteryMatches ArrayList (lottery match)
     * Calls drawLotteryOutput:
     *      Returns string from method
     * @param numbers String containing numbers entered by user from view class
     * @return String output
     */
    public String drawLottery(String numbers) {
        if(!validateInput(numbers)) {
            return "";
        }

        ArrayList<Integer> lotteryDrawing = model.lotteryDrawing();
        ArrayList<Integer> userLotteryNumbers = new ArrayList<>();
        ArrayList<Integer> lotteryMatches = new ArrayList<>();
        
        String[] stringParts = numbers.split("\\s+");
        for(String str : stringParts) {
            int temp = Integer.parseInt(str);
           userLotteryNumbers.add(temp);
        }

        for(int i = 0; i < lotteryDrawing.size(); i++) {
            if(userLotteryNumbers.contains(lotteryDrawing.get(i))) {
                lotteryMatches.add(lotteryDrawing.get(i));
            }
        }

        return drawLotteryOutput(userLotteryNumbers, lotteryDrawing, lotteryMatches);
    }
    
    /**
     * validateInput validates the user input
     * Method splits original string into parts and adds to a string array
     * Method uses a for-each loop to iterate through each element of the array:
     *      Increments counter variable by 1
     *      if sstringParts[i] does not contain a digit:
     *          nonDigitFlag (Not an integer; invalid input)
     *      else:
     *          Parses string into an integer, stores value temporarily
     *          If integer does not range from 1 - 60:
     *              digitInclusiveFlag (Not within range; invalid input)
     * Method checks flags and counters:
     *      If counter does not equal 6:
     *          Calls showErrorMsg method; Returns false (invalid input)
     *      If nonDigitFlag equals true:
     *          Calls showErrorMsg method; Returns false (invalid input)
     *      If digitInclusiveFlag equal true:
     *          Calls showErrorMsg method; Returns false (invalid input)
     * Method returns true if no flags are raised (valid input)
     * @param numbers
     * @return True if no flags are raised, false is a flag is raised
     */
    public boolean validateInput(String numbers) {
        boolean nonDigitFlag = false;
        boolean digitInclusiveFlag = false;
        int counter = 0;
        String[] stringParts = numbers.split("\\s+");
        for(String str : stringParts) {
            counter++;
            if(!str.matches("\\d+")) {
                nonDigitFlag = true;
            } else {
                int temp = Integer.parseInt(str);
                if(temp < 0 || temp > 61) {
                    digitInclusiveFlag = true;
                }
            }
        }
        
        if(counter != 6) {
            view.showErrorMsg("Input must contain only 6 integers.");
            return false;
        }
        
        if(nonDigitFlag) {
            view.showErrorMsg("Input must contain only integers or spaces.");
            return false;
        }
        
        if(digitInclusiveFlag) {
            view.showErrorMsg("Digits must be or between 1 and 60.");
            return false;
        }
        
        return true;
    }
    
    /**
     * drawLotteryOutput produces a string output for the drawLottery method
     * @param userLotteryNumbers ArrayList of integers containing numbers inputted by user
     * @param lotteryDrawing ArrayList of integers containing numbers from lottery drawing
     * @param lotteryMatches ArrayList of integers containing numbers that match
     * @return String output
     */
    private String drawLotteryOutput(ArrayList<Integer> userLotteryNumbers, 
            ArrayList<Integer> lotteryDrawing, ArrayList<Integer> lotteryMatches) {
        String output = "You entered ";
        for(int num : userLotteryNumbers) {
            String temp = String.valueOf(num);
            output += temp + " ";
        }
        output += "\nThe numbers from the lottery drawing are " + lotteryDrawing;
        output += "\nThe matching numbers are " + lotteryMatches;
        return output;
    }
    
    /**
     * getView method returns view object
     * @return View object
     */
    public View getView() {
        return view;
    }
    
}
