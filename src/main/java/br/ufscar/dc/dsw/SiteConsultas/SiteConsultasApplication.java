package br.ufscar.dc.dsw.SiteConsultas;

import br.ufscar.dc.dsw.SiteConsultas.dao.IMedicoDAO;
import br.ufscar.dc.dsw.SiteConsultas.dao.IPacienteDAO;
import br.ufscar.dc.dsw.SiteConsultas.domain.Medico;
import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SiteConsultasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiteConsultasApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IMedicoDAO medicoDAO, IPacienteDAO pacienteDAO) {
		return args -> {
			Medico medico = new Medico();
			medico.setEmail("maria@gmail.com");
			medico.setSenha("123456");
			medico.setCrm("123.456");
			medico.setNome("Maria");
			medico.setEspecialidade("Cardiologia");
			medicoDAO.save(medico);

			Paciente paciente = new Paciente();
			paciente.setEmail("sergio@gmail.com");
			paciente.setSenha("123456");
			paciente.setCpf("000.000.000-00");
			paciente.setNome("Sergio");
			paciente.setTelefone("(16)99169-9169");
			paciente.setSexo("masculino");
			paciente.setDataNascimento("08/08/2008");
			pacienteDAO.save(paciente);

		};
	}

}
