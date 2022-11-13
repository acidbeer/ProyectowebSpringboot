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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @GetMapping("/listar-permisos")
    public List<PermisosRol> buscarTodosLosPermisosRoles(){
        log.info("Buscando todos los permisos roles en la base de datos....");
        return miRepositoriopermisosRol.findAll();
    }






    //Relacion de muchos a muchos
    @PostMapping("rol/{idRol}/permiso/{idPermiso}")
    public PermisosRol crearPermisosRol(@PathVariable String idRol, @PathVariable String idPermiso) {
        Rol rol = miRepositorioRol
                .findById(idRol)
                .orElseThrow(RuntimeException::new);

        Permiso permiso = mirepositorioPermiso
                .findById(idPermiso)
                .orElseThrow(RuntimeException::new);

        PermisosRol permisosRol = new PermisosRol(rol, permiso);

        return miRepositoriopermisosRol.save(permisosRol);
    }




    /*
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

    }*/

    @GetMapping("validar-permiso/rol/{idRol}")
    public PermisosRol validarPermisosDelRol(@PathVariable String idRol, @RequestBody Permiso infoPermiso,HttpServletResponse response) throws IOException {

        //Buscar en base de datos el rol y permiso
        Rol rolActual = miRepositorioRol.findById(idRol).orElse(null);
        Permiso permisoActual =mirepositorioPermiso.findByurlAndMethod(infoPermiso.getUrl(), infoPermiso.getMetodo());

        //Validar si existe el rol y el permiso en base de datos
        if (rolActual != null && permisoActual != null) {

            String idRolActual = rolActual.get_id();
            String idPermisoActual = permisoActual.get_id();
            log.info("idRolActual: {}, idPermisoActual: {}", idRolActual, idPermisoActual);

            //Buscar en la tabla PermisosRol si el rol tiene asociado el permiso.
            PermisosRol permisosRolActual = miRepositoriopermisosRol.findByRolAndPermiso(idRolActual,idPermisoActual);
            log.info("El permisosRol que encontró en BD fue: {}", permisosRolActual);

            if (permisosRolActual != null) {
                return permisosRolActual;
            } else {
                log.error("NO se encuentra el PermisosRol en base de datos");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return null;
            }
        } else {
            log.error("NO se encuentra el rol o el permiso en base de datos");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }

    }

}
