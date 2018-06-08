package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class CadastroVisita extends ConexaoBD{

	private String idVisita = null;
	private String txtNmCliente = null;
	private String cmbSegmento = null;
	private String txtDtVisita = null;
	private Vector cmbRepresentadas = null;
	private String txtEndereco = null;
	private String txtEnderecoNumero = null;
	private String txtEnderecoComplemento = null;
	private String txtEnderecoBairro = null;
	private String txtEnderecoCidade = null;
	private String cmbEnderecoEstado = null;
	private String txtEnderecoCep = null;
	private String txtTelCliente = null;
	private String txtFaxCliente = null;
	private String txtEmail = null;
	private String txtSite = null;
	private String txtObs = null;
	private String cmbVendedores = null;
	private String txtRespUm = null;
	private String txtRespDois = null;
	private String txtRespTres = null;
	private String txtRespQuatro = null;
	private String txtRespCinco = null;
	private String txtRespSeis = null;
	private String txtRespSete = null;
	private String txtLembrar = null;
	private String txtTipo = null;
	private boolean lembrar = true;
	private String cdVendedor = null;
	private String nmVendedor = null;

	DefaultTableModel tabelaVisitas = new DefaultTableModel();

	public DefaultTableModel getTabelaVisitas(){
		return this.tabelaVisitas;
	}

	public void ajustaDados(String idVisita, String txtNmCliente, String cmbSegmento, String txtDtVisita, Vector cmbRepresentadas, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelCliente, String txtFaxCliente, String txtEmail, String txtSite, String txtObs, String cmbVendedores, String txtRespUm, String txtRespDois, String txtRespTres, String txtRespQuatro, String txtRespCinco, String txtRespSeis, String txtRespSete, String txtLembrar, String txtTipo, boolean lembrar, String cdVendedor, String nmVendedor) {
		this.idVisita = idVisita;
		this.txtNmCliente = txtNmCliente;
		this.cmbSegmento = cmbSegmento;
		this.txtDtVisita = txtDtVisita;
		this.cmbRepresentadas = cmbRepresentadas;
		this.txtEndereco = txtEndereco;
		this.txtEnderecoNumero = txtEnderecoNumero;
		this.txtEnderecoComplemento = txtEnderecoComplemento;
		this.txtEnderecoBairro = txtEnderecoBairro;
		this.txtEnderecoCidade = txtEnderecoCidade;
		this.cmbEnderecoEstado = cmbEnderecoEstado;
		this.txtEnderecoCep = txtEnderecoCep;
		this.txtTelCliente = txtTelCliente;
		this.txtFaxCliente = txtFaxCliente;
		this.txtEmail = txtEmail;
		this.txtSite = txtSite;
		this.txtObs = txtObs;
		this.cmbVendedores = cmbVendedores;
		this.txtRespUm = txtRespUm;
		this.txtRespDois = txtRespDois;
		this.txtRespTres = txtRespTres;
		this.txtRespQuatro = txtRespQuatro;
		this.txtRespCinco = txtRespCinco;
		this.txtRespSeis = txtRespSeis;
		this.txtRespSete = txtRespSete;
		this.txtLembrar = txtLembrar;
		this.txtTipo = txtTipo;
		this.lembrar = lembrar;
		this.cdVendedor = cdVendedor;
		this.nmVendedor = nmVendedor;
	}

	public String getNmVendedor(){
		return nmVendedor;
	}
	public String getCdVendedor(){
		return cdVendedor;
	}
	public String getIdVisita(){
		return idVisita;
	}
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
	}
	public Vector getCmbRepresentadas() {
		return cmbRepresentadas;
	}
	public String getCmbSegmento() {
		return cmbSegmento;
	}
	public String getCmbVendedores() {
		return cmbVendedores;
	}
	public String getTxtDtVisita() {
		return txtDtVisita;
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
	public boolean getLembrar() {
		return lembrar;
	}
	public String getTxtLembrar() {
		return txtLembrar;
	}
	public String getTxtNmCliente() {
		return txtNmCliente;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public String getTxtRespCinco() {
		return txtRespCinco;
	}
	public String getTxtRespDois() {
		return txtRespDois;
	}
	public String getTxtRespQuatro() {
		return txtRespQuatro;
	}
	public String getTxtRespSeis() {
		return txtRespSeis;
	}
	public String getTxtRespSete() {
		return txtRespSete;
	}
	public String getTxtRespTres() {
		return txtRespTres;
	}
	public String getTxtRespUm() {
		return txtRespUm;
	}
	public String getTxtSite() {
		return txtSite;
	}
	public String getTxtTelCliente() {
		return txtTelCliente;
	}
	public String getTxtTipo() {
		return txtTipo;
	}
	private int registros = 0;
	public int getRegistros(){
		return registros;
	}
	public void consultar(String nmEmpresaVisita, String nmRepresentada, String nmVendedor){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "";

			if(nmEmpresaVisita.equals("")){
				if(nmRepresentada.equals("")){
					if(nmVendedor.equals("")){
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas ORDER BY id_Visita;";
					}else{
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas WHERE nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY id_Visita;";
					}
				}else{
					if(nmVendedor.equals("")){
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas WHERE nm_EmpresaOferecida LIKE '"+nmRepresentada+"%' ORDER BY id_Visita;";
					}else{
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas WHERE nm_EmpresaOferecida LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY id_Visita;";
					}
				}
			}else{
				if(nmRepresentada.equals("")){
					if(nmVendedor.equals("")){
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas WHERE nm_Empresa LIKE '"+nmEmpresaVisita+"%' ORDER BY dt_Visita;";
					}else{
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas WHERE nm_Empresa LIKE '"+nmEmpresaVisita+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY id_Visita;";
					}
				}else{
					if(nmVendedor.equals("")){
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas WHERE nm_Empresa LIKE '"+nmEmpresaVisita+"%' AND nm_EmpresaOferecida LIKE '"+nmRepresentada+"%' ORDER BY id_Visita;";
					}else{
						condicao="SELECT id_Visita, nm_Empresa, nm_EmpresaOferecida, nm_Funcionario FROM tb_EmpresasOferecidas WHERE nm_Empresa LIKE '"+nmEmpresaVisita+"%' AND nm_EmpresaOferecida LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY id_Visita;";
					}
				}
			}

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar empresas representadas
			ResultSet rs = st.executeQuery(condicao);

			tabelaVisitas.addColumn("Visita");
			tabelaVisitas.addColumn("Empresa visitada");
			tabelaVisitas.addColumn("Empresa oferecida");
			tabelaVisitas.addColumn("Vendedor");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[4];
				linha[0] = ""+rs.getInt("id_Visita");
				linha[1] = rs.getString("nm_Empresa");
				linha[2] = rs.getString("nm_EmpresaOferecida");
				linha[3] = rs.getString("nm_Funcionario");
				tabelaVisitas.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultar(String idVisita){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT * FROM tb_EmpresasClientesVisitas WHERE id_Visita="+idVisita+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar empresas representadas
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.idVisita = ""+rs.getInt("id_Visita");
			this.txtNmCliente = rs.getString("nm_Empresa");
			this.cmbSegmento = rs.getString("cd_Segmento");
			this.txtDtVisita = rs.getString("dt_Visita");
			this.txtEndereco = rs.getString("nm_Endereco");
			this.txtEnderecoNumero = rs.getString("cd_EnderecoNumero");
			this.txtEnderecoComplemento = rs.getString("ds_EnderecoComplemento");
			this.txtEnderecoBairro = rs.getString("nm_EnderecoBairro");
			this.txtEnderecoCidade = rs.getString("nm_EnderecoCidade");
			this.cmbEnderecoEstado = rs.getString("sg_EnderecoEstado");
			this.txtEnderecoCep = rs.getString("cd_Cep");
			this.txtTelCliente = rs.getString("cd_Telefone");
			this.txtFaxCliente = rs.getString("cd_Fax");
			this.txtEmail = rs.getString("nm_Email");
			this.txtSite = rs.getString("ds_Site");
			this.txtObs = rs.getString("ds_Obs");
			this.lembrar = rs.getBoolean("sg_Lembrar");
			this.txtLembrar = rs.getString("dt_Lembrar");
			this.nmVendedor = rs.getString("nm_Funcionario");
			this.txtTipo = rs.getString("sg_TipoVisita");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarQuestoes(String idVisita){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT * FROM tb_QuestoesVisitas WHERE id_Visita="+idVisita+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar empresas representadas
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.txtRespUm = rs.getString("ds_Quest01");
			this.txtRespDois = rs.getString("ds_Quest02");
			this.txtRespTres = rs.getString("ds_Quest03");
			this.txtRespQuatro = rs.getString("ds_Quest04");
			this.txtRespCinco = rs.getString("ds_Quest05");
			this.txtRespSeis = rs.getString("ds_Quest06");
			this.txtRespSete = rs.getString("ds_Quest07");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	public Object consultarOferecidas(String idVisita){
		Object oferecidas[] = new Object[3];
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT nm_EmpresaOferecida FROM tb_EmpresasOferecidas WHERE id_Visita="+idVisita+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar empresas representadas
			ResultSet rs = st.executeQuery(condicao);

			oferecidas[0] = "";
			oferecidas[1] = "";
			oferecidas[2] = "";

			int contador = 0;
			while(rs.next()){
				oferecidas[contador] = rs.getString("nm_EmpresaOferecida");
				contador+=1;
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return oferecidas;	
	}
	public void cadastrar(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresasClientesVisitas (id_Visita, nm_Empresa, cd_Segmento, nm_Endereco, cd_EnderecoNumero, ds_EnderecoComplemento, nm_EnderecoBairro, nm_EnderecoCidade, sg_EnderecoEstado, " +
			"cd_Cep, cd_Telefone, cd_Fax, nm_Email, ds_Site, dt_Visita, sg_TipoVisita, cd_Funcionario, dt_Lembrar, sg_Lembrar, nm_Funcionario, ds_Obs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getIdVisita());
			stmt.setString(2, getTxtNmCliente());
			stmt.setString(3, getCmbSegmento());
			stmt.setString(4, getTxtEndereco());
			stmt.setString(5, getTxtEnderecoNumero());
			stmt.setString(6, getTxtEnderecoComplemento());
			stmt.setString(7, getTxtEnderecoBairro());
			stmt.setString(8, getTxtEnderecoCidade());
			stmt.setString(9, getCmbEnderecoEstado());
			stmt.setString(10, getTxtEnderecoCep());
			stmt.setString(11, getTxtTelCliente());
			stmt.setString(12, getTxtFaxCliente());
			stmt.setString(13, getTxtEmail());
			stmt.setString(14, getTxtSite());
			stmt.setString(15, getTxtDtVisita());
			stmt.setString(16, getTxtTipo());
			stmt.setString(17, getCdVendedor());
			if(getTxtLembrar().trim().equals("/  /")){
				stmt.setString(18, "00/00/0000");	
			}else{
				stmt.setString(18, getTxtLembrar());
			}			
			stmt.setBoolean(19, getLembrar());
			stmt.setString(20, getNmVendedor());
			stmt.setString(21, getTxtObs());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Visita cadastrada com sucesso!\nEmpresa visitada: "+getTxtNmCliente(),"Cadastrado",1);
		}
		catch(SQLException ex){
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
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_EmpresasClientesVisitas SET nm_Empresa=?, cd_Segmento=?, nm_Endereco=?, cd_EnderecoNumero=?, ds_EnderecoComplemento=?, nm_EnderecoBairro=?, nm_EnderecoCidade=?, sg_EnderecoEstado=?, cd_Cep=?, cd_Telefone=?, cd_Fax=?, nm_Email=?, ds_Site=?, dt_Visita=?, sg_TipoVisita=?, cd_Funcionario=?, dt_Lembrar=?, sg_Lembrar=?, nm_Funcionario=?, ds_Obs=? WHERE id_Visita="+getIdVisita()+";");

			//Preenche os campos
			stmt.setString(1, getTxtNmCliente());
			stmt.setString(2, getCmbSegmento());
			stmt.setString(3, getTxtEndereco());
			stmt.setString(4, getTxtEnderecoNumero());
			stmt.setString(5, getTxtEnderecoComplemento());
			stmt.setString(6, getTxtEnderecoBairro());
			stmt.setString(7, getTxtEnderecoCidade());
			stmt.setString(8, getCmbEnderecoEstado());
			stmt.setString(9, getTxtEnderecoCep());
			stmt.setString(10, getTxtTelCliente());
			stmt.setString(11, getTxtFaxCliente());
			stmt.setString(12, getTxtEmail());
			stmt.setString(13, getTxtSite());
			stmt.setString(14, getTxtDtVisita());
			stmt.setString(15, getTxtTipo());
			stmt.setString(16, getCdVendedor());
			if(getTxtLembrar().trim().equals("/  /")){
				stmt.setString(17, "00/00/0000");	
			}else{
				stmt.setString(17, getTxtLembrar());
			}
			stmt.setBoolean(18, getLembrar());
			stmt.setString(19, getNmVendedor());
			stmt.setString(20, getTxtObs());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();

			alteraOferecidas();

			JOptionPane.showMessageDialog(null,"Visita realizada alterada com sucesso\nEmpresa visitada: "+getTxtNmCliente(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-----Cadastrar empresas oferecidas na visita
	public void ajustaOferecidas(Object[] lista){
		int contador = 0;
		while(contador<3){
			if(!lista[contador].equals("")){
				this.listaB[qtLista] = (String) lista[contador];
				this.qtLista += 1;
			}
			contador+=1;
		}
	}
	private Object[] listaB = new Object[3];
	private int qtLista = 0;
	private int getQtLista(){
		return qtLista;
	}	
	public void cadastraOferecidas(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_EmpresasOferecidas (id_Visita, nm_Empresa, dt_Visita, nm_Funcionario, nm_EmpresaOferecida) VALUES (?,?,?,?,?)");
			//Preenche os campos
			int contador = 0;
			while(contador<getQtLista()){
				stmt.setString(1, getIdVisita());
				stmt.setString(2, getTxtNmCliente());
				stmt.setString(3, getTxtDtVisita());
				stmt.setString(4, getNmVendedor());
				stmt.setString(5, (String) this.listaB[contador]);
				contador+=1;
				//Executa a instrução SQL
				stmt.execute();
			}

			stmt.close();
			con.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}		
	}
	public void alteraOferecidas(){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_EmpresasOferecidas WHERE id_Visita="+getIdVisita()+";";

			st.executeUpdate(condicao);//Remover visita da tabela empresas oferecidas			

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	public int geraCodVisita(){
		int x = 1;
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT id_Visita FROM tb_EmpresasClientesVisitas;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				x += 1;
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;
	}
	public void cadastraQuestoes(String idVisita, String dtVisita, String nmEmpresa, String questao1, String questao2, String questao3, String questao4, String questao5, String questao6, String questao7){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_QuestoesVisitas (id_Visita, dt_Visita, nm_Empresa, ds_Quest01, ds_Quest02, ds_Quest03, ds_Quest04, ds_Quest05, ds_Quest06, ds_Quest07) VALUES (?,?,?,?,?,?,?,?,?,?)");

			//Preenche os campos
			stmt.setString(1, idVisita.trim());
			stmt.setString(2, dtVisita.trim());
			stmt.setString(3, nmEmpresa.trim());
			stmt.setString(4, questao1.trim());
			stmt.setString(5, questao2.trim());
			stmt.setString(6, questao3.trim());
			stmt.setString(7, questao4.trim());
			stmt.setString(8, questao5.trim());
			stmt.setString(9, questao6.trim());
			stmt.setString(10, questao7.trim());

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
	public void alteraQuestoes(String idVisita, String dtVisita, String nmEmpresa, String questao1, String questao2, String questao3, String questao4, String questao5, String questao6, String questao7){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_QuestoesVisitas SET dt_Visita=?, nm_Empresa=?, ds_Quest01=?, ds_Quest02=?, ds_Quest03=?, ds_Quest04=?, ds_Quest05=?, ds_Quest06=?, ds_Quest07=? WHERE id_Visita='"+idVisita+"';");

			//Preenche os campos
			stmt.setString(1, dtVisita);
			stmt.setString(2, nmEmpresa);
			stmt.setString(3, questao1);
			stmt.setString(4, questao2);
			stmt.setString(5, questao3);
			stmt.setString(6, questao4);
			stmt.setString(7, questao5);
			stmt.setString(8, questao6);
			stmt.setString(9, questao7);

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
	//---Atributo e métodos para combobox vendedores
	private Vector vendedores = new Vector();//Armazena itens da cmbRepresentadas

	//--Retorna os componentes cadastrados
	public Vector getVendedores(){
		return this.vendedores;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbVendedores(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT nm_Funcionario FROM tb_Funcionarios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				vendedores.addElement(rs.getString("nm_Funcionario"));
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public String consultarVendedor(String nmFunc){
		String cdVendedor = "";
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_Funcionario FROM tb_Funcionarios WHERE nm_Funcionario='"+nmFunc+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			cdVendedor = rs.getString("cd_Funcionario");			

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return cdVendedor;
	}
	//---Atributo e métodos para combobox representadas
	private Vector representadas = new Vector();//Armazena itens da cmbRepresentadas

	//--Retorna os componentes cadastrados
	public Vector getRepresentadas(){
		return this.representadas;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbRepresentadas(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT nm_EmpresaRepresentada FROM tb_EmpresasRepresentadas;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				representadas.addElement(rs.getString("nm_EmpresaRepresentada"));
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public int verificaExiste(String nome, String data){
		int x = 0;
		try {			
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_EmpresasClientesVisitas WHERE nm_Empresa='"+nome+"' AND dt_Visita='"+data+"';";

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
	public void remover(String idVisita, String nmEmpresa){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_EmpresasClientesVisitas WHERE id_Visita='"+idVisita+"';";
			String condicao2 ="DELETE FROM tb_EmpresaClienteVisitaContatos WHERE id_Visita='"+idVisita+"';";
			String condicao3 ="DELETE FROM tb_QuestoesVisitas WHERE id_Visita='"+idVisita+"';";
			String condicao4 ="DELETE FROM tb_EmpresasOferecidas WHERE id_Visita='"+idVisita+"';";
			String condicao5 ="DELETE FROM tb_Contatos WHERE nm_Empresa='"+nmEmpresa+"';";

			st.executeUpdate(condicao);//Remover visita da tabela empresaClientesVIsitas
			st.executeUpdate(condicao2);//Remover visita da tabela EmpresaClienteVisitaContatos
			st.executeUpdate(condicao3);//Remover visita da tabela Questoes
			st.executeUpdate(condicao4);//Remover visita da tabela EmpresaOferecidas
			st.executeUpdate(condicao5);//Remover visita da tabela contatos

			st.close();
			con.close();

			JOptionPane.showMessageDialog(null,"Visita realizada excluída com sucesso","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}

	@SuppressWarnings("unchecked")
	public JasperPrint gerarRelatorio(String nmEmpresaVisita, String nmVendedor){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			if(nmEmpresaVisita.equals("")){
				if(nmVendedor.equals("")){
					condicao="SELECT * FROM tb_EmpresasClientesVisitas ORDER BY dt_Visita;";
				}else{
					condicao="SELECT * FROM tb_EmpresasClientesVisitas WHERE nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Visita;";
				}
			}else{
				if(nmVendedor.equals("")){
					condicao="SELECT * FROM tb_EmpresasClientesVisitas WHERE nm_Empresa LIKE '"+nmEmpresaVisita+"%' ORDER BY dt_Visita;";
				}else{
					condicao="SELECT * FROM tb_EmpresasClientesVisitas WHERE nm_Empresa LIKE '"+nmEmpresaVisita+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Visita;";
				}
			}

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			try {
				String arquivoJasper = "relatorios/RelEmpresasClientesVisitas.jasper";
				String arquivoSubJasper = "relatorios/subRelOferecidas.jasper";
				HashMap map = new HashMap();
				map.put("nmEmpresa", nmEmpresaVisita);
				map.put("dirSubRel", arquivoSubJasper);
				map.put("REPORT_CONNECTION", con);
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
	@SuppressWarnings("unchecked")
	public JasperPrint gerarRelatorioVisita(String nmEmpresaVisita, String dtVisita, String nmVendedor){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			try {
				String arquivoJasper = "relatorios/RelEmpresasClientesVisitas.jasper";
				String arquivoSubJasper = "relatorios/subRelOferecidas.jasper";
				HashMap map = new HashMap();
				map.put("nmEmpresa", nmEmpresaVisita);
				map.put("dtVisita", dtVisita);
				map.put("nmVendedor", nmVendedor);
				map.put("dirSubRel", arquivoSubJasper);
				rel = JasperFillManager.fillReport(arquivoJasper, map, con);
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
			String arquivoJasper = "relatorios/RelVisitasResumido.jasper";
			rel = JasperFillManager.fillReport(arquivoJasper, map, con);
		} catch (JRException e) {

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		}
		return rel;
	}
	@SuppressWarnings("unchecked")
	public JasperPrint gerarRelatorioContatos(String nmEmpresa, String dtVisita){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			condicao= "SELECT * FROM tb_EmpresaClienteVisitaContatos WHERE nm_Empresa='"+nmEmpresa+"';";

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			try {				
				HashMap map = new HashMap();
				map.put("nmEmpresa", nmEmpresa);
				map.put("dtVisita", dtVisita);
				String arquivoJasper = "relatorios/RelEmpresaVisitasContatosA.jasper";
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
	@SuppressWarnings("unchecked")
	public JasperPrint gerarRelatorioQuestoes(String nmEmpresa, String dtVisita, String nmVendedor){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			try {				
				HashMap map = new HashMap();				
				map.put("nmEmpresa", nmEmpresa);
				map.put("dtVisita", dtVisita);
				map.put("nmFuncionario", nmVendedor);
				String arquivoJasper = "relatorios/RelEmpresasClientesVisitasQuestoes.jasper";
				rel = JasperFillManager.fillReport(arquivoJasper, map, con);
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
	public JasperPrint gerarRelatorioUteisVisitas(){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			try {				
				HashMap map = new HashMap();				
				String arquivoJasper = "relatorios/RelUteisVisitasA.jasper";
				rel = JasperFillManager.fillReport(arquivoJasper, map, con);
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
