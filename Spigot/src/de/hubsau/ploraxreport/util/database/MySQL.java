package de.hubsau.ploraxreport.util.database;
/*Class erstellt von Hubsau


20:21 2019 13.04.2019
Wochentag : Samstag


*/


import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.util.report.Report;
import de.hubsau.ploraxreport.util.report.SecondReason;
import net.plorax.api.PloraxAPI;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MySQL {

    private Main main;
    private Connection connection;

    public MySQL(Main main) {
        this.main = main;
        try {
            PloraxAPI.getConnection().createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Report(UUID VARCHAR(50), Reports LONGTEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    public void createReport(UUID uuid, Report report) {

        try {


            PreparedStatement preparedStatement = PloraxAPI.getConnection().prepareStatement("UPDATE Report SET REPORTS=? WHERE UUID=?");

            String scdReasn = "";
            for (SecondReason secondReason : report.getSecondReasons()) {
                scdReasn+=secondReason+",";
            }
            String reportString = report.getReason().name()+ "§8(§7"+scdReasn+ "§8);"+ report.getUUIDReporter().toString() + ";"+ report.getDateOfCreate();
            String reports = getReports(uuid);



            reports+=reportString+"!";



           preparedStatement.setString(1, reports);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException var) {
            var.printStackTrace();
        }



    }

    public String getReports(UUID uuid)
    {
        String result = "";

        try {
            PreparedStatement preparedStatement = PloraxAPI.getConnection().prepareStatement("SELECT * FROM Report WHERE UUID='" + uuid + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getString("Reports");


                return result;
            }

            this.insertPlayer(uuid);
            return  "";
        } catch (SQLException var4) {
            var4.printStackTrace();
        }


        return null;
    }

    private void insertPlayer(UUID uuid) {
        try {
            PreparedStatement preparedStatement = PloraxAPI.getConnection().prepareStatement("INSERT INTO Report(UUID, Reports) VALUES (?, ?)");
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, "");
            preparedStatement.executeUpdate();
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }







}
