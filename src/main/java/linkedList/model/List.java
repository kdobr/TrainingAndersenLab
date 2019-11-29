package linkedList.model;

public interface List<E> extends Iterable<E> {
    int size();

    int indexOf(E o);

    E getElementByIndex(int index);

    E setElementToIndex(int index, E element);

    boolean add(E element);

    boolean add(int index, E element);

    E remove(int index);

    boolean remove(E element);

    void clear();
}
