package org.example.StreamIO;

import lombok.Data;
import java.util.Properties;

@Data
public class Config {
    private Properties property;
    private Reader reader;
    private String fileName = "C:\\Users\\IResetnicova\\IdeaProjects\\VideoCourse\\src\\MentorTime\\org.example.StreamIO\\employee.properties";

    public Config() {
        reader = new Reader();
        property = reader.read(fileName);
    }





}
