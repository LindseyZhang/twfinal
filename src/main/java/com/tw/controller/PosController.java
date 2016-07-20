package com.tw.controller;

import com.tw.service.compute.computeImpl.ComputeServiceImpl;
import com.tw.service.input.inputsImpl.InputServiceImple;
import com.tw.service.ouputService.outputImpl.OutputServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pos-api")
public class PosController {
    static Logger logger = Logger.getLogger (OutputServiceImpl.class.getName());
    @Autowired
    public InputServiceImple inputService;

    @Autowired
    public ComputeServiceImpl computeService;

    @Autowired
    public OutputServiceImpl outputService;


    @RequestMapping(value = "receipts", method = RequestMethod.POST)
    @ResponseBody
    public String generateReceiptFromBarcodes(HttpServletResponse response, @RequestBody String inputs) {
        logger.info("request body :" +inputs);
        String receipt = outputService.getOutput(computeService.computePromotion(inputService.transferStringToList(inputs)));
        System.out.println(receipt);
        response.setContentType("charset=utf-8");
        return receipt;
    }
}
