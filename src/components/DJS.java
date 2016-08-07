// Assignment 3
// Brandon Vowell
// DJS Class

// DJS stands for Disjoint Set

package components;

import java.util.*;
import java.io.*;

public class DJS {
    DJSNode head;
    DJSNode tail;
    Vertex root;
    int numberInserted;

    public DJS() {
        head = null;
        tail = null;
        root = null;
        numberInserted = 0;
    }

    public int getNumberInserted() {
        return numberInserted;
    }

    public Vertex getRoot() {
        return root;
    }

    public void makeSet(Vertex v, int specifiedRoot) {
        DJSNode toInsert = new DJSNode(v);
        if(specifiedRoot == v.name)
            root = v;
        if(head == null) {
            head = toInsert;
            tail = toInsert;
            numberInserted++;
        }
        else {
            tail.next = toInsert;
            toInsert.previous = tail;
            tail = toInsert;
            numberInserted++;
        }
        return;
    }

    public void printRootList() {
        System.out.println("Printing Root List");
        DJSNode walk = head;
        while(walk != null) {
            System.out.println("Vertex Name: " + walk.value.name);
            walk = walk.next;
            if(walk != null)
                head.previous = null;
        }
        return;
    }

    private void removeVertexFromList(Vertex v) {
        DJSNode walk = head;
        while(walk.value != v)
            walk = walk.next;
        if(walk == head)
            head = head.next;
        if(walk.next != null)
            walk.next.previous = walk.previous;
        if(walk.previous != null)
            walk.previous.next = walk.next;
        return;
    }

    public Vertex findSet(Vertex v) {
        if(v.parent == v) 
            return v;
        else {
            //v.parent = findSet(v.parent);
            return findSet(v.parent);
        }
    }

    private boolean unionSet(Vertex v1, Vertex v2) {
        if(v1 == v2)
            return false;
        else {
            int v1Rank = v1.getRank();
            int v2Rank = v2.getRank();
            if(v1Rank == v2Rank) {
                v2.parent = v1;
                removeVertexFromList(v2);
                v1.rank++;
            }
            else if(v1Rank > v2Rank) {
                v2.parent = v1;
                removeVertexFromList(v2);
            }
            else {
                v1.parent = v2;
                removeVertexFromList(v1);
            }
            return true;
        }
    }

    public void handleEdge(Edge e) {
        if(e == null)
            return;
        Vertex rootOfFirst = findSet(e.v1);
        Vertex rootOfSecond = findSet(e.v2);
        boolean combineSet = unionSet(rootOfFirst, rootOfSecond);
        if(combineSet) {
            //System.out.println(e.v1.name + " is being added to " + e.v2.name + "'s AdjList");
            //System.out.println(e.v2.name + " is being added to " + e.v1.name + "'s AdjList");
            e.getV1().getAdjList().addToList(e.v1, e.v2, e.weight);
            e.getV2().getAdjList().addToList(e.v2, e.v1, e.weight);
        }
        return;
    }
}
