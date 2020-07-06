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

    private Integer id;
   // Alumno idAuxiliar = alumnoService.findOne(id);

    Materia auxMateria = new Materia();
    @RequestMapping("/cursadas")
    public ModelAndView initMain(@RequestParam(value = "codigo") Integer codigo) {
        ModelAndView mav = new ModelAndView();
        List<Materia> materias = null;
        Alumno alumno = new Alumno();

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


    @PostMapping("/guardarMateria")
    public ModelAndView insertarMateria(@Valid @ModelAttribute Materia materia,BindingResult br, @RequestParam(value = "codigo") Integer codigo){
        ModelAndView mav = new ModelAndView();
        List<CatalogoMateria> catalogo = null;
        Alumno alumno = null;


        if(br.hasErrors()){

            System.out.println("estoy en el if de error");
            System.out.println(materia.getCatalogoMateria().getCodigoCatalogo().toString());
            System.out.println(materia.getYear().toString());
            System.out.println(materia.getCicle().toString());
            System.out.println(materia.getNotaMateria().toString());
            System.out.println(br.toString());
            System.out.println(codigo);
            System.out.println(alumnoService.findOne(codigo).toString());

            try {
                System.out.println("lleno de nuevo al catalogo");
                catalogo = catalogoMateriaService.findAll();
                alumno = alumnoService.findOne(codigo);


            }catch (Exception e){
                e.printStackTrace();
                System.out.println();
                System.out.println("estoy en el catch");
            }
            System.out.println("estoy fuera del catch");
            mav.addObject("alumno", alumno);
            mav.addObject("cat", catalogo);
            mav.setViewName("guardarMateria");
        }else{
            if (materia.getNotaMateria() >6){
                materia.setResultado("APROBADO");
            }else {
                materia.setResultado("REPROBADO");
            }
            List<Materia> materias= null;

            try {
                materias = materiaService.findMateriasAlumno(codigo);
                alumno = alumnoService.findOne(codigo);
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("estoy en el else para guardar materia");
            materia.setAlumnoMateria(alumno);
            materiaService.save(materia);
            mav.addObject("alumno", alumno);
            mav.addObject("mat", materias);
            mav.setViewName("listMateria");
        }

        return  mav;
    }

    @RequestMapping("/editarMateria")
    public ModelAndView editar(@RequestParam(value = "codigo") Integer codigo, @RequestParam(value = "codigoEstudiante") Integer codigoE){
        ModelAndView mav = new ModelAndView();
        List<CatalogoMateria> catalogo = null;
        List<Materia> materias = null;
        Materia materia = null;
        Alumno alumno = null;

        try {
            catalogo = catalogoMateriaService.findAll();
            //materias = materiaService.findMateriasAlumno(codigoE);
            alumno = alumnoService.findOne(codigoE);
            materia = materiaService.findOne(codigo);
        }catch (Exception e){
            e.printStackTrace();
        }

        mav.addObject("materia", materia);
        mav.addObject("alumno", alumno);
        //mav.addObject("materia", materias);
        mav.addObject("cat", catalogo);
        mav.setViewName("editarMateria");

        return mav;
    }

}
