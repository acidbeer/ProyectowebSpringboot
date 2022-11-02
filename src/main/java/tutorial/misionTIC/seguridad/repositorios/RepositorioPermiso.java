package tutorial.misionTIC.seguridad.repositorios;

import tutorial.misionTIC.seguridad.modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermiso extends MongoRepository<Permiso,String> {

}
