package com.example.lista_enlazada;

public class SimpleNode<T> {

    private T element;
    private SimpleNode<T> next;

    public SimpleNode(T element, SimpleNode<T> next) {
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public SimpleNode<T> getNext() {
        return next;
    }

    public void setNext(SimpleNode<T> next) {
        this.next = next;
    }

}