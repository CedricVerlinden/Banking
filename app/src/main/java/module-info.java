module com.cedricverlinden.banking {
    requires javafx.fxml;
    requires com.google.gson;
    requires MaterialFX;
    requires transitive java.sql;
    requires transitive javafx.controls;
    requires transitive javafx.graphics;

    opens com.cedricverlinden.banking to javafx.fxml;
    opens com.cedricverlinden.banking.models to javafx.base;
    opens com.cedricverlinden.banking.controllers to javafx.fxml;

    exports com.cedricverlinden.banking;
}