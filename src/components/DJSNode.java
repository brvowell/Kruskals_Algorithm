// Assignment 2
// DJSNode Class
// Brandon Vowell

package components;

import java.io.*;
import java.util.*;

public class DJSNode {
    
    Vertex value;
    DJSNode next;
    DJSNode previous;

    public DJSNode(Vertex n) {
        value = n;
        next = null;
        previous = null;
    }

    public Vertex getValue() {
        return value;
    }

    

}
