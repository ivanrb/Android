package es.unex.gexcall.tarea10;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class CargaDatosWS {
	public String getProvincia(String provincia, String municipio){
		String respuesta = null;
		SoapObject rcp; //Objeto para llamar al WS
		
		rcp = new SoapObject("http://www.catastro.meh.es/","Provincia");
		rcp.addProperty("Provincia",provincia);
		rcp.addProperty("Municipio",municipio);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rcp;
		envelope.dotNet = true;
		envelope.encodingStyle = SoapSerializationEnvelope.XSD;
		
		HttpTransportSE androidHttpTransport = null;
		try{
			String conexion = "http://ovc.catastro.meh.es/ovcservweb/OVCSWLocalizacionRC/OVCCallejero.asmx?op=ConsultaMunicipio";
			androidHttpTransport = new HttpTransportSE(conexion);
			androidHttpTransport.debug = true;
			androidHttpTransport.call("http://tempuri.org/OVCServWeb/OVCCallejero/ConsultaMunicipio", envelope);
			respuesta = envelope.getResponse().toString();
		} catch (Exception e){
			respuesta = e.getMessage();
		}
		
		return respuesta;
	}
	
	public String getMunicipio(String municipio){
		String respuesta = null;
		return respuesta;
	}

}
