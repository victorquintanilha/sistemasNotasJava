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
		int index = 0;
		do {
			int opcaoMenuEmpresas = Console.mostrarMenu(opcoesMenuEmpresas, "Empresas", "Voltar");

			switch (opcaoMenuEmpresas) {

			case 1: // Adicionar
				criarEmpresa();
				break;

			case 2: // Consultar
				try {
				System.out.println(pegarEmpresa());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
//				buscaCNPJ = Console.recuperaTexto("Informe o CNPJ: ");
//				index = consultarEmpresa(buscaCNPJ);
//
//				if (index >= 0) {
//					System.out.println(empresas.get(index));
//					System.out.println();
//
//				} else {
//					System.out.println("CNPJ não encontrado...");
//					System.out.println();
//				}

				break;
			case 3: // Excluir

				String cnpj = Console.recuperaTexto("Informe o CNPJ: ");
				index = encontrarIndexEmpresa(cnpj);

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
	private static void menuRelatorios() {

		String[] opcoes = { "Por Empresas", "Notas Canceladas", "Por Valor" };
		boolean continua = true;
		do {
			int opcao = Console.mostrarMenu(opcoes, "Relatórios", "Voltar");
			switch (opcao) {

			case 1:
				gerarRelatorioPorEmpresa();
				break;
			case 2:
				gerarRelatorioNotasCanceladas();
				break;
			case 3:
				gerarRelatorioPorValorDaNota();
				break;
			case -1:
				System.out.println("Voltando ao Menu Principal...");
				continua = false;
				break;
			}

		} while (continua);

	}

	private static void gerarRelatorioPorValorDaNota() {

	}

	private static void gerarRelatorioNotasCanceladas() {

	}

	private static void gerarRelatorioPorEmpresa() {
		ArrayList<NotaFiscal> notasAtivas = new ArrayList<>();

		try {
		Empresa empresa = pegarEmpresa();

		for (NotaFiscal nota : empresa.getNotasFiscais()) {

			if (nota.isCancelada()) {
				notasAtivas.add(nota);
			}
		}
		System.out.println(notasAtivas);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Cancela a nota fiscal na empresa desejada
	 */
	private static void cancelarNotas() {
		try {
		String cnpj = Console.recuperaTexto("Informe o CNPJ da empresa: ");
		int index = encontrarIndexEmpresa(cnpj);
		Empresa empresa = pegarEmpresa();
		System.out.println(empresas.get(index).getNotasFiscais());
		String numeroNota = Console.recuperaTexto("Informe o número da nota: ");
		ArrayList<NotaFiscal> notas = empresas.get(index).getNotasFiscais();
		for (NotaFiscal nota : notas) {
			if (numeroNota.equalsIgnoreCase(nota.getNumero())) {
				nota.setCancelada(true);
				System.out.println("Nota cancelada...");
			}
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Consulta a nota fiscal na empresa desejada
	 */
	private static void consultarNotas() {
		try {
		Empresa empresaSolicitada = pegarEmpresa();
		String numeroNota = Console.recuperaTexto("Informe o número da nota: ");
		ArrayList<NotaFiscal> notas = empresaSolicitada.getNotasFiscais();

		for (NotaFiscal nota : notas) {
			if (numeroNota.equalsIgnoreCase(nota.getNumero())) {
				System.out.println(nota);
			}
		}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Emite uma nota fiscal (Add no Arraylist notasFiscais do objeto empresa)
	 */
	private static void emitirNotas() {
		try {
		Empresa empresaSolicitada = pegarEmpresa();
		empresaSolicitada.addNotaFiscal(criarNota());
		System.out.println("Nota Emitida...\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Cria uma Nota Fiscal
	 * 
	 * @return Nota Fiscal
	 */
	private static NotaFiscal criarNota() {

		String numero = Console.recuperaTexto("Informe o número da nota: ");
		String descricao = Console.recuperaTexto("Informe o motivo da nota: ");
		Double valor = Console.recuperaDecimal("Informe o valor da nota: ");
		Imposto imposto = null;
		boolean continua = true;

		do {
			int opcao = Console
					.recuperaInteiroPositivo("Escolha o Estado:\n1) Paraná\n2) Santa Catarina\n3) São Paulo.");
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
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static Empresa pegarEmpresa() throws Exception {
			String cnpj = Console.recuperaTexto("Informe o CNPJ da empresa: ");
		if(encontrarIndexEmpresa(cnpj) == -1) {
			throw new Exception("Index < 0 não existe...");
		} else {		
		return empresas.get(encontrarIndexEmpresa(cnpj));
		}
	}

	/**
	 * Localiza uma empresa no ArrayList empresas
	 * 
	 * @param cnpj parametro de busca
	 * @return um inteiro com index do ArrayList empresas
	 */
	private static int encontrarIndexEmpresa(String cnpj) {
		

		int i = -1;
		for (Empresa empresa : empresas) {
			i++;
			if (cnpj.equalsIgnoreCase(empresa.getCnpj())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Cria uma empresa
	 */
	private static void criarEmpresa() {

		String nome = Console.recuperaTexto("Digite o nome da empresa");
		String cnpj = Console.recuperaTexto("Digite o CNPJ da empresa");

		int index = encontrarIndexEmpresa(cnpj);

		if (index == -1) {
			Empresa empresa = new Empresa(nome, cnpj);
			empresas.add(empresa);
			System.out.println("Empresa adicionada!");
			System.out.println();
		}

	}
}
