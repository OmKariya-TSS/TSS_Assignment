package com.tss.springcore.service;

import com.tss.springcore.model.Computer;
import com.tss.springcore.model.HardDisk;
import org.springframework.stereotype.Service;

@Service
public class ComputerSeviceImpl implements IComputerService{
    private Computer computer;

    public ComputerSeviceImpl(Computer computer) {
        this.computer = computer;
    }

    @Override
    public Computer getComputer() {
        return computer;
    }

}
