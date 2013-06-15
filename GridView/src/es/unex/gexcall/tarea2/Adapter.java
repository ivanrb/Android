package es.unex.gexcall.tarea2;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends ArrayAdapter<Dibujo>{

	private LayoutInflater inflater;
	
	public Adapter(Context context, List<Dibujo> imgenes){
		super(context, R.layout.elemento,R.id.texto, imgenes);
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		Dibujo imagenes = (Dibujo) this.getItem(position);
		ImageView imagen;
		TextView texto;
		
		if(convertView==null){
			convertView = inflater.inflate(R.layout.elemento, null);
						
			texto = (TextView) convertView.findViewById(R.id.texto);
			imagen = (ImageView) convertView.findViewById( R.id.imagen );
			
			imagen.setImageResource(imagenes.getDibujo());
			if(imagenes.getTipo()=="Rotacion")
				imagen.setImageLevel(5000);
			
			texto.setText(imagenes.getTipo());
			
			convertView.setTag( new ManejadorObjeto(texto,imagen) );
		}
		else{
			ManejadorObjeto manejadorObjetos = (ManejadorObjeto) convertView.getTag();
	        imagen = manejadorObjetos.getImage() ;
	        texto = manejadorObjetos.getTextView() ;
		}
		imagen.setTag(imagenes);
		return convertView;
	}
	
	public class ManejadorObjeto{
	    private ImageView image ;
	    private TextView textView ;
	    public ManejadorObjeto() {
	    	
	    }
	    public ManejadorObjeto( TextView textView, ImageView image ) {
	      this.image = image ;
	      this.textView = textView ;
	    }
	    public ImageView getImage() {
	      return image;
	    }
	    public void setImage(ImageView image) {
	      this.image = image;
	    }
	    public TextView getTextView() {
	      return textView;
	    }
	    public void setTextView(TextView textView) {
	      this.textView = textView;
	    }    
	  }

}
