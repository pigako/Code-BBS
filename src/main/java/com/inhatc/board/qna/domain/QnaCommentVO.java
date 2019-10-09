package com.inhatc.board.qna.domain;

import java.util.Date;

public class QnaCommentVO {
        private int qbc_no;
        private int qb_no;
        private int mem_no;
        private String mem_id;
        private String qbc_text;
        private Date qbc_reg;
        public int getQbc_no() {
                return qbc_no;
        }
        public void setQbc_no(int qbc_no) {
                this.qbc_no = qbc_no;
        }
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
        public String getQbc_text() {
                return qbc_text;
        }
        public void setQbc_text(String qbc_text) {
                this.qbc_text = qbc_text;
        }
        public Date getQbc_reg() {
                return qbc_reg;
        }
        public void setQbc_reg(Date qbc_reg) {
                this.qbc_reg = qbc_reg;
        }
}
