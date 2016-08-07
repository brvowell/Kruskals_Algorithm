// Assignment 3
// Brandon Vowell
// RBTree Class

package components;

import java.util.*;
import java.io.*;

public class RBTree {
    TreeNode root;
    int vertexNodeCount;
    int edgeNodeCount;

    public RBTree() {
        root = null;
        vertexNodeCount = 0;
        edgeNodeCount = 0;
    }

    public void setRoot(TreeNode t) {
        root = t;
        return;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRootColor(char c) {
        root.color = c;
        return;
    }

    public int getVertexNodeCount() {
        return vertexNodeCount;
    }

    public int getEdgeNodeCount() {
        return edgeNodeCount;
    }

    public Vertex findParticularVertex(TreeNode start, int valueToFind) {
        if(start == null)
            return null;
        int compareResult = valueToFind - start.getVertexTreeValue().getName();
        if(compareResult == 0)
            return start.getVertexTreeValue();
        else if(compareResult < 0 && start.leftChild != null)
            return findParticularVertex(start.leftChild, valueToFind);
        else if(compareResult < 0 && start.leftChild == null)
            return null;
        else if(compareResult > 0 && start.rightChild != null)
            return findParticularVertex(start.rightChild, valueToFind);
        else
            return null;
    }

    public boolean findParticularEdge(TreeNode start, int v1Value, int v2Value) {
        if(start == null)
            return false;
        int comparev1 = v1Value - start.getEdgeTreeValue().getV1().getName();
        int comparev2 = v2Value - start.getEdgeTreeValue().getV2().getName();
        if(comparev1 == 0 && comparev2 == 0)
            return true;
        if(comparev1 == 0) {
            if(comparev2 < 0 && start.leftChild != null)
                return findParticularEdge(start.leftChild, v1Value, v2Value);
            else if(comparev2 < 0 && start.leftChild == null)
                return false;
            else if(comparev2 > 0 && start.rightChild != null)
                return findParticularEdge(start.rightChild, v1Value, v2Value);
            else
                return false;
        }
        else {
            if(comparev1 < 0 && start.leftChild != null) 
                return findParticularEdge(start.leftChild, v1Value, v2Value);
            else if(comparev1 < 0 && start.leftChild == null)
                return false;
            else if(comparev1 > 0 && start.rightChild != null)
                return findParticularEdge(start.rightChild, v1Value, v2Value);
            else
                return false;
        }
    }

    public TreeNode insertNewVertex(TreeNode startNode, TreeNode newValue, DJS d, int rootForTree) {
        if(startNode == null) {
            root = newValue;
            if(rootForTree == -1)
                d.root = newValue.getVertexTreeValue();
            vertexNodeCount++;
            return newValue;
        }

        int compareResult = newValue.vertexTreeValue.name - startNode.vertexTreeValue.name;
        if(compareResult == 0)
            return null;
        if(compareResult < 0 && startNode.leftChild == null) {
            startNode.leftChild = newValue;
            newValue.parent = startNode;
            newValue.level++;
            newValue.location = 'L';
            vertexNodeCount++;
            return newValue;
        }
        else if(compareResult < 0 && startNode.leftChild != null) {
            newValue.level++;
            return insertNewVertex(startNode.leftChild, newValue, d, rootForTree);
        }
        else if(compareResult > 0 && startNode.rightChild == null) {
            startNode.rightChild = newValue;
            newValue.parent = startNode;
            newValue.level++;
            newValue.location = 'R';
            vertexNodeCount++;
            return newValue;
        }
        else {
            newValue.level++;
            return insertNewVertex(startNode.rightChild, newValue, d, rootForTree);
        }
    }

    public TreeNode insertNewEdge(TreeNode startNode, TreeNode newValue) {
        if(startNode == null) {
            root = newValue;
            edgeNodeCount++;
            return newValue;
        }
        
        int comparev1 = newValue.edgeTreeValue.v1.name - startNode.edgeTreeValue.v1.name;
        int comparev2 = newValue.edgeTreeValue.v2.name - startNode.edgeTreeValue.v2.name;

        if(comparev1 == 0) {
            if(comparev2 < 0 && startNode.leftChild != null) {
                newValue.level++;
                return insertNewEdge(startNode.leftChild, newValue);
            }
            else if(comparev2 < 0 && startNode.leftChild == null) {
                startNode.leftChild = newValue;
                newValue.parent = startNode;
                newValue.level++;
                newValue.location = 'L';
                edgeNodeCount++;
                return newValue;
            }
            else if(comparev2 > 0 && startNode.rightChild != null) {
                newValue.level++;
                return insertNewEdge(startNode.rightChild, newValue);
            }
            else {
                startNode.rightChild = newValue;
                newValue.parent = startNode;
                newValue.level++;
                newValue.location = 'R';
                edgeNodeCount++;
                return newValue;
            }
        }

        else {
            if(comparev1 < 0 && startNode.leftChild != null) {
                newValue.level++;
                return insertNewEdge(startNode.leftChild, newValue);
            }
            else if(comparev1 < 0 && startNode.leftChild == null) {
                startNode.leftChild = newValue;
                newValue.parent = startNode;
                newValue.level++;
                newValue.location = 'L';
                edgeNodeCount++;
                return newValue;
            }
            else if(comparev1 > 0 && startNode.rightChild != null) {
                newValue.level++;
                return insertNewEdge(startNode.rightChild, newValue);
            }
            else {
                startNode.rightChild = newValue;
                newValue.parent = startNode;
                newValue.level++;
                newValue.location = 'R';
                edgeNodeCount++;
                return newValue;
            }
        }
    }
}

