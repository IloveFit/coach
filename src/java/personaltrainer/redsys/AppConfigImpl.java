package personaltrainer.redsys;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.Notification;

public class AppConfigImpl implements AppConfig {

    static String getMerchantCode() {
        return "061978060"; //Este es el c�digo de comercio
    }

    static String getTerminal() {
        return "001"; //Este es el identificador de la terminal
    }

    static String getSecretKey() {
        return "sq7HjrUOBfKmC576ILgskD5srU870gJ7"; //Esta es la clave secreta de tu pasarela en PRODUCCI�N
                                                   //La clave de preproducci�n es igual para todas las pasarelas por lo que no tienes que indicarla
    }

    static boolean isTestMode() {
        return true; //Establ�celo a false cuando quieras hacer funcionar la pasarela en el entorno de producci�n
    }

    @Override
    public void saveNotification(Notification notification) {
        // Pon aqu� lo que quieras hacer con la notificaci�n. Seguramente, almacenar en tu base de datos que el pedido ha sido pagado
        // Las notificaciones aqu� recibidas ya han pasado todas las comprobaciones de seguridad y son v�lidas
    }
}