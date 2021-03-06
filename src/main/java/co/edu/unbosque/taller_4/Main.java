package co.edu.unbosque.taller_4;

import co.edu.unbosque.taller_4.dto.PetCase;
import co.edu.unbosque.taller_4.services.PetCaseService;
import co.edu.unbosque.taller_4.services.UserService;
import co.edu.unbosque.taller_4.services.VetsService;
import co.edu.unbosque.taller_4.services.VisitsService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/FourPaws";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "12345";

    public static Scanner leer;

    public static void main(String[] args){

        leer = new Scanner(System.in);

        // Objects for handling connection
        Connection conn = null;
        try {
            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);
            int option;
            do {
                System.out.println("Ciudadanos de 4 Patas \n" +
                        "1. Consultar la lista de usuarios registrados para un rol dado.\n" +
                        "2. Contar la lista de veterinarias registradas en la plataforma.\n" +
                        "3. Consultar las visitas que se han registrado para un ID de mascota dado.\n" +
                        "4. Registrar un nuevo caso de robo de una mascota dado su ID.\n" +
                        "5. Salir\n" +
                        "Ingrese la opción deseada:");
                option = Integer.parseInt(leer.nextLine());
                switch (option) {
                    case 1:
                        System.out.println("Ingrese el rol a consultar");
                        String rol = leer.nextLine();
                        // Opening database connection
                        System.out.println("Conectando a la base de datos...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        //Connect to usersService and use listUsers method
                        UserService usersService = new UserService(conn);
                        usersService.listUsers(rol.toLowerCase());
                        // Closing database connection
                        conn.close();
                        break;
                    case 2:
                        // Opening database connection
                        System.out.println("Conectando a la base de datos...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        //Connect to usersService and use listUsers method
                        VetsService vetsService = new VetsService(conn);
                        vetsService.countVets();
                        // Closing database connection
                        conn.close();
                        break;
                    case 3:
                        System.out.println("Ingrese el nuemero del pet Id a consultar:");
                        int pet_id = Integer.parseInt(leer.nextLine());
                        // Opening database connection
                        System.out.println("Conectando a la base de datos...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        //Connect to usersService and use listUsers method
                        VisitsService visitsService = new VisitsService(conn);
                        visitsService.listUsers(pet_id);
                        // Closing database connection
                        conn.close();
                        break;
                    case 4:
                        System.out.println("Ingrese el nùmero del pet Id para registrar el caso:");
                        int pet_id1 = Integer.parseInt(leer.nextLine());
                        System.out.println("Ingrese la descripciòn de caso:");
                        String description = leer.nextLine();
                        // Opening database connection
                        System.out.println("Conectando a la base de datos...");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        //Connect to usersService and use listUsers method
                        PetCaseService petCaseService = new PetCaseService(conn);
                        //Get number of cases in existance and add 1
                        int visit_id=petCaseService.countPetCases()+1;
                        //Create PetCase
                        PetCase pCase = new PetCase(visit_id, now().toString(), "robo", description, pet_id1);
                        petCaseService.createPetCase(pCase);
                        // Closing database connection
                        conn.close();
                        break;
                    case 5:
                        System.out.println("Gracias por usar el programa de ciudadanos de 4 patas");
                        break;
                    default:
                        System.out.println("Ingrese una opción correcta");
                }
            } while (option!=5);
        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch(ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } catch(NumberFormatException e) {
            System.out.println("Ingrese un número");
        } finally {
            // Cleaning-up environment
            try {
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
