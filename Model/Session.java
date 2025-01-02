/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dzkam
 */
public class Session {
    private static int userId;
    private static String role;

    // Setter dan Getter untuk userId
    public static int getUserId() {
        return userId;
    }

    public static void setUserId(int id) {
        userId = id;
    }

    // Setter dan Getter untuk role
    public static String getRole() {
        return role;
    }

    public static void setRole(String userRole) {
        role = userRole;
    }
}
