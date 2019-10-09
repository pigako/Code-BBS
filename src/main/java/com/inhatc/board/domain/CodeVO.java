package com.inhatc.board.domain;

import java.util.Date;

public class CodeVO {
        private int cb_no;
        private int mem_no;
        private String mem_id;
        private String cb_title;
        private String cb_text;
        private Date cb_reg;
        private int cb_view;
        private String cb_lang;
        private int count;
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
        public String getCb_title() {
                return cb_title;
        }
        public void setCb_title(String cb_title) {
                this.cb_title = cb_title;
        }
        public String getCb_text() {
                return cb_text;
        }
        public void setCb_text(String cb_text) {
                this.cb_text = cb_text;
        }
        public Date getCb_reg() {
                return cb_reg;
        }
        public void setCb_reg(Date cb_reg) {
                this.cb_reg = cb_reg;
        }
        public int getCb_view() {
                return cb_view;
        }
        public void setCb_view(int cb_view) {
                this.cb_view = cb_view;
        }
        public String getCb_lang() {
                return cb_lang;
        }
        public void setCb_lang(String cb_lang) {
                this.cb_lang = cb_lang;
        }
        public int getCount() {
                return count;
        }
        public void setCount(int count) {
                this.count = count;
        }
}
