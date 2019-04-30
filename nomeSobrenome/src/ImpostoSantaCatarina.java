/**
 * @author 1829799
 *
 */
public abstract class ImpostoSantaCatarina extends Imposto {

	public ImpostoSantaCatarina(Double valor) {
		super(valor);
		
	}

	public Double calcularImpostoEstadual(Double valor) {
		
		return valor * 0.1;
		
	}

}