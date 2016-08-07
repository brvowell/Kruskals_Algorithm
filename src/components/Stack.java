// Assignment 2
// Brandon Vowell
// Stack Class

package components;

import java.io.*;
import java.util.*;

public class Stack {
    
    QueueNode head;
    QueueNode tail;

    public Stack() {
        head = null;
        tail = null;
    }

    public void push(Edge e) {
        QueueNode newValue = new QueueNode(e);
        if(head == null) {
            head = newValue;
            tail = newValue;
        }
        else {
            newValue.next = head;
            head.previous = newValue;
            head = newValue;
        }
        return;
    }

    public QueueNode pop() {
        if(head == null) {
            System.out.println("No value to pop");
            return null;
        }
        else {
            QueueNode temp = head;
            head = head.next;
            if(head != null)
                head.previous = null;
            return temp;
        }
    }

    public Edge peek() {
        return head.value;
    }

    public QueueNode getHead() {
        return head;
    }
    
    public void printStack() {
        while(head != null) {
            System.out.println("Vertex 1: " + head.value.v1.name);
            System.out.println("Vertex 2: " + head.value.v2.name);
            System.out.println("Weight: " + head.value.weight);
            head = head.next;
        }
    }
} 




