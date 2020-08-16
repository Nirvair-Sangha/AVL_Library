package com.company;

public class AVLTree
{

    private AVLNode root;

    public AVLTree()
    {
        root = null;
    }

    public void insert(String isbn, Book book)
    {
        root = insert(isbn, book, root);
    }

    private int height(AVLNode node)
    {
        if (node != null)
        {
            return node.height;//give height
        }
        else
        {
            return -1;//if empty
        }
    }

    private int max(int l, int r)
    {
        if (r > l)
        {
            return r;
        }
        else
        {
            return l;
        }
    }

    private int compare(String s, String t)
    {
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)!='-')//make sure to skip over dash for ISBN
            {
                if(s.charAt(i) > t.charAt(i))
                {
                    return 1;//if first string greater
                }

                else if (t.charAt(i) > s.charAt(i))
                {
                    return 0;//if second string greater
                }
            }
        }
        return 0;
    }

    private AVLNode insert(String isbn, Book book, AVLNode node)
    {
        if (node == null)
        {
            node = new AVLNode(isbn, book);//Create a new node if the tree is empty.
        }

        else if (compare(node.key, isbn) == 1)
        {
            node.leftPtr = insert(isbn, book, node.leftPtr);

            if (height(node.leftPtr) - height(node.rightPtr) == 2)
            {
                System.out.print("Imbalance occurred at inserting ISBN " + isbn);
                if (compare(node.leftPtr.key, isbn) == 1)
                {
                    System.out.println("; fixed in left Rotation");
                    node = rotateWithLeftChild(node);
                }
                else
                {
                    System.out.println("; fixed in RightLeft Rotation");
                    node = doubleWithLeftChild(node);
                }
            }
        }
        else if (compare(node.key, isbn) == 0)
        {
//Call the method again with the right subtree.
            node.rightPtr = insert(isbn, book, node.rightPtr);
//Check for imbalance and show the error message.
            if (height(node.rightPtr) - height(node.leftPtr) == 2)
            {
                System.out.print("Imbalance occurred at inserting ISBN " + isbn);
//Compare the key value to check the imbalance.
//if (Integer.parseInt(isbn) > Integer.parseInt(top.rightPtr.key))
                if (compare(node.rightPtr.key, isbn) == 0)
                {
//Display the message and fix the imbalance.
                    System.out.println("; fixed in Right Rotation");
                    node = rotateWithRightChild(node);
                }
                else
                {
//Display the message and fix the imbalance.
                    System.out.println("; fixed in LeftRight Rotation");
                    node = doubleWithRightChild(node);
                }
            }
        }

        node.height = max(height(node.leftPtr), height(node.rightPtr)) + 1;//get height of node

        return node;
    }

    //Define the method to rotate with left sub tree.
    private AVLNode rotateWithLeftChild(AVLNode node)
    {
        AVLNode aNode = node.leftPtr;//rotate tree based on the node
        node.leftPtr = aNode.rightPtr;
        aNode.rightPtr = node;

        node.height = max(height(node.leftPtr), height(node.rightPtr)) + 1;//get height of given node
        aNode.height = max(height(aNode.leftPtr), node.height) + 1;

        return aNode;//return new node
    }

    //Define the method to rotate with right sub tree.
    private AVLNode rotateWithRightChild(AVLNode node)
    {
        AVLNode aNode = node.rightPtr;
        node.rightPtr = aNode.leftPtr;
        aNode.leftPtr = node;

        node.height = max(height(node.leftPtr), height(node.rightPtr)) + 1;
        aNode.height = max(height(aNode.rightPtr), node.height) + 1;

        return aNode;
    }

    private AVLNode doubleWithLeftChild(AVLNode node)
    {
        node.leftPtr = rotateWithRightChild(node.leftPtr);//rotate right with left subtree

        return rotateWithLeftChild(node);
    }

//Define the method doubleWithRightChild().

    private AVLNode doubleWithRightChild(AVLNode node)
    {
        node.rightPtr = rotateWithLeftChild(node.rightPtr);

        return rotateWithRightChild(node);
    }

    public void inorder()
    {
        inorder(root);//calls inorder to print
    }

    private void inorder(AVLNode node)
    {
        if (node != null)//Check if the node is empty or not.
        {
            inorder(node.leftPtr);//call to left subtree
            System.out.print(node.key + " ");
            inorder(node.rightPtr);//call to right subtree
        }
    }

}
