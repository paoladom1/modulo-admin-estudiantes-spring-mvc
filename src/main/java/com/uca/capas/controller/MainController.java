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
public class MainController {
    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private AlumnoService alumnoService;

    @Autowired
    private InstitucionService institucionService;

    @Autowired
    private TipoService tipoService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("/inicio")
    public ModelAndView initMain(@ModelAttribute Usuario usuario) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("usuario", usuario);
        mav.setViewName("login");
        return mav;
    }



    @RequestMapping("/registro")
    public ModelAndView registrar() {
        ModelAndView mav = new ModelAndView();
        List<Tipo> tipos = null;
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;

        try {
            tipos = tipoService.findAll();
            departamentos = departamentoService.findAll();
            municipios = municipioService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("tipos", tipos);
        mav.addObject("departamentos", departamentos);
        mav.addObject("municipios", municipios);

        mav.addObject("usuario", new Usuario());
        mav.setViewName("registro");

        return mav;
    }

    @PostMapping("/guardarUsuario")
    public ModelAndView guardarUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult br) throws ParseException {
        ModelAndView mav = new ModelAndView();
        if (br.hasErrors()) {
            List<Tipo> tipos = null;
            List<Departamento> departamentos = null;
            List<Municipio> municipios = null;

            try {
                tipos = tipoService.findAll();
                departamentos = departamentoService.findAll();
                municipios = municipioService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mav.addObject("tipos", tipos);
            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.setViewName("registro");
        } else {
            Date fechaNacimiento = usuario.getFechaNacimiento();

            usuario.setEdad(usuario.getEdad(fechaNacimiento));
            usuarioService.save(usuario);
            mav.setViewName("login");
        }

        return mav;
    }

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
    public ModelAndView buscarExpediente(@RequestParam(value = "busqueda") Integer busqueda, @RequestParam(value = "cadena") String cadena) {
        ModelAndView mav = new ModelAndView();
        List<Alumno> alumnos = null;

        System.out.println(busqueda);
        if (busqueda == 0) {

            try {
                alumnos = alumnoService.findByNombres(cadena);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (busqueda == 1) {
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
        List<Institucion> centrosEscolares = null;

        try {
            centrosEscolares = institucionService.findAll();
            alumno = alumnoService.findOne(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("centrosEscolares", centrosEscolares);

        mav.addObject("alumno", alumno);
        mav.setViewName("editarExpediente");
        return mav;
    }

    @RequestMapping("/agregarExpediente")
    public ModelAndView nuevoExpediente() {
        ModelAndView mav = new ModelAndView();
        List<Institucion> centrosEscolares = null;

        try {
            centrosEscolares = institucionService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("centrosEscolares", centrosEscolares);
        mav.addObject("alumno", new Alumno());
        mav.setViewName("nuevoExpediente");

        return mav;
    }

    @PostMapping("/guardarAlumno")
    public ModelAndView guardarAlumno(@Valid @ModelAttribute Alumno alumno, BindingResult br) throws ParseException {
        ModelAndView mav = new ModelAndView();
        List<String> criterios = Arrays.asList("Nombres", "Apellidos");
        String mensaje = "Expediente creado con exito";
        if (br.hasErrors()) {
            List<Institucion> centrosEscolares = null;

            try {
                centrosEscolares = institucionService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.addObject("centrosEscolares", centrosEscolares);
            mav.setViewName("nuevoExpediente");
        } else {
            Date fechaNacimiento = alumno.getFechaNacimiento();

            alumno.setEdad(alumno.getEdad(fechaNacimiento));
            alumnoService.save(alumno);
            mav.addObject("criterios", criterios);
            mav.addObject("mensaje", mensaje);
            mav.setViewName("busquedaAlumno");
        }

        return mav;
    }
}
