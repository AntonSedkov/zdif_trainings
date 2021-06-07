package anton.sample.model;

/**
 * User: Sedkov Anton
 * Date: 07.06.2021
 */
public enum ContactType {
    PHONE("Phone"),
    MOBILE("Mobile"),
    MAIL("Mail"),
    SKYPE("Skype"),
    VIBER("Viber"),
    TELEGRAM("Telegram"),
    WHATS_APP("WhatsApp"),
    LINKEDIN("LinkedIn"),
    FACEBOOK("Facebook"),
    VK("Vk");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
