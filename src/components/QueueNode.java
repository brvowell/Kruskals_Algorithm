// Assignment 2
// QueueNode Class
// Brandon Vowell

// QueueNode will be used for the Queue and the Stack Class

package components;

import java.io.*;
import java.util.*;

public class QueueNode {
    
    Edge value;
    EdgeNode vertexValue;
    QueueNode next;
    QueueNode previous;

    public QueueNode(Edge n) {
        value = n;
        vertexValue = null;
        next = null;
        previous = null;
    }

    public QueueNode(EdgeNode e) {
        value = null;
        vertexValue = e;
        next = null;
        previous = null;
    }

    public QueueNode() {
        value = null;
        vertexValue = null;
        next = null;
        previous = null;
    }

    public Edge getValue() {
        return value;
    }

    public EdgeNode getVertexValue() {
        return vertexValue;
    }

    

}
