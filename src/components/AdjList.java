// Assignment 3
// Brandon Vowell
// AdjList Class

package components;

import java.util.*;
import java.io.*;

public class AdjList {
    AdjListNode head;
    AdjListNode tail;

    public AdjList() {
        head = null;
        tail = null;
    }

    public void addToList(Vertex v1, Vertex v2, int w) {
        AdjListNode toInsert = new AdjListNode(v1, v2, w);
        AdjListNode walk = head;
        if(head == null) {
            head = toInsert;
            tail = toInsert;
        }
        else {
            toInsert.previous = tail;
            tail.next = toInsert;
            tail = toInsert;
        }
        return;
    }

    public void printAdjList() {
        System.out.println("Printing AdjList");
        AdjListNode walk = head;
        while(walk != null) {
            System.out.println("NAME OF ADJ NODE: " + walk.value.destination.name);
            walk = walk.next;
            if(walk == null)
                break;
        }
        return;
    }
}
