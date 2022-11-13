package tutorial.misionTIC.seguridad.repositorios;

import org.springframework.data.mongodb.repository.Query;
import tutorial.misionTIC.seguridad.modelos.Permiso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioPermiso extends MongoRepository<Permiso,String> {

    @Query("{'url': ?0, 'metodo': ?1}")
    public Permiso findByurlAndMethod(String url, String metodo);

}
