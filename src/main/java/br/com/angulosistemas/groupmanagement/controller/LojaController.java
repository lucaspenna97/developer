package br.com.angulosistemas.groupmanagement.controller;

import br.com.angulosistemas.groupmanagement.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LojaController {

    @Autowired
    private LojaService lojaService;


    @PostMapping(value = "/insertLoja", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String insertLoja(@RequestBody String loja){
        return lojaService.insertLoja(loja);
    }


    @GetMapping("/getLoja")
    @ResponseBody
    public String getLoja(@RequestParam(name = "id_cliente", required = false, defaultValue = "") String id_cliente,
                          @RequestParam(name = "cnpj", required = false, defaultValue = "") String cnpj){
        return lojaService.getLoja(id_cliente, cnpj);
    }


    @DeleteMapping("/deleteLoja")
    @ResponseBody
    public String deleteLoja(@RequestParam(name = "id_cliente", required = false, defaultValue = "") String id_cliente,
                              @RequestParam(name = "cnpj", required = false, defaultValue = "") String cnpj){
        return lojaService.deleteLoja(id_cliente, cnpj);
    }


    @PutMapping("/updateLoja")
    @ResponseBody
    public String updateLoja(@RequestBody String loja){
        return lojaService.updateLoja(loja);
    }

}
