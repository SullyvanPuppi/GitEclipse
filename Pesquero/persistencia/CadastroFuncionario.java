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

public class CadastroFuncionario extends ConexaoBD{


//	Campos do cadastro simplificado
	private String txtFuncionario;
	private String txtNmFuncionario;
	private String txtFuncao;
	private double txtSalario;
	private String txtDoc;
	private String txtTelefone;
	private String txtEmail;
	private boolean chkValeTransporte;
	private boolean chkValeRefeicao;
	private int txtDependentes;
	private boolean chkValeFamilia;
	private String sexo;
	private String txtObs;
//	Campos do cadastro completo
	private String txtCbo;
	private String txtCtps;
	private String txtDtNascimento;
	private String txtCpf;
	private String txtEndereco;
	private String txtEnderecoNumero;
	private String txtEnderecoComplemento;
	private String txtEnderecoBairro;
	private String txtEnderecoCidade;
	private String cmbEnderecoEstado;
	private String txtEnderecoCep;
	private String txtTelefoneContato;

	DefaultTableModel tabelaFuncionarios = new DefaultTableModel();

	public DefaultTableModel getTabelaFuncionarios(){
		return this.tabelaFuncionarios;
	}

	//--Campos do cadastro completo
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
	}
	public String getTxtCbo() {
		return txtCbo;
	}
	public String getTxtCpf() {
		return txtCpf;
	}
	public String getTxtCtps() {
		return txtCtps;
	}
	public String getTxtDtNascimento() {
		return txtDtNascimento;
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
	public String getTxtTelefoneContato() {
		return txtTelefoneContato;
	}	
	public String getTxtObs(){
		return txtObs;
	}	
	public String getSexo(){
		return sexo;
	}	
	public boolean getChkValeFamilia() {
		return chkValeFamilia;
	}
	public boolean getChkValeRefeicao() {
		return chkValeRefeicao;
	}
	public boolean getChkValeTransporte() {
		return chkValeTransporte;
	}
	public int getTxtDependentes() {
		return txtDependentes;
	}
	public String getTxtDoc() {
		return txtDoc;
	}
	public String getTxtEmail() {
		return txtEmail;
	}
	public String getTxtFuncao() {
		return txtFuncao;
	}
	public String getTxtFuncionario() {
		return txtFuncionario;
	}
	public String getTxtNmFuncionario() {
		return txtNmFuncionario;
	}
	public double getTxtSalario() {
		return txtSalario;
	}
	public String getTxtTelefone() {
		return txtTelefone;
	}
	public void ajustaDados(String txtFuncionario, String txtNmFuncionario, String txtFuncao, double txtSalario, String txtDoc, String txtTelefone, String txtEmail, boolean chkValeTransporte, boolean chkValeRefeicao, int txtDependentes, boolean chkValeFamilia, String sexo, String txtObs) {
		this.txtFuncionario = txtFuncionario;
		this.txtNmFuncionario = txtNmFuncionario;
		this.txtFuncao = txtFuncao;
		this.txtSalario = txtSalario;
		this.txtDoc = txtDoc;
		this.txtTelefone = txtTelefone;
		this.txtEmail = txtEmail;
		this.chkValeTransporte = chkValeTransporte;
		this.chkValeRefeicao = chkValeRefeicao;
		this.txtDependentes = txtDependentes;
		this.chkValeFamilia = chkValeFamilia;
		this.sexo = sexo;
		this.txtObs = txtObs;
	}
	public int verificaExiste(String nmFuncionario, String doc){
		int x = 0;
		try {			
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Funcionarios WHERE nm_Funcionario='"+nmFuncionario+"' OR cd_Rg='"+doc+"';";

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
	public int geraCodFuncionario(){
		int x = 1;
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_Funcionario FROM tb_Funcionarios order by cd_Funcionario asc;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			String ultimo = "";
			while(rs.next()){
				ultimo=rs.getString("cd_Funcionario");
			}
			int y = 0;
			if(!ultimo.equals("")){
				y = Integer.parseInt(ultimo.substring(4));
			}
			x = y + 1;

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
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
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Funcionarios (cd_Funcionario, nm_Funcao, vl_SalarioBase, nm_Funcionario, sg_Sexo, cd_Rg, cd_Telefone, nm_Email, ds_Observacoes, sg_Transporte, qt_Dependentes, sg_Familia, sg_Refeicao, sg_TipoDeCadastro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtFuncionario());
			stmt.setString(2, getTxtFuncao());
			stmt.setDouble(3, getTxtSalario());
			stmt.setString(4, getTxtNmFuncionario());
			stmt.setString(5, getSexo());
			stmt.setString(6, getTxtDoc());
			stmt.setString(7, getTxtTelefone());
			stmt.setString(8, getTxtEmail());
			stmt.setString(9, getTxtObs());
			stmt.setBoolean(10, getChkValeTransporte());
			stmt.setInt(11, getTxtDependentes());
			stmt.setBoolean(12, getChkValeFamilia());
			stmt.setBoolean(13, getChkValeRefeicao());
			stmt.setString(14, "Simplificado");

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Funcionário cadastrado com sucesso!\nFuncionário: "+getTxtNmFuncionario(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarSimples(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Funcionarios set nm_Funcao=?, vl_SalarioBase=?, nm_Funcionario=?, sg_Sexo=?, cd_Rg=?, cd_Telefone=?, nm_Email=?, ds_Observacoes=?, sg_Transporte=?, qt_Dependentes=?, sg_Familia=?, sg_Refeicao=?, sg_TipoDeCadastro=? WHERE cd_Funcionario='"+getTxtFuncionario()+"';");

			//Preenche os campos
			stmt.setString(1, getTxtFuncao());
			stmt.setDouble(2, getTxtSalario());
			stmt.setString(3, getTxtNmFuncionario());
			stmt.setString(4, getSexo());
			stmt.setString(5, getTxtDoc());
			stmt.setString(6, getTxtTelefone());
			stmt.setString(7, getTxtEmail());
			stmt.setString(8, getTxtObs());
			stmt.setBoolean(9, getChkValeTransporte());
			stmt.setInt(10, getTxtDependentes());
			stmt.setBoolean(11, getChkValeFamilia());
			stmt.setBoolean(12, getChkValeRefeicao());
			stmt.setString(13, "Simplificado");

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados do funcionário alterados com sucesso!\nFuncionário: "+getTxtNmFuncionario(),"Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void ajustaDados(String txtFuncionario, String txtNmFuncionario, String txtFuncao, double txtSalario, String txtDoc, String txtTelefone, String txtEmail, boolean chkValeTransporte, boolean chkValeRefeicao, int txtDependentes, boolean chkValeFamilia, String sexo, String txtObs, String txtCbo, String txtCtps, String txtDtNascimento, String txtCpf, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelefoneContato) {
		this.txtFuncionario = txtFuncionario;
		this.txtNmFuncionario = txtNmFuncionario;
		this.txtFuncao = txtFuncao;
		this.txtSalario = txtSalario;
		this.txtDoc = txtDoc;
		this.txtTelefone = txtTelefone;
		this.txtEmail = txtEmail;
		this.chkValeTransporte = chkValeTransporte;
		this.chkValeRefeicao = chkValeRefeicao;
		this.txtDependentes = txtDependentes;
		this.chkValeFamilia = chkValeFamilia;
		this.sexo = sexo;
		this.txtObs = txtObs;
		this.txtCbo = txtCbo;
		this.txtCtps = txtCtps;
		this.txtDtNascimento = txtDtNascimento;
		this.txtCpf = txtCpf;
		this.txtEndereco = txtEndereco;
		this.txtEnderecoNumero = txtEnderecoNumero;
		this.txtEnderecoComplemento = txtEnderecoComplemento;
		this.txtEnderecoBairro = txtEnderecoBairro;
		this.txtEnderecoCidade = txtEnderecoCidade;
		this.cmbEnderecoEstado = cmbEnderecoEstado;
		this.txtEnderecoCep = txtEnderecoCep;
		this.txtTelefoneContato = txtTelefoneContato;
	}
	public void cadastrarCompleto(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Funcionarios (cd_Funcionario, cd_Cbo, nm_Funcao, cd_Ctps, vl_SalarioBase, nm_Funcionario, sg_Sexo, dt_Nascimento, cd_Rg, cd_Cpf, nm_Endereco, cd_EnderecoNumero, ds_EnderecoComplemento, nm_EnderecoBairro, nm_EnderecoCidade, " +
			"sg_EnderecoEstado, cd_Cep, cd_Telefone, cd_Telefone2, nm_Email, ds_Observacoes, sg_Transporte, qt_Dependentes, sg_Familia, sg_Refeicao, sg_TipoDeCadastro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtFuncionario());
			stmt.setString(2, getTxtCbo());
			stmt.setString(3, getTxtFuncao());
			stmt.setString(4, getTxtCtps());
			stmt.setDouble(5, getTxtSalario());
			stmt.setString(6, getTxtNmFuncionario());
			stmt.setString(7, getSexo());
			stmt.setString(8, getTxtDtNascimento());
			stmt.setString(9, getTxtDoc());
			stmt.setString(10, getTxtCpf());
			stmt.setString(11, getTxtEndereco());
			stmt.setString(12, getTxtEnderecoNumero());
			stmt.setString(13, getTxtEnderecoComplemento());
			stmt.setString(14, getTxtEnderecoBairro());
			stmt.setString(15, getTxtEnderecoCidade());
			stmt.setString(16, getCmbEnderecoEstado());
			stmt.setString(17, getTxtEnderecoCep());
			stmt.setString(18, getTxtTelefone());
			stmt.setString(19, getTxtTelefoneContato());
			stmt.setString(20, getTxtEmail());
			stmt.setString(21, getTxtObs());
			stmt.setBoolean(22, getChkValeTransporte());
			stmt.setInt(23, getTxtDependentes());
			stmt.setBoolean(24, getChkValeFamilia());
			stmt.setBoolean(25, getChkValeRefeicao());
			stmt.setString(26, "Completo");

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Funcionário cadastrado com sucesso!\nFuncionário: "+getTxtNmFuncionario(),"Cadastrado",1);
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
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Funcionarios set cd_Cbo=?, nm_Funcao=?, cd_Ctps=?, vl_SalarioBase=?, nm_Funcionario=?, sg_Sexo=?, dt_Nascimento=?, cd_Rg=?, cd_Cpf=?, nm_Endereco=?, cd_EnderecoNumero=?, ds_EnderecoComplemento=?, nm_EnderecoBairro=?, nm_EnderecoCidade=?, " +
					"sg_EnderecoEstado=?, cd_Cep=?, cd_Telefone=?, cd_Telefone2=?, nm_Email=?, ds_Observacoes=?, sg_Transporte=?, qt_Dependentes=?, sg_Familia=?, sg_Refeicao=?, sg_TipoDeCadastro=? WHERE cd_Funcionario='"+getTxtFuncionario()+"';");

			//Preenche os campos
			stmt.setString(1, getTxtCbo());
			stmt.setString(2, getTxtFuncao());
			stmt.setString(3, getTxtCtps());
			stmt.setDouble(4, getTxtSalario());
			stmt.setString(5, getTxtNmFuncionario());
			stmt.setString(6, getSexo());
			stmt.setString(7, getTxtDtNascimento());
			stmt.setString(8, getTxtDoc());
			stmt.setString(9, getTxtCpf());
			stmt.setString(10, getTxtEndereco());
			stmt.setString(11, getTxtEnderecoNumero());
			stmt.setString(12, getTxtEnderecoComplemento());
			stmt.setString(13, getTxtEnderecoBairro());
			stmt.setString(14, getTxtEnderecoCidade());
			stmt.setString(15, getCmbEnderecoEstado());
			stmt.setString(16, getTxtEnderecoCep());
			stmt.setString(17, getTxtTelefone());
			stmt.setString(18, getTxtTelefoneContato());
			stmt.setString(19, getTxtEmail());
			stmt.setString(20, getTxtObs());
			stmt.setBoolean(21, getChkValeTransporte());
			stmt.setInt(22, getTxtDependentes());
			stmt.setBoolean(23, getChkValeFamilia());
			stmt.setBoolean(24, getChkValeRefeicao());
			stmt.setString(25, "Completo");

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados do funcionário alterados com sucesso!\nFuncionário: "+getTxtNmFuncionario(),"Alterado",1);
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
	public void consultar(String matricula, String nome){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			if(matricula.trim().equals("")){
				if(nome.trim().equals("")){
					condicao= "SELECT cd_Funcionario, nm_Funcionario, sg_TipoDeCadastro FROM tb_Funcionarios;";
				}else{
					condicao= "SELECT cd_Funcionario, nm_Funcionario, sg_TipoDeCadastro FROM tb_Funcionarios WHERE nm_Funcionario LIKE '"+nome+"%';";
				}
			}else{
				if(nome.trim().equals("")){
					condicao= "SELECT cd_Funcionario, nm_Funcionario, sg_TipoDeCadastro FROM tb_Funcionarios WHERE cd_Funcionario LIKE '"+matricula+"%';";
				}else{
					condicao= "SELECT cd_Funcionario, nm_Funcionario, sg_TipoDeCadastro FROM tb_Funcionarios WHERE cd_Funcionario LIKE '"+matricula+"%' AND nm_Funcionario LIKE '"+nome+"%';";
				}
			}

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			tabelaFuncionarios.addColumn("Matrícula");
			tabelaFuncionarios.addColumn("Nome");
			tabelaFuncionarios.addColumn("Tipo de cadastro");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[3];
				linha[0] = rs.getString("cd_Funcionario");
				linha[1] = rs.getString("nm_Funcionario");
				linha[2] = rs.getString("sg_TipoDeCadastro");
				tabelaFuncionarios.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarFunc(String matricula, String tipo){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			condicao= "SELECT * FROM tb_Funcionarios WHERE cd_Funcionario='"+matricula+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			if(rs.getString("sg_TipoDeCadastro").equals("Simplificado")){
				this.txtFuncionario = rs.getString("cd_Funcionario");
				this.txtNmFuncionario = rs.getString("nm_Funcionario");
				this.txtFuncao = rs.getString("nm_Funcao");
				this.txtSalario = rs.getDouble("vl_SalarioBase");
				this.txtDoc = rs.getString("cd_Rg");
				this.txtTelefone = rs.getString("cd_Telefone");
				this.txtEmail = rs.getString("nm_Email");
				this.chkValeTransporte = rs.getBoolean("sg_Transporte");
				this.chkValeRefeicao = rs.getBoolean("sg_Refeicao");
				this.txtDependentes = rs.getInt("qt_Dependentes");
				this.chkValeFamilia = rs.getBoolean("sg_Familia");
				this.sexo = rs.getString("sg_Sexo");
				this.txtObs = rs.getString("ds_Observacoes");
			}else if(rs.getString("sg_TipoDeCadastro").equals("Completo")){
				this.txtFuncionario = rs.getString("cd_Funcionario");
				this.txtNmFuncionario = rs.getString("nm_Funcionario");
				this.txtFuncao = rs.getString("nm_Funcao");
				this.txtSalario = rs.getDouble("vl_SalarioBase");
				this.txtDoc = rs.getString("cd_Rg");
				this.txtTelefone = rs.getString("cd_Telefone");
				this.txtEmail = rs.getString("nm_Email");
				this.chkValeTransporte = rs.getBoolean("sg_Transporte");
				this.chkValeRefeicao = rs.getBoolean("sg_Refeicao");
				this.txtDependentes = rs.getInt("qt_Dependentes");
				this.chkValeFamilia = rs.getBoolean("sg_Familia");
				this.sexo = rs.getString("sg_Sexo");
				this.txtObs = rs.getString("ds_Observacoes");
				this.txtCbo = rs.getString("cd_Cbo");
				this.txtCtps = rs.getString("cd_Ctps");
				this.txtDtNascimento = rs.getString("dt_Nascimento");
				this.txtCpf = rs.getString("cd_Cpf");
				this.txtEndereco = rs.getString("nm_Endereco");
				this.txtEnderecoNumero = rs.getString("cd_EnderecoNumero");
				this.txtEnderecoComplemento = rs.getString("ds_EnderecoComplemento");
				this.txtEnderecoBairro = rs.getString("nm_EnderecoBairro");
				this.txtEnderecoCidade = rs.getString("nm_EnderecoCidade");
				this.cmbEnderecoEstado = rs.getString("sg_EnderecoEstado");
				this.txtEnderecoCep = rs.getString("cd_Cep");
				this.txtTelefoneContato = rs.getString("cd_Telefone2");
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void remover(String cdMatricula, String nmFuncionario){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_Funcionarios WHERE cd_Funcionario='"+cdMatricula+"';";
			String condicao2 ="DELETE FROM tb_Contatos WHERE nm_Categoria='Funcionário' AND nm_Contato='"+nmFuncionario+"';";

			st.executeUpdate(condicao);//Remover funcionário da tabela funcionários
			st.executeUpdate(condicao2);//Remover funcionário da tabela de contatos

			st.close();
			con.close();

			JOptionPane.showMessageDialog(null,"Funcionário excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	public JasperPrint gerarRelatorio(String matricula, String nome){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			if(matricula.trim().equals("")){
				if(nome.trim().equals("")){
					condicao= "SELECT * FROM tb_Funcionarios;";
				}else{
					condicao= "SELECT * FROM tb_Funcionarios WHERE nm_Funcionario LIKE '"+nome+"%';";
				}
			}else{
				if(nome.trim().equals("")){
					condicao= "SELECT * FROM tb_Funcionarios WHERE cd_Funcionario LIKE '"+matricula+"%';";
				}else{
					condicao= "SELECT * FROM tb_Funcionarios WHERE cd_Funcionario LIKE '"+matricula+"%' AND nm_Funcionario LIKE '"+nome+"%';";
				}
			}

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			try {

				HashMap map = new HashMap();
				String arquivoJasper = "relatorios/RelFuncionarios.jasper";
				rel = JasperFillManager.fillReport(arquivoJasper, map, jrRS);
			} catch (JRException e) {
				JOptionPane.showMessageDialog(null,e.getMessage());
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
			String arquivoJasper = "relatorios/RelFuncionariosResumido.jasper";
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
