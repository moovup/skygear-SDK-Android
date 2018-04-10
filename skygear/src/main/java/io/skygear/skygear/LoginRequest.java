/*
 * Copyright 2017 Oursky Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.skygear.skygear;

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
     * @param authData the unique identifier of a user
     * @param password the password
     */
    public LoginRequest(Map<String, Object> authData, String password) {
        super("auth:login");

        this.data = new HashMap<>();

        this.data.put("auth_data", authData);
        this.data.put("password", password);
    }

    /**
     * Instantiates a new Login request with provider.
     *
     * @param providerID       the provider identifier
     * @param providerAuthData the provider specific auth data
     */
    public LoginRequest(String providerID, Map<String, Object> providerAuthData) {
        super("auth:login");

        this.data = new HashMap<>();

        this.data.put("provider", providerID);
        this.data.put("provider_auth_data", providerAuthData);
    }

    @Override
    protected void validate() throws Exception {
        String providerID = (String) this.data.get("provider");
        Map providerAuthData = (Map) this.data.get("provider_auth_data");
        Map authData = (Map) this.data.get("auth_data");
        String password = (String) this.data.get("password");

        if (providerID != null || providerAuthData != null) {
            if (providerID != null) {
                throw new InvalidParameterException("Provider ID should not be null");
            }

            if (providerAuthData != null) {
                throw new InvalidParameterException("Provider auth data should not be null");
            }

            if (providerAuthData.isEmpty()) {
                throw new InvalidParameterException("Provider auth data should not be empty");
            }
        } else {
            if (authData == null) {
                throw new InvalidParameterException("Auth data should not be null");
            }

            if (authData.isEmpty()) {
                throw new InvalidParameterException("Auth data should not be empty");
            }

            if (password == null) {
                throw new InvalidParameterException("Password should not be null");
            }
        }
    }
}
