package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class CadastroEmpresa extends ConexaoBD{

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

	public CadastroEmpresa(){
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
	public void ajustaDados(String txtNome, String txtCnpj, String txtIe, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtCep, String txtTel1, String txtTel2, String txtFax, String txtEmail, String txtSite, String txtResponsavel, String txtResponsavelDoc) {
		this.txtNome = txtNome.trim();
		this.txtCnpj = txtCnpj.trim();
		this.txtIe = txtIe.trim();
		this.txtEndereco = txtEndereco.trim();
		this.txtEnderecoNumero = txtEnderecoNumero.trim();
		this.txtEnderecoComplemento = txtEnderecoComplemento.trim();
		this.txtEnderecoBairro = txtEnderecoBairro.trim();
		this.txtEnderecoCidade = txtEnderecoCidade.trim();
		this.cmbEnderecoEstado = cmbEnderecoEstado.trim();
		this.txtCep = txtCep.trim();
		this.txtTel1 = txtTel1.trim();
		this.txtTel2 = txtTel2.trim();
		this.txtFax = txtFax.trim();
		this.txtEmail = txtEmail.trim();
		this.txtSite = txtSite.trim();
		this.txtResponsavel = txtResponsavel.trim();
		this.txtResponsavelDoc = txtResponsavelDoc.trim();
	}
	public int verificaExiste(){
		int x = 0;
		try {			
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_EmpresaResponsavel;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				x = 1;
			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;
	}
	public void cadastrar(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresaResponsavel (nm_Empresa, cd_Cnpj, cd_InscricaoEstadual, nm_Endereco, cd_EnderecoNumero, ds_EnderecoComplemento, nm_EnderecoBairro, nm_EnderecoCidade, sg_EnderecoEstado, cd_Cep, cd_telefone, cd_Telefone2, cd_Fax, nm_Email, ds_Site, nm_Responsavel, cd_ResponsavelDoc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtNome());
			stmt.setString(2, getTxtCnpj());
			stmt.setString(3, getTxtIe());
			stmt.setString(4, getTxtEndereco());
			stmt.setString(5, getTxtEnderecoNumero());
			stmt.setString(6, getTxtEnderecoComplemento());
			stmt.setString(7, getTxtEnderecoBairro());
			stmt.setString(8, getTxtEnderecoCidade());
			stmt.setString(9, getCmbEnderecoEstado());
			stmt.setString(10, getTxtCep());
			stmt.setString(11, getTxtTel1());
			stmt.setString(12, getTxtTel2());
			stmt.setString(13, getTxtFax());
			stmt.setString(14, getTxtEmail());
			stmt.setString(15, getTxtSite());
			stmt.setString(16, getTxtResponsavel());
			stmt.setString(17, getTxtResponsavelDoc());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados da empresa cadastrado com sucesso!\nEmpresa:\n"+getTxtNome(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultar(){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_EmpresaResponsavel;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			rs.next();

			this.txtNome = rs.getString("nm_Empresa");
			this.txtCnpj = rs.getString("cd_Cnpj");
			this.txtIe = rs.getString("cd_InscricaoEstadual");
			this.txtEndereco = rs.getString("nm_Endereco");
			this.txtEnderecoNumero = rs.getString("cd_EnderecoNumero");
			this.txtEnderecoComplemento = rs.getString("ds_EnderecoComplemento");
			this.txtEnderecoBairro = rs.getString("nm_EnderecoBairro");
			this.txtEnderecoCidade = rs.getString("nm_EnderecoCidade");
			this.cmbEnderecoEstado = rs.getString("sg_EnderecoEstado");
			this.txtCep = rs.getString("cd_Cep");
			this.txtTel1 = rs.getString("cd_telefone");
			this.txtTel2 = rs.getString("cd_Telefone2");
			this.txtFax = rs.getString("cd_Fax");
			this.txtEmail = rs.getString("nm_Email");
			this.txtSite = rs.getString("ds_Site");
			this.txtResponsavel = rs.getString("nm_Responsavel");
			this.txtResponsavelDoc = rs.getString("cd_ResponsavelDoc");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterar(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresaResponsavel set nm_Empresa=?, cd_Cnpj=?, cd_InscricaoEstadual=?, nm_Endereco=?, cd_EnderecoNumero=?, ds_EnderecoComplemento=?, nm_EnderecoBairro=?, nm_EnderecoCidade=?, sg_EnderecoEstado=?, cd_Cep=?, cd_telefone=?, cd_Telefone2=?, cd_Fax=?, nm_Email=?, ds_Site=?, nm_Responsavel=?, cd_ResponsavelDoc=? WHERE id_Empresa=1;");

			//Preenche os campos
			stmt.setString(1, getTxtNome());
			stmt.setString(2, getTxtCnpj());
			stmt.setString(3, getTxtIe());
			stmt.setString(4, getTxtEndereco());
			stmt.setString(5, getTxtEnderecoNumero());
			stmt.setString(6, getTxtEnderecoComplemento());
			stmt.setString(7, getTxtEnderecoBairro());
			stmt.setString(8, getTxtEnderecoCidade());
			stmt.setString(9, getCmbEnderecoEstado());
			stmt.setString(10, getTxtCep());
			stmt.setString(11, getTxtTel1());
			stmt.setString(12, getTxtTel2());
			stmt.setString(13, getTxtFax());
			stmt.setString(14, getTxtEmail());
			stmt.setString(15, getTxtSite());
			stmt.setString(16, getTxtResponsavel());
			stmt.setString(17, getTxtResponsavelDoc());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados da empresa alterados com sucesso!\nEmpresa:\n"+getTxtNome(),"Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public JasperPrint gerarRelatorio(){
		JasperPrint rel = null;
		try {
			Connection con = DriverManager.getConnection(getUrlBd(), getUsuarioBd(), getSenhaBd());
			HashMap map = new HashMap();
			String arquivoJasper = "relatorios/RelEmpresaResponsavel.jasper";
			rel = JasperFillManager.fillReport(arquivoJasper, map, con);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rel;
	}
}
