package tutorial.misionTIC.seguridad.repositorios;

import tutorial.misionTIC.seguridad.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario  extends MongoRepository<Usuario,String>{


}
