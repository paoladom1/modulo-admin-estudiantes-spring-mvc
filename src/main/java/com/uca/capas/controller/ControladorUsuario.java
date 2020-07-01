package com.uca.capas.controller;

import com.uca.capas.domain.Departamento;
import com.uca.capas.domain.Municipio;
import com.uca.capas.domain.Tipo;
import com.uca.capas.domain.Usuario;
import com.uca.capas.service.DepartamentoService;
import com.uca.capas.service.MunicipioService;
import com.uca.capas.service.TipoService;
import com.uca.capas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
public class ControladorUsuario {
    @Autowired
    private TipoService tipoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MunicipioService municipioService;

    @Autowired
    private DepartamentoService departamentoService;


    @RequestMapping("/inicio")
    public ModelAndView initMain(@ModelAttribute Usuario usuario) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("usuario", usuario);
        mav.setViewName("login");
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute Usuario usuario, BindingResult br) {
        ModelAndView mav = new ModelAndView();
        Usuario user;
        if(br.hasErrors()) {
            try {
                usuarioService.findByNombreUsuario(usuario);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.addObject("usuario", usuario);
            mav.setViewName("login");
        } else {
            user = usuarioService.findByNombreUsuario(usuario);
            if(user.getTipoUsuario().getTipo() == "Administrador") {
                mav.setViewName("registro");
            } else {
                mav.setViewName("busquedaAlumno");
            }
        }

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
}
