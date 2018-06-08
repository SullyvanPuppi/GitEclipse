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

public class CadastroContato extends ConexaoBD{

	public CadastroContato(){
		super();
	}	
	private String nmEmpresa = null;
	private String txtNmContato = null;
	private String txtFuncao = null;
	private String txtTelefone = null;
	private String txtTelefone2 = null;
	private String txtEmail = null;
	private String sexo = null;

	DefaultTableModel tabelaContatos = new DefaultTableModel();

	public DefaultTableModel getTabelaContatos(){
		return this.tabelaContatos;
	}
	DefaultTableModel tabelaContatosEmpresa = new DefaultTableModel();

	public DefaultTableModel getTabelaContatosEmpresa(){
		return this.tabelaContatosEmpresa;
	}
	public String getSexo() {
		return sexo;
	}
	public String getNmEmpresa() {
		return nmEmpresa;
	}
	public String getTxtEmail() {
		return txtEmail;
	}
	public String getTxtFuncao() {
		return txtFuncao;
	}
	public String getTxtNmContato() {
		return txtNmContato;
	}
	public String getTxtTelefone() {
		return txtTelefone;
	}
	public String getTxtTelefone2() {
		return txtTelefone2;
	}
	public void ajustaDados(String nmEmpresa, String txtNmContato, String txtFuncao, String txtTelefone, String txtTelefone2, String txtEmail, String sexo) {
		this.nmEmpresa = nmEmpresa;
		this.txtNmContato = txtNmContato;
		this.txtFuncao = txtFuncao;
		this.txtTelefone = txtTelefone;
		this.txtTelefone2 = txtTelefone2;
		this.txtEmail = txtEmail;
		this.sexo = sexo;
	}
	public int verificaExiste(String nmEmpresa, String nmContato){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Contatos WHERE nm_Empresa='"+nmEmpresa+"' AND nm_Contato='"+nmContato+"';";

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
	public void cadastrarContato(String nmEmpresa, String categoria){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Contatos (nm_Empresa, nm_Categoria, nm_Contato, nm_Cargo, sg_Sexo, cd_Telefone, cd_Telefone2, nm_Email) VALUES (?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, nmEmpresa);
			stmt.setString(2, categoria);
			stmt.setString(3, getTxtNmContato());
			stmt.setString(4, getTxtFuncao());
			stmt.setString(5, getSexo());
			stmt.setString(6, getTxtTelefone());
			stmt.setString(7, getTxtTelefone2());
			stmt.setString(8, getTxtEmail());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			if(categoria.equals("Outros")){
				JOptionPane.showMessageDialog(null,"Contato cadastrado com sucesso\nNome do contato: "+getTxtNmContato()+"\nTelefone: "+getTxtTelefone(),"Cadastrado",1);
			}
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	private String cdCnpjRepresentada = "";
	private String getCdCnpjRepresentada(){
		return cdCnpjRepresentada;
	}
	public void cnpjRepresentada(String nmEmpresa){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			condicao= "SELECT cd_CnpjEmpresaRepresentada FROM tb_EmpresasRepresentadas WHERE nm_EmpresaRepresentada='"+nmEmpresa+"';"; 

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.cdCnpjRepresentada = rs.getString("cd_CnpjEmpresaRepresentada");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarCttRepresentada(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresaRepresentadaContatos (nm_Contato, nm_Cargo, sg_Sexo, cd_Telefone, cd_Telefone2, nm_Email, cd_CnpjEmpresaRepresentada) VALUES (?,?,?,?,?,?,?);");

			cnpjRepresentada(getNmEmpresa());

			//Preenche os campos
			stmt.setString(1, getTxtNmContato());
			stmt.setString(2, getTxtFuncao());
			stmt.setString(3, getSexo());
			stmt.setString(4, getTxtTelefone());
			stmt.setString(5, getTxtTelefone2());
			stmt.setString(6, getTxtEmail());
			stmt.setString(7, getCdCnpjRepresentada());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Contato cadastrado com sucesso\nNome do contato: "+getTxtNmContato()+"\nTelefone: "+getTxtTelefone(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarCttCliente(String cnpj){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresaClienteContatos (cd_CnpjEmpresaCliente, nm_Contato, nm_Cargo, sg_Sexo, cd_Telefone, cd_Telefone2, nm_Email) VALUES (?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, cnpj);
			stmt.setString(2, getTxtNmContato());
			stmt.setString(3, getTxtFuncao());
			stmt.setString(4, getSexo());
			stmt.setString(5, getTxtTelefone());
			stmt.setString(6, getTxtTelefone2());
			stmt.setString(7, getTxtEmail());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Contato cadastrado com sucesso\nNome do contato: "+getTxtNmContato()+"\nTelefone: "+getTxtTelefone(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarCttVisita(int idVisita, String nmEmpresa, String txtNmContato, String txtFuncao, String txtTelefone, String txtTelefone2, String txtEmail, String sexo){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresaClienteVisitaContatos (id_Visita, nm_Empresa, nm_Contato, nm_Cargo, cd_Telefone, cd_Telefone2, nm_Email, sg_Sexo) VALUES (?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setInt(1, idVisita);
			stmt.setString(2, nmEmpresa);
			stmt.setString(3, txtNmContato);
			stmt.setString(4, txtFuncao);
			stmt.setString(5, txtTelefone);
			stmt.setString(6, txtTelefone2);
			stmt.setString(7, txtEmail);
			stmt.setString(8, sexo);

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Contato cadastrado com sucesso\nNome do contato: "+getTxtNmContato()+"\nTelefone: "+getTxtTelefone(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	public void alterarContato(String nmContato, String nmEmpresa, String categoria){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Contatos set nm_Categoria=?, nm_Cargo=?, sg_Sexo=?, cd_Telefone=?, cd_Telefone2=?, nm_Email=? WHERE nm_Contato='"+nmContato+"' AND nm_Empresa='"+nmEmpresa+"';");

			//Preenche os campos
			stmt.setString(1, categoria);
			stmt.setString(2, getTxtFuncao());
			stmt.setString(3, getSexo());
			stmt.setString(4, getTxtTelefone());
			stmt.setString(5, getTxtTelefone2());
			stmt.setString(6, getTxtEmail());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();

			JOptionPane.showMessageDialog(null,"Contato alterado com sucesso\nNome do contato: "+getTxtNmContato()+"\nTelefone: "+getTxtTelefone(),"Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarCttRepresentada(String nmContato, String cnpj){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresaRepresentadaContatos SET nm_Cargo=?, sg_Sexo=?, cd_Telefone=?, cd_Telefone2=?, nm_Email=? WHERE cd_CnpjEmpresaRepresentada='"+cnpj+"' AND nm_Contato='"+nmContato+"';"); 

			//Preenche os campos
			stmt.setString(1, getTxtFuncao());
			stmt.setString(2, getSexo());
			stmt.setString(3, getTxtTelefone());
			stmt.setString(4, getTxtTelefone2());
			stmt.setString(5, getTxtEmail());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarCttCliente(String nmContato, String cnpj){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresaClienteContatos SET nm_Cargo=?, sg_Sexo=?, cd_Telefone=?, cd_Telefone2=?, nm_Email=? WHERE cd_CnpjEmpresaCliente='"+cnpj+"' AND nm_Contato='"+nmContato+"';"); 

			//Preenche os campos
			stmt.setString(1, getTxtFuncao());
			stmt.setString(2, getSexo());
			stmt.setString(3, getTxtTelefone());
			stmt.setString(4, getTxtTelefone2());
			stmt.setString(5, getTxtEmail());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarCttVisita(int idVisita, String nmContato, String nmEmpresa){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresaClienteVisitaContatos SET nm_Cargo=?, sg_Sexo=?, cd_Telefone=?, cd_Telefone2=?, nm_Email=? WHERE id_Visita="+idVisita+" AND nm_Contato='"+nmContato+"';"); 

			//Preenche os campos
			stmt.setString(1, getTxtFuncao());
			stmt.setString(2, getSexo());
			stmt.setString(3, getTxtTelefone());
			stmt.setString(4, getTxtTelefone2());
			stmt.setString(5, getTxtEmail());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarCttFuncionario(String nmContato){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Funcionarios SET nm_Funcao=?, sg_Sexo=?, cd_Telefone=?, cd_Telefone2=?, nm_Email=? WHERE nm_Funcionario='"+nmContato+"';"); 

			//Preenche os campos
			stmt.setString(1, getTxtFuncao());
			stmt.setString(2, getSexo());
			stmt.setString(3, getTxtTelefone());
			stmt.setString(4, getTxtTelefone2());
			stmt.setString(5, getTxtEmail());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
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
	public void consultarContatosEmpresa(String nmEmpresa){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "";

			//Condições de pesquisa
			if(nmEmpresa.equals("")){
				condicao= "SELECT nm_Empresa, nm_Categoria, nm_Contato, cd_Telefone FROM tb_Contatos WHERE nm_Empresa='"+nmEmpresa+"';";
			}else{
				condicao= "SELECT nm_Empresa, nm_Categoria, nm_Contato, cd_Telefone FROM tb_Contatos WHERE nm_Empresa='"+nmEmpresa+"';";	
			}

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			tabelaContatosEmpresa.addColumn("Contato");
			tabelaContatosEmpresa.addColumn("Telefone");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[2];
				linha[0] = rs.getString("nm_Contato");
				linha[1] = rs.getString("cd_Telefone");
				tabelaContatosEmpresa.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarContatos(String nmContato, String categoria){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			if(categoria.equals("")){
				if(nmContato.trim().equals("")){
					condicao= "SELECT nm_Empresa, nm_Categoria, nm_Contato, cd_Telefone FROM tb_Contatos;";
				}else{
					condicao= "SELECT nm_Empresa, nm_Categoria, nm_Contato, cd_Telefone FROM tb_Contatos WHERE nm_Contato LIKE '"+nmContato+"%';";
				}
			}else{
				if(nmContato.trim().equals("")){
					condicao= "SELECT nm_Empresa, nm_Categoria, nm_Contato, cd_Telefone FROM tb_Contatos WHERE nm_Categoria LIKE '"+categoria+"';";
				}else{
					condicao= "SELECT nm_Empresa, nm_Categoria, nm_Contato, cd_Telefone FROM tb_Contatos WHERE nm_Contato LIKE '"+nmContato+"%' AND nm_Categoria LIKE '"+categoria+"';";
				}	
			}

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			tabelaContatos.addColumn("Empresa");
			tabelaContatos.addColumn("Categoria");
			tabelaContatos.addColumn("Contato");
			tabelaContatos.addColumn("Telefone");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[4];
				linha[0] = rs.getString("nm_Empresa");
				linha[1] = rs.getString("nm_Categoria");
				linha[2] = rs.getString("nm_Contato");
				linha[3] = rs.getString("cd_Telefone");
				tabelaContatos.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarContato(String nmEmpresa, String nmContato){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			condicao= "SELECT * FROM tb_Contatos WHERE nm_Empresa='"+nmEmpresa+"' AND nm_Contato='"+nmContato+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.nmEmpresa = rs.getString("nm_Empresa");
			this.txtNmContato = rs.getString("nm_Contato");
			this.txtFuncao = rs.getString("nm_Cargo");
			this.txtTelefone = rs.getString("cd_Telefone");
			this.txtTelefone2 = rs.getString("cd_Telefone2");
			this.txtEmail = rs.getString("nm_Email");
			this.sexo = rs.getString("sg_Sexo");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void remover(String nmContato, String nmEmpresa, String cnpjEmpresa, String categoria){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											
			String condicao = "";
			condicao ="DELETE FROM tb_Contatos WHERE nm_Contato='"+nmContato+"' AND nm_Empresa='"+nmEmpresa+"';";
			String condicao2 = "";
			if(categoria.equals("Representada")){
				condicao2 = "DELETE FROM tb_EmpresaRepresentadaContatos WHERE nm_Contato='"+nmContato+"'AND cd_CnpjEmpresaRepresentada='"+cnpjEmpresa+"';";
				st.executeUpdate(condicao2);//Remover contato da tabela contatos específica
			}else if(categoria.equals("Cliente")){
				condicao2 = "DELETE FROM tb_EmpresaClienteContatos WHERE nm_Contato='"+nmContato+"'AND cd_CnpjEmpresaCliente='"+cnpjEmpresa+"';";
				st.executeUpdate(condicao2);//Remover contato da tabela contatos específica
			}else if(categoria.equals("Visita")){
				condicao2 = "DELETE FROM tb_EmpresaClienteVisitaContatos WHERE nm_Contato='"+nmContato+"'AND nm_Empresa='"+nmEmpresa+"';";
				st.executeUpdate(condicao2);//Remover contato da tabela contatos específica
			}else if(categoria.equals("Outros")){
				condicao ="DELETE FROM tb_Contatos WHERE nm_Contato='"+nmContato+"' AND nm_Empresa='"+nmEmpresa+"' AND nm_Categoria='Outros';";
			}
						
			st.executeUpdate(condicao);//Remover contato da tabela contatos

			st.close();
			con.close();

			JOptionPane.showMessageDialog(null,"Funcionário excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	public JasperPrint gerarRelatorio(String nmContato, String categoria){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			if(categoria.equals("")){
				if(nmContato.trim().equals("")){
					condicao= "SELECT * FROM tb_Contatos;";
				}else{
					condicao= "SELECT * FROM tb_Contatos WHERE nm_Contato LIKE '"+nmContato+"%';";
				}
			}else{
				if(nmContato.trim().equals("")){
					condicao= "SELECT * FROM tb_Contatos WHERE nm_Categoria LIKE '"+categoria+"';";
				}else{
					condicao= "SELECT * FROM tb_Contatos WHERE nm_Contato LIKE '"+nmContato+"%' AND nm_Categoria LIKE '"+categoria+"';";
				}	
			}

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			try {

				HashMap map = new HashMap();
				String arquivoJasper = "relatorios/RelAgenda.jasper";
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
}
