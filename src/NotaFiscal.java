import java.util.Date;

/**
 * @author 1829203
 *
 */
public class NotaFiscal {

	private String numero;
	private String descricao;
	private Date dataEmissao;
	private Imposto imposto;
	private Double valor;
	private Double valorComImposto;
	private boolean cancelada;
	
	public NotaFiscal(String numero, String descricao, Date dataEmissao, Imposto imposto, Double valor,
			Double valorComImposto, boolean cancelada) {
		super();
		this.numero = numero;
		this.descricao = descricao;
		this.dataEmissao = dataEmissao;
		this.imposto = imposto;
		this.valor = valor;
		this.valorComImposto = valorComImposto;
		this.cancelada = cancelada;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorComImposto() {
		return valorComImposto;
	}

	public void setValorComImposto(Double valorComImposto) {
		this.valorComImposto = valorComImposto;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	@Override
	public String toString() {
		return "NotaFiscal\n\nNúmero: " + numero + "\nDescricao: " + descricao + "\nData Emissão: " + dataEmissao
				+ "\nImposto: " + imposto + "\nValor: " + valor + "\nValor com Imposto: " + valorComImposto + "\nCancelada: "
				+ cancelada;
	}
	
}