// Assignment 3
// Brandon Vowell
// AdjListNode Class

package components;

import java.util.*;
import java.io.*;

public class AdjListNode {
    EdgeNode value;
    AdjListNode next;
    AdjListNode previous;

    public AdjListNode(Vertex v1, Vertex v2,  int w) {
        EdgeNode e = new EdgeNode(v1, v2, w);
        value = e;
        next = null;
        previous = null;
    }

}
