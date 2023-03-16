import java.text.DecimalFormat;
import java.util.*;

public class Polinom {
    private LinkedList<Monom> monoame = new LinkedList<Monom>();

    public Polinom(){
        super();
    }

    public LinkedList<Monom> getMonoame() {
        return monoame;
    }

    public int rang(){
        int rangPolinom = 0;

        for(Monom m: monoame){
            if(rangPolinom < m.getIndex())
                rangPolinom = m.getIndex();
        }

        return rangPolinom;
    }

    public void add(Monom m){
        this.monoame.add(m);
        Collections.sort(monoame);
    }

    public int searchByIndex(int index, double coef){

       for(Monom m1: monoame){
           if(index == m1.getIndex()){
               m1.setCoeficient(m1.getCoeficient() + coef);
               return 1;
           }
       }
       return 0;
    }

    public static Polinom inmultireCuMonom(Polinom pol1, Monom m){
        Polinom result = new Polinom();

        for(Monom m1: pol1.monoame){
            result.add(new Monom(m1.getCoeficient() * m.getCoeficient(),m1.getIndex() + m.getIndex()));
        }
        return result;
    }

    public void adunareCuMonom(Monom m){
      int ok = 0;

       for(Monom m1: monoame){
           if(m1.getIndex() == m.getIndex()) {
               m1.setCoeficient(m1.getCoeficient() + m.getCoeficient());
                ok = 1;
           }
       }

       if(ok == 0)
           monoame.add(m);
    }

    public void removeZeros(){

        ListIterator<Monom> monomIterator = monoame.listIterator();
        if(monomIterator.hasNext())

            while(monomIterator.hasNext()){
                if(monomIterator.next().getCoeficient() == 0){
                    monomIterator.remove();
                }
            }
    }

    @Override
    public String toString() {
        String str = "";
       Collections.sort(monoame);
        int putere = 99999;
        for(Monom m: monoame){
            if(m.getIndex() < putere) {
                putere = m.getIndex();
                break;
            }
        }
        for(Monom m: monoame){
            if(m.getIndex() == putere){
                if(m.getCoeficient() % 1 == 0)
                    str = str + (int)m.getCoeficient() + "x^" + m.getIndex();
                else
                    str = str + new DecimalFormat("#.##").format(m.getCoeficient()) + "x^" + m.getIndex();
            }
            else{
                if(m.getCoeficient() % 1 == 0)
                    str = str + "+" + (int)m.getCoeficient() + "x^" + m.getIndex();
                else
                    str = str + "+" + new DecimalFormat("#.##").format(m.getCoeficient()) + "x^" + m.getIndex();
            }
        }
        str = str.replace("+-", "-");
        str = str.replace("x^0", "");
        str = str.replace("x^1", "x");
        str = str.replace("1x", "x");
        str = str.replace("0x", "");
        str = str.replace("+0", "");
        str = str.replace("-0", "");

        return str.isEmpty() ? "0" : str;
    }
}
