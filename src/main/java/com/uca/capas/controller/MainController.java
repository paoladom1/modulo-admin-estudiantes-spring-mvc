package com.uca.capas.controller;

import com.uca.capas.domain.*;
import com.uca.capas.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;

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
    private AuthorityService tipoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private CatalogoMateriaService catMateriaService;

    @RequestMapping("/login")
    public ModelAndView initMain(@ModelAttribute Usuario usuario) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("usuario", usuario);
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/default")
    public ModelAndView defaultAfterLogin(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Collection<? extends GrantedAuthority> authorities;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        authorities = auth.getAuthorities();
        String myRole = authorities.toArray()[0].toString();
        String coord = "COORD";
        String admin = "ADMIN";

        if (myRole.equals(coord)) {
            List<String> criterios = Arrays.asList("Nombres", "Apellidos");
            mav.addObject("criterios", criterios);
            mav.setViewName("busquedaAlumno");
        } else if (myRole.equals(admin)) {
            mav.setViewName("inicioAdministrador");
        }

        return mav;
    }

    @RequestMapping("/registro")
    public ModelAndView registrar() {
        ModelAndView mav = new ModelAndView();
        String authority = "COORD";
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;

        try {
            departamentos = departamentoService.findAll();
            municipios = municipioService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("duplicate", false);
        mav.addObject("authority", authority);
        mav.addObject("departamentos", departamentos);
        mav.addObject("municipios", municipios);

        mav.addObject("usuario", new Usuario());
        mav.setViewName("registro");

        return mav;
    }

    @PostMapping("/guardarUsuario")
    public ModelAndView guardarUsuario(@RequestParam(value = "authority") String authority, @Valid @ModelAttribute Usuario usuario, BindingResult br) throws ParseException {
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        ModelAndView mav = new ModelAndView();
        if (br.hasErrors()) {
            try {
                departamentos = departamentoService.findAll();
                municipios = municipioService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.setViewName("registro");
        } else {
            Authorities role = new Authorities(usuario.getNombreUsuario(), authority);

            usuario.setEdad(usuario.getEdadDelegate());
            if (usuarioService.findByNombreUsuario(usuario.getNombreUsuario()) != null) {
                mav.addObject("duplicate", true);
                try {
                    departamentos = departamentoService.findAll();
                    municipios = municipioService.findAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mav.addObject("departamentos", departamentos);
                mav.addObject("municipios", municipios);
                mav.setViewName("registro");
                return mav;
            }


            usuarioService.save(usuario);
            tipoService.save(role);
            mav.setViewName("login");
        }

        return mav;
    }

    @RequestMapping("/coordinador")
    public ModelAndView coordinador() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/inicioCoordinador");
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

    @PostMapping("/buscar")
    public ModelAndView buscarExpediente(@RequestParam(value = "busqueda") Integer busqueda, @RequestParam(value = "cadena") String cadena) {
        ModelAndView mav = new ModelAndView();
        List<Alumno> alumnos = null;
        List<List<Materia>> materiasAlumnos = new ArrayList<>();
        List<Integer> aprobadasAlumnos = new ArrayList<>();
        List<Integer> reprobadasAlumnos = new ArrayList<>();
        List<Double> promedioAlumnos = new ArrayList<>();

        System.out.println(busqueda);
        if (busqueda == 0) {

            try {
                alumnos = alumnoService.findByNombres(cadena);
                alumnos.forEach(alumno -> materiasAlumnos.add(materiaService.findMateriasAlumno(alumno.getCodigoEstudiante())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (busqueda == 1) {
            try {
                alumnos = alumnoService.findByApellidos(cadena);
                alumnos.forEach(alumno -> materiasAlumnos.add(materiaService.findMateriasAlumno(alumno.getCodigoEstudiante())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (List<Materia> materiasAlumno : materiasAlumnos) {
            int aprobadas = 0;
            int reprobadas = 0;
            double promedio = 0;
            double suma = 0;
            for (Materia materia : materiasAlumno) {
                if (materia.getResultado().equals("APROBADO")) {
                    aprobadas++;
                } else {
                    reprobadas++;
                }

                if (materiasAlumno.size() != 0) {
                    suma = suma + Double.valueOf(materia.getNotaMateria());
                } else {
                    suma = 0;
                }
            }

            double prom = 0;

            if (suma != 0) {
                promedio = suma / materiasAlumno.size();
                BigDecimal bd = new BigDecimal(promedio).setScale(2, RoundingMode.HALF_UP);
                prom = bd.doubleValue();
            } else {
                prom = 0;
            }

            promedioAlumnos.add(prom);
            aprobadasAlumnos.add(aprobadas);
            reprobadasAlumnos.add(reprobadas);

        }

        mav.addObject("aprobadas", aprobadasAlumnos);
        mav.addObject("reprobadas", reprobadasAlumnos);
        mav.addObject("promedio", promedioAlumnos);
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
            alumno.setEdad(alumno.getEdadDelegate());
            alumnoService.save(alumno);
            mav.addObject("criterios", criterios);
            mav.addObject("mensaje", mensaje);
            mav.setViewName("busquedaAlumno");
        }

        return mav;
    }

    @RequestMapping("/catalogoCentrosE")
    public ModelAndView catalogoCentrosE() {
        ModelAndView model = new ModelAndView();
        List<Institucion> centros = null;
        try {
            centros = institucionService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("centros", centros);
        model.setViewName("CatalogoCentrosEscolares");
        return model;
    }

    @RequestMapping("/catalogoMateria")
    public ModelAndView catalogoMateria() {
        ModelAndView model = new ModelAndView();
        List<CatalogoMateria> materias = null;
        try {
            materias = catMateriaService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("materias", materias);
        model.setViewName("CatalogoMateria");
        return model;
    }

    @RequestMapping("/catalogoUsuarios")
    public ModelAndView catalogoUsuarios() {
        ModelAndView model = new ModelAndView();
        List<Usuario> usuarios = null;
        try {
            usuarios = usuarioService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("usuarios", usuarios);
        model.setViewName("CatalogoUsuarios");
        return model;
    }

    @RequestMapping("/menuAdministrador")
    public ModelAndView menuAdmin() {
        ModelAndView model = new ModelAndView();
        model.setViewName("inicioAdministrador");
        return model;
    }

    @RequestMapping("/agregarCatMateria")
    public ModelAndView agregarCatMateria() {
        ModelAndView model = new ModelAndView();
        model.addObject("materia", new CatalogoMateria());
        model.setViewName("NuevoCatMateria");
        return model;
    }

    @RequestMapping("/agregarCatUsuario")
    public ModelAndView agregarCatUsuario() {
        ModelAndView model = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        String authority = "COORD";

        try {
            departamentos = departamentoService.findAll();
            municipios = municipioService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("duplicate", false);
        model.addObject("authority", authority);
        model.addObject("usuario", new Usuario());
        model.addObject("municipios", municipios);
        model.addObject("departamentos", departamentos);
        model.setViewName("NuevoCatUsuario");
        return model;
    }

    @RequestMapping("/agregarCatCentro")
    public ModelAndView agregarCatCentro() {
        ModelAndView model = new ModelAndView();
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;

        try {
            departamentos = departamentoService.findAll();
            municipios = municipioService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("departamentos", departamentos);
        model.addObject("municipios", municipios);
        model.addObject("centro", new Institucion());
        model.setViewName("NuevoCatCentro");
        return model;
    }

    @RequestMapping("/saveCatMateria")
    public ModelAndView saveCatMateria(@ModelAttribute CatalogoMateria materia ) {
    	ModelAndView model = new ModelAndView();
    	List<CatalogoMateria> materias =null;
    	
    	try {
    		catMateriaService.save(materia);
    		materias = catMateriaService.findAll();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	model.addObject("materias",materias);    	
    	model.setViewName("CatalogoMateria");
    	return model;
    }

    @RequestMapping("/saveCatCentro")
    public ModelAndView saveCatCentro(@ModelAttribute Institucion insti ) {
    	ModelAndView model = new ModelAndView();
    	List<Institucion> centros = null;
    	
    	try {
    		institucionService.save(insti);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	try {
    		centros=institucionService.findAll();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	model.addObject("centros",centros);
    	model.setViewName("CatalogoCentrosEscolares");
    	return model;
    }

    @RequestMapping("/saveCatUsuario")
    public ModelAndView guardarCatUsuario(@RequestParam(value = "authority") String authority, @Valid @ModelAttribute Usuario usuario, BindingResult br) throws ParseException {
        List<Departamento> departamentos = null;
        List<Municipio> municipios = null;
        ModelAndView mav = new ModelAndView();
        List<Usuario> usuarios = null;
        if (br.hasErrors()) {
            try {
                departamentos = departamentoService.findAll();
                municipios = municipioService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }

            mav.addObject("departamentos", departamentos);
            mav.addObject("municipios", municipios);
            mav.setViewName("NuevoCatUsuario");
        } else {
            Authorities role = new Authorities(usuario.getNombreUsuario(), authority);

            usuario.setEdad(usuario.getEdadDelegate());
            if (usuarioService.findByNombreUsuario(usuario.getNombreUsuario()) != null) {
                mav.addObject("duplicate", true);
                try {
                    departamentos = departamentoService.findAll();
                    municipios = municipioService.findAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mav.addObject("departamentos", departamentos);
                mav.addObject("municipios", municipios);
                mav.setViewName("NuevoCatUsuario");
                return mav;
            }

            System.out.println("guardar");
            usuarioService.save(usuario);
            tipoService.save(role);
            try {
                usuarios = usuarioService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mav.addObject("usuarios", usuarios);
            mav.setViewName("CatalogoUsuarios");
        }

        return mav;
    }

    @RequestMapping("/editarCatMateria")
    public ModelAndView editarCatMateria(@RequestParam(value = "codigo") Integer codigo) {
        ModelAndView model = new ModelAndView();
        CatalogoMateria materia = null;
        try {
            materia = catMateriaService.findOne(codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("materia", materia);
        model.setViewName("EditarCatMateria");
        return model;
    }

    @RequestMapping("/editarCatalogoInstitucion")
    public ModelAndView editarCatInst(@RequestParam(value = "codigo") Integer codigo) {
        ModelAndView model = new ModelAndView();
        Institucion ins = null;
        List<Municipio> municipios = null;
        List<Departamento> departamentos = null;
        try {
            ins = institucionService.findOne(codigo);
            municipios = municipioService.findAll();
            departamentos = departamentoService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("departamentos", departamentos);
        model.addObject("municipios", municipios);
        model.addObject("centro", ins);
        model.setViewName("EditarCatCentroEs");
        return model;
    }

    @RequestMapping("/editarCatUsuario")
    public ModelAndView editarCatUsuario(@RequestParam(value = "codigo") String nombre) {
        ModelAndView model = new ModelAndView();
        Usuario usr = null;
        List<Municipio> municipios = null;
        List<Departamento> departamentos = null;
        try {
            usr = usuarioService.findByNombreUsuario(nombre);
            municipios = municipioService.findAll();
            departamentos = departamentoService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addObject("departamentos", departamentos);
        model.addObject("municipios", municipios);
        model.addObject("usuario", usr);
        model.setViewName("EditarCatUsuario");
        return model;
    }

    @RequestMapping("/saveEUsuario")
    public ModelAndView saveEUsuario(@Valid @ModelAttribute Usuario usuario, BindingResult br) {
        ModelAndView model = new ModelAndView();
        List<Municipio> municipios = null;
        List<Departamento> departamentos = null;
        List<Usuario> users = null;
        if (br.hasErrors()) {
            try {
                departamentos = departamentoService.findAll();
                municipios = municipioService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }

            model.addObject("departamentos", departamentos);
            model.addObject("municipios", municipios);
            model.setViewName("EditarCatUsuario");
        } else {
            try {
                departamentos = departamentoService.findAll();
                municipios = municipioService.findAll();
                usuario.setEdad(usuario.getEdadDelegate());
                usuarioService.save(usuario);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                users = usuarioService.findAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
            model.addObject("departamentos", departamentos);
            model.addObject("municipios", municipios);
            model.addObject("usuarios", users);
            model.setViewName("CatalogoUsuarios");
        }
        return model;
    }
}
