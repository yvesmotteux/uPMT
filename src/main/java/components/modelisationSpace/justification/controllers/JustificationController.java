package components.modelisationSpace.justification.controllers;

import application.configuration.Configuration;
import components.modelisationSpace.justification.justificationCell.JustificationCell;
import models.Descripteme;
import components.modelisationSpace.justification.appCommands.JustificationCommandFactory;
import models.Justification;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import utils.dragAndDrop.DragStore;
import utils.modelControllers.ListView.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JustificationController implements Initializable {

    //Controller logical elements
    private Justification justification;
    private JustificationCommandFactory cmdFactory;
    private ListView<Descripteme, JustificationCell> descriptemes;

    //Graphics elements
    @FXML private VBox childrenBox;


    public JustificationController(Justification j) {
        this.justification = j;
        this.cmdFactory = new JustificationCommandFactory(justification);
    }

    public static Node createJustificationArea(JustificationController controller) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(controller.getClass().getResource(
                    "/views/modelisationSpace/Justification/Justification.fxml"
            ));
            loader.setController(controller);
            loader.setResources(Configuration.langBundle);
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Adding descripteme VBox
        descriptemes = new ListView<>(
                justification.descriptemesProperty(),
                (descripteme -> new JustificationCell(descripteme, cmdFactory)),
                JustificationCell::create,
                childrenBox
        );
    }

    public boolean acceptDescripteme(Descripteme d) {
        return justification.indexOf(DragStore.getDraggable()) == -1;
    }

    public void addDescripteme(Descripteme d) {
        cmdFactory.addDescripteme(d).execute();
    }
}
