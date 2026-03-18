package com.tss.springcore.controller;

import com.tss.springcore.model.Computer;
import com.tss.springcore.service.IComputerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ComputerController {
    private IComputerService computerService;

    public ComputerController(IComputerService computerService) {
        this.computerService = computerService;
    }
    @GetMapping("/computers")
    public Computer getComputer(){
        return computerService.getComputer();
    }
}
