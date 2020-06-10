package gui;

import cli.Logger;
import cli.ReferenceUpdater;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class mainGUIController {
    private String testListFilePath;
    private String projectFolderPath;
    private String referenceFolderPath;
    private ReferenceUpdater referenceUpdater;
    PrintStream ps;

    @FXML
    private ComboBox<String> testListPathComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> projectFolderPathComboBox = new ComboBox<>();
    @FXML
    private ComboBox<String> referenceFolderPathComboBox = new ComboBox<>();
    @FXML
    private CheckBox updateRelatedObjAutoCheckBox = new CheckBox();
    @FXML
    private VBox RelatedObjectsVbox = new VBox();
    @FXML
    private VBox RelatedObjectsHiddenVBox = new VBox();
    @FXML
    private TextArea loggerTextArea = new TextArea();
    @FXML
    private Button updateButton = new Button();
    @FXML
    private Button checkRelatedObjButton = new Button();

    @FXML
    private void initialize() {
        this.ps = new PrintStream(new Console(loggerTextArea));

        EventHandler handler = (EventHandler<Event>) event -> {
            if (testListPathComboBox.getValue() != null &&
                    projectFolderPathComboBox.getValue() != null &&
                    referenceFolderPathComboBox.getValue() != null) {
                updateButton.setDisable(false);
                checkRelatedObjButton.setDisable(false);
            }
        };

        testListPathComboBox.addEventHandler(EventType.ROOT, handler);
        projectFolderPathComboBox.addEventHandler(EventType.ROOT, handler);
        referenceFolderPathComboBox.addEventHandler(EventType.ROOT, handler);
    }

    public void onCancelButtonClick() {
        System.exit(0);
    }


    public void onTestListBrowse() {
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Test-list file", "*.*");
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(filter);
        chooser.setTitle("Specify path to test-list file");
        File testListFile = chooser.showOpenDialog(new Stage());

        if (testListFile != null) {
            testListFilePath = testListFile.getAbsolutePath();
            testListPathComboBox.getItems().add(0, testListFilePath);
            testListPathComboBox.getSelectionModel().select(0);
        }
    }

    public void onProjectPathBrowse() {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Specify path project folder");
        File projectFolderPAth = chooser.showDialog(new Stage());

        if (projectFolderPAth != null) {
            projectFolderPath = projectFolderPAth.getAbsolutePath();
            projectFolderPathComboBox.getItems().add(0, projectFolderPath);
            projectFolderPathComboBox.getSelectionModel().select(0);
        }
    }

    public void onReferncePathBrowse() {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Specify path reference folder");
        File referenceFolderFile = chooser.showDialog(new Stage());

        if (referenceFolderFile != null) {
            referenceFolderPath = referenceFolderFile.getAbsolutePath();
            referenceFolderPathComboBox.getItems().add(0, referenceFolderPath);
            referenceFolderPathComboBox.getSelectionModel().select(0);
        }
    }

    public void onUpdateReferenceClick() {
        ArrayList<String> relatedObjects;
        if (referenceUpdater == null) {
            boolean updateAutomatically = updateRelatedObjAutoCheckBox.isSelected();
            String updateReferenceAutomatically = "No";

            if (updateAutomatically) {
                updateReferenceAutomatically = "Yes";
            }
            referenceUpdater = new ReferenceUpdater(testListFilePath, projectFolderPath, referenceFolderPath, updateReferenceAutomatically);
            relatedObjects = new ArrayList<>(referenceUpdater.checkRelatedObjects());
            if (!updateAutomatically) {
                relatedObjects.clear();
            }
        } else {
            relatedObjects = new ArrayList<>();

            for (Node tmp : RelatedObjectsVbox.getChildren()) {
                if (((CheckBox) tmp).isSelected()) {
                    relatedObjects.add(((CheckBox) tmp).getText());
                }
            }

        }
        referenceUpdater.UpdateReference(relatedObjects);

    }

    public void onCheckRelatedObjectClick() {
        System.setErr(ps);
        System.setOut(ps);
        if (RelatedObjectsVbox.getChildren() != null) {
            RelatedObjectsVbox.getChildren().clear();
        }

        if (!RelatedObjectsHiddenVBox.isVisible()) {
            RelatedObjectsHiddenVBox.setPrefHeight(200);
            RelatedObjectsVbox.getScene().getWindow()
                    .setHeight(RelatedObjectsVbox.getScene().getWindow().getHeight() + 200);
            RelatedObjectsHiddenVBox.setVisible(true);
        }
        boolean updateAutomatically = updateRelatedObjAutoCheckBox.isSelected();
        String updateReferenceAutomatically = "No";

        if (updateAutomatically) {
            updateReferenceAutomatically = "Yes";
        }
        referenceUpdater = new ReferenceUpdater(testListFilePath, projectFolderPath, referenceFolderPath, updateReferenceAutomatically);
        ArrayList<String> relatedObjects = new ArrayList<>(referenceUpdater.checkRelatedObjects());
        List<CheckBox> relatedObjectsCB = new ArrayList<>();
        for (String obj : relatedObjects) {
            CheckBox tmp = new CheckBox(obj);
            tmp.setSelected(updateAutomatically);
            tmp.setFont(Font.font(14));
            tmp.paddingProperty().setValue(new Insets(5, 0, 5, 10));
            relatedObjectsCB.add(tmp);
        }
        RelatedObjectsVbox.getChildren().addAll(relatedObjectsCB);

    }
}
