// Assignment 3
// Brandon Vowell
// EdgeNode Class

package components;

import java.util.*;
import java.io.*;

public class EdgeNode {
    Vertex origin;
    Vertex destination;
    int weight;

    public EdgeNode(Vertex a, Vertex b, int c) {
        origin = a;
        destination = b;
        weight = c;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public void enqueueAdjList(Queue q) {
        AdjListNode walk = this.destination.connectedNodes.head;
        while(walk != null) {
            if(walk.value.destination.color == 'w') {
                QueueNode newValue = new QueueNode(walk.value);
                q.enqueue(newValue);
            }
            walk = walk.next;
            if(walk == null)
                break;
        }
        return;
    }
}







