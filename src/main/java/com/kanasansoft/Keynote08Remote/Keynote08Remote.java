package com.kanasansoft.Keynote08Remote;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.script.ScriptException;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.WebSocket.Outbound;

public class Keynote08Remote implements OnMessageObserver{

	Robot robot = new Robot();
	Keynote08Wrapper keynote = null;

	public static void main(String[] args) throws Exception {
		new Keynote08Remote();
	}

	public Keynote08Remote() throws Exception {

		try {
			keynote = new Keynote08Wrapper();
		} catch (ScriptException e) {

			e.printStackTrace();

			Dialog alert = new Dialog(new Frame(), "Keynote08Remote", true);

			alert.setSize(300, 150);
			alert.setResizable(false);
			alert.setLocationByPlatform(true);
			alert.setAlwaysOnTop(true);

			GridBagLayout layout = new GridBagLayout();
			alert.setLayout(layout);
			GridBagConstraints gbc = new GridBagConstraints();

			Label lbl = new Label(e.getMessage(),Label.CENTER);
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.insets=new Insets(5, 5, 5, 5);
			layout.setConstraints(lbl, gbc);

			Button btn = new Button("Quit");
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.insets=new Insets(5, 5, 5, 5);
			layout.setConstraints(btn, gbc);

			alert.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					((Dialog)e.getSource()).setVisible(false);
				}
			});
			btn.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg) {
					((Button)arg.getSource()).getParent().setVisible(false);
				}}
			);

			alert.add(lbl);
			alert.add(btn);

			alert.setVisible(true);

			System.exit(0);

		}

		MenuItem quitMenuItem = new MenuItem("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		PopupMenu popupMenu = new PopupMenu();
		popupMenu.add(quitMenuItem);

		URL imageUrl = this.getClass().getClassLoader().getResource("images/icon.png");
		TrayIcon trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().createImage(imageUrl));
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip("Keynote08Remote");
		trayIcon.setPopupMenu(popupMenu);

		SystemTray systemTray = java.awt.SystemTray.getSystemTray();
		systemTray.add(trayIcon);

		Server server = new Server(40320);

		ResourceHandler resourceHandler = new ResourceHandler();
		String htmlPath = this.getClass().getClassLoader().getResource("html").toExternalForm();
		resourceHandler.setResourceBase(htmlPath);

		WebSocketServletWithMessageObserver wsServlet = new WebSocketServletWithMessageObserver(this);
		ServletHolder wsServletHolder = new ServletHolder(wsServlet);
		wsServletHolder.setInitParameter("bufferSize", Integer.toString(8192*256,10));
		ServletContextHandler wsServletContextHandler = new ServletContextHandler();
		wsServletContextHandler.addServlet(wsServletHolder, "/ws/*");

		HandlerList handlerList = new HandlerList();
		handlerList.setHandlers(new Handler[] {resourceHandler, wsServletContextHandler});
		server.setHandler(handlerList);
		server.start();
	}

	@Override
	synchronized public void onMessage(Outbound outbound, byte frame, String data) {
		if(data==null){return;}
		if(data.equals("")){return;}
		String[] messages = data.split(",",2);
		if(messages.length==0){return;}
		String messageType = messages[0];
		String messageData = messages.length==1?"":messages[1];
		try {
			if(messageType.equals("startslideshow")){
				keynote.startSlideshow();
			}else if(messageType.equals("stopslideshow")){
				keynote.stopSlideshow();
			}else if(messageType.equals("resumeslideshow")){
				keynote.resumeSlideshow();
			}else if(messageType.equals("pauseslideshow")){
				keynote.pauseSlideshow();
			}else if(messageType.equals("startorresumeslideshow")){
				if(keynote.startSlideshow()){
				}else if(keynote.resumeSlideshow()){
				}else{
				}
			}else if(messageType.equals("shownext")){
				keynote.showNext();
			}else if(messageType.equals("showprevious")){
				keynote.showPrevious();
			}else if(messageType.equals("showslideswitcher")){
				keynote.showSlideSwitcher();
			}else if(messageType.equals("acceptslideswitcher")){
				keynote.acceptSlideSwitcher();
			}else if(messageType.equals("cancelslideswitcher")){
				keynote.cancelSlideSwitcher();
			}else if(messageType.equals("moveslideswitcherforward")){
				keynote.moveSlideSwitcherForward();
			}else if(messageType.equals("moveslideswitcherbackward")){
				keynote.moveSlideSwitcherBackward();
			}
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	@Override
	synchronized public void onMessage(Outbound outbound, byte frame, byte[] data, int offset, int length) {
	}

}
