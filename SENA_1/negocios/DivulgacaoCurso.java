package negocios;

import persistencia.CadastroCurso;

public class DivulgacaoCurso {

	//--Campos para a tabela de curso
	private String nmCurso;
	
	private String nmTipo;
	
	private String nmArea;
	
	private int qtCiclos;
	
	private String qtDuracao;
	
	private String nmCoordenador;
	
	private String dsCurso;
	
	//--Campos para a tabela ciclos
	private int cdCiclo;
	
	private String nmCiclo;
	
	private String dsCiclo;
	
	//--Campos para a tabela CursoComponente
	private String nmComponente;
	
	private int cdCicloComponente;
	
	private String dsComponente;

//	---------Método para ajustar dados para cadastro
	//--Ajustar curso
	public void ajustaDadosCurso(String nmCurso, String nmTipo, String nmArea, int qtCiclos, String qtDuracao, String nmCoordenador, String dsCurso) {
		this.nmCurso = nmCurso;
		this.nmTipo = nmTipo;
		this.nmArea = nmArea;
		this.qtCiclos = qtCiclos;
		this.qtDuracao = qtDuracao;
		this.nmCoordenador = nmCoordenador;
		this.dsCurso = dsCurso;
	}
	//--Ajustar ciclo
	public void ajustaDadosCiclo(String nmCurso, int cdCiclo, String nmCiclo, String dsCiclo){
		this.nmCurso = nmCurso;
		this.cdCiclo = cdCiclo;
		this.nmCiclo = nmCiclo;
		this.dsCiclo = dsCiclo;
	}
	//--Ajustar componente
	public void ajustaDadosComponente(String nmComponente, String dsComponente, int cdCicloComponente, String nmCurso){
		this.nmComponente = nmComponente;
		this.dsComponente = dsComponente;
		this.cdCicloComponente = cdCicloComponente;
		this.nmCurso = nmCurso;
	}	
	//--------------Retornam dados respectivos-------------------------------//
	public int getCdCiclo() {
		return cdCiclo;
	}
	public String getDsCiclo() {
		return dsCiclo;
	}
	public int getCdCicloComponente(){
		return cdCicloComponente;
	}
	public String getDsComponente() {
		return dsComponente;
	}
	public String getNmArea() {
		return nmArea;
	}
	public String getNmCiclo() {
		return nmCiclo;
	}
	public String getNmComponente() {
		return nmComponente;
	}
	public String getNmCurso() {
		return nmCurso;
	}
	public String getNmTipo() {
		return nmTipo;
	}
	public int getQtCiclos() {
		return qtCiclos;
	}
	public String getQtDuracao() {
		return qtDuracao;
	}
	public String getNmCoordenador(){
		return nmCoordenador;
	}
	public String getDsCurso(){
		return dsCurso;
	}
	//-----Dados de divulgacao
	
	
	
	
	//---------------Alterar cadastro----------------------//
	//--Alterar curso
	public void alterarCurso(String nmCurso){
		CadastroCurso alterar = new CadastroCurso();
		alterar.alterarCurso(getNmCurso(), getNmTipo(), getNmArea(), getQtCiclos(), getQtDuracao(), getNmCoordenador(), getDsCurso());
	}
	//--Alterar ciclo
	public void alterarCiclo(){
		CadastroCurso cadastrar = new CadastroCurso();
		cadastrar.alterarCiclo(getNmCurso(), getCdCiclo(), getNmCiclo(), getDsCiclo());
	}
	//--Alterar componente
	public void alterarComponente(){
		CadastroCurso cadastrar = new CadastroCurso();
		cadastrar.ajustaNmCiclo(getNmCurso(), getCdCicloComponente());
		cadastrar.alterarComponente(getNmComponente(), getCdCicloComponente(), getNmCurso(), getDsComponente());
	}
}
