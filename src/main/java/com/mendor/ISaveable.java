package com.mendor;

import java.sql.SQLException;

public interface ISaveable {
    void save() throws SQLException;
}
