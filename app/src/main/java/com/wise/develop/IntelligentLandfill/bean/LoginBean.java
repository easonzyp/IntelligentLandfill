package com.wise.develop.IntelligentLandfill.bean;

import com.wise.develop.IntelligentLandfill.base.BaseResponse;

public class LoginBean extends BaseResponse {
    /**
     * count : 0
     * RoleName : 部门(车间)负责人
     * data : {"TokenValue":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoienJyIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiI2YWYzYmFhZi02YTE0LTRjYzktYTk5Ny0xOTIxZmZiZWE5ZWUiLCJPcmdhbml6ZUlkIjoiNTI2MWU5OTUtY2EwOC00ZWE1LTgxODQtNTlkMjI0NzI4MzMzIiwiT3JnYW5pemVOYW1lIjoi56We5a6d54Wk55-_IiwiRGVwSWQiOiI2MzA5MjEzZS0xM2RjLTRjYTItOWYyOS04ZTIxZWUzNTVhODUiLCJEZXBOYW1lIjoi6LSj5Lu75Y2V5L2NIiwiUmVhbE5hbWUiOiLotKPku7vkuroiLCJSb2xlTmFtZSI6IumDqOmXqCjovabpl7Qp6LSf6LSj5Lq6IiwiSXNBZG1pbiI6IjAiLCJpcCI6IjE5Mi4xNjguMS4xMDMiLCJuYmYiOjE1OTEzMjYxODIsImV4cCI6MTU5MjMzNDE4MiwiaXNzIjoieHkiLCJhdWQiOiJldmVyeW9uZSJ9.3rT68ag3zrdpJWzxPknn9eCoDD1ZIHVJwwsQSeWPW20","Expires":"2020-06-17 03:03:02","TokenType":"Bearer"}
     */

    private int count;
    private String RoleName;
    private String userid;
    private DataBean data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * TokenValue : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoienJyIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiI2YWYzYmFhZi02YTE0LTRjYzktYTk5Ny0xOTIxZmZiZWE5ZWUiLCJPcmdhbml6ZUlkIjoiNTI2MWU5OTUtY2EwOC00ZWE1LTgxODQtNTlkMjI0NzI4MzMzIiwiT3JnYW5pemVOYW1lIjoi56We5a6d54Wk55-_IiwiRGVwSWQiOiI2MzA5MjEzZS0xM2RjLTRjYTItOWYyOS04ZTIxZWUzNTVhODUiLCJEZXBOYW1lIjoi6LSj5Lu75Y2V5L2NIiwiUmVhbE5hbWUiOiLotKPku7vkuroiLCJSb2xlTmFtZSI6IumDqOmXqCjovabpl7Qp6LSf6LSj5Lq6IiwiSXNBZG1pbiI6IjAiLCJpcCI6IjE5Mi4xNjguMS4xMDMiLCJuYmYiOjE1OTEzMjYxODIsImV4cCI6MTU5MjMzNDE4MiwiaXNzIjoieHkiLCJhdWQiOiJldmVyeW9uZSJ9.3rT68ag3zrdpJWzxPknn9eCoDD1ZIHVJwwsQSeWPW20
         * Expires : 2020-06-17 03:03:02
         * TokenType : Bearer
         */

        private String TokenValue;
        private String Expires;
        private String TokenType;

        public String getTokenValue() {
            return TokenValue;
        }

        public void setTokenValue(String TokenValue) {
            this.TokenValue = TokenValue;
        }

        public String getExpires() {
            return Expires;
        }

        public void setExpires(String Expires) {
            this.Expires = Expires;
        }

        public String getTokenType() {
            return TokenType;
        }

        public void setTokenType(String TokenType) {
            this.TokenType = TokenType;
        }
    }
}
