module com.odaviml {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.odaviml to javafx.fxml;
    exports com.odaviml;
}
