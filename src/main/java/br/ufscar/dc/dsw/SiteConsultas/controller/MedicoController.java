package br.ufscar.dc.dsw.SiteConsultas.controller;



import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.service.IMedicoService;
import javax.validation.Valid;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @GetMapping("/cadastrar")
    public String cadastrar(Medico medico) {
        return "medico/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("medicos",service.buscarTodos());
        return "medico/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {
//        medico.setPapel("ROLE_Medico");
//        System.out.println("ROLE_".concat(medico.getPapel()));
//        System.out.println(medico.getPapel());
//        System.out.println(medico.getId());
//        System.out.println(medico.getEmail());
        if (result.hasErrors()) {
            System.out.println("Erros de validação encontrados: " + result.getAllErrors());
            return "medico/cadastro";
        }
//        System.out.println("ROLE_".concat(medico.getPapel()));
//        System.out.println(medico.getPapel());

        service.salvar(medico);
        attr.addFlashAttribute("sucess", "medico inserido com sucesso.");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", service.buscarPorId(id));
        return "medico/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Medico medico, BindingResult result, RedirectAttributes attr) {
        medico.setPapel("ROLE_Medico");

        if (result.hasErrors()) {
            return "medico/cadastro";
        }

        service.salvar(medico);
        attr.addFlashAttribute("sucess", "medico editado com sucesso.");
        return "redirect:/medicos/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
//        if (service.medicoTemConsultas(id)) {
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