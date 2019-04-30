/**
 * @author 1829799
 *
 */
public abstract class ImpostoSaoPaulo extends Imposto {

	public ImpostoSaoPaulo(Double valor) {
		super(valor);
		
	}

	public Double calcularImpostoEstadual(Double valor) {
		
		return valor * 0.18;
		
	}
	

}
