package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.services.CatalogoMateriaService;
import com.uca.capas.services.AlumnoService;
import com.uca.capas.services.MateriaService;
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
    public ModelAndView initMain(@RequestParam(value = "codigo") Integer codigo) {
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;
        Alumno alumno = null;

        try {
            materias = materiaService.findMateriasAlumno(codigo);
            alumno = alumnoService.findOne(codigo);

        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("mat", materias);
        mav.addObject("alumno", alumno);
        mav.setViewName("listMateria");
        return mav;
    }

    @GetMapping("/insertarMateria")
    public ModelAndView nuevaMateria(@RequestParam(value = "codigo") Integer codigo) {
        ModelAndView mav = new ModelAndView();
        List<CatalogoMateria> catalogo = null;
        Alumno alumno = null;

        try {
           catalogo = catalogoMateriaService.findAll();
            alumno = alumnoService.findOne(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("lleno el catalogo");
        mav.addObject("materia", new Materia());
       mav.addObject("cat", catalogo);
        mav.addObject("alumno", alumno);
        mav.setViewName("guardarMateria");
        return mav;
    }


   @PostMapping("/guardarM")
    public ModelAndView insertarMateria(@Valid @ModelAttribute Materia materia, BindingResult br, @RequestParam(value = "codigo") Integer codigo){
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;
       Alumno alumno = null;

       if(br.hasErrors()){
           List<CatalogoMateria> catalogo = null;
           System.out.println("estoy en el if de error");
           try {
               catalogo = catalogoMateriaService.findAll();
           }catch (Exception e){
               e.printStackTrace();
           }
           mav.addObject("cat", catalogo);
           mav.setViewName("guardarMateria");
       }else{
           if (materia.getNotaMateria()>=6.0){
               materia.setResultado("APROBADO");
               System.out.println("estoy en el if de aprobado");

           }else{
               materia.setResultado("REPROBADO");
               System.out.println("estoy en el if de reprobado");
           }
           List<Materia> ma = null;
           try {
               ma = materiaService.findMateriasAlumno(codigo);
           }catch (Exception e){
               e.printStackTrace();
           }
           System.out.println("estoy en el else para guardar materia");
           materiaService.save(materia);
           mav.addObject("materias", ma);
           mav.setViewName("listMateria");

       }
        return  mav;
    }

}
