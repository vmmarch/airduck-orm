package com.brouck.generate;

import com.brouck.User;
import com.brouck.session.GetSQLSession;
import com.brouck.session.BrouckSession;
import org.junit.Test;

/**
 * @author brouck
 * Create time 2022/3/30
 */
public class GenerateTableTest {

    @Test
    public void generateTable() {
        var brouckSession = new BrouckSession(GetSQLSession.getSqlSession());
        brouckSession.addTableMetaData(User.class);
        brouckSession.executeGenerateTable();
    }

}
