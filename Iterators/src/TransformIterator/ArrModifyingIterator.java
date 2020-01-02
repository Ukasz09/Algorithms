package Ex3;

import Ex2.MyIterator;

import java.util.NoSuchElementException;

public abstract class ArrModifyingIterator implements MyIterator {
    private int[] arr;
    private int pos;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrModifyingIterator(int[] arr){
        this.arr=arr;
        pos=-1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void next() throws NoSuchElementException {
        if(hasNext())
            pos++;
        else throw new NoSuchElementException();
    }

    @Override
    public void previous() throws NoSuchElementException {
        if(hasPrevious())
            pos--;
        else throw new NoSuchElementException();
    }

    @Override
    public void first() throws  NoSuchElementException{
        if (arr!=null)
            pos=-1;
        else throw new NoSuchElementException();
    }

    @Override
    public void last() throws NoSuchElementException{
        if (arr!=null)
            pos=arr.length;
        else throw new NoSuchElementException();
    }

    @Override
    public boolean hasNext(){
        if (arr==null) return false;
        else return (pos<arr.length-1);
    }

    @Override
    public boolean hasPrevious(){
        if(arr==null) return false;
        else return (pos>0);
    }

    @Override
    public int current() throws NoSuchElementException{
        if((arr!=null)&&(pos>=0))
            return arr[pos];
        else throw new NoSuchElementException();
    }

    protected int[] getArr() {
        return arr;
    }

    protected int getPos() {
        return pos;
    }

}
