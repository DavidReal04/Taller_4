package co.edu.unbosque.taller_4.services;

import co.edu.unbosque.taller_4.dto.Visit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisitsService {

    // Objects for handling connection
    private Connection conn;

    public VisitsService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers(int param_pet_id) {

        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        List<Visit> visits = new ArrayList<Visit>();

        try {

            // Executing a SQL query
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Visit WHERE pet_id='"+param_pet_id+"'";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while(rs.next()) {
                // Extracting row values by column name
                int visit_id = rs.getInt("visit_id");;
                String created_at = rs.getString("created_at");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String vet_id = rs.getString("vet_id");
                int pet_id = rs.getInt("pet_id");

                // Creating a new UserApp class instance and adding it to the array list
                visits.add(new Visit(visit_id, created_at, type, description, vet_id, pet_id));
            }

            // Printing results
            System.out.println("Visit Id | Creation | Type | Description | Vet Id | Pet Id");
            for (Visit visit : visits) {
                System.out.print(visit.getVisit_id() + " | ");
                System.out.print(visit.getCreated_at() + " | ");
                System.out.print(visit.getType() + " | ");
                System.out.print(visit.getDescription() + " | ");
                System.out.print(visit.getVet_id() + " | ");
                System.out.println(visit.getPet_id());
            }

            // Printing total rows
            System.out.println("Total de visitas encontradas: " + visits.size() + "\n");

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
    }

}
