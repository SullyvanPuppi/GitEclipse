package negocios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.view.JasperViewer;

import persistencia.CadastroVenda;

public class Venda {

	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;

	//--Dados da empresa representada
	private String txtCnpjRepresentada = null;
	private String cmbNmRepresentada = null;

	//--Dados da empresa cliente
	private String txtCnpjCliente = null;
	private String cmbNmCliente = null;

	//--Dados da empresa representada para guia comissao
	private String txtCnpjRepresentada2 = null;
	private String cmbNmRepresentada2 = null;

	//--Dados da empresa cliente para guia comissao
	private String txtCnpjCliente2 = null;
	private String cmbNmCliente2 = null;

	//--Dados da venda
	private String txtNtFiscal = null;
	private String txtNtPedido = null;
	private String txtDtVenda = null;
	private String txtNmProduto = null;
	private String txtVlProduto = null;
	private double vlProduto = 0;	
	private String cmbMedida = null;
	private String txtQtProduto = null;
	private int qtProduto = 0;
	private String txtVlAcrescido = null;
	private double vlAcrescido = 0;
	private String txtVlFinal = null;
	private String txtDtEntrega = null;

	//--Dados da venda para a guia comissão
	private String txtNtFiscal2 = null;
	private String txtDtVenda2 = null;
	private String txtVlFinal2 = null;
	private String txtDtEntrega2 = null;

	//--Dados do vendedor
	private String txtVendedor = null;
	private String cmbVendedores = null;
	private boolean chkComissionado;

	//--Dados do vendedor para aguia comissão
	private String txtVendedor2 = null;
	private String cmbVendedores2 = null;

	private String txtObs = null;

	//--Guia comissão
	private String txtComissao = null;
	private double vlComissao = 0;

	public Venda() {
		super();
	}
	public void ajustaDados(String txtCnpjRepresentada, String cmbNmRepresentada, String txtCnpjCliente, String cmbNmCliente, String txtNtFiscal, String txtDtVenda, String txtNmProduto, String txtVlProduto, String cmbMedida, String txtQtProduto, String txtVlAcrescido, String txtVlFinal, String txtDtEntrega, String txtVendedor, String cmbVendedores, String txtObs, String txtNtPedido) {
		this.txtCnpjRepresentada = txtCnpjRepresentada.trim();
		this.cmbNmRepresentada = cmbNmRepresentada;
		this.txtCnpjCliente = txtCnpjCliente.trim();
		this.cmbNmCliente = cmbNmCliente;
		this.txtNtFiscal = txtNtFiscal.trim();
		this.txtDtVenda = txtDtVenda.trim();
		this.txtNmProduto = txtNmProduto.trim();
		this.txtVlProduto = txtVlProduto.trim();
		this.cmbMedida = cmbMedida.trim();
		this.txtQtProduto = txtQtProduto.trim();
		this.txtVlAcrescido = txtVlAcrescido.trim();
		this.txtVlFinal = txtVlFinal.trim();
		this.txtDtEntrega = txtDtEntrega.trim();
		this.txtVendedor = txtVendedor.trim();
		this.cmbVendedores = cmbVendedores;
		this.txtObs = txtObs.trim();
		this.txtNtPedido = txtNtPedido.trim();
	}
	public int validaDados(){
		if(getCmbNmRepresentada().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nNome da empresa representada nesta venda inválido";	
		}
		if(getCmbNmCliente().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nNome da empresa cliente nesta venda inválido";	
		}
		if(getCmbVendedores().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nNome do vendedor nesta venda inválido";	
		}
		if(getTxtNtFiscal().equals("")){
			this.status +=1;
			this.msgStatus +="\nNota fiscal desta venda inválido";	
		}
		if(getTxtNtPedido().equals("")){
			this.status +=1;
			this.msgStatus +="\nNota do pedido desta venda inválido";	
		}
		if(getTxtDtVenda().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData desta venda inválido";	
		}else{
			if(validaData(getTxtDtVenda())>0){
				this.status +=1;
				this.msgStatus +="\nData desta venda inválido";	
			}
		}
		if(getTxtDtEntrega().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData para entrega desta venda inválido";	
		}else{
			if(validaData(getTxtDtEntrega())>0){
				this.status +=1;
				this.msgStatus +="\nData da entrega desta venda inválido";	
			}else{
				if(validaDataEntrega(getTxtDtVenda(), getTxtDtEntrega())>0){
					this.status +=1;
					this.msgStatus +="\nData da entrega desta venda inválido";
				}
			}
		}
		if(getTxtNmProduto().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome do produto nesta venda inválido";	
		}
		if(getTxtVlProduto().equals("") || getTxtVlProduto().equals("R$ 0,00")){
			this.status +=1;
			this.msgStatus +="\nValor do produto nesta venda inválido";	
		}else{
			this.vlProduto = Double.parseDouble(getTxtVlProduto().replace(".","").replace(",",".").substring(3));
		}
		if(getCmbMedida().equals("----------")){
			this.status +=1;
			this.msgStatus +="\nUnidade de medida nesta venda inválido";	
		}
		if(getTxtQtProduto().equals("")){
			this.status +=1;
			this.msgStatus +="\nQuantidade do produto nesta venda inválido";	
		}else{
			this.qtProduto = Integer.parseInt(getTxtQtProduto());
		}
		this.vlAcrescido = Double.parseDouble(getTxtVlAcrescido().replace(".","").replace(",",".").substring(3));
		if(getTxtVlFinal().equals("") || getTxtVlFinal().equals("R$ 0,00")){
			this.status +=1;
			this.msgStatus +="\nValores informados nesta venda inválido";	
		}
		return status;
	}
	public int verificaExiste(){
		CadastroVenda consultar = new CadastroVenda();
		return consultar.verificaExiste(getTxtNtFiscal());
	}
	public void ajustaGuiaComissao(){
		this.txtCnpjRepresentada2 = txtCnpjRepresentada.trim();
		this.cmbNmRepresentada2 = cmbNmRepresentada.trim();
		this.txtCnpjCliente2 = txtCnpjCliente.trim();
		this.cmbNmCliente2 = cmbNmCliente.trim();
		this.txtNtFiscal2 = txtNtFiscal.trim();
		this.txtDtVenda2 = txtDtVenda.trim();
		this.txtVlFinal2 = txtVlFinal.trim();
		this.txtDtEntrega2 = txtDtEntrega.trim();
		this.txtVendedor2 = txtVendedor.trim();
		this.cmbVendedores2 = cmbVendedores.trim();
	}
	public int validaDataEntrega(String dtCompra, String dtEntrega){//Se retorna 1, data de entrega inválida
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataCompra = null;
		Date dataEntrega = null;

		try {
			dataCompra= sdf.parse(dtCompra);
			dataEntrega= sdf.parse(dtEntrega);
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataCompra);
		date2.setTime(dataEntrega);

		if (dataEntrega.before(dataCompra)) {
			x = 1;
		}
		return x;
	}
	public int validaData(String data){
		int x = 0;

		int dia = Integer.parseInt(data.substring(0, 2));

		int mes = Integer.parseInt(data.substring(3, 5));

		if(dia>31 || dia<1){
			x=1;
		}
		if(mes>12 || mes<1){
			x=1;
		}
		return x;
	}
	public void cadastrar(){
		CadastroVenda cadastrar = new CadastroVenda();
		cadastrar.ajustaDados(getTxtCnpjRepresentada(), getCmbNmRepresentada(), getTxtCnpjCliente(), getCmbNmCliente(), getTxtNtFiscal(), getTxtDtVenda(), getTxtNmProduto(), vlProduto, getCmbMedida(), qtProduto, vlAcrescido, txtVlFinal, getTxtDtEntrega(), getTxtVendedor(), getCmbVendedores(), getChkComissionado(), getTxtObs(), vlComissao, getTxtNtPedido());
		cadastrar.cadastrar();
	}
	public void ajustaComissao(String comissao){
		this.vlComissao = Double.parseDouble(comissao.replace(".","").replace(",",".").substring(3));
	}
	public void cadastrarComissao(){
		CadastroVenda cadastrar = new CadastroVenda();
		cadastrar.ajustaDados(getTxtCnpjRepresentada(), getCmbNmRepresentada(), getTxtCnpjCliente(), getCmbNmCliente(), getTxtNtFiscal(), getTxtDtVenda(), getTxtNmProduto(), vlProduto, getCmbMedida(), qtProduto, vlAcrescido, txtVlFinal, getTxtDtEntrega(), getTxtVendedor(), getCmbVendedores(), getChkComissionado(), getTxtObs(), vlComissao, getTxtNtPedido());
		cadastrar.cadastrarComissao(getTxtNtFiscal(), getCmbVendedores());
	}
	public void alterar(){
		CadastroVenda cadastrar = new CadastroVenda();
		cadastrar.ajustaDados(getTxtCnpjRepresentada(), getCmbNmRepresentada(), getTxtCnpjCliente(), getCmbNmCliente(), getTxtNtFiscal(), getTxtDtVenda(), getTxtNmProduto(), vlProduto, getCmbMedida(), qtProduto, vlAcrescido, txtVlFinal, getTxtDtEntrega(), getTxtVendedor(), getCmbVendedores(), getChkComissionado(), getTxtObs(), vlComissao, getTxtNtPedido());
		cadastrar.alterar();
	}	
	public void alterarComissao(){
		CadastroVenda cadastrar = new CadastroVenda();
		cadastrar.ajustaDados(getTxtCnpjRepresentada(), getCmbNmRepresentada(), getTxtCnpjCliente(), getCmbNmCliente(), getTxtNtFiscal(), getTxtDtVenda(), getTxtNmProduto(), vlProduto, getCmbMedida(), qtProduto, vlAcrescido, txtVlFinal, getTxtDtEntrega(), getTxtVendedor(), getCmbVendedores(), getChkComissionado(), getTxtObs(), vlComissao, getTxtNtPedido());
		cadastrar.alterarComissao(getTxtNtFiscal(), getTxtVendedor());
	}
	public void consultar(String notaFiscal){
		CadastroVenda consultar = new CadastroVenda();
		consultar.consultar(notaFiscal);
		this.txtCnpjRepresentada = consultar.getTxtCnpjRepresentada();
		this.cmbNmRepresentada = consultar.getCmbNmRepresentada();
		this.txtCnpjCliente = consultar.getTxtCnpjCliente();
		this.cmbNmCliente = consultar.getCmbNmCliente();
		this.txtNtFiscal = consultar.getTxtNtFiscal();
		this.txtDtVenda = consultar.getTxtDtVenda();
		this.txtNmProduto = consultar.getTxtNmProduto();
		this.txtVlProduto = ""+consultar.getVlProduto();
		/*if(consultar.getVlProduto()<100000){
			if(consultar.getVlProduto()<10000){
				if(consultar.getVlProduto()<1000){
					if(consultar.getVlProduto()<100){
						if(consultar.getVlProduto()<10){
							if(consultar.getVlProduto()==0){
								String tot = ""+consultar.getVlProduto();
								tot = ""+tot.replace(".",",").replace(",",".");
								double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
								if(b<10){
									tot = tot+"00";
								}
								String z = 0+""+0+""+0+""+0+""+0+tot;
								z = z.replace(",",".").replace(".",",");
								this.txtVlProduto = z;
							}else{
								String tot = ""+consultar.getVlProduto();
								tot = ""+tot.replace(".",",").replace(",",".");
								double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
								if(b<10){
									tot = tot+"0";
								}
								String z = 0+""+0+""+0+""+0+""+0+tot;
								z = z.replace(",",".").replace(".",",");
								this.txtVlProduto = z;
							}
						}else{
							String tot = ""+consultar.getVlProduto();
							tot = ""+tot.replace(".",",").replace(",",".");
							double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
							if(b<10){
								tot = tot+"0";
							}
							String z = 0+""+0+""+0+""+0+tot;
							z = z.replace(",",".").replace(".",",");
							this.txtVlProduto = z;
						}
					}else{
						String tot = ""+consultar.getVlProduto();
						tot = ""+tot.replace(".",",").replace(",",".");
						double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
						if(b<10){
							tot = tot+"0";
						}
						String z = 0+""+0+""+0+tot;
						z = z.replace(",",".").replace(".",",");
						this.txtVlProduto = z;
					}
				}else{
					String tot = ""+consultar.getVlProduto();
					tot = ""+tot.replace(".",",").replace(",",".");
					double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
					if(b<10){
						tot = tot+"0";
					}
					String z = 0+""+0+tot;
					z = z.replace(",",".").replace(".",",");
					this.txtVlProduto = z;
				}
			}else{
				String tot = ""+consultar.getVlProduto();
				tot = ""+tot.replace(".",",").replace(",",".");
				double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
				if(b<10){
					tot = tot+"0";
				}
				String z = 0+tot;
				z = z.replace(",",".").replace(".",",");
				this.txtVlProduto = z;
			}
		}else{
			String tot = ""+consultar.getVlProduto();
			tot = ""+tot.replace(".",",").replace(",",".");
			double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
			if(b<10){
				tot = tot+"0";
			}
			String z = tot;
			z = z.replace(",",".").replace(".",",");
			this.txtVlProduto = z;
		}*/

		this.cmbMedida = consultar.getCmbMedida();
		if(consultar.getQtProduto()<100000){
			this.txtQtProduto = "0";
			this.txtQtProduto += (String) (Double.toString(consultar.getQtProduto()));
		}else{
			this.txtQtProduto = (String) (Double.toString(consultar.getQtProduto()));
		}
		if(consultar.getQtProduto()<10000){
			this.txtQtProduto = "00";
			this.txtQtProduto += (String) (Double.toString(consultar.getQtProduto()));
		}if(consultar.getQtProduto()<1000){
			this.txtQtProduto = "000";
			this.txtQtProduto += (String) (Double.toString(consultar.getQtProduto()));
		}if(consultar.getQtProduto()<100){
			this.txtQtProduto = "0000";
			this.txtQtProduto += (String) (Double.toString(consultar.getQtProduto()));
		}if(consultar.getQtProduto()<10){
			this.txtQtProduto = "00000";
			this.txtQtProduto += (String) (Double.toString(consultar.getQtProduto()));
		}
		this.txtVlAcrescido = ""+consultar.getVlAcrescido();
		/*if(consultar.getVlAcrescido()<100000){
			if(consultar.getVlAcrescido()<10000){
				if(consultar.getVlAcrescido()<1000){
					if(consultar.getVlAcrescido()<100){
						if(consultar.getVlAcrescido()<10){
							if(consultar.getVlAcrescido()==0){
								String tot = ""+consultar.getVlAcrescido();
								tot = ""+tot.replace(".",",").replace(",",".");
								double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
								if(b<10){
									tot = tot+"00";
								}
								String z = 0+""+0+""+0+""+0+""+0+tot;
								z = z.replace(",",".").replace(".",",");
								this.txtVlAcrescido = z;
							}else{
								String tot = ""+consultar.getVlAcrescido();
								tot = ""+tot.replace(".",",").replace(",",".");
								double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
								if(b<10){
									tot = tot+"0";
								}
								String z = 0+""+0+""+0+""+0+""+0+tot;
								z = z.replace(",",".").replace(".",",");
								this.txtVlAcrescido = z;
							}
						}else{
							String tot = ""+consultar.getVlAcrescido();
							tot = ""+tot.replace(".",",").replace(",",".");
							double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
							if(b<10){
								tot = tot+"0";
							}
							String z = 0+""+0+""+0+""+0+tot;
							z = z.replace(",",".").replace(".",",");
							this.txtVlAcrescido = z;
						}
					}else{
						String tot = ""+consultar.getVlAcrescido();
						tot = ""+tot.replace(".",",").replace(",",".");
						double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
						if(b<10){
							tot = tot+"0";
						}
						String z = 0+""+0+""+0+tot;
						z = z.replace(",",".").replace(".",",");
						this.txtVlAcrescido = z;
					}
				}else{
					String tot = ""+consultar.getVlAcrescido();
					tot = ""+tot.replace(".",",").replace(",",".");
					double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
					if(b<10){
						tot = tot+"0";
					}
					String z = 0+""+0+tot;
					z = z.replace(",",".").replace(".",",");
					this.txtVlAcrescido = z;
				}
			}else{
				String tot = ""+consultar.getVlAcrescido();
				tot = ""+tot.replace(".",",").replace(",",".");
				double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
				if(b<10){
					tot = tot+"0";
				}
				String z = 0+tot;
				z = z.replace(",",".").replace(".",",");
				this.txtVlAcrescido = z;
			}
		}else{
			String tot = ""+consultar.getVlAcrescido();
			tot = ""+tot.replace(".",",").replace(",",".");
			double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
			if(b<10){
				tot = tot+"0";
			}
			String z = tot;
			z = z.replace(",",".").replace(".",",");
			this.txtVlAcrescido = z;
		}*/

		this.txtVlFinal = consultar.getVlFinal();
		this.txtDtEntrega = consultar.getTxtDtEntrega();
		this.txtVendedor = consultar.getTxtVendedor();
		this.cmbVendedores = consultar.getCmbVendedores();
		this.chkComissionado = consultar.getChkComissionado();
		this.txtObs = consultar.getTxtObs();
		
		this.txtComissao = ""+consultar.getTxtComissao();
		/*if(consultar.getTxtComissao()<100000){
			if(consultar.getTxtComissao()<10000){
				if(consultar.getTxtComissao()<1000){
					if(consultar.getTxtComissao()<100){
						if(consultar.getTxtComissao()<10){
							if(consultar.getTxtComissao()==0){
								String tot = ""+consultar.getTxtComissao();
								tot = ""+tot.replace(".",",").replace(",",".");
								double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
								if(b<10){
									tot = tot+"00";
								}
								String z = 0+""+0+""+0+""+0+""+0+tot;
								z = z.replace(",",".").replace(".",",");
								this.txtComissao = z;
							}else{
								String tot = ""+consultar.getTxtComissao();
								tot = ""+tot.replace(".",",").replace(",",".");
								double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
								if(b<10){
									tot = tot+"0";
								}
								String z = 0+""+0+""+0+""+0+""+0+tot;
								z = z.replace(",",".").replace(".",",");
								this.txtComissao = z;
							}
						}else{
							String tot = ""+consultar.getTxtComissao();
							tot = ""+tot.replace(".",",").replace(",",".");
							double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
							if(b<10){
								tot = tot+"0";
							}
							String z = 0+""+0+""+0+""+0+tot;
							z = z.replace(",",".").replace(".",",");
							this.txtComissao = z;
						}
					}else{
						String tot = ""+consultar.getTxtComissao();
						tot = ""+tot.replace(".",",").replace(",",".");
						double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
						if(b<10){
							tot = tot+"0";
						}
						String z = 0+""+0+""+0+tot;
						z = z.replace(",",".").replace(".",",");
						this.txtComissao = z;
					}
				}else{
					String tot = ""+consultar.getTxtComissao();
					tot = ""+tot.replace(".",",").replace(",",".");
					double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
					if(b<10){
						tot = tot+"0";
					}
					String z = 0+""+0+tot;
					z = z.replace(",",".").replace(".",",");
					this.txtComissao = z;
				}
			}else{
				String tot = ""+consultar.getTxtComissao();
				tot = ""+tot.replace(".",",").replace(",",".");
				double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
				if(b<10){
					tot = tot+"0";
				}
				String z = 0+tot;
				z = z.replace(",",".").replace(".",",");
				this.txtComissao = z;
			}
		}else{
			String tot = ""+consultar.getTxtComissao();
			tot = ""+tot.replace(".",",").replace(",",".");
			double b = Double.parseDouble(tot.substring(tot.lastIndexOf(".")+1));
			if(b<10){
				tot = tot+"0";
			}
			String z = tot;
			z = z.replace(",",".").replace(".",",");
			this.txtComissao = z;
		}*/
		this.txtNtPedido = consultar.getTxtNtPedido();


	}
	DefaultTableModel tabelaVendas = new DefaultTableModel();

	public DefaultTableModel getTabelaVendas(){
		return this.tabelaVendas;
	}
	public void consultarVendas(String nmCliente, String nmRepresentada, String nmVendedor, String periodoDe, String periodoA){
		CadastroVenda consultar = new CadastroVenda();
		if(!periodoDe.trim().equals("/  /") && periodoA.trim().equals("/  /")){
			JOptionPane.showMessageDialog(null,"Para pesquisar por período digite o intervalo que deseja encontrar válido","Notificação",1);	
		}
		if(!periodoA.trim().equals("/  /") && periodoDe.trim().equals("/  /")){
			JOptionPane.showMessageDialog(null,"Para pesquisar por período digite o intervalo que deseja encontrar válido","Notificação",1);
		}
		if(periodoDe.trim().equals("/  /") || periodoA.trim().equals("/  /")){
			consultar.consultar(nmCliente.trim(), nmRepresentada.trim(), nmVendedor.trim(), "/  /", "/  /");	
		}else{
			if(validaData(periodoDe)>0 || validaData(periodoA)>0){
				JOptionPane.showMessageDialog(null,"Para pesquisar por período digite o intervalo que deseja encontrar válido","Notificação",1);
			}else{
				consultar.consultar(nmCliente.trim(), nmRepresentada.trim(), nmVendedor.trim(), periodoDe.trim(), periodoA.trim());	
			}			
		}
		this.registros = consultar.getRegistros();
		this.tabelaVendas = consultar.getTabelaVendas();
	}
	public void remover(String notaFiscal){
		CadastroVenda cadastrar = new CadastroVenda();
		cadastrar.remover(notaFiscal);
	}

	//-------------------Retorno de dados

	public boolean getChkComissionado() {
		return chkComissionado;
	}
	public String getCmbMedida() {
		return cmbMedida;
	}
	public String getCmbNmCliente() {
		return cmbNmCliente;
	}
	public String getCmbNmCliente2() {
		return cmbNmCliente2;
	}
	public String getCmbNmRepresentada() {
		return cmbNmRepresentada;
	}
	public String getCmbNmRepresentada2() {
		return cmbNmRepresentada2;
	}
	public String getCmbVendedores() {
		return cmbVendedores;
	}
	public String getCmbVendedores2() {
		return cmbVendedores2;
	}
	public String getTxtCnpjCliente() {
		return txtCnpjCliente;
	}
	public String getTxtCnpjCliente2() {
		return txtCnpjCliente2;
	}
	public String getTxtCnpjRepresentada() {
		return txtCnpjRepresentada;
	}
	public String getTxtCnpjRepresentada2() {
		return txtCnpjRepresentada2;
	}
	public String getTxtComissao() {
		return txtComissao;
	}
	public String getTxtDtEntrega() {
		return txtDtEntrega;
	}
	public String getTxtDtEntrega2() {
		return txtDtEntrega2;
	}
	public String getTxtDtVenda() {
		return txtDtVenda;
	}
	public String getTxtDtVenda2() {
		return txtDtVenda2;
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
	public String getTxtNtFiscal2() {
		return txtNtFiscal2;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public String getTxtQtProduto() {
		return txtQtProduto;
	}
	public String getTxtVendedor() {
		return txtVendedor;
	}
	public String getTxtVendedor2() {
		return txtVendedor2;
	}
	public String getTxtVlAcrescido() {
		return txtVlAcrescido;
	}
	public String getTxtVlFinal() {
		return txtVlFinal;
	}
	public String getTxtVlFinal2() {
		return txtVlFinal2;
	}
	public String getTxtVlProduto() {
		return txtVlProduto;
	}
	public String getMsgStatus(){
		return msgStatus;
	}
	public int registros(){
		return registros;
	}
	//----Consultar representadas para exibir
	public Vector consultarRepresentadas(){
		CadastroVenda consultar = new CadastroVenda();
		consultar.consultarCmbRepresentadas();
		return consultar.getRepresentadas();
	}
	//----Consultar vendedores para exibir
	public Vector consultarVendedores(){
		CadastroVenda consultar = new CadastroVenda();
		consultar.consultarCmbVendedores();
		return consultar.getVendedores();
	}
	//----Consultar clientes para exibir
	public Vector consultarClientes(){
		CadastroVenda consultar = new CadastroVenda();
		consultar.consultarCmbClientes();
		return consultar.getClientes();
	}
	//--Consultar cnpj de cliente
	public String cnpjCliente(String nmEmpresa){
		CadastroVenda consultar = new CadastroVenda();
		return consultar.cnpjCliente(nmEmpresa);
	}
	//--Consultar cnpj de representada
	public String cnpjRepresentada(String nmEmpresa){
		CadastroVenda consultar = new CadastroVenda();
		return consultar.cnpjRepresentada(nmEmpresa);
	}
	//--Consultar cnpj de cliente
	public String codFuncionario(String nmFuncionario){
		CadastroVenda consultar = new CadastroVenda();
		return consultar.codFuncionario(nmFuncionario);
	}
	public JasperViewer gerarRelatorio(int permissao, String nmCliente,String  nmRepresentada,String  nmVendedor,String  periodoDe,String  periodoA, String notaFiscal){
		CadastroVenda gerar = new CadastroVenda();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio(permissao, nmCliente, nmRepresentada, nmVendedor, periodoDe, periodoA, notaFiscal) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioResumido(){
		CadastroVenda gerar = new CadastroVenda();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioResumido() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioExtrato(int permissao, String  nmVendedor,String  periodoDe,String  periodoA){
		CadastroVenda gerar = new CadastroVenda();
		
		if(!periodoDe.trim().equals("/  /") && !periodoA.trim().equals("/  /") && !nmVendedor.trim().equals("--------------------------------")){
			if(validaData(periodoDe)>0 || validaData(periodoA)>0){
				JOptionPane.showMessageDialog(null,"Para gerar extrato de vendas é necessário selecionar o funcionário e o intervalo de dias desejado","Notificação",1);
			}else{
				//exibe o resultado
				JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioExtrato(permissao, nmVendedor, periodoDe, periodoA) , false );
				viewer.setTitle("Visualizador de relatório - Pesquero Representações");
				return viewer;	
			}
			JOptionPane.showMessageDialog(null,"Para gerar extrato de vendas é necessário selecionar o funcionário e o intervalo de dias desejado","Notificação",1);
		}else{
			JOptionPane.showMessageDialog(null,"Para gerar extrato de vendas é necessário selecionar o funcionário e o intervalo de dias desejado","Notificação",1);
		}
		return null;
	}
	public JasperViewer gerarRelatorioUteisVenda(){
		CadastroVenda gerar = new CadastroVenda();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioUteisVenda() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
