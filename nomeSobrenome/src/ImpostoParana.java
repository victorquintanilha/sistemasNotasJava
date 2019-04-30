/**
 * @author 1829799
 *
 */
public abstract class ImpostoParana extends Imposto {

	public ImpostoParana(Double valor) {
		super(valor);
		
	}

	public Double calcularImpostoEstadual(Double valor) {
		
		return valor * 0.05;
		
	}
	

}