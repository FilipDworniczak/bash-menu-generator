package pl.fdworniczak.bashmenugenerator;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Main {
    public static void main(final String args[]) {
        try {
            if (args.length == 0) {
                log.info("Please provide path to file of menu map");
                return;
            }
            BashMenuGenerator bashMenuGenerator = new BashMenuGenerator(args[0]);
            bashMenuGenerator.generateBashMenu();
            log.info("Finished");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
