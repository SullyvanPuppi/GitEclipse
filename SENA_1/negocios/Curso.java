/**
 * 
 * Autor: Sullyvan Puppi
 * 
 * Classe responsável pela lógica ligada a cursos.
 *  
 */
package negocios;

//Imports de classes internas necessárias
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import persistencia.CadastroCurso;

/* Importando classe da camada de persistência responsável pela
 * comunicação com o banco de dados.
 */


public class Curso {

	//Atributo que armazenará os items em branco
	private String status = "";
	
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
		
	/**----------Construtor--------------**/
	public Curso(){
		super();
	}
	//---------Método para ajustar dados para cadastro
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
	public String getDsComponente(){
		return dsComponente;
	}
	public String getStatus() {
		return status;
	}
	//--------Validação dos dados para cadastro de curso--------------//
	public int validarCurso(){
		int x=0;
		this.status ="";
		if(getNmCurso().equals("")){
			this.status = "\nNome do curso inválido.";
			x+=1;
		}
		if(getNmTipo().equals("----------")){
			this.status += "\nTipo do curso inválido.";
			x+=1;
		}
		if(getNmArea().equals("----------")){
			this.status += "\nÁrea de atuação do curso inválido.";
			x+=1;
		}
		if(getQtCiclos()==0){
			this.status += "\nQuantidade de ciclos do curso inválido.";
			x+=1;
		}
		if(getQtDuracao().equals("----------")){
			this.status += "\nDuração dos ciclos do curso inválido.";
			x+=1;
		}
		if(getNmCoordenador().equals("")){
			this.status += "\nNome do coordenador do curso inválido.";
			x+=1;
		}
		return x;
	}
	//--------Validação dos dados para cadastro de ciclos
	public int validarCiclo(){
		int x = 0;
		this.status = "";
		if(getNmCiclo().equals("")){
			this.status +="\nTítulo do ciclo do curso inválido.";
			x+=1;
		}
		if(getCdCiclo()==0){
			this.status += "\nOrdenação do ciclo no curso inválido.";
			x+=1;
		}
		return x;
	}
	//---------Validação dos dados para adicionar componente ao curso
	public int validarComponente(){
		int x = 0;
		this.status = "";
		if(getNmComponente().equals("----------")){
			this.status += "\nNome do componente inválido.";
			x+=1;
		}
		if(getCdCicloComponente()==0){
			this.status += "\nCódigo de ordenação do ciclo para o componente inválido.";
			x+=1;
		}
		return x;
	}
	//------Comandos para a camada de persistência--------------------------------//	
	CadastroCurso consultar = new CadastroCurso();
	//Métodos de controle para cadastro
	public int getExisteCurso(){
		CadastroCurso consultar = new CadastroCurso();
		consultar.verificaExiste(getNmCurso());
		return consultar.getExiste();
	}
	public int getExisteCiclo(){
		CadastroCurso consultar = new CadastroCurso();
		consultar.verificaExisteCiclo(getNmCurso(), getCdCiclo());
		return consultar.getExisteCiclo();
	}
	public int getExisteComponente(){
		CadastroCurso consultar = new CadastroCurso();
		consultar.verificaExisteComponente(getNmCurso(), getNmComponente(), getCdCicloComponente());
		return consultar.getExisteComponente();
	}
	//----------Cadastrar------------//
	//---Cadastrar curso
	public void cadastrarCurso(){
		CadastroCurso cadastrar = new CadastroCurso();
		cadastrar.cadastrarCurso(getNmCurso(), getNmTipo(), getNmArea(), getQtCiclos(), getQtDuracao(), getNmCoordenador(), getDsCurso());
	}
	//---------Ciclo
	public void cadastrarCiclo(){
		CadastroCurso cadastrar = new CadastroCurso();
		cadastrar.cadastrarCiclo(getNmCurso(), getCdCiclo(), getNmCiclo(), getDsCiclo());
	}
	//--------Componente
	public void cadastrarComponente(){
		CadastroCurso cadastrar = new CadastroCurso();
		cadastrar.ajustaNmCiclo(getNmCurso(), getCdCicloComponente());
		cadastrar.cadastrarComponenteCurso(getNmComponente(), getCdCicloComponente(), getNmCurso(), getDsComponente());
	}
	//----Atributos e métodos de pesquisa
	//--Tabela cursos
	DefaultTableModel tabela = new DefaultTableModel();
	
	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	//--Tabela ciclos
	DefaultTableModel tabelaCiclos = new DefaultTableModel();
		
	public DefaultTableModel getTabelaCiclos(){
		return this.tabelaCiclos;
	}
	public void consultarCiclos(String nmCurso){
		consultar.consultarCiclos(nmCurso);
		this.tabelaCiclos = consultar.getTabelaCiclos();
	}
	//--Tabela ciclo componentes
	DefaultTableModel tabelaCicloComponentes = new DefaultTableModel();
	
	public DefaultTableModel getTabelaCicloComponentes(){
		return this.tabelaCicloComponentes;
	}
	public void consultarCicloComponentes(String nmCurso, String cdCiclo){
		consultar.consultarCicloComponentes(nmCurso, cdCiclo);
		this.tabelaCicloComponentes = consultar.getTabelaCicloComponentes();
	}
	//--Tabela curso componentes
	DefaultTableModel tabelaCursoComponentes = new DefaultTableModel();
	
	public DefaultTableModel getTabelaCursoComponentes(){
		return this.tabelaCursoComponentes;
	}
	public void consultarCursoComponentes(String nmCurso){
		consultar.consultarCursoComponentes(nmCurso);
		this.tabelaCursoComponentes = consultar.getTabelaCursoComponentes();
	}
	//----Consultar componentes para exibir
	public Vector consultarComponentes(){
		consultar.consultarCmbComponentes();
		return consultar.getComponentes();
	}
	//--Consultar componente no curso para mostrar descrição
	public void consultarDsComponenteCurso(String nmCurso, String nmComponente, String cdCiclo){
		consultar.consultarDsComponente(nmCurso, nmComponente, cdCiclo);
		this.dsComponente = consultar.getDsComponente();
	}	
	public void consultarDsComponenteCurso(String nmComponente){
		consultar.consultarDsComponente(nmComponente);
		this.dsComponente = consultar.getDsComponente();
	}
	//---Consultar ciclo para mostrar descrição
	public void consultarDsCiclo(String nmCurso, String cdCiclo){
		consultar.consultarDsCiclo(nmCurso, cdCiclo);
		this.dsCiclo = consultar.getDsCiclo();
	}
	//----Consultar cadastro
	public void consultar(String nome, String tipo, String area){
		consultar.consultar(nome, tipo, area);
		this.tabela = consultar.getTabela();
	}
	//----Consultar para exibir
	public void consultar(String nmCurso){
		consultar.consultar(nmCurso);
		this.nmCurso = consultar.getNmCurso();
		this.nmTipo = consultar.getNmTipo();
		this.nmArea = consultar.getNmArea();
		this.qtCiclos = consultar.getQtCiclos();
		this.qtDuracao = consultar.getQtDuracao();
		this.nmCoordenador = consultar.getNmCoordenador();
		this.dsCurso = consultar.getDsCurso();
	}
	//---------------Remover dados-----------------//
	//----Remover curso
	public void removerCurso(String nmCurso){
		consultar.removerCurso(nmCurso);
	}
	//----Remover ciclo
	public void removerCiclo(String nmCurso, String cdCiclo){
		consultar.removerCiclo(nmCurso, cdCiclo);
	}
	//----Remover componente curricular
	public void removerComponente(String nmCurso, String nmComponente, String cdCiclo){
		consultar.removerComponente(nmCurso, nmComponente, cdCiclo);
	}
	
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
