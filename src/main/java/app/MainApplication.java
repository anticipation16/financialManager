package app;

import com.example.finman.model.Initializer;
import com.example.finman.views.Main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class MainApplication {
    public static void main(String[] args) throws SQLException, IOException, URISyntaxException {
        Initializer.main(args);
        Main.main(args);
    }
}
