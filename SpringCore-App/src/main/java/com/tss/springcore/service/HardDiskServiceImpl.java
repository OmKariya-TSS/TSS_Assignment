package com.tss.springcore.service;

import com.tss.springcore.model.HardDisk;
import org.springframework.stereotype.Service;

@Service
public class HardDiskServiceImpl implements IHardDiskService{
    private HardDisk hardDisk;

    public HardDiskServiceImpl(HardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    @Override
    public HardDisk getHarddisk() {
        return hardDisk;
    }
}
