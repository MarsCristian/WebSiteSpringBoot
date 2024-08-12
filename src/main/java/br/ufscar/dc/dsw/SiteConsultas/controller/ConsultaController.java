package br.ufscar.dc.dsw.SiteConsultas.controller;




import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;
import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import br.ufscar.dc.dsw.SiteConsultas.exception.HorarioDuplicadoException;
import br.ufscar.dc.dsw.SiteConsultas.service.IConsultaService;

import br.ufscar.dc.dsw.SiteConsultas.service.IMedicoService;
import br.ufscar.dc.dsw.SiteConsultas.service.IPacienteService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private IConsultaService service;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IMedicoService medicoService;

    @GetMapping("/cadastrar")
    public String cadastrar(Consulta consulta, ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Aqui, username é o email

        // Verificar se o usuário tem a role ROLE_Admin
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Admin"));
        boolean isPaciente = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Paciente"));
        boolean isMedico = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Medico"));
        if(isAdmin) {
            model.addAttribute("medicos", medicoService.buscarTodos());
            model.addAttribute("pacientes", pacienteService.buscarTodos());
        }
        else if(isPaciente) {
            model.addAttribute("medicos", medicoService.buscarTodos());
            model.addAttribute("pacientes", pacienteService.buscarPorNome(username));
        }
        else if(isMedico) {
            model.addAttribute("medicos", medicoService.buscarPorNome(username));
            model.addAttribute("pacientes", pacienteService.buscarTodos());
        }

        return "consulta/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();// Aqui, username é o email

        System.out.println(authentication.getClass());

        // Verificar se o usuário tem a role ROLE_Admin
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Admin"));
        boolean isPaciente = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Paciente"));
        boolean isMedico = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_Medico"));

        //Adm
        if(isAdmin)
        {
            model.addAttribute("medicos", medicoService.buscarTodos());
            model.addAttribute("pacientes", pacienteService.buscarTodos());
            model.addAttribute("consultas",service.buscarTodos());
        }
        else if(isPaciente)
        {
            // Buscar o paciente pelo username (email)
            Paciente paciente = pacienteService.buscarPorNome(username);

            model.addAttribute("medicos", medicoService.buscarTodos());
            model.addAttribute("pacientes", pacienteService.buscarPorNome(username));
            model.addAttribute("consultas",service.buscarPorPaciente(paciente));

        } else if (isMedico) {
            // Buscar o paciente pelo username (email)
            Medico medico = medicoService.buscarPorNome(username);

            model.addAttribute("medicos", medicoService.buscarPorNome(username));
            model.addAttribute("pacientes", pacienteService.buscarTodos());
            model.addAttribute("consultas",service.buscarPorMedico(medico));

        }

        return "consulta/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {
        System.out.println(consulta.getConsultaKey());
        System.out.println(consulta.getDataHora());
        System.out.println(consulta.getMedico().getNome());

        if (result.hasErrors()) {
            System.out.println("Erros de validação encontrados: " + result.getAllErrors());
            return "consulta/cadastro";
        }

        service.salvar(consulta);
        attr.addFlashAttribute("sucess", "consulta inserido com sucesso.");
        return "redirect:/consultas/listar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("consulta", service.buscarPorId(id));
        model.addAttribute("medicos", medicoService.buscarTodos());
        model.addAttribute("pacientes", pacienteService.buscarTodos());
        return "consulta/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Consulta consulta, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "consulta/cadastro";
        }


        try {
            service.salvar(consulta);
        } catch (HorarioDuplicadoException e) {
            result.rejectValue("dataHora", "error.consulta", e.getMessage());
            return "consulta/cadastro";
        }

        attr.addFlashAttribute("success", "Consulta inserida com sucesso.");
        return "redirect:/consultas/listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, ModelMap model) {
//        if (service.consultaTemConsultas(id)) {
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