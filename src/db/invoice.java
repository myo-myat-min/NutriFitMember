package db;

import java.time.LocalDate;

public class invoice  extends  Member{
      
         private String Iid;
         private LocalDate invoice_date;
         private LocalDate end;
         private String member_id;
        private String membership_id;
        private String membership_name;
         private int admin_id;

    public invoice(String Iid, LocalDate invoice_date, LocalDate end, String member_id, String membership_id, int admin_id, String id, String name, String password, LocalDate dob, String gender, int weight, int height, String email, String nrc, String ph, String address, String medical_data) {
        super(id, name, password, dob, gender, weight, height, email, nrc, ph, address, medical_data);
        this.Iid = id;
        this.invoice_date = invoice_date;
        this.end = end;
        this.member_id = member_id;
        this.membership_id = membership_id;
        this.admin_id = admin_id;
    }

    public invoice(String Iid, LocalDate invoice_date, LocalDate end, String member_id, String membership_id, int admin_id) {
        this.Iid = Iid;
        this.invoice_date = invoice_date;
        this.end = end;
        this.member_id = member_id;
        this.membership_id = membership_id;
        this.admin_id = admin_id;
    }

    public invoice(LocalDate end, String member_id, String membership_name, String name, String nrc) {
        super(name, nrc);
        this.end = end;
        this.member_id = member_id;
        this.membership_name = membership_name;
    }

    public invoice(String Iid, LocalDate invoice_date,LocalDate end, String membership_name, String id, String name, String email) {
        super(id, name, email);
        this.Iid = Iid;
        this.end=end;
        this.invoice_date = invoice_date;
        this.membership_name = membership_name;
    }

    public String getIid() {
        return Iid;
    }

    public void setIid(String Iid) {
        this.Iid = Iid;
    }

    public String getMembership_name() {
        return membership_name;
    }

    public void setMembership_name(String membership_name) {
        this.membership_name = membership_name;
    }

    public LocalDate getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(LocalDate invoice_date) {
        this.invoice_date = invoice_date;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(String membership_id) {
        this.membership_id = membership_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "invoice{" + "Iid=" + Iid + ", invoice_date=" + invoice_date + ", end=" + end + ", member_id=" + member_id + ", membership_id=" + membership_id + ", membership_name=" + membership_name + ", admin_id=" + admin_id + '}';
    }  
    
}
