package es.unex.gexcall.tarea2;


public class Dibujo {
	private Integer dibujo;
	private String tipo;
	
	public Dibujo(Integer ruta, String tipo){
		dibujo=ruta;
		this.tipo=tipo;
	}
	
	public Integer getDibujo(){
		return dibujo;
	}
	
	public String getTipo(){
		return tipo;
	}
	
}
