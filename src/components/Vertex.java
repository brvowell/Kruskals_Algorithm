// Assignment 3
// Brandon Vowell
// Vertex Class

package components;

import java.util.*;
import java.io.*;

public class Vertex {
    int name;
    AdjList connectedNodes;
    Vertex parent;
    int rank;
    char color;

    public Vertex(int s) {
        name = s;
        connectedNodes = new AdjList();
        parent = this;
        rank = 1;
        color = 'w';
    }

    public int getName() {
        return name;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex p) {
        parent = p;
    }

    public int getRank() {
        return rank;
    }

    public AdjList getAdjList() {
        return connectedNodes;
    }

   public void breadthFirstSearch(Queue q, int numberOfVertices) {
        int i = 0, w = 0, iterator = 0;
        int numberInMST = 1;
        EdgeNode[] sorter = new EdgeNode[numberOfVertices];
        Comparator<EdgeNode> eComparator = new Comparator<EdgeNode>() {
            @Override
            public int compare(EdgeNode e1, EdgeNode e2) {
                if(e2 == null) 
                    return -1;
                else if(e1 == null)
                    return 1;
                else
                    return e1.weight - e2.weight;
            }
        };

        System.out.print(i + ": " + name + "; ");
        EdgeNode e = new EdgeNode(null, this, 0);
        QueueNode rootNode = new QueueNode(e);
        q.enqueue(rootNode);
        QueueNode nullHolder = new QueueNode();
        q.enqueue(nullHolder);
        color = 'b';
        EdgeNode poppedOff;
        while(q.head != null) {
            while(q.head.vertexValue != null) {
                poppedOff= q.dequeueVertex();
                sorter[iterator] = poppedOff;
                iterator++;
                poppedOff.destination.color = 'b';
                poppedOff.enqueueAdjList(q);
            }
            Arrays.sort(sorter, eComparator);
            for(int j = 0; j < iterator; j++) {
                if(sorter[j].destination != this) {
                    System.out.print(sorter[j].destination.name + "(" + sorter[j].origin.name + ")" + sorter[j].weight + "; ");
                    w += sorter[j].weight;
                    numberInMST++;
                    sorter[j] = null;
                }
            }
            iterator = 0;
            q.dequeueVertex();
            if(q.head != null) {
                nullHolder = new QueueNode();
                q.enqueue(nullHolder);
                i++;
                System.out.println();
                System.out.print(i + ": ");
            }
        }
        System.out.println();
        System.out.println("weight: " + w);
        System.out.println("unreachable: " + (numberOfVertices - numberInMST));
        return;
    }
}

    
