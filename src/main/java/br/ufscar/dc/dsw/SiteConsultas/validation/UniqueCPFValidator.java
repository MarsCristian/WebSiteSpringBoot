//package br.ufscar.dc.dsw.SiteConsultas.validation;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//import br.ufscar.dc.dsw.SiteConsultas.dao.IPacienteDAO;
//import br.ufscar.dc.dsw.SiteConsultas.domain.Paciente;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {
//
//    @Autowired
//    private IPacienteDAO dao;
//
//    @Override
//    public void initialize(UniqueCPF constraintAnnotation) {
//
//    }
//
//    @Override
//    public boolean isValid(String CPF, ConstraintValidatorContext context) {
//        if (dao != null) {
//            Paciente paciente = dao.findByCPF(CPF);
//            return paciente == null;
//        } else {
//            // Não necessidade de validação
//            // Durante a execução da classe LivrariaMvcApplication
//            // não há injeção de dependência.
//            return true;
//        }
//    }
//}