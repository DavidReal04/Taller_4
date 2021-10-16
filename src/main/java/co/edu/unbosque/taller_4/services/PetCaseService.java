package co.edu.unbosque.taller_4.services;

import co.edu.unbosque.taller_4.dto.PetCase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PetCaseService {

    // Objects for handling connection
    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void createPetCase(PetCase petCase) {

        // Objects for handling SQL statement
        Statement stmt = null;

        try {
            // Executing a SQL query
            stmt = conn.createStatement();
            String sql = "INSERT INTO PetCase (case_id, created_at, type, description, pet_id) " +
                    "VALUES ("+petCase.getCase_id()+", '"+petCase.getCreated_at()+"', '" +
                    petCase.getType()+"', '"+petCase.getDescription()+"', '"+ petCase.getPet_id()+"')";
            stmt.executeUpdate(sql);

            // Printing results
            System.out.println("Registro de caso agregado correctamente\n");

            // Closing resources
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public int countPetCases() {

        int count=0;

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) AS count FROM PetCase";
            ResultSet rs = stmt.executeQuery(sql);

            // Pointing to fist row
            rs.next();

            // Storing results
            count = rs.getInt("count");

            // Closing resources
            rs.close();
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return count;
    }
}
