package io.skygear.skygear;

import org.json.JSONObject;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

/**
 * The Login request.
 */
public class LoginRequest extends Request {
    /**
     * Instantiates a new Login request.
     *
     * @param username the username
     * @param email    the email
     * @param password the password
     * @param provider the provider
     * @param authData data for provider
     */
    public LoginRequest(String username, String email, String password, String provider, Map<String, Object> authData) {
        super("auth:login");

        this.data = new HashMap<>();

        this.data.put("username", username);
        this.data.put("email", email);
        this.data.put("password", password);
        this.data.put("provider", provider);
        this.data.put("auth_data", new JSONObject(authData));
    }

    @Override
    protected void validate() throws Exception {
        String username = (String) this.data.get("username");
        String email = (String) this.data.get("email");
        String password = (String) this.data.get("password");
        String provider = (String) this.data.get("provider");

        // login with provider
        if (provider != null) {
            return;
        }

        if (username != null && email != null) {
            throw new InvalidParameterException("Username and email should not coexist");
        }

        if (username == null && email == null) {
            throw new InvalidParameterException("Username and email should not both be null");
        }

        String identifier = username != null ? username : email;
        if (identifier.length() == 0) {
            throw new InvalidParameterException("Username and email should not both be empty");
        }

        if (password == null) {
            throw new InvalidParameterException("Password should not be null");
        }
    }
}
