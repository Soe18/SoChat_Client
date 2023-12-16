package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        ProxyClient proxyClient = new ProxyClient();
        int header = -2;
        String nickname = "";
        while (header != -1) {
            // tmp input
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inserisci header 0:register, 1:login, 2:message");
            header = Integer.parseInt(input.readLine());

            if (header == 0) { // register nome utente
                System.out.println("Scrivi il tuo nome utente");
                nickname = input.readLine();
                System.out.println("Scrivi la tua password in chiaro :)");
                String password = input.readLine();
                System.out.println("Scrivi la tua password in chiaro :)");
                String confirmPassword = input.readLine();

                // premo register
                proxyClient.register(nickname, password, confirmPassword);
            }

            if (header == 1) { // login nome utente
                System.out.println("Scrivi il tuo nome utente");
                nickname = input.readLine();
                System.out.println("Scrivi la tua password in chiaro :)");
                String password = input.readLine();
                // premo login
                proxyClient.login(nickname, password);
            }

            if (header == 2) { // messaggio
                System.out.println("Scegli destinatario");
                String receiver = input.readLine();
                System.out.println("Scrivi messaggio");
                String text = input.readLine();
                // premo invio
                proxyClient.sendMsg(text, nickname, receiver);
            }

            if (header == 4) {
                proxyClient.loadContacts(nickname);
            }

            if (header == 5) {
                proxyClient.loadChat(nickname, "fabiano");
            }

            // 4 load contacts
            // 5 load chat (current, requestedUser)
        }
    }
}
