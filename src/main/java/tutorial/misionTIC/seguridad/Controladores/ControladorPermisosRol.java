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
        log.info("Buscando todos los permisos roles en la base de datos....");
        return miRepositoriopermisosRol.findAll();
    }

    //Relacion de muchos a muchos
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

    @GetMapping("{idPermisosRol}")
    public PermisosRol show(@PathVariable String idPermisosRol){
        PermisosRol permisosRolActual= this.miRepositoriopermisosRol
                .findById(idPermisosRol)
                .orElse(null);
        return permisosRolActual;
    }

    @PutMapping("{idPermisos_rol}/rol/{idRol}/permiso/{idPermiso}")
    public PermisosRol modificarPermisosRol(@PathVariable String idPermisos_rol, @PathVariable String idRol,
    @PathVariable String idPermiso){
        PermisosRol permisoRolActual= this.miRepositoriopermisosRol.findById(idPermisos_rol).orElse(null);
        Rol elRol=this.miRepositorioRol.findById(idRol).get();
        Permiso elPermiso=this.mirepositorioPermiso.findById(idPermiso).get();

        if(permisoRolActual != null && elPermiso != null && elRol != null){
            permisoRolActual.setPermiso(elPermiso);
            permisoRolActual.setRol(elRol);

            return this.miRepositoriopermisosRol.save(permisoRolActual);
        }else{
            return null;
        }


    }


    @DeleteMapping("{idPermisos_rol}")
    public void eliminar(@PathVariable String idPermisos_rol){
        PermisosRol permisoRolActual=this.miRepositoriopermisosRol.findById(idPermisos_rol).orElse(null);

        if(permisoRolActual != null){
            this.miRepositoriopermisosRol.delete(permisoRolActual);

        }

    }

}
