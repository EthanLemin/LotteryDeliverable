package lotteryd3;

public class LotteryD3 {

    public static void main(String[] args) {
        //https://docs.oracle.com/javase/tutorial/uiswing/concurrency/initial.html
        javax.swing.SwingUtilities.invokeLater(() -> {
            Controller controller = new Controller();
            controller.getView().displaySelf();
        });

    }

}
