package Blume.Coding.skype;

import java.io.IOException;
import java.util.Scanner;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NotParticipatingException;

public class Main {

	String input = null;
	Scanner inputReader = new Scanner(System.in);

	public static class Bot {
		public static void main(String[] args) {
			new Bot();
		}

		public static void clearScreen() {
			for (int clear = 0; clear < 1000; clear++) {
				System.out.println("\b");
				System.out.flush();
			}
		}

		public Bot() {
			String input = null;
			String input2 = null;
			Scanner inputReader = new Scanner(System.in);
			System.out.println("Gib deinen skype usernamen ein : ");
			input = inputReader.nextLine();
			System.out.println("Gib dein skype passwort ein : ");
			input2 = inputReader.nextLine();
			Skype skype = new SkypeBuilder(input, input2).withAllResources().build();
			try {
				skype.login();
			} catch (NotParticipatingException e) {
				e.printStackTrace();
				return;
			} catch (InvalidCredentialsException e) {
				e.printStackTrace();
				return;
			} catch (ConnectionException e) {
				e.printStackTrace();
				return;
			}

			registerEvents(skype);

			try {
				skype.subscribe();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				skype.setVisibility(Visibility.DO_NOT_DISTURB);
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearScreen();

			System.out.println("##    ## ######## ########");
			System.out.println(" ##  ##  ##       ##      ");
			System.out.println("  ####   ##       ##      ");
			System.out.println("   ##    ######## ########");
			System.out.println("   ##    ##       ##      ");
			System.out.println("   ##    ##       ##      ");
			System.out.println("   ##    ######## ########");

			System.out.println("Eingeloggt c:");
		}

		private void registerEvents(Skype skype) {

			// skype.getEventDispatcher().registerListener(new AutoAccept());
			skype.getEventDispatcher().registerListener(new Nachricht());

		}
	}

}
