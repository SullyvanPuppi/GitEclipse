package com.spuppi.dao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.spuppi.model.Fatura;

public class FaturaDAO {
	
	public List<Fatura> buscarFaturasVencidas(){
		//Seria buscado no banco de dados
		Fatura f1 = new Fatura("joao@jose.com", 350.0, LocalDate.now().minusDays(3));
		Fatura f2 = new Fatura("maria@jose.com", 150.0, LocalDate.now().minusDays(2));
		Fatura f3 = new Fatura("jose@jose.com", 200.0, LocalDate.now().minusDays(5));
		
		return Arrays.asList(f1, f2, f3);
	}

}
