package com.example.nutri_well.db;

import java.io.IOException;

public interface DBUpdater {
    void updateDatabase(String filePath) throws IOException;
}
