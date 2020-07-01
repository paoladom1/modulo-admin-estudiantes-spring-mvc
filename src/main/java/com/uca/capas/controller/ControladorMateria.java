package com.uca.capas.controller;

import com.uca.capas.domain.Materia;
import com.uca.capas.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorMateria {

    @Autowired
    private MateriaService materiaService;

    @RequestMapping("/cursadas")
    public ModelAndView initMain(){
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;

        try{
            materias = materiaService.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("mat", materias);
        mav.setViewName("listMateria");

        return  mav;
    }

    @GetMapping("/insertarMateria")
    public ModelAndView inicio(){
        ModelAndView mav = new ModelAndView();

        mav.addObject("materia", new Materia());
        mav.setViewName("guardarMateria");

        return mav;
    }

}
