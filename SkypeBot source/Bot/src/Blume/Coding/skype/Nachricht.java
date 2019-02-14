package Blume.Coding.skype;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.events.EventHandler;
import com.samczsun.skype4j.events.Listener;
import com.samczsun.skype4j.events.chat.message.MessageReceivedEvent;
import com.samczsun.skype4j.events.contact.ContactRequestEvent;
import com.samczsun.skype4j.exceptions.ConnectionException;

public class Nachricht implements Listener {
	String Random;

	@EventHandler
	public void onContact(ContactRequestEvent event) {
		try {
			System.out.println("Neue contact anfrage von " + event.getRequest().getSender().getUsername() + " um "
					+ event.getRequest().getTime() + " mit der nachricht " + event.getRequest().getMessage());
			event.getRequest().accept();

			System.out.println("fügte " + event.getRequest().getSender().getUsername() + " zu kontakte hinzu");

		} catch (ConnectionException e) {

			e.printStackTrace();
		}
	}

	@EventHandler
	public void onMessageReceived(MessageReceivedEvent e) throws IOException {
		String msg = e.getMessage().getContent().toString();

		try (BufferedReader reader = new BufferedReader(new FileReader("message.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				try {
					String status = String.format(line, ".");
					System.out.println("die nachricht : " + "'"+status+"'"+ " wurde versendet an "
							+ e.getChat().getIdentity().replaceAll("8:", ""));

					sendMessage(e.getChat(), status);
			
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	private static void sendMessage(Chat c, String msg) {
		try {
			c.sendMessage(msg);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
