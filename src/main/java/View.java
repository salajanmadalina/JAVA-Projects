import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View extends JFrame{
    private JTextField input1 = new JTextField(100);
    private JTextField input2 = new JTextField(100);
    private JButton adunare = new JButton("+");
    private JButton scadere = new JButton("-");
    private JButton inmultire = new JButton("*");
    private JButton impartire = new JButton("/");
    private JButton derivare = new JButton("'");
    private JButton integrare = new JButton("∫");
    private JButton reset = new JButton("Reset");
    private Model model;
    private JLabel result = new JLabel(" ");
    private JLabel rest = new JLabel(" ");
    private JPanel content = new JPanel();

    public View(Model model) {
        this.model = model;

        //Layout the components
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
        content.add(new JLabel("Introduceti primul polinom:"));
        content.add(input1);
        content.add(new JLabel("Introduceti al doilea polinom:"));
        content.add(input2);

        JPanel content1 = new JPanel();
        content1.setLayout(new GridLayout());
        content1.add(adunare);
        content1.add(scadere);
        content1.add(inmultire);
        content1.add(impartire);
        content1.add(derivare);
        content1.add(integrare);
        content1.add(reset);

        content.add(content1);
        content.add(new JLabel("Rezultatul este:"));
        content.add(result);
        content.add(new JLabel("Restul impartirii este:"));
        content.add(rest);

        this.setSize(700, 220);
        this.setContentPane(content);
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    String getUserInput1() {
        return input1.getText();
    }

    String getUserInput2() {
        return input2.getText();
    }

    public JTextField getInput1() {
        return input1;
    }

    public JTextField getInput2() {
        return input2;
    }

    public void addAdunareListener(ActionListener mal) {
        adunare.addActionListener(mal);
    }

    public void addResetListener(ActionListener mal) {
        reset.addActionListener(mal);
    }

    public void addScadereListener(ActionListener mal) {
        scadere.addActionListener(mal);
    }

    public void addInmultireListener(ActionListener mal) {
        inmultire.addActionListener(mal);
    }

    public void addImpartireListener(ActionListener mal) {
        impartire.addActionListener(mal);
    }

    public void addDerivareListener(ActionListener mal) {
        derivare.addActionListener(mal);
    }

    public void addIntegrareListener(ActionListener mal){
        integrare.addActionListener(mal);
    }

    public JLabel getResult() {
        return result;
    }

    public JPanel getContent() {
        return content;
    }

    public JLabel getRest() {
        return rest;
    }

}
