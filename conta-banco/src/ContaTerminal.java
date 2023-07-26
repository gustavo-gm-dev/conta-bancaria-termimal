import java.util.Scanner;

public class ContaTerminal {

    private String agenciaUsuario;
    private String agenciaDigito;
    private int contaUsuario;
    private String nomeUsuario;
    private double saldoConta;

    // Construtor
    public ContaTerminal (String agenciaUsuario, String agenciaDigito, int contaUsuario) {
        this.agenciaUsuario = agenciaUsuario;
        this.agenciaDigito = agenciaDigito;
        this.contaUsuario = contaUsuario;

    }

    public String getAgenciaUsuario() {
        return agenciaUsuario;
    }



    public String getAgenciaDigito() {
        return agenciaDigito;
    }



    public int getContaUsuario() {
        return contaUsuario;
    }

    // Armanazenamento de usaurio teste
    public enum Login{
        CONTA_TESTE("067","8",1021);

        private String agenciaUsuario;
        private String agenciaDigito;
        private int contaUsuario;

        private Login(String agenciaUsuario, String agenciaDigito, int contaUsuario) {
            this.agenciaUsuario = agenciaUsuario;
            this.agenciaDigito = agenciaDigito;
            this.contaUsuario = contaUsuario;
        }

        public String getAgenciaUsuario() {
            return agenciaUsuario;
        }

        public String getAgenciaDigito() {
            return agenciaDigito;
        }

        public int getContaUsuario() {
            return contaUsuario;
        }
    }

    // Metodo Login Terminal
    public boolean LoginTerminal(ContaTerminal minhaConta) {

        Login login = Login.CONTA_TESTE;
        boolean autenticador = false;

        if(login.getAgenciaUsuario().equals(minhaConta.getAgenciaUsuario()) && 
            login.getAgenciaDigito().equals(minhaConta.getAgenciaDigito()) &&
            login.getContaUsuario() == minhaConta.getContaUsuario()) {
                autenticador =  true;
            }
        
        return autenticador;
    }



    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        ContaTerminal minhaConta = null;
        boolean autenticador = false;
        
        //Lopin validador de login
        while (!autenticador) {
            System.out.println("############################################");
            System.out.println("#               TELA DE LOGIN              #");
            System.out.println("############################################");

            System.out.println("#  Insira os dados da sua Agência e Conta  #");

            System.out.print("  Agencia (sem o digito): ");
                String numeroAgencia = scanner.next();
            System.out.print("  Digito: ");
                String digitoAgencia = scanner.next();
            
            System.out.print("  Conta: ");
                int numeroConta = scanner.nextInt();
                scanner.nextLine();

            minhaConta = new ContaTerminal(numeroAgencia, digitoAgencia, numeroConta);
            autenticador = minhaConta.LoginTerminal(minhaConta);

            if(autenticador) {
                System.out.println("\n#      Login bem-sucedido. Bem-vindo!      #\n\n");
            } else {
                System.out.println("\n#    Error: agencia ou conta incorretas    #\n\n");
            }
        }


        scanner.close();
    }
}
