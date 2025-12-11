package org.ut4.gymmanager.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.ut4.gymmanager.model.Rutina;
import org.ut4.gymmanager.model.RutinaEjercicio;
import org.ut4.gymmanager.service.EjercicioService;
import org.ut4.gymmanager.service.RutinaService;

import java.util.List;

@Controller
@RequestMapping("/web/rutinas")
public class RutinaWebController {

    private final RutinaService rutinaService;
    private final EjercicioService ejercicioService;

    public RutinaWebController(RutinaService rutinaService,
                               EjercicioService ejercicioService) {
        this.rutinaService = rutinaService;
        this.ejercicioService = ejercicioService;
    }

    @GetMapping
    public String listdoRutinas(Model model){
        List<Rutina> rutinas = rutinaService.listarTodos();

        model.addAttribute("rutinas" , rutinas);

        return "rutinas-list";
    }

    @GetMapping("/nueva")
    public String nuevaRutina(Model model){
        Rutina r = new Rutina();
        List<String> niveles = List.of("PRINCIPIANTE", "INTERMEDIO", "AVANZADO");

        model.addAttribute("rutina", r);
        model.addAttribute("niveles", niveles);

        return "rutina-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("rutina") Rutina rutina, Model model){
        rutinaService.guardar(rutina);
        return "redirect:/web/rutinas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable long id, Model model){

        Rutina rutina = rutinaService.buscarPorId(id).orElse(null);

        if(rutina != null){
            List<String> niveles = List.of("PRINCIPIANTE", "INTERMEDIO", "AVANZADO");

            model.addAttribute(rutina);
            model.addAttribute("niveles", niveles);

            return "rutina-form";
        }

        return "redirect:/web/rutinas";
    }

    @GetMapping("/{id}/borrar")
    public String eliminar(@PathVariable long id){
        rutinaService.borrarPorId(id);
        return "redirect:/web/rutinas";
    }

    @GetMapping("/{id}")
    public String verDetalleRutina(@PathVariable long id, Model model){
        Rutina rutina = rutinaService.buscarPorId(id).orElse(null);
        if(rutina == null){
            return "redirect:/web/rutinas";
        }
        List<RutinaEjercicio> ejerciciosRutina = rutina.getEjercicios();
        model.addAttribute("rutina", rutina);
        return "rutina-detalle";
    }
}
