package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastroImovel extends ConexaoBD{

	public String getNmImovel() {
		return nmImovel;
	}
	public String getNmEndereco() {
		return nmEndereco;
	}
	public String getNmBairro() {
		return nmBairro;
	}
	public String getNmCidade() {
		return nmCidade;
	}
	public String getSgEstado(){
		return sgEstado;
	}
	public String getNmTipo() {
		return nmTipo;
	}
	public String getNmTamanho() {
		return nmTamanho;
	}
	public String getDormitorios() {
		return dormitorios;
	}
	public String getDtInicio() {
		return dtInicio;
	}
	public String getDtEntrega() {
		return dtEntrega;
	}
	public String getDsImovel() {
		return dsImovel;
	}
	public String getVlImovel() {
		return vlImovel;
	}
	public String getDsFormaPagamento() {
		return dsFormaPagamento;
	}
	public String getDsFoto1() {
		return dsFoto1;
	}
	public String getDsFoto2() {
		return dsFoto2;
	}
	public String getDsFoto3() {
		return dsFoto3;
	}
	public String getDsFoto4() {
		return dsFoto4;
	}
	public String getDsFoto5() {
		return dsFoto5;
	}

	private String nmImovel;

	private String nmEndereco;

	private String nmBairro;

	private String nmCidade;
	
	private String sgEstado;

	private String nmTipo;

	private String nmTamanho;

	private String dormitorios;

	private String dtInicio;

	private String dtEntrega;

	private String dsImovel;

	private String vlImovel;

	private String dsFormaPagamento;

	private String dsFoto1;

	private String dsFoto2;

	private String dsFoto3;

	private String dsFoto4;

	private String dsFoto5;

	private String cdImovel;

	public String getCdImovel(){
		return this.cdImovel;
	}

	DefaultTableModel tabelaimoveis = new DefaultTableModel();

	public DefaultTableModel getTabelaImoveis(){
		return this.tabelaimoveis;
	}

	public void ajustaDados(String cdImovel, String nmImovel, String nmEndereco, String nmBairro, String nmCidade, String sgEstado, String nmTipo, String nmTamanho, String dormitorios, String dtInicio, String dtEntrega, String dsImovel, String vlImovel, String dsFormaPagamento, String dsFoto1, String dsFoto2, String dsFoto3, String dsFoto4, String dsFoto5) {
		this.cdImovel = cdImovel;
		this.nmImovel = nmImovel;
		this.nmEndereco = nmEndereco;
		this.nmBairro = nmBairro;
		this.nmCidade = nmCidade;
		this.sgEstado = sgEstado;
		this.nmTipo = nmTipo;
		this.nmTamanho = nmTamanho;
		this.dormitorios = dormitorios;
		this.dtInicio = dtInicio;
		this.dtEntrega = dtEntrega;
		this.dsImovel = dsImovel;
		this.vlImovel = vlImovel;
		this.dsFormaPagamento = dsFormaPagamento;
		this.dsFoto1 = dsFoto1;
		this.dsFoto2 = dsFoto2;
		this.dsFoto3 = dsFoto3;
		this.dsFoto4 = dsFoto4;
		this.dsFoto5 = dsFoto5;
	}
	public int verificaExiste(String nmImovel, String cdImovel){
		int x = 0;
		try {			
			//Estabelece a conexï¿½o
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condiï¿½ï¿½es de pesquisa
			String condicao= "SELECT * FROM tb_imoveis WHERE nm_imovel='"+nmImovel+"' OR cd_Imovel='"+cdImovel+"';";

			Statement st = con.createStatement();

			//Executa o cï¿½digo sql para pesquisar usuï¿½rio
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				if(rs.getString("nm_imovel").equals(nmImovel)){
					x = 1;
				}else{
					x=3;
				}
				if(rs.getString("cd_imovel").equals(cdImovel)){
					x = 2;	
				}else{
					x=3;
				}
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
	public int geraCodImovel(){
		int x = 1;
		try {						
			//Estabelece a conexï¿½o
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condiï¿½ï¿½es de pesquisa
			String condicao= "SELECT cd_imovel FROM tb_imoveis order by cd_imovel asc;";

			Statement st = con.createStatement();

			//Executa o cï¿½digo sql para pesquisar usuï¿½rio
			ResultSet rs = st.executeQuery(condicao);

			String ultimo = "";
			while(rs.next()){
				ultimo=rs.getString("cd_imovel");
			}
			int y = 0;
			if(!ultimo.equals("")){
				y = Integer.parseInt(ultimo.substring(6));
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
	public void cadastrar(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexï¿½o
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o cï¿½digo sql para cadastrar usuï¿½rio
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_imoveis (cd_Imovel, nm_imovel, " +
					"nm_endereco, nm_bairro, nm_cidade, sg_Estado, nm_tipo, qt_tamanho, qt_dormitorios, dt_inicio, dt_entrega, " +
					"ds_imovel, vl_imovel, ds_formapagamento, ds_foto1, ds_foto2, ds_foto3, ds_foto4, ds_foto5" +
			") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getCdImovel());
			stmt.setString(2, getNmImovel());
			stmt.setString(3, getNmEndereco());
			stmt.setString(4, getNmBairro());
			stmt.setString(5, getNmCidade());
			stmt.setString(6, getSgEstado());
			stmt.setString(7, getNmTipo());
			stmt.setString(8, getNmTamanho());
			stmt.setString(9, getDormitorios());
			stmt.setString(10, getDtInicio());
			stmt.setString(11, getDtEntrega());
			stmt.setString(12, getDsImovel());
			stmt.setString(13, getVlImovel());
			stmt.setString(14, getDsFormaPagamento());
			if(!getDsFoto1().equals("")){
				stmt.setString(15, "imagens/"+getCdImovel()+"a.jpg");	
			}else{
				stmt.setString(15, "");
			}
			if(!getDsFoto2().equals("")){
				stmt.setString(16, "imagens/"+getCdImovel()+"b.jpg");	
			}else{
				stmt.setString(16, "");
			}
			if(!getDsFoto3().equals("")){
				stmt.setString(17, "imagens/"+getCdImovel()+"c.jpg");	
			}else{
				stmt.setString(17, "");
			}
			if(!getDsFoto4().equals("")){
				stmt.setString(18, "imagens/"+getCdImovel()+"d.jpg");	
			}else{
				stmt.setString(18, "");
			}
			if(!getDsFoto5().equals("")){
				stmt.setString(19, "imagens/"+getCdImovel()+"e.jpg");	
			}else{
				stmt.setString(19, "");
			}
			
			//Executa a instruï¿½ï¿½o SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Imóvel cadastrado com sucesso!\nImóvel: "+getNmImovel(),"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterar(String cdImovel){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexï¿½o
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o cï¿½digo sql para cadastrar usuï¿½rio
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_imoveis set nm_imovel=?, nm_endereco=?, nm_bairro=?, nm_cidade=?, sg_estado=?, " +
					"nm_tipo=?, qt_tamanho=?, qt_dormitorios=?, dt_inicio=?, dt_entrega=?, ds_imovel=?, vl_imovel=?, ds_formapagamento=?, ds_foto1=?, " +
					"ds_foto2=?, ds_foto3=?, ds_foto4=?, ds_foto5=? WHERE cd_Imovel='"+cdImovel+"';");

			//Preenche os campos
			stmt.setString(1, getNmImovel());
			stmt.setString(2, getNmEndereco());
			stmt.setString(3, getNmBairro());
			stmt.setString(4, getNmCidade());
			stmt.setString(5, getSgEstado());
			stmt.setString(6, getNmTipo());
			stmt.setString(7, getNmTamanho());
			stmt.setString(8, getDormitorios());
			stmt.setString(9, getDtInicio());
			stmt.setString(10, getDtEntrega());
			stmt.setString(11, getDsImovel());
			stmt.setString(12, getVlImovel());
			stmt.setString(13, getDsFormaPagamento());
			if(!getDsFoto1().equals("")){
				stmt.setString(14, "imagens/"+getCdImovel()+"a.jpg");	
			}else{
				stmt.setString(14, "");
			}
			if(!getDsFoto2().equals("")){
				stmt.setString(15, "imagens/"+getCdImovel()+"b.jpg");	
			}else{
				stmt.setString(15, "");
			}
			if(!getDsFoto3().equals("")){
				stmt.setString(16, "imagens/"+getCdImovel()+"c.jpg");	
			}else{
				stmt.setString(16, "");
			}
			if(!getDsFoto4().equals("")){
				stmt.setString(17, "imagens/"+getCdImovel()+"d.jpg");	
			}else{
				stmt.setString(17, "");
			}
			if(!getDsFoto5().equals("")){
				stmt.setString(18, "imagens/"+getCdImovel()+"e.jpg");	
			}else{
				stmt.setString(18, "");
			}
			
			//Executa a instruï¿½ï¿½o SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Dados do imóvel alterados com sucesso!\nImóvel: "+getNmImovel(),"Alterado",1);
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
	public void consultar(String cdImovel, String nmImovel, String tipo){
		try {
			//Estabelece a conexï¿½o
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condiï¿½ï¿½es de pesquisa
			if(cdImovel.trim().equals("")){
				if(nmImovel.trim().equals("")){
					if(tipo.trim().equals("--")){
						condicao= "SELECT cd_Imovel, nm_Imovel, nm_tipo FROM tb_imoveis ORDER BY cd_Imovel desc;";	
					}else{
						condicao= "SELECT cd_Imovel, nm_Imovel, nm_Tipo FROM tb_imoveis WHERE nm_tipo='"+tipo+"' ORDER BY cd_Imovel desc";
					}					
				}else{
					if(tipo.trim().equals("--")){
						condicao= "SELECT cd_Imovel, nm_Imovel, nm_tipo FROM tb_imoveis WHERE nm_imovel LIKE '"+nmImovel+"%' ORDER BY cd_Imovel desc;";	
					}else{
						condicao= "SELECT cd_Imovel, nm_Imovel, nm_Tipo FROM tb_imoveis WHERE nm_imovel LIKE '"+nmImovel+"%' AND nm_tipo='"+tipo+"' ORDER BY cd_Imovel desc";
					}
				}
			}else{
				condicao= "SELECT cd_Imovel, nm_Imovel, nm_tipo FROM tb_imoveis WHERE cd_imovel='"+cdImovel+"' ORDER BY cd_Imovel desc;";
			}
			
			Statement st = con.createStatement();

			//Executa o cï¿½digo sql para pesquisar usuï¿½rio
			ResultSet rs = st.executeQuery(condicao);

			tabelaimoveis.addColumn("Código");
			tabelaimoveis.addColumn("Nome do imóvel");
			tabelaimoveis.addColumn("Tipo");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[3];
				linha[0] = rs.getString("cd_imovel");
				linha[1] = rs.getString("nm_imovel");
				linha[2] = rs.getString("nm_tipo");
				tabelaimoveis.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarImovel(String matricula){
		try {
			//Estabelece a conexï¿½o
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			//Condiï¿½ï¿½es de pesquisa
			condicao= "SELECT * FROM tb_imoveis WHERE cd_Imovel='"+matricula+"';";

			Statement st = con.createStatement();

			//Executa o cï¿½digo sql para pesquisar usuï¿½rio
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.cdImovel = rs.getString("cd_imovel");
			this.nmImovel = rs.getString("nm_imovel");
			this.nmEndereco = rs.getString("nm_endereco");
			this.nmBairro = rs.getString("nm_bairro");
			this.nmCidade = rs.getString("nm_cidade");
			this.sgEstado = rs.getString("sg_estado");
			this.nmTipo = rs.getString("nm_tipo");
			this.nmTamanho = rs.getString("qt_tamanho");
			this.dormitorios = rs.getString("qt_dormitorios");
			this.dtInicio = rs.getString("dt_inicio");
			this.dtEntrega = rs.getString("dt_entrega");
			this.dsImovel = rs.getString("ds_imovel");
			this.vlImovel = rs.getString("vl_imovel");
			this.dsFormaPagamento = rs.getString("ds_formapagamento");
			this.dsFoto1 = rs.getString("ds_foto1");
			this.dsFoto2 = rs.getString("ds_foto2");
			this.dsFoto3 = rs.getString("ds_foto3");
			this.dsFoto4 = rs.getString("ds_foto4");
			this.dsFoto5 = rs.getString("ds_foto5");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void remover(String cdMatricula){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_imoveis WHERE cd_Imovel='"+cdMatricula+"';";
			
			st.executeUpdate(condicao);//Remover imóvel da tabela imóveis
			
			st.close();
			con.close();

			JOptionPane.showMessageDialog(null,"Imóvel excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
}