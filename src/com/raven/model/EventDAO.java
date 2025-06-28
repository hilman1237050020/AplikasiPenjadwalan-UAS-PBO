package com.raven.model;

import com.raven.ui.*;
import com.raven.connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
     public static boolean insertEvent(Event e) {
        String sql = "INSERT INTO event (name, date, time, description, priority) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, e.getName());
            stmt.setString(2, e.getDate());
            stmt.setString(3, e.getTime());
            stmt.setString(4, e.getDescription());
            stmt.setString(5, e.getPriority());

            return stmt.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM event ORDER BY date, time";
        
        try (Connection conn = DBConnection.getConnection(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Event e = new Event(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("date"),
                    rs.getString("time"),
                    rs.getString("description"),
                    rs.getString("priority")
                );
                events.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return events;
    }

    public static boolean updateEvent(Event e) {
        String sql = "UPDATE event SET name=?, date=?, time=?, description=?, priority=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, e.getName());
            stmt.setString(2, e.getDate());
            stmt.setString(3, e.getTime());
            stmt.setString(4, e.getDescription());
            stmt.setString(5, e.getPriority());
            stmt.setInt(6, e.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteEvent(int id) {
        String sql = "DELETE FROM event WHERE id=?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
