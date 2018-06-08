package negocios;

//--Importando a classe responsável pela comunicação com o banco de dados
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

//Importando classe responsável pela comunicação com o banco de dados
import persistencia.CadastroAluno;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável pela parte lógica
 * referente a alunos.
 * 
 */
public class Aluno {


	//----Dados do aluno para a tabela Alunos
	private String matricula;

	private String nmAluno;

	private String cdCpf;

	private String cdRg;

	private String rgOrgao;

	private String sexo;

	private String dtNascimento;

	private String naturalidade;

	private String estadoNatural;

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

	private String caminhoFoto;

	private String dtMatricula;

	//--Dados para a tabela aluno menor de idade
	private String nmResponsavel;

	private String cdCpfResponsavel;

	private String cdRgResponsavel;

	private String rgOrgaoResponsavel;

	private String sexoResponsavel;

	private String dtNascimentoResponsavel;

	private String naturalidadeResponsavel;

	private String estadoNaturalResponsavel;

	private String nacionalidadeResponsavel;

	private String enderecoResponsavel;

	private String numeroResponsavel;

	private String complementoResponsavel;

	private String bairroResponsavel;

	private String cidadeResponsavel;

	private String estadoResponsavel;

	private String cepResponsavel;

	private String telefoneResponsavel;

	private String emailResponsavel;

	//----Dados para a tabela Alunos documentos apresentados

	private String nmDocumentoApresentado;

	private String dtApresentado;

	private String dsDocumentoApresentado;

	//---Dados para a tabela Alunos documentos pendentes
	private String nmDocumentoPendente;

	private String dtPendente;

	private String dsDocumentoPendente;

	//---Dados da tabela alunos documento solicitados
	private String nmDocumentoSolicitado;

	private String dtSolicitado;

	private String dsDocumentoSolicitado;

	private String statusDocumentoSolicitado;

	//---Dados pra a tabela Alunos historico pro
	private String tituloPro;

	private String dtHistoricoPro;

	private String dsHistoricoPro;

	//---Dados para a tabela Alunos historico contra
	private String tituloContra;

	private String dtHistoricoContra;

	private String dsHistoricoContra;

	//--Dados para a tabela de Alunos usuarios sena 4
	private String nmLogin;

	private String senha;
	
	private boolean exibirPerfil;
	
	private boolean exibirCurriculo;
	
	private boolean exibirProsContras;
	
	private String skin;

	//--Dados da tabela Aluno matricula trancada
	private String dtTrancamento;

	private String nmClasseUltima;

	private int cdClasse;

	private String nmCurso;

	private int cdCiclo;

	/**------CONSTRUTOR-------**/
	public Aluno() {
		super();	
	}	
	//---------------Ajuste de dados
	//--Dados do aluno
	public void ajustaAluno(String matricula, String nmAluno, String cdCpf, String cdRg, String rgOrgao, String sexo, String dtNascimento, String naturalidade, String estadoNatural, String nacionalidade, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String cep, String telefone, String email, String nmPai, String nmMae, String caminhoFoto, String dtMatricula){
		this.matricula = matricula;
		this.nmAluno = nmAluno;
		this.cdCpf = cdCpf;
		this.cdRg = cdRg;
		this.rgOrgao = rgOrgao;
		this.sexo = sexo;
		this.dtNascimento = dtNascimento;
		this.naturalidade = naturalidade;
		this.estadoNatural = estadoNatural;
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
		this.caminhoFoto = caminhoFoto;
		this.dtMatricula = dtMatricula;
	}	
	//--Dados do responsável se menor de idade
	public void ajustaResponsavel(String nmResponsavel, String cdCpfResponsavel, String cdRgResponsavel, String rgOrgaoResponsavel, String sexoResponsavel, String dtNascimentoResponsavel, String naturalidadeResponsavel, String estadoNaturalResponsavel, String nacionalidadeResponsavel, String enderecoResponsavel, String numeroResponsavel, String complementoResponsavel, String bairroResponsavel, String cidadeResponsavel, String estadoResponsavel, String cepResponsavel, String telefoneResponsavel, String emailResponsavel){
		this.nmResponsavel = nmResponsavel;
		this.cdCpfResponsavel = cdCpfResponsavel;
		this.cdRgResponsavel = cdRgResponsavel;
		this.rgOrgaoResponsavel = rgOrgaoResponsavel;
		this.sexoResponsavel = sexoResponsavel;
		this.dtNascimentoResponsavel = dtNascimentoResponsavel;
		this.naturalidadeResponsavel = naturalidadeResponsavel;
		this.estadoNaturalResponsavel = estadoNaturalResponsavel;
		this.nacionalidadeResponsavel = nacionalidadeResponsavel;
		this.enderecoResponsavel = enderecoResponsavel;
		this.numeroResponsavel = numeroResponsavel;
		this.complementoResponsavel = complementoResponsavel;
		this.bairroResponsavel = bairroResponsavel;
		this.cidadeResponsavel = cidadeResponsavel;
		this.estadoResponsavel = estadoResponsavel;
		this.cepResponsavel = cepResponsavel;
		this.telefoneResponsavel = telefoneResponsavel;
		this.emailResponsavel = emailResponsavel;
	}
	//----Ajuste documento apresentado
	public void ajustaDocumentoApresentado(String matricula, String nmAluno, String nmDocumentoApresentado, String dtApresentado, String dsDocumentoApresentado){
		this.matricula = matricula;
		this.nmAluno = nmAluno;
		this.nmDocumentoApresentado = nmDocumentoApresentado;
		this.dtApresentado = dtApresentado;
		this.dsDocumentoApresentado = dsDocumentoApresentado;
	}
	//----Ajuste documento pendente
	public void ajustaDocumentoPendente(String matricula, String nmAluno, String nmDocumentoPendente, String dtPendente, String dsDocumentoPendente){
		this.matricula = matricula;
		this.nmAluno = nmAluno;
		this.nmDocumentoPendente = nmDocumentoPendente;
		this.dtPendente = dtPendente;
		this.dsDocumentoPendente = dsDocumentoPendente;
	}
	//----Ajuste documento solicitado
	public void ajustaDocumentoSolicitado(String nmDocumentoSolicitado, String dtSolicitado, String dsDocumentoSolicitado, String statusDocumentoSolicitado){
		this.nmDocumentoSolicitado = nmDocumentoSolicitado;
		this.dtSolicitado = dtSolicitado;
		this.dsDocumentoSolicitado = dsDocumentoSolicitado;
		this.statusDocumentoSolicitado = statusDocumentoSolicitado;
	}
	//----Ajuste historico pro
	public void ajustaHistoricoPro(String matricula, String nmAluno, String tituloPro, String dtHistoricoPro, String dsHistoricoPro){
		this.matricula = matricula;
		this.nmAluno = nmAluno;
		this.tituloPro = tituloPro;
		this.dtHistoricoPro = dtHistoricoPro;
		this.dsHistoricoPro = dsHistoricoPro;
	}
	//----Ajuste historico contra
	public void ajustaHistoricoContra(String matricula, String nmAluno, String tituloContra, String dtHistoricoContra, String dsHistoricoContra){
		this.matricula = matricula;
		this.nmAluno = nmAluno;
		this.tituloContra = tituloContra;
		this.dtHistoricoContra = dtHistoricoContra;
		this.dsHistoricoContra = dsHistoricoContra;
	}
	//----Ajuste usuário SENA 4
	public void ajustaUsuarioSenaQuatro(String nmLogin, String senha, String skin, boolean perfil, boolean curriculo, boolean prosContras){
		this.nmLogin = nmLogin;
		this.senha = senha;
		this.skin = skin;
		this.exibirPerfil = perfil;
		this.exibirCurriculo = curriculo;
		this.exibirProsContras = prosContras;
	}
	//---Ajuste trancamento de matrícula
	public void ajustaTrancamento(String dtTrancamento, String nmClasseUltima, int cdClasse, String nmCurso, int cdCiclo){
		this.dtTrancamento = dtTrancamento;
		this.nmClasseUltima = nmClasseUltima;
		this.cdClasse = cdClasse;
		this.nmCurso = nmCurso;
		this.cdCiclo = cdCiclo;
	}
	//----------------Validação dos dados-----------------------------------------------------//
	String status = "";//Armazena status de validação
	public String getStatus(){
		return status;
	}
	//---Validar dados do aluno
	public int validarAluno(){
		int x = 0;
		this.status = "";
		if(getNmAluno().equals("")){
			this.status = "\nNome do aluno inválido";
			x+=1;
		}
		if(getCdRg().equals("")){
			this.status += "\nR.G. do aluno inválido";
			x+=1;
		}
		if(getRgOrgao().equals("")){
			this.status += "\nOrgão expedidor do R.G. do aluno inválido";
			x+=1;
		}
		if(getSexo().equals("----------")){
			this.status += "\nSexo do aluno inválido";
			x+=1;
		}
		if(getDtNascimento().equals("__/__/____")){
			this.status += "\nData de nascimento do aluno inválido";
			x+=1;
		}else{
			if(validaData(getDtNascimento())==1){
				x+=1;
				this.status += "\nData de nascimento do aluno inválido";
			}else{
				if(validaDataNascimento(getDtNascimento(), getDtMatricula())==1){
					this.status += "\nData de nascimento do aluno inválido";
					x+=1;
				}else{
					if(calculaIdade(getDtNascimento())>18){
						if(getCdCpf().equals("___.___.___-__")){// Antes de validar cpf validar idade
							this.status += "\nC.P.F. do aluno inválido";
							x+=1;
						}
					}else{
						if(calculaIdade(getDtNascimento())<=0){
							this.status += "\nData de nascimento do aluno inválido";
							x+=1;
						}
					}
				}	
			}						
		}
		if(getNaturalidade().equals("")){
			this.status += "\nNaturalidade do aluno inválido";
			x+=1;
		}
		if(getNacionalidade().equals("")){
			this.status += "\nNacionalidade do aluno inválido";
			x+=1;
		}
		if(getEndereco().equals("")){
			this.status += "\nEndereço de residência do aluno inválido";
			x+=1;
		}
		if(getNumero().equals("")){
			this.status += "\nNumero de endereço de residência do aluno inválido";
			x+=1;
		}
		if(getBairro().equals("")){
			this.status += "\nBairro de residência do aluno inválido";
			x+=1;
		}
		if(getCidade().equals("")){
			this.status += "\nCidade de residência do aluno inválido";
			x+=1;
		}
		if(getEstado().equals("--")){
			this.status += "\nEstado de residência do aluno inválido";
			x+=1;
		}
		if(getCep().equals("")){
			this.status += "\nC.E.P. da residência do aluno inválido";
			x+=1;
		}
		if(getTelefone().equals("(__)____-____")){
			this.status += "\nTelefone do aluno inválido";
			x+=1;
		}
		if(getNmPai().equals("")){
			this.status += "\nNome do pai do aluno inválido";
			x+=1;
		}if(getNmMae().equals("")){
			this.status += "\nNome da mãe do aluno inválido";
			x+=1;
		}
		return x;
	}
	//---Validar dados do responsável se menor de idade
	public int validarResponsavel(){
		int x = 0;
		this.status = "";
		if(getNmResponsavel().equals("")){
			this.status = "\nNome do responsável do aluno inválido";
			x+=1;
		}
		if(getCdCpfResponsavel().equals("___.___.___-__")){
			this.status += "\nC.P.F. do responsável do aluno inválido";
			x+=1;
		}
		if(getCdRgResponsavel().equals("")){
			this.status += "\nR.G. do responsável do aluno inválido";
			x+=1;
		}
		if(getRgOrgaoResponsavel().equals("")){
			this.status += "\nOrgão expedidor do R.G. do responsável do aluno inválido";
			x+=1;
		}
		if(getSexoResponsavel().equals("----------")){
			this.status += "\nSexo do responsável do aluno inválido";
			x+=1;
		}
		if(getDtNascimentoResponsavel().equals("__/__/____")){
			this.status += "\nData de nascimento do responsável do aluno inválido";
			x+=1;
		}else{
			if(validaData(getDtNascimentoResponsavel())==1){
				this.status += "\nData de nascimento do responsável do aluno inválido";
				x+=1;	
			}
		}
		if(getNaturalidadeResponsavel().equals("")){
			this.status += "\nNaturalidade do responsável do aluno inválido";
			x+=1;
		}
		if(getNacionalidadeResponsavel().equals("")){
			this.status += "\nNacionalidade do responsável do aluno inválido";
			x+=1;
		}
		if(getEnderecoResponsavel().equals("")){
			this.status += "\nEndereço de residência do responsável do aluno inválido";
			x+=1;
		}
		if(getNumeroResponsavel().equals("")){
			this.status += "\nNumero de endereço de residência do responsável do aluno inválido";
			x+=1;
		}
		if(getBairroResponsavel().equals("")){
			this.status += "\nBairro de residência do responsável do aluno inválido";
			x+=1;
		}
		if(getCidadeResponsavel().equals("")){
			this.status += "\nCidade de residência do responsável do aluno inválido";
			x+=1;
		}
		if(getEstadoResponsavel().equals("--")){
			this.status += "\nEstado de residência do responsável do aluno inválido";
			x+=1;
		}
		if(getCepResponsavel().equals("")){
			this.status += "\nC.E.P. da residência do responsável do aluno inválido";
			x+=1;
		}
		if(getTelefoneResponsavel().equals("(__)____-____")){
			this.status += "\nTelefone do responsável do aluno inválido";
			x+=1;
		}
		return x;
	}
	//---Validar documento pendente
	public int validarDocumentoApresentado(){
		int x = 0;
		this.status ="";
		if(getNmDocumentoApresentado().equals("")){
			this.status = "\nNome do documento apresentado do aluno inválido";
			x+=1;
		}
		if(getDtApresentado().equals("__/__/____")){
			this.status += "\nData de apresentação do documento do aluno inválido";
			x+=1;
		}
		return x;
	}
	//---Validar documento pendente
	public int validarDocumentoPendente(){
		int x = 0;
		this.status ="";
		if(getNmDocumentoPendente().equals("")){
			this.status = "\nNome do documento pendente do aluno inválido";
			x+=1;
		}
		if(getDtPendente().equals("__/__/____")){
			this.status += "\nData de pendência do documento do aluno inválido";
			x+=1;
		}
		return x;
	}
	public int validarHistoricoPro(){
		int x = 0;
		this.status ="";
		if(getTituloPro().equals("")){
			this.status = "\nNome da atividade positiva do aluno inválido";
			x+=1;
		}
		if(getDtHistoricoPro().equals("__/__/____")){
			this.status += "\nData da atividade positiva do aluno inválido";
			x+=1;
		}
		return x;
	}
	public int validarHistoricoContra(){
		int x = 0;
		this.status ="";
		if(getTituloContra().equals("")){
			this.status = "\nNome da atividade negativa do aluno inválido";
			x+=1;
		}
		if(getDtHistoricoContra().equals("__/__/____")){
			this.status += "\nData da atividade negativa do aluno inválido";
			x+=1;
		}
		return x;
	}
	//---Calcular idade para verificar se é necessário informar dados do responsável
	public int calculaIdade(String dataNasc){
		int age = 0;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascInput = null;

		try {
			dataNascInput= sdf.parse(dataNasc);
		} catch (Exception e) {

		}

		Calendar dateOfBirth = new GregorianCalendar();

		dateOfBirth.setTime(dataNascInput);

		int dia = dateOfBirth.get(Calendar.DAY_OF_MONTH);

		int mes = dateOfBirth.get(Calendar.MONTH)+1; 

		int ano = dateOfBirth.get(Calendar.YEAR);

		//Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		if(dia<32 && dia>0 && mes>0 && mes<=12 && ano>1900 && ano<today.get(Calendar.YEAR)){
			//Obtém a idade baseado no ano
			age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

			dateOfBirth.add(Calendar.YEAR, age);
			if (today.before(dateOfBirth)) {
				age--;
			}	
		}
		return age;
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

	//---Valida data de nascimento comparando com data de matricula
	@SuppressWarnings("deprecation")
	public int validaDataNascimento(String dtNasc, String dtMatricula){//Se retorna 1, data de nascimento inválida
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataNascimento = null;
		Date dataMatricula = null;

		try {
			dataNascimento= sdf.parse(dtNasc);
			dataMatricula= sdf.parse(dtMatricula);
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataNascimento);
		date2.setTime(dataMatricula);
		
		return x;
	}
	public int validaData(String data){
		int x = 0;		
		
		int dia = Integer.parseInt(data.substring(0, 2));
		
		int mes = Integer.parseInt(data.substring(3, 5));
		
		if(dia>31 || dia<1){
			x=1;
		}
		if(mes>12 || mes<1){
			x=1;
		}
		return x;
	}
	//---Verifica se existe aluno cadastrado
	public int getExiste(String cdMatricula){
		int x=0;
		/*CadastroProfessor existe = new CadastroProfessor();
		existe.verificaExisteProfessor(matricula);
		if(existe.getExisteProfessor()==1){
			x=1;
		}*/
		return x;
	}
	//---Gerar matrícula
	public int getMatriculaNova(){
		CadastroAluno gerar = new CadastroAluno();
		gerar.ajustaMatricula();
		return gerar.getMatriculaNova();
	}
	//----------------Comunicação com o banco de dados----------------------------------------//
	//--Cadastros
	public void cadastrarAluno(){
		CadastroAluno cadastrar = new CadastroAluno();
		cadastrar.cadastrarAluno(getMatricula(), getNmAluno(), getCdCpf(), getCdRg(), getRgOrgao(), getSexo(), getDtNascimento(), getNaturalidade(), 
		getEstadoNatural(), getNacionalidade(), getEndereco(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado(), getCep(), getTelefone(), getEmail(), getNmPai(), getNmMae(),
		getCaminhoFoto(), getDtMatricula(), getCaminhoFoto());
	}
	public void cadastrarResponsavel(){
		CadastroAluno cadastrar = new CadastroAluno();
		cadastrar.cadastrarResponsavel(getMatricula(), getNmAluno(), getNmResponsavel(), getCdCpfResponsavel(), getCdRgResponsavel(), getRgOrgaoResponsavel(),
		getSexoResponsavel(), getDtNascimentoResponsavel(), getNaturalidadeResponsavel(), getEstadoNaturalResponsavel(), getNacionalidadeResponsavel(),
		getEnderecoResponsavel(), getNumeroResponsavel(), getComplementoResponsavel(), getBairroResponsavel(), getCidadeResponsavel(), getEstadoResponsavel(),
		getCepResponsavel(), getTelefoneResponsavel(), getEmailResponsavel());
	}
	public void cadastrarDocumentoApresentado(){
		CadastroAluno cadastrar = new CadastroAluno();
		cadastrar.cadastrarDocumentoApresentado(getMatricula(), getNmAluno(), getNmDocumentoApresentado(), getDtApresentado(), getDsDocumentoApresentado());
	}
	public void cadastrarDocumentoPendente(){
		CadastroAluno cadastrar = new CadastroAluno();
		cadastrar.cadastrarDocumentoPendente(getMatricula(), getNmAluno(), getNmDocumentoPendente(), getDtPendente(), getDsDocumentoPendente());
	}
	public void cadastrarHistoricoPro(){
		CadastroAluno cadastrar = new CadastroAluno();
		cadastrar.cadastrarHistoricoPro(getMatricula(), getNmAluno(), getTituloPro(), getDtHistoricoPro(), getDsHistoricoPro());
	}
	public void cadastrarHistoricoContra(){
		CadastroAluno cadastrar = new CadastroAluno();
		cadastrar.cadastrarHistoricoContra(getMatricula(), getNmAluno(), getTituloContra(), getDtHistoricoContra(), getDsHistoricoContra());
	}
	public void cadastrarTrancamento(){

	}
	//--Alterações
	public void alterarAluno(String cdMatricula){

	}
	public void alterarResponsavel(String cdMatricula){

	}
	public void cadastrarDestrancamento(String cdMatricula){

	}
	public void novaSenha(String cdMatricula, String nmAluno){
		CadastroAluno cadastrar = new CadastroAluno();
		cadastrar.NovaSenha(cdMatricula, nmAluno);
	}
	//---Consultas
	//----Atributos e métodos de pesquisa
	//--Tabela alunos
	DefaultTableModel tabelaAlunos = new DefaultTableModel();

	public DefaultTableModel getTabelaAlunos(){
		return this.tabelaAlunos;
	}
	public void consultarAlunos(String nmAluno,String situacao,String nmCurso,String classe){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarAlunos(nmAluno, situacao, nmCurso, classe);
		this.tabelaAlunos = consultar.getTabelaAlunos();
	}
	public void consultarAtividade(int x, String cdMatricula, String nmAtividade){
		if(x==1){//Positiva
			consultarDsHistoricoPro(cdMatricula, nmAtividade);
		}else{//Negativa
			consultarDsHistoricoContra(cdMatricula, nmAtividade);
		}
	}
	//--Tabela histórico pró
	DefaultTableModel tabelaHistoricoPro = new DefaultTableModel();

	public DefaultTableModel getTabelaHistoricoPro(){
		return this.tabelaHistoricoPro;
	}
	public void consultarHistoricoPro(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarHistoricoPro(cdMatricula);
		this.tabelaHistoricoPro = consultar.getTabelaHistoricoPro();
	}
	public void consultarDsHistoricoPro(String cdMatricula, String nmAtividade){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarDsHistoricoPro(cdMatricula, nmAtividade);
		this.tituloPro = consultar.getTituloPro();
		this.dsHistoricoPro = consultar.getDsHistoricoPro();
		this.dtHistoricoPro = consultar.getDtHistoricoPro();
	}
	//--Tabela histórico contra
	DefaultTableModel tabelaHistoricoContra = new DefaultTableModel();

	public DefaultTableModel getTabelaHistoricoContra(){
		return this.tabelaHistoricoContra;
	}
	public void consultarDsHistoricoContra(String cdMatricula, String nmAtividade){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarDsHistoricoContra(cdMatricula, nmAtividade);
		this.tituloContra = consultar.getTituloContra();
		this.dsHistoricoContra = consultar.getDsHistoricoContra();
		this.dtHistoricoContra = consultar.getDtHistoricoContra();
	}
	public void consultarHistoricoContra(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarHistoricoContra(cdMatricula);
		this.tabelaHistoricoContra = consultar.getTabelaHistoricoContra();
	}
	//--Tabela Documentos apresentados
	DefaultTableModel tabelaDocApresentados = new DefaultTableModel();

	public DefaultTableModel getTabelaDocApresentados(){
		return this.tabelaDocApresentados;
	}
	public void consultarDocApresentados(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarDocApresentados(cdMatricula);
		this.tabelaDocApresentados = consultar.getTabelaDocApresentados();
	}
	//--Tabela Documentos pendentes
	DefaultTableModel tabelaDocPendentes = new DefaultTableModel();

	public DefaultTableModel getTabelaDocPendentes(){
		return this.tabelaDocPendentes;
	}
	public void consultarDocPendentes(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarDocPendentes(cdMatricula);
		this.tabelaDocPendentes = consultar.getTabelaDocPendentes();
	}
	//--Tabela Documentos solicitados
	DefaultTableModel tabelaDocSolicitados = new DefaultTableModel();

	public DefaultTableModel getTabelaDocSolicitados(){
		return this.tabelaDocSolicitados;
	}
	public void consultarDocSolicitados(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarDocSolicitados(cdMatricula);
		this.tabelaDocSolicitados = consultar.getTabelaDocSolicitados();
	}
	//--Tabela histórico de classes
	DefaultTableModel tabelaHistoricoClasses = new DefaultTableModel();

	public DefaultTableModel getTabelaHistoricoClasses(){
		return this.tabelaHistoricoClasses;
	}
	public void consultarHistoricoClasses(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarClasses(cdMatricula);
		this.tabelaHistoricoClasses = consultar.getTabelaClasses();
	}

	public Vector consultarCmbCursos(){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarCmbCursos();
		return consultar.getCursos();
	}
	public Vector consultarCmbClasses(String nmCurso){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarCmbClasses(nmCurso);
		return consultar.getClasses();
	}
	public Vector consultarCmbSkins(){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarCmbSkins();
		return consultar.getSkins();
	}
	public Vector consultarCmbMsgs(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarCmbMsgs(cdMatricula);
		return consultar.getMsgs();
	}
	public void consultarAluno(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarAluno(cdMatricula);
		this.matricula = consultar.getMatricula();
		this.nmAluno = consultar.getNmAluno();
		this.cdCpf = consultar.getCdCpf();
		this.cdRg = consultar.getCdRg();
		this.rgOrgao = consultar.getRgOrgao();
		this.sexo = consultar.getSexo();
		this.dtNascimento = consultar.getDtNascimento();
		this.naturalidade = consultar.getNaturalidade();
		this.estadoNatural = consultar.getEstadoNatural();
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
		this.caminhoFoto = consultar.getCaminhoFoto();
		this.dtMatricula = consultar.getDtMatricula();
		this.nmLogin = consultar.getNmLogin();
		this.senha = consultar.getSenha();
		this.exibirPerfil = consultar.getExibirPerfil();
		this.exibirCurriculo = consultar.getExibirCurriculo();
		this.exibirProsContras = consultar.getExibirProsContrass();
	}	
	public void consultarResponsavel(String cdMatricula){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarAluno(cdMatricula);
		this.nmResponsavel = consultar.getNmResponsavel();
		this.cdCpfResponsavel = consultar.getCdCpfResponsavel();
		this.cdRgResponsavel = consultar.getCdRgResponsavel();
		this.rgOrgaoResponsavel = consultar.getRgOrgaoResponsavel();
		this.sexoResponsavel = consultar.getSexoResponsavel();
		this.dtNascimentoResponsavel = consultar.getDtNascimentoResponsavel();
		this.naturalidadeResponsavel = consultar.getNaturalidadeResponsavel();
		this.estadoNaturalResponsavel = consultar.getEstadoNaturalResponsavel();
		this.nacionalidadeResponsavel = consultar.getNacionalidadeResponsavel();
		this.enderecoResponsavel = consultar.getEnderecoResponsavel();
		this.numeroResponsavel = consultar.getNumeroResponsavel();
		this.complementoResponsavel = consultar.getComplementoResponsavel();
		this.bairroResponsavel = consultar.getBairroResponsavel();
		this.cidadeResponsavel = consultar.getCidadeResponsavel();
		this.estadoResponsavel = consultar.getEstadoResponsavel();
		this.cepResponsavel = consultar.getCepResponsavel();
		this.telefoneResponsavel = consultar.getTelefoneResponsavel();
		this.emailResponsavel = consultar.getEmailResponsavel();
	}
	
	public void consultarDocumentoApresentado(String cdMatricula){

	}
	public void consultarDocumentoApresentado(String cdMatricula, String documento){

	}
	public void consultarDocumentoPendente(String cdMatricula){

	}
	public void consultarDocumentoPendente(String cdMatricula, String documento){

	}
	public void consultarClasse(String cdMatricula){

	}
	private String msg;
	private String nome;
	private String assunto;
	private String dsMsg;
	private String dtMsg;
	
	public String getMsg(){
		return this.msg;
	}	
	public String getAssunto() {
		return assunto;
	}
	public String getDsMsg() {
		return dsMsg;
	}
	public String getNome() {
		return nome;
	}
	public String getDtMsg(){
		return dtMsg;
	}
	public void consultarMsg(String cdMatricula, String assunto){
		CadastroAluno consultar = new CadastroAluno();
		consultar.consultarMsgSelecionado(cdMatricula, assunto);
		this.msg = consultar.getMsg();
		this.assunto = consultar.getAssunto();
		this.nome = consultar.getNome();
		this.dtMsg = consultar.getDtMsg();
	}
	public void removerAluno(String cdMatricula){

	}
	public void removerHistoricoPro(String cdMatricula, String nmAtividade){
		CadastroAluno remover = new CadastroAluno();
		remover.removerHistoricoPro(cdMatricula, nmAtividade);
	}
	public void removerHistoricoContra(String cdMatricula, String nmAtividade){
		CadastroAluno remover = new CadastroAluno();
		remover.removerHistoricoContra(cdMatricula, nmAtividade);
	}
	//------------Alterações
	//--Alterar cadastro no SENA IV
	public void alterarSenaQuatro(String cdMatricula){
		CadastroAluno alterar = new CadastroAluno();
		alterar.alterarSenaQuatro(cdMatricula, getNmLogin(), getSenha(), getSkin(), getExibirPerfil(), getExibirCurriculo(), getExibirProsContras());
	}
	//----------------Retorno de dados--------------------------------------------------------//
	public String getBairro() {
		return bairro;
	}

	public String getBairroResponsavel() {
		return bairroResponsavel;
	}

	public String getCaminhoFoto() {
		return caminhoFoto;
	}

	public int getCdCiclo() {
		return cdCiclo;
	}

	public int getCdClasse() {
		return cdClasse;
	}

	public String getCdCpf() {
		return cdCpf;
	}

	public String getCdCpfResponsavel() {
		return cdCpfResponsavel;
	}

	public String getCdRg() {
		return cdRg;
	}

	public String getCdRgResponsavel() {
		return cdRgResponsavel;
	}

	public String getCep() {
		return cep;
	}

	public String getCepResponsavel() {
		return cepResponsavel;
	}

	public String getCidade() {
		return cidade;
	}

	public String getCidadeResponsavel() {
		return cidadeResponsavel;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getComplementoResponsavel() {
		return complementoResponsavel;
	}

	public String getDsDocumentoApresentado() {
		return dsDocumentoApresentado;
	}

	public String getDsDocumentoPendente() {
		return dsDocumentoPendente;
	}

	public String getDsDocumentoSolicitado() {
		return dsDocumentoSolicitado;
	}

	public String getDsHistoricoContra() {
		return dsHistoricoContra;
	}

	public String getDsHistoricoPro() {
		return dsHistoricoPro;
	}

	public String getDtApresentado() {
		return dtApresentado;
	}

	public String getDtHistoricoContra() {
		return dtHistoricoContra;
	}

	public String getDtHistoricoPro() {
		return dtHistoricoPro;
	}

	public String getDtMatricula() {
		return dtMatricula;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public String getDtNascimentoResponsavel() {
		return dtNascimentoResponsavel;
	}

	public String getDtPendente() {
		return dtPendente;
	}

	public String getDtSolicitado() {
		return dtSolicitado;
	}

	public String getDtTrancamento() {
		return dtTrancamento;
	}

	public String getEmail() {
		return email;
	}

	public String getEmailResponsavel() {
		return emailResponsavel;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getEnderecoResponsavel() {
		return enderecoResponsavel;
	}

	public String getEstado() {
		return estado;
	}

	public String getEstadoNatural() {
		return estadoNatural;
	}

	public String getEstadoNaturalResponsavel() {
		return estadoNaturalResponsavel;
	}

	public String getEstadoResponsavel() {
		return estadoResponsavel;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public String getNacionalidadeResponsavel() {
		return nacionalidadeResponsavel;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public String getNaturalidadeResponsavel() {
		return naturalidadeResponsavel;
	}

	public String getNmAluno() {
		return nmAluno;
	}

	public String getNmClasseUltima() {
		return nmClasseUltima;
	}

	public String getNmCurso() {
		return nmCurso;
	}

	public String getNmDocumentoApresentado() {
		return nmDocumentoApresentado;
	}

	public String getNmDocumentoPendente() {
		return nmDocumentoPendente;
	}

	public String getNmDocumentoSolicitado() {
		return nmDocumentoSolicitado;
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

	public String getNmResponsavel() {
		return nmResponsavel;
	}

	public String getNumero() {
		return numero;
	}

	public String getNumeroResponsavel() {
		return numeroResponsavel;
	}

	public String getRgOrgao() {
		return rgOrgao;
	}

	public String getRgOrgaoResponsavel() {
		return rgOrgaoResponsavel;
	}

	public String getSenha() {
		return senha;
	}

	public String getSexo() {
		return sexo;
	}

	public String getSexoResponsavel() {
		return sexoResponsavel;
	}

	public String getStatusDocumentoSolicitado() {
		return statusDocumentoSolicitado;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}

	public String getTituloContra() {
		return tituloContra;
	}

	public String getTituloPro() {
		return tituloPro;
	}
	public boolean getExibirCurriculo() {
		return exibirCurriculo;
	}
	public boolean getExibirPerfil() {
		return exibirPerfil;
	}
	public boolean getExibirProsContras() {
		return exibirProsContras;
	}
	public String getSkin() {
		return skin;
	}



}
