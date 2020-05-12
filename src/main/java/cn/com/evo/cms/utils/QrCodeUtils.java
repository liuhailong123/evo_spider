package cn.com.evo.cms.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtils {
    /**
     * 生成某网址的base64二维码
     * 编码
     *
     * @param contents
     */

    public static String base64EncodeForQR(String contents, int width, int height) {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.MARGIN, 0);
        String qr = "";
        try {
            BitMatrix byteMatrix;
            byteMatrix = new MultiFormatWriter().encode(new String(contents.getBytes("UTF-8"), "iso-8859-1"),
                    BarcodeFormat.QR_CODE, width, height, hints);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(byteMatrix, "png", bao);
            qr = "data:image/png;base64," + Base64Utils.encodeToString(bao.toByteArray()).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return qr;

    }

    public static void main(String[] args) {
        String url = "https://wx.evomedia.cn/redirect?code=001&id=ff80808165173607016517ab49021176&type=1";
        String binary = QrCodeUtils.base64EncodeForQR(url, 200, 200);
        System.out.println(binary);

    }
}

