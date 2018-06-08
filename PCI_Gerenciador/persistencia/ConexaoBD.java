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
	//private String url = "jdbc:mysql://localhost/pci";
	//private String usuario = "root";
	//private String senha = "123";
	
	private String driver = "com.mysql.jdbc.Driver";
	//private String url = "jdbc:mysql://200.234.214.78/pesqueroconstr";
	//ip servidor phoenix 201.76.50.91
	private String url = "jdbc:mysql://201.76.50.91/pesqueroconstr";
	private String usuario = "pesqueroconstr";
	private String senha = "pci@adm@";
	
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