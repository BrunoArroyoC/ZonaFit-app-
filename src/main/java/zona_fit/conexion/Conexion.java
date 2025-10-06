package zona_fit.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        Connection conexion = null;
        String baseDatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "root";
        String password = "rootBruno2004";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,password);
        }catch (Exception e){
            System.out.println("Error a conectarnos a la base de datos" + e.getMessage());
        }

        return conexion;
    }


    public static void main(String[] args) {
        Connection conexion = Conexion.getConexion();
        if(conexion != null){
            System.out.println("Conexion exitosa: " + conexion);
        }else{
            System.out.println("Error");
        }

    }
}
