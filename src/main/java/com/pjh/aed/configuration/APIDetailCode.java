package com.pjh.aed.configuration;

import lombok.AllArgsConstructor;

public interface APIDetailCode {

    @AllArgsConstructor
    enum AEDAPIDetailCode {
        MANAGE_INFO(0), LOCATION_INFO(1), FULL_DATA(2);

        private Integer code;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
    @AllArgsConstructor
    enum ErmctAPIDetailCode {
        MANAGE_INFO(0);

        private Integer code;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
}