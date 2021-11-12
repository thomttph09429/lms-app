package com.poly.lmsapp.model;

public class BaseResponse {

        private String time;
        private Error error;
        private Object data;

        public String getTime() {
                return time;
        }

        public void setTime(String time) {
                this.time = time;
        }

        public Error getError() {
                return error;
        }

        public void setError(Error error) {
                this.error = error;
        }

        public Object getData() {
                return data;
        }
}

