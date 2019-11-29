package command.command;

import command.receiver.TextFile;

public class SaveTextFileOperation implements command.command.TextFileOperation {

    private final TextFile textFile;

    public SaveTextFileOperation(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String execute() {
        return textFile.save();
    }
}
