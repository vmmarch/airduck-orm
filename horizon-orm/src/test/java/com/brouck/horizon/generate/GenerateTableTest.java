package com.brouck.horizon.generate;

import com.brouck.horizon.User;
import com.brouck.horizon.session.GetSQLSession;
import com.brouck.horizon.session.HorizonSession;
import org.junit.Test;

/**
 * @author brouck
 * Create time 2022/3/30
 */
public class GenerateTableTest {

    @Test
    public void generateTable() {
        var horizonSession = new HorizonSession(GetSQLSession.getSqlSession());
        horizonSession.addTableMetaData(User.class);
        horizonSession.executeGenerateTable();
    }

}
