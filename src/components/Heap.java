// Assignment 3
// Brandon Vowell
// Heap Class

package components;

import java.util.*;
import java.io.*;

public class Heap {
    Edge root;

    public Heap() {
        root = null;
    }

    public void setHeapRoot(Edge e) {
        root = e;
        return;
    }

    public Edge getRoot() {
        return root;
    }

    public void heapify(Edge e) {
        int leftWeight = 100000000, rightWeight = 100000000, min = 0;
        if(e == null) 
            return;
        if(e.leftChild != null)
            leftWeight = e.leftChild.weight;
        if(e.rightChild != null)
            rightWeight = e.rightChild.weight;
        min = (leftWeight < rightWeight) ? leftWeight : rightWeight;
        if(min < e.weight) {
            int tempInt = e.weight;
            Vertex v1Temp = e.v1;
            Vertex v2Temp = e.v2;
            if(min == leftWeight) {
                e.weight = e.leftChild.weight;
                e.leftChild.weight = tempInt;
                e.v1 = e.leftChild.v1;
                e.leftChild.v1 = v1Temp;
                e.v2 = e.leftChild.v2;
                e.leftChild.v2 = v2Temp;
                heapify(e.leftChild);
            }
            else {
                e.weight = e.rightChild.weight;
                e.rightChild.weight = tempInt;
                e.v1 = e.rightChild.v1;
                e.rightChild.v1 = v1Temp;
                e.v2 = e.rightChild.v2;
                e.rightChild.v2 = v2Temp;
                heapify(e.rightChild);
            }
        }
        return;
    }

    public Edge extractMin(Queue q) {
        if(q.head == null) {
            root = null;
            return null;
        }
        Edge temp = new Edge(root.getV1(), root.getV2(), root.getWeight());
        Edge poppedOff = q.dequeue();
        root.weight = poppedOff.weight;
        root.v1 = poppedOff.v1;
        root.v2 = poppedOff.v2;
        poppedOff.disconnectChildFromParent();
        heapify(root);
        return temp;
    }

}
