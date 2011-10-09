package com.kanasansoft.Keynote08Remote;

import org.eclipse.jetty.websocket.WebSocket;

class WebSocketWithMessageObserver implements WebSocket.OnTextMessage {
	OnMessageObserver onMessageObserver = null;
	Connection connection;
	public WebSocketWithMessageObserver(OnMessageObserver onMessageObserver) {
		super();
		this.onMessageObserver = onMessageObserver;
	}
	@Override
	public void onOpen(Connection connection) {
		System.out.println("connect : "+this);
		this.connection = connection;
	}
	@Override
	public void onClose(int closeCode, String message) {
		System.out.println("disconnect : "+this);
	}
	@Override
	synchronized public void onMessage(String data) {
		onMessageObserver.onMessage(this.connection, data);
	}
}
