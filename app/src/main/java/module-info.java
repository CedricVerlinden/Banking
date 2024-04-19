module banking {
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires MaterialFX;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;

    opens com.cedricverlinden.banking to javafx.fxml;

    exports com.cedricverlinden.banking;
}