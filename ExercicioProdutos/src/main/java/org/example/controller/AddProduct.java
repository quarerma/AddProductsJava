package org.example.controller;

import org.example.view.ShowScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProduct implements ActionListener {
    private ShowScreen screen;

    public AddProduct(ShowScreen screen) {
        this.screen = screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            screen.addProduct();
    }
}
