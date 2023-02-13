package lotteryd1;

/**
 *
 * @author ethanlemin
 */
public class LotteryD1 {

    public static void main(String[] args) {
        //https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        javax.swing.SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller();
            controller.getView().displaySelf();
        });
    }
    
}
