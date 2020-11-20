package com.example.project309.app;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.project309.R;
import com.example.project309.app.SocketConnect;
import com.example.project309.app.SocketListener;
import com.example.project309.net_utils.Const;
import com.google.android.material.navigation.NavigationView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import static com.example.project309.net_utils.Const.URL_SOCKET;

public class DeliverConnect extends AppCompatActivity {

    private WebSocketClient mWebSocketClient;
    private Button bConnect, bDisconnect ,bSendButton;
    private TextView mOutput;
    private EditText mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_test);

        bConnect = findViewById(R.id.bConnect);
        bDisconnect = findViewById(R.id.bDisconnect);
        bSendButton = findViewById(R.id.bSendButton);

        mOutput = findViewById(R.id.mOutput);

        mOutput.setMovementMethod(new ScrollingMovementMethod());

        mInput = findViewById(R.id.mInput);

        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectWebSocket();
            }
        });

        bDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebSocketClient.close();
                mOutput.setText("");
            }
        });

        bSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mInput.getText().toString();
                if(message != null && message.length() > 0) {
                    mWebSocketClient.send(message);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
    }

    protected void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://echo.websocket.org");
            //uri = new URI(URL_SOCKET + Profile.currentLogin.getId());
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String message) {
                Log.i("Websocket", "Message Received");
                mOutput.setText(message);
            }

            @Override
            public void onClose(int errorCode, String reason, boolean remote) {
                Log.i("Websocket", "Closed " + reason);
            }

            @Override
            public void onError(Exception ex) {
                Log.i("Websocket", "Error " + ex.getMessage());
            }
        };
        mWebSocketClient.connect();
    }
}
