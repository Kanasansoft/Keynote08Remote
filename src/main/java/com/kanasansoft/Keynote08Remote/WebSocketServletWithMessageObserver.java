package com.kanasansoft.Keynote08Remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

class WebSocketServletWithMessageObserver extends WebSocketServlet {
	private static final long serialVersionUID = 1L;
	OnMessageObserver onMessageObserver = null;

	public WebSocketServletWithMessageObserver(OnMessageObserver onMessageObserver) {
		super();
		this.onMessageObserver = onMessageObserver;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getNamedDispatcher("default").forward(request, response);
	}
	@Override
	protected WebSocket doWebSocketConnect(HttpServletRequest request, String protocol) {
		return new WebSocketWithMessageObserver(onMessageObserver);
	}
}
