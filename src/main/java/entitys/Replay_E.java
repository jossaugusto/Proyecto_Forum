package entitys;

import java.util.Date;

public class Replay_E {
    private int id_respuesta;
    private int id_tema;
    private int id_usuario;
    private String contenido;
    private Date fecha_publicacion;
    private boolean es_respuesta_aceptada;
    private int id_respuesta_padre;
    
	public int getId_respuesta() {
		return id_respuesta;
	}
	public void setId_respuesta(int id_respuesta) {
		this.id_respuesta = id_respuesta;
	}
	public int getId_tema() {
		return id_tema;
	}
	public void setId_tema(int id_tema) {
		this.id_tema = id_tema;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFecha_publicacion() {
		return fecha_publicacion;
	}
	public void setFecha_publicacion(Date fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}
	public boolean isEs_respuesta_aceptada() {
		return es_respuesta_aceptada;
	}
	public void setEs_respuesta_aceptada(boolean es_respuesta_aceptada) {
		this.es_respuesta_aceptada = es_respuesta_aceptada;
	}
	public int getId_respuesta_padre() {
		return id_respuesta_padre;
	}
	public void setId_respuesta_padre(int id_respuesta_padre) {
		this.id_respuesta_padre = id_respuesta_padre;
	}
    
    
}
