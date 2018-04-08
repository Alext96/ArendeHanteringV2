package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MenyKontroller extends Stage {

    Stage primaryStage;
    Scene primaryScene;
    RegistreraArende RegistreraArende;
    SokArende SokArende;
    GenereraRapport GenereraRapport;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField txt_anvandare;
    @FXML
    private TextField txt_losenord;
    @FXML
    public Button logga_in;
    @FXML
    private TextField txt_firstname;
    @FXML
    private TextField txt_lastname;
    @FXML
    private TextField txt_arbetsuppgifter;
    @FXML
    private TextField txt_budkost;
    @FXML
    private TextField txt_kommentarer;
    @FXML
    private TextField txt_status;
    @FXML
    private TextField txt_kompetenser;
    @FXML
    private TextField txt_budtid;
    @FXML
    private TextField txt_antalpersoner;
    @FXML
    private TextField txt_aNr;
    @FXML
    private TextField txt_sokArende;
    @FXML
    private TextField txt_aNummer;
    @FXML
    private TextField txt_fornamn;
    @FXML
    private TextField txt_efternamn;
    @FXML
    private TextField txt_statusen;
    @FXML
    private TextField txt_kommentar;
    @FXML
    private TextField txt_kompetens;
    @FXML
    private TextField txt_datum;
    @FXML
    private TextField txt_datumm;
    @FXML
    private TextField txt_budgtid;
    @FXML
    private TextField txt_antalpers;
    @FXML
    private TextField txt_arbetsuppgifter2;
    @FXML
    private TextField txt_forbtTid;
    @FXML
    private TextField txt_tidPerPerson;
    @FXML
    private Button swagyolo;

    //@Override
    public void meny() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Menyn.fxml"));

        primaryStage = new Stage();
        primaryScene = new Scene(root, 428, 358);

        primaryStage.setScene(primaryScene);
        primaryStage.show();


        //Parent root = FXMLLoader.load(getClass().getResource("Menyn.fxml"));
        //primaryStage.setTitle("logga in");
        //primaryStage.setScene(new Scene(root, 495, 435));
        //primaryStage.show();
    }

    @FXML
    private void menyn_registrera_arende(ActionEvent event) {

        try {
            RegistreraArende = new RegistreraArende();
            RegistreraArende.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menyn_sok_arende(ActionEvent event) {

        try {
            SokArende = new SokArende();
            SokArende.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void menyn_generera_rapport(ActionEvent event) {

        try {
            GenereraRapport = new GenereraRapport();
            GenereraRapport.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void generera_pdf_rapport(ActionEvent event) {

        String value = txt_aNr.getText();
        String value1 = txt_firstname.getText();
        String value2 = txt_lastname.getText();
        String value3 = txt_datumm.getText();

        JFileChooser dialog = new JFileChooser();
        dialog.setSelectedFile(new File("Ärende Nummer-" + value + "-" + value3 + ".pdf"));
        int dialogResultat = dialog.showSaveDialog(null);
        if (dialogResultat == JFileChooser.APPROVE_OPTION) {

            String filePath = dialog.getSelectedFile().getPath();

        }
    }

    @FXML
    private void Reg_support_button(ActionEvent event) {

        int x = JOptionPane.showConfirmDialog(null, "Är du säker på att du vill registrera ärendet?", "Registrera Ärende", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
            try {
                String sql = "INSERT INTO Support_info "
                        + "(first_name,last_name,Status,Budgeterad_Kostnad,"
                        + "Kommentarer,Kompetenser,Datum,"
                        + "Budgeterad_tid,Antal_personer,Arbetsuppgifter) values (?,?,?,?,?,?,?,?,?,?) ";
                conn = dbs.java_db();

                pst = conn.prepareStatement(sql);
                pst.setString(1, txt_aNr.getText());
                pst.setString(2, txt_firstname.getText());
                pst.setString(3, txt_lastname.getText());
                pst.setString(4, txt_arbetsuppgifter.getText());
                pst.setString(5, txt_budkost.getText());
                pst.setString(6, txt_kommentarer.getText());
                pst.setString(7, txt_status.getText());
                pst.setString(8, txt_kompetenser.getText());
                pst.setString(9, txt_datum.getText());
                pst.setString(10, txt_budtid.getText());
                pst.setString(11, txt_antalpersoner.getText());
                pst.execute();
                JOptionPane.showMessageDialog(null, "Ärende Sparat");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        }
    }

    @FXML
    private void reg_rensa_button(ActionEvent event) {
        txt_aNr.setText("");
        txt_firstname.setText("");
        txt_lastname.setText("");
        txt_arbetsuppgifter.setText("");
        txt_budkost.setText("");
        txt_kommentarer.setText("");
        txt_status.setText("");
        txt_kompetenser.setText("");
        txt_datum.setText("");
        txt_budtid.setText("");
        txt_antalpersoner.setText("");
    }



    @FXML
    private void sok_Arende_Bt(ActionEvent event) {
        try {
            String sql = "SELECT * FROM Support_info where support_id = ?";

            conn = dbs.java_db();
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_sokArende.getText());
            rs = pst.executeQuery();

            String add1 = rs.getString("support_id");
            txt_aNummer.setText(add1);

            String add2 = rs.getString("first_name");
            txt_fornamn.setText(add2);

            String add3 = rs.getString("last_name");
            txt_efternamn.setText(add3);

            String add4 = rs.getString("Status");
            txt_statusen.setText(add4);

            String add5 = rs.getString("Kommentarer");
            txt_kommentar.setText(add5);

            String add6 = rs.getString("Kompetenser");
            txt_kompetens.setText(add6);

            String add7 = rs.getString("Datum");
            txt_datumm.setText(add7);

            String add8 = rs.getString("Budgeterad_tid");
            txt_budgtid.setText(add8);

            String add9 = rs.getString("Antal_personer");
            txt_antalpers.setText(add9);

            String add10 = rs.getString("Arbetsuppgifter");
            txt_arbetsuppgifter2.setText(add10);

            String add11 = rs.getString("forbrukad_tid");
            txt_forbtTid.setText(add11);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @FXML
    private void sok_uppdatera_button(ActionEvent event) { // FUNGERAR 100%%%%%%%%% YES
        int x = JOptionPane.showConfirmDialog(null, "Är du säker på att du vill uppdatera?", "Uppdatera Ärende", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
            try {

                String val = txt_aNummer.getText();
                String val2 = txt_fornamn.getText();
                String val3 = txt_efternamn.getText();
                String val4 = txt_datumm.getText();
                String val5 = txt_arbetsuppgifter2.getText();
                String val6 = txt_kompetens.getText();
                String val7 = txt_forbtTid.getText();
                String val8 = txt_statusen.getText();
                String val9 = txt_tidPerPerson.getText();
                String val10 = txt_kommentar.getText();
                String val11 = txt_antalpers.getText();
                String val12 = txt_budgtid.getText();
                String val13 = txt_budkost.getText();

                String sql = "UPDATE Support_info SET support_id = '" + val + "', first_name = '" + val2 + "', last_name = '" + val3 + "',"
                        + "status = '" + val8 + "', Budgeterad_Kostnad = '" + val13 + "', Kommentarer = '" + val10 + "', Kompetenser = '" + val6 + "',"
                        + "Datum = '" + val4 + "', Budgeterad_tid = '" + val12 + "', Antal_personer = '" + val11 + "', Arbetsuppgifter = '" + val5 + "',"
                        + "forbrukad_tid = '" + val7 + "', tid_per_pers = '" + val9 + "' WHERE support_id = '" + val + "'";

                conn = dbs.java_db();
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Ärende Uppdaterades");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Något är fel...");
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }
        }
    }

    /*public static void main(String[] args) {
        launch(args);
    }*/
}
