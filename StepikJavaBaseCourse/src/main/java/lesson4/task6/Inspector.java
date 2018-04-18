package lesson4.task6;

import static lesson4.task6.Constants.BANNED_SUBSTANCE;
import static lesson4.task6.Constants.WEAPONS;

/**
 * Инспектор, который следит за запрещенными и украденными посылками и бьет тревогу в виде исключения, если была обнаружена подобная посылка. Если он заметил запрещенную посылку с одним из запрещенных содержимым ("weapons" и "banned substance"), то он бросает IllegalPackageException. Если он находит посылку, состоящую из камней (содержит слово "stones"), то тревога прозвучит в виде StolenPackageException. Оба исключения вы должны объявить самостоятельно в виде непроверяемых исключений.
 *
 * @author Starovoytov
 * @since 11.04.2018
 */
public class Inspector implements MailService {
    @Override
    public Sendable processMail(Sendable mail) {
        if (mail instanceof MailPackage) {
            String content = ((MailPackage) mail).getContent().getContent();

            if (content.contains(BANNED_SUBSTANCE) || content.contains(WEAPONS)) {
                throw new IllegalPackageException();
            }

            if (content.contains("stones")) {
                throw new StolenPackageException();
            }
        }

        return mail;
    }
}
