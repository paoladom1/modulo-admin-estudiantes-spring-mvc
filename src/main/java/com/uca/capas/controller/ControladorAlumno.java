package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorAlumno {
    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private InstitucionService institucionService;



    @RequestMapping("/expediente")
    public ModelAndView buscar() {
        ModelAndView mav = new ModelAndView();
        List<String> criterios = Arrays.asList("Nombres", "Apellidos");

        mav.addObject("criterios", criterios);
        mav.addObject("alumno", new Alumno());
        mav.setViewName("busquedaAlumno");
        return mav;
    }

    @RequestMapping("/buscar")
    public ModelAndView buscarExpediente(@RequestParam(value = "busqueda") Integer busqueda, @RequestParam (value = "cadena") String cadena) {
        ModelAndView mav = new ModelAndView();
        List<Alumno> alumnos = null;

        System.out.println(busqueda);
        if(busqueda == 0) {

            try {
               alumnos = alumnoService.findByNombres(cadena);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(busqueda == 1) {
            try {
                alumnos = alumnoService.findByApellidos(cadena);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mav.addObject("alumnos", alumnos);
        mav.setViewName("expedienteAlumno");

        return mav;

    }

    @RequestMapping("/editarAlumno")
    public ModelAndView editar(@RequestParam(value = "codigo") Integer codigo) {
        ModelAndView mav = new ModelAndView();

        Alumno alumno = null;
        List<Municipio> municipios = null;
        List<Institucion> centrosEscolares = null;

        try {
            municipios = municipioService.findAll();
            centrosEscolares = institucionService.findAll();
            alumno = alumnoService.findOne(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("municipios", municipios);
        mav.addObject("centrosEscolares", centrosEscolares);

        mav.addObject("alumno", alumno);
        mav.setViewName("editarExpediente");
        return mav;
    }

    @RequestMapping("/agregarExpediente")
    public ModelAndView nuevoExpediente() {
        ModelAndView mav = new ModelAndView();
        List<Municipio> municipios = null;
        List<Institucion> centrosEscolares = null;

        try {
            municipios = municipioService.findAll();
            centrosEscolares = institucionService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("municipios", municipios);
        mav.addObject("centrosEscolares", centrosEscolares);
        mav.addObject("alumno", new Alumno());
        mav.setViewName("nuevoExpediente");

        return mav;
    }

    @PostMapping("/guardarAlumno")
    public ModelAndView guardarAlumno(@Valid @ModelAttribute Alumno alumno, BindingResult br) throws ParseException {
        ModelAndView mav = new ModelAndView();
        if (br.hasErrors()) {
            List<Municipio> municipios = null;
            List<Institucion> centrosEscolares = null;

            try {
                municipios = municipioService.findAll();
                centrosEscolares = institucionService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.addObject("centrosEscolares", centrosEscolares);
            mav.addObject("municipios", municipios);
            mav.setViewName("nuevoExpediente");
        } else {
            Date fechaNacimiento = alumno.getFechaNacimiento();

            alumno.setEdad(alumno.getEdad(fechaNacimiento));
            alumnoService.save(alumno);
            mav.setViewName("busquedaAlumno");
        }

        return mav;
    }
}
