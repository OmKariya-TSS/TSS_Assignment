package com.tss.springcore.controller;

import com.tss.springcore.model.HardDisk;
import com.tss.springcore.service.IHardDiskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class HardDiskController {
    private IHardDiskService hardDiskService;

    public HardDiskController(IHardDiskService hardDiskService) {
        this.hardDiskService = hardDiskService;
    }
    @GetMapping("/harddisk")
    public HardDisk getHardDisk(){
        return hardDiskService.getHarddisk();
    }

}
