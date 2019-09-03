package pl.fdworniczak.bashmenugenerator;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import pl.fdworniczak.bashmenugenerator.model.Choice;
import pl.fdworniczak.bashmenugenerator.model.MenuMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;

public class BashMenuGenerator {
    private final String LINE_SEPARATOR = System.lineSeparator();
    private Gson gson = new Gson();
    private String menuName;
    private MenuMap menuMap;

    public BashMenuGenerator(final String menuMapPath) throws IOException {
        this.menuMap = loadMenuMapFromFile(menuMapPath);
    }

    public void generateBashMenu() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb
            .append("#!/bin/bash").append(LINE_SEPARATOR)
            .append(LINE_SEPARATOR)
            .append(generateMenuFunction(menuMap.navigation))
            .append("Choice_root");

        FileUtils.writeStringToFile(new File(menuName + ".sh"), sb.toString(), Charset.defaultCharset());
    }

     private String generateMenuFunction(final Choice choice) {
         StringBuilder sb = new StringBuilder();
         sb
                 .append("function Choice_").append(choice.entryKey).append("() {").append(LINE_SEPARATOR);
         if (StringUtils.isNotEmpty(choice.execute)) {
             sb.append(choice.execute).append(LINE_SEPARATOR);
         }
         if (!choice.options.choices.isEmpty()) {
             sb.append(generateMenuChoiceHandler(choice));
         } else {
             // dynamic
         }
         sb.append("}").append(LINE_SEPARATOR);
         if (!choice.options.choices.isEmpty()) {
             choice.options.choices.forEach(iteratedChoice -> sb.append(generateMenuFunction(iteratedChoice)));
         }
         return sb.toString();
     }

    private String generateMenuChoiceHandler(final Choice choice) {
        StringBuilder sb = new StringBuilder();
        sb
                .append("while :").append(LINE_SEPARATOR)
                .append("do").append(LINE_SEPARATOR)
                // Hints
                .append("echo \"Options\"").append(LINE_SEPARATOR);
        choice.options.choices.forEach(subChoice -> {
            sb.append("echo \"").append(subChoice.entryKey).append(" - ").append(subChoice.message).append("\"").append(LINE_SEPARATOR);
        });
        sb
                .append("echo \"").append(menuMap.settings.exitKey).append(" - Exit\"").append(LINE_SEPARATOR)
                // Choices
                .append("read -e -p \"").append(menuMap.navigation.afterSelectionMessage).append("\" bmg_choice_").append(choice.entryKey).append(LINE_SEPARATOR)
                .append("if [[ ! -z \"$bmg_choice_").append(choice.entryKey).append("\" ]]; then").append(LINE_SEPARATOR);
        AtomicBoolean firstIteration = new AtomicBoolean(true);
        choice.options.choices.forEach(choiceIteration -> {
            sb
                    .append(firstIteration.get() ? "" : "el")
                    .append("if [[ $bmg_choice_").append(choice.entryKey).append(" = \"").append(choiceIteration.entryKey).append("\" ]]; then").append(LINE_SEPARATOR)
                    .append("Choice_").append(choiceIteration.entryKey).append(LINE_SEPARATOR);
            firstIteration.set(false);
        });
        sb
                .append("elif [[ $bmg_choice = \"").append(menuMap.settings.exitKey).append("\" ]]; then").append(LINE_SEPARATOR)
                .append("exit").append(LINE_SEPARATOR)
                .append("else").append(LINE_SEPARATOR)
                .append("echo \"Please choose an option\"").append(LINE_SEPARATOR)
                .append("fi").append(LINE_SEPARATOR)
                .append("fi").append(LINE_SEPARATOR)
                .append("done").append(LINE_SEPARATOR)
                .append("}").append(LINE_SEPARATOR);
        return sb.toString();
    }

    private MenuMap loadMenuMapFromFile(final String menuMapPath) throws IOException {
        File menuMapFile = new File(menuMapPath);
        menuName = FilenameUtils.removeExtension(menuMapFile.getName());
        return gson.fromJson(FileUtils.readFileToString(menuMapFile, Charset.defaultCharset()), MenuMap.class);
    }
}
