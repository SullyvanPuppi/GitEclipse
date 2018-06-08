package negocios;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import persistencia.CadastroSeguranca;

public class Seguranca {

	public String selecionaCaminho(){

		String retorno = "";

		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		int r = chooser.showOpenDialog(new JFrame());
		if (r == JFileChooser.APPROVE_OPTION){
			retorno = chooser.getSelectedFile().getAbsolutePath();
		}
		return retorno;
	}
	Object tbEmpresasClientes[] = new  Object[1000];
	public Object[] getTbEmpresasClientes(){
			return (Object[]) tbEmpresasClientes;
	}	
	public void geraBackupClientes(){
		CadastroSeguranca gerar = new CadastroSeguranca();
		gerar.geraBackupTbCliente();
		int i =0;
		while(i<=gerar.getBacContCliente()){
			this.tbEmpresasClientes[i] = gerar.getTbEmpresasClientes()[i];	
		}
	}
	
}