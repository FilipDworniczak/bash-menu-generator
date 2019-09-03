package pl.fdworniczak.bashmenugenerator.model;

import lombok.Getter;

@Getter
public class Choice {
    public String entryKey;
    public String message;
    public String afterSelectionMessage;
    public String execute;
    public Options options;
}
