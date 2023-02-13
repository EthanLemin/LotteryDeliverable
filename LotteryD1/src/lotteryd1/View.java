package lotteryd1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class View extends JFrame {
    
    private static final int FRAME_WIDTH = 750;
    private static final int FRAME_HEIGHT = 250;

    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;
    
    private static final String INPUT_SPECIFIER = 
            "\nEnter six integers from 1 through 60 inclusive, separated by one or more spaces.";
    
    private JLabel label;
    private JTextField textField;
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

        setTitle("Lottery Deliverable by Ethan Lemin");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(button);
    }
    
    private void createTextField() {
        label = new JLabel(INPUT_SPECIFIER);
        final int FIELD_WIDTH = 10;
        textField = new JTextField(FIELD_WIDTH);
    }
    
    private void createButton() {
        button = new JButton("Draw Lottery"); 
        button.addActionListener(event -> lottery(textField.getText()));
    }

    private void createPanel() {
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(resultArea);
        panel.add(button);
        add(panel);
    }
    
    private void lottery(String input) {
       resultArea.setText(controller.drawLottery(input));
    }

    public void showErrorMsg(String howToFixInputError) {
        javax.swing.JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                "Invalid Input: " + textField.getText() + "\n"
                + howToFixInputError);
        textField.requestFocus();
    }

    public void displaySelf() {
        this.setVisible(true);
    }
}
