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

public class CadastroRepresentada extends ConexaoBD{

	//--Campos de cadastro simplificado
	private String txtNmRepresentada = null;
	private String txtCnpjRepresentada = null;
	private String cmbSegmento = null;
	private String txtDsAtividade = null;
	private String txtRepresentadaDesde = null;
	private String txtDtCadastro = null;
	private String txtTelRepresentada = null;
	private String txtFaxRepresentada = null;
	private String txtEmail = null;
	private String txtSite = null;
	private String txtObs = null;
	
	//--Campos de cadastro completo
	private String txtRazaoRepresentada = null;
	private String txtIeRepresentada = null;
	private String txtEndereco = null;
	private String txtEnderecoNumero = null;
	private String txtEnderecoComplemento = null;
	private String txtEnderecoBairro = null;
	private String txtEnderecoCidade = null;
	private String cmbEnderecoEstado = null;
	private String txtEnderecoCep = null;
	private String txtTelefone2 = null;
	
	DefaultTableModel tabelaRepresentadas = new DefaultTableModel();

	public DefaultTableModel getTabelaRepresentadas(){
		return this.tabelaRepresentadas;
	}
	
	//--Campos de cadastro completo
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
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
	public String getTxtIeRepresentada() {
		return txtIeRepresentada;
	}
	public String getTxtRazaoRepresentada() {
		return txtRazaoRepresentada;
	}
	public String getTxtTelefone2() {
		return txtTelefone2;
	}
	//--Campos do cadastro simples
	public String getCmbSegmento() {
		return cmbSegmento;
	}
	public String getTxtCnpjRepresentada() {
		return txtCnpjRepresentada;
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
	public String getTxtFaxRepresentada() {
		return txtFaxRepresentada;
	}
	public String getTxtNmRepresentada() {
		return txtNmRepresentada;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public String getTxtRepresentadaDesde() {
		return txtRepresentadaDesde;
	}
	public String getTxtSite() {
		return txtSite;
	}
	public String getTxtTelRepresentada() {
		return txtTelRepresentada;
	}
	public void ajustaDados(String txtNmRepresentada, String txtCnpjRepresentada, String cmbSegmento, String txtDsAtividade, String txtRepresentadaDesde, String txtDtCadastro, String txtTelRepresentada, String txtFaxRepresentada, String txtEmail, String txtSite, String txtObs) {
		this.txtNmRepresentada = txtNmRepresentada.trim();
		this.txtCnpjRepresentada = txtCnpjRepresentada.trim();
		this.cmbSegmento = cmbSegmento.trim();
		this.txtDsAtividade = txtDsAtividade.trim();
		this.txtRepresentadaDesde = txtRepresentadaDesde.trim();
		this.txtDtCadastro = txtDtCadastro.trim();
		this.txtTelRepresentada = txtTelRepresentada.trim();
		this.txtFaxRepresentada = txtFaxRepresentada.trim();
		this.txtEmail = txtEmail.trim();
		this.txtSite = txtSite.trim();
		this.txtObs = txtObs.trim();
	}
	public void ajustaDados(String txtNmRepresentada, String txtCnpjRepresentada, String cmbSegmento, String txtDsAtividade, String txtRepresentadaDesde, String txtDtCadastro, String txtTelRepresentada, String txtFaxRepresentada, String txtEmail, String txtSite, String txtObs, String txtRazaoRepresentada, String txtIeRepresentada, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelefone2) {
		this.txtNmRepresentada = txtNmRepresentada;
		this.txtCnpjRepresentada = txtCnpjRepresentada;
		this.cmbSegmento = cmbSegmento;
		this.txtDsAtividade = txtDsAtividade;
		this.txtRepresentadaDesde = txtRepresentadaDesde;
		this.txtDtCadastro = txtDtCadastro;
		this.txtTelRepresentada = txtTelRepresentada;
		this.txtFaxRepresentada = txtFaxRepresentada;
		this.txtEmail = txtEmail;
		this.txtSite = txtSite;
		this.txtObs = txtObs;
		this.txtRazaoRepresentada = txtRazaoRepresentada;
		this.txtIeRepresentada = txtIeRepresentada;
		this.txtEndereco = txtEndereco;
		this.txtEnderecoNumero = txtEnderecoNumero;
		this.txtEnderecoComplemento = txtEnderecoComplemento;
		this.txtEnderecoBairro = txtEnderecoBairro;
		this.txtEnderecoCidade = txtEnderecoCidade;
		this.cmbEnderecoEstado = cmbEnderecoEstado;
		this.txtEnderecoCep = txtEnderecoCep;
		this.txtTelefone2 = txtTelefone2;
	}
	public int verificaExiste(String nome, String cnpj){
		int x = 0;
		try {			
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_EmpresasRepresentadas WHERE nm_EmpresaRepresentada='"+nome+"' OR cd_CnpjEmpresaRepresentada='"+cnpj+"';";

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
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresasRepresentadas (cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, cd_Segmento, ds_RamoAtividade, dt_RepresentadaDesde, dt_CadastroRepresentada, cd_Telefone, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtCnpjRepresentada());
			stmt.setString(2, getTxtNmRepresentada());
			stmt.setString(3, getCmbSegmento());
			stmt.setString(4, getTxtDsAtividade());
			stmt.setString(5, getTxtRepresentadaDesde());
			stmt.setString(6, getTxtDtCadastro());
			stmt.setString(7, getTxtTelRepresentada());
			stmt.setString(8, getTxtFaxRepresentada());
			stmt.setString(9, getTxtEmail());
			stmt.setString(10, getTxtSite());
			stmt.setString(11, getTxtObs());
			stmt.setString(12, "Simplificado");
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Empresa representada cadastrada com sucesso!\nEmpresa: "+getTxtNmRepresentada(),"Cadastrado",1);
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
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresasRepresentadas set nm_EmpresaRepresentada=?, cd_Segmento=?, ds_RamoAtividade=?, dt_RepresentadaDesde=?, dt_CadastroRepresentada=?, cd_Telefone=?, cd_Fax=?, nm_Email=?, ds_Site=?, ds_Observacoes=?, sg_TipoCadastro=? WHERE cd_CnpjEmpresaRepresentada='"+getTxtCnpjRepresentada()+"';");

			//Preenche os campos
			stmt.setString(1, getTxtNmRepresentada());
			stmt.setString(2, getCmbSegmento());
			stmt.setString(3, getTxtDsAtividade());
			stmt.setString(4, getTxtRepresentadaDesde());
			stmt.setString(5, getTxtDtCadastro());
			stmt.setString(6, getTxtTelRepresentada());
			stmt.setString(7, getTxtFaxRepresentada());
			stmt.setString(8, getTxtEmail());
			stmt.setString(9, getTxtSite());
			stmt.setString(10, getTxtObs());
			stmt.setString(11, "Simplificado");
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados da empresa representada alterados com sucesso!\nEmpresa: "+getTxtNmRepresentada(),"Alterado",1);
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
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresasRepresentadas (cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, cd_InscricaoEstadual, cd_Segmento, ds_RamoAtividade, dt_RepresentadaDesde, dt_CadastroRepresentada, " +
					"nm_Endereco, cd_EnderecoNumero, ds_EnderecoComplemento, nm_EnderecoBairro, nm_EnderecoCidade, sg_EnderecoEstado, cd_Cep, cd_Telefone, cd_Telefone2, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro, nm_RazaoSocial) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtCnpjRepresentada());
			stmt.setString(2, getTxtNmRepresentada());
			stmt.setString(3, getTxtIeRepresentada());
			stmt.setString(4, getCmbSegmento());
			stmt.setString(5, getTxtDsAtividade());
			stmt.setString(6, getTxtRepresentadaDesde());
			stmt.setString(7, getTxtDtCadastro());
			stmt.setString(8, getTxtEndereco());
			stmt.setString(9, getTxtEnderecoNumero());
			stmt.setString(10, getTxtEnderecoComplemento());
			stmt.setString(11, getTxtEnderecoBairro());
			stmt.setString(12, getTxtEnderecoCidade());
			stmt.setString(13, getCmbEnderecoEstado());
			stmt.setString(14, getTxtEnderecoCep());
			stmt.setString(15, getTxtTelRepresentada());
			stmt.setString(16, getTxtTelefone2());
			stmt.setString(17, getTxtFaxRepresentada());
			stmt.setString(18, getTxtEmail());
			stmt.setString(19, getTxtSite());
			stmt.setString(20, getTxtObs());
			stmt.setString(21, "Completo");
			stmt.setString(22, getTxtRazaoRepresentada());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Empresa representada cadastrada com sucesso!\nEmpresa: "+getTxtNmRepresentada(),"Cadastrado",1);
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
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresasRepresentadas set nm_EmpresaRepresentada=?, cd_InscricaoEstadual=?, cd_Segmento=?, ds_RamoAtividade=?, dt_RepresentadaDesde=?, dt_CadastroRepresentada=?, nm_Endereco=?, cd_EnderecoNumero=?, ds_EnderecoComplemento=?, nm_EnderecoBairro=?, nm_EnderecoCidade=?, sg_EnderecoEstado=?, cd_Cep=?, cd_Telefone=?, cd_Telefone2=?, cd_Fax=?, nm_Email=?, ds_Site=?, ds_Observacoes=?, sg_TipoCadastro=?, nm_RazaoSocial=? WHERE cd_CnpjEmpresaRepresentada='"+getTxtCnpjRepresentada()+"';");

			//Preenche os campos
			stmt.setString(1, getTxtNmRepresentada());
			stmt.setString(2, getTxtIeRepresentada());
			stmt.setString(3, getCmbSegmento());
			stmt.setString(4, getTxtDsAtividade());
			stmt.setString(5, getTxtRepresentadaDesde());
			stmt.setString(6, getTxtDtCadastro());
			stmt.setString(7, getTxtEndereco());
			stmt.setString(8, getTxtEnderecoNumero());
			stmt.setString(9, getTxtEnderecoComplemento());
			stmt.setString(10, getTxtEnderecoBairro());
			stmt.setString(11, getTxtEnderecoCidade());
			stmt.setString(12, getCmbEnderecoEstado());
			stmt.setString(13, getTxtEnderecoCep());
			stmt.setString(14, getTxtTelRepresentada());
			stmt.setString(15, getTxtTelefone2());
			stmt.setString(16, getTxtFaxRepresentada());
			stmt.setString(17, getTxtEmail());
			stmt.setString(18, getTxtSite());
			stmt.setString(19, getTxtObs());
			stmt.setString(20, "Completo");
			stmt.setString(21, getTxtRazaoRepresentada());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados da empresa representada alterados com sucesso!\nEmpresa: "+getTxtNmRepresentada(),"Alterado",1);
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
				if(nome.trim().equals("")){
					condicao= "SELECT cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, sg_TipoCadastro FROM tb_EmpresasRepresentadas;";
				}else{
					condicao= "SELECT cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, sg_TipoCadastro FROM tb_EmpresasRepresentadas WHERE nm_EmpresaRepresentada LIKE '"+nome+"%';";
				}
			}else{
				if(nome.trim().equals("")){
					condicao= "SELECT cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, sg_TipoCadastro FROM tb_EmpresasRepresentadas WHERE cd_CnpjEmpresaRepresentada LIKE '"+cnpj+"%';";
				}else{
					condicao= "SELECT cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, sg_TipoCadastro FROM tb_EmpresasRepresentadas WHERE cd_CnpjEmpresaRepresentada LIKE '"+cnpj+"%' AND nm_Funcionario LIKE '"+nome+"%';";
				}
			}
			
			Statement st = con.createStatement();

			//Executa o código sql para pesquisar empresas representadas
			ResultSet rs = st.executeQuery(condicao);

			tabelaRepresentadas.addColumn("CNPJ");
			tabelaRepresentadas.addColumn("Empresa");
			tabelaRepresentadas.addColumn("Tipo de cadastro");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[3];
				linha[0] = rs.getString("cd_CnpjEmpresaRepresentada");
				linha[1] = rs.getString("nm_EmpresaRepresentada");
				linha[2] = rs.getString("sg_TipoCadastro");
				tabelaRepresentadas.addRow(linha);
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
			condicao= "SELECT * FROM tb_EmpresasRepresentadas WHERE nm_EmpresaRepresentada='"+nmEmpresa+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			if(rs.getString("sg_TipoCadastro").equals("Simplificado")){
				this.txtNmRepresentada = rs.getString("nm_EmpresaRepresentada");
				this.txtCnpjRepresentada = rs.getString("cd_CnpjEmpresaRepresentada");
				this.cmbSegmento = rs.getString("cd_Segmento");
				this.txtDsAtividade = rs.getString("ds_RamoAtividade");
				this.txtRepresentadaDesde = rs.getString("dt_RepresentadaDesde");
				this.txtDtCadastro = rs.getString("dt_CadastroRepresentada");
				this.txtTelRepresentada = rs.getString("cd_Telefone");
				this.txtFaxRepresentada = rs.getString("cd_Fax");
				this.txtEmail = rs.getString("nm_Email");
				this.txtSite = rs.getString("ds_Site");
				this.txtObs = rs.getString("ds_Observacoes");
			}else if(rs.getString("sg_TipoCadastro").equals("Completo")){
				this.txtNmRepresentada = rs.getString("nm_EmpresaRepresentada");
				this.txtCnpjRepresentada = rs.getString("cd_CnpjEmpresaRepresentada");
				this.cmbSegmento = rs.getString("cd_Segmento");
				this.txtDsAtividade = rs.getString("ds_RamoAtividade");
				this.txtRepresentadaDesde = rs.getString("dt_RepresentadaDesde");
				this.txtDtCadastro = rs.getString("dt_CadastroRepresentada");
				this.txtTelRepresentada = rs.getString("cd_Telefone");
				this.txtFaxRepresentada = rs.getString("cd_Fax");
				this.txtEmail = rs.getString("nm_Email");
				this.txtSite = rs.getString("ds_Site");
				this.txtObs = rs.getString("ds_Observacoes");
				this.txtRazaoRepresentada = rs.getString("nm_RazaoSocial");
				this.txtIeRepresentada = rs.getString("cd_InscricaoEstadual");
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

			String condicao ="DELETE FROM tb_EmpresasRepresentadas WHERE cd_CnpjEmpresaRepresentada='"+cnpj+"';";
			String condicao2 = "DELETE FROM tb_EmpresaRepresentadaContatos WHERE cd_CnpjEmpresaRepresentada='"+cnpj+"';";
			String condicao3 = "DELETE FROM tb_Contatos WHERE nm_Empresa='"+nmEmpresa+"';";
			
			st.executeUpdate(condicao);//Remover empresa da tabela empresas representadas
			st.executeUpdate(condicao2);//Remover empresa da tabela empresas representadas contatos
			st.executeUpdate(condicao3);//Remover empresa da tabela de contatos
			
			st.close();
			con.close();
			
			JOptionPane.showMessageDialog(null,"Empresa representada excluída com sucesso!","Exclusão",1);
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
			if(cnpj.trim().equals("")){
				if(nome.trim().equals("")){
					condicao= "SELECT * FROM tb_EmpresasRepresentadas;";
				}else{
					condicao= "SELECT * FROM tb_EmpresasRepresentadas WHERE nm_EmpresaRepresentada LIKE '"+nome+"%';";
				}
			}else{
				if(nome.trim().equals("")){
					condicao= "SELECT * FROM tb_EmpresasRepresentadas WHERE cd_CnpjEmpresaRepresentada LIKE '"+cnpj+"%';";
				}else{
					condicao= "SELECT * FROM tb_EmpresasRepresentadas WHERE cd_CnpjEmpresaRepresentada LIKE '"+cnpj+"%' AND nm_EmpresaRepresentada LIKE '"+nome+"%';";
				}
			}
			
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
			
			try {				
				HashMap map = new HashMap();
				String arquivoJasper = "relatorios/RelEmpresasRepresentadas.jasper";
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
			String arquivoJasper = "relatorios/RelRepresentadasResumido.jasper";
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

			condicao= "SELECT * FROM tb_EmpresaRepresentadaContatos WHERE cd_CnpjEmpresaRepresentada='"+cnpj+"';";
			
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
						
			try {				
				HashMap map = new HashMap();
				map.put("cdCnpj", cnpj);
				map.put("nmEmpresa", nmEmpresa);
				String arquivoJasper = "relatorios/RelEmpresaRepresentadaContatosA.jasper";
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
