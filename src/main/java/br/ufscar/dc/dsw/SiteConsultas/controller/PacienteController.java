package br.ufscar.dc.dsw.SiteConsultas.controller;



import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import br.ufscar.dc.dsw.SiteConsultas.service.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Paciente paciente) {
        return "paciente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("pacientes",service.buscarTodos());
        return "paciente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "paciente/cadastro";
        }

        service.salvar(paciente);
        attr.addFlashAttribute("sucess", "paciente inserido com sucesso.");
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("paciente", service.buscarPorId(id));
        return "paciente/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "paciente/cadastro";
        }

        service.salvar(paciente);
        attr.addFlashAttribute("sucess", "paciente editado com sucesso.");
        return "redirect:/pacientes/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
//        if (service.pacienteTemConsultas(id)) {
//            model.addAttribute("fail", "Editora não excluída. Possui livro(s) vinculado(s).");
//        }
        if (1==2) {
            model.addAttribute("fail", "Editora não excluída. Possui livro(s) vinculado(s).");
        } else {
            service.excluir(id);
            model.addAttribute("sucess", "Editora excluída com sucesso.");
        }
        return listar(model);
    }
}