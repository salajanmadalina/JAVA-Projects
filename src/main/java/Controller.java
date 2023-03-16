import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {

    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        view.addAdunareListener(e ->{
            String polinom1 = view.getUserInput1();
            String polinom2 = view.getUserInput2();

            if(functieVerificare(polinom1, polinom2) == 1){

                Polinom pol1 = model.convertStringIntoPolinom(polinom1);
                Polinom pol2 = model.convertStringIntoPolinom(polinom2);

                Polinom result = model.adunare(pol1, pol2);
                view.getResult().setText(result.toString());
                view.getRest().setText("                                                         ");
            }
        });
        view.addScadereListener(e ->{
            String polinom1 = view.getUserInput1();
            String polinom2 = view.getUserInput2();

            if (functieVerificare(polinom1, polinom2) == 1) {
                Polinom pol1 = model.convertStringIntoPolinom(polinom1);
                Polinom pol2 = model.convertStringIntoPolinom(polinom2);

                Polinom result = model.scadere(pol1, pol2);
                view.getResult().setText(result.toString());
                view.getRest().setText("                                                         ");
            }
        });
        view.addInmultireListener(e ->{
            String polinom1 = view.getUserInput1();
            String polinom2 = view.getUserInput2();

            if(functieVerificare(polinom1, polinom2) == 1) {
                Polinom pol1 = model.convertStringIntoPolinom(polinom1);
                Polinom pol2 = model.convertStringIntoPolinom(polinom2);

                Polinom result = model.inmultire(pol1, pol2);
                view.getResult().setText(result.toString());
                view.getRest().setText("                                                         ");
            }
        });
        view.addImpartireListener(e ->{
            String polinom1 = view.getUserInput1();
            String polinom2 = view.getUserInput2();

            if(polinom2.length() == 1 && polinom2.contains("0")) {
                JOptionPane.showMessageDialog(view.getContent(), "Nu se poate imparti la 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(functieVerificare(polinom1, polinom2) == 1) {
                Polinom pol1 = model.convertStringIntoPolinom(polinom1);
                Polinom pol2 = model.convertStringIntoPolinom(polinom2);

                ArrayList<Polinom> result = model.impartire(pol1, pol2);
                view.getResult().setText(result.get(0).toString());
                view.getRest().setText(result.get(1).toString());
            }
        });
        view.addDerivareListener(e ->{
            String polinom1 = view.getUserInput1();
            int ok = 0;

            if(polinom1.isEmpty())
                JOptionPane.showMessageDialog(view.getContent(), "Trebuie introduse ambele polinoame!", "Error", JOptionPane.ERROR_MESSAGE);

            for(int i = 0; i < polinom1.length(); i ++) {
                if (polinom1.toLowerCase().charAt(i) > 'a' && polinom1.toLowerCase().charAt(i) < 'z' && polinom1.toLowerCase().charAt(i) != 'x') {
                    JOptionPane.showMessageDialog(view.getContent(), "Trebuie introduse polinoame valide!", "Error", JOptionPane.ERROR_MESSAGE);
                    ok = 1;
                    break;
                }
            }

            if(ok == 0) {
                Polinom pol1 = model.convertStringIntoPolinom(polinom1);
                Polinom result = model.derivare(pol1);
                view.getResult().setText(result.toString());
                view.getRest().setText("                                                         ");
            }
        });
        view.addIntegrareListener(e ->{
            String polinom1 = "";

            polinom1 = view.getUserInput1();

            int ok = 0;

            if (polinom1.isEmpty())
                JOptionPane.showMessageDialog(view.getContent(), "Trebuie introduse ambele polinoame!", "Error", JOptionPane.ERROR_MESSAGE);

            for (int i = 0; i < polinom1.length(); i++) {
                if (polinom1.toLowerCase().charAt(i) > 'a' && polinom1.toLowerCase().charAt(i) < 'z' && polinom1.toLowerCase().charAt(i) != 'x') {
                    JOptionPane.showMessageDialog(view.getContent(), "Trebuie introduse polinoame valide!", "Error", JOptionPane.ERROR_MESSAGE);
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                Polinom pol1 = model.convertStringIntoPolinom(polinom1);

                Polinom result = model.integrare(pol1);
                view.getResult().setText(result.toString());
                view.getRest().setText("                                                         ");
            }

        });
        view.addResetListener(e ->{
            view.getRest().setText("                                                         ");
            view.getResult().setText("                                                           ");
            view.getInput1().setText("");
            view.getInput2().setText("");
        });
    }

    public int functieVerificare(String polinom1, String polinom2){
        if(polinom1.isEmpty() || polinom2.isEmpty()) {
            JOptionPane.showMessageDialog(view.getContent(), "Trebuie introduse ambele polinoame!", "Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        for(int i = 0; i < polinom1.length(); i ++){
            if(polinom1.toLowerCase().charAt(i) > 'a' && polinom1.toLowerCase().charAt(i) < 'z' && polinom1.toLowerCase().charAt(i) != 'x') {
                JOptionPane.showMessageDialog(view.getContent(), "Trebuie introduse polinoame valide!", "Error", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }

        for(int i = 0; i < polinom2.length(); i ++){
            if(polinom2.toLowerCase().charAt(i) > 'a' && polinom2.toLowerCase().charAt(i) < 'z' && polinom2.toLowerCase().charAt(i) != 'x') {
                JOptionPane.showMessageDialog(view.getContent(), "Trebuie introduse polinoame valide!", "Error", JOptionPane.ERROR_MESSAGE);
                return 0;
            }
        }
        return 1;
    }

}
