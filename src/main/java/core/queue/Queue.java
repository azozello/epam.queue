package core.queue;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author azozello
 */

public class Queue<T> {

    private T[] array;
    private T last, first;
    private int size = 0;
    private int index;
    private boolean isEmpty;

    public Queue(T[] sourceArray) {
        this.index = sourceArray.length;
        this.array = sourceArray;
        if (sourceArray.length!=0){
            this.first = sourceArray[0];
            this.last = sourceArray[sourceArray.length-1];
        }
        this.size = sourceArray.length;
        this.isEmpty = checkEmpty();
    }

    @SuppressWarnings(value = "unchecked")
    public Queue(int size, Class<T> c) {
        this.index = 0;
        this.size = size;
        this.array = (T[]) Array.newInstance(c,size);
        this.isEmpty = checkEmpty();
    }

    public void insert(T t){
        if (!checkFull()) {
            if (checkEmpty()) {
                first = t;
                last = t;
                array[index++] = t;
                this.isEmpty = false;
            } else {
                array[index++] = t;
                last = t;
            }
        } else {
            amplifyArray();
            insert(t);
        }
    }

    public void remove(){
        if (index>1) {
            last = array[index-2];
            array[(index--)-1] = null;
        } else if(index==1){
            array[0] = null;
            last = null;
            first = null;
            index--;
        } else {
            array[0] = null;
            last = null;
            first = null;
        }
    }

    @SuppressWarnings("unchecked")
    private void amplifyArray(){
        T[] oldArray = this.array;
        T[] newArray = (T[])Array.newInstance(this.last.getClass(),((size/2)*3)+1);
        System.arraycopy(oldArray,0,newArray,0,oldArray.length);
        this.size = newArray.length;
        this.array = newArray;
    }

    private boolean checkEmpty(){
        for (T t : array){
            if (t!=null) return false;
        } return true;
    }

    private boolean checkFull(){
        for (T t : array){
            if (t==null) return false;
        } return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (array!=null) {
            if (isEmpty()) {
                result.append("Empty");
                result.append(System.getProperty("line.separator"));

                result.append("Size: ");
                result.append(getSize());
                result.append(System.getProperty("line.separator"));
            } else {
                result.append("First: ");
                result.append(getFirst());
                result.append(System.getProperty("line.separator"));

                result.append("Last:  ");
                result.append(getLast());
                result.append(System.getProperty("line.separator"));

                result.append("Size:  ");
                result.append(getSize());
                result.append(System.getProperty("line.separator"));

                for (T t : array) {
                    result.append(t);
                    result.append(System.getProperty("line.separator"));
                }
            }
        } else {
            result.append("Empty object");
            result.append(System.getProperty("line.separator"));
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Queue<?> queue = (Queue<?>) o;

        if (size != queue.size) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(array, queue.array);
    }

    @Override
    public int hashCode() {
        return size;
    }

    public T getLast() {
        return last;
    }

    public T getFirst() {
        return first;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return isEmpty;
    }
}
