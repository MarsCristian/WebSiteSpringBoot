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

			Medico medico2 = new Medico();
			medico2.setEmail("charles@gmail.com");
			medico2.setSenha(encoder.encode("123456"));
			medico2.setCrm("123.4567");
			medico2.setNome("Charles");
			medico2.setEspecialidade("Oftalmologia");
			medico2.setPapel("ROLE_Medico");
			medicoDAO.save(medico2);

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

			Paciente paciente2 = new Paciente();
			paciente2.setEmail("pedro@mail.com");
			paciente2.setSenha(encoder.encode("123456"));
			paciente2.setCpf("000.000.000-01");
			paciente2.setNome("Pedro");
			paciente2.setTelefone("(16)99129-9169");
			paciente2.setSexo("masculino");
			paciente2.setDataNascimento("08/18/2001");
			paciente2.setPapel("ROLE_Paciente");
			pacienteDAO.save(paciente2);

			Consulta consulta = new Consulta();
			consulta.setDataHora(LocalDateTime.now());
			consulta.setMedico(medico);
			consulta.setPaciente(paciente);
			consulta.setConsultaKey();
			consultaDAO.save(consulta);

//			Consulta consulta2 = new Consulta();
//			consulta2.setDataHora(LocalDateTime.now());
//			consulta2.setMedico(medico2);
//			consulta2.setPaciente(paciente);
//			consulta2.setConsultaKey();
//			consultaDAO.save(consulta2);

			Usuario adm = new Usuario();
			adm.setEmail("admin@mail.com");
			adm.setSenha(encoder.encode("admin"));
			adm.setNome("Admin");
			adm.setPapel("ROLE_Admin");
			usuarioDAO.save(adm);

		};
	}

}
