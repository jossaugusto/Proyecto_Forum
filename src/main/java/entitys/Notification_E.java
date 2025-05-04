package entitys;

import java.util.Date;

public class Notification_E {
    private int id_notificacion;
    private int id_usuario_destino;
    private String tipo_notificacion;
    private String contenido;
    private Date fecha;
    private boolean leida;
    
	public int getId_notificacion() {
		return id_notificacion;
	}
	public void setId_notificacion(int id_notificacion) {
		this.id_notificacion = id_notificacion;
	}
	public int getId_usuario_destino() {
		return id_usuario_destino;
	}
	public void setId_usuario_destino(int id_usuario_destino) {
		this.id_usuario_destino = id_usuario_destino;
	}
	public String getTipo_notificacion() {
		return tipo_notificacion;
	}
	public void setTipo_notificacion(String tipo_notificacion) {
		this.tipo_notificacion = tipo_notificacion;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean getLeida() {
		return leida;
	}
	public void setLeida(boolean leida) {
		this.leida = leida;
	}
    
    
}
