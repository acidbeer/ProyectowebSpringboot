package tutorial.misionTIC.seguridad.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tutorial.misionTIC.seguridad.modelos.PermisosRol;

public interface RepositorioPermisosRol  extends MongoRepository<PermisosRol, String> {

    @Query("{'rol.$id': ObjectId(?0), 'permiso.$id': ObjectId(?1)}")
    public PermisosRol findByRolAndPermiso(String idRol, String idPermiso);


}
