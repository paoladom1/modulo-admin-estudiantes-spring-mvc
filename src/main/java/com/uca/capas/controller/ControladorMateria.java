package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.service.CatalogoMateriaService;
import com.uca.capas.service.AlumnoService;
import com.uca.capas.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ControladorMateria {

    @Autowired
    private CatalogoMateriaService catalogoMateriaService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private AlumnoService alumnoService;

    @RequestMapping("/cursadas")
    public ModelAndView initMain(@RequestParam(value = "codigo") Integer codigo){
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;
        Alumno alumno = null;

        try{
           materias = materiaService.findMateriasAlumno(codigo);
           alumno = alumnoService.findOne(codigo);

        }catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("mat", materias);
        mav.addObject("alumno", alumno);
        mav.setViewName("listMateria");
        return  mav;
    }

    @GetMapping("/insertarMateria")
    public ModelAndView nuevaMateria(@RequestParam(value = "codigo") Integer codigo){
      ModelAndView mav = new ModelAndView();
      List<CatalogoMateria> catalogo = null;
        Alumno alumno = null;

      try{
          catalogo = catalogoMateriaService.findAll();
          alumno = alumnoService.findOne(codigo);
        }catch (Exception e){
          e.printStackTrace();
      }

      mav.addObject("materia", new Materia());
      mav.addObject("cat", catalogo);
      mav.addObject("alumno", alumno);
      mav.setViewName("guardarMateria");
        return mav;
    }

   @PostMapping("/guardarM")
    public ModelAndView insertarMateria(@Valid @ModelAttribute Materia materia, BindingResult br, @RequestParam(value = "codigo") Integer codigo){
        ModelAndView mav = new ModelAndView();
        List<CatalogoMateria> catalogo = null;
        List<Materia> materias = null;


        if(br.hasErrors()){
            catalogo = catalogoMateriaService.findAll();
            mav.addObject("cat", catalogo);
            mav.setViewName("guardarMateria");
        } else {
            if(materia.getNotaMateria() >= 6){
                materia.setResultado("aprobar");
            }else{
                materia.setResultado("reprobado");
            }

            materiaService.save(materia);
            try {
                materias = materiaService.findMateriasAlumno(codigo);
            }catch (Exception e){
                e.printStackTrace();
            }

            mav.addObject("mat", materias);
            mav.setViewName("listMateria");

        }
        return  mav;
    }





}
