package com.spuppi.test;

import java.util.List;

import com.spuppi.dao.FaturaDAO;
import com.spuppi.email.EnviaEmail;
import com.spuppi.model.Fatura;

public class Exemplo {
	
	public static void main(String[] args) {
		List<Fatura> faturasVencidas = new FaturaDAO().buscarFaturasVencidas();
		
		EnviaEmail enviaEmail = new EnviaEmail();
		
//		for(Fatura f : faturasVencidas) {
//			enviaEmail.enviar(f.getEmailDevedor(), f.resumoFatura());
//		}
		
//		faturasVencidas.forEach( f -> {//Para chamar mais métodos
//			enviaEmail.enviar(f.getEmailDevedor(), f.resumoFatura());
//		});
		
		faturasVencidas.forEach( f -> enviaEmail.enviar(f.getEmailDevedor(), f.resumoFatura()));

	}

}
