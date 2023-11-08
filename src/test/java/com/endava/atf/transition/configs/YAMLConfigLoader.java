package com.endava.atf.transition.configs;

import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class YAMLConfigLoader {
    public Map<String, Object> loadYAML(String filePath) throws IOException {
        FileInputStream input = new FileInputStream(filePath);
        Yaml yaml = new Yaml();
        return yaml.load(input);
    }
}














