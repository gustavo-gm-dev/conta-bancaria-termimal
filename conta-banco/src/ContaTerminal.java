import java.util.Scanner;

public class ContaTerminal {

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

        

        return false;
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
            System.out.print("Primeiro Nome: ");
            String primeiroNome = scanner.next();
            System.out.print("Sobrenome: ");
            String sobrenome = scanner.next();
    
            System.out.print("Agencia (com ultimo digito): ");
            String agenciaUsuario = scanner.next();
            System.out.print("Conta (sem ultimo digito): ");
            int contaUsuario = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Valor que deseja depositar: ");
            double saldoDepositado = scanner.nextDouble();

            ContaTerminal contaTerminal = new ContaTerminal(contaUsuario,
                                                            agenciaUsuario,
                                                            primeiroNome.concat(" " + sobrenome),
                                                            saldoDepositado);
            
            cadastroFinalizado = CadastrarDados(contaTerminal);

        }


        //Tela de boas vindas
        System.out.println("Olá [Nome Cliente], obrigado por criar uma conta em " +
            "nosso banco, sua agência é [Agencia], conta [Numero] e" +
            "seu saldo [Saldo] já está disponível para saque");

    }

}
