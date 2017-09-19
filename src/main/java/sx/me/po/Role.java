package sx.me.po;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Role {
    private int roleId;
    private String roleName;
    private int roleRoot;
    private Collection<RoleUser> roleUsersByRoleId;

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name", nullable = false, length = 20)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_root", nullable = false)
    public int getRoleRoot() {
        return roleRoot;
    }

    public void setRoleRoot(int roleRoot) {
        this.roleRoot = roleRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleId != role.roleId) return false;
        if (roleRoot != role.roleRoot) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + roleRoot;
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<RoleUser> getRoleUsersByRoleId() {
        return roleUsersByRoleId;
    }

    public void setRoleUsersByRoleId(Collection<RoleUser> roleUsersByRoleId) {
        this.roleUsersByRoleId = roleUsersByRoleId;
    }
}
