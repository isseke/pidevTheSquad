module PidevTheSquad {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.logging;
    requires java.sql;
    requires TrayTester;
    requires java.transaction.xa;
    requires javafx.media;
    requires javafx.swing;
    requires javafx.web;
    requires AnimateFX;
    requires java.mail;
    requires itextpdf;
  //  requires opencv;

    //requires kotlin.stdlib;
    requires javafx.swt;
    requires javafx.base;
    requires mysql.connector.java;
    exports pidevthesquad;
    opens pidevthesquad to javafx.graphics, javafx.controls, javafx.fxml;

    exports ConnectDB;
    opens ConnectDB to javafx.graphics;

    exports Gestionnaire;
    opens Gestionnaire to javafx.graphics, javafx.fxml;

    exports Interface;
    opens Interface to javafx.graphics, javafx.fxml;

    exports Modele;
    opens Modele to javafx.graphics, javafx.fxml;

    exports Services;
    opens Services to javafx.graphics, javafx.fxml;





}