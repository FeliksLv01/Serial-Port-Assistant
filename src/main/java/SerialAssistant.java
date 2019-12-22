import gnu.io.SerialPortEvent;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.util.List;

public class SerialAssistant {
    @FXML
    private TextArea receiveText;
    @FXML
    private TextArea sendText;
    @FXML
    private Button openBnt;
    @FXML
    private ComboBox<String> comboBox;

    private SerialController serialController;
    private List<String> systemPorts;
    private boolean isOpen = false;

    public SerialAssistant() {
        systemPorts = SerialController.getSystemPort();
        serialController = new SerialController();
    }

    /**
     * 点击commbox，刷新里面的数据
     */
    public void onShowComboBox(Event event) {
        comboBox.getItems().clear();
        comboBox.getItems().addAll(systemPorts);
    }

    /**
     * 打开串口
     */
    public void onActionOpenBtn(ActionEvent actionEvent) {
        if (!isOpen) {
            if (!serialController.open(comboBox.getValue(), 9600)) {
                return;
            }
            serialController.setListenerToSerialPort(ev -> {
                if (ev.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                    receiveText.appendText( new String(serialController.readData()));
                }
            });
            isOpen = true;
            openBnt.setText("关闭串口");
        } else {
            isOpen = false;
            serialController.close();
            openBnt.setText("打开串口");
        }
    }

    /**
     * 发送数据
     */
    public void onActionSendBtn(ActionEvent actionEvent) {
        serialController.sendData(sendText.getText().getBytes());
    }
}

