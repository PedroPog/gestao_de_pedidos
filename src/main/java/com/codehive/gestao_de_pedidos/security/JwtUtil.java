package com.codehive.gestao_de_pedidos.security;

import com.codehive.gestao_de_pedidos.model.RoleName;
import com.codehive.gestao_de_pedidos.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {

    private static final String SECRET_KEY = "CHAVE_MEGA_SECRETA_CHAVE_MEGA_SECRETA";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora (em milissegundos)
    private static final String ALGORITHM = "HmacSHA256";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    //AuthService authService = new AuthService();

    /**
     * Gera um token JWT sem bibliotecas externas
     */
    public String gerarToken(String email) {
        try {
            long now = System.currentTimeMillis();
            long exp = now + EXPIRATION_TIME;

            // Cabeçalho JWT (Header)
            String headerJson = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
            String header = base64UrlEncode(headerJson);

            // Payload (Corpo do Token)
            String payloadJson = String.format("{\"sub\":\"%s\",\"iat\":%d,\"exp\":%d}", email, now / 1000, exp / 1000);
            String payload = base64UrlEncode(payloadJson);

            // Assinatura (Signature)
            String signature = assinar(header + "." + payload);

            return header + "." + payload + "." + signature;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar token", e);
        }
    }

    /**
     * Valida um token JWT
     */
    public boolean validarToken(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return false;
            }

            String header = parts[0];
            String payload = parts[1];
            String signature = parts[2];

            // Verifica se a assinatura gerada é igual à recebida
            String expectedSignature = assinar(header + "." + payload);
            if (!expectedSignature.equals(signature)) {
                return false; // Assinatura inválida
            }

            // Verifica se o token está expirado
            Map<String, Object> payloadData = objectMapper.readValue(base64UrlDecode(payload), Map.class);
            long exp = ((Number) payloadData.get("exp")).longValue();
            return System.currentTimeMillis() / 1000 < exp; // Retorna true se ainda não expirou
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extrai o email do token JWT
     */
    public String extrairEmail(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length != 3) {
                return null;
            }

            String payload = parts[1];
            Map<String, Object> payloadData = objectMapper.readValue(base64UrlDecode(payload), Map.class);
            return (String) payloadData.get("sub");
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, String> validarTokenAdmin(String token){
        Map<String, String> resposta = new HashMap<>();
        boolean valido = validarToken(token);
        if(valido){
            String email = extrairEmail(token);
            resposta.put("email", email);
            resposta.put("status", "valido");
        }else {
            resposta.put("status", "invalido");
        }

        return resposta;
    }

    /**
     * Método auxiliar para assinar um JWT com HMAC SHA-256
     */
    private String assinar(String data) throws Exception {
        Mac mac = Mac.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
        mac.init(secretKeySpec);
        byte[] signatureBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return base64UrlEncode(signatureBytes);
    }

    /**
     * Método auxiliar para codificar Base64URL
     */
    private String base64UrlEncode(String data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data.getBytes(StandardCharsets.UTF_8));
    }

    private String base64UrlEncode(byte[] data) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
    }

    /**
     * Método auxiliar para decodificar Base64URL
     */
    private String base64UrlDecode(String data) {
        return new String(Base64.getUrlDecoder().decode(data), StandardCharsets.UTF_8);
    }
}
