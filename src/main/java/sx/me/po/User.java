package sx.me.po;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class User {
    private int uid;
    private String uname;
    private String tname;
    private String upwd;
    private String uphone;
    private Timestamp uRegtime;
    private Collection<RoleUser> roleUsersByUid;
    private Company companyByCompanyId;
    private Dept deptByDeptId;

    @Id
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "uname", nullable = false, length = 20)
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "tname", nullable = false, length = 20)
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    @Basic
    @Column(name = "upwd", nullable = false, length = 20)
    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    @Basic
    @Column(name = "uphone", nullable = true, length = 20)
    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    @Basic
    @Column(name = "uRegtime", nullable = false)
    public Timestamp getuRegtime() {
        return uRegtime;
    }

    public void setuRegtime(Timestamp uRegtime) {
        this.uRegtime = uRegtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (uid != user.uid) return false;
        if (uname != null ? !uname.equals(user.uname) : user.uname != null) return false;
        if (tname != null ? !tname.equals(user.tname) : user.tname != null) return false;
        if (upwd != null ? !upwd.equals(user.upwd) : user.upwd != null) return false;
        if (uphone != null ? !uphone.equals(user.uphone) : user.uphone != null) return false;
        if (uRegtime != null ? !uRegtime.equals(user.uRegtime) : user.uRegtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (tname != null ? tname.hashCode() : 0);
        result = 31 * result + (upwd != null ? upwd.hashCode() : 0);
        result = 31 * result + (uphone != null ? uphone.hashCode() : 0);
        result = 31 * result + (uRegtime != null ? uRegtime.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<RoleUser> getRoleUsersByUid() {
        return roleUsersByUid;
    }

    public void setRoleUsersByUid(Collection<RoleUser> roleUsersByUid) {
        this.roleUsersByUid = roleUsersByUid;
    }

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "company_id", nullable = false)
    public Company getCompanyByCompanyId() {
        return companyByCompanyId;
    }

    public void setCompanyByCompanyId(Company companyByCompanyId) {
        this.companyByCompanyId = companyByCompanyId;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id", nullable = false)
    public Dept getDeptByDeptId() {
        return deptByDeptId;
    }

    public void setDeptByDeptId(Dept deptByDeptId) {
        this.deptByDeptId = deptByDeptId;
    }

    public User(String uname, String tname, String upwd, String uphone, Company companyByCompanyId, Dept deptByDeptId) {
        this.uname = uname;
        this.tname = tname;
        this.upwd = upwd;
        this.uphone = uphone;
        this.companyByCompanyId = companyByCompanyId;
        this.deptByDeptId = deptByDeptId;
    }

    public User() {
    }
}
