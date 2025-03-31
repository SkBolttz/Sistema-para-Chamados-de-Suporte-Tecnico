package br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Security;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.GestaoChamados.Chamados.para.Suporte.Tecnico.Entity.Model.TodosUsuarios;

@Service
public class TokenService {
    
    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(TodosUsuarios usuario, String string) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Acesso")
                    .withSubject(usuario.getNome())
                    .withClaim("email", usuario.getEmail())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar o Token! " + e.getMessage());
        }
    }

    private Instant getExpirationDate() {
        return Instant.now().plusSeconds(7200);
    }

    public DecodedJWT validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Acesso")
                    .build()
                    .verify(token);
        } catch (Exception e) {
            throw new RuntimeException("Token inválido! " + e.getMessage());
        }
    }
}
