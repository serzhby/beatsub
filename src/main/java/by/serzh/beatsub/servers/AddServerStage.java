package by.serzh.beatsub.servers;

import by.serzh.beatsub.ui.BaseStage;
import by.serzh.beatsub.ui.StageController;

public class AddServerStage extends BaseStage {

    public AddServerStage(StageController stageController) {
        super(stageController, "/templates/add_server.fxml", "servers.add_server");
    }
}
