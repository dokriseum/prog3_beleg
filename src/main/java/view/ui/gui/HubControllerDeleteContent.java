/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package view.ui.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.BusinessLogic;
import models.mediaDB.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class HubControllerDeleteContent {
    @FXML
    private ListView viewListContentAddress;
    @FXML
    private TextField textFieldInteractionType;
    @FXML
    private TextField textFieldLicensor;
    @FXML
    private VBox vBoxTags;
    @FXML
    private TextField textFieldBitrate;
    @FXML
    private TextField textFieldUploader;
    @FXML
    private TextField textFieldLengthHours;
    @FXML
    private TextField textFieldLengthMinutes;
    @FXML
    private TextField textFieldLengthSeconds;
    @FXML
    private TextField textFieldVideoEncoding;
    @FXML
    private TextField textFieldFormatWidth;
    @FXML
    private TextField textFieldFormatHeight;
    @FXML
    private TextField textFieldSamplingrate;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonDelete;
    private List<CheckBox> checkBoxes;
    private List<String> listContentAddress;
    private String selectedAddress;

    private BusinessLogic businessLogic;
    private HubController hubController;

    public HubControllerDeleteContent() {
    }

    public void clickButtonCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    public void clickButtonDelete(ActionEvent actionEvent) {
        Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        Optional<ButtonType> option;
        StringBuilder outputTextArea = new StringBuilder();
        StringBuilder outputTextField = new StringBuilder();
        alertConfirmation.setTitle("Delete Contant?");
        alertConfirmation.setHeaderText("Sure, that you want delete this content?");
        alertConfirmation.setContentText("When yes, than clock \"OK\"!");
        option = alertConfirmation.showAndWait();
        try {
            if (this.selectedAddress == null) {
                alertError.setTitle("No Address Selected!");
                alertError.setHeaderText("You haven't selected a content!");
                alertError.setContentText("Please check the list and select your the content.");
                alertError.showAndWait();
            } else {
                Content tempContent = this.businessLogic.getContent(this.selectedAddress);
                Uploadable tempUploadable = (Uploadable) this.businessLogic.getContent(this.selectedAddress);
                if (((option.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) || (option.get().getButtonData().equals(ButtonBar.ButtonData.APPLY))) && (this.selectedAddress != null)) {
                    this.businessLogic.deleteContent(this.selectedAddress);
                    outputTextField.append(String.format("Delete: %s", tempContent.getAddress()));
                    outputTextArea.append(String.format("Uploader: %16s%n", tempUploadable.getUploader()));
                    outputTextArea.append(String.format("Tags: %s%n", tempContent.getTags()));
                    outputTextArea.append(String.format("From: %tT %tF %n", tempUploadable.getUploadDate(), tempUploadable.getUploadDate()));
                }
            }
        } catch (NumberFormatException e) {
            outputTextField.append(e.getClass());
            outputTextArea.append(e.getMessage() + "\n" + e.getLocalizedMessage() + "\n\n");
            alertError.setTitle(e.getClass().getName());
            alertError.setHeaderText(e.getCause().getMessage());
            alertError.setContentText(e.getCause().toString());
            alertError.showAndWait();
        } catch (NullPointerException e) {
            alertError.setTitle("No address selected!");
            alertError.setHeaderText("You haven't selected a content!");
            alertError.setContentText("Please check the list and select your the content.");
            alertError.showAndWait();
        } finally {
            if ((option.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) || (option.get().getButtonData().equals(ButtonBar.ButtonData.APPLY))) {
                this.hubController.getMcmsTabMCMS_TextField().setText(outputTextField.toString());
                this.hubController.getMcmsTabMCMS_TextArea().setText(outputTextArea.toString());
            }
            if (option.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE)) {
                if (this.selectedAddress != null) {
                    ((Stage) buttonDelete.getScene().getWindow()).close();
                }
            }
        }
    }

    public BusinessLogic getBusinessLogic() {
        return this.businessLogic;
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public HubController getHubController() {
        return this.hubController;
    }

    public void setHubController(HubController hubController) {
        this.hubController = hubController;
    }

    public void observeSelectListView() {
        this.getCheckBoxesTags();
        this.viewListContentAddress.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.print(viewListContentAddress.getSelectionModel().getSelectedIndex());
            }
        });

        this.viewListContentAddress.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Content content = businessLogic.getStorage().getMapAddressContent().get(newValue);
                selectedAddress = content.getAddress();
                if (content instanceof LicensedAudioVideo) {
                    textFieldInteractionType.setVisible(false);
                    textFieldLicensor.setVisible(true);
                    textFieldSamplingrate.setVisible(true);
                    textFieldInteractionType.setDisable(true);
                    textFieldLicensor.setDisable(false);
                    textFieldSamplingrate.setDisable(false);
                    LicensedAudioVideo contentParsed = (LicensedAudioVideo) content;
                    textFieldUploader.setText(contentParsed.getUploader().getName());
                    textFieldLicensor.setText(contentParsed.getHolder());
                    textFieldSamplingrate.setText(String.valueOf(contentParsed.getSamplingRate()));
                    textFieldVideoEncoding.setText(String.valueOf(contentParsed.getEncoding()));
                    textFieldFormatWidth.setText(String.valueOf(contentParsed.getWidth()));
                    textFieldFormatHeight.setText(String.valueOf(contentParsed.getHeight()));
                    /**
                     *
                     */

                    long seconds = contentParsed.getLength().getSeconds();
                    long absSeconds = Math.abs(seconds);
                    String durationString = String.format(
                            "%d:%02d:%02d",
                            absSeconds / 3600,
                            (absSeconds % 3600) / 60,
                            absSeconds % 60);
                    String[] durationStringArray = durationString.trim().split(":");

                    /**
                     *
                     */
                    textFieldLengthHours.setText(durationStringArray[0]);
                    textFieldLengthMinutes.setText(durationStringArray[1]);
                    textFieldLengthSeconds.setText(durationStringArray[2]);
                    textFieldBitrate.setText(String.valueOf(contentParsed.getBitrate()));
                    for (CheckBox k : checkBoxes) {
                        k.setSelected(false);
                    }
                    for (Tag k1 : contentParsed.getTags()) {
                        for (CheckBox k2 : checkBoxes) {
                            if (k2.getText().equals(k1.toString())) {
                                k2.setSelected(true);
                            }
                        }
                    }
                } else if (content instanceof InteractiveVideo) {
                    textFieldInteractionType.setVisible(true);
                    textFieldLicensor.setVisible(false);
                    textFieldSamplingrate.setVisible(false);
                    textFieldInteractionType.setDisable(false);
                    textFieldLicensor.setDisable(true);
                    textFieldSamplingrate.setDisable(true);
                    InteractiveVideo contentParsed = (InteractiveVideo) content;
                    textFieldUploader.setText(contentParsed.getUploader().getName());
                    textFieldVideoEncoding.setText(String.valueOf(contentParsed.getEncoding()));
                    textFieldFormatWidth.setText(String.valueOf(contentParsed.getWidth()));
                    textFieldFormatHeight.setText(String.valueOf(contentParsed.getHeight()));
                    textFieldInteractionType.setText(contentParsed.getType());
                    /**
                     *
                     */

                    long seconds = contentParsed.getLength().getSeconds();
                    long absSeconds = Math.abs(seconds);
                    String durationString = String.format(
                            "%d:%02d:%02d",
                            absSeconds / 3600,
                            (absSeconds % 3600) / 60,
                            absSeconds % 60);
                    String[] durationStringArray = durationString.trim().split(":");

                    /**
                     *
                     */
                    textFieldLengthHours.setText(durationStringArray[0]);
                    textFieldLengthMinutes.setText(durationStringArray[1]);
                    textFieldLengthSeconds.setText(durationStringArray[2]);
                    textFieldBitrate.setText(String.valueOf(contentParsed.getBitrate()));
                    for (CheckBox k : checkBoxes) {
                        k.setSelected(false);
                    }
                    for (Tag k1 : contentParsed.getTags()) {
                        for (CheckBox k2 : checkBoxes) {
                            if (k2.getText().equals(k1.toString())) {
                                k2.setSelected(true);
                            }
                        }
                    }
                } else {

                }
            }
        });


    }

    public void getCheckBoxesTags() {
        this.checkBoxes = new ArrayList<>();
        List<Node> nodeArrayList = this.vBoxTags.getChildren();
        for (Node n : nodeArrayList) {
            if (n instanceof CheckBox) {
                checkBoxes.add((CheckBox) n);
            }
        }
    }

    private Collection<Tag> getTags(Collection<Tag> readValueTags) {
        List<Node> nodeArrayList = this.vBoxTags.getChildren();
        for (Node n : nodeArrayList) {
            if (n instanceof CheckBox) {
                CheckBox k = (CheckBox) n;
                if (k.isSelected()) {
                    readValueTags.add(Tag.valueOf(k.getText()));
                }
            }
        }
        return readValueTags;
    }

    public void initListView() {
        this.listContentAddress = FXCollections.observableArrayList();
        for (Content k : this.businessLogic.getStorage().getListContent()) {
            listContentAddress.add(k.getAddress());
        }
        this.viewListContentAddress.setItems((ObservableList) listContentAddress);

        this.viewListContentAddress.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new TextFieldListCell<>(new StringConverter<String>() {

                    @Override
                    public String toString(String object) {
                        return object;
                    }

                    @Override
                    public String fromString(String string) {
                        return string;
                    }
                });
            }
        });
    }
}
