package tutorial.misionTIC.seguridad.Controladores;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tutorial.misionTIC.seguridad.modelos.Permiso;
import tutorial.misionTIC.seguridad.modelos.PermisosRol;
import tutorial.misionTIC.seguridad.modelos.Rol;
import tutorial.misionTIC.seguridad.repositorios.RepositorioPermiso;
import tutorial.misionTIC.seguridad.repositorios.RepositorioPermisosRol;
import tutorial.misionTIC.seguridad.repositorios.RepositorioRol;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/permisos-rol")
public class ControladorPermisosRol {

    @Autowired
    private RepositorioPermisosRol miRepositoriopermisosRol;

    @Autowired
    private RepositorioRol miRepositorioRol;

    @Autowired
    private RepositorioPermiso mirepositorioPermiso;

    @GetMapping
    public List<PermisosRol> buscarTodosLosPermisosRoles(){
        log.info("Buscando todos los permisos roles en bas e de datos");
        return miRepositoriopermisosRol.findAll();
    }

    @PostMapping("rol/{idRol}/permiso/{idPermiso}")
    public PermisosRol crearpermisosRol(@PathVariable String idRol, @PathVariable String idPermiso) {

        PermisosRol nuevo=new PermisosRol();
        Rol elRol=this.miRepositorioRol.findById(idRol).get();
        Permiso elPermiso=this.mirepositorioPermiso.findById(idPermiso).get();

        if(elRol != null && elPermiso != null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return this.miRepositoriopermisosRol.save(nuevo);
        }else{
            return null;
        }

    }

}
