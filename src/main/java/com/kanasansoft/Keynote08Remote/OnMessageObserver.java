package com.kanasansoft.Keynote08Remote;

import org.eclipse.jetty.websocket.WebSocket.Connection;

public interface OnMessageObserver {
	void onMessage(Connection connection, String data);
}
