package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CadastroSeguranca extends ConexaoBD{

	Object tbEmpresasClientes[] = new  Object[1000];
	public Object[] getTbEmpresasClientes(){
		return (Object[]) tbEmpresasClientes;
	}
	int bacContCliente = 0;
	public int getBacContCliente(){
		return bacContCliente;
	}
	public void geraBackupTbCliente(){
		try {
			String txtNmCliente = null;
			String txtCnpjCliente = null;
			String cmbSegmento = null;
			String txtDsAtividade = null;
			String txtClienteDesde = null;
			String txtDtCadastro = null;
			String txtTelCliente = null;
			String txtFaxCliente = null;
			String txtEmail = null;
			String txtSite = null;
			String txtObs = null;

			//--Campos de cadastro completo
			String txtRazaoCliente = null;
			String txtIeCliente = null;
			String txtEndereco = null;
			String txtEnderecoNumero = null;
			String txtEnderecoComplemento = null;
			String txtEnderecoBairro = null;
			String txtEnderecoCidade = null;
			String cmbEnderecoEstado = null;
			String txtEnderecoCep = null;
			String txtTelefone2 = null;
			String tipo = null;
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			condicao= "SELECT * FROM tb_EmpresasClientes;";

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			int x = 0;

			while(rs.next()){
				txtNmCliente = rs.getString("nm_EmpresaCliente");
				txtCnpjCliente = rs.getString("cd_CnpjEmpresaCliente");
				cmbSegmento = rs.getString("cd_Segmento");
				txtDsAtividade = rs.getString("ds_RamoAtividade");
				txtClienteDesde = rs.getString("dt_ClienteDesde");
				txtDtCadastro = rs.getString("dt_CadastroCliente");
				txtTelCliente = rs.getString("cd_Telefone");
				txtFaxCliente = rs.getString("cd_Fax");
				txtEmail = rs.getString("nm_Email");
				txtSite = rs.getString("ds_Site");
				txtObs = rs.getString("ds_Observacoes");
				txtRazaoCliente = rs.getString("nm_RazaoSocial");
				txtIeCliente = rs.getString("cd_InscricaoEstadual");
				txtEndereco = rs.getString("nm_Endereco");
				txtEnderecoNumero = rs.getString("cd_EnderecoNumero");
				txtEnderecoComplemento = rs.getString("ds_EnderecoComplemento");
				txtEnderecoBairro = rs.getString("nm_EnderecoBairro");
				txtEnderecoCidade = rs.getString("nm_EnderecoCidade");
				cmbEnderecoEstado = rs.getString("sg_EnderecoEstado");
				txtEnderecoCep = rs.getString("cd_Cep");
				txtTelefone2 = rs.getString("cd_Telefone2");
				tipo = rs.getString("sg_TipoCadastro");
				if(x==0){
					this.tbEmpresasClientes[x]= "INSERT INTO tb_EmpresasClientes (cd_CnpjEmpresaCliente, nm_EmpresaCliente, cd_Segmento, ds_RamoAtividade, dt_ClienteDesde, dt_CadastroCliente, cd_Telefone, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro) VALUES ('"+txtCnpjCliente+"', '"+txtNmCliente+"', '"+txtIeCliente+"', '"+cmbSegmento+"', '"+txtDsAtividade+"', '"+txtClienteDesde+"', '"+txtDtCadastro+"', '"+txtEndereco+"', '"+txtEnderecoNumero+"', '"+txtEnderecoComplemento+"', '"+txtEnderecoBairro+"', '"+txtEnderecoCidade+"', '"+cmbEnderecoEstado+"', '"+txtEnderecoCep+"', '"+txtTelCliente+"', '"+txtTelefone2+"', '"+txtFaxCliente+"', '"+txtEmail+"', '"+txtSite+"', '"+txtObs+"', '"+txtRazaoCliente+"', '"+tipo+"');";
				}else{
					this.tbEmpresasClientes[x]= "\nINSERT INTO tb_EmpresasClientes (cd_CnpjEmpresaCliente, nm_EmpresaCliente, cd_Segmento, ds_RamoAtividade, dt_ClienteDesde, dt_CadastroCliente, cd_Telefone, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro) VALUES ('"+txtCnpjCliente+"', '"+txtNmCliente+"', '"+txtIeCliente+"', '"+cmbSegmento+"', '"+txtDsAtividade+"', '"+txtClienteDesde+"', '"+txtDtCadastro+"', '"+txtEndereco+"', '"+txtEnderecoNumero+"', '"+txtEnderecoComplemento+"', '"+txtEnderecoBairro+"', '"+txtEnderecoCidade+"', '"+cmbEnderecoEstado+"', '"+txtEnderecoCep+"', '"+txtTelCliente+"', '"+txtTelefone2+"', '"+txtFaxCliente+"', '"+txtEmail+"', '"+txtSite+"', '"+txtObs+"', '"+txtRazaoCliente+"', '"+tipo+"');";	
				}				
				x+=1;
			}
			this.bacContCliente = x;
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	Object tbEmpresasRepresentadas[] = new  Object[1000];
	public Object[] getTbEmpresasRepresentadas(){
		return (Object[]) tbEmpresasRepresentadas;
	}
	int bacContRepresentada = 0;
	public int getBacContRepresentada(){
		return bacContRepresentada;
	}
	public void geraBackupTbRepresentada(){
		try {
			String txtNmRepresentada = null;
			String txtCnpjRepresentada = null;
			String cmbSegmento = null;
			String txtDsAtividade = null;
			String txtRepresentadaDesde = null;
			String txtDtCadastro = null;
			String txtTelRepresentada = null;
			String txtFaxRepresentada = null;
			String txtEmail = null;
			String txtSite = null;
			String txtObs = null;

			//--Campos de cadastro completo
			String txtRazaoRepresentada = null;
			String txtIeRepresentada = null;
			String txtEndereco = null;
			String txtEnderecoNumero = null;
			String txtEnderecoComplemento = null;
			String txtEnderecoBairro = null;
			String txtEnderecoCidade = null;
			String cmbEnderecoEstado = null;
			String txtEnderecoCep = null;
			String txtTelefone2 = null;
			String tipo = null;
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			condicao= "SELECT * FROM tb_EmpresasRepresentadas;";

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			int x = 0;

			while(rs.next()){
				txtNmRepresentada = rs.getString("nm_EmpresaRepresentada");
				txtCnpjRepresentada = rs.getString("cd_CnpjEmpresaRepresentada");
				cmbSegmento = rs.getString("cd_Segmento");
				txtDsAtividade = rs.getString("ds_RamoAtividade");
				txtRepresentadaDesde = rs.getString("dt_RepresentadaDesde");
				txtDtCadastro = rs.getString("dt_CadastroRepresentada");
				txtTelRepresentada = rs.getString("cd_Telefone");
				txtFaxRepresentada = rs.getString("cd_Fax");
				txtEmail = rs.getString("nm_Email");
				txtSite = rs.getString("ds_Site");
				txtObs = rs.getString("ds_Observacoes");
				txtRazaoRepresentada = rs.getString("nm_RazaoSocial");
				txtIeRepresentada = rs.getString("cd_InscricaoEstadual");
				txtEndereco = rs.getString("nm_Endereco");
				txtEnderecoNumero = rs.getString("cd_EnderecoNumero");
				txtEnderecoComplemento = rs.getString("ds_EnderecoComplemento");
				txtEnderecoBairro = rs.getString("nm_EnderecoBairro");
				txtEnderecoCidade = rs.getString("nm_EnderecoCidade");
				cmbEnderecoEstado = rs.getString("sg_EnderecoEstado");
				txtEnderecoCep = rs.getString("cd_Cep");
				txtTelefone2 = rs.getString("cd_Telefone2");
				tipo = rs.getString("sg_TipoCadastro");
				if(x==0){
					this.tbEmpresasRepresentadas[x]= "INSERT INTO tb_EmpresasRepresentadas (cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, cd_Segmento, ds_RamoAtividade, dt_RepresentadaDesde, dt_CadastroRepresentada, cd_Telefone, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro) VALUES ('"+txtCnpjRepresentada+"', '"+txtNmRepresentada+"', '"+txtIeRepresentada+"', '"+cmbSegmento+"', '"+txtDsAtividade+"', '"+txtRepresentadaDesde+"', '"+txtDtCadastro+"', '"+txtEndereco+"', '"+txtEnderecoNumero+"', '"+txtEnderecoComplemento+"', '"+txtEnderecoBairro+"', '"+txtEnderecoCidade+"', '"+cmbEnderecoEstado+"', '"+txtEnderecoCep+"', '"+txtTelRepresentada+"', '"+txtTelefone2+"', '"+txtFaxRepresentada+"', '"+txtEmail+"', '"+txtSite+"', '"+txtObs+"', '"+txtRazaoRepresentada+"', '"+tipo+"');";
				}else{
					this.tbEmpresasRepresentadas[x]= "\nINSERT INTO tb_EmpresasRepresentadas (cd_CnpjEmpresaRepresentada, nm_EmpresaRepresentada, cd_Segmento, ds_RamoAtividade, dt_RepresentadaDesde, dt_CadastroRepresentada, cd_Telefone, cd_Fax, nm_Email, ds_Site, ds_Observacoes, sg_TipoCadastro) VALUES ('"+txtCnpjRepresentada+"', '"+txtNmRepresentada+"', '"+txtIeRepresentada+"', '"+cmbSegmento+"', '"+txtDsAtividade+"', '"+txtRepresentadaDesde+"', '"+txtDtCadastro+"', '"+txtEndereco+"', '"+txtEnderecoNumero+"', '"+txtEnderecoComplemento+"', '"+txtEnderecoBairro+"', '"+txtEnderecoCidade+"', '"+cmbEnderecoEstado+"', '"+txtEnderecoCep+"', '"+txtTelRepresentada+"', '"+txtTelefone2+"', '"+txtFaxRepresentada+"', '"+txtEmail+"', '"+txtSite+"', '"+txtObs+"', '"+txtRazaoRepresentada+"', '"+tipo+"');";	
				}				
				x+=1;
			}
			this.bacContRepresentada = x;
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	Object tbEmpresasClientesVisitas[] = new  Object[1000];
	public Object[] getTbEmpresasClientesVisitas(){
		return (Object[]) tbEmpresasClientesVisitas;
	}
	int bacContClienteVisitas = 0;
	public int getBacContClienteVisitas(){
		return bacContClienteVisitas;
	}
	public void geraBackupTbClienteVisitas(){
		try {
			String idVisita = null;
			String txtNmCliente = null;
			String cmbSegmento = null;
			String txtDtVisita = null;
			String txtEndereco = null;
			String txtEnderecoNumero = null;
			String txtEnderecoComplemento = null;
			String txtEnderecoBairro = null;
			String txtEnderecoCidade = null;
			String cmbEnderecoEstado = null;
			String txtEnderecoCep = null;
			String txtTelCliente = null;
			String txtFaxCliente = null;
			String txtEmail = null;
			String txtSite = null;
			String txtObs = null;
			String cmbVendedores = null;
			String idVendedores = null;
			String txtLembrar = null;
			String txtTipo = null;
			boolean lembrar = true;
			/*Vector cmbRepresentadas = null;
			String txtRespUm = null;
			String txtRespDois = null;
			String txtRespTres = null;
			String txtRespQuatro = null;
			String txtRespCinco = null;
			String txtRespSeis = null;
			String txtRespSete = null;*/
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			condicao= "SELECT * FROM tb_EmpresasClientes;";

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			int x = 0;

			while(rs.next()){
				idVisita = ""+rs.getInt("id_Visita");
				txtNmCliente = rs.getString("nm_Empresa");
				cmbSegmento = rs.getString("cd_Segmento");
				txtDtVisita = rs.getString("dt_Visita");
				txtEndereco = rs.getString("nm_Endereco");
				txtEnderecoNumero = rs.getString("cd_EnderecoNumero");
				txtEnderecoComplemento = rs.getString("ds_EnderecoComplemento");
				txtEnderecoBairro = rs.getString("nm_EnderecoBairro");
				txtEnderecoCidade = rs.getString("nm_EnderecoCidade");
				cmbEnderecoEstado = rs.getString("sg_EnderecoEstado");
				txtEnderecoCep = rs.getString("cd_Cep");
				txtTelCliente = rs.getString("cd_Telefone");
				txtFaxCliente = rs.getString("cd_Fax");
				txtEmail = rs.getString("nm_Email");
				txtSite = rs.getString("ds_Site");
				txtObs = rs.getString("ds_Obs");
				lembrar = rs.getBoolean("sg_Lembrar");
				txtLembrar = rs.getString("dt_Lembrar");
				cmbVendedores = rs.getString("nm_Funcionario");
				idVendedores = rs.getString("cd_Funcionario");
				txtTipo = rs.getString("sg_TipoVisita");
				if(x==0){
					this.tbEmpresasClientes[x]= "INSERT INTO tb_EmpresasClientesVisitas (id_Visita, nm_Empresa, cd_Segmento, nm_Endereco, cd_EnderecoNumero, ds_EnderecoComplemento, nm_EnderecoBairro, nm_EnderecoCidade, sg_EnderecoEstado, " +
					"cd_Cep, cd_Telefone, cd_Fax, nm_Email, ds_Site, dt_Visita, sg_TipoVisita, cd_Funcionario, dt_Lembrar, sg_Lembrar, nm_Funcionario, ds_Obs) VALUES ('"+idVisita+"', '"+txtNmCliente+"', '"+cmbSegmento+"', '"+txtEndereco+"', '"+txtEnderecoNumero+"', '"+txtEnderecoComplemento+"', '"+txtEnderecoBairro+"', '"+txtEnderecoCidade+"', '"+cmbEnderecoEstado+"', '"+txtEnderecoCep+"', '"+txtTelCliente+"', '"+txtFaxCliente+"', '"+txtEmail+"', '"+txtSite+"', '"+txtDtVisita+"', '"+txtTipo+"', '"+idVendedores+"', '"+txtLembrar+"', '"+	lembrar+"', '"+cmbVendedores+"', '"+txtObs+"');";
				}else{
					this.tbEmpresasClientes[x]= "\nINSERT INTO tb_EmpresasClientesVisitas (id_Visita, nm_Empresa, cd_Segmento, nm_Endereco, cd_EnderecoNumero, ds_EnderecoComplemento, nm_EnderecoBairro, nm_EnderecoCidade, sg_EnderecoEstado, " +
					"cd_Cep, cd_Telefone, cd_Fax, nm_Email, ds_Site, dt_Visita, sg_TipoVisita, cd_Funcionario, dt_Lembrar, sg_Lembrar, nm_Funcionario, ds_Obs) VALUES ('"+idVisita+"', '"+txtNmCliente+"', '"+cmbSegmento+"', '"+txtEndereco+"', '"+txtEnderecoNumero+"', '"+txtEnderecoComplemento+"', '"+txtEnderecoBairro+"', '"+txtEnderecoCidade+"', '"+cmbEnderecoEstado+"', '"+txtEnderecoCep+"', '"+txtTelCliente+"', '"+txtFaxCliente+"', '"+txtEmail+"', '"+txtSite+"', '"+txtDtVisita+"', '"+txtTipo+"', '"+idVendedores+"', '"+txtLembrar+"', '"+	lembrar+"', '"+cmbVendedores+"', '"+txtObs+"');";	
				}				
				x+=1;
			}
			this.bacContClienteVisitas = x;
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	Object tbQuestoesVisitas[] = new  Object[1000];
	public Object[] getTbQuestoesVisitas(){
		return (Object[]) tbQuestoesVisitas;
	}
	int bacContQuestoes = 0;
	public int getBacContQuestos(){
		return bacContQuestoes;
	}
	public void geraBackupTbClienteVisitasQuestoes(){
		try {
			//Vector cmbRepresentadas = null;
			int idVisita;
			String dtVisita = null;
			String nmEmpresa = null;
			String questao1 = null;
			String questao2 = null;
			String questao3 = null;
			String questao4 = null;
			String questao5 = null;
			String questao6 = null;
			String questao7 = null;
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao;

			condicao= "SELECT * FROM tb_QuestoesVisitas;";

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(condicao);

			int x = 0;

			while(rs.next()){
				idVisita = rs.getInt("id_Visita");
				dtVisita = rs.getString("dt_Visita");
				nmEmpresa = rs.getString("nm_Empresa");
				questao1 = rs.getString("ds_Quest01");
				questao2 = rs.getString("ds_Quest02");
				questao3 = rs.getString("ds_Quest03");
				questao4 = rs.getString("ds_Quest04");
				questao5 = rs.getString("ds_Quest05");
				questao6 = rs.getString("ds_Quest06");
				questao7 = rs.getString("ds_Quest07");
				if(x==0){
					this.tbQuestoesVisitas[x]= "INSERT INTO tb_QuestoesVisitas (id_Visita, dt_Visita, nm_Empresa, ds_Quest01, ds_Quest02, ds_Quest03, ds_Quest04, ds_Quest05, ds_Quest06, ds_Quest07) VALUES ('"+idVisita+"', '"+dtVisita+"', '"+nmEmpresa+"', '"+questao1+"', '"+questao2+"', '"+questao3+"', '"+questao4+"', '"+questao5+"', '"+questao6+"', '"+questao7+"');";
				}else{
					this.tbQuestoesVisitas[x]= "\nINSERT INTO tb_QuestoesVisitas (id_Visita, dt_Visita, nm_Empresa, ds_Quest01, ds_Quest02, ds_Quest03, ds_Quest04, ds_Quest05, ds_Quest06, ds_Quest07) VALUES ('"+idVisita+"', '"+dtVisita+"', '"+nmEmpresa+"', '"+questao1+"', '"+questao2+"', '"+questao3+"', '"+questao4+"', '"+questao5+"', '"+questao6+"', '"+questao7+"');";	
				}				
				x+=1;
			}
			this.bacContClienteVisitas = x;
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}

}
