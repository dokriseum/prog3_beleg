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
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.BusinessLogic;
import logic.dummies.DummyContentAdder;
import models.storage.StorageContent;

import java.io.IOException;
import java.math.BigDecimal;

public class HubController {
    @FXML
    private MenuBar mcmsMain_MenuBar;
    @FXML
    private AnchorPane mcmsMain_AnchorPane;
    @FXML
    private AnchorPane mcmsMain_MirrorText_AnchorPane;
    @FXML
    private HBox mcmsMain_MirrorText_HBox_1;
    @FXML
    private VBox mcmsMain_MirrorText_VBox_1;
    @FXML
    private VBox mcmsMain_MirrorText_VBox_2;
    @FXML
    private VBox mcmsMain_VBOX_1;
    @FXML
    private TextField mcmsTabMCMS_TextField;
    @FXML
    private TextArea mcmsTabMCMS_TextArea;
    @FXML
    private TextArea mcmsTabMirrorText_textAreaĹeft;
    @FXML
    private TextArea mcmsTabMirrorText_textAreaRight;
    @FXML
    private TabPane mcmsMain_TabPane;

    private BusinessLogic businessLogic;
    private DummyContentAdder dummyContentAdder;

    public HubController() {
        this.businessLogic = new BusinessLogic(new StorageContent(new BigDecimal("9876543210")));
        this.dummyContentAdder = new DummyContentAdder(this.businessLogic);
    }

    public void scale(Scene scene) {
        this.mcmsMain_AnchorPane.setMaxSize(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());


        this.mcmsMain_TabPane.prefHeightProperty().bind(scene.heightProperty());
        this.mcmsMain_TabPane.prefWidthProperty().bind(scene.widthProperty());
        this.mcmsMain_VBOX_1.prefHeightProperty().bind(scene.heightProperty());
        this.mcmsMain_VBOX_1.prefWidthProperty().bind(scene.widthProperty());
        this.mcmsMain_AnchorPane.prefHeightProperty().bind(scene.heightProperty());
        this.mcmsMain_AnchorPane.prefWidthProperty().bind(scene.widthProperty());
        this.mcmsMain_MirrorText_AnchorPane.prefHeightProperty().bind(scene.heightProperty());
        this.mcmsMain_MirrorText_AnchorPane.prefWidthProperty().bind(scene.widthProperty());
        this.mcmsMain_MirrorText_HBox_1.prefHeightProperty().bind(scene.heightProperty());
        this.mcmsMain_MirrorText_HBox_1.prefWidthProperty().bind(scene.widthProperty());
        this.mcmsMain_MirrorText_VBox_1.prefHeightProperty().bind(scene.heightProperty());
        this.mcmsMain_MirrorText_VBox_1.prefWidthProperty().bind(scene.widthProperty());
        this.mcmsMain_MirrorText_VBox_2.prefHeightProperty().bind(scene.heightProperty());
        this.mcmsMain_MirrorText_VBox_2.prefWidthProperty().bind(scene.widthProperty());
        this.mcmsMain_MenuBar.prefWidthProperty().bind(scene.widthProperty());
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        System.exit(0);
    }

    public void uploadContent(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MCMSAddContentChoice.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        HubControllerAddContentChoice hubControllerAddContentChoice = fxmlLoader.getController();
        hubControllerAddContentChoice.setBusinessLogic(businessLogic);
        hubControllerAddContentChoice.setHubController(this);

        stage.setScene(scene);
        stage.show();
    }

    public void close(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void deleteMediaContent(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MCMSDeleteContent.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        HubControllerDeleteContent hubControllerDeleteContent = fxmlLoader.getController();
        hubControllerDeleteContent.setBusinessLogic(businessLogic);
        hubControllerDeleteContent.setHubController(this);
        hubControllerDeleteContent.initListView();
        hubControllerDeleteContent.observeSelectListView();

        stage.setScene(scene);
        stage.show();
    }

    public void updateContent(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MCMSEditContent.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        HubControllerEditContent hubControllerEditContent = fxmlLoader.getController();
        hubControllerEditContent.setBusinessLogic(businessLogic);
        hubControllerEditContent.setHubController(this);
        hubControllerEditContent.initListView();
        hubControllerEditContent.observeSelectListView();
        hubControllerEditContent.changeSliders();

        stage.setScene(scene);
        stage.show();
    }

    public void showContent(ActionEvent actionEvent) {
        this.mcmsTabMCMS_TextField.setText("Action: Show Content");
        this.mcmsTabMCMS_TextArea.setText(this.businessLogic.getStorage().getListContent().toString());
    }

    public void showUploaders(ActionEvent actionEvent) {
        this.mcmsTabMCMS_TextField.setText("Action: Show Uploaders");
        this.mcmsTabMCMS_TextArea.setText(this.businessLogic.getUploaders().toString());
    }

    public void about(ActionEvent actionEvent) {
    }

    public void mirrorToLeft(ActionEvent actionEvent) {
        this.mcmsTabMirrorText_textAreaĹeft.setText(this.mcmsTabMirrorText_textAreaRight.getText());
    }

    public void mirrorToRight(ActionEvent actionEvent) {
        this.mcmsTabMirrorText_textAreaRight.setText(this.mcmsTabMirrorText_textAreaĹeft.getText());
    }

    public BusinessLogic getBusinessLogic() {
        return this.businessLogic;
    }

    public void dummyAddMediaContent(ActionEvent actionEvent) {
        this.mcmsTabMCMS_TextField.setText("Action: Add Dummy");
        this.mcmsTabMCMS_TextArea.setText(this.dummyContentAdder.addDummy());
    }

    public TextField getMcmsTabMCMS_TextField() {
        return this.mcmsTabMCMS_TextField;
    }

    public TextArea getMcmsTabMCMS_TextArea() {
        return this.mcmsTabMCMS_TextArea;
    }
}
