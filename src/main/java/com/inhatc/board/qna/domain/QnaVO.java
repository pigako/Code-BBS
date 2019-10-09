package com.inhatc.board.qna.domain;

import java.util.Date;

public class QnaVO {
        private int qb_no;
        private int mem_no;
        private String mem_id;
        private String qb_title;
        private String qb_text;
        private Date qb_reg;
        private int qb_view;
        private int count;
        public int getQb_no() {
                return qb_no;
        }
        public void setQb_no(int qb_no) {
                this.qb_no = qb_no;
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
        public String getQb_title() {
                return qb_title;
        }
        public void setQb_title(String qb_title) {
                this.qb_title = qb_title;
        }
        public String getQb_text() {
                return qb_text;
        }
        public void setQb_text(String qb_text) {
                this.qb_text = qb_text;
        }
        public Date getQb_reg() {
                return qb_reg;
        }
        public void setQb_reg(Date qb_reg) {
                this.qb_reg = qb_reg;
        }
        public int getQb_view() {
                return qb_view;
        }
        public void setQb_view(int qb_view) {
                this.qb_view = qb_view;
        }
        public int getCount() {
                return count;
        }
        public void setCount(int count) {
                this.count = count;
        }
}
