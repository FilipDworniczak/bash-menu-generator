package pl.fdworniczak.bashmenugenerator;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import pl.fdworniczak.bashmenugenerator.model.MenuMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class BashMenuGenerator {
    private Gson gson = new Gson();
    private String menuName;
    private MenuMap menuMap;

    public BashMenuGenerator(final String menuMapPath) throws IOException {
        this.menuMap = loadMenuMapFromFile(menuMapPath);
    }

    public void generateBashMenu() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("#!/bin/bash");
        FileUtils.writeStringToFile(new File(menuName + ".sh"), sb.toString(), Charset.defaultCharset());
    }

    private MenuMap loadMenuMapFromFile(final String menuMapPath) throws IOException {
        File menuMapFile = new File(menuMapPath);
        menuName = FilenameUtils.removeExtension(menuMapFile.getName());
        return gson.fromJson(FileUtils.readFileToString(menuMapFile, Charset.defaultCharset()), MenuMap.class);
    }
}
