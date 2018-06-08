package negocios;

import javax.swing.table.DefaultTableModel;

import persistencia.CadastroComponente;

public class ComponenteCurricular {

	private String nmComponente;
	
	private String dsComponente;
	
	private String status;

	public String getDsComponente() {
		return dsComponente;
	}

	public String getNmComponente() {
		return nmComponente;
	}
	public String getStatus() {
		return status;
	}
	public void ajustaComponente(String nmComponente, String dsComponente) {
		this.nmComponente = nmComponente;
		this.dsComponente = dsComponente;
	}
	public ComponenteCurricular(){
		super();
	}
	//--Tabela cursos componentes
	CadastroComponente consultar = new CadastroComponente();
	
	DefaultTableModel tabelaComponentes = new DefaultTableModel();

	public DefaultTableModel getTabelaComponentes(){
		return this.tabelaComponentes;
	}
	public void consultarComponenteCurso(String nmComponente){
		consultar.consultarComponenteCurso(nmComponente);
		this.tabelaComponentes = consultar.getTabelaComponenteCurso();
	}
	public void consultarDsComponente(String nmComponente){
		consultar.consultarDsComponente(nmComponente);
		this.dsComponente = consultar.getDsComponente();
	}
	
	//--------Validação dos dados para cadastro de curso--------------//
	public int validarComponente(){
		int x=0;
		this.status ="";
		if(getNmComponente().equals("")){
			this.status = "\nNome do componente curricular inválido.";
			x+=1;
		}
		return x;
	}
	public int getExisteComponente(String nmComponente){
		CadastroComponente consultar = new CadastroComponente();
		consultar.verificaExiste(nmComponente);
		return consultar.getExiste();
	}
	public void cadastrarComponente(){
		CadastroComponente cadastrar = new CadastroComponente();
		cadastrar.cadastrarComponente(getNmComponente(), getDsComponente());
	}
	public void alterarComponente(){
		CadastroComponente cadastrar = new CadastroComponente();
		cadastrar.alterarComponente(getNmComponente(), getDsComponente());
	}
	public void removerComponente(String nmComponente){
		CadastroComponente remover = new CadastroComponente();
		remover.removerComponente(nmComponente);
	}
}
