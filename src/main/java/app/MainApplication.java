package app;

import com.example.finman.model.Initializer;
import com.example.finman.views.Main;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class MainApplication {
    public static void main(String[] args) throws SQLException, IOException, URISyntaxException {
        File file = new File("finance.db");
        if (!file.exists()) {
            Initializer initializer = new Initializer();
            initializer.initializeTables();
        }

        Main.main(args);
    }
}
