module banking {
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires transitive javafx.graphics;

    opens com.cedricverlinden.banking to javafx.fxml;

    exports com.cedricverlinden.banking;
}