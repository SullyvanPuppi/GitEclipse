package ajuda;

import java.awt.Dimension;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;

public class CriaHelp{
	public String helpHS = "ajuda/helpset.hs";       //nome do helpset.hs 
	private HelpSet hs; 
	private HelpBroker hb;   

	public CriaHelp() { 
		ClassLoader cl = this.getClass().getClassLoader(); 

		try { 
			//procura caminho do helpset.hs através do ClassLoader 
			URL hsURL = HelpSet.findHelpSet(cl, helpHS);  
			//sabendo o caminho do helpset cria um objeto HelpSet 
			hs = new HelpSet(null, hsURL); 
		} catch (Exception e) { 
			//Se não achar o arquivo helpset informa o erro 
			System.out.println("HelpSet " + e.getMessage()); 
			System.out.println("HelpSet " + helpHS + " não localizado"); 
			return; 
		} 
		// Com o objeto HelpSet criado manda criar o Help. 
		hb = hs.createHelpBroker(); 
		//Exibe o help para o usuário com as dimensões totais da tela 
		hb.setSize(new Dimension(700, 550)); 
		hb.setDisplayed(true); 

	}
}


