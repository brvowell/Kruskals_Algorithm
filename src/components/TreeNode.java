// Assignment 3
// Brandon Vowell
// TreeNode Class

package components;

import java.util.*;
import java.io.*;

public class TreeNode {
    Vertex vertexTreeValue;
    Edge edgeTreeValue;
    char color;
    char location;
    int level;
    TreeNode leftChild;
    TreeNode rightChild;
    TreeNode parent;

    public TreeNode(Vertex v) {
        vertexTreeValue = v;
        edgeTreeValue = null;
        color = 'r';
        location = 'X';
        level = 1;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public TreeNode(Edge e) {
        vertexTreeValue = null;
        edgeTreeValue = e;
        color = 'r';
        location = 'X';
        level = 1;
        leftChild = null;
        rightChild = null;
        parent = null;
    }

    public TreeNode(char c) {
        vertexTreeValue = null;
        edgeTreeValue = null;
        color = c;
        location = 'X';
        level = 1;
        leftChild = null;
        rightChild = null;
        parent = null;
    }
        

    public Vertex getVertexTreeValue() {
        return vertexTreeValue;
    }

    public Edge getEdgeTreeValue() {
        return edgeTreeValue;
    }

    public void setVertexTreeValue(Vertex v) {
        vertexTreeValue = v;
        return;
    }

    public void setEdgeTreeValue(Edge e) {
        edgeTreeValue = e;
        return;
    }

    public char getColor() {
        return color;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setColor(char c) {
        color = c;
        return;
    }

    public char getLocation() {
        return location;
    }

    public void setLocation(char c) {
        location = c;
        return;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int i) {
        level = i;
        return;
    }

    public boolean isLeaf() {
        if(leftChild == null && rightChild == null)
            return true;
        else
            return false;
    }

    public static void setNodeToNull(TreeNode nulledOut, RBTree sT) {
        if(nulledOut.location == 'L') {
            nulledOut.parent.leftChild = null;
            nulledOut = null;
        }
        else if(nulledOut.location == 'R') {
            nulledOut.parent.rightChild = null;
            nulledOut = null;
        }
        else {
            sT.setRoot(null);
        }

    }
    
    private static void increaseLevelOnSubTree(TreeNode start) {
        if(start == null)
            return;
        start.level++;;
        if(start.leftChild != null)
            increaseLevelOnSubTree(start.getLeftChild());
        if(start.rightChild != null)
            increaseLevelOnSubTree(start.getRightChild());
        return;
    }

    private static void decreaseLevelOnSubTree(TreeNode start) {
        if(start == null)
            return;
        start.level--;
        if(start.leftChild != null)
            decreaseLevelOnSubTree(start.getLeftChild());
        if(start.rightChild != null)
            decreaseLevelOnSubTree(start.getRightChild());
        return;
    }

    public static void rotateRight(TreeNode x, RBTree sT) {
        TreeNode temp = x.rightChild;
        increaseLevelOnSubTree(x.getParent().getRightChild());
        decreaseLevelOnSubTree(x.getParent().getLeftChild());
        if(temp != null)
            temp.location = 'L';
        if(x.parent == sT.getRoot())
            sT.setRoot(x);
        x.rightChild = x.parent;
        x.rightChild.level++;
        x.location = x.rightChild.location;
        x.parent = x.rightChild.parent;
        if(x.parent != null) {
            if(x.rightChild.location == 'L')
                x.parent.leftChild = x;
            else
                x.parent.rightChild = x;
        }
        x.rightChild.location = 'R';
        x.rightChild.leftChild = temp;
        x.rightChild.parent = x;
        if(x.rightChild.leftChild != null) {
            x.rightChild.leftChild.parent = x.rightChild;
            increaseLevelOnSubTree(x.rightChild.leftChild);
        }
        return;
    }
    
    public static void rotateLeft(TreeNode x, RBTree sT) {
        TreeNode temp = x.leftChild;
        increaseLevelOnSubTree(x.getParent().getLeftChild());
        decreaseLevelOnSubTree(x.getParent().getRightChild());
        if(temp != null)
            temp.location = 'R';
        if(x.parent == sT.getRoot()) {
            sT.setRoot(x);
        }
        x.leftChild = x.parent;
        x.leftChild.level++;
        x.location = x.leftChild.location;
        x.parent = x.leftChild.parent;
        if(x.parent != null) {
            if(x.leftChild.location == 'L')
                x.parent.leftChild = x;
            else
                x.parent.rightChild = x;
        }
        x.leftChild.location = 'L';
        x.leftChild.rightChild = temp;
        x.leftChild.parent = x;
        if(x.leftChild.rightChild != null) {
            x.leftChild.rightChild.parent = x.leftChild;
            increaseLevelOnSubTree(x.leftChild.rightChild);
        }
        return;
    }

    public void insertionFixUp(TreeNode x, RBTree sT) {
        TreeNode uncle = new TreeNode('b');
        while(true) {
            if(x == sT.getRoot())
                break;
            if(x.parent.color == 'b')
                break;
            if(x.parent.location == 'L')
                uncle = x.parent.parent.rightChild;
            if(x.parent.location == 'R')
                uncle = x.parent.parent.leftChild;


            if(uncle != null && uncle.color == 'r') {
                x.parent.color = 'b';
                uncle.color = 'b';
                x.parent.parent.color = 'r';
                x = x.parent.parent;
            }

            else {
                if(x.parent.parent != null) {
                    if(x.parent.parent.location == 'X' && x.location != x.parent.location) {
                        if(x.location == 'R') {
                            rotateLeft(x, sT);
                            x = x.leftChild;
                        }
                        else {
                            rotateRight(x, sT);
                            x = x.rightChild;
                        }
                    }
                    if(x.location != x.parent.location) {
                        if(x.location == 'R') {
                            rotateLeft(x, sT);
                            x = x.leftChild;
                        }
                        else {
                            rotateRight(x, sT);
                            x = x.rightChild;
                        }
                    }
                    x.parent.color = 'b';
                    x.parent.parent.color = 'r';
                    if(x.parent.location == 'R')
                        rotateLeft(x.parent, sT);
                    else
                        rotateRight(x.parent, sT);
                }
            }
        }
        sT.setRootColor('b');
        return;
    }

}
    
