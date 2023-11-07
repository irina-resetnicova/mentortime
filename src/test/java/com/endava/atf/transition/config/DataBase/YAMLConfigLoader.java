package com.endava.atf.transition.config.DataBase;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;


// reading properties from application.yaml and HOOKS uses this implementation and Hooks after writes result in TextContext
public class YAMLConfigLoader {

    public Map<String, Object> loadYAML(String filePath) throws IOException {
        FileInputStream input = new FileInputStream(filePath);
        Yaml yaml = new Yaml();
        return yaml.load(input);
    }
}
