/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truongvl.dtos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nero
 */
@XmlRootElement
public class ChoiceOfUser implements Serializable {
    
    private Integer id;
    private Account accountId;
    private Product productId;
    
    public ChoiceOfUser() {
    }

    public ChoiceOfUser(Integer id, Account accountId, Product productId) {
        this.id = id;
        this.accountId = accountId;
        this.productId = productId;
    }
    
    @XmlElement(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "accountId")
    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    @XmlElement(name = "productId")
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }
}
