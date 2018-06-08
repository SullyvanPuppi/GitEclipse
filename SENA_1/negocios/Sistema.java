/**
 * 
 */
package negocios;

import persistencia.CadastroSistema;

/**
 * @author Administrador
 *
 * Classe para validação de dados para cadastro do Sistema.
 *
 */
public class Sistema {

	private String nmInstituicao;
	
	private String endereco;
	
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	private String cep;
	
	private String telefone;
	
	private String email;
	
	private String fax;
	
	private String nmResponsavel;
	
	private String cnpj;
	
	private String ie;
	
	private String nmSecretario;
	
	private String rgSecretario;
	
	private String nmDiretor;
	
	private String rgDiretor;
	
	private String caminhoLogo;
	
	/**
	 * 
	 * Construtor
	 *
	 */
	public Sistema(){
		super();
	}
	
	//---Ajusta dados para validação
	public void ajustaDados(String nmInstituicao, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String cep, String telefone, String email, String fax, String nmResponsavel, String cnpj, String ie, String nmSecretario, String rgSecretario, String nmDiretor, String rgDiretor, String caminhoLogo){
		this.nmInstituicao = nmInstituicao;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
		this.email = email;
		this.fax = fax;
		this.nmResponsavel = nmResponsavel;
		this.cnpj = cnpj;
		this.ie = ie;
		this.nmSecretario = nmSecretario;
		this.rgSecretario = rgSecretario;
		this.nmDiretor = nmDiretor;
		this.rgDiretor = rgDiretor;
		this.caminhoLogo = caminhoLogo;	
	}

	//----Métodos que retornam valores respectivos
	public String getBairro() {
		return bairro;
	}

	public String getCaminhoLogo() {
		return caminhoLogo;
	}

	public String getCep() {
		return cep;
	}

	public String getCidade() {
		return cidade;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getComplemento() {
		return complemento;
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

	public String getFax() {
		return fax;
	}

	public String getIe() {
		return ie;
	}

	public String getNmDiretor() {
		return nmDiretor;
	}

	public String getNmInstituicao() {
		return nmInstituicao;
	}

	public String getNmResponsavel() {
		return nmResponsavel;
	}

	public String getNmSecretario() {
		return nmSecretario;
	}

	public String getNumero() {
		return numero;
	}

	public String getRgDiretor() {
		return rgDiretor;
	}

	public String getRgSecretario() {
		return rgSecretario;
	}

	public String getTelefone() {
		return telefone;
	}	
	
	//----------Validaçaõ dos dados-----------------------------------------------//
	//---Atributo que armazenará status de validação
	private String status;
	
	//---Retorna status de validação
	public String getStatus(){
		return this.status;
	}
	public int validar(){
		int x = 0;
		this.status = "";
		if(getNmInstituicao().equals("")){
			this.status +="\nNome da Instituição de ensino inválido.";
			x+=1;
		}
		if(getEndereco().equals("")){
			this.status +="\nEndereço da Instituição de ensino inválido.";
			x+=1;
		}
		if(getNumero().equals("")){
			this.status +="\nNúmero de endereço da Instituição de ensino inválido.";
			x+=1;
		}
		if(getBairro().equals("")){
			this.status +="\nBairro do endereço da Instituição de ensino inválido.";
			x+=1;
		}
		if(getCidade().equals("")){
			this.status +="\nCidade da Instituição de ensino inválido.";
			x+=1;
		}
		if(getEstado().equals("--")){
			this.status +="\nEstado da Instituição de ensino inválido.";
			x+=1;
		}
		if(getCep().equals("__.___-___")){
			this.status +="\nCEP da Instituição de ensino inválido.";
			x+=1;
		}
		if(getTelefone().equals("(__)____-____")){
			this.status +="\nTelefone da Instituição de ensino inválido.";
			x+=1;
		}
		if(getNmResponsavel().equals("")){
			this.status +="\nNome do responsável pela Instituição de ensino inválido.";
			x+=1;
		}
		if(getNmSecretario().equals("")){
			this.status +="\nNome do secretário(a) da Instituição de ensino inválido.";
			x+=1;
		}
		if(getRgSecretario().equals("")){
			this.status +="\nR.G. do secretário(a) da Instituição de ensino inválido.";
			x+=1;
		}
		if(getNmDiretor().equals("")){
			this.status +="\nNome do diretor(a) da Instituição de ensino inválido.";
			x+=1;
		}
		if(getRgDiretor().equals("")){
			this.status +="\nR.G. do diretor(a) da Instituição de ensino inválido.";
			x+=1;
		}
		if(x==0){
			this.status = "";
		}
		return x;
	}
	//------------Comandos para a camada de persistência---------------------------//
	//--Verifica se existe
	public int getExiste(){
		CadastroSistema cadastrar = new CadastroSistema();
		cadastrar.verificaExiste();
		if(cadastrar.getExiste()==0){
			return 0;
		}else{
			return 1;
		}
	}
	//--Cadastrar
	public void cadastrar(){
		CadastroSistema cadastrar = new CadastroSistema();
		cadastrar.ajustaDados(getNmInstituicao(), getEndereco(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado(), getCep(), getTelefone(), getEmail(), getFax(), getNmResponsavel(), getCnpj(), getIe(), getNmSecretario(), getRgSecretario(), getNmDiretor(), getRgDiretor(), getCaminhoLogo());
		cadastrar.cadastrar();
	}
	//--Alterar
	public void alterar(){
		CadastroSistema alterar = new CadastroSistema();
		alterar.ajustaDados(getNmInstituicao(), getEndereco(), getNumero(), getComplemento(), getBairro(), getCidade(), getEstado(), getCep(), getTelefone(), getEmail(), getFax(), getNmResponsavel(), getCnpj(), getIe(), getNmSecretario(), getRgSecretario(), getNmDiretor(), getRgDiretor(), getCaminhoLogo());
		alterar.alterar();
	}
		
	//--Busca dados
	public void consultar(){
		CadastroSistema consultar = new CadastroSistema();
		consultar.consultar();
		this.nmInstituicao = consultar.getNmInstituicao();
		this.endereco = consultar.getEndereco();
		this.numero = consultar.getNumero();
		this.complemento = consultar.getComplemento();
		this.bairro = consultar.getBairro();
		this.cidade = consultar.getCidade();
		this.estado = consultar.getEstado();
		this.cep = consultar.getCep();
		this.telefone = consultar.getTelefone();
		this.email = consultar.getEmail();
		this.fax = consultar.getFax();
		this.nmResponsavel = consultar.getNmResponsavel();
		this.cnpj = consultar.getCnpj();
		this.ie = consultar.getIe();
		this.nmSecretario = consultar.getNmSecretario();
		this.rgSecretario = consultar.getRgSecretario();
		this.nmDiretor = consultar.getNmDiretor();
		this.rgDiretor = consultar.getRgDiretor();
		this.caminhoLogo = consultar.getCaminhoLogo();	
	}
}
