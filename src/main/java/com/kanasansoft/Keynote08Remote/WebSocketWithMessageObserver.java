package com.kanasansoft.Keynote08Remote;

import org.eclipse.jetty.websocket.WebSocket;

class WebSocketWithMessageObserver implements WebSocket {
	OnMessageObserver onMessageObserver = null;
	Outbound outbound;
	public WebSocketWithMessageObserver(OnMessageObserver onMessageObserver) {
		super();
		this.onMessageObserver = onMessageObserver;
	}
	@Override
	public void onConnect(Outbound outbound) {
		System.out.println("connect : "+this);
		this.outbound = outbound;
	}
	@Override
	public void onDisconnect() {
		System.out.println("disconnect : "+this);
	}
	@Override
	synchronized public void onMessage(byte frame, String data) {
		onMessageObserver.onMessage(this.outbound, frame, data);
	}
	@Override
	synchronized public void onMessage(byte frame, byte[] data, int offset, int length) {
		onMessageObserver.onMessage(this.outbound, frame, data, offset, length);
	}
}
