/**
 * 
 */
package persistencia;

/**
 * @author Sullyvan Puppi
 *
 */
public class ConexaoBD {

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/senadb";
	private String usuario = "root";
	private String senha = "123";
	
	//private String driver = "com.mysql.jdbc.Driver";
	//private String url = "jdbc:mysql://200.234.202.96/sistemanovaath";
	//private String usuario = "sistemanovaath";
	//private String senha = "perox@carne";
	
	public String getDriver() {
		return this.driver;
	}
	public String getSenha() {
		return this.senha;
	}
	public String getUrl() {
		return this.url;
	}
	public String getUsuario() {
		return usuario;
	}
}