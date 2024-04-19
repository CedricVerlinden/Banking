module banking {
    requires javafx.fxml;
    requires com.google.gson;
    requires MaterialFX;
    requires transitive java.sql;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;

    opens com.cedricverlinden.banking to javafx.fxml;

    exports com.cedricverlinden.banking;
}