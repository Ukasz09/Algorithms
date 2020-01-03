package utilities;

import java.util.Iterator;
import java.util.RandomAccess;

public class ArrayList<T> extends AbstractList<T> implements RandomAccess {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private final int _initialCapacity;
    private T[] _array;
    private int _size;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity <= 0)
            capacity = DEFAULT_INITIAL_CAPACITY;
        _initialCapacity = capacity;
        _array = (T[]) (new Object[capacity]);
        _size = 0;
    }

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(ArrayList<T> arr){
        _array = (T[]) (new Object[arr.size()]);
        if (arr.size() >= 0) System.arraycopy(arr.get_array(), 0, _array, 0, arr.size());
        _size=_array.length;
        _initialCapacity=_size;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressWarnings("unchecked")
    private void ensureCapacity(int capacity) {
        if (_array.length < capacity) {
            T[] copy = (T[]) (new Object[capacity + capacity / 2]);
            System.arraycopy(_array, 0, copy, 0, _size);
            _array = copy;
        }
    }

    private void checkOutOfBounds(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= _size) throw new IndexOutOfBoundsException();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        _array = (T[]) (new Object[_initialCapacity]);
        _size = 0;
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean add(T value) {
        ensureCapacity(_size + 1);
        _array[_size] = value;
        _size++;
        return true;
    }

    @Override
    public boolean add(int index, T value) {
        if (index < 0 || index > _size) throw new IndexOutOfBoundsException();
        ensureCapacity(_size + 1);
        if (index != _size)
            System.arraycopy(_array, index, _array, index + 1, _size - index);
        _array[index] = value;
        _size++;
        return false;
    }

    @Override
    public T get(int index) {
        checkOutOfBounds(index);
        return _array[index];
    }

    @Override
    public T set(int index, T element) {
        checkOutOfBounds(index);
        T retValue = _array[index];
        _array[index] = element;
        return retValue;
    }

    @Override
    public T delete(int index) {
        checkOutOfBounds(index);
        T retValue = _array[index];
        int copyFrom = index + 1;
        if (copyFrom < _size)
            System.arraycopy(_array, copyFrom, _array, index, _size - copyFrom);
        --_size;
        return retValue;
    }

    @Override
    public boolean delete(T value) {
        int pos = 0;
        while (pos < _size && !_array[pos].equals(value))
            pos++;
        if (pos < _size) {
            delete(pos);
            return true;
        }
        return false;
    }

    private class InnerIterator implements Iterator<T> {
        int _pos = 0;

        @Override
        public boolean hasNext() {
            return _pos < _size;
        }

        @Override
        public T next() {
            return _array[_pos++];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    private T[] get_array() {
        return _array;
    }
}
