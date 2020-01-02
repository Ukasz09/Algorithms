package Ex3;

public class TransformIterator extends ArrModifyingIterator {
    private int multiplierValue;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TransformIterator(int[] arr, int multiplierValue) {
        super(arr);
        this.multiplierValue = multiplierValue;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void showAllNext() {
        while (this.hasNext()) {
            this.next();
            this.changeValue();
            System.out.print(this.current() + " ");
        }
    }

    @Override
    public void showAllPrevious() {
        while (this.hasPrevious()) {
            this.previous();
            this.changeValue();
            System.out.print(this.current() + " ");
        }
    }

    private boolean changeValue() {
        if (getArr() != null) {
            if ((getPos() >= 0) && (getPos() < getArr().length)) {
                getArr()[getPos()] *= multiplierValue;
                return true;
            } else return false;
        } else return false;
    }
}
