// Assignment 3
// Brandon Vowell
// Edge Class

package components;

import java.util.*;
import java.io.*;

public class Edge {
    Vertex v1;
    Vertex v2;
    int weight;
    Edge leftChild;
    Edge rightChild;
    Edge parent;

    public Edge() {
        v1 = null;
        v2 = null;
        weight = -1;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public Edge(Vertex a, Vertex b) {
        v1 = a;
        v2 = b;
        weight = 1;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public Edge(Vertex a, Vertex b, int w) {
        v1 = a;
        v2 = b;
        weight = w;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getV1() {
        return v1;
    }

    public Vertex getV2() {
        return v2;
    }

    public void disconnectChildFromParent() {
        if(parent == null)
            return;
        if(parent.rightChild == null) {
            parent.leftChild = null;
            return;
        }
        else
            parent.rightChild = null;
        return;
    }

}
