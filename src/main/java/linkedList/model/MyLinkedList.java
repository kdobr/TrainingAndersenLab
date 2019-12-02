package linkedList.model;

import java.util.Iterator;

public class MyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.data;
                current = current.next;
                return element;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.data))
                    return index;
                index++;
            }
        }
        return -1;
    }


    @Override
    public E getElementByIndex(int index) {
        Node<E> node = getNodeByIndex(index);
        return node.data;
    }

    private Node<E> getNodeByIndex(int index) {
        checkIndex(index);
        Node<E> node;
        if (index <= size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
        }
    }

    @Override
    public E setElementToIndex(int index, E element) {
        Node<E> node = getNodeByIndex(index);
        E elementChanged = node.data;
        node.data = element;
        return elementChanged;
    }

    @Override
    public boolean add(E element) {

        Node<E> newNode = new Node<>(tail, element, null);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {

        if (index == size) {
            add(element);
            return true;
        }

        Node<E> newNode = new Node<>(null, element, null);
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next, i++) {
            if (i == index) {
                newNode.next = x;
                newNode.prev = x.prev;
                x.prev.next = newNode;
                x.prev = newNode;
                size++;
                if (i == 0) head = newNode;
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        return unlink(getNodeByIndex(index));
    }

    @Override
    public boolean remove(E element) {

        if (element == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (element.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    private E unlink(Node<E> x) {
        Node<E> prev = x.prev;
        Node<E> next = x.next;
        E element = x.data;

        if (prev != null) {
            prev.next = next;
            x.prev = null;
        } else {
            head = next;
            head.prev = null;
        }

        if (next != null) {
            next.prev = prev;
            x.next = null;
        } else {
            tail = prev;
            tail.next = null;
        }

        x.data = null;
        size--;
        return element;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        Node(Node<E> prev, E data, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void reverseOrder() {

        Node<E> temp = head;
        head = tail;
        tail = temp;

        Node<E> current = head;

        while (current != null) {
            temp = current.next;
            current.next = current.prev;
            current.prev = temp;
            current = current.next;
        }
    }
}
