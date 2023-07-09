package pe.gob.minjus.sisarb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.minjus.sisarb.model.domain.TipoParametro;
import pe.gob.minjus.sisarb.model.request.TipoParametroRequest;
import pe.gob.minjus.sisarb.model.response.Respuesta;
import pe.gob.minjus.sisarb.service.TipoParametroService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tipoparametro")
public class TipoParametroController {


    @Autowired
    TipoParametroService tipoParametroService;

    @PostMapping("list")
    public ResponseEntity<Respuesta<List<TipoParametro>>> listTipoParametros(@Valid @RequestBody  TipoParametroRequest request){
        return tipoParametroService.listarTiposParametros(request);
    }
    
    @GetMapping("listAll")
    public ResponseEntity<Respuesta<List<TipoParametro>>> listTipoParametrosActivos(){
        return tipoParametroService.listarTiposParametrosActivos();
    }
    
    @PostMapping("insert")
    public ResponseEntity<Respuesta<TipoParametro>> insertTipoParametro(@Valid @RequestBody  TipoParametro request)  {
        return tipoParametroService.insertTiposParametros(request);
    }
    
    @PutMapping("update")
    public ResponseEntity<Respuesta<TipoParametro>> updateTipoParametro(@Valid @RequestBody  TipoParametro request)  {
        return tipoParametroService.updateTiposParametros(request);
    }
    
    @DeleteMapping("delete")
    public ResponseEntity<Respuesta<TipoParametro>> eliminarTipoParametro(@RequestBody TipoParametro request){
        return tipoParametroService.deleteTiposParametros(request);
    }
}
