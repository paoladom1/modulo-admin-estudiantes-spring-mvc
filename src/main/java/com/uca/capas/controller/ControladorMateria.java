package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.service.AlumnoMateriaService;
import com.uca.capas.service.AlumnoService;
import com.uca.capas.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorMateria {

    @Autowired
    private AlumnoMateriaService alumnoMateriaService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private AlumnoService alumnoService;

    @RequestMapping("/cursadas")
    public ModelAndView initMain(@ModelAttribute Alumno alumno){
        ModelAndView mav = new ModelAndView();
        List<AlumnoMateria> materias = null;

        try{
            materias = alumnoMateriaService.findAll();

        }catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("mat", materias);
       //mav.addObject("alumno", am.getAlumno());
        mav.setViewName("listMateria");

        return  mav;
    }

    @RequestMapping("/insertarMateria")
    public ModelAndView nuevaMateria(@ModelAttribute AlumnoMateria am){
      ModelAndView mav = new ModelAndView();
      List<Materia> materias = null;
      List<AlumnoMateria> alumnos = null;
      try {
          materias  = materiaService.findAll();

      }catch (Exception e){
          e.printStackTrace();
      }
      mav.addObject("materias", materias);
      mav.addObject("alumno", am.getAlumno());
      mav.setViewName("guardarMateria");
        return mav;
    }

    @PostMapping("/guardarM")
    public ModelAndView insertarMateria(@Valid @ModelAttribute AlumnoMateria am, BindingResult br){
        ModelAndView mav = new ModelAndView();


        if(br.hasErrors()){
            List<Materia> materias = null;

            try{
                materias = materiaService.findAll();

            }catch (Exception e){
                e.printStackTrace();
            }
            mav.addObject("materias", materias);
           //mav.addObject("alumno", am.getAlumno());
            mav.setViewName("guardarMateria");

        }else{

            alumnoMateriaService.save(am);
            mav.setViewName("listMateria");
        }


        return  mav;
    }





}
