package components.schemaTree.Cell.modelCommands;

import components.schemaTree.Cell.SchemaTreePluggable;
import utils.reactiveTree.Commands.RenameReactiveTreePluggableCommand;

public class RenameSchemaTreePluggable extends RenameReactiveTreePluggableCommand {

    SchemaTreePluggable element;
    private boolean mustBeRenamed;

    public RenameSchemaTreePluggable(SchemaTreePluggable element, String newName) {
        super(element, newName);
        this.element = element;
        this.mustBeRenamed = element.mustBeRenamed();
    }

    @Override
    public Void execute() {
        super.execute();
        if(mustBeRenamed)
            element.setMustBeRenamed(false);
        return null;
    }

    @Override
    public Void undo() {
        if(mustBeRenamed)
            element.setMustBeRenamed(true);
        super.undo();
        return null;
    }
}
