import java.util.ArrayList;
import java.util.Date;

/**
 * Classe principal da aplicação
 * 
 * @author 1829799
 *
 */
public class Principal {

	static ArrayList<Empresa> empresas = new ArrayList<>();

	/**
	 * Executa o metodo da main
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		/**
		 * Menu Principal
		 */
		String[] opcoes = { "Empresas", "Notas Fiscais", "Relatórios" };
		boolean continua = true;
		do {
			int opcao = Console.mostrarMenu(opcoes, "Sistema de Notas", null);
			switch (opcao) {

			case 1:
				menuEmpresa();
				break;

			case 2:
				menuNotaFiscal();
				break;

			case 3:
				menuRelatorios();
				break;

			case -1:
				System.out.println("Saindo do Sistema...");
				continua = false;
				break;
			}

		} while (continua);

	}

	private static void menuEmpresa() {

		String[] opcoesMenuEmpresas = { "Cadastrar", "Consultar", "Excluir" };
		boolean continua = true;
		String buscaCNPJ;
		int index = 0;
		do {
			int opcaoMenuEmpresas = Console.mostrarMenu(opcoesMenuEmpresas, "Empresas", "Voltar");

			switch (opcaoMenuEmpresas) {

			case 1: // Adicionar
				criarEmpresa();
				break;

			case 2: // Consultar

				buscaCNPJ = Console.recuperaTexto("Informe o CNPJ: ");
				index = consultarEmpresa(buscaCNPJ);

				if (index >= 0) {
					System.out.println(empresas.get(index));
					System.out.println();

				} else {
					System.out.println("CNPJ não encontrado...");
					System.out.println();
				}

				break;
			case 3: // Excluir

				buscaCNPJ = Console.recuperaTexto("Informe o CNPJ: ");
				index = consultarEmpresa(buscaCNPJ);
				
				
				
				if (index >= 0) {
					System.out.println();
					String confirmacao = Console.recuperaTexto("Deseja excluir essa empresa? Sim(S) Não(N): ");
					if (confirmacao.equalsIgnoreCase("s")) {
						empresas.remove(index);
						System.out.println("Empresa Excluida!");
					} else {
						System.out.println("Exclusão cancelada...");
						System.out.println();
					}
				}

				break;

			case -1:
				System.out.println("Saindo do Sistema...");
				continua = false;
				break;
			}

		} while (continua);

	}

	private static void menuNotaFiscal() {

		String[] opcoes = { "Emitir", "Consultar", "Cancelar" };
		boolean continua = true;
		do {
			int opcao = Console.mostrarMenu(opcoes, "Notas Fiscais", "Voltar");
			switch (opcao) {

			case 1:
				emitirNotas();
				break;

			case 2:
				consultarNotas();
				break;

			case 3:
				cancelarNotas();
				break;
			case -1:
				System.out.println("Voltando ao Menu Principal...");
				continua = false;
				break;
			}

		} while (continua);

	}

	/**
	 * 
	 */
	private static void cancelarNotas() {
		
		String cnpj = Console.recuperaTexto("Informe o CNPJ da empresa: ");
		int index = consultarEmpresa(cnpj);
		Empresa empresaSolicitada = empresas.get(index);
		
		System.out.println(empresaSolicitada.getNotasFiscais());

		String numeroNota = Console.recuperaTexto("Informe o número da nota: ");
		String consultarNota = numeroNota;
		ArrayList<NotaFiscal> notas = empresaSolicitada.getNotasFiscais();
		
		for (NotaFiscal nota : notas) {
			if (consultarNota.equalsIgnoreCase(nota.getNumero())) {
				nota.setCancelada(true);
				System.out.println("Nota cancelada...");
			}
		}
		
	}

	/**
	 * 
	 */
	private static void consultarNotas() {
		
		String cnpj = Console.recuperaTexto("Informe o CNPJ da empresa: ");
		int index = consultarEmpresa(cnpj);
		Empresa empresaSolicitada = empresas.get(index);
		String numeroNota = Console.recuperaTexto("Informe o número da nota: ");
		String consultarNota = numeroNota;
		ArrayList<NotaFiscal> notas = empresaSolicitada.getNotasFiscais();
		
		for (NotaFiscal nota : notas) {
			if (consultarNota.equalsIgnoreCase(nota.getNumero())) {
				System.out.println(nota);
			}
		}
		
	}

	/**
	 * 
	 */
	private static void emitirNotas() {
		String cnpj = Console.recuperaTexto("Informe o CNPJ da empresa: ");
		int index = consultarEmpresa(cnpj);
		Empresa empresaSolicitada = empresas.get(index);
		empresaSolicitada.addNotaFiscal(criarNota());
		System.out.println("Nota Emitida...\n");
	}

	/**
	 * 
	 */
	private static void menuRelatorios() {

		String[] opcoes = { "Por Empresas", "Por Valor", "Notas Canceladas" };
		boolean continua = true;
		do {
			int opcao = Console.mostrarMenu(opcoes, "Relatórios", "Voltar");
			switch (opcao) {

			case 1:

				break;

			case 2:

				break;

			case 3:

				break;
			case -1:
				System.out.println("Voltando ao Menu Principal...");
				continua = false;
				break;
			}

		} while (continua);

	}

	/**
	 * 
	 * @return
	 */
	private static NotaFiscal criarNota() {

		String numero = Console.recuperaTexto("Informe o número da nota: ");
		String descricao = Console.recuperaTexto("Informe o motivo da nota: ");
		Double valor = Console.recuperaDecimal("Informe o valor da nota: ");
		Imposto imposto = null;
		boolean continua = true;
			
		do {
			int opcao = Console.recuperaInteiroPositivo("Escolha o Estado:\n1) Paraná\n2) Santa Catarina\n3) São Paulo.");
			switch (opcao) {

			case 1:
				imposto = new ImpostoParana(valor);
				continua = false;
				break;

			case 2:
				imposto = new ImpostoSantaCatarina(valor);
				continua = false;
				break;

			case 3:
				imposto = new ImpostoSaoPaulo(valor);
				continua = false;
				break;
			default:
				System.out.println("Número informado não corresponde a um Estado...\n");
							
			}
		
		} while (continua);
		
		NotaFiscal nota = new NotaFiscal(numero, descricao, imposto, valor);
		return nota;
	}

	/**
	 * Localiza uma empresa no ArrayList empresas
	 * 
	 * @param cnpj parametro de busca
	 * @return um inteiro com index do ArrayList empresas
	 */
	private static int consultarEmpresa(String cnpj) {

		String consultarEmpresa = cnpj;
		int i = -1;
		for (Empresa empresa : empresas) {
			i++;
			if (consultarEmpresa.compareTo(empresa.getCnpj()) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Cria uma empresa
	 * 
	 */
	private static void criarEmpresa() {

		String nome = Console.recuperaTexto("Digite o nome da empresa");
		String cnpj = Console.recuperaTexto("Digite o CNPJ da empresa");

		int index = consultarEmpresa(cnpj);

		if (index == -1) {
			Empresa empresa = new Empresa(nome, cnpj);
			empresas.add(empresa);
			System.out.println("Empresa adicionada!");
			System.out.println();
		} else {
			System.out.println("CNPJ já cadastrado...");
		}
	}

}