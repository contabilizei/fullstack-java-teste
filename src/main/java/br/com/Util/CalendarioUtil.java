package br.com.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Classe utilitaria
 * @author marinamontelo
 */
public class CalendarioUtil {
	
	public static Date obterPrimeiroDiaDoMes(Date data){
		Calendar c = obterCalendar(data);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}
	
	public static Date obterUltimoDiaDoMes(Date data){
		Calendar c = obterCalendar(data);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();		
	}
	
	public static Calendar obterCalendar(Date data){
		Calendar c = new GregorianCalendar();
		c.setTime(data);
		return c;
	}
	
	public static Date adicionarDiasAData(Date data,int dias){
		Calendar c = obterCalendar(data);
		c.add(Calendar.DAY_OF_MONTH, dias);
		return c.getTime();
	}

}
