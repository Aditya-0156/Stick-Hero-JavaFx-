module com.example.sh {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;

    opens com.example.sh to javafx.fxml;
    exports com.example.sh;
}