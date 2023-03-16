public class Monom implements Comparable{

    private double coeficient;
    private int index;

    public Monom(double coeficient, int index) {
        this.coeficient = coeficient;
        this.index = index;
    }

    public double getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(double coeficient) {
        this.coeficient = coeficient;
    }

    public int getIndex() {
        return index;
    }

    public Monom divide(Monom m){
        return new Monom(this.coeficient/m.getCoeficient(), this.index - m.getIndex());
    }

    @Override
    public int compareTo(Object o) {
        Monom m = (Monom)o;

        if(m.getIndex() > index)
            return 1;
        else
            return -1;
    }

}
