package tesis.playon.restful.controller;

import java.util.Calendar;

import javax.annotation.Resource;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tesis.playon.restful.domain.Comentario;
import tesis.playon.restful.domain.Comentarios;
import tesis.playon.restful.domain.Playa;
import tesis.playon.restful.domain.ResponseJson;
import tesis.playon.restful.domain.Usuario;
import tesis.playon.restful.service.IComentarioService;

@Controller("comentarioController")
public class ComentarioController {

    private static final String COMENTARIO_ENTITY_JSON = "comentario-entity";

    private static final String PLAYA_JSON = "playa";

    private static final String USUARIO_JSON = "usuario";

    private static final String COMENTARIO_JSON = "comentario";

    private static final String SUCCESS_CODE = "1003";

    private static final String ERROR_CODE = "1004";

    private static final String SUCCESSFUL_OPERATION = "Successful, comment.";

    private static final String ERROR_OPERATION = "Error, comment.";

    @Resource(name = "comentarioService")
    private IComentarioService comentarioService;

    @RequestMapping(value = "/comentarios", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Comentarios getTarifas() {
	Comentarios result = new Comentarios();
	result.setLista(comentarioService.findAll());
	return result;
    }

    @RequestMapping(value = "/comentarios/{nombreComercialPlaya}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    Comentarios getPlayaNombre(@PathVariable("nombreComercialPlaya") String nombreComercialPlaya) {
	Comentarios result = new Comentarios();
	result.setLista(comentarioService.findByPlaya(nombreComercialPlaya));
	return result;
    }

    @RequestMapping(value = "/comentarionuevo", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    ResponseJson saveComentario(@RequestBody String body) {

	ResponseJson response = new ResponseJson();

	try {
	    JSONObject mObj = new JSONObject(body);
	    JSONObject mComentario = mObj.getJSONObject(COMENTARIO_ENTITY_JSON);

	    Usuario usuario = new Usuario();
	    usuario.setId(mComentario.getInt(USUARIO_JSON));
	    Playa playa = new Playa();
	    playa.setId(mComentario.getInt(PLAYA_JSON));
	    Comentario comentario = new Comentario();
	    comentario.setComentario(mComentario.getString(COMENTARIO_JSON));
	    comentario.setFecha(Calendar.getInstance().getTime());
	    comentario.setHabilitado(true);
	    comentario.setUsuario(usuario);
	    comentario.setPlaya(playa);
	    comentarioService.save(comentario);

	    response.setCode(SUCCESS_CODE);
	    response.setSuccess(true);
	    response.setMessage(SUCCESSFUL_OPERATION);

	} catch (JSONException e) {
	    response.setCode(ERROR_CODE);
	    response.setSuccess(false);
	    response.setMessage(ERROR_OPERATION);

	    e.printStackTrace();
	}

	return response;
    }
}