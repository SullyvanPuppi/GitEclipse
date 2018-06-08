package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável para se comunicar ao banco de dados usuários do SENA 1.
 * Esta classe herda a classe Conexão BD com os dados relativos ao banco de dados.
 * Cadastros referentes ao sistema e a instituição de ensino.
 * 
 */
public class CadastroSistema extends ConexaoBD{

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
	public CadastroSistema(){
		super();
	}
	
	//---Ajusta dados para cadastro
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
	//------------Comandos para comunicação com o banco de dados-----------------------//
	//--Verifica se existe cadastrado
	//--Armazena
	private int existe;
	//--Retorna se existe
	public int getExiste(){
		return this.existe;
	}	
	public void verificaExiste(){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_sistema;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				this.existe= 1;
			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Cadastrar IE
	public void cadastrar(){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar o sistema
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_sistema (nm_Instituicao, nm_Endereco, cd_Numero, ds_Complemento, nm_Bairro, nm_Cidade, sg_Estado, cd_Cep, cd_Telefone, nm_Email, cd_Fax, nm_Responsavel, cd_Cnpj, cd_Ie, nm_Secretario, cd_RgSecretario, nm_Diretor, cd_RgDiretor, ds_CaminhoLogo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getNmInstituicao().trim());
			stmt.setString(2, getEndereco().trim());
			stmt.setString(3, getNumero().trim());
			stmt.setString(4, getComplemento().trim());
			stmt.setString(5, getBairro().trim());
			stmt.setString(6, getCidade().trim());
			stmt.setString(7, getEstado().trim());
			stmt.setString(8, getCep().trim());
			stmt.setString(9, getTelefone().trim());
			stmt.setString(10, getEmail().trim());
			stmt.setString(11, getFax().trim());
			stmt.setString(12, getNmResponsavel().trim());
			stmt.setString(13, getCnpj().trim());
			stmt.setString(14, getIe().trim());
			stmt.setString(15, getNmSecretario().trim());
			stmt.setString(16, getRgSecretario().trim());
			stmt.setString(17, getNmDiretor().trim());
			stmt.setString(18, getRgDiretor().trim());
			stmt.setString(19, getCaminhoLogo().trim());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Sistema cadastrado com sucesso!","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-----------Consultar
	public void consultar(){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_sistema;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar sistema
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();
			
			//--Busca os dados cadastrados
			this.nmInstituicao = rs.getString("nm_Instituicao");
			this.endereco = rs.getString("nm_Endereco");
			this.numero = rs.getString("cd_Numero");
			this.complemento = rs.getString("ds_Complemento");
			this.bairro = rs.getString("nm_Bairro");
			this.cidade = rs.getString("nm_Cidade");
			this.estado = rs.getString("sg_Estado");
			this.cep = rs.getString("cd_Cep");
			this.telefone = rs.getString("cd_Telefone");
			this.email = rs.getString("nm_Email");
			this.fax = rs.getString("cd_Fax");
			this.nmResponsavel = rs.getString("nm_Responsavel");
			this.cnpj = rs.getString("cd_Cnpj");
			this.ie = rs.getString("cd_Ie");
			this.nmSecretario = rs.getString("nm_Secretario");
			this.rgSecretario = rs.getString("cd_RgSecretario");
			this.nmDiretor = rs.getString("nm_Diretor");
			this.rgDiretor = rs.getString("cd_RgDiretor");
			this.caminhoLogo = rs.getString("ds_CaminhoLogo");	
						
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-----------Alterar dados cadastrados
	public void alterar(){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_sistema set nm_Instituicao=?, nm_Endereco=?, cd_Numero=?, ds_Complemento=?, nm_Bairro=?, nm_Cidade=?, sg_Estado=?, cd_Cep=?, cd_Telefone=?, nm_Email=?, cd_Fax=?, nm_Responsavel=?, cd_Cnpj=?, cd_Ie=?, nm_Secretario=?, cd_RgSecretario=?, nm_Diretor=?, cd_RgDiretor=?, ds_CaminhoLogo=? WHERE id_Sistema=1;");

			//Preenche os campos
			stmt.setString(1, getNmInstituicao());
			stmt.setString(2, getEndereco());
			stmt.setString(3, getNumero());
			stmt.setString(4, getComplemento());
			stmt.setString(5, getBairro());
			stmt.setString(6, getCidade());
			stmt.setString(7, getEstado());
			stmt.setString(8, getCep());
			stmt.setString(9, getTelefone());
			stmt.setString(10, getEmail());
			stmt.setString(11, getFax());
			stmt.setString(12, getNmResponsavel());
			stmt.setString(13, getCnpj());
			stmt.setString(14, getIe());
			stmt.setString(15, getNmSecretario());
			stmt.setString(16, getRgSecretario());
			stmt.setString(17, getNmDiretor());
			stmt.setString(18, getRgDiretor());
			stmt.setString(19, getCaminhoLogo());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Sistema alterado com sucesso!","Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
}