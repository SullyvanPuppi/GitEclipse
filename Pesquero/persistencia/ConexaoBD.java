/**
 * 
 */
package persistencia;

/**
 * @author Sullyvan Puppi
 *
 */
public class ConexaoBD {

	//private String driver = "com.mysql.jdbc.Driver";
	//private String url = "jdbc:mysql://localhost/pesquero";
	//private String usuario = "root";
	//private String senha = "123";
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://200.234.202.107/pesquerorepres";
	private String usuario = "pesquerorepres";
	private String senha = "pesquero@banco@";
	
	public String getDriverBd() {
		return this.driver;
	}
	public String getSenhaBd() {
		return this.senha;
	}
	public String getUrlBd() {
		return this.url;
	}
	public String getUsuarioBd() {
		return usuario;
	}
}