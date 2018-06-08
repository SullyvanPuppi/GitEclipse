package persistencia;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConsultaLogin extends ConexaoBD{

	private int permissao;

	private String login;

	//-----Retorna o login do usu�rio atual
	public String getLogin(){
		return this.login;
	}

	//--Ajusta a permiss�o do usu�rio atual
	public void setPermissao(int x){
		this.permissao = x;
	}
	public int getPermissao(){
		return permissao;
	}	
	public ConsultaLogin(String login, String senha){
		try{
			String msg = "Usu�rio e senha incorretos.";
			String titulo = "Erro";
			String condicao = "SELECT * FROM tb_usuarios_sena_um WHERE nm_Login='"+login+"';";
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				String senhaReal = rs.getString("cd_Senha");

				if (senha.equals(senhaReal)){
					String grupo = rs.getString("nm_Grupo");
					/*Tabela de grupos de direitos de acesso ao sistema
					 * 1 - Administrador;
					 * 2 - Coordenador;
					 * 3 - Secret�rio;
					 * 4 - Convidado.
					 */
					if (grupo.equals("Administrador")){
						setPermissao(1);
						titulo = "Bem vindo";
						msg = "Bem vindo "+rs.getString("nm_Usuario")+", voc� possui direitos de: Administrador.";
					}else if (grupo.equals("Coordenador de cursos")){
						setPermissao(2);
						titulo = "Bem vindo";
						msg = "Bem vindo "+rs.getString("nm_Usuario")+", voc� possui direitos de: Coordenador de cursos.";
					}else if (grupo.equals("Secretaria")){
						setPermissao(3);
						titulo = "Bem vindo";
						msg = "Bem vindo "+rs.getString("nm_Usuario")+", voc� possui direitos de: Secretaria.";
					}else if (grupo.equals("Convidado")){
						setPermissao(4);
						titulo = "Bem vindo";
						msg = "Bem vindo "+rs.getString("nm_Usuario")+", voc� possui direitos de: Convidado.";
					}this.login = rs.getString("nm_login");
				}
			}else{
				msg = "Usu�rio n�o existe!";
			}
			JOptionPane.showMessageDialog(null,msg,titulo,1);
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null,"Preencha os campos corretamente.","Erro",2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Erro interno do sistema.","Erro",2);
		}
	}
}