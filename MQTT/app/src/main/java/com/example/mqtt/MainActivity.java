package com.example.mqtt;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MainActivity extends AppCompatActivity {
    MqttAndroidClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String clientId = MqttClient.generateClientId();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("scada");
        options.setPassword("scada".toCharArray());
        options.setConnectionTimeout(240000);

        client = new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.99.106:1883",
                        clientId);

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic,MqttMessage message) throws Exception {
                Log.d("mqtt",message.toString());
                try {
                   // ConnectionHelper con = new ConnectionHelper();
                    //Connection connect = ConnectionHelper.CONN();

                    //int qos= message.getQos();
                   // String mess = message.toString();

                    //String SQL_INSERT = "INSERT INTO 'Messages' ('message','topic','quality_of_service') VALUES (?,?,?)";
                   // String SQL_INSERT = "Insert into Messages " + " (message,topic,quality_of_service) values " + "('" + mess
                          //  + "','" + topic + "','" + qos "')";
                    //statement.setBytes(1, message.getPayload());
                    //statement.setString(2, topic.getChars(););
                    //statement.setInt(3, message.getQos());
                    //PreparedStatement preparedStatement = connect
                           // .prepareStatement(SQL_INSERT);

                   // preparedStatement.executeUpdate();

                    //preparedStatement.close();
                } catch (Exception e) {
                    Log.d("Error while inserting", e.toString());
                }
            }


            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });



        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d("mqtt", "Ket noi Thanh Cong voi MQTT server");
                    String topic = "/CotHieu";

                    int qos = 1;

                    try {
                        IMqttToken subToken = client.subscribe(topic, qos);

                        subToken.setActionCallback(new IMqttActionListener() {
                            @Override
                            public void onSuccess(IMqttToken asyncActionToken) {
                                // The message was published
                                Log.d("mqtt", "Lay Du lieu tu /CotHieu");
                                //Log.d("mqtt1", "Lay Du lieu tu /Serial_Diag");
                            }

                            @Override
                            public void onFailure(IMqttToken asyncActionToken,
                                                  Throwable exception) {
                                // The subscription could not be performed, maybe the user was not
                                // authorized to subscribe on the specified topic e.g. using wildcards

                            }
                        });


                    } catch (MqttException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d("mqtt", "onFailure");
                    Log.d("mqtt1", "onFailure");
                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}