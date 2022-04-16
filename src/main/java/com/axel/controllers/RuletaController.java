package com.axel.controllers;

import com.axel.entities.Ruleta;
import com.axel.services.RuletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RuletaController {
    @Autowired
    private RuletaService ruletaService;

    @GetMapping("/play")
    public ModelAndView play(
            @RequestParam(value = "betTypes") String gameType,
            @RequestParam(value = "_betTypes") String _getBetTypes,
            @RequestParam(value = "betOption") String getBetOption,
            @RequestParam(value = "money") Integer getMoney) {
        Ruleta ruleta = ruletaService.getRuleta();
        ruletaService.play(gameType, getBetOption, getMoney);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("ruleta", ruleta);
        modelAndView.addObject("longType", ruletaService.getLongType());
        modelAndView.addObject("betType", ruletaService.getBetType());
        modelAndView.addObject("dinero", ruletaService.getDinero());
        modelAndView.addObject("betMoney", ruletaService.getBetMoney());
        modelAndView.addObject("isWinner", ruletaService.getIsWinner());
        modelAndView.addObject("isLoser", ruletaService.getIsLoser());
        modelAndView.addObject("parar", ruletaService.getParar());
        modelAndView.addObject("winnerNumber", ruletaService.getCorrectNumber());
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("ruleta", ruletaService.getRuleta());
        modelAndView.addObject("longType", ruletaService.getLongType());
        modelAndView.addObject("betType", ruletaService.getBetType());
        modelAndView.addObject("dinero", ruletaService.getDinero());
        return modelAndView;
    }

    @GetMapping("/reset")
    public ModelAndView reset(){
        ruletaService.reset();

        return index();
    }
}
