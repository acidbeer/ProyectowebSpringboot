package tutorial.misionTIC.seguridad.repositorios;

import tutorial.misionTIC.seguridad.modelos.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRol extends MongoRepository<Rol,String> {

}
