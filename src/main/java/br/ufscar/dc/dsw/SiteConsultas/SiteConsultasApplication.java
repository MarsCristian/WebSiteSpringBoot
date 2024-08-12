package br.ufscar.dc.dsw.SiteConsultas;

import br.ufscar.dc.dsw.SiteConsultas.dao.IConsultaDAO;
import br.ufscar.dc.dsw.SiteConsultas.dao.IMedicoDAO;
import br.ufscar.dc.dsw.SiteConsultas.dao.IPacienteDAO;
import br.ufscar.dc.dsw.SiteConsultas.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Consulta;
import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import br.ufscar.dc.dsw.SiteConsultas.domain.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SiteConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteConsultasApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IMedicoDAO medicoDAO, IPacienteDAO pacienteDAO, IConsultaDAO consultaDAO, IUsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder ) {
		return args -> {
			Medico medico = new Medico();
			medico.setEmail("maria@gmail.com");
			medico.setSenha(encoder.encode("123456"));
			medico.setCrm("123.456");
			medico.setNome("Maria");
			medico.setEspecialidade("Cardiologia");
			medico.setPapel("ROLE_Medico");
			medicoDAO.save(medico);

			Paciente paciente = new Paciente();
			paciente.setEmail("sergio@gmail.com");
			paciente.setSenha(encoder.encode("123456"));
			paciente.setCpf("000.000.000-00");
			paciente.setNome("Sergio");
			paciente.setTelefone("(16)99169-9169");
			paciente.setSexo("masculino");
			paciente.setDataNascimento("08/08/2008");
			paciente.setPapel("ROLE_Paciente");
			pacienteDAO.save(paciente);

			Consulta consulta = new Consulta();
			consulta.setDataHora(LocalDateTime.now());
			consulta.setMedico(medico);
			consulta.setPaciente(paciente);
			consulta.setConsultaKey();
			consultaDAO.save(consulta);

			Usuario adm = new Usuario();
			adm.setEmail("admin@mail.com");
			adm.setSenha(encoder.encode("admin"));
			adm.setNome("Admin");
			adm.setPapel("ROLE_Admin");
			usuarioDAO.save(adm);

		};
	}

}
