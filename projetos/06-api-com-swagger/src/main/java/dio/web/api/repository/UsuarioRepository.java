package dio.web.api.repository;

import dio.web.api.handler.BusinessException;
import dio.web.api.handler.CampoObrigatorioException;
import dio.web.api.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    public void save(Usuario usuario){
        if(usuario.getLogin() == null)
            throw new CampoObrigatorioException("login");

        if(usuario.getPassword() == null)
            throw new CampoObrigatorioException("password");

        if (usuario.getId() == null)
            System.out.println("Save - Recebendo o usuário na camada de repositorio");
        else
            System.out.println("Update - Recebendo usuário na camada repositorio");

        System.out.println(usuario);
    }

    public void deleteById(Integer id){
        System.out.println(String.format("Delete/id - Recebendo o ID: %d para excluir um usuário", id));
        System.out.println(id);
    }

    public List<Usuario> findAll(){
        System.out.println("List - Listando os usuários do sistema");
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Josué", "password"));
        usuarios.add(new Usuario("Elaine", "masterpass"));
        return usuarios;
    }

    public Usuario findById(Integer id){
        System.out.println(String.format("Find/id - Recebendo o id: %d para localizar", id));
        return new Usuario("Josué", "password");
    }

    public Usuario findByUserName(String userName){
        System.out.println(String.format("Find/username - Recebendo o userName: %s " +
                "para locanizar um usuário", userName));
        return new Usuario("Josué", "password");
    }
}
