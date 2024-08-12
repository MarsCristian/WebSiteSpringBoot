package br.ufscar.dc.dsw.SiteConsultas.controller;



import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import br.ufscar.dc.dsw.SiteConsultas.service.IPacienteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Aqui, username é o email

        // Verificar se o usuário tem a role ROLE_Admin
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Admin"));

        if (isAdmin) {
            // Se for admin, buscar todos os pacientes
            model.addAttribute("pacientes", service.buscarTodos());
        } else {
            // Se não for admin, buscar pacientes com base no nome
            model.addAttribute("pacientes", service.buscarPorNome(username));
        }

        return "paciente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Paciente paciente, BindingResult result, RedirectAttributes attr) {
       // paciente.setPapel("ROLE_Paciente");
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
        paciente.setPapel("ROLE_Paciente");
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