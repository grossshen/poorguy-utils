package netUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author poorguy
 * @version 0.0.1
 * @E-mail 494939649@qq.com
 * @created 2019/3/29
 */
public class NetUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(NetUtil.class);
    public static void main(String[] args){
        System.out.println(getPhysicalAddress());
    }

    /**
     * 获取本地ip
     *
     * @return 本地ip字符串
     */
    public static String getLocalIp() {
        String ip = null;
        try {
            byte[] addr = InetAddress.getLocalHost().getAddress();
            ip = (addr[0] & 0xff) + "." + (addr[1] & 0xff) + "." + (addr[2] & 0xff) + "." + (addr[3] & 0xff);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }
    /**
     * 获取本地mac地址
     * @return
     */
    public static String getPhysicalAddress() {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[] mac = new byte[0];
            try {
                mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            //System.out.println("mac数组长度：" + mac.length);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                // logger.info("每8位:" + str);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            LOGGER.info("本机MAC地址:" + sb.toString().toUpperCase());
            return sb.toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
