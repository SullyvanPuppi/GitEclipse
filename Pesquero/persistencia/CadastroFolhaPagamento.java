package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CadastroFolhaPagamento extends ConexaoBD{

	private String txtFuncionario = null;
	private String txtNmFuncionario = null;
	private String txtMes = null;
	private String txtDtFechamento = null;
	private String txtQtHorasExtras = null;
	private String txtSalarioBase = null;
	private String txtVlTransporte = null;
	private String txtQtdependentes = null;
	private String txtSalarioFamilia = null;
	private String txtQtFaltas = null;
	private String txtVlInss = null;
	private String txtVlIr = null;
	private String txtVlFgts = null;
	private String txtVlRefeicao = null;
	private String txtVlSalarioLiquido = null;
	private String txtNotaFiscal = null;
	private String txtCliente = null;
	private String txtRepresentada = null;
	private String txtProduto = null;
	private String txtValorVenda = null;
	private String txtVenda = null;
	
	private double vlBase = 0;
	private double vlHorasExtras = 0;
	
	public CadastroFolhaPagamento() {
		super();
	}
	public void ajustaDados(String txtFuncionario, String txtNmFuncionario, String txtMes, String txtDtFechamento, String txtQtHorasExtras, String txtSalarioBase, String txtVlTransporte, String txtQtdependentes, String txtSalarioFamilia, String txtQtFaltas, String txtVlInss, String txtVlIr, String txtVlFgts, String txtVlRefeicao, String txtVlSalarioLiquido, String txtNotaFiscal, String txtCliente, String txtRepresentada, String txtProduto, String txtValorVenda, String txtVenda) {
		this.txtFuncionario = txtFuncionario;
		this.txtNmFuncionario = txtNmFuncionario;
		this.txtMes = txtMes;
		this.txtDtFechamento = txtDtFechamento;
		this.txtQtHorasExtras = txtQtHorasExtras;
		this.txtSalarioBase = txtSalarioBase;
		this.txtVlTransporte = txtVlTransporte;
		this.txtQtdependentes = txtQtdependentes;
		this.txtSalarioFamilia = txtSalarioFamilia;
		this.txtQtFaltas = txtQtFaltas;
		this.txtVlInss = txtVlInss;
		this.txtVlIr = txtVlIr;
		this.txtVlFgts = txtVlFgts;
		this.txtVlRefeicao = txtVlRefeicao;
		this.txtVlSalarioLiquido = txtVlSalarioLiquido;
		this.txtNotaFiscal = txtNotaFiscal;
		this.txtCliente = txtCliente;
		this.txtRepresentada = txtRepresentada;
		this.txtProduto = txtProduto;
		this.txtValorVenda = txtValorVenda;
		this.txtVenda = txtVenda;
	}
	public double vlTransporte(double salBase){
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT vl_Transporte FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			x = (salBase/100) * rs.getDouble("vl_Transporte");
						
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;
	}
	public int direitoTansporte(String cdFuncionario){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT sg_Transporte FROM tb_Funcionarios WHERE cd_Funcionario='"+cdFuncionario+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			if(rs.getBoolean("sg_Transporte")){
				x = 1;
			}else{
				x = 0;
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
	public int direitoFamilia(String cdFuncionario){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT sg_Familia FROM tb_Funcionarios WHERE cd_Funcionario='"+cdFuncionario+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			if(rs.getBoolean("sg_Familia")){
				x = 1;
			}else{
				x = 0;
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
	public int direitoRefeicao(String cdFuncionario){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT sg_Refeicao FROM tb_Funcionarios WHERE cd_Funcionario='"+cdFuncionario+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			if(rs.getBoolean("sg_Refeicao")){
				x = 1;
			}else{
				x = 0;
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
	public int getDependentes(String cdFuncionario){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT qt_Dependentes FROM tb_Funcionarios WHERE cd_Funcionario='"+cdFuncionario+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				x = rs.getInt("qt_Dependentes");
			}else{
				x = 0;
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
	public double vlSalarioFamila(double salBase){
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT vl_Familia1, vl_Familia2, pc_Familia1, pc_Familia2 FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();
			
			double vlFamilia1 = 0;
			double vlFamilia2 = 0;
			double pcFamilia1 = 0;
			double pcFamilia2 = 0;
			
			vlFamilia1 = rs.getDouble("vl_Familia1");
			vlFamilia2 = rs.getDouble("vl_Familia2");
			
			pcFamilia1 = rs.getDouble("pc_Familia1");
			pcFamilia2 = rs.getDouble("pc_Familia2");
			
			if(salBase<=vlFamilia2){
				if(salBase<=vlFamilia1){
					x = pcFamilia1;
				}else{
					x = pcFamilia2;
				}
			}else{
				x = 0;
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
	public double vlInss(double salBase){
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT vl_Inss1, vl_Inss2, vl_Inss3, pc_Inss1, pc_Inss2, pc_Inss3 FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();
			
			double vlInss1 = 0;
			double vlInss2 = 0;
			double vlInss3 = 0;
			double pcInss1 = 0;
			double pcInss2 = 0;
			double pcInss3 = 0;
			
			vlInss1 = rs.getDouble("vl_Inss1");
			vlInss2 = rs.getDouble("vl_Inss2");
			vlInss3 = rs.getDouble("vl_Inss3");
			
			pcInss1 = rs.getDouble("pc_Inss1");
			pcInss2 = rs.getDouble("pc_Inss2");
			pcInss3 = rs.getDouble("pc_Inss3");
			
			if(salBase<=vlInss3){
				if(salBase<=vlInss2){
					if(salBase<=vlInss1){
						x = (salBase/100)*pcInss1;
					}else{
						x = (salBase/100)*pcInss2;
					}
				}else{
					x = (salBase/100)*pcInss3;
				}
			}else{
				x = (salBase/100)*pcInss3;
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
	public double vlIrrf(double salBase, int qtFaltas, String cdFuncionario){
		System.out.println(salBase+"-----"+qtFaltas+"-----"+cdFuncionario);
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();
			
			double vlIr1 = 0;
			double vlIr2 = 0;
			//double vlIr3 = 0;
			
			//double pcIr1 = 0;
			double pcIr2 = 0;
			double pcIr3 = 0;
			
			//double vlIr1Ded = 0;
			double vlIr2Ded = 0;
			double vlIr3Ded = 0;
			
			double deducaoDependentes = 0;
			
			vlIr1 = rs.getDouble("vl_Ir1");
			vlIr2 = rs.getDouble("vl_Ir2");
			//vlIr3 = rs.getDouble("vl_Ir3");
						
			//pcIr1 = rs.getDouble("pc_Ir1");
			pcIr2 = rs.getDouble("pc_Ir2");
			//pcIr3 = rs.getDouble("pc_Ir3");
			
			//vlIr1Ded = rs.getDouble("vl_IrDed1");
			vlIr2Ded = rs.getDouble("vl_IrDed2");
			vlIr3Ded = rs.getDouble("vl_IrDed3");
						
			deducaoDependentes = rs.getDouble("vl_Dependente");
			
			double deducaoDep = getDependentes(cdFuncionario) * deducaoDependentes;
			
			double vlFaltas = (salBase/30)*qtFaltas;
			
			System.out.println("salBase "+salBase);
			
			System.out.println("Ded dependentes "+deducaoDep);
			
			System.out.println("Qt faltas "+vlFaltas);
			
			//this.vlBase = ((salBase+getVlHorasExtras()+vlRefeicao())- vlFaltas);
			this.vlBase = ((salBase+getVlHorasExtras())- vlFaltas);
			
			System.out.println("GetBase "+getVlBase());
			
			if(getVlBase()>vlIr1){
				if(getVlBase()<vlIr2){
					x = (((getVlBase() - deducaoDependentes - vlInss(salBase))/100) * pcIr2) - vlIr2Ded;
					System.out.println("pc ir2 "+pcIr2);
					System.out.println("Inss "+vlInss(salBase));
					System.out.println("um porcento "+(getVlBase() - deducaoDependentes - vlInss(salBase))/100);
					System.out.println("Ded depe "+deducaoDependentes);
					System.out.println("vl ir ded2 "+vlIr2Ded);
				}else{
					x = (((getVlBase() - deducaoDependentes - vlInss(salBase))/100) * pcIr3) - vlIr3Ded;
				}	
			}else{
				System.out.println("ZERO ZERO ZERO");
				x = 0.0;
			}
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		System.out.println("Desconto "+x);
		return x;
	}
	public double vlFgts(double salBase){
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT vl_Fgts FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			x = (salBase/100) * rs.getDouble("vl_Fgts");
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;
	}
	public double vlRefeicao(){
		double x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT vl_Refeicao FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			x = rs.getDouble("vl_Refeicao");
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;
	}
	public int qtFuncionarios(){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_Funcionario FROM tb_Funcionarios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			while(rs.next()){
				x += 1;
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
	public int folhaCadastrados(String mes){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_Funcionario FROM tb_Funcionarios WHERE dt_Mes='"+mes+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			while(rs.next()){
				x += 1;
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
	public void cancelarFolha(String mes){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_FolhaPagamento WHERE dt_Fechamento='"+mes+"';";
			
			st.executeUpdate(condicao);
			
			st.close();
			con.close();
			
			JOptionPane.showMessageDialog(null,"Folha de pagamento cancelada","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}			
	}
	public void consultar(String codFuncionario){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	
	
	
	public void avancaFuncionario(String codFunc){
			
	}
	public void recuaFuncionario(String codFunc){
		
	}
	public void primeiroFuncionario(String codFunc){
		
	}
	public void ultimoFuncionario(String codFunc){
		
	}
	//----Retorno de dados
	public String getTxtCliente() {
		return txtCliente;
	}
	public String getTxtDtFechamento() {
		return txtDtFechamento;
	}
	public String getTxtFuncionario() {
		return txtFuncionario;
	}
	public String getTxtMes() {
		return txtMes;
	}
	public String getTxtNmFuncionario() {
		return txtNmFuncionario;
	}
	public String getTxtNotaFiscal() {
		return txtNotaFiscal;
	}
	public String getTxtProduto() {
		return txtProduto;
	}
	public String getTxtQtdependentes() {
		return txtQtdependentes;
	}
	public String getTxtQtFaltas() {
		return txtQtFaltas;
	}
	public String getTxtQtHorasExtras() {
		return txtQtHorasExtras;
	}
	public String getTxtRepresentada() {
		return txtRepresentada;
	}
	public String getTxtSalarioBase() {
		return txtSalarioBase;
	}
	public String getTxtSalarioFamilia() {
		return txtSalarioFamilia;
	}
	public String getTxtValorVenda() {
		return txtValorVenda;
	}
	public String getTxtVenda() {
		return txtVenda;
	}
	public String getTxtVlFgts() {
		return txtVlFgts;
	}
	public String getTxtVlInss() {
		return txtVlInss;
	}
	public String getTxtVlIr() {
		return txtVlIr;
	}
	public String getTxtVlRefeicao() {
		return txtVlRefeicao;
	}
	public String getTxtVlSalarioLiquido() {
		return txtVlSalarioLiquido;
	}
	public String getTxtVlTransporte() {
		return txtVlTransporte;
	}
	public double getVlBase(){
		return vlBase;
	}
	public double getVlHorasExtras(){
		return vlHorasExtras;
	}
}
