// Assignment 2
// Queue Class
// Brandon Vowell

package components;

import java.io.*;
import java.util.*;

public class Queue {
    
    QueueNode head;
    QueueNode tail;

    public Queue() {
        head = null;
        tail = null;
    }

    public void enqueue(QueueNode newValue) {
        if(head == null) {
            head = newValue;
            tail = newValue;
        }
        else {
            newValue.previous = tail;
            tail.next = newValue;
            tail = newValue;
        }
        return;
    }

    public Edge dequeue() {
        Edge temp = new Edge();
        if(head == null)
            System.out.println("No value to dequeue");
        else {
            temp = head.value;
            head = head.next;
            if(head != null)
                head.previous = null;
        }
        return temp;
    }

    public EdgeNode dequeueVertex() {
        EdgeNode temp = null;
        if(head == null)
            System.out.println("No value to dequeue");
        else {
            temp = head.vertexValue;
            head = head.next;
            if(head != null)
                head.previous = null;
        }
        return temp;
    }

    public Edge peek() {
        return head.value;
    }

    public QueueNode getHead() {
        return head;
    }

    public void buildHeap(Heap h, Edge e) {
        QueueNode qNode = new QueueNode(e);
        if(head == null) {
            enqueue(qNode);
            h.setHeapRoot(e);
        }
        else if(head.value.leftChild == null) {
            head.value.leftChild = e;
            e.parent = head.value;
            enqueue(qNode);
        }
        else if(head.value.rightChild == null) {
            head.value.rightChild = e;
            e.parent = head.value;
            enqueue(qNode);
        }
        else {
            dequeue();
            buildHeap(h, e);
        }
        return;
    }

    public void printQueue() {
        while(head != null) {
            System.out.println("Vertex 1: " + head.value.v1.name);
            System.out.println("Vertex 2: " + head.value.v2.name);
            System.out.println("Weight of Edge: " + head.value.weight);
            head = head.next;
        }
        return;
    }

    public void resetQueue() {
        head = null;
        tail = null;
        return;
    }
}
