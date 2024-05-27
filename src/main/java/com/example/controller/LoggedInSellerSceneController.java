package com.example.controller;

import com.example.model.Buyer;

public class LoggedInSellerScene {
    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
        instructionLabel.setText("Welcome, " + buyer.getName() + "!");
    }
}
