package com.codebind.pages;
/*import com.codebind.ContentManager;
import com.codebind.PageInterface;
import com.codebind.databaseConnection.NetworkOverview;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class dahjkd implements PageInterface {

    JPanel panel = new JPanel();
    private ContentManager contentManager;

    public dahjkd() {
       //panel.add(new )

    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }

    @Override
    public void afterSetup() {
        contentManager.setTitle("ajdghqjagd");
        PreparedStatement preparedStatement = contentManager.getDataBaseConnection().getPreparedStatement("Select * from gebruiker");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Timestamp timestamp = resultSet.getTimestamp("gebruikercode");
        }
        try {
            contentManager.setPage(new NetworksOverview(NetworkOverview.getAllNetworkOverview(contentManager.getDataBaseConnection())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
*/