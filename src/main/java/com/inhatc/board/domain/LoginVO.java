package com.inhatc.board.domain;

public class LoginVO {
        private String id;
        private String pw;
        private int mem_no;
        private int count;
        public String getId() {
                return id;
        }
        public void setId(String id) {
                this.id = id;
        }
        public String getPw() {
                return pw;
        }
        public void setPw(String pw) {
                this.pw = pw;
        }
        public int getMem_no() {
                return mem_no;
        }
        public void setMem_no(int mem_no) {
                this.mem_no = mem_no;
        }
        public int getCount() {
                return count;
        }
        public void setCount(int count) {
                this.count = count;
        }
}
