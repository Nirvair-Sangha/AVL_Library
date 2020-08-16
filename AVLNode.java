package com.company;

public class AVLNode

{
    String key;
    Book value;
    int height;
    AVLNode leftPtr, rightPtr;

    public AVLNode(String isbn, Book book)

    {
        key = isbn;
        leftPtr = null;
        rightPtr = null;
        value = book;
        height = 0;

    }

}