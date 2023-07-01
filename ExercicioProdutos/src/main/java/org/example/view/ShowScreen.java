package org.example.view;

import org.example.Exception.NegativeQuantityException;
import org.example.Exception.NegativeValueException;
import org.example.Exception.NothingSelectedException;
import org.example.controller.AddProduct;
import org.example.controller.CloneProduct;
import org.example.controller.RemoveProduct;
import org.example.model.Product;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ShowScreen {
    private JFrame screen;
    private final int WIDTH = 500;
    private final int HEIGHT = 200;
    private final int V_GAP = 10;
    private final int H_GAP = 5;


    private JTextField tfName;
    private JTextField tfValue;
    private JTextField tfQuantity;
    private JTextField tfDescription;

    private JList<Product> jlProducts;

    public void draw(){

        screen = new JFrame("Produtos");

        screen.setSize(WIDTH, HEIGHT);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setVisible(true);
        screen.setLayout(new BorderLayout());

        drawList();
        drawForms();

        screen.pack();
    }

    private void drawList(){

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Products"));
        painel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        painel.setLayout(new BorderLayout());

        DefaultListModel<Product> model = new DefaultListModel<>();


        jlProducts = new JList<>(model);

        painel.add(new JScrollPane(jlProducts), BorderLayout.CENTER);

        screen.getContentPane().add(painel, BorderLayout.EAST);
    }

    private void drawForms(){
        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Produtos: "));


        JPanel formulario = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP,V_GAP));
        painelLabel.add(new JLabel("Nome"));
        painelLabel.add(new JLabel("Valor"));
        painelLabel.add(new JLabel("Quantidade"));
        painelLabel.add(new JLabel("Descrição"));


        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0,1, H_GAP,V_GAP));
        tfName = new JTextField(20);
        tfValue = new JTextField(20);
        tfQuantity = new JTextField(20);
        tfDescription = new JTextField(20);

        painelField.add(tfName);
        painelField.add(tfValue);
        painelField.add(tfQuantity);
        painelField.add(tfDescription);


        formulario.add(painelLabel);
        formulario.add(painelField);

        painel.setLayout(new BorderLayout());
        painel.add(formulario, BorderLayout.WEST);

        JButton btnAdd = new JButton("Adicionar");
        btnAdd.addActionListener(new AddProduct(this));

        JButton btnRemove = new JButton("Remover");
        btnRemove.addActionListener(new RemoveProduct(this));

        JButton btnClone = new JButton("Clonar");
        btnClone.addActionListener(new CloneProduct(this));

        JPanel botoes = new JPanel();
        botoes.add(btnAdd);
        botoes.add(btnRemove);
        botoes.add(btnClone);

        painel.add(botoes, BorderLayout.SOUTH);

        screen.getContentPane().add(painel, BorderLayout.WEST);
    }

    public void addProduct(){
        DefaultListModel<Product> model = (DefaultListModel<Product>)jlProducts.getModel();

        try {
            String name = tfName.getText();
            double value = Integer.parseInt(tfValue.getText());
            int quantity = Integer.parseInt(tfQuantity.getText());
            String description = tfDescription.getText();
            Product product = new Product(name, value, quantity, description);
            model.addElement(product);
        }
        catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Valor inválido");
        }
        catch (NegativeValueException e) {
            JOptionPane.showMessageDialog(null, "Não é possível Valor Negativo ou Nulo");
        } catch (NegativeQuantityException e) {
            JOptionPane.showMessageDialog(null, "Não é possível Quantidade Negativa ou Nula");
        }
    }

    public void checkIndex(int index) throws NothingSelectedException {
        if(index == -1) throw new NothingSelectedException();
    }
    public void removeProduct(){
        int selectedIndex = jlProducts.getSelectedIndex();

        try {
            checkIndex(selectedIndex);
            DefaultListModel<Product> model = (DefaultListModel<Product>)jlProducts.getModel();
            model.remove(selectedIndex);

        } catch (NothingSelectedException e) {
            JOptionPane.showMessageDialog(null, "Nenhum elemento selecionado");
        }
    }

    public void cloneProduct(){
        int selectedIndex = jlProducts.getSelectedIndex();
        try {
            checkIndex(selectedIndex);
            DefaultListModel<Product> model = (DefaultListModel<Product>)jlProducts.getModel();
            Product product = model.elementAt(selectedIndex);
            model.addElement(product);

        } catch (NothingSelectedException e) {
            JOptionPane.showMessageDialog(null, "Nenhum elemento selecionado");
        }
    }


}
