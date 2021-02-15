/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package view.gui;

import exceptions.SizeReachedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import logic.BusinessLogic;
import models.mediaDB.Content;
import models.mediaDB.InteractiveVideo;
import models.mediaDB.LicensedAudioVideo;
import models.mediaDB.Tag;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HubControllerEditContent {
    @FXML
    private ListView viewListContentAddress;
    @FXML
    private TextField textFieldInteractionType;
    @FXML
    private TextField textFieldLicensor;
    @FXML
    private VBox vBoxTags;
    @FXML
    private Label labelGetBitrate;
    @FXML
    private TextField textFieldUploader;
    @FXML
    private Slider sliderBitrate;
    @FXML
    private TextField textFieldLengthHours;
    @FXML
    private TextField textFieldLengthMinutes;
    @FXML
    private TextField textFieldLengthSeconds;
    @FXML
    private ChoiceBox choiceBoxEncoding;
    @FXML
    private TextField textFieldFormatWidth;
    @FXML
    private TextField textFieldFormatHeight;
    @FXML
    private ChoiceBox choiceBoxSamplingrate;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonEdit;
    private List<CheckBox> checkBoxes;
    private List<String> listContentAddress;
    private String selectedAddress;

    private BusinessLogic businessLogic;
    private HubController hubController;

    public HubControllerEditContent() {
    }

    public void clickButtonCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    public void clickButtonEdit(ActionEvent actionEvent) {

        Alert alertError = new Alert(Alert.AlertType.ERROR);
        StringBuilder outputTextArea = new StringBuilder();
        StringBuilder outputTextField = new StringBuilder();
        try {
            String readValueUploader = this.textFieldUploader.getText();
            if ((readValueUploader == null) || (readValueUploader.length() == 0)) {
                alertError.setTitle("Empty Uploader");
                alertError.setHeaderText("No Uploader was set");
                alertError.setContentText("You have to input an uploader!");
                alertError.showAndWait();
            }
            Integer readValueLengthHours = Integer.parseInt(this.textFieldLengthHours.getText());
            Integer readValueLengthMinutes = Integer.parseInt(this.textFieldLengthMinutes.getText());
            Integer readValueLengthSeconds = Integer.parseInt(this.textFieldLengthSeconds.getText());
            Integer readValueFormatWidth = Integer.parseInt(this.textFieldFormatWidth.getText());
            Integer readValueFormatHeight = Integer.parseInt(this.textFieldFormatHeight.getText());
            Integer readValueSamplingrate = -1;
            if (this.choiceBoxSamplingrate.isShowing()) {
                readValueSamplingrate = Integer.parseInt(this.choiceBoxSamplingrate.getValue().toString());
            }
            String readValueEncoding = this.choiceBoxEncoding.getValue().toString();
            Duration readValueLength = Duration.ofSeconds(readValueLengthSeconds).plusMinutes(readValueLengthMinutes).plusHours(readValueLengthHours);
            Collection<Tag> readValueTags = this.getTags(new ArrayList<>());
            String readValueInteractionType = this.textFieldInteractionType.getText();
            Long readValueBitrate = Double.doubleToLongBits(this.sliderBitrate.getValue());
            String readValueHolder = this.textFieldLicensor.getText();
            this.businessLogic.editContent(this.selectedAddress,
                    readValueSamplingrate,
                    readValueFormatWidth,
                    readValueFormatHeight,
                    readValueEncoding,
                    readValueHolder,
                    readValueBitrate,
                    readValueLength,
                    readValueTags,
                    readValueUploader,
                    readValueInteractionType);
            outputTextField.append("updated");
            outputTextArea.append(String.format("Uploader: %16s%n", readValueUploader));
            outputTextArea.append(String.format("Tags: %s%n", readValueTags));
            outputTextArea.append(String.format("Address: %s%n",
                    this.businessLogic.getStorage().getListContent().get(
                            this.businessLogic.getStorage().getListContent().size() - 1
                    ).getAddress()
            ));

            ((Stage) buttonEdit.getScene().getWindow()).close();
        } catch (NumberFormatException | SizeReachedException e) {
            outputTextField.append(e.getClass());
            outputTextArea.append(e.getMessage() + "\n" + e.getLocalizedMessage() + "\n\n");
        } catch (NullPointerException e) {
            alertError.setTitle("Wrong Input");
            alertError.setHeaderText("You have an empty input!");
            alertError.setContentText("Please check you parameters from the content.");
            alertError.showAndWait();
        } finally {
            this.hubController.getMcmsTabMCMS_TextField().setText(outputTextField.toString());
            this.hubController.getMcmsTabMCMS_TextArea().setText(outputTextArea.toString());

            Stage stage = (Stage) buttonEdit.getScene().getWindow();
            stage.close();
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

    public void showSliderValue() {
        this.sliderBitrate.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                labelGetBitrate.setText("Bitrate: " + newValue + " kbps");
            }
        });
    }

    public void observeSelectListView() {
        this.getCheckBoxesTags();
        this.showSliderValue();
        /**
         this.viewListContentAddress.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent event) {
        viewListContentAddress.getSelectionModel().getSelectedIndex();
        }
        });
         */
        this.viewListContentAddress.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                Content content = businessLogic.getStorage().getMapAddressContent().get(newValue);
                selectedAddress = content.getAddress();
                if (content instanceof LicensedAudioVideo) {
                    textFieldInteractionType.setVisible(false);
                    textFieldLicensor.setVisible(true);
                    choiceBoxSamplingrate.setVisible(true);
                    textFieldInteractionType.setDisable(true);
                    textFieldLicensor.setDisable(false);
                    choiceBoxSamplingrate.setDisable(false);
                    LicensedAudioVideo contentParsed = (LicensedAudioVideo) content;
                    textFieldUploader.setText(contentParsed.getUploader().getName());
                    textFieldLicensor.setText(contentParsed.getHolder());
                    choiceBoxSamplingrate.setValue(contentParsed.getSamplingRate());
                    choiceBoxEncoding.setValue(contentParsed.getEncoding());
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
                    sliderBitrate.setValue(contentParsed.getBitrate());
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
                    choiceBoxSamplingrate.setVisible(false);
                    textFieldInteractionType.setDisable(false);
                    textFieldLicensor.setDisable(true);
                    choiceBoxSamplingrate.setDisable(true);
                    InteractiveVideo contentParsed = (InteractiveVideo) content;
                    textFieldUploader.setText(contentParsed.getUploader().getName());
                    choiceBoxEncoding.setValue(contentParsed.getEncoding());
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
                    sliderBitrate.setValue(contentParsed.getBitrate());
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

    public void changeSliders() {
        this.sliderBitrate.valueProperty().addListener((obs, oldval, newVal) -> this.sliderBitrate.setValue(newVal.intValue()));
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
