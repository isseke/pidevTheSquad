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
    //requires kotlin.stdlib;

    exports pidevthesquad;
   opens pidevthesquad to javafx.graphics;

    exports ConnectDB;
    opens ConnectDB to javafx.graphics;

    exports Gestionnaire;
    opens Gestionnaire to javafx.graphics;

    exports Interface;
    opens Interface to javafx.graphics;

    exports Modele;
    opens Modele to javafx.graphics;

    exports Services;
    opens Services to javafx.graphics;





}