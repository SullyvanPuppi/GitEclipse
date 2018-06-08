package negocios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

//--Importando a classe responsável pela comunicação com o banco de dados
import persistencia.CadastroProfessor;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável pela parte lógica
 * referente a professores.
 * 
 */
public class Professor {


	//-----Tabela professores

	private String cdMatricula;

	private String nmProfessor;

	private String cdCpf;

	private String cdRg;
	
	private String rgOrgao;

	private String sexo;

	private String dtNascimento;

	private String naturalidade;

	private String naturalidadeEstado;

	private String nacionalidade;

	private String endereco;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;

	private String telefone;

	private String email;

	private String nmPai;

	private String nmMae;

	private String dsCaminhoFoto;
	
	private String dtAdmissaoIe;
	
	private String dsCaminhoFotoSenaQuatro;

	//---Tabela ProfessorComponente

	private String nmComponente;

	private String dsComponente;

	private String dtAdmissao;

	//---Tabela usuários do sena dois

	private String nmLogin;

	private String cdSenha;

	/**Construtor**/
	public Professor(){
		super();
	}	
	//------------------Ajusta dados---------------------------------------------------------------//
	//---Ajusta professor
	public void ajustaProfessor(String cdMatricula, String nmProfessor, String cdCpf, String cdRg, String rgOrgao, String sexo, String dtNascimento, String naturalidade, String naturalidadeEstado, String nacionalidade, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String cep, String telefone, String email, String nmPai, String nmMae, String dsCaminhoFoto, String dtAdmissaoIe){
		this.cdMatricula = cdMatricula;
		this.nmProfessor = nmProfessor;
		this.cdCpf = cdCpf;
		this.cdRg = cdRg;
		this.rgOrgao = rgOrgao;
		this.sexo = sexo;
		this.dtNascimento = dtNascimento;
		this.naturalidade = naturalidade;
		this.naturalidadeEstado = naturalidadeEstado;
		this.nacionalidade = nacionalidade;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.nmPai = nmPai;
		this.nmMae = nmMae;
		this.dsCaminhoFoto = dsCaminhoFoto;
		this.dtAdmissaoIe = dtAdmissaoIe; 
	}
	//----Ajusta componentes
	public void ajustaComponente(String dtEntrada, String cdMatricula, String nmProfessor, String nmComponente, String dtAdmissao){
		this.dtAdmissaoIe = dtEntrada;
		this.cdMatricula = cdMatricula;
		this.nmProfessor = nmProfessor;
		this.nmComponente = nmComponente;
		this.dtAdmissao = dtAdmissao;
	}
	//------------------Validação de dados----------------------------------------------------------//
	//Armazena status de validação
	private String status="";
	//Retorna status
	public String getStatus(){
		return this.status;
	}
	//---Valida dados do professor
	public int validarProfessor(){
		int x=0;
		this.status = "";
		if(getNmProfessor().equals("")){
			this.status = "\nNome do professor inválido.";
			x+=1;
		}
		if(getCdCpf().equals("___.___.___-__")){
			this.status += "\nC.P.F. do professor inválido.";
			x+=1;
		}
		if(getCdRg().equals("")){
			this.status += "\nR.G do professor inválido.";
			x+=1;
		}
		if(getRgOrgao().equals("")){
			this.status += "\nOrgão expedidor do R.G do professor inválido.";
			x+=1;
		}
		if(getSexo().equals("----------")){
			this.status += "\nSexo do professor inválido.";
			x+=1;
		}
		if(getDtNascimento().equals("__/__/____")){
			this.status += "\nData de nascimento do professor inválido.";
			x+=1;
		}
		if(getNaturalidade().equals("")){
			this.status += "\nNaturalidade do professor inválido.";
			x+=1;
		}
		if(getNaturalidadeEstado().equals("--")){
			this.status += "\nEstado de naturalidade do professor inválido.";
			x+=1;
		}
		if(getNacionalidade().equals("")){
			this.status += "\nNacionalidade do professor inválido.";
			x+=1;
		}
		if(getEndereco().equals("")){
			this.status += "\nEndereço de residência do professor inválido.";
			x+=1;
		}
		if(getBairro().equals("")){
			this.status += "\nBairro do endereço de residência do professor inválido.";
			x+=1;
		}
		if(getCidade().equals("")){
			this.status += "\nCidade de residência do professor inválido.";
			x+=1;
		}
		if(getEstado().equals("--")){
			this.status += "\nEstado de residência do professor inválido.";
			x+=1;
		}
		if(getCep().equals("__.___-___")){
			this.status += "\nC.E.P de residência do professor inválido.";
			x+=1;
		}
		if(getTelefone().equals("(__)____-____")){
			this.status += "\nTelefone do professor inválido.";
			x+=1;
		}
		if(x==0){
			if(validaDtNasc(getDtNascimento())==1){
				this.status += "\nProfessor menor de idade";
				x+=1;
			}	
		}
		return x;
	}
	//---Valida data de nascimento do professor irá retornar 1 se professor menor de idade
	public int validaDtNasc(String dtNasc){
		int x = 0;
		int age = 0;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascInput = null;

		try {
			dataNascInput= sdf.parse(dtNasc);
		} catch (Exception e) {}
		
		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNascInput);
		

		//Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();
		
		//Obtém a idade baseado no ano
		age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

		dateOfBirth.add(Calendar.YEAR, age);
		if (today.before(dateOfBirth)) {
			age--;
		}
		if(age<18){
			x = 1;
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
	//---Valida da de associação a componente é menor do que a data de entrada do professor na I.E.
	public int validaDataComponente(String dtEntrada, String dtAdmissao){
	 int x = 0;
	 DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataEntrada = null;
		Date dataAdmissao = null;

		try {
			dataEntrada= sdf.parse(dtEntrada);
			dataAdmissao= sdf.parse(dtAdmissao);
		} catch (Exception e) {}
		
		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataEntrada);
		date2.setTime(dataAdmissao);
		
		if (date2.before(date1)) {
			x = 1;
			
		}
		return x;
	}
	//---Valida dados do componente
	public int validarComponente(){
		int x=0;
		this.status = "";
		if(getNmComponente().equals("----------")){
			this.status = "\nComponente curricular inválido..";
			x+=1;
		}
		if(getDtAdmissao().equals("__/__/____")){
			this.status += "\nData de admissão no componente curricular inválido.";
			x+=1;
		}else{
			consultar.verificaComponente(getCdMatricula(), getNmComponente());
			if(consultar.getExisteComponente()==1){
				this.status = "\nProfessor ja ministra este componente curricular";
				x+=1;
			}else{
				if(validaDataComponente(getDtAdmissaoIe(), getDtAdmissao())==1){
					this.status = "\nData de admissão no componente curricular informado é menor\nque a data de entrada do professor na instituição de ensino.";
					x+=1;
				}
			}
		}
		return x;
	}
	//---Verifica se existe professor cadastrado
	public int getExiste(String matricula){
		int x=0;
		CadastroProfessor existe = new CadastroProfessor();
		existe.verificaExisteProfessor(matricula);
		if(existe.getExisteProfessor()==1){
			x=1;
		}
		return x;
	}
	//---Gerar matrícula
	public int getMatriculaNova(){
		CadastroProfessor gerar = new CadastroProfessor();
		gerar.ajustaMatricula();
		return gerar.getMatriculaNova();
	}
	//------------------Cadastrar-------------------------------------------------------------------//
	public void cadastrarProfessor(){
		CadastroProfessor cadastrar = new CadastroProfessor();
		cadastrar.cadastrarProfessor(getCdMatricula(), getNmProfessor(), getCdCpf(), getCdRg(), getRgOrgao(), getSexo(), getDtNascimento(), getNaturalidade(), getNaturalidadeEstado(), getNacionalidade(), getEndereco(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado(), getCep(), getTelefone(), getEmail(), getNmPai(), getNmMae(), getDsCaminhoFoto(), getDtAdmissaoIe(), getDsCaminhoFotoSenaQuatro());
	}
	public void cadastrarComponente(){
		CadastroProfessor cadastrar = new CadastroProfessor();
		cadastrar.cadastrarComponente(getCdMatricula(), getNmProfessor(), getNmComponente(), getDtAdmissao());
	}
	public void novaSenha(String matricula, String nmProfessor){
		CadastroProfessor cadastrar = new CadastroProfessor();
		cadastrar.NovaSenha(matricula, nmProfessor);
	}
	//------------------Consultas-------------------------------------------------------------------//
	CadastroProfessor consultar =  new CadastroProfessor();

	//----Atributos e métodos de pesquisa
	//--Tabela professores
	DefaultTableModel tabela = new DefaultTableModel();

	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	//----Consultar cadastro
	public void consultar(String nmProfessor, String nmComponente){
		consultar.consultar(nmProfessor, nmComponente);
		this.tabela = consultar.getTabela();
	}

	//----Consultar componentes para exibir
	public Vector consultarComponentes(){
		consultar.consultarCmbComponentes();
		return consultar.getComponentes();
	}
	//--Tabela curso componentes
	DefaultTableModel tabelaComponentes = new DefaultTableModel();

	public DefaultTableModel getTabelaComponentes(){
		return this.tabelaComponentes;
	}
	public void consultarComponentes(String nmProfessor){
		consultar.consultarComponentes(nmProfessor);
		this.tabelaComponentes = consultar.getTabelaComponentes();
	}
	//--Consultar descrição de componente
	public void consultarDsComponente(String nmComponente){
		consultar.consultarDsComponente(nmComponente);
		this.dsComponente = consultar.getDsComponente();
	}
	//----Consultar para exibir
	public void consultar(String matricula){
		consultar.consultar(matricula);
		this.cdMatricula = consultar.getCdMatricula();
		this.nmProfessor = consultar.getNmProfessor();
		this.cdCpf = consultar.getCdCpf();
		this.cdRg = consultar.getCdRg();
		this.rgOrgao = consultar.getRgOrgao();
		this.sexo = consultar.getSexo();
		this.dtNascimento = consultar.getDtNascimento();
		this.naturalidade = consultar.getNaturalidade();
		this.naturalidadeEstado = consultar.getNaturalidadeEstado();
		this.nacionalidade = consultar.getNacionalidade();
		this.endereco = consultar.getEndereco();
		this.numero = consultar.getNumero();
		this.complemento = consultar.getComplemento();
		this.bairro = consultar.getBairro();
		this.cidade = consultar.getCidade();
		this.estado = consultar.getEstado();
		this.cep = consultar.getCep();
		this.telefone = consultar.getTelefone();
		this.email = consultar.getEmail();
		this.nmPai = consultar.getNmPai();
		this.nmMae = consultar.getNmMae();
		this.dsCaminhoFoto = consultar.getDsCaminhoFoto();
		this.dtAdmissaoIe = consultar.getDtAdmissaoIe();
		this.nmLogin = consultar.getNmLogin();
		this.cdSenha = consultar.getSenha();
	}
	//------------------Alterar-------------------------------------------------------------------//
	public void alterarProfessor(){
		CadastroProfessor alterar = new CadastroProfessor();
		alterar.alterarProfessor(getCdMatricula(), getNmProfessor(), getCdCpf(), getCdRg(), getRgOrgao(), getSexo(), getDtNascimento(), getNaturalidade(), getNacionalidade(), getEndereco(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado(), getCep(), getTelefone(), getEmail(), getNmPai(), getNmMae(), getDsCaminhoFoto());
	}
	//------------------Remover-------------------------------------------------------------------//
	public void removerProfessor(String matricula){
		CadastroProfessor remover = new CadastroProfessor();
		remover.removerProfessor(matricula);
	}
	public void removerComponente(String matricula, String nmProfessor, String componente){
		CadastroProfessor remover = new CadastroProfessor();
		remover.removerComponente(matricula, nmProfessor, componente);
	}
	//-----------------Retorno de dados-------------------------------------------------------//
	public String getBairro() {
		return bairro;
	}
	public String getCdCpf() {
		return cdCpf;
	}
	public String getCdRg() {
		return cdRg;
	}
	public String getRgOrgao(){
		return rgOrgao;
	}
	public String getCdSenha() {
		return cdSenha;
	}
	public String getCep() {
		return cep;
	}
	public String getCidade() {
		return cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getDtAdmissao() {
		return dtAdmissao;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public String getEmail() {
		return email;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getEstado() {
		return estado;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public String getNaturalidadeEstado(){
		return naturalidadeEstado;
	}
	public String getNmComponente() {
		return nmComponente;
	}
	public String getNmLogin() {
		return nmLogin;
	}
	public String getNmMae() {
		return nmMae;
	}
	public String getNmPai() {
		return nmPai;
	}
	public String getNmProfessor() {
		return nmProfessor;
	}
	public String getNumero() {
		return numero;
	}
	public String getSexo() {
		return sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getDsCaminhoFoto(){
		return dsCaminhoFoto;
	}
	public String getDtAdmissaoIe(){
		return dtAdmissaoIe;
	}
	public String getCdMatricula(){
		return cdMatricula;
	}
	public String getDsComponente(){
		return dsComponente;
	}
	public String getDsCaminhoFotoSenaQuatro(){
		this.dsCaminhoFotoSenaQuatro = getDsCaminhoFoto();
		return dsCaminhoFotoSenaQuatro;
	}
}
