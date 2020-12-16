/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package view.ui.gui;

import exceptions.SizeReachedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.BusinessLogic;
import models.mediaDB.Tag;
import models.storage.MediaType;

import java.time.Duration;
import java.util.*;

public class HubControllerAddContentInteractiveVideo {
    @FXML
    private RadioButton radioButtonTestmode;
    @FXML
    private TextField textFieldInteractionType;
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
    private ChoiceBox choiceBoxVideoEncoding;
    @FXML
    private TextField textFieldFormatWidth;
    @FXML
    private TextField textFieldFormatHeight;
    @FXML
    private ChoiceBox choiceBoxSamlingrate;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonAdd;
    private List<CheckBox> checkBoxes;

    private BusinessLogic businessLogic;
    private HubController hubController;
    private HubControllerAddContentChoice hubControllerAddContentChoice;

    public HubControllerAddContentInteractiveVideo() {
    }

    public void clickButtonCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    public void clickButtonAdd(ActionEvent actionEvent) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        StringBuilder outputTextArea = new StringBuilder();
        StringBuilder outputTextField = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner("\n");
        try {
            String readValueUploader = this.textFieldUploader.getText();
            System.out.println(readValueUploader);
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
            String readValueVideoEncoding = this.choiceBoxVideoEncoding.getValue().toString();
            Duration readValueLength = Duration.ofSeconds(readValueLengthSeconds).plusMinutes(readValueLengthMinutes).plusHours(readValueLengthHours);
            Collection<Tag> readValueTags = this.getTags(new ArrayList<>());
            String readValueInteractionType = this.textFieldInteractionType.getText();
            Long readValueBitrate = Double.doubleToLongBits(this.sliderBitrate.getValue());
            Date thisDate = new Date();
            this.businessLogic.uploadContent(MediaType.InteractiveVideo,
                    -1,
                    readValueFormatWidth,
                    readValueFormatHeight,
                    readValueVideoEncoding,
                    null,
                    readValueBitrate,
                    readValueLength,
                    readValueTags,
                    0,
                    readValueUploader,
                    thisDate,
                    readValueInteractionType);

            outputTextField.append("An interactive video was added.");
            outputTextArea.append(String.format("Uploader: %16s%n", readValueUploader));
            outputTextArea.append(String.format("Date: %tT%tF%n", thisDate, thisDate));
            outputTextArea.append(String.format("Tags: %s%n", readValueTags));
            outputTextArea.append(String.format("Address: %s%n",
                    this.businessLogic.getStorage().getListContent().get(
                            this.businessLogic.getStorage().getListContent().size() - 1
                    ).getAddress()
            ));

            ((Stage) buttonAdd.getScene().getWindow()).close();
            this.hubControllerAddContentChoice.close();
        } catch (SizeReachedException e) {
            outputTextField.append(e.getClass());
            outputTextArea.append(e.getMessage() + "\n" + e.getLocalizedMessage() + "\n\n");
        } catch (NumberFormatException e) {
            outputTextField.append(e.getClass());
            outputTextArea.append(e.getMessage() + "\n" + e.getLocalizedMessage() + "\n\n");
        } finally {
            this.hubController.getMcmsTabMCMS_TextField().setText(outputTextField.toString());
            this.hubController.getMcmsTabMCMS_TextArea().setText(outputTextArea.toString());
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

    public HubControllerAddContentChoice getHubControllerAddContentChoice() {
        return this.hubControllerAddContentChoice;
    }

    public void setHubControllerAddContentChoice(HubControllerAddContentChoice hubControllerAddContentChoice) {
        this.hubControllerAddContentChoice = hubControllerAddContentChoice;
        this.hubController = hubControllerAddContentChoice.getHubController();
    }

    public void showSliderValue() {
        this.sliderBitrate.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                labelGetBitrate.setText("Bitrate: " + newValue + " kbps");
            }
        });
    }

    public void changeSliders() {
        /**
         StringConverter<Double> valuesBitrate = new DoubleStringConverter();
         valuesBitrate.fromString("256000");
         this.sliderBitrate.setLabelFormatter(valuesBitrate);
         */

        this.sliderBitrate.valueProperty().addListener((obs, oldval, newVal) -> this.sliderBitrate.setValue(newVal.intValue()));
        //Integer[] arraySamlingrate = {32000, 44100, 48000, 88200, 96000, 192000};
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
        //CheckBox[] checkBoxes = {this.checkBoxTagAnimal, this.checkBoxTagLifestyle, this.checkBoxTagNews, this.checkBoxTagTutorial};
        List<Node> nodeArrayList = this.vBoxTags.getChildren();
        for (Node n : nodeArrayList) {
            if (n instanceof CheckBox) {
                CheckBox k = (CheckBox) n;
                checkBoxes.add(k);
                if (k.isSelected()) {
                    readValueTags.add(Tag.valueOf(k.getText()));
                }
            }
        }
        /**
         for (CheckBox k : checkBoxes) {
         if (k.isSelected()) {
         readValueTags.add(Tag.valueOf(k.getText()));
         }
         }
         this.getCheckBoxes();
         */
        return readValueTags;
    }

    public void clickRadioButtonTestmode(ActionEvent actionEvent) {
        if (this.radioButtonTestmode.isSelected()) {
            this.textFieldUploader.setText("Testingers");
            this.textFieldInteractionType.setText("Voting");
            this.choiceBoxVideoEncoding.setValue(this.choiceBoxVideoEncoding.getItems().get(0));
            this.textFieldFormatWidth.setText("1920");
            this.textFieldFormatHeight.setText("1080");
            this.textFieldLengthHours.setText("2");
            this.textFieldLengthMinutes.setText("24");
            this.textFieldLengthSeconds.setText("47");
            this.sliderBitrate.setValue(25600);
            Random r = new Random();
            for (CheckBox k : this.checkBoxes) {
                k.setSelected(r.nextBoolean());
            }
        } else {
            this.textFieldUploader.clear();
            this.textFieldInteractionType.clear();
            this.choiceBoxVideoEncoding.setValue(null);
            this.textFieldFormatWidth.clear();
            this.textFieldFormatHeight.clear();
            this.textFieldLengthHours.setText("0");
            this.textFieldLengthMinutes.setText("0");
            this.textFieldLengthSeconds.setText("0");
            this.sliderBitrate.setValue(0);
            for (CheckBox k : this.checkBoxes) {
                k.setSelected(false);
            }
        }
    }
}