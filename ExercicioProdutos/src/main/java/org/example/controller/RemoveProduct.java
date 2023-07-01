package org.example.controller;

import org.example.view.ShowScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveProduct implements ActionListener {
    private ShowScreen screen;

    public RemoveProduct(ShowScreen screen) {
        this.screen = screen;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        screen.removeProduct();
    }
}
