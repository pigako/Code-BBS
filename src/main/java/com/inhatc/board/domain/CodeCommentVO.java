package com.inhatc.board.domain;

import java.util.Date;

public class CodeCommentVO {
        private int cbc_no;
        private int cb_no;
        private int mem_no;
        private String mem_id;
        private String cc_text;
        private Date cc_reg;
        public int getCbc_no() {
                return cbc_no;
        }
        public void setCbc_no(int cbc_no) {
                this.cbc_no = cbc_no;
        }
        public int getCb_no() {
                return cb_no;
        }
        public void setCb_no(int cb_no) {
                this.cb_no = cb_no;
        }
        public int getMem_no() {
                return mem_no;
        }
        public void setMem_no(int mem_no) {
                this.mem_no = mem_no;
        }
        public String getMem_id() {
                return mem_id;
        }
        public void setMem_id(String mem_id) {
                this.mem_id = mem_id;
        }
        public String getCc_text() {
                return cc_text;
        }
        public void setCc_text(String cc_text) {
                this.cc_text = cc_text;
        }
        public Date getCc_reg() {
                return cc_reg;
        }
        public void setCc_reg(Date cc_reg) {
                this.cc_reg = cc_reg;
        }
}
