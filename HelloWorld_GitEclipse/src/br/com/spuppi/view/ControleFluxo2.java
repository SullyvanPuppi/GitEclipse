package br.com.spuppi.view;

public class ControleFluxo2 {

	public static void main(String[] args) {
		byte dia = 1;
		for (;dia<=7;dia++) {
			if(dia==1 || dia == 7 ) {
				System.out.println("[If]Dia " + dia + ", é final de semana!");
			}else {
				System.out.println("[If]Dia " + dia + ", é dia útil!");
			}
			switch(dia) {
			case 1: case 7:
				System.out.println("[Switch]Dia " + dia + ", é final de semana!");
			case 2: case 3: case 4: case 5: case 6:
				System.out.println("[Switch]Dia " + dia + ", é dia útil!");
			}
		}
	}

}
