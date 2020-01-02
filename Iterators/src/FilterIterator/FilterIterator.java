package Ex2;

import java.util.NoSuchElementException;

public class FilterIterator implements MyIterator {
    private MyIterator myIterator;
    private Predicate predicate;
    private Integer elementNext;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean foundedValidValue;

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    public FilterIterator(MyIterator myIterator, Predicate<Integer> predicate) {
        this.myIterator = myIterator;
        this.predicate = predicate;
        hasNext = myIterator.hasNext();
        hasPrevious = myIterator.hasPrevious();
        elementNext = null;
        foundedValidValue = true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private void findNextValid() {
        foundedValidValue = false;
        while (myIterator.hasNext()) {
            myIterator.next();
            elementNext = myIterator.current();
            if (predicate.accept(elementNext)) {
                foundedValidValue = true;
                return;
            }
        }
        hasNext = false;
    }

    private void findPreviousValid() {
        foundedValidValue = false;
        while (myIterator.hasPrevious()) {
            myIterator.previous();
            elementNext = myIterator.current();
            if (predicate.accept(elementNext)) {
                foundedValidValue = true;
                return;
            }
        }
        hasPrevious = false;
    }

    @Override
    public void next() {
        findNextValid();
    }

    @Override
    public void previous() {
        findPreviousValid();
    }

    @Override
    public void first() {
        myIterator.first();
        hasNext = myIterator.hasNext();
    }

    @Override
    public void last() {
        myIterator.last();
        hasPrevious = myIterator.hasPrevious();
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public boolean hasPrevious() {
        return hasPrevious;
    }

    @Override
    public int current() throws NoSuchElementException {
        if (elementNext != null)
            return elementNext;
        else throw new NoSuchElementException();
    }

    @Override
    public void showAllNext() {
        while (this.hasNext()) {
            this.next();
            if (foundedValidValue)
                System.out.print(this.current() + " ");
        }
    }

    @Override
    public void showAllPrevious() {
        while (this.hasPrevious()) {
            this.previous();
            if (foundedValidValue)
                System.out.print(this.current() + " ");
        }
    }
}
