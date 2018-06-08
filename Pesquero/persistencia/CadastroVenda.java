package persistencia;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class CadastroVenda extends ConexaoBD{

	private int registros = 0;
	//--Dados da empresa representada
	private String txtCnpjRepresentada = null;
	private String cmbNmRepresentada = null;

	//--Dados da empresa cliente
	private String txtCnpjCliente = null;
	private String cmbNmCliente = null;

	//--Dados da venda
	private String txtNtFiscal = null;
	private String txtNtPedido = null;
	private String txtDtVenda = null;
	private String txtNmProduto = null;
	private double vlProduto = 0;	
	private String cmbMedida = null;
	private int qtProduto = 0;
	private double vlAcrescido = 0;
	private String vlFinal = null; 
	private String txtDtEntrega = null;

	//--Dados do vendedor
	private String txtVendedor = null;
	private String cmbVendedores = null;
	private boolean chkComissionado;

	private String txtObs = null;

	DefaultTableModel tabelaVendas = new DefaultTableModel();

	public DefaultTableModel getTabelaVendas(){
		return this.tabelaVendas;
	}

	//--Guia comissão
	private double txtComissao = 0;

	public CadastroVenda() {
		super();
	}

	public void ajustaDados(String txtCnpjRepresentada, String cmbNmRepresentada, String txtCnpjCliente, String cmbNmCliente, String txtNtFiscal, String txtDtVenda, String txtNmProduto, double vlProduto, String cmbMedida, int qtProduto, double vlAcrescido, String vlFinal, String txtDtEntrega, String txtVendedor, String cmbVendedores, boolean chkComissionado, String txtObs, double txtComissao, String txtNtPedido) {
		this.txtCnpjRepresentada = txtCnpjRepresentada;
		this.cmbNmRepresentada = cmbNmRepresentada;
		this.txtCnpjCliente = txtCnpjCliente;
		this.cmbNmCliente = cmbNmCliente;
		this.txtNtFiscal = txtNtFiscal;
		SimpleDateFormat formatIso = new SimpleDateFormat("yyyy/MM/dd");  
		SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");  
		Date date;  
		try {  
			date = formatOut.parse(txtDtVenda);
			this.txtDtVenda = formatIso.format(date);
		} catch (ParseException e) {  
			e.printStackTrace();  
		}
		this.txtNmProduto = txtNmProduto;
		this.vlProduto = vlProduto;
		this.cmbMedida = cmbMedida;
		this.qtProduto = qtProduto;
		this.vlAcrescido = vlAcrescido;
		this.vlFinal = vlFinal;
		this.txtDtEntrega = txtDtEntrega;
		this.txtVendedor = txtVendedor;
		this.cmbVendedores = cmbVendedores;
		this.chkComissionado = chkComissionado;
		this.txtObs = txtObs;
		this.txtComissao = txtComissao;
		this.txtNtPedido = txtNtPedido;
	}
	public int verificaExiste(String notaFiscal){
		int x = 0;
		try {			
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Vendas WHERE cd_NotaFiscal="+notaFiscal+";";

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
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Vendas (cd_NotaFiscal, cd_CnpjEmpresaCliente, cd_CnpjEmpresaRepresentada, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Produto, vl_Produto, sg_Tipo, qt_Produto, vl_FinalVenda, dt_Venda, dt_Entrega, ds_Observacoes, sg_Comissionado, cd_Funcionario, vl_Acrescimo, nm_Funcionario, cd_Pedido) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtNtFiscal());
			stmt.setString(2, getTxtCnpjCliente());
			stmt.setString(3, getTxtCnpjRepresentada());
			stmt.setString(4, getCmbNmCliente());
			stmt.setString(5, getCmbNmRepresentada());
			stmt.setString(6, getTxtNmProduto());
			stmt.setDouble(7, getVlProduto());
			stmt.setString(8, getCmbMedida());
			stmt.setInt(9, getQtProduto());
			stmt.setString(10, getVlFinal());
			stmt.setString(11, getTxtDtVenda());
			stmt.setString(12, getTxtDtEntrega());
			stmt.setString(13, getTxtObs());
			stmt.setBoolean(14, getChkComissionado());
			stmt.setString(15, getTxtVendedor());
			stmt.setDouble(16, getVlAcrescido());
			stmt.setString(17, getCmbVendedores());
			stmt.setString(18, getTxtNtPedido());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Venda cadastrada com sucesso!\nRealizada por: "+getTxtVendedor(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarComissao(String notaFiscal, String funcionario){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Comissao (cd_NotaFiscal, cd_Funcionario, nm_Funcionario, vl_Venda, vl_Comissao, dt_Venda) VALUES (?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, notaFiscal.trim());
			stmt.setString(2, funcionario.trim());
			stmt.setString(3, getCmbVendedores());
			stmt.setString(4, getVlFinal());
			stmt.setDouble(5, getTxtComissao());
			stmt.setString(6, getTxtDtVenda());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();

			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_Vendas set sg_Comissionado=?, vl_Comissao=? WHERE cd_NotaFiscal='"+notaFiscal+"';");
			stmt2.setBoolean(1, true);
			stmt2.setDouble(2, getTxtComissao());

			stmt2.execute();
			stmt2.close();

			con.close();
			JOptionPane.showMessageDialog(null,"Comissão sobre venda cadastrada com sucesso!\nRealizada por: "+getTxtVendedor(),"Cadastrado",1);
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
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Vendas set cd_CnpjEmpresaCliente=?, cd_CnpjEmpresaRepresentada=?, nm_EmpresaCliente=?, nm_EmpresaRepresentada=?, nm_Produto=?, vl_Produto=?, sg_Tipo=?, qt_Produto=?, vl_FinalVenda=?, dt_Venda=?, dt_Entrega=?, ds_Observacoes=?, cd_Funcionario=?, vl_Acrescimo=?, nm_Funcionario=? WHERE cd_NotaFiscal='"+getTxtNtFiscal()+"';");

			//Preenche os campos
			stmt.setString(1, getTxtCnpjCliente());
			stmt.setString(2, getTxtCnpjRepresentada());
			stmt.setString(3, getCmbNmCliente());
			stmt.setString(4, getCmbNmRepresentada());
			stmt.setString(5, getTxtNmProduto());
			stmt.setDouble(6, getVlProduto());
			stmt.setString(7, getCmbMedida());
			stmt.setInt(8, getQtProduto());
			stmt.setString(9, getVlFinal());
			stmt.setString(10, getTxtDtVenda());
			stmt.setString(11, getTxtDtEntrega());
			stmt.setString(12, getTxtObs());
			stmt.setString(13, getTxtVendedor());
			stmt.setDouble(14, getVlAcrescido());
			stmt.setString(15, getCmbVendedores());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Venda alterada com sucesso!\nRealizada por: "+getTxtVendedor(),"Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarComissao(String notaFiscal, String funcionario){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			String condicao ="DELETE FROM tb_Comissao WHERE cd_NotaFiscal='"+notaFiscal+"';";

			st.executeUpdate(condicao);//Remover comissao

			st.close();
			con.close();

			cadastrarComissao(notaFiscal, funcionario);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultar(String notaFiscal){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			condicao= "SELECT * FROM tb_Vendas WHERE cd_NotaFiscal='"+notaFiscal+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.txtCnpjRepresentada = rs.getString("cd_CnpjEmpresaRepresentada");
			this.cmbNmRepresentada = rs.getString("nm_EmpresaRepresentada");
			this.txtCnpjCliente = rs.getString("cd_CnpjEmpresaCliente");
			this.cmbNmCliente = rs.getString("nm_EmpresaCliente");
			this.txtNtFiscal = rs.getString("cd_NotaFiscal");
			this.txtNtPedido = rs.getString("cd_Pedido");
			this.txtDtVenda = rs.getString("dt_Venda");
			this.txtNmProduto = rs.getString("nm_Produto");			
			this.vlProduto = rs.getDouble("vl_Produto");			
			this.cmbMedida = rs.getString("sg_Tipo");			
			this.qtProduto = rs.getInt("qt_Produto");			
			this.vlAcrescido = rs.getDouble("vl_Acrescimo");	
			this.vlFinal = rs.getString("vl_FinalVenda");
			this.txtDtEntrega = rs.getString("dt_Entrega");
			this.txtVendedor = rs.getString("cd_Funcionario");
			this.cmbVendedores = rs.getString("nm_Funcionario");
			this.chkComissionado = rs.getBoolean("sg_Comissionado");
			this.txtObs = rs.getString("ds_Observacoes");
			this.txtNtPedido = rs.getString("cd_Pedido");

			if(rs.getBoolean("sg_Comissionado")){
				consultarComissao(notaFiscal);	
			}			

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarComissao(String notaFiscal){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condições de pesquisa
			condicao= "SELECT vl_Comissao FROM tb_Comissao WHERE cd_NotaFiscal='"+notaFiscal+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.txtComissao = rs.getDouble("vl_Comissao");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}	
	public void consultar(String nmCliente, String nmRepresentada, String nmVendedor, String periodoDe, String periodoA){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			
			if(!periodoDe.equals("/  /") || !periodoA.equals("/  /")){
				SimpleDateFormat formatIso = new SimpleDateFormat("yyyy/MM/dd");  
				SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");  
				Date dateDe;
				Date dateA;
				try {  
					dateDe = formatOut.parse(periodoDe);
					periodoDe = formatIso.format(dateDe);
					dateA = formatOut.parse(periodoA);
					periodoA = formatIso.format(dateA);
				} catch (ParseException e) {  
					e.printStackTrace();  
				}	
			}			
			
			String condicao = "";

			condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas ORDER BY dt_Venda;";

			if(nmCliente.equals("")){
				if(nmRepresentada.equals("")){
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}else{
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}
			}else{
				if(nmRepresentada.equals("")){
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}else{
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}
			}
			Statement st = con.createStatement();
			
			

			//Executa o código sql para pesquisar empresas representadas
			ResultSet rs = st.executeQuery(condicao);

			tabelaVendas.addColumn("Nota fiscal");
			tabelaVendas.addColumn("Pedido");
			tabelaVendas.addColumn("Empresa cliente");
			tabelaVendas.addColumn("Empresa representada");
			tabelaVendas.addColumn("Vendedor");
			tabelaVendas.addColumn("Data da venda");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[6];
				linha[0] = ""+rs.getInt("cd_NotaFiscal");
				linha[1] = ""+rs.getInt("cd_Pedido");
				linha[2] = rs.getString("nm_EmpresaCliente");
				linha[3] = rs.getString("nm_EmpresaRepresentada");
				linha[4] = rs.getString("nm_Funcionario");
				//SimpleDateFormat formatIso = new SimpleDateFormat("yyyy/MM/dd");  
				SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");  
				Date date;  
				date = rs.getDate("dt_Venda");
				linha[5] = formatOut.format(date);
				tabelaVendas.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}

	public void remover(String notaFiscal){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_Vendas WHERE cd_NotaFiscal='"+notaFiscal+"';";
			String condicao2 = "DELETE FROM tb_Comissao WHERE cd_NotaFiscal='"+notaFiscal+"';";

			st.executeUpdate(condicao);//Remover venda da tabela de vendas
			st.executeUpdate(condicao2);//Remover venda da tabela de comissão

			st.close();
			con.close();

			JOptionPane.showMessageDialog(null,"Venda excluída com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}


	//-----------------------Campos dinâmicos
	public String cnpjCliente(String nmEmpresa){
		String cnpj = "";
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_CnpjEmpresaCliente FROM tb_EmpresasClientes WHERE nm_EmpresaCliente='"+nmEmpresa+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			cnpj = rs.getString("cd_CnpjEmpresaCliente");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return cnpj;
	}
	public String cnpjRepresentada(String nmEmpresa){
		String cnpj = "";
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_CnpjEmpresaRepresentada FROM tb_EmpresasRepresentadas WHERE nm_EmpresaRepresentada='"+nmEmpresa+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			cnpj = rs.getString("cd_CnpjEmpresaRepresentada");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return cnpj;
	}
	public String codFuncionario(String nmFuncionario){
		String cod = "";
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_Funcionario FROM tb_Funcionarios WHERE nm_Funcionario='"+nmFuncionario+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			cod = rs.getString("cd_Funcionario");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return cod;
	}
	//---Atributo e métodos para combobox clientes
	private Vector clientes = new Vector();//Armazena itens da cmbClientes

	//--Retorna os componentes cadastrados
	public Vector getClientes(){
		return this.clientes;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbClientes(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT nm_EmpresaCliente FROM tb_EmpresasClientes;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				clientes.addElement(rs.getString("nm_EmpresaCliente"));
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
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
	@SuppressWarnings("unchecked")
	public JasperPrint gerarRelatorio(int permissao, String nmCliente, String nmRepresentada, String nmVendedor, String periodoDe, String periodoA, String notaFiscal){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			if(!periodoDe.equals("/  /") || !periodoA.equals("/  /")){
				SimpleDateFormat formatIso = new SimpleDateFormat("yyyy/MM/dd");  
				SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");  
				Date dateDe;
				Date dateA;
				try {  
					dateDe = formatOut.parse(periodoDe);
					periodoDe = formatIso.format(dateDe);
					dateA = formatOut.parse(periodoA);
					periodoA = formatIso.format(dateA);
				} catch (ParseException e) {  
					e.printStackTrace();  
				}	
			}	
			
			String condicao = "";

			condicao="SELECT cd_NotaFiscal, nm_EmpresaCliente, nm_EmpresaRepresentada, nm_Funcionario, dt_Venda, cd_Pedido FROM tb_Vendas ORDER BY dt_Venda;";

			if(nmCliente.equals("")){
				if(nmRepresentada.equals("")){
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT * FROM tb_Vendas ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT * FROM tb_Vendas WHERE nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}else{
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELEC* FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}
			}else{
				if(nmRepresentada.equals("")){
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}else{
					if(nmVendedor.equals("")){
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}else{
						if(periodoDe.equals("/  /") || periodoA.equals("/  /")){
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' ORDER BY dt_Venda;";
						}else{
							condicao="SELECT * FROM tb_Vendas WHERE nm_EmpresaCliente LIKE '"+nmCliente+"%' AND nm_EmpresaRepresentada LIKE '"+nmRepresentada+"%' AND nm_Funcionario LIKE '"+nmVendedor+"%' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";
						}
					}
				}
			}
			if(notaFiscal.equals("")){

			}else{
				condicao="SELECT * FROM tb_Vendas WHERE cd_NotaFiscal="+notaFiscal+";";
			}

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			String x = "";

			x = consultarComissaoParam(notaFiscal);

			if(x.equals("R$ 0,00")){
				x = "Não disponível neste relatório";
			}

			try {				
				HashMap map = new HashMap();
				map.put("permissao", permissao);
				map.put("vlComissao", x);
				String arquivoJasper = "relatorios/RelVendas.jasper";
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
	public JasperPrint gerarRelatorioExtrato(int permissao, String nmVendedor, String periodoDe, String periodoA){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			if(!periodoDe.equals("/  /") || !periodoA.equals("/  /")){
				SimpleDateFormat formatIso = new SimpleDateFormat("yyyy/MM/dd");  
				SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy");  
				Date dateDe;
				Date dateA;
				try {  
					dateDe = formatOut.parse(periodoDe);
					periodoDe = formatIso.format(dateDe);
					dateA = formatOut.parse(periodoA);
					periodoA = formatIso.format(dateA);
				} catch (ParseException e) {  
					e.printStackTrace();  
				}	
			}	
			
			String condicao = "";

			condicao="SELECT * FROM tb_Vendas WHERE nm_Funcionario='"+nmVendedor+"' AND dt_Venda>='"+periodoDe+"' AND dt_Venda<='"+periodoA+"' ORDER BY dt_Venda;";

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			int qtVendas = getQtVendas(condicao);
			double vlVendas = getVlVendas(condicao);
			BigDecimal bd = new BigDecimal (vlVendas);
			bd.add (new BigDecimal (vlVendas));
			DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(LOCAL));
			String s = df.format(bd);
			String vlVendasS = "R$ "+s;			
			double vlComissoes = getVlComissoes(condicao);
			String paramPesquisado = "Vendas realizadas no período de "+periodoDe+" à "+periodoA+" pelo funcionário.";

			JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );

			try {	
				HashMap map = new HashMap();
				map.put("permissao", permissao);
				map.put("paramPesquisado", paramPesquisado);
				map.put("qtVendas", ""+qtVendas);
				map.put("vlComissoes", "R$ "+vlComissoes);
				map.put("vlTotal", vlVendasS);
				String arquivoJasper = "relatorios/RelVendasMontante.jasper";
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
	public int getQtVendas(String condicao){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				x+= 1;
			}	
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "aaaaaaaaa", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;		
	}
	private static final Locale LOCAL = new Locale("pt","BR");

	public double getVlVendas(String condicao){
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				String z = rs.getString("vl_FinalVenda").substring(3).replace(".", "").replace(",", ".");
				x += Double.parseDouble(z);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "bbbbbbbbbbbbbbbbbbbb", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;		
	}
	public double getVlComissoes(String condicao){
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				x+= rs.getDouble("vl_Comissao");
			}	

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "cccccccccccccccccccc", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;		
	}
	public String consultarComissaoParam(String notaFiscal){
		String x = "0,00";
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "";

			//Condições de pesquisa
			if(notaFiscal.equals("")){

			}else{
				condicao= "SELECT vl_Comissao FROM tb_Comissao WHERE cd_NotaFiscal="+notaFiscal+";";	

				Statement st = con.createStatement();

				//Executa o código sql para pesquisar usuário
				ResultSet rs = st.executeQuery(condicao);

				if(rs.next()){
					x = rs.getString("vl_Comissao");
				}
				st.close();
			}

			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return "R$ "+x;
	}
	public JasperPrint gerarRelatorioResumido(){
		JasperPrint rel = null;
		try {
			Connection con = DriverManager.getConnection(getUrlBd(), getUsuarioBd(), getSenhaBd());
			HashMap map = new HashMap();
			String arquivoJasper = "relatorios/RelVendasResumido.jasper";
			rel = JasperFillManager.fillReport(arquivoJasper, map, con);
		} catch (JRException e) {

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		}
		return rel;
	}
	public JasperPrint gerarRelatorioUteisVenda(){
		JasperPrint rel = null;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			Statement st = con.createStatement();

			try {				
				HashMap map = new HashMap();				
				String arquivoJasper = "relatorios/RelUteisVenda.jasper";
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

//	-----------------Retorno de dados
	public boolean getChkComissionado() {
		return chkComissionado;
	}
	public String getCmbMedida() {
		return cmbMedida;
	}
	public String getCmbNmCliente() {
		return cmbNmCliente;
	}
	public String getCmbNmRepresentada() {
		return cmbNmRepresentada;
	}
	public String getCmbVendedores() {
		return cmbVendedores;
	}
	public String getTxtCnpjCliente() {
		return txtCnpjCliente;
	}
	public String getTxtCnpjRepresentada() {
		return txtCnpjRepresentada;
	}
	public double getTxtComissao() {
		return txtComissao;
	}
	public String getTxtDtEntrega() {
		return txtDtEntrega;
	}
	public String getTxtDtVenda() {
		return txtDtVenda;
	}
	public String getTxtNmProduto() {
		return txtNmProduto;
	}
	public String getTxtNtFiscal() {
		return txtNtFiscal;
	}
	public String getTxtNtPedido() {
		return txtNtPedido;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public int getQtProduto() {
		return qtProduto;
	}
	public String getTxtVendedor() {
		return txtVendedor;
	}
	public double getVlAcrescido() {
		return vlAcrescido;
	}
	public String getVlFinal() {
		return vlFinal;
	}
	public double getVlProduto() {
		return vlProduto;
	}
	public int getRegistros(){
		return registros;
	}
}
