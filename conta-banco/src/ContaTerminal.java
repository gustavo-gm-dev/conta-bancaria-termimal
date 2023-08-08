import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        
        String padrao = "[a-zA-Z áàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇÑ ]+";
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(contaTerminal.getNomeCliente());

        if(!matcher.matches()){
            System.out.println("Favor informe seu nome corretamente, sem acentos e caracteres especiais.");
            LOGGER.info("Erro ao validar nome: " + contaTerminal.getNomeCliente());
            return false;
        }
        
        //Validando se a agencia foi informada corretamente
        if(!contaTerminal.getAgencia().contains("-")){
            int tamanhoString = contaTerminal.getAgencia().length();
            String validandoAgencia = contaTerminal.getAgencia();
            contaTerminal.setAgencia(validandoAgencia.substring(0, tamanhoString - 1) + "-" + validandoAgencia.charAt(validandoAgencia.length() - 1));
        } 

        if(!contaTerminal.getAgencia().matches("[0-9]+-[0-9]{1}")){
            System.out.println("Agencia informada incorreta. Exemplo: 1234-0");
            LOGGER.info("Erro ao validar agencia");
            return false;
        }

        if(!(contaTerminal.getAgencia().length() >= 5 || contaTerminal.getAgencia().length() <= 6)){
            System.out.println("Agencia invalida. Certifique-se de validar se informou de 4 a 5 caracteres com o digito");
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
            System.out.println("Favor informar um quantia no sando. Lembre de usar ',' para o decimal Ex: 100.50");
            LOGGER.info("Erro na validação do saldo");
            return false;
        }

        double multiplicador = Math.pow(10, 2);
        double resultado = contaTerminal.getSaldo() * multiplicador;

        if(!(resultado % 1 == 0)){
            System.out.println("Favor informal uma quantia com apenas duas casas deciamis");
            return false;
        }

        return true;
    }


    public static void main(String[] args) {

        System.setProperty("file.encoding", "UTF-8");
        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        
        ContaTerminal contaTerminal = null;
        Scanner scanner = new Scanner(System.in);
        boolean cadastroFinalizado = false;

        //Cadastro de infomacoes do usuario
        while(!cadastroFinalizado){
            try {
                //Tela de Inicio
                System.out.println("_________________________________________________\n");
                System.out.println("    Programa: Crie sua conta bancaria");
                System.out.println("_________________________________________________\n");

                System.out.print("Primeiro Nome: ");
                String primeiroNome = scanner.nextLine();
                
                System.out.print("Sobrenome: ");
                String sobrenome = scanner.nextLine();
        
                System.out.print("Agencia (com ultimo digito): ");
                String agenciaUsuario = scanner.nextLine();
                
                System.out.print("Conta (sem ultimo digito): ");
                int contaUsuario = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("Valor que deseja depositar (Utilizar separador decimal ','): ");
                double saldoDepositado = scanner.nextDouble();
                scanner.nextLine();

                if(primeiroNome.isEmpty() || agenciaUsuario.isEmpty() || contaUsuario == 0 || saldoDepositado == 0){
                    System.out.println("Favor informar todos os campos para que possa prosseguir.");
                    continue;
                }
    
                contaTerminal = new ContaTerminal(contaUsuario,
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
                System.out.println("\n_________________________________________________\n");
                System.out.println("                     BEM VINDO");
                System.out.println("_________________________________________________\n");
        System.out.println("Olá " + contaTerminal.getNomeCliente() + ", obrigado por criar uma conta \n" +
            "em nosso banco, sua agência é " + contaTerminal.getAgencia() + ", conta " + contaTerminal.getNumero() + " e \n" +
            "seu saldo " + contaTerminal.getSaldo() + " já está disponível para saque.");

    }

}
