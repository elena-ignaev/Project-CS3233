module com.example.projectp1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    exports com.example.projectp1.Model;
    opens com.example.projectp1.Model to javafx.fxml;

    exports com.example.projectp1.Controller;
    opens com.example.projectp1.Controller to javafx.fxml;

    exports com.example.projectp1;
    opens com.example.projectp1 to javafx.fxml;


}