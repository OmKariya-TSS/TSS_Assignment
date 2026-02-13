package com.tss.lsp.test;

import com.tss.lsp.model.Rectangle;
import com.tss.lsp.model.Square;

import com.tss.lsp.model.Shape;

public class Main {
    public static void main(String[] args) {

        Shape rect = new Rectangle(5, 10);
        Shape square = new Square(10);

        System.out.println(rect.getArea());
        System.out.println(square.getArea());
    }
}
