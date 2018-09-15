package ar.edu.itba.paw.interfaces;

public interface EmailService {
    public void sendMessage(String to, String subject, String text);
}
