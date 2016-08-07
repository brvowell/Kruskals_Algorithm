// Assignment 3: Kruskal's Algorithm
// Brandon Vowell
// Main File
package main;

import java.io.*;
import java.util.Scanner;
import components.*;

class Kruskal {
    public static void main(String[] args) {
        int rootForTree = -1; 
        String f = args[0];
        if(f.charAt(0) == '-') {
            String root = args[1];
            rootForTree = Integer.parseInt(root);
            f = args[2];
        }
        File file = new File(f);
        Heap minEdgeHeap = new Heap();
        RBTree vertexLookUp = new RBTree();
        RBTree edgeLookUp = new RBTree();
        Queue edgeQueue = new Queue();
        Stack s = new Stack();
        DJS disjointSet = new DJS();
        try {
            Scanner sc = new Scanner(file);
            createEdgesAndVertices(sc, minEdgeHeap, vertexLookUp, edgeLookUp, edgeQueue, s, disjointSet, rootForTree);
            sc.close();
            edgeQueue.resetQueue();
            while(s.getHead() != null) {
                QueueNode popped = s.pop();
                edgeQueue.enqueue(popped);
                Edge edg = popped.getValue();
                minEdgeHeap.heapify(edg);
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        while(minEdgeHeap.getRoot() != null) {
            Edge removed = minEdgeHeap.extractMin(edgeQueue);
            disjointSet.handleEdge(removed);
        }
        Queue vertexQueue = new Queue();
        disjointSet.getRoot().breadthFirstSearch(vertexQueue, vertexLookUp.getVertexNodeCount());
    }

    private static void createEdgesAndVertices(Scanner sc, Heap minEdgeHeap, RBTree vertexLookUp, RBTree edgeLookUp, Queue edgeQueue, Stack s, DJS d, int rootValue) {
        int i = 0;
        String[] storeEdges = new String[3];
        TreeNode vertexTemp, edgeTemp;
        Vertex v, w;
        while(sc.hasNext()) {
            storeEdges[i] = sc.next();
            i++;
            if(i == 3) {
                if(storeEdges[2].charAt(0) == ';') {
                    int zero = Integer.parseInt(storeEdges[0]);
                    Vertex lookForV = vertexLookUp.findParticularVertex(vertexLookUp.getRoot(), zero);
                    if(lookForV != null) 
                        v = lookForV;
                    else {
                        v = new Vertex(zero);
                        d.makeSet(v, rootValue);
                        TreeNode vertexOne = new TreeNode(v);
                        vertexTemp = vertexLookUp.insertNewVertex(vertexLookUp.getRoot(), vertexOne, d, rootValue);
                        vertexOne.insertionFixUp(vertexTemp, vertexLookUp);
                    }

                    int one = Integer.parseInt(storeEdges[1]);
                    Vertex lookForW = vertexLookUp.findParticularVertex(vertexLookUp.getRoot(), one);
                    if(lookForW != null)
                        w = lookForW;
                    else {
                        w = new Vertex(one);
                        d.makeSet(w, rootValue);
                        TreeNode vertexTwo = new TreeNode(w);
                        vertexTemp = vertexLookUp.insertNewVertex(vertexLookUp.getRoot(), vertexTwo, d, rootValue);
                        vertexTwo.insertionFixUp(vertexTemp, vertexLookUp);
                    }

                    boolean originalEdge = edgeLookUp.findParticularEdge(edgeLookUp.getRoot(), v.getName(), w.getName());
                    boolean invertedEdge = edgeLookUp.findParticularEdge(edgeLookUp.getRoot(), w.getName(), v.getName());
                    boolean isEdgeInTree = originalEdge | invertedEdge;
                    if(isEdgeInTree == false) {
                        Edge e = new Edge(v, w);
                        TreeNode edgeForTree = new TreeNode(e);
                        edgeTemp = edgeLookUp.insertNewEdge(edgeLookUp.getRoot(), edgeForTree);
                        edgeForTree.insertionFixUp(edgeTemp, edgeLookUp);
                    s.push(e);
                    edgeQueue.buildHeap(minEdgeHeap, e);
                    }

                    i = 0;
                }
                else {
                    int zero = Integer.parseInt(storeEdges[0]);
                    Vertex lookForV = vertexLookUp.findParticularVertex(vertexLookUp.getRoot(), zero);
                    if(lookForV != null)
                        v = lookForV;
                    else {
                        v = new Vertex(zero);
                        d.makeSet(v, rootValue);
                        TreeNode vertexOne = new TreeNode(v);
                        vertexTemp = vertexLookUp.insertNewVertex(vertexLookUp.getRoot(), vertexOne, d, rootValue);
                        vertexOne.insertionFixUp(vertexTemp, vertexLookUp);
                    }

                    int one = Integer.parseInt(storeEdges[1]);
                    Vertex lookForW = vertexLookUp.findParticularVertex(vertexLookUp.getRoot(), one);
                    if(lookForW != null)
                        w = lookForW;
                    else {
                        w = new Vertex(one);
                        d.makeSet(w, rootValue);
                        TreeNode vertexTwo = new TreeNode(w);
                        vertexTemp = vertexLookUp.insertNewVertex(vertexLookUp.getRoot(), vertexTwo, d, rootValue);
                        vertexTwo.insertionFixUp(vertexTemp, vertexLookUp);
                    }

                    int weight = Integer.parseInt(storeEdges[2]);
                    boolean originalEdge = edgeLookUp.findParticularEdge(edgeLookUp.getRoot(), v.getName(), w.getName());
                    boolean invertedEdge = edgeLookUp.findParticularEdge(edgeLookUp.getRoot(), w.getName(), v.getName());
                    boolean isEdgeInTree = originalEdge | invertedEdge;
                    if(isEdgeInTree == false) {
                        Edge e = new Edge(v, w, weight);
                        TreeNode edgeForTree = new TreeNode(e);
                        edgeTemp = edgeLookUp.insertNewEdge(edgeLookUp.getRoot(), edgeForTree);
                        edgeForTree.insertionFixUp(edgeTemp, edgeLookUp);
                        s.push(e);
                        edgeQueue.buildHeap(minEdgeHeap, e);
                    }

                    i = 0;
                    String discard = sc.next();
                }
            }
        }
    }

}
