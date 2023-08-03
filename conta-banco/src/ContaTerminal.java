import java.util.Scanner;
import java.util.logging.Logger;

public class ContaTerminal {

    private static final Logger LOGGER = Logger.getLogger(ContaTerminal.class.getName());

    private int numero;
    private String agencia;
    private String nomeCliente;
    private double saldo;

    public ContaTerminal(int numero, String agencia, String nomeCliente, double saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }
    

    public int getNumero() {
        return numero;
    }


    public void setNumero(int numero) {
        this.numero = numero;
    }


    public String getAgencia() {
        return agencia;
    }


    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }


    public String getNomeCliente() {
        return nomeCliente;
    }


    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }


    public double getSaldo() {
        return saldo;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public static boolean CadastrarDados (ContaTerminal contaTerminal) {

        //Validando se o usuario informou o nome
        if(!contaTerminal.getNomeCliente().matches("[a-zA-Z]+")){
            System.out.println("Favor informe seu nome corretamente.");
            LOGGER.info("Erro ao validar nome: " + contaTerminal.getNomeCliente());
            return false;
        }
        
        //Validando se a agencia foi informada corretamente
        if(!contaTerminal.getAgencia().contains("-")){
            int tamanhoString = contaTerminal.getAgencia().length();
            String validandoAgencia = contaTerminal.getAgencia();
            contaTerminal.setAgencia(validandoAgencia.substring(0, tamanhoString - 1) + validandoAgencia.charAt(validandoAgencia.length() - 1));
            LOGGER.info("Erro ao adcionar um hifen na agencia");
        } 
        if(!contaTerminal.getAgencia().matches("[0-9]+-[0-9]{1}")){
            System.out.println("Agencia informada incorreta. Exemplo: 1234-0");
            LOGGER.info("Erro ao validar agencia");
            return false;
        }

        //Validando se a conta foi informada corretamente
        if(!(contaTerminal.getNumero() > 999 && contaTerminal.getNumero() < 1000000)){
            System.out.println("Numero de conta incorreta, favor informar um conta de 3 a 6 digitos");
            LOGGER.info("Erro ao validar conta");
            return false;
        }

        //Validando se o saldo foi informado corretamente
        if(!(contaTerminal.getSaldo() > 0.01)){
            System.out.println("Favor informar um quantia no sando. Lembre de usar '.' para o decimal Ex: 100.50");
            LOGGER.info("Erro na validação do saldo");
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        
        //Tela de Inicio
        System.out.println("_________________________________________________\n");
        System.out.println("    Programa: Crie sua conta bancaria");
        System.out.println("_________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        boolean cadastroFinalizado = false;

        //Cadastro de infomacoes do usuario
        while(!cadastroFinalizado){
            try {
                System.out.print("Primeiro Nome: ");
                String primeiroNome = scanner.nextLine();
                

                System.out.print("Sobrenome: ");
                String sobrenome = scanner.nextLine();
        
                System.out.print("Agencia (com ultimo digito): ");
                String agenciaUsuario = scanner.nextLine();
                
                System.out.print("Conta (sem ultimo digito): ");
                int contaUsuario = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("Valor que deseja depositar (Utilizar separador decimal '.'): ");
                double saldoDepositado = scanner.nextDouble();
                scanner.nextLine();
    
                ContaTerminal contaTerminal = new ContaTerminal(contaUsuario,
                                                                agenciaUsuario,
                                                                primeiroNome.concat(" " + sobrenome),
                                                                saldoDepositado);
                
                cadastroFinalizado = CadastrarDados(contaTerminal);
            } catch (Exception e) {
                System.out.println("Erro: Entrada invalida. Certifique-se de digitar corretamente");
                scanner.nextLine();
            }
        }
        scanner.close();

        //Tela de boas vindas
        System.out.println("Olá [Nome Cliente], obrigado por criar uma conta em " +
            "nosso banco, sua agência é [Agencia], conta [Numero] e" +
            "seu saldo [Saldo] já está disponível para saque");

    }

}
