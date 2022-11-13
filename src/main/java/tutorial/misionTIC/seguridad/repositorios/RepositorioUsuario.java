package tutorial.misionTIC.seguridad.repositorios;

import org.springframework.data.mongodb.repository.Query;
import tutorial.misionTIC.seguridad.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario  extends MongoRepository<Usuario,String>{

    @Query("{'correo' : ?0}")
    public Usuario findByEmail(String correo);


}
