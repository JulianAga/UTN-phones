package com.utn.phones.controllers;

import com.utn.phones.model.Bill;
import com.utn.phones.model.User;
import com.utn.phones.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping("/")
    public List<Bill> findAll() {
        return this.billService.findAll();
    }
}

