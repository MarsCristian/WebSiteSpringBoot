package br.ufscar.dc.dsw.SiteConsultas.dao;

import br.ufscar.dc.dsw.SiteConsultas.domain.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    Usuario findById(long id);
    Usuario findByEmail(String email);
    Usuario findByNome(String nome);
    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    void deleteById(Long id);

}
