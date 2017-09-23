package sortbox.pcs.entity;

/**
 * TwoTuple class encapsulates two objects into one object.
 * It is useful to be a middle variable.
 * <p>
 * Created by Clever.Su on 2017/09/15
 *
 * @param <F> the first generic type element
 * @param <S> the second generic type element
 */
public class TwoTuple<F, S> implements Comparable<TwoTuple<F, S>> {

    private F f;
    private S s;

    private TwoTuple() {
        // def constructor
        // nothing...
    }

    public TwoTuple(F f, S s) {
        this.f = f;
        this.s = s;
    }

    public F extractFirst() {
        return this.f;
    }

    public S extractSecond() {
        return this.s;
    }

    public void updateF(F newF) {
        this.f = newF;
    }

    public void updateS(S newS) {
        this.s = newS;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "f=" + f +
                ", s=" + s +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TwoTuple twoTuple = (TwoTuple) o;

        return !(f != null ? !f.equals(twoTuple.f) : twoTuple.f != null) && !(s != null ? !s.equals(twoTuple.s) : twoTuple.s != null);
    }

    @Override
    public int hashCode() {
        int result = f != null ? f.hashCode() : 0;
        result = 31 * result + (s != null ? s.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(TwoTuple<F, S> o2) {
        F f1 = this.extractFirst();
        F f2 = o2.extractFirst();

        if (f1 instanceof Integer && f2 instanceof Integer) {
            return ((Integer) f1) - ((Integer) f2);
        }

        return 0;
    }
}
