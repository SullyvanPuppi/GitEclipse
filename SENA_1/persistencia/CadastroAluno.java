package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável para se comunicar ao banco de dados usuários do SENA 1.
 * Esta classe herda a classe Conexão BD com os dados relativos ao banco de dados.
 * Cadastros referentes a alunos.
 * 
 */
public class CadastroAluno extends ConexaoBD{

//	----Dados do aluno para a tabela Alunos
	private String matricula;

	private String nmAluno;

	private String cdCpf;

	private String cdRg;

	private String rgOrgao;

	private String sexo;

	private String dtNascimento;

	private String naturalidade;

	private String estadoNatural;

	private String nacionalidade;

	private String endereco;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;

	private String telefone;

	private String email;

	private String nmPai;

	private String nmMae;

	private String caminhoFoto;

	private String dtMatricula;

	//--Dados para a tabela aluno menor de idade
	private String nmResponsavel;

	private String cdCpfResponsavel;

	private String cdRgResponsavel;

	private String rgOrgaoResponsavel;

	private String sexoResponsavel;

	private String dtNascimentoResponsavel;

	private String naturalidadeResponsavel;

	private String estadoNaturalResponsavel;

	private String nacionalidadeResponsavel;

	private String enderecoResponsavel;

	private String numeroResponsavel;

	private String complementoResponsavel;

	private String bairroResponsavel;

	private String cidadeResponsavel;

	private String estadoResponsavel;

	private String cepResponsavel;

	private String telefoneResponsavel;

	private String emailResponsavel;
	
	//----Dados para SENA IV
	private boolean exibirPerfil;
	
	private boolean exibirCurriculo;
	
	private boolean exibirProsContras;
	
	private String skin;
	
	//----Dados para a tabela Alunos documentos apresentados

	private String nmDocumentoApresentado;

	private String dtApresentado;

	private String dsDocumentoApresentado;

	//---Dados para a tabela Alunos documentos pendentes
	private String nmDocumentoPendente;

	private String dtPendente;

	private String dsDocumentoPendente;

	//---Dados da tabela alunos documento solicitados
	private String nmDocumentoSolicitado;

	private String dtSolicitado;

	private String dsDocumentoSolicitado;

	private String statusDocumentoSolicitado;

	//---Dados pra a tabela Alunos historico pro
	private String tituloPro;

	private String dtHistoricoPro;

	private String dsHistoricoPro;

	//---Dados para a tabela Alunos historico contra
	private String tituloContra;

	private String dtHistoricoContra;

	private String dsHistoricoContra;

	//--Dados para a tabela de Alunos usuarios sena 4
	private String nmLogin;

	private String cdSenha;

	//--Dados da tabela Aluno matricula trancada
	private String dtTrancamento;

	private String nmClasseUltima;

	private int cdClasse;

	private String nmCurso;

	private int cdCiclo;

	//--------------------------Consultas------------------------------------------------------------------------//
	//--Tabela alunos--//	
	DefaultTableModel tabelaAlunos = new DefaultTableModel();

	public DefaultTableModel getTabelaAlunos(){
		return this.tabelaAlunos;
	}
	//----Colunas da tabela alunos
	public void addColunasAlunosA(){
		tabelaAlunos.addColumn("Registro de matrícula");
		tabelaAlunos.addColumn("Aluno");
	}
	public void addColunasAlunosB(){
		tabelaAlunos.addColumn("Registro de matrícula");
		tabelaAlunos.addColumn("Aluno");
		tabelaAlunos.addColumn("Situação");
	}
	public void addColunasAlunosC(){
		tabelaAlunos.addColumn("Registro de matrícula");
		tabelaAlunos.addColumn("Aluno");
		tabelaAlunos.addColumn("Curso");
		tabelaAlunos.addColumn("Classe");
	}
	public void consultarAlunos(String nmAluno, String situacao, String nmCurso, String classe){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "";
			int tb = 0;
			if(nmAluno.equals("")){
				if(situacao.equals("----------")){
					if(nmCurso.equals("----------")){
						if(classe.equals("----------")){
							tb=1;
							addColunasAlunosA();
							condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_Alunos;";
						}else{
							if(classe.equals("Sem classe")){
								tb=1;
								addColunasAlunosA();
								condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_AlunosSemClasse;";	
							}else{
								tb=3;
								addColunasAlunosC();
								condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE cd_Classe='"+classe+"';";	
							}							
						}
					}else{
						if(classe.equals("----------")){
							tb=3;
							addColunasAlunosC();
							condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE nm_Curso='"+nmCurso+"';";
						}else{
							tb=3;
							addColunasAlunosC();
							condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE cd_Classe='"+classe+"' AND nm_Curso='"+nmCurso+"';";								
						}						
					}
				}else{
					if(situacao.equals("Regular")){
						tb=2;
						addColunasAlunosB();
						condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_ClasseAlunos;";	
					}else if(situacao.equals("Trancada")){
						tb=2;
						addColunasAlunosB();
						condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_AlunoMatriculaTrancada;";	
					}else if(situacao.equals("Desistente")){
						tb=2;
						addColunasAlunosB();
						condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_AlunosSemClasse WHERE sg_Status='"+situacao+"';";
					}				
				}
			}else{
				if(situacao.equals("----------")){
					if(nmCurso.equals("----------")){
						if(classe.equals("----------")){
							tb=1;
							addColunasAlunosA();
							condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_Alunos;";
						}else{
							if(classe.equals("Sem classe")){
								tb=1;
								addColunasAlunosA();
								condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_AlunosSemClasse;";	
							}else{
								tb=3;
								addColunasAlunosC();
								condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE cd_Classe='"+classe+"';";	
							}							
						}
					}else{
						if(classe.equals("----------")){
							tb=3;
							addColunasAlunosC();
							condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE nm_Curso='"+nmCurso+"';";
						}else{
							tb=3;
							addColunasAlunosC();
							condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE cd_Classe='"+classe+"' AND nm_Curso='"+nmCurso+"';";								
						}						
					}
				}else{
					if(situacao.equals("Regular")){
						if(nmCurso.equals("----------")){
							if(classe.equals("----------")){
								tb=1;
								addColunasAlunosA();
								condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_Alunos;";
							}else{
								if(classe.equals("Sem classe")){
									tb=1;
									addColunasAlunosA();
									condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_AlunosSemClasse;";	
								}else{
									tb=3;
									addColunasAlunosC();
									condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE cd_Classe='"+classe+"';";	
								}							
							}
						}else{
							if(classe.equals("----------")){
								tb=3;
								addColunasAlunosC();
								condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE nm_Curso='"+nmCurso+"';";
							}else{
								tb=3;
								addColunasAlunosC();
								condicao = "SELECT cd_MatriculaAluno, nm_Aluno, nm_Curso, nm_Classe FROM tb_ClasseAlunos WHERE cd_Classe='"+classe+"' AND nm_Curso='"+nmCurso+"';";								
							}						
						}	
					}else if(situacao.equals("Trancada")){
						tb=2;
						addColunasAlunosB();
						condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_AlunoMatriculaTrancada;";	
					}else if(situacao.equals("Desistente")){
						tb=2;
						addColunasAlunosB();
						condicao = "SELECT cd_MatriculaAluno, nm_Aluno FROM tb_AlunosSemClasse WHERE sg_Status='"+situacao+"';";
					}					
				}
			}
			
			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			if(tb==1){
				while(rs.next()){
					Object linha[] = new Object[2];
					linha[0] = rs.getInt("cd_MatriculaAluno");
					linha[1] = rs.getString("nm_Aluno");
					tabelaAlunos.addRow(linha);
				}
			}else if(tb==2){
				while(rs.next()){
					Object linha[] = new Object[3];
					linha[0] = rs.getInt("cd_MatriculaAluno");
					linha[1] = rs.getString("nm_Aluno");
					linha[2] = "Trancada";
					tabelaAlunos.addRow(linha);
				}	
			}else if(tb==3){
				while(rs.next()){
					Object linha[] = new Object[4];
					linha[0] = rs.getInt("cd_MatriculaAluno");
					linha[1] = rs.getString("nm_Aluno");
					linha[2] = rs.getString("nm_Curso");
					linha[3] = rs.getString("nm_Classe");
					tabelaAlunos.addRow(linha);
				}	
			}	

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Tabela histórico pró--//	
	DefaultTableModel tabelaHistoricoPro = new DefaultTableModel();

	public DefaultTableModel getTabelaHistoricoPro(){
		return this.tabelaHistoricoPro;
	}
	//----Colunas da tabela HistoricoPro
	public void addColunasHistoricoPro(){
		tabelaHistoricoPro.addColumn("Atividade");
	}
	public void consultarHistoricoPro(String cdMatricula){
		addColunasHistoricoPro();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Atividade from tb_AlunoHistoricoPro WHERE cd_MatriculaAluno="+cdMatricula+";";

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar histórico pró
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta

			while(rs.next()){
				Object linha[] = new Object[1];
				linha[0] = rs.getString("nm_Atividade");
				tabelaHistoricoPro.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	
	public void consultarDsHistoricoPro(String cdMatricula, String atividade){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT * from tb_AlunoHistoricoPro WHERE cd_MatriculaAluno="+cdMatricula+" AND nm_Atividade='"+atividade+"';";

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar histórico pró
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			rs.next();
			
			this.tituloPro = rs.getString("nm_Atividade");
			this.dtHistoricoPro = rs.getString("dt_HistoricoPro");
			this.dsHistoricoPro = rs.getString("ds_HistoricoPro");			

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void removerHistoricoPro(String cdMatricula, String atividade){
		try {
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			
			Statement st = con.createStatement();
			
			String condicao ="DELETE FROM tb_AlunoHistoricoPro WHERE cd_MatriculaAluno="+cdMatricula+" AND nm_Atividade='"+atividade+"';";
			
			st.executeUpdate(condicao);//Remover atividade positiva do aluno
						
			st.close();
			con.close();
			
			JOptionPane.showMessageDialog(null,"Atividade positiva excluída com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Tabela histórico contra--//	
	DefaultTableModel tabelaHistoricoContra = new DefaultTableModel();

	public DefaultTableModel getTabelaHistoricoContra(){
		return this.tabelaHistoricoContra;
	}
	//----Colunas da tabela HistoricoContra
	public void addColunasHistoricoContra(){
		tabelaHistoricoContra.addColumn("Atividade");
	}
	public void consultarHistoricoContra(String cdMatricula){
		addColunasHistoricoContra();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Atividade from tb_AlunoHistoricoContra WHERE cd_MatriculaAluno="+cdMatricula+";";

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar histórico pró
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta

			while(rs.next()){
				Object linha[] = new Object[1];
				linha[0] = rs.getString("nm_Atividade");
				tabelaHistoricoContra.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarDsHistoricoContra(String cdMatricula, String atividade){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT * from tb_AlunoHistoricoContra WHERE cd_MatriculaAluno="+cdMatricula+" AND nm_Atividade='"+atividade+"';";
			
			Statement st = con.createStatement();
			//Executa o código sql para pesquisar histórico pró
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			rs.next();
			
			this.tituloContra = rs.getString("nm_Atividade");
			this.dtHistoricoContra = rs.getString("dt_HistoricoContra");
			this.dsHistoricoContra = rs.getString("ds_HistoricoContra");			

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void removerHistoricoContra(String cdMatricula, String atividade){
		try {
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			
			Statement st = con.createStatement();
			
			String condicao ="DELETE FROM tb_AlunoHistoricoContra WHERE cd_MatriculaAluno="+cdMatricula+" AND nm_Atividade='"+atividade+"';";
			
			st.executeUpdate(condicao);//Remover atividade positiva do aluno
						
			st.close();
			con.close();
			
			JOptionPane.showMessageDialog(null,"Atividade negativa excluída com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	
	//--Tabela Documentos apresentados--//	
	DefaultTableModel tabelaDocApresentados = new DefaultTableModel();

	public DefaultTableModel getTabelaDocApresentados(){
		return this.tabelaDocApresentados;
	}
	//----Colunas da tabela DocApresentados
	public void addColunasDocApresentados(){
		tabelaDocApresentados.addColumn("Documento");
	}
	public void consultarDocApresentados(String cdMatricula){
		addColunasDocApresentados();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Documento from tb_AlunosDocumentosApresentados WHERE cd_MatriculaAluno="+cdMatricula+";";

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar documentos apresentados
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta

			while(rs.next()){
				Object linha[] = new Object[1];
				linha[0] = rs.getString("nm_Documento");
				tabelaDocApresentados.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Tabela Documentos pendentes--//	
	DefaultTableModel tabelaDocPendentes = new DefaultTableModel();

	public DefaultTableModel getTabelaDocPendentes(){
		return this.tabelaDocPendentes;
	}
	//----Colunas da tabela DocPendentes
	public void addColunasDocPendentes(){
		tabelaDocPendentes.addColumn("Documento");
	}
	public void consultarDocPendentes(String cdMatricula){
		addColunasDocPendentes();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Documento from tb_AlunosDocumentosPendentes WHERE cd_MatriculaAluno="+cdMatricula+";";

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar documentos apresentados
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				Object linha[] = new Object[1];
				linha[0] = rs.getString("nm_Documento");
				tabelaDocPendentes.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Tabela Documentos solicitados--//	
	DefaultTableModel tabelaDocSolicitados = new DefaultTableModel();

	public DefaultTableModel getTabelaDocSolicitados(){
		return this.tabelaDocSolicitados;
	}
	//----Colunas da tabela DocApresentados
	public void addColunasDocSolicitados(){
		tabelaDocSolicitados.addColumn("Documento");
	}
	public void consultarDocSolicitados(String cdMatricula){
		addColunasDocSolicitados();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Documento from tb_AlunosDocumentosSolicitados WHERE cd_MatriculaAluno="+cdMatricula+";";

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar documentos apresentados
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				Object linha[] = new Object[1];
				linha[0] = rs.getString("nm_Documento");
				tabelaDocSolicitados.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Tabela classes--//	
	DefaultTableModel tabelaClasses = new DefaultTableModel();

	public DefaultTableModel getTabelaClasses(){
		return this.tabelaClasses;
	}
	//----Colunas da tabela Classes
	public void addColunasClasses(){
		tabelaClasses.addColumn("Classe");
		tabelaClasses.addColumn("Data entrada");
		tabelaClasses.addColumn("Situação");
	}
	public void consultarClasses(String cdMatricula){
		addColunasClasses();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Classe, dt_Entrada, sg_Status from tb_AlunoHistoricoClasse WHERE cd_MatriculaAluno="+cdMatricula+" ORDER BY dt_Entrada;";

			if(!cdMatricula.equals(getMatriculaNova())){
				Statement st = con.createStatement();

				//Executa o código sql para pesquisar histórico de classes
				ResultSet rs = st.executeQuery(condicao);
				
				//Percorre a consulta
				while(rs.next()){
					Object linha[] = new Object[3];
					linha[0] = rs.getString("nm_Classe");
					linha[1] = rs.getString("dt_Entrada");
					linha[2] = rs.getString("sg_Situacao");
					tabelaClasses.addRow(linha);
				}
				st.close();
			}			
			
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}

	//---Atributo e métodos para combobox cursos
	private Vector cursos = new Vector();//Armazena itens da cmbCursos

	//--Retorna os componentes cadastrados
	public Vector getCursos(){
		return this.cursos;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbCursos(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT nm_Curso FROM tb_Cursos;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				cursos.addElement(rs.getString("nm_Curso"));
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---Atributo e métodos para combobox classes
	private Vector classes = new Vector();//Armazena itens da cmbClasses

	//--Retorna as classes cadastrados do curso pesquisado
	public Vector getClasses(){
		return this.classes;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbClasses(String nmCurso){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao; 

			Statement st = con.createStatement();

			if(!nmCurso.equals("----------")){
				condicao = "SELECT nm_Classe FROM tb_Classe WHERE nm_Curso='"+nmCurso+"';";

				//Executa o código sql para pesquisar classes
				ResultSet rs = st.executeQuery(condicao);

				while(rs.next()){
					classes.addElement(rs.getString("nm_Classe"));
				}
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---Atributo e métodos para combobox skins
	private Vector skins = new Vector();//Armazena itens da cmbSkins

	public Vector getSkins(){
		return this.skins;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbSkins(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao;

			Statement st = con.createStatement();

			condicao = "SELECT nm_Skin FROM tb_SkinsSenaQuatro;";

			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				skins.addElement(rs.getString("nm_Skin"));
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---Atributo e métodos para combobox mensagens
	private Vector msgs = new Vector();//Armazena itens da cmbMensagens

	public Vector getMsgs(){
		return this.msgs;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbMsgs(String cdMatricula){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao;

			Statement st = con.createStatement();

			condicao = "SELECT nm_Assunto FROM tb_CanalDireto WHERE nm_De="+cdMatricula+" OR cd_Para_MatriculaAluno="+cdMatricula+";";
			
			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				msgs.addElement(rs.getString("nm_Assunto"));
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	private String msg;
	private String nome;
	private String assunto;
	private String dsMsg;
	private String dtMsg;
	
	public String getMsg(){
		return this.msg;
	}	
	public String getAssunto() {
		return assunto;
	}
	public String getDsMsg() {
		return dsMsg;
	}
	public String getNome() {
		return nome;
	}
	public String getDtMsg(){
		return dtMsg;
	}
	public void consultarMsgSelecionado(String cdMatricula, String assunto){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao;

			Statement st = con.createStatement();

			condicao = "SELECT * FROM tb_CanalDireto WHERE nm_De="+cdMatricula+" OR cd_Para_MatriculaAluno="+cdMatricula+" AND nm_Assunto='"+assunto+"';";
			
			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);
			
			if(rs.next()){
				this.msg = rs.getString("ds_Recado");
				this.assunto = rs.getString("nm_Assunto");
				this.nome = rs.getString("nm_De");
				this.dtMsg = rs.getString("dt_Recado");
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarAluno(String matricula){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao; 

			Statement st = con.createStatement();

			condicao = "SELECT * FROM tb_Alunos WHERE cd_MatriculaAluno="+matricula+";";

			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);

			rs.next();
			
			this.matricula = rs.getString("cd_MatriculaAluno");
			this.nmAluno = rs.getString("nm_Aluno");
			this.cdCpf = rs.getString("cd_Cpf");
			this.cdRg = rs.getString("cd_Rg");
			this.rgOrgao = rs.getString("sg_RgOrgao");
			this.sexo = rs.getString("nm_Sexo");
			this.dtNascimento = rs.getString("dt_Nascimento");
			this.naturalidade = rs.getString("nm_Naturalidade");
			this.estadoNatural = rs.getString("sg_EstadoNatural");
			this.nacionalidade = rs.getString("nm_Nacionalidade");
			this.endereco = rs.getString("nm_Endereco");
			this.numero = rs.getString("cd_Numero");
			this.complemento = rs.getString("ds_Complemento");
			this.bairro = rs.getString("nm_Bairro");
			this.cidade = rs.getString("nm_Cidade");
			this.estado = rs.getString("sg_Estado");
			this.cep = rs.getString("cd_Cep");
			this.telefone = rs.getString("cd_Telefone");
			this.email = rs.getString("nm_Email");
			this.nmPai = rs.getString("nm_Pai");
			this.nmMae = rs.getString("nm_Mae");
			this.caminhoFoto = rs.getString("ds_CaminhoFoto");
			this.dtMatricula = rs.getString("dt_Matricula");
			
			consultarAlunoSenaQuatro(matricula);
			consultarAlunoPerfil(matricula);
			consultarAlunoCurriculo(matricula);
			
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarResponsavel(String matricula){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao; 

			Statement st = con.createStatement();

			condicao = "SELECT * FROM tb_AlunoMenorIdade WHERE cd_MatriculaAluno="+matricula+";";

			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);

			rs.next();
			
			this.nmResponsavel = rs.getString("nm_Responsavel");
			this.cdCpfResponsavel = rs.getString("cd_Cpf");
			this.cdRgResponsavel = rs.getString("cd_Rg");
			this.rgOrgaoResponsavel = rs.getString("sg_RgOrgao");
			this.sexoResponsavel = rs.getString("nm_Sexo");
			this.dtNascimentoResponsavel = rs.getString("dt_Nascimento");
			this.naturalidadeResponsavel = rs.getString("nm_Naturalidade");
			this.estadoNaturalResponsavel = rs.getString("sg_EstadoNatural");
			this.nacionalidadeResponsavel = rs.getString("nm_Nacionalidade");
			this.enderecoResponsavel = rs.getString("nm_Endereco");
			this.numeroResponsavel = rs.getString("cd_Numero");
			this.complementoResponsavel = rs.getString("ds_Complemento");
			this.bairroResponsavel = rs.getString("nm_Bairro");
			this.cidadeResponsavel = rs.getString("nm_Cidade");
			this.estadoResponsavel = rs.getString("sg_Estado");
			this.cepResponsavel = rs.getString("cd_Cep");
			this.telefoneResponsavel = rs.getString("cd_Telefone");
			this.emailResponsavel = rs.getString("nm_Email");
						
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarAlunoSenaQuatro(String matricula){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao; 

			Statement st = con.createStatement();

			condicao = "SELECT * FROM tb_Alunos_Usuarios_Sena_Quatro WHERE cd_MatriculaAluno="+matricula+";";

			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);

			rs.next();
			
			this.nmLogin = rs.getString("nm_Login");
			this.cdSenha = rs.getString("cd_Senha");
						
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarAlunoPerfil(String matricula){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao; 

			Statement st = con.createStatement();

			condicao = "SELECT * FROM tb_AlunoPerfil WHERE cd_MatriculaAluno="+matricula+";";

			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);

			rs.next();
			
			if(rs.getInt("sg_Exibir")==1){
				this.exibirPerfil = true;	
			}else{
				this.exibirPerfil = false;
			}			
			
			this.skin = rs.getString("nm_Skin");
						
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarAlunoCurriculo(String matricula){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao; 

			Statement st = con.createStatement();

			condicao = "SELECT * FROM tb_AlunoCurriculo WHERE cd_MatriculaAluno="+matricula+";";

			//Executa o código sql para pesquisar classes
			ResultSet rs = st.executeQuery(condicao);

			rs.next();
			if (rs.getInt("sg_Exibir")==1){
				this.exibirCurriculo = true;	
			}else{
				this.exibirCurriculo = false;
			}
			if(rs.getInt("sg_ExibirProsContras")==1){
				this.exibirProsContras = true;	
			}else{
				this.exibirProsContras = false;
			}			
						
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}		
	}
	//-------------Novo cadastro de aluno
	//----Ajusta matricula do aluno
	private int matriculaNova;
	public int getMatriculaNova(){
		return this.matriculaNova;
	}
	public void ajustaMatricula(){
		try {			
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			//String condicao= "SELECT cd_MatriculaAluno FROM tb_Alunos ORDER BY cd_MatriculaAluno DESC;";
			String condicao= "SELECT cd_MatriculaAluno FROM tb_Alunos;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar aluno
			ResultSet rs = st.executeQuery(condicao);

			int x = 1;

			while(rs.next()){
				x+=1;
			}

			this.matriculaNova = x;

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
//	-----Verifica se ja existe aluno
	private int existeAluno = 0;

	public int getExisteAluno(){
		return this.existeAluno;
	}
	public void verificaExisteAluno(String cdMatricula){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Alunos WHERE cd_MatriculaAluno="+cdMatricula+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				this.existeAluno= 1;
			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
//	--------------Cadastros------------------------------------------------------------------------//
	public void cadastrarAluno(String matricula, String nmAluno, String cdCpf, String cdRg, String rgOrgao, String sexo, String dtNascimento, String naturalidade, String estadoNatural, String nacionalidade, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String cep, String telefone, String email, String nmPai, String nmMae, String caminhoFoto, String dtMatricula, String dsCaminhoFotoSenaQuatro){
		/* cadastrar aluno nas tabelas:
		 * tb_Aluno
		 * tb_AlunoSemClasse
		 * tb_AlunoPerfil
		 * tb_AlunoCurriculo
		 * tb_Alunos_Usuarios_Sena_Quatro
		 */
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Alunos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Alunos (cd_MatriculaAluno, nm_Aluno, cd_Cpf, cd_Rg, sg_RgOrgao, nm_Sexo, dt_Nascimento, nm_Naturalidade, sg_EstadoNatural, nm_Nacionalidade, nm_Endereco, cd_Numero, ds_Complemento, nm_Bairro, nm_Cidade, sg_Estado, cd_Cep, cd_Telefone, nm_Email, nm_Pai, nm_Mae, ds_CaminhoFoto, dt_Matricula, ds_CaminhoFotoSenaQuatro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, matricula.trim());
			stmt.setString(2, nmAluno.trim());
			stmt.setString(3, cdCpf.trim());
			stmt.setString(4, cdRg.trim());
			stmt.setString(5, rgOrgao.trim());
			stmt.setString(6, sexo.trim());
			stmt.setString(7, dtNascimento.trim());
			stmt.setString(8, naturalidade.trim());
			stmt.setString(9, estadoNatural.trim());
			stmt.setString(10, nacionalidade.trim());
			stmt.setString(11, endereco.trim());
			stmt.setString(12, numero);
			stmt.setString(13, complemento.trim());
			stmt.setString(14, bairro.trim());
			stmt.setString(15, cidade.trim());
			stmt.setString(16, estado.trim());
			stmt.setString(17, cep.trim());
			stmt.setString(18, telefone.trim());
			stmt.setString(19, email.trim());
			stmt.setString(20, nmPai.trim());
			stmt.setString(21, nmMae.trim());
			stmt.setString(22, caminhoFoto);
			stmt.setString(23, dtMatricula.trim());
			stmt.setString(24, dsCaminhoFotoSenaQuatro);

			stmt.execute();
			stmt.close();

			//Prepara o código sql para cadastrar curso tb_AlunoSemClasse
			PreparedStatement stmt2 = con.prepareStatement("INSERT INTO tb_AlunosSemClasse (cd_MatriculaAluno, nm_Aluno, dt_Matricula) VALUES (?,?,?);");
			//Preenche os campos
			stmt2.setString(1, matricula.trim());
			stmt2.setString(2, nmAluno.trim());
			stmt2.setString(3, dtMatricula.trim());
			
			//Executa a instrução SQL
			stmt2.execute();
			stmt2.close();

			//Prepara o código sql para cadastrar curso tb_AlunoPerfil
			PreparedStatement stmt3 = con.prepareStatement("INSERT INTO tb_AlunoPerfil (cd_MatriculaAluno, nm_Aluno, nm_Apelido, ds_CaminhoFoto, nm_Email, sg_Exibir, nm_Skin) VALUES (?,?,?,?,?,?,?);");
			//Preenche os campos
			stmt3.setString(1, matricula.trim());
			stmt3.setString(2, nmAluno.trim());
			stmt3.setString(3, nmAluno.trim());
			stmt3.setString(4, dsCaminhoFotoSenaQuatro);
			stmt3.setString(5, email.trim());
			stmt3.setInt(6, 1);//Opção inicial de exibir perfil no SENA 4
			stmt3.setString(7, "Padrão");//Opção inicial de skin
			//Executa a instrução SQL
			stmt3.execute();
			stmt3.close();

			//Prepara o código sql para cadastrar curso tb_AlunoCurrículo
			PreparedStatement stmt4 = con.prepareStatement("INSERT INTO tb_AlunoCurriculo (cd_MatriculaAluno, nm_Aluno, sg_Exibir, sg_ExibirProsContras) VALUES (?,?,?,?);");
			//Preenche os campos
			stmt4.setString(1, matricula.trim());
			stmt4.setString(2, nmAluno.trim());
			stmt4.setInt(3, 0);//Opção inicial não mostrar currículo
			stmt4.setInt(4, 0);//Opção inicial não mostrar pros nem contras			

			//Executa a instrução SQL
			stmt4.execute();
			stmt4.close();

			//Prepara o código sql para cadastrar curso tb_Alunos usuários do sena 4
			PreparedStatement stmt5 = con.prepareStatement("INSERT INTO tb_Alunos_Usuarios_Sena_Quatro (cd_MatriculaAluno, nm_Aluno, nm_Login, cd_Senha) VALUES (?,?,?,?);");
			//Preenche os campos
			stmt5.setString(1, matricula.trim());
			stmt5.setString(2, nmAluno.trim());
			stmt5.setString(3, matricula.trim());
			stmt5.setString(4, matricula.trim());

			//Executa a instrução SQL
			stmt5.execute();
			stmt5.close();	

			//Prepara o código sql para cadastrar curso tb_Alunos_Usuarios_Sena_Quatro
			PreparedStatement stmt6 = con.prepareStatement("INSERT INTO tb_Aluno_Usuario_Sena_Quatro (cd_MatriculaAluno, nm_Aluno, nm_Login, cd_Senha) VALUES (?,?,?,?);");
			//Preenche os campos
			stmt6.setString(1, matricula.trim());
			stmt6.setString(2, nmAluno.trim());
			stmt6.setString(3, matricula.trim());
			stmt6.setString(4, matricula.trim());

			con.close();
			JOptionPane.showMessageDialog(null,"Aluno cadastrado com sucesso!\nAluno:\n"+nmAluno+"\nRegistro de matrícula:\n"+matricula,"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
//	---Cadastrar senha
	public void NovaSenha(String cdMatricula, String nmAluno){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			///Prepara o código sql para alterar senha do aluno
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Alunos_Usuarios_Sena_Quatro set nm_Login=?, cd_Senha=? WHERE cd_MatriculaAluno="+cdMatricula+";");
			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, cdMatricula.trim());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Gerado nova senha para o aluno:\n\n"+nmAluno+"\n\n","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarResponsavel(String cdMatricula, String nmAluno, String nmResponsavel, String cdCpfResponsavel, String cdRgResponsavel, String rgOrgaoResponsavel, String sexoResponsavel, String dtNascimentoResponsavel, String naturalidadeResponsavel, String estadoNaturalResponsavel, String nacionalidadeResponsavel, String enderecoResponsavel, String numeroResponsavel, String complementoResponsavel, String bairroResponsavel, String cidadeResponsavel, String estadoResponsavel, String cepResponsavel, String telefoneResponsavel, String emailResponsavel){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Cursos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_AlunoMenorIdade (cd_MatriculaAluno, nm_Aluno, nm_Responsavel, cd_Cpf, cd_Rg, sg_RgOrgao, nm_Sexo, dt_Nascimento, nm_Naturalidade, sg_EstadoNatural, nm_Nacionalidade, nm_Endereco, cd_Numero, ds_Complemento, nm_Bairro, nm_Cidade, sg_Estado, cd_Cep, cd_Telefone, nm_Email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, nmAluno.trim());
			stmt.setString(3, nmResponsavel.trim());
			stmt.setString(4, cdCpfResponsavel.trim());
			stmt.setString(5, cdRgResponsavel.trim());
			stmt.setString(6, rgOrgaoResponsavel.trim());
			stmt.setString(7, sexoResponsavel.trim());
			stmt.setString(8, dtNascimentoResponsavel.trim());
			stmt.setString(9, naturalidadeResponsavel.trim());
			stmt.setString(10, estadoNaturalResponsavel.trim());
			stmt.setString(11, nacionalidadeResponsavel.trim());
			stmt.setString(12, enderecoResponsavel.trim());
			stmt.setString(13, numeroResponsavel);
			stmt.setString(14, complementoResponsavel.trim());
			stmt.setString(15, bairroResponsavel.trim());
			stmt.setString(16, cidadeResponsavel.trim());
			stmt.setString(17, estadoResponsavel.trim());
			stmt.setString(18, cepResponsavel.trim());
			stmt.setString(19, telefoneResponsavel.trim());
			stmt.setString(20, emailResponsavel.trim());

			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Responsável pelo aluno:\n"+nmAluno+"\ncadastrado com sucesso!","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarSenaQuatro(String cdMatricula, String login, String senha, String skin, boolean perfil, boolean curriculo, boolean prosContras){
		try{
						
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para alterar cadastro sena quatro
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Alunos_Usuarios_Sena_Quatro set nm_Login=?, cd_Senha=? WHERE cd_MatriculaAluno="+senha+";");
						
			//Preenche os campos
			stmt.setString(1, login.trim());
			stmt.setString(2, senha.trim());
			
			stmt.execute();
			stmt.close();

			//Prepara o código sql para alterar cadastro sena quatro
			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_AlunoPerfil set sg_Exibir=?, nm_Skin=? WHERE cd_MatriculaAluno="+cdMatricula+";");

			//Preenche os campos
			stmt2.setBoolean(1, perfil);
			stmt2.setString(2, skin.trim());

			stmt2.execute();
			stmt2.close();

			//Prepara o código sql para alterar cadastro sena quatro
			PreparedStatement stmt3 = con.prepareStatement("UPDATE tb_AlunoCurriculo set sg_Exibir=?, sg_ExibirProsContras=? WHERE cd_MatriculaAluno="+cdMatricula+";");

			//Preenche os campos
			stmt3.setBoolean(1, curriculo);
			stmt3.setBoolean(2, prosContras);
			
			stmt3.execute();
			stmt3.close();

			con.close();
			JOptionPane.showMessageDialog(null,"Cadastro do aluno no SENA IV alterado com sucesso","Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---Cadastrar historico
	public void cadastrarHistoricoPro(String cdMatricula, String nmAluno, String atividade, String dtHistorico, String descricao){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar documento apresentados
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_AlunoHistoricoPro (cd_MatriculaAluno, nm_Aluno, nm_Atividade, dt_HistoricoPro, ds_HistoricoPro) VALUES (?,?,?,?,?);");
			
			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, nmAluno.trim());
			stmt.setString(3, atividade.trim());
			stmt.setString(4, dtHistorico.trim());
			stmt.setString(5, descricao.trim());
			
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Atividade positiva do aluno:\n"+nmAluno+"\ncadastrado com sucesso!","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarHistoricoContra(String cdMatricula, String nmAluno, String atividade, String dtHistorico, String descricao){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar documento apresentados
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_AlunoHistoricoContra (cd_MatriculaAluno, nm_Aluno, nm_Atividade, dt_HistoricoContra, ds_HistoricoContra) VALUES (?,?,?,?,?);");
			
			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, nmAluno.trim());
			stmt.setString(3, atividade.trim());
			stmt.setString(4, dtHistorico.trim());
			stmt.setString(5, descricao.trim());
			
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Atividade negativa do aluno:\n"+nmAluno+"\ncadastrado com sucesso!","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
//	----Cadastrar documentos
	public void cadastrarDocumentoApresentado(String cdMatricula, String nmAluno, String documento, String dtApresentacao, String descricao){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar documento apresentados
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_AlunosDocumentosApresentados (cd_MatriculaAluno, nm_Aluno, nm_Documento, dt_Apresentacao, ds_Documento) VALUES (?,?,?,?,?);");
			
			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, nmAluno.trim());
			stmt.setString(3, documento.trim());
			stmt.setString(4, dtApresentacao.trim());
			stmt.setString(5, descricao.trim());
			
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Documento apresentado do aluno:\n"+nmAluno+"\ncadastrado com sucesso!","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarDocumentoPendente(String cdMatricula, String nmAluno, String documento, String dtPendencia, String descricao){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Cursos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_AlunosDocumentosPendentes (cd_MatriculaAluno, nm_Aluno, nm_Documento, dt_Pendencia, ds_Documento) VALUES (?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, nmAluno.trim());
			stmt.setString(3, documento.trim());
			stmt.setString(4, dtPendencia.trim());
			stmt.setString(5, descricao.trim());

			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Documento pendente do aluno:\n"+nmAluno+"\ncadastrado com sucesso!","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarDocumentoSolicitado(String cdMatricula, String nmAluno, String documento, String dtApresentacao, String descricao){

	}	
	public void trancarMatricula(String cdMatricula, String nmAluno, String dtTrancamento, String classeUltima, String nmCurso, String cdClasse, String cdCiclo){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Cursos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_AlunoMatriculaTrancada (cd_MatriculaAluno, nm_Aluno, dt_Trancamento, nm_ClasseUltima, nm_Curso, cd_Classe, cd_Ciclo) VALUES (?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, nmAluno.trim());
			stmt.setString(3, dtTrancamento);
			stmt.setString(4, classeUltima.trim());
			stmt.setString(5, nmCurso.trim());
			stmt.setString(6, cdClasse.trim());
			stmt.setString(7, cdCiclo.trim());

			stmt.execute();
			stmt.close();

			//Condições de pesquisa
			String condicao2 = "SELECT * FROM tb_AlunoSemClasse WHERE cd_MatriculaAluno="+cdMatricula+";";

			Statement st2 = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st2.executeQuery(condicao2);

			if(rs.next()){
				String condicao = "DELETE FROM tb_AlunoSemClasse WHERE cd_MatriculaAluno="+cdMatricula+";";
				st2.executeUpdate(condicao);//Remover aluno da tabela de alunos sem classe
				st2.close();
			}


			//---MESMO PROCESSO PARA EXCLUIR ALUNO DE CLASSE


			con.close();
			JOptionPane.showMessageDialog(null,"Responsável pelo aluno:\n"+nmAluno+"\ncadastrado com sucesso!","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
//	--------------------------------------------------Alterações-----------------------------------------//
	public void alterarAluno(String matricula, String nmAluno, String cdCpf, String cdRg, String rgOrgao, String sexo, String dtNascimento, String naturalidade, String estadoNatural, String nacionalidade, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String cep, String telefone, String email, String nmPai, String nmMae, String caminhoFoto){
		/* Alterar cadastro do aluno nas tabelas:
		 * tb_Aluno
		 * tb_AlunoCurriculo
		 */
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Alunos
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Alunos SET nm_Aluno=?, cd_Cpf=?, cd_Rg=?, sg_RgOrgao=?, nm_Sexo=?, dt_Nascimento=?, nm_Naturalidade=?, sg_EstadoNatural=?, nm_Nacionalidade=?, nm_Endereco=?, cd_Numero=?, ds_Complemento=?, nm_Bairro=?, nm_Cidade=?, sg_Estado=?, cd_Cep=?, cd_Telefone=?, nm_Email=?, nm_Pai=?, nm_Mae=?, ds_CaminhoFoto=? WHERE cd_MatriculaAluno="+matricula+";");

			//Preenche os campos
			stmt.setString(1, nmAluno.trim());
			stmt.setString(2, cdCpf.trim());
			stmt.setString(3, cdRg.trim());
			stmt.setString(4, rgOrgao.trim());
			stmt.setString(5, sexo.trim());
			stmt.setString(6, dtNascimento.trim());
			stmt.setString(7, naturalidade.trim());
			stmt.setString(8, estadoNatural.trim());
			stmt.setString(9, nacionalidade.trim());
			stmt.setString(10, endereco.trim());
			stmt.setString(11, numero);
			stmt.setString(12, complemento.trim());
			stmt.setString(13, bairro.trim());
			stmt.setString(14, cidade.trim());
			stmt.setString(15, estado.trim());
			stmt.setString(16, cep.trim());
			stmt.setString(17, telefone.trim());
			stmt.setString(18, email.trim());
			stmt.setString(19, nmPai.trim());
			stmt.setString(20, nmMae.trim());
			stmt.setString(21, caminhoFoto);

			stmt.execute();
			stmt.close();

			//Prepara o código sql para cadastrar curso tb_AlunoCurrículo
			PreparedStatement stmt4 = con.prepareStatement("UPDATE tb_AlunoCurriculo SET nm_Aluno=? WHERE cd_MatriculaAluno="+matricula+";");
			//Preenche os campos
			stmt4.setString(1, matricula.trim());
			stmt4.setString(2, nmAluno.trim());

			//Executa a instrução SQL
			stmt4.execute();
			stmt4.close();

			con.close();
			JOptionPane.showMessageDialog(null,"Aluno cadastrado com sucesso!\nAluno:\n"+nmAluno+"\nRegistro de matrícula:\n"+matricula,"Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	
	
//	-------------Exclusões---------------------------------------------------------------------//
	public void removerAluno(String cdMatricula){
		try{
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();		
		
			String condicao ="DELETE FROM tb_Alunos WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao2 = "DELETE FROM tb_HistoricoPro WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao3 = "DELETE FROM tb_HistoricoContra WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao4 = "DELETE FROM tb_AlunosDocumentosApresentados WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao5 = "DELETE FROM tb_AlunosDocumentosPendentes WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao6 = "DELETE FROM tb_AlunosDocumentosSolicitados WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao7 = "DELETE FROM tb_Alunos_Usuarios_Sena_Quatro WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao8 = "DELETE FROM tb_AlunoMenorIdade WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao9 = "DELETE FROM tb_AlunoMatriculaTrancada WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao10 = "DELETE FROM tb_AlunoSemClasse WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao11 = "DELETE FROM tb_AlunoPerfil WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao12 = "DELETE FROM tb_AlunoCurriculo WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao13 = "DELETE FROM tb_AlunoCurriculoExperiencias WHERE cd_MatriculaAluno='"+cdMatricula+"';";
			String condicao14 = "DELETE FROM tb_AlunoCurriculoCursos WHERE cd_MatriculaAluno='"+cdMatricula+"';";

			st.executeUpdate(condicao);//Remover aluno da tabela alunos
			st.executeUpdate(condicao2);//Remover aluno da tabela histórico pró
			st.executeUpdate(condicao3);//Remover aluno da tabela histórico contra
			st.executeUpdate(condicao4);//Remover aluno da tabela documentos apresentados
			st.executeUpdate(condicao5);//Remover aluno da tabela documentos pendentes
			st.executeUpdate(condicao6);//Remover aluno da tabela documentos solicitados
			st.executeUpdate(condicao7);//Remover aluno da tabela alunos usuários sena 4
			st.executeUpdate(condicao8);//Remover aluno da tabela aluno menor idade
			st.executeUpdate(condicao9);//Remover aluno da tabela matrícula trancada
			st.executeUpdate(condicao10);//Remover aluno da tabela Alunos sem classe
			st.executeUpdate(condicao11);//Remover aluno da tabela perfil
			st.executeUpdate(condicao12);//Remover aluno da tabela currículo
			st.executeUpdate(condicao13);//Remover aluno da tabela experiencias
			st.executeUpdate(condicao14);//Remover aluno da tabela cursos			

			st.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Aluno excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}


//	-------------Retorno de dados cadastrados-------------------------------------------------//
	public String getBairro() {
		return bairro;
	}
	public String getBairroResponsavel() {
		return bairroResponsavel;
	}
	public String getCaminhoFoto() {
		return caminhoFoto;
	}
	public int getCdCiclo() {
		return cdCiclo;
	}
	public int getCdClasse() {
		return cdClasse;
	}
	public String getCdCpf() {
		return cdCpf;
	}
	public String getCdCpfResponsavel() {
		return cdCpfResponsavel;
	}
	public String getCdRg() {
		return cdRg;
	}
	public String getCdRgResponsavel() {
		return cdRgResponsavel;
	}
	public String getCep() {
		return cep;
	}
	public String getCepResponsavel() {
		return cepResponsavel;
	}
	public String getCidade() {
		return cidade;
	}
	public String getCidadeResponsavel() {
		return cidadeResponsavel;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getComplementoResponsavel() {
		return complementoResponsavel;
	}
	public String getDsDocumentoApresentado() {
		return dsDocumentoApresentado;
	}
	public String getDsDocumentoPendente() {
		return dsDocumentoPendente;
	}
	public String getDsDocumentoSolicitado() {
		return dsDocumentoSolicitado;
	}
	public String getDsHistoricoContra() {
		return dsHistoricoContra;
	}
	public String getDsHistoricoPro() {
		return dsHistoricoPro;
	}
	public String getDtApresentado() {
		return dtApresentado;
	}
	public String getDtHistoricoContra() {
		return dtHistoricoContra;
	}
	public String getDtHistoricoPro() {
		return dtHistoricoPro;
	}
	public String getDtMatricula() {
		return dtMatricula;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public String getDtNascimentoResponsavel() {
		return dtNascimentoResponsavel;
	}
	public String getDtPendente() {
		return dtPendente;
	}
	public String getDtSolicitado() {
		return dtSolicitado;
	}
	public String getDtTrancamento() {
		return dtTrancamento;
	}
	public String getEmail() {
		return email;
	}
	public String getEmailResponsavel() {
		return emailResponsavel;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getEnderecoResponsavel() {
		return enderecoResponsavel;
	}
	public String getEstado() {
		return estado;
	}
	public String getEstadoNatural() {
		return estadoNatural;
	}
	public String getEstadoNaturalResponsavel() {
		return estadoNaturalResponsavel;
	}
	public String getEstadoResponsavel() {
		return estadoResponsavel;
	}
	public String getMatricula() {
		return matricula;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public String getNacionalidadeResponsavel() {
		return nacionalidadeResponsavel;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public String getNaturalidadeResponsavel() {
		return naturalidadeResponsavel;
	}
	public String getNmAluno() {
		return nmAluno;
	}
	public String getNmClasseUltima() {
		return nmClasseUltima;
	}
	public String getNmCurso() {
		return nmCurso;
	}
	public String getNmDocumentoApresentado() {
		return nmDocumentoApresentado;
	}
	public String getNmDocumentoPendente() {
		return nmDocumentoPendente;
	}
	public String getNmDocumentoSolicitado() {
		return nmDocumentoSolicitado;
	}
	public String getNmLogin() {
		return nmLogin;
	}
	public String getNmMae() {
		return nmMae;
	}
	public String getNmPai() {
		return nmPai;
	}
	public String getNmResponsavel() {
		return nmResponsavel;
	}
	public String getNumero() {
		return numero;
	}
	public String getNumeroResponsavel() {
		return numeroResponsavel;
	}
	public String getRgOrgao() {
		return rgOrgao;
	}
	public String getRgOrgaoResponsavel() {
		return rgOrgaoResponsavel;
	}
	public String getCdSenha() {
		return cdSenha;
	}
	public String getSexo() {
		return sexo;
	}
	public String getSexoResponsavel() {
		return sexoResponsavel;
	}
	public String getStatusDocumentoSolicitado() {
		return statusDocumentoSolicitado;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getTelefoneResponsavel() {
		return telefoneResponsavel;
	}
	public String getTituloContra() {
		return tituloContra;
	}
	public String getTituloPro() {
		return tituloPro;
	}
	public boolean getExibirCurriculo() {
		return exibirCurriculo;
	}
	public boolean getExibirPerfil() {
		return exibirPerfil;
	}
	public boolean getExibirProsContrass() {
		return exibirProsContras;
	}
	public String getSkin(){
		return skin;
	}

}
