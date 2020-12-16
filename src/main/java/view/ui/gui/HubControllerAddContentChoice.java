/**
 * @author Dustin Eikmeier
 * @version 1.0
 * @since 1.8
 */

package view.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.BusinessLogic;

import java.io.IOException;

public class HubControllerAddContentChoice {
    @FXML
    private Button buttonInteractiveVideo;
    @FXML
    private Button buttonLicensedAudioVideo;
    @FXML
    private Button buttonCancel;

    private BusinessLogic businessLogic;
    private HubController hubController;


    public HubControllerAddContentChoice() {
    }

    public void clickButtonCancel(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) buttonCancel.getScene().getWindow();
        stage.close();
    }

    public void clickButtonAddInteractiveVideo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MCMSAddContentInteractiveVideo.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        HubControllerAddContentInteractiveVideo hubControllerAddContentInteractiveVideo = fxmlLoader.getController();
        hubControllerAddContentInteractiveVideo.setBusinessLogic(businessLogic);
        hubControllerAddContentInteractiveVideo.showSliderValue();
        hubControllerAddContentInteractiveVideo.changeSliders();
        hubControllerAddContentInteractiveVideo.setHubControllerAddContentChoice(this);
        hubControllerAddContentInteractiveVideo.getCheckBoxesTags();

        stage.setScene(scene);
        stage.show();
    }

    public void clickButtonAddLicensedAudioVideo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MCMSAddContentLicensedAudioVideo.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        HubControllerAddContentLicensedAudioVideo hubControllerAddContentLicensedAudioVideo = fxmlLoader.getController();
        hubControllerAddContentLicensedAudioVideo.setBusinessLogic(businessLogic);
        hubControllerAddContentLicensedAudioVideo.showSliderValue();
        hubControllerAddContentLicensedAudioVideo.changeSliders();
        hubControllerAddContentLicensedAudioVideo.setHubControllerAddContentChoice(this);
        hubControllerAddContentLicensedAudioVideo.getCheckBoxesTags();

        stage.setScene(scene);
        stage.show();
    }

    public void close() {
        ((Stage) buttonCancel.getScene().getWindow()).close();
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
}
