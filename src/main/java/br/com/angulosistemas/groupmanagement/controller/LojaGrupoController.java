package br.com.angulosistemas.groupmanagement.controller;

import br.com.angulosistemas.groupmanagement.service.LojaGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LojaGrupoController {

    @Autowired
    LojaGrupoService lojaGrupoService;


    @PostMapping(value = "/createLojaGrupo", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createGrupo(@RequestParam(name = "id_grupo", required = true, defaultValue = "") String id_grupo,
                              @RequestParam(name = "id_cliente", required = true, defaultValue = "") String id_loja){
        return lojaGrupoService.createLojaGrupo(id_grupo, id_loja);
    }

    @GetMapping("/getLojasGrupo")
    @ResponseBody
    public String getLojaGrupo(@RequestParam(name = "id_grupo", required = true, defaultValue = "") String id_grupo){
        return lojaGrupoService.getLojasGrupo(id_grupo);
    }

    @GetMapping("/getLojasGrupos")
    @ResponseBody
    public String getLojasGrupos(){ return lojaGrupoService.getLojasGrupos() ; }

    @DeleteMapping("/deleteLojaGrupo")
    @ResponseBody
    public String deleteLojaGrupo(@RequestParam(name = "id_grupo", required = true, defaultValue = "") String id_grupo,
                              @RequestParam(name = "id_cliente", required = true, defaultValue = "") String id_cliente){
        return lojaGrupoService.deleteLojaGrupo(id_grupo, id_cliente);
    }

}
