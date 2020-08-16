package com.company;

import java.io.*;

public class AVLTreeTester
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("TesterFile.txt"));

        AVLTree tree = new AVLTree();
        String line;

        while ((line = in.readLine()) != null)
        {
            String bookAtt[] = line.split(" ");//separates by spaces
            Book book = new Book(bookAtt[1],bookAtt[2]);//takes the book name and author from array and puts in book
            tree.insert(bookAtt[0], book);//inserts ISBN into tree to check for balance and sends book object to AVLTree class
        }

        in.close();
        System.out.println("\nOutput of ISBN Keys in Order: ");
        tree.inorder();
    }

}


