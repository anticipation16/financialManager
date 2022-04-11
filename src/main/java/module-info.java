module com.example.finman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    exports com.example.finman.views to javafx.graphics;
    exports com.example.finman.controllers to javafx.fxml;
    opens com.example.finman.controllers to javafx.fxml;
}