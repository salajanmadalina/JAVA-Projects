import java.util.*;

public class Model {

    public Model() {
        super();
    }

    public static Polinom convertStringIntoPolinom(String str) {

        Polinom result = new Polinom();
        Monom m;

        str = str.replace("-", "+-");
        str = str.replace(" ", "").toLowerCase();
        str = str.replace("-x", "-1x");
        str = str.replace("+x", "+1x");

        String[] str2 = str.split("\\+");

        for (String str1 : str2) {
            if (str1.isEmpty())
                continue;
            if (str1.contains("x")) {
                String[] a = str1.split("x");

                if (a.length == 0)
                    result.add(new Monom(1, 1));

                if (a.length == 1)
                    result.add(new Monom((a[0].isEmpty()) ? 1 : Double.parseDouble(a[0]), 1));

                if (a.length == 2)
                    result.add(new Monom((a[0].isEmpty()) ? 1 : Double.parseDouble(a[0]), Integer.parseInt(a[1].substring(1))));
            } else
                result.add(new Monom(Double.parseDouble(str1), 0));
        }
        return result;
    }

    public static Polinom adunare(Polinom polinom1, Polinom polinom2) {
        Polinom result = new Polinom();
        int ok;
        ArrayList<Integer> puteri_existente = new ArrayList<Integer>();
        for (Monom m1 : polinom1.getMonoame()) {
            ok = 0;
            for (Monom m2 : polinom2.getMonoame()) {
                if (m1.getIndex() == m2.getIndex()) {
                    result.add(new Monom((m1.getCoeficient() + m2.getCoeficient()), m1.getIndex()));
                    ok = 1;
                    puteri_existente.add(m1.getIndex());
                }
            }
            if (ok == 0) {
                result.add(new Monom(m1.getCoeficient(), m1.getIndex()));
                puteri_existente.add(m1.getIndex());
            }
        }
        for (Monom m1 : polinom2.getMonoame()) {
            ok = 0;
            for (Integer i : puteri_existente) {
                if (m1.getIndex() == i)
                    ok = 1;
            }
            if (ok == 0)
                result.add(new Monom(m1.getCoeficient(), m1.getIndex()));
        }
        return result;
    }

    public static Polinom scadere(Polinom polinom1, Polinom polinom2) {
        Polinom result = new Polinom();
        int ok;
        ArrayList<Integer> puteri_existente = new ArrayList<Integer>();
        for (Monom m1 : polinom1.getMonoame()) {
            ok = 0;
            for (Monom m2 : polinom2.getMonoame()) {
                if (m1.getIndex() == m2.getIndex()) {
                    result.add(new Monom((m1.getCoeficient() - m2.getCoeficient()), m1.getIndex()));
                    ok = 1;
                    puteri_existente.add(m1.getIndex());
                }
            }
            if (ok == 0) {
                result.add(new Monom(m1.getCoeficient(), m1.getIndex()));
                puteri_existente.add(m1.getIndex());
            }
        }
        for (Monom m1 : polinom2.getMonoame()) {
            ok = 0;
            for (Integer i : puteri_existente) {
                if (m1.getIndex() == i)
                    ok = 1;
            }
            if (ok == 0)
                result.add(new Monom(-m1.getCoeficient(), m1.getIndex()));
        }
        result.removeZeros();
        return result;
    }

    public static Polinom inmultire(Polinom polinom1, Polinom polinom2) {
        Polinom result = new Polinom();

        for (Monom m1 : polinom1.getMonoame()) {
            for (Monom m2 : polinom2.getMonoame()) {
                if (result.searchByIndex(m1.getIndex() + m2.getIndex(), m1.getCoeficient() * m2.getCoeficient()) == 0) {
                    result.add(new Monom(m1.getCoeficient() * m2.getCoeficient(), m1.getIndex() + m2.getIndex()));
                }
            }
        }
        return result;
    }

    public static ArrayList<Polinom> impartire(Polinom polinom1, Polinom polinom2) {
        ArrayList<Polinom> result = new ArrayList<Polinom>();
        Polinom c = new Polinom();
        
        while (polinom1.rang() >= polinom2.rang()) {

            Collections.sort(polinom1.getMonoame());
            Collections.sort(polinom2.getMonoame());

            Monom m1 = new Monom(polinom1.getMonoame().getFirst().getCoeficient(),polinom1.getMonoame().getFirst().getIndex());
            Monom m2 = new Monom(polinom2.getMonoame().getFirst().getCoeficient(),polinom2.getMonoame().getFirst().getIndex());
            Monom mc = m1.divide(m2);

            c.adunareCuMonom(mc);

            polinom1 = scadere(polinom1, Polinom.inmultireCuMonom(polinom2, mc));

            if(polinom1.getMonoame().isEmpty())
                break;
        }

        result.add(c);
        result.add(polinom1);

        return result;
    }

    public static Polinom derivare(Polinom polinom1) {

        Polinom result = new Polinom();
        Monom m;

        for (Monom m1 : polinom1.getMonoame()) {
            if (m1.getIndex() - 1 >= 0) {
                m = new Monom((m1.getCoeficient() * m1.getIndex()), m1.getIndex() - 1);
                result.add(m);
            }
        }

        return result;
    }

    public static Polinom integrare(Polinom polinom1) {

        Polinom result = new Polinom();
        Monom m;

        for (Monom m1 : polinom1.getMonoame()) {
            m = new Monom(m1.getCoeficient() / (m1.getIndex() + 1), m1.getIndex() + 1);
            result.add(m);
        }

        return result;
    }
}
