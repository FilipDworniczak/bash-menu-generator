package pl.fdworniczak.bashmenugenerator.model;

import lombok.Getter;

@Getter
public class Choice {
    String entryKey;
    String message;
    String afterSelectionMessage;
    String execute;
    Options options;
}
