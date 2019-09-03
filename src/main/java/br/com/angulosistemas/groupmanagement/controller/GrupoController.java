package br.com.angulosistemas.groupmanagement.controller;

import br.com.angulosistemas.groupmanagement.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping(value = "/createGrupo", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String createGrupo(@RequestBody String grupo){
        return grupoService.createGrupo(grupo);
    }

    @GetMapping("/getGrupo")
    @ResponseBody
    public String getGrupo(@RequestParam(name = "id_grupo", required = false, defaultValue = "") String id_grupo){
        return grupoService.getGrupo(id_grupo);
    }

    @GetMapping("/getGrupos")
    @ResponseBody
    public String getGrupos(){ return grupoService.getGrupos(); }

    @DeleteMapping("/deleteGrupo")
    @ResponseBody
    public String deleteGrupo(@RequestParam(name = "id_grupo", required = false, defaultValue = "") String id_grupo){
        return grupoService.deleteGrupo(id_grupo);
    }

    @PutMapping("/updateGrupo")
    @ResponseBody
    public String updateGrupo(@RequestBody String grupo){
        return grupoService.updateGrupo(grupo);
    }

}
