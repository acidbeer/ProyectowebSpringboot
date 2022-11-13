package tutorial.misionTIC.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tutorial.misionTIC.seguridad.modelos.PermisosRol;

public interface RepositorioPermisosRol  extends MongoRepository<PermisosRol, String> {

    @Query("{'rol.$_id': ObjectId(?0), 'permiso.$_id': ObjectId(?1)}")
    PermisosRol findByRolAndPermiso(String idRol, String idPermiso);


}
