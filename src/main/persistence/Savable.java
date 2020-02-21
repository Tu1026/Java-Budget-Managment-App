package persistence;

import java.io.PrintWriter;

// Reference from the tellerApp
// Represents data that can be saved to file
public interface Savable {
    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
