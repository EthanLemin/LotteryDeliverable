package lotteryd3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class View extends JFrame {

    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 300;

    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;

    private static final String DIGITS_SPECIFIER
            = "\nEnter six integers from 1 through 60 inclusive, separated by one or more spaces.";
    private static final String ITERATION_SPECIFIER
            = "\nEnter an interger from 1 through 100,000 for how many times to iterate the lottery drawing.";

    private JLabel lotteryNumbersLabel;
    private JLabel iterationLabel;
    private JTextField lotteryNumbersTextField;
    private JTextField iterationTextField;
    private JButton button;
    private final JTextArea resultArea;

    private final Controller controller;

    public View(Controller controller) {
        this.controller = controller;

        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setEditable(false);
        resultArea.setText("");

        createTextField();
        createButton();
        createPanel();

        setTitle("Lottery Deliverable 3 by Ethan Lemin");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(button);
    }

    private void createTextField() {
        final int FIELD_WIDTH = 10;

        lotteryNumbersLabel = new JLabel(DIGITS_SPECIFIER);
        lotteryNumbersTextField = new JTextField(FIELD_WIDTH);

        iterationLabel = new JLabel(ITERATION_SPECIFIER);
        iterationTextField = new JTextField(FIELD_WIDTH);
    }

    private void createButton() {
        button = new JButton("Draw Lottery");
        button.addActionListener(event -> lottery(lotteryNumbersTextField.getText(), iterationTextField.getText()));
    }

    private void createPanel() {
        JPanel panel = new JPanel();
        panel.add(lotteryNumbersLabel);
        panel.add(lotteryNumbersTextField);
        panel.add(iterationLabel);
        panel.add(iterationTextField);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        panel.add(button);
        add(panel);
    }

    private void lottery(String digits, String iteration) {
        resultArea.setText(controller.drawLottery(digits, iteration));
    }

    /**
     * showErrorMsg displays an error message to the user if input is incorrect
     * @param howToFixInputError String containing a message to fix error
     * @param invalidInput String containing invalid input
     */
    public void showErrorMsg(String howToFixInputError, String invalidInput) {
        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                "Invalid Input: " + invalidInput + "\n"
                + howToFixInputError);
    }

    public void displaySelf() {
        this.setVisible(true);
    }
}
