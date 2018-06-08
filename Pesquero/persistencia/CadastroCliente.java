package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class CadastroCliente extends ConexaoBD{

	//--Campos de cadastro simples
	private String txtNmCliente = null;
	private String txtCnpjCliente = null;
	private String cmbSegmento = null;
	private String txtDsAtividade = null;
	private String txtClienteDesde = null;
	private String txtDtCadastro = null;
	private String txtTelCliente = null;
	private String txtFaxCliente = null;
	private String txtEmail = null;
	private String txtSite = null;
	private String txtObs = null;

	//--Campos de cadastro completo
	private String txtRazaoCliente = null;
	private String txtIeCliente = null;
	private String txtEndereco = null;
	private String txtEnderecoNumero = null;
	private String txtEnderecoComplemento = null;
	private String txtEnderecoBairro = null;
	private String txtEnderecoCidade = null;
	private String cmbEnderecoEstado = null;
	private String txtEnderecoCep = null;
	private String txtTelefone2 = null;

	DefaultTableModel tabelaClientes = new DefaultTableModel();

	public DefaultTableModel getTabelaClientes(){
		return this.tabelaClientes;
	}

	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
	}
	public String getCmbSegmento() {
		return cmbSegmento;
	}
	public String getTxtClienteDesde() {
		return txtClienteDesde;
	}
	public String getTxtCnpjCliente() {
		return txtCnpjCliente;
	}
	public String getTxtDsAtividade() {
		return txtDsAtividade;
	}
	public String getTxtDtCadastro() {
		return txtDtCadastro;
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
	public String getTxtEnderecoCep() {
		return txtEnderecoCep;
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
	public String getTxtFaxCliente() {
		return txtFaxCliente;
	}
	public String getTxtIeCliente() {
		return txtIeCliente;
	}
	public String getTxtNmCliente() {
		return txtNmCliente;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public String getTxtRazaoCliente() {
		return txtRazaoCliente;
	}
	public String getTxtSite() {
		return txtSite;
	}
	public String getTxtTelCliente() {
		return txtTelCliente;
	}
	public String getTxtTelefone2() {
		return txtTelefone2;
	}
	public void ajustaDados(String txtNmCliente, String txtCnpjCliente, String cmbSegmento, String txtDsAtividade, String txtClienteDesde, String txtDtCadastro, String txtTelCliente, String txtFaxCliente, String txtEmail, String txtSite, String txtObs, String txtRazaoCliente, String txtIeCliente, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelefone2) {
		this.txtNmCliente = txtNmCliente;
		this.txtCnpjCliente = txtCnpjCliente;
		this.cmbSegmento = cmbSegmento;
		this.txtDsAtividade = txtDsAtividade;
		this.txtClienteDesde = txtClienteDesde;
		this.txtDtCadastro = txtDtCadastro;
		this.txtTelCliente = txtTelCliente;
		this.txtFaxCliente = txtFaxCliente;
		this.txtEmail = txtEmail;
		this.txtSite = txtSite;
		this.txtObs = txtObs;
		this.txtRazaoCliente = txtRazaoCliente;
		this.txtIeCliente = txtIeCliente;
		this.txtEndereco = txtEndereco;
		this.txtEnderecoNumero = txtEnderecoNumero;
		this.txtEnderecoComplemento = txtEnderecoComplemento;
		this.txtEnderecoBairro = txtEnderecoBairro;
		this.txtEnderecoCidade = txtEnderecoCidade;
		this.cmbEnderecoEstado = cmbEnderecoEstado;
		this.txtEnderecoCep = txtEnderecoCep;
		this.txtTelefone2 = txtTelefone2;
	}
	public void ajustaDados(String txtNmCliente, String txtCnpjCliente, String cmbSegmento, String txtDsAtividade, String txtClienteDesde, String txtDtCadastro, String txtTelCliente, String txtFaxCliente, String txtEmail, String txtSite, String txtObs) {
		this.txtNmCliente = txtNmCliente;
		this.txtCnpjCliente = txtCnpjCliente;
		this.cmbSegmento = cmbSegmento;
		this.txtDsAtividade = txtDsAtividade;
		this.txtClienteDesde = txtClienteDesde;
		this.txtDtCadastro = txtDtCadastro;
		this.txtTelCliente = txtTelCliente;
		this.txtFaxCliente = txtFaxCliente;
		this.txtEmail = txtEmail;
		this.txtSite = txtSite;
		this.txtObs = txtObs;
	}
	public int verificaExiste(String nome, String cnpj){
		int x = 0;
		try {			
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_EmpresasClientes WHERE nm_EmpresaCliente='"+nome+"' OR cd_CnpjEmpresaCliente='"+cnpj+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar empresa
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
	public void cadastrarSimples(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresasClientes (cd_CnpjEmpresaCliente, nm_EmpresaCliente, cd_Segmento, ds_RamoAtividade, dt_ClienteDesde, dt_CadastroCliente, cd_Telefone, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtCnpjCliente());
			stmt.setString(2, getTxtNmCliente());
			stmt.setString(3, getCmbSegmento());
			stmt.setString(4, getTxtDsAtividade());
			stmt.setString(5, getTxtClienteDesde());
			stmt.setString(6, getTxtDtCadastro());
			stmt.setString(7, getTxtTelCliente());
			stmt.setString(8, getTxtFaxCliente());
			stmt.setString(9, getTxtEmail());
			stmt.setString(10, getTxtSite());
			stmt.setString(11, getTxtObs());
			stmt.setString(12, "Simplificado");

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Empresa cliente cadastrada com sucesso!\nEmpresa: "+getTxtNmCliente(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}		
	}
	public void alteraSimples(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresasClientes set nm_EmpresaCliente=?, cd_Segmento=?, ds_RamoAtividade=?, dt_ClienteDesde=?, dt_CadastroCliente=?, cd_Telefone=?, cd_Fax=?, nm_Email=?, ds_Site=?, ds_Observacoes=?, sg_TipoCadastro=? WHERE cd_CnpjEmpresaCliente='"+getTxtCnpjCliente()+"';");

			//Preenche os campos
			stmt.setString(1, getTxtNmCliente());
			stmt.setString(2, getCmbSegmento());
			stmt.setString(3, getTxtDsAtividade());
			stmt.setString(4, getTxtClienteDesde());
			stmt.setString(5, getTxtDtCadastro());
			stmt.setString(6, getTxtTelCliente());
			stmt.setString(7, getTxtFaxCliente());
			stmt.setString(8, getTxtEmail());
			stmt.setString(9, getTxtSite());
			stmt.setString(10, getTxtObs());
			stmt.setString(11, "Simplificado");

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados da empresa cliente alterados com sucesso!\nEmpresa: "+getTxtNmCliente(),"Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarCompleto(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresasClientes (cd_CnpjEmpresaCliente, nm_EmpresaCliente, cd_InscricaoEstadual, cd_Segmento, ds_RamoAtividade, dt_ClienteDesde, dt_CadastroCliente, " +
			"nm_Endereco, cd_EnderecoNumero, ds_EnderecoComplemento, nm_EnderecoBairro, nm_EnderecoCidade, sg_EnderecoEstado, cd_Cep, cd_Telefone, cd_Telefone2, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro, nm_RazaoSocial) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtCnpjCliente());
			stmt.setString(2, getTxtNmCliente());
			stmt.setString(3, getTxtIeCliente());
			stmt.setString(4, getCmbSegmento());
			stmt.setString(5, getTxtDsAtividade());
			stmt.setString(6, getTxtClienteDesde());
			stmt.setString(7, getTxtDtCadastro());
			stmt.setString(8, getTxtEndereco());
			stmt.setString(9, getTxtEnderecoNumero());
			stmt.setString(10, getTxtEnderecoComplemento());
			stmt.setString(11, getTxtEnderecoBairro());
			stmt.setString(12, getTxtEnderecoCidade());
			stmt.setString(13, getCmbEnderecoEstado());
			stmt.setString(14, getTxtEnderecoCep());
			stmt.setString(15, getTxtTelCliente());
			stmt.setString(16, getTxtTelefone2());
			stmt.setString(17, getTxtFaxCliente());
			stmt.setString(18, getTxtEmail());
			stmt.setString(19, getTxtSite());
			stmt.setString(20, getTxtObs());
			stmt.setString(21, "Completo");
			stmt.setString(22, getTxtRazaoCliente());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Empresa cliente cadastrada com sucesso!\nEmpresa: "+getTxtNmCliente(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}		
	}
	public void alterarCompleto(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresasClientes set nm_EmpresaCliente=?, cd_InscricaoEstadual=?, cd_Segmento=?, ds_RamoAtividade=?, dt_ClienteDesde=?, dt_CadastroCliente=?, nm_Endereco=?, cd_EnderecoNumero=?, ds_EnderecoComplemento=?, nm_EnderecoBairro=?, nm_EnderecoCidade=?, sg_EnderecoEstado=?, cd_Cep=?, cd_Telefone=?, cd_Telefone2=?, cd_Fax=?, nm_Email=?, ds_Site=?, ds_Observacoes=?, sg_TipoCadastro=?, nm_RazaoSocial=? WHERE cd_CnpjEmpresaCliente='"+getTxtCnpjCliente()+"';");

			//Preenche os campos
			stmt.setString(1, getTxtNmCliente());
			stmt.setString(2, getTxtIeCliente());
			stmt.setString(3, getCmbSegmento());
			stmt.setString(4, getTxtDsAtividade());
			stmt.setString(5, getTxtClienteDesde());
			stmt.setString(6, getTxtDtCadastro());
			stmt.setString(7, getTxtEndereco());
			stmt.setString(8, getTxtEnderecoNumero());
			stmt.setString(9, getTxtEnderecoComplemento());
			stmt.setString(10, getTxtEnderecoBairro());
			stmt.setString(11, getTxtEnderecoCidade());
			stmt.setString(12, getCmbEnderecoEstado());
			stmt.setString(13, getTxtEnderecoCep());
			stmt.setString(14, getTxtTelCliente());
			stmt.setString(15, getTxtTelefone2());
			stmt.setString(16, getTxtFaxCliente());
			stmt.setString(17, getTxtEmail());
			stmt.setString(18, getTxtSite());
			stmt.setString(19, getTxtObs());
			stmt.setString(20, "Completo");
			stmt.setString(21, getTxtRazaoCliente());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados da empresa cliente alterados com sucesso!\nEmpresa: "+getTxtNmCliente(),"Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	private int registros = 0;
	public int getRegistros(){
		return registros;
	}
	public void consultar(String cnpj, String nome){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			if(cnpj.trim().equals(".   .   /    -")){
				if(nome.equals("")){
					condicao= "SELECT cd_CnpjEmpresaCliente, nm_EmpresaCliente, sg_TipoCadastro FROM tb_EmpresasClientes;";
				}else{
					condicao= "SELECT cd_CnpjEmpresaCliente, nm_EmpresaCliente, sg_TipoCadastro FROM tb_EmpresasClientes WHERE nm_EmpresaCliente LIKE '"+nome+"%';";
				}
			}else{
				if(nome.trim().equals("")){
					condicao= "SELECT cd_CnpjEmpresaCliente, nm_EmpresaCliente, sg_TipoCadastro FROM tb_EmpresasClientes WHERE cd_CnpjEmpresaCliente LIKE '"+cnpj+"%';";
				}else{
					condicao= "SELECT cd_CnpjEmpresaCliente, nm_EmpresaCliente, sg_TipoCadastro FROM tb_EmpresasClientes WHERE cd_CnpjEmpresaCliente LIKE '"+cnpj+"%' AND nm_EmpresaCliente LIKE '"+nome+"%';";
				}
			}

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar empresas Clientes
			ResultSet rs = st.executeQuery(condicao);

			tabelaClientes.addColumn("CNPJ");
			tabelaClientes.addColumn("Empresa");
			tabelaClientes.addColumn("Tipo de cadastro");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[3];
				linha[0] = rs.getString("cd_CnpjEmpresaCliente");
				linha[1] = rs.getString("nm_EmpresaCliente");
				linha[2] = rs.getString("sg_TipoCadastro");
				tabelaClientes.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarEmpresa(String nmEmpresa){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			condicao= "SELECT * FROM tb_EmpresasClientes WHERE nm_EmpresaCliente='"+nmEmpresa+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			if(rs.getString("sg_TipoCadastro").equals("Simplificado")){
				this.txtNmCliente = rs.getString("nm_EmpresaCliente");
				this.txtCnpjCliente = rs.getString("cd_CnpjEmpresaCliente");
				this.cmbSegmento = rs.getString("cd_Segmento");
				this.txtDsAtividade = rs.getString("ds_RamoAtividade");
				this.txtClienteDesde = rs.getString("dt_ClienteDesde");
				this.txtDtCadastro = rs.getString("dt_CadastroCliente");
				this.txtTelCliente = rs.getString("cd_Telefone");
				this.txtFaxCliente = rs.getString("cd_Fax");
				this.txtEmail = rs.getString("nm_Email");
				this.txtSite = rs.getString("ds_Site");
				this.txtObs = rs.getString("ds_Observacoes");
			}else if(rs.getString("sg_TipoCadastro").equals("Completo")){
				this.txtNmCliente = rs.getString("nm_EmpresaCliente");
				this.txtCnpjCliente = rs.getString("cd_CnpjEmpresaCliente");
				this.cmbSegmento = rs.getString("cd_Segmento");
				this.txtDsAtividade = rs.getString("ds_RamoAtividade");
				this.txtClienteDesde = rs.getString("dt_ClienteDesde");
				this.txtDtCadastro = rs.getString("dt_CadastroCliente");
				this.txtTelCliente = rs.getString("cd_Telefone");
				this.txtFaxCliente = rs.getString("cd_Fax");
				this.txtEmail = rs.getString("nm_Email");
				this.txtSite = rs.getString("ds_Site");
				this.txtObs = rs.getString("ds_Observacoes");
				this.txtRazaoCliente = rs.getString("nm_RazaoSocial");
				this.txtIeCliente = rs.getString("cd_InscricaoEstadual");
				this.txtEndereco = rs.getString("nm_Endereco");
				this.txtEnderecoNumero = rs.getString("cd_EnderecoNumero");
				this.txtEnderecoComplemento = rs.getString("ds_EnderecoComplemento");
				this.txtEnderecoBairro = rs.getString("nm_EnderecoBairro");
				this.txtEnderecoCidade = rs.getString("nm_EnderecoCidade");
				this.cmbEnderecoEstado = rs.getString("sg_EnderecoEstado");
				this.txtEnderecoCep = rs.getString("cd_Cep");
				this.txtTelefone2 = rs.getString("cd_Telefone2");
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void remover(String cnpj, String nmEmpresa){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_EmpresasClientes WHERE cd_CnpjEmpresaCliente='"+cnpj+"';";
			String condicao2 = "DELETE FROM tb_EmpresaClienteContatos WHERE cd_CnpjEmpresaCliente='"+cnpj+"';";
			String condicao3 = "DELETE FROM tb_Contatos WHERE nm_Empresa='"+nmEmpresa+"';";

			st.executeUpdate(condicao);//Remover empresa da tabela empresas Clientes
			st.executeUpdate(condicao2);//Remover empresa da tabela empresas Clientes contatos
			st.executeUpdate(condicao3);//Remover empresa da tabela de contatos

			st.close();
			con.close();

			JOptionPane.showMessageDialog(null,"Empresa cliente excluída com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	public JasperPrint gerarRelatorio(String cnpj, String nome){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			if(cnpj.trim().equals(".   .   /    -")){
				if(nome.trim().equals("")){
					condicao= "SELECT * FROM tb_EmpresasClientes;";
				}else{
					condicao= "SELECT * FROM tb_EmpresasClientes WHERE nm_EmpresaCliente LIKE '"+nome+"%';";
				}
			}else{
				if(nome.trim().equals("")){
					condicao= "SELECT * FROM tb_EmpresasClientes WHERE cd_CnpjEmpresaCliente LIKE '"+cnpj+"%';";
				}else{
					condicao= "SELECT * FROM tb_EmpresasClientes WHERE cd_CnpjEmpresaCliente LIKE '"+cnpj+"%' AND nm_EmpresaCliente LIKE '"+nome+"%';";
				}
			}

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			try {				
				HashMap map = new HashMap();
				String arquivoJasper = "relatorios/RelEmpresasClientes.jasper";
				rel = JasperFillManager.fillReport(arquivoJasper, map, jrRS);
			} catch (JRException e) {

			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return rel;	
	}
	public JasperPrint gerarRelatorioResumido(){
		JasperPrint rel = null;
		try {
			Connection con = DriverManager.getConnection(getUrlBd(), getUsuarioBd(), getSenhaBd());
			HashMap map = new HashMap();
			String arquivoJasper = "relatorios/RelClientesResumido.jasper";
			rel = JasperFillManager.fillReport(arquivoJasper, map, con);
		} catch (JRException e) {

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		}
		return rel;
	}
	@SuppressWarnings("unchecked")
	public JasperPrint gerarRelatorioContatos(String cnpj, String nmEmpresa){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			condicao= "SELECT * FROM tb_EmpresaClienteContatos WHERE cd_CnpjEmpresaCliente='"+cnpj+"';";

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			try {				
				HashMap map = new HashMap();
				map.put("cdCnpj", cnpj);
				map.put("nmEmpresa", nmEmpresa);
				String arquivoJasper = "relatorios/RelEmpresaClientesContatosA.jasper";
				rel = JasperFillManager.fillReport(arquivoJasper, map, jrRS);
			} catch (JRException e) {

			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return rel;	
	}
	
}
