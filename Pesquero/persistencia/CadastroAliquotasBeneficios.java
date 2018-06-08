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

public class CadastroAliquotasBeneficios extends ConexaoBD {

	private double txtVlTransporte;
	private double txtVlRefeicao;
	private double txtVlFgts;
	private double txtVlInss1;
	private double txtVlInss2;
	private double txtVlInss3;
	private double txtVlInss1P;
	private double txtVlInss2P;
	private double txtVlInss3P;
	private double txtVlInssTeto;
	private double txtVlIr1;
	private double txtVlIr2;
	private double txtVlIr3;
	private double txtVlIr1P;
	private double txtVlIr2P;
	private double txtVlIr3P;
	private double txtVlIr1Ded;
	private double txtVlIr2Ded;
	private double txtVlIr3Ded;
	private double txtVlDependentes;
	private double txtVlFamilia1;
	private double txtVlFamilia2;
	private double txtVlFamilia1P;
	private double txtVlFamilia2P;
	private String hoje;
	private String atualizado;

	public CadastroAliquotasBeneficios(){
		super();
	}

	public double getTxtVlIr1Ded() {
		return txtVlIr1Ded;
	}
	public double getTxtVlIr2Ded() {
		return txtVlIr2Ded;
	}
	public double getTxtVlIr3() {
		return txtVlIr3;
	}
	public double getTxtVlIr3Ded() {
		return txtVlIr3Ded;
	}
	public double getTxtVlIr3P() {
		return txtVlIr3P;
	}
	public double getTxtVlFamilia1P() {
		return txtVlFamilia1P;
	}
	public double getTxtVlFamilia2P() {
		return txtVlFamilia2P;
	}
	public double getTxtVlInss1P() {
		return txtVlInss1P;
	}
	public double getTxtVlInss2P() {
		return txtVlInss2P;
	}
	public double getTxtVlInss3P() {
		return txtVlInss3P;
	}
	public double getTxtVlInssTeto() {
		return txtVlInssTeto;
	}
	public double getTxtVlIr1P() {
		return txtVlIr1P;
	}
	public double getTxtVlIr2P() {
		return txtVlIr2P;
	}
	public double getTxtVlDependentes() {
		return txtVlDependentes;
	}
	public double getTxtVlFamilia1() {
		return txtVlFamilia1;
	}
	public double getTxtVlFamilia2() {
		return txtVlFamilia2;
	}
	public double getTxtVlFgts() {
		return txtVlFgts;
	}
	public double getTxtVlInss1() {
		return txtVlInss1;
	}
	public double getTxtVlInss2() {
		return txtVlInss2;
	}
	public double getTxtVlInss3() {
		return txtVlInss3;
	}
	public double getTxtVlIr1() {
		return txtVlIr1;
	}
	public double getTxtVlIr2() {
		return txtVlIr2;
	}
	public double getTxtVlRefeicao() {
		return txtVlRefeicao;
	}
	public double getTxtVlTransporte() {
		return txtVlTransporte;
	}
	public String getHoje(){
		return hoje;
	}
	public String getAtualizado(){
		return atualizado;
	}

	public void ajustaDados(double txtVlTransporte, double txtVlRefeicao, double txtVlFgts, double txtVlInss1, double txtVlInss2, double txtVlInss3, double txtVlIr1, double txtVlIr2, double txtVlDependentes, double txtVlFamilia1, double txtVlFamilia2, String hoje, double txtVlInss1P, double txtVlInss2P, double txtVlInss3P, double txtVlIr1P, double txtVlIr2P, double txtVlFamilia1P, double txtVlFamilia2P, double txtVlIr3,  double txtVlIr3P,  double txtVlIrDed1,  double txtVlIrDed2,  double txtVlIrDed3, double txtVlInssTeto) {
		this.txtVlTransporte = txtVlTransporte;
		this.txtVlRefeicao = txtVlRefeicao;
		this.txtVlFgts = txtVlFgts;
		this.txtVlInss1 = txtVlInss1;
		this.txtVlInss2 = txtVlInss2;
		this.txtVlInss3 = txtVlInss3;
		this.txtVlIr1 = txtVlIr1;
		this.txtVlIr2 = txtVlIr2;
		this.txtVlDependentes = txtVlDependentes;
		this.txtVlFamilia1 = txtVlFamilia1;
		this.txtVlFamilia2 = txtVlFamilia2;
		this.hoje = hoje;
		this.txtVlInss1P = txtVlInss1P;		
		this.txtVlInss2P = txtVlInss2P;
		this.txtVlInss3P = txtVlInss3P;
		this.txtVlIr1P = txtVlIr1P;
		this.txtVlIr2P = txtVlIr2P;
		this.txtVlFamilia1P = txtVlFamilia1P;
		this.txtVlFamilia2P = txtVlFamilia2P;
		this.txtVlIr3 = txtVlIr3;
		this.txtVlIr3P = txtVlIr3P;
		this.txtVlIr1Ded  = txtVlIrDed1;
		this.txtVlIr2Ded = txtVlIrDed2;
		this.txtVlIr3Ded = txtVlIrDed3;	
		this.txtVlInssTeto = txtVlInssTeto;
	}
	public int verificaExiste(){
		int x = 0;
		try {			
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_AliquotasBeneficios;";

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
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_AliquotasBeneficios (vl_Transporte, vl_Refeicao, vl_Fgts, vl_Inss1, vl_Inss2, vl_Inss3, vl_Ir1, vl_Ir2, vl_Dependente, vl_Familia1, vl_Familia2, dt_Atualizado, pc_Inss1, pc_Inss2, pc_Inss3, pc_Ir1, pc_Ir2, pc_Familia1, pc_Familia2, vl_Ir3, pc_Ir3, vl_IrDed1, vl_IrDed2, vl_IrDed3, vl_InssTeto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setDouble(1, getTxtVlTransporte());
			stmt.setDouble(2, getTxtVlRefeicao());
			stmt.setDouble(3, getTxtVlFgts());
			stmt.setDouble(4, getTxtVlInss1());
			stmt.setDouble(5, getTxtVlInss2());
			stmt.setDouble(6, getTxtVlInss3());
			stmt.setDouble(7, getTxtVlIr1());
			stmt.setDouble(8, getTxtVlIr2());
			stmt.setDouble(9, getTxtVlDependentes());
			stmt.setDouble(10, getTxtVlFamilia1());
			stmt.setDouble(11, getTxtVlFamilia2());
			stmt.setString(12, getHoje());
			stmt.setDouble(13, getTxtVlInss1P());
			stmt.setDouble(14, getTxtVlInss2P());
			stmt.setDouble(15, getTxtVlInss3P());
			stmt.setDouble(16, getTxtVlIr1P());
			stmt.setDouble(17, getTxtVlIr2P());
			stmt.setDouble(18, getTxtVlFamilia1P());
			stmt.setDouble(19, getTxtVlFamilia2P());
			stmt.setDouble(20, getTxtVlIr3());
			stmt.setDouble(21, getTxtVlIr3P());
			stmt.setDouble(22, getTxtVlIr1Ded());
			stmt.setDouble(23, getTxtVlIr2Ded());
			stmt.setDouble(24, getTxtVlIr3Ded());
			stmt.setDouble(25, getTxtVlInssTeto());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados referentes a alíquotas e benefícios cadastrados com sucesso!","Cadastrado",1);
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
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_AliquotasBeneficios set vl_Transporte=?,  vl_Refeicao=?,  vl_Fgts=?,  vl_Inss1=?,  vl_Inss2=?,  vl_Inss3=?,  vl_Ir1=?,  vl_Ir2=?,  vl_Dependente=?,  vl_Familia1=?,  vl_Familia2=?, dt_Atualizado=?, pc_Inss1=?, pc_Inss2=?, pc_Inss3=?, pc_Ir1=?, pc_Ir2=?, pc_Familia1=?, pc_Familia2=?, vl_Ir3=?, pc_Ir3=?, vl_IrDed1=?, vl_IrDed2=?, vl_IrDed3=?, vl_InssTeto=?;");

			//Preenche os campos
			stmt.setDouble(1, getTxtVlTransporte());
			stmt.setDouble(2, getTxtVlRefeicao());
			stmt.setDouble(3, getTxtVlFgts());
			stmt.setDouble(4, getTxtVlInss1());
			stmt.setDouble(5, getTxtVlInss2());
			stmt.setDouble(6, getTxtVlInss3());
			stmt.setDouble(7, getTxtVlIr1());
			stmt.setDouble(8, getTxtVlIr2());
			stmt.setDouble(9, getTxtVlDependentes());
			stmt.setDouble(10, getTxtVlFamilia1());
			stmt.setDouble(11, getTxtVlFamilia2());
			stmt.setString(12, getHoje());
			stmt.setDouble(13, getTxtVlInss1P());
			stmt.setDouble(14, getTxtVlInss2P());
			stmt.setDouble(15, getTxtVlInss3P());
			stmt.setDouble(16, getTxtVlIr1P());
			stmt.setDouble(17, getTxtVlIr2P());
			stmt.setDouble(18, getTxtVlFamilia1P());
			stmt.setDouble(19, getTxtVlFamilia2P());
			stmt.setDouble(20, getTxtVlIr3());
			stmt.setDouble(21, getTxtVlIr3P());
			stmt.setDouble(22, getTxtVlIr1Ded());
			stmt.setDouble(23, getTxtVlIr2Ded());
			stmt.setDouble(24, getTxtVlIr3Ded());
			stmt.setDouble(25, getTxtVlInssTeto());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados referentes a alíquotas e benefícios alterados com sucesso!","Alterado",1);
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
			String condicao= "SELECT * FROM tb_AliquotasBeneficios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			rs.next();

			this.txtVlTransporte = rs.getDouble("vl_Transporte");
			this.txtVlRefeicao = rs.getDouble("vl_Refeicao");
			this.txtVlFgts = rs.getDouble("vl_Fgts");
			this.txtVlInss1 = rs.getDouble("vl_Inss1");
			this.txtVlInss2 = rs.getDouble("vl_Inss2");
			this.txtVlInss3 = rs.getDouble("vl_Inss3");
			this.txtVlIr1 = rs.getDouble("vl_Ir1");
			this.txtVlIr2 = rs.getDouble("vl_Ir2");
			this.txtVlIr3 = rs.getDouble("vl_Ir3");
			this.txtVlDependentes = rs.getDouble("vl_Dependente");
			this.txtVlFamilia1 = rs.getDouble("vl_Familia1");
			this.txtVlFamilia2 = rs.getDouble("vl_Familia2");
			this.atualizado = rs.getString("dt_Atualizado");
			this.txtVlInss1P = rs.getDouble("pc_Inss1");
			this.txtVlInss2P = rs.getDouble("pc_Inss2");
			this.txtVlInss3P = rs.getDouble("pc_Inss3");
			this.txtVlIr1P = rs.getDouble("pc_Ir1");
			this.txtVlIr2P = rs.getDouble("pc_Ir2");
			this.txtVlIr3P = rs.getDouble("pc_Ir3");
			this.txtVlFamilia1P = rs.getDouble("pc_Familia1");
			this.txtVlFamilia2P = rs.getDouble("pc_Familia2");
			this.txtVlIr1Ded = rs.getDouble("vl_IrDed1");
			this.txtVlIr2Ded = rs.getDouble("vl_IrDed2");
			this.txtVlIr3Ded = rs.getDouble("vl_IrDed3");
			this.txtVlInssTeto = rs.getDouble("vl_InssTeto");
			
			st.close();
			con.close();
		}catch(SQLException ex){
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
			String arquivoJasper = "relatorios/RelAliquotasBeneficios.jasper";
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
