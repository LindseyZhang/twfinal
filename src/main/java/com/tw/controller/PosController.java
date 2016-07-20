package com.tw.controller;

//import com.tw.service.PosService;

import com.tw.service.compute.ComputeService;
import com.tw.service.compute.computeImpl.ComputeServiceImpl;
import com.tw.service.input.InputService;
import com.tw.service.input.inputsImpl.InputServiceImple;
import com.tw.service.ouputService.OutputService;
import com.tw.service.ouputService.outputImpl.OutputServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pos-api")
public class PosController {




    @RequestMapping(value = "receipts", method = RequestMethod.POST)
    @ResponseBody
    public String generateReceiptFromBarcodes(HttpServletResponse response, @RequestBody String inputs) {
        InputService inputService = new InputServiceImple();
        ComputeService computeService = new ComputeServiceImpl();
        OutputService outputService = new OutputServiceImpl();

        String receipt = outputService.getOutput(computeService.computePromotion(inputService.transferStringToList(inputs)));
        System.out.println(receipt);
        response.setContentType("charset=utf-8");
        return receipt;
    }
}
