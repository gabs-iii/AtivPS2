package br.mack.ps2;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.logging.ConsoleHandler;

public class App{
    private static Object saldos;

    public static void main(String[]args) {
        Connection conn = null;
        Scanner in = new Scanner(System.in);\

        int op;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://12.0.0.1:3306/Mackenzie";
            String psw = "root";

            conn = DriverManager.getConnection(url, user, psw);

            String createSQL = "INSERT INTO contas VALUES(?,?)";
            String readSQL = "SELECT * FROM contas";
            String deleteSQL = "DELETE FROM contas WHERE nro_conta = ?";

            long nro_Conta;
            BigDecimal saldo;
            do {
                System.out.println("====MENU====");
                System.out.println("\n1.Insert\n2.Deletar\n3.Ver contas\n4.Sair");
                System.out.println("Opção:");
                op = in.nextInt();
                switch (op) {
                    case 1:
                        int parameterIndex;
                        try {
                            PreparedStatement stm = conn.prepareStatement(createSQL);
                            System.out.println("Número da conta:");
                            nro_Conta = in.nextLong();
                            System.out.println("Saldo:");
                            saldo = in.nextBigDecimal();
                            stm.execute();
                            stm.setLong(parameterIndex:1, nro_Conta);
                            stm.setBigDecimal(parameterIndex:2, saldos);

                            System.out.println("Dados adicionados corretamente!");
                        } catch (final SQLException err) {
                            System.out.println("Falha na conexão!");
                        } finally {
                            try {
                                conn.close();
                            } catch (final Exception err) {
                                err.printStackTrace();
                            }
                        }
                        break;
                    case 2:
                        try {
                            PreparedStatement stm = conn.prepareStatement(deleteSQL);
                            System.out.println("Número da Conta:");
                            nro_Conta = in.nextLong();
                            stm.setLong(parameterIndex:1, nro_Conta);
                            stm.execute();

                            System.out.println("Conta excluida!");
                        } catch (final SQLException err) {
                            err.printStackTrace();
                        }
                }
                break;
                case 3:
                    PreparedStatement stm = conn.prepareStatement(readSQL);
                    ResultSet rs = stm.executeQuery();
                    int cont = 0;
                    while (rs.next()) {
                        cont++;
                        System.out.println(cont + "Registro");
                        int columnLabel;
                        nro_Conta = rs.getLong(columnLabel:"nro_conta");
                        saldo = rs.getBigDecimal(columnLabel:"saldo");

                        System.out.println("Conta:" + nro_Conta + "- Saldo:" + saldo);

                    }
                    rs.close();
                    break;
                default:
                    break;
            }
        } while (op ! = 4);
    }catch(final ClassNotFoundException err){
        System.out.println("Falha");
        err.printStackTrace();
    }catch(final SQLException err){
        System.out.println("Falha");
        err.printStackTrace();
    }   finally
    private final ConsoleHandler conn;

    {
        try{
            conn.close();
        } catch (final SQLException err) {
            err.printStackTrace();
        }
    }

}
}