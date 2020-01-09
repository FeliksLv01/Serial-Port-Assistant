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
    @FXML
    private ComboBox<Integer> BaudRateBox;

    private SerialController serialController;
    private List<String> systemPorts;
    private boolean isOpen = false;

    public SerialAssistant() {

        serialController = new SerialController();
    }

    /**
     * 点击commbox，刷新里面的数据
     */
    public void onShowComboBox(Event event) {
        systemPorts = SerialController.getSystemPort();
        comboBox.getItems().clear();
        comboBox.getItems().addAll(systemPorts);
    }

    /**
    * 点击BaudRateBox,显示可供选择的波特率
    */
    public void onShowBaudRateBox(Event event){
        BaudRateBox.getItems().addAll(2400,4800,9600,19200,38400,57600,115200);
        BaudRateBox.setVisibleRowCount(4);
    }

    /**
     * 打开串口
     */
    public void onActionOpenBtn(ActionEvent actionEvent) {
        if (!isOpen) {
            if (!serialController.open(comboBox.getValue(), BaudRateBox.getValue())) {
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

