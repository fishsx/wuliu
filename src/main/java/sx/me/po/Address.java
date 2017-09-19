package sx.me.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by sx on 2017/7/11.
 */
@Entity
public class Address {
    private int addressId;
    private String addressShengfen;
    private String addressChengshi;
    private String addressQu;
    private String addressJiedao;

    @Id
    @Column(name = "address_id", nullable = false)
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "address_shengfen", nullable = false, length = 20)
    public String getAddressShengfen() {
        return addressShengfen;
    }

    public void setAddressShengfen(String addressShengfen) {
        this.addressShengfen = addressShengfen;
    }

    @Basic
    @Column(name = "address_chengshi", nullable = false, length = 20)
    public String getAddressChengshi() {
        return addressChengshi;
    }

    public void setAddressChengshi(String addressChengshi) {
        this.addressChengshi = addressChengshi;
    }

    @Basic
    @Column(name = "address_qu", nullable = false, length = 20)
    public String getAddressQu() {
        return addressQu;
    }

    public void setAddressQu(String addressQu) {
        this.addressQu = addressQu;
    }

    @Basic
    @Column(name = "address_jiedao", nullable = false, length = 20)
    public String getAddressJiedao() {
        return addressJiedao;
    }

    public void setAddressJiedao(String addressJiedao) {
        this.addressJiedao = addressJiedao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (addressId != address.addressId) return false;
        if (addressShengfen != null ? !addressShengfen.equals(address.addressShengfen) : address.addressShengfen != null)
            return false;
        if (addressChengshi != null ? !addressChengshi.equals(address.addressChengshi) : address.addressChengshi != null)
            return false;
        if (addressQu != null ? !addressQu.equals(address.addressQu) : address.addressQu != null) return false;
        if (addressJiedao != null ? !addressJiedao.equals(address.addressJiedao) : address.addressJiedao != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = addressId;
        result = 31 * result + (addressShengfen != null ? addressShengfen.hashCode() : 0);
        result = 31 * result + (addressChengshi != null ? addressChengshi.hashCode() : 0);
        result = 31 * result + (addressQu != null ? addressQu.hashCode() : 0);
        result = 31 * result + (addressJiedao != null ? addressJiedao.hashCode() : 0);
        return result;
    }
}
