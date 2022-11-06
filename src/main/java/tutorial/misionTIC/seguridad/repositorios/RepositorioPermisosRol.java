package tutorial.misionTIC.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import tutorial.misionTIC.seguridad.modelos.PermisosRol;

public interface RepositorioPermisosRol  extends MongoRepository<PermisosRol, String> {


}
