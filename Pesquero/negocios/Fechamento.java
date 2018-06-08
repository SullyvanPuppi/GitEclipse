package negocios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Fechamento {

	public Fechamento() {
		super();
	}

	private String mesReferente;

	public String getMesReferente() {
		return mesReferente;
	}

	public int validaMes(String referencia, String hoje){
		String dtReferencia;
		int x = 0;

		if(referencia.equals("__/____")){
			x = 1;
		}else{
			DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");

			Date dataReferente = null;
			Date dataHoje = null;

			if(Integer.parseInt((String) referencia.subSequence(0, 2))==02){
				dtReferencia = ""+28+"/"+referencia;
			}else{
				dtReferencia = ""+30+"/"+referencia;
			}		
			try {
				dataReferente = sdf.parse(dtReferencia);
				dataHoje = sdf2.parse(hoje);
			} catch (Exception e) {}

			Calendar date1 = new GregorianCalendar();
			Calendar date2 = new GregorianCalendar();

			date1.setTime(dataReferente);
			date2.setTime(dataHoje);

			if (date2.before(date1)) {
				x = 1;
			}
		}
		return x;
	}
	public String getHoje(){
		String data = "";
		//Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		int dia = today.get(Calendar.DAY_OF_MONTH);

		int mes = today.get(Calendar.MONTH)+1; 

		int ano = today.get(Calendar.YEAR);

		if(dia<10){
			if(mes<10){
				data = "0"+dia+"0"+mes+ano;
			}else{
				data = "0"+dia+mes+ano;
			}
		}else{
			if(mes<10){
				data = ""+dia+"0"+mes+ano;
			}else{
				data = ""+dia+mes+ano;
			}
		}

		return data;
	}


}
