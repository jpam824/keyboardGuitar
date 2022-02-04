package deque;

import org.junit.Test;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node{
        public T item;
        public Node next;
        public Node prev;

        public Node(){
            this(null, null, null);
        }

        public Node(T g, Node n, Node p){
            item = g;
            next = n;
            prev = p;
        }

    }

    public Node sentinal;
    private int size;

    public LinkedListDeque(){
        sentinal = new Node();
        sentinal.prev = sentinal;
        sentinal.next = sentinal;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinal.next, sentinal);
        sentinal.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    @Override
    public void addLast(T item){
        Node newNode = new Node(item, sentinal, sentinal.prev);
        sentinal.prev = newNode;
        newNode.prev.next = newNode;
        size++;
    }

    @Override
    public T removeLast(){

        Node last = sentinal.prev;
        if(isEmpty()){
            return null;
        }
        Node p = sentinal;
        p.prev = null;
        sentinal.prev = last.prev;
        size--;
        return last.item;

    }

    @Override
    public T removeFirst(){

        Node first = sentinal.next;
        if(isEmpty()){
            return null;
        }
        Node p = sentinal;
        p.next = null;
        size--;
        sentinal.next = first.next;
        return first.item;
    }

    @Override
    public T get(int index){
        if(index == 0){
            return sentinal.next.item;
        }
        Node currentNode = sentinal.next.next;
        while(index > 1 && currentNode.next.item != null){
            index--;
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    @Override
    public void printDeque(){
        while(sentinal.next != sentinal){
            System.out.println(sentinal.next.item);
            sentinal.next = sentinal.next.next;
        }
    }

    @Override
    public int size(){
        return size;
    }



}