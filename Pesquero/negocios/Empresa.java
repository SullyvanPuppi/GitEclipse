package negocios;

import net.sf.jasperreports.view.JasperViewer;
import persistencia.CadastroEmpresa;

public class Empresa {

	private int status = 0;
	private String msgStatus = "";
	
	private String txtNome;
	private String txtCnpj;
	private String txtIe;
	private String txtEndereco;
	private String txtEnderecoNumero;
	private String txtEnderecoComplemento;
	private String txtEnderecoBairro;
	private String txtEnderecoCidade;
	private String cmbEnderecoEstado;
	private String txtCep;
	private String txtTel1;
	private String txtTel2;
	private String txtFax;
	private String txtEmail;
	private String txtSite;
	private String txtResponsavel;
	private String txtResponsavelDoc;
	
	public Empresa() {
		super();
	}
	
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
	}
	public String getTxtCep() {
		return txtCep;
	}
	public String getTxtCnpj() {
		return txtCnpj;
	}
	public String getTxtEmail() {
		return txtEmail;
	}
	public String getTxtEndereco() {
		return txtEndereco;
	}
	public String getTxtEnderecoBairro() {
		return txtEnderecoBairro;
	}
	public String getTxtEnderecoCidade() {
		return txtEnderecoCidade;
	}
	public String getTxtEnderecoComplemento() {
		return txtEnderecoComplemento;
	}
	public String getTxtEnderecoNumero() {
		return txtEnderecoNumero;
	}
	public String getTxtFax() {
		return txtFax;
	}
	public String getTxtIe() {
		return txtIe;
	}
	public String getTxtNome() {
		return txtNome;
	}
	public String getTxtResponsavel() {
		return txtResponsavel;
	}
	public String getTxtResponsavelDoc() {
		return txtResponsavelDoc;
	}
	public String getTxtSite() {
		return txtSite;
	}
	public String getTxtTel1() {
		return txtTel1;
	}
	public String getTxtTel2() {
		return txtTel2;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void ajustaDados(String txtNome, String txtCnpj, String txtIe, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtCep, String txtTel1, String txtTel2, String txtFax, String txtEmail, String txtSite, String txtResponsavel, String txtResponsavelDoc) {
		this.txtNome = txtNome;
		this.txtCnpj = txtCnpj;
		this.txtIe = txtIe;
		this.txtEndereco = txtEndereco;
		this.txtEnderecoNumero = txtEnderecoNumero;
		this.txtEnderecoComplemento = txtEnderecoComplemento;
		this.txtEnderecoBairro = txtEnderecoBairro;
		this.txtEnderecoCidade = txtEnderecoCidade;
		this.cmbEnderecoEstado = cmbEnderecoEstado;
		this.txtCep = txtCep;
		this.txtTel1 = txtTel1;
		this.txtTel2 = txtTel2;
		this.txtFax = txtFax;
		this.txtEmail = txtEmail;
		this.txtSite = txtSite;
		this.txtResponsavel = txtResponsavel;
		this.txtResponsavelDoc = txtResponsavelDoc;
	}
	public int validar(){
		if(getTxtNome().equals("                                             ")){
			this.status +=1;
			this.msgStatus += "\nNome da empresa inválido";
		}
		if(getTxtCnpj().equals("  .   .   /    -  ")){
			this.status +=1;
			this.msgStatus += "\nCNPJ da empresa inválido";
		}
		if(getTxtEndereco().equals("                                             ")){
			this.status +=1;
			this.msgStatus += "\nEndereço da empresa inválido";
		}
		if(getTxtEnderecoNumero().equals("     ")){
			this.status +=1;
			this.msgStatus += "\nNúmero do endereço da empresa inválido";
		}
		if(getTxtEnderecoCidade().equals("                                             ")){
			this.status +=1;
			this.msgStatus += "\nCidade do endereço da empresa inválido";
		}
		if(getCmbEnderecoEstado().equals("--")){
			this.status +=1;
			this.msgStatus += "\nEstado do endereço da empresa inválido";
		}
		if(getTxtCep().equals("  .   -   ")){
			this.status +=1;
			this.msgStatus += "\nCEP do endereço da empresa inválido";
		}
		if(getTxtTel1().equals("(  )    -    ")){
			this.status +=1;
			this.msgStatus += "\nTelefone da empresa inválido";
		}
		if(getTxtResponsavel().equals("                                             ")){
			this.status +=1;
			this.msgStatus += "\nNome do responsável da empresa inválido";
		}
		if(getTxtResponsavelDoc().equals("                    ")){
			this.status +=1;
			this.msgStatus += "\nDocumento do responsável da empresa inválido";
		}
		return status;
	}
	public void cadastrar(){
		CadastroEmpresa cadastrar = new CadastroEmpresa();
		cadastrar.ajustaDados(getTxtNome(), getTxtCnpj(), getTxtIe(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtCep(), getTxtTel1(), getTxtTel2(), getTxtFax(), getTxtEmail(), getTxtSite(), getTxtResponsavel(), getTxtResponsavelDoc());
		cadastrar.cadastrar();
	}
	public void alterar(){
		CadastroEmpresa cadastrar = new CadastroEmpresa();
		cadastrar.ajustaDados(getTxtNome(), getTxtCnpj(), getTxtIe(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtCep(), getTxtTel1(), getTxtTel2(), getTxtFax(), getTxtEmail(), getTxtSite(), getTxtResponsavel(), getTxtResponsavelDoc());
		cadastrar.alterar();
	}
	public int verificaExiste(){
		CadastroEmpresa cadastrar = new CadastroEmpresa();
		return cadastrar.verificaExiste();
	}
	public void consultar(){
		CadastroEmpresa cadastrar = new CadastroEmpresa();
		cadastrar.consultar();
		this.txtNome = cadastrar.getTxtNome();
		this.txtCnpj = cadastrar.getTxtCnpj();
		this.txtIe = cadastrar.getTxtIe();
		this.txtEndereco = cadastrar.getTxtEndereco();
		this.txtEnderecoNumero = cadastrar.getTxtEnderecoNumero();
		this.txtEnderecoComplemento = cadastrar.getTxtEnderecoComplemento();
		this.txtEnderecoBairro = cadastrar.getTxtEnderecoBairro();
		this.txtEnderecoCidade = cadastrar.getTxtEnderecoCidade();
		this.cmbEnderecoEstado = cadastrar.getCmbEnderecoEstado();
		this.txtCep = cadastrar.getTxtCep();
		this.txtTel1 = cadastrar.getTxtTel1();
		this.txtTel2 = cadastrar.getTxtTel2();
		this.txtFax = cadastrar.getTxtFax();
		this.txtEmail = cadastrar.getTxtEmail();
		this.txtSite = cadastrar.getTxtSite();
		this.txtResponsavel = cadastrar.getTxtResponsavel();
		this.txtResponsavelDoc = cadastrar.getTxtResponsavelDoc();
	}
	public JasperViewer gerarRelatorio(){
		CadastroEmpresa gerar = new CadastroEmpresa();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
