/*
 * This file is generated by jOOQ.
 */
package com.example.demo.modelgenerate.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal amount;
    private Boolean active;
    private LocalDate createddate;
    private LocalDateTime createdtime;
    private Integer numberorder;
    private String one;
    private String two;
    private String tree;
    private String four;
    private String five;
    private String six;
    private String seven;
    private String eight;
    private String nine;
    private String ten;

    public Orders() {}

    public Orders(Orders value) {
        this.id = value.id;
        this.amount = value.amount;
        this.active = value.active;
        this.createddate = value.createddate;
        this.createdtime = value.createdtime;
        this.numberorder = value.numberorder;
        this.one = value.one;
        this.two = value.two;
        this.tree = value.tree;
        this.four = value.four;
        this.five = value.five;
        this.six = value.six;
        this.seven = value.seven;
        this.eight = value.eight;
        this.nine = value.nine;
        this.ten = value.ten;
    }

    public Orders(
        Long id,
        BigDecimal amount,
        Boolean active,
        LocalDate createddate,
        LocalDateTime createdtime,
        Integer numberorder,
        String one,
        String two,
        String tree,
        String four,
        String five,
        String six,
        String seven,
        String eight,
        String nine,
        String ten
    ) {
        this.id = id;
        this.amount = amount;
        this.active = active;
        this.createddate = createddate;
        this.createdtime = createdtime;
        this.numberorder = numberorder;
        this.one = one;
        this.two = two;
        this.tree = tree;
        this.four = four;
        this.five = five;
        this.six = six;
        this.seven = seven;
        this.eight = eight;
        this.nine = nine;
        this.ten = ten;
    }

    /**
     * Getter for <code>jdbi.orders.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>jdbi.orders.id</code>.
     */
    public Orders setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.amount</code>.
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Setter for <code>jdbi.orders.amount</code>.
     */
    public Orders setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.active</code>.
     */
    public Boolean getActive() {
        return this.active;
    }

    /**
     * Setter for <code>jdbi.orders.active</code>.
     */
    public Orders setActive(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.createddate</code>.
     */
    public LocalDate getCreateddate() {
        return this.createddate;
    }

    /**
     * Setter for <code>jdbi.orders.createddate</code>.
     */
    public Orders setCreateddate(LocalDate createddate) {
        this.createddate = createddate;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.createdtime</code>.
     */
    public LocalDateTime getCreatedtime() {
        return this.createdtime;
    }

    /**
     * Setter for <code>jdbi.orders.createdtime</code>.
     */
    public Orders setCreatedtime(LocalDateTime createdtime) {
        this.createdtime = createdtime;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.numberorder</code>.
     */
    public Integer getNumberorder() {
        return this.numberorder;
    }

    /**
     * Setter for <code>jdbi.orders.numberorder</code>.
     */
    public Orders setNumberorder(Integer numberorder) {
        this.numberorder = numberorder;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.one</code>.
     */
    public String getOne() {
        return this.one;
    }

    /**
     * Setter for <code>jdbi.orders.one</code>.
     */
    public Orders setOne(String one) {
        this.one = one;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.two</code>.
     */
    public String getTwo() {
        return this.two;
    }

    /**
     * Setter for <code>jdbi.orders.two</code>.
     */
    public Orders setTwo(String two) {
        this.two = two;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.tree</code>.
     */
    public String getTree() {
        return this.tree;
    }

    /**
     * Setter for <code>jdbi.orders.tree</code>.
     */
    public Orders setTree(String tree) {
        this.tree = tree;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.four</code>.
     */
    public String getFour() {
        return this.four;
    }

    /**
     * Setter for <code>jdbi.orders.four</code>.
     */
    public Orders setFour(String four) {
        this.four = four;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.five</code>.
     */
    public String getFive() {
        return this.five;
    }

    /**
     * Setter for <code>jdbi.orders.five</code>.
     */
    public Orders setFive(String five) {
        this.five = five;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.six</code>.
     */
    public String getSix() {
        return this.six;
    }

    /**
     * Setter for <code>jdbi.orders.six</code>.
     */
    public Orders setSix(String six) {
        this.six = six;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.seven</code>.
     */
    public String getSeven() {
        return this.seven;
    }

    /**
     * Setter for <code>jdbi.orders.seven</code>.
     */
    public Orders setSeven(String seven) {
        this.seven = seven;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.eight</code>.
     */
    public String getEight() {
        return this.eight;
    }

    /**
     * Setter for <code>jdbi.orders.eight</code>.
     */
    public Orders setEight(String eight) {
        this.eight = eight;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.nine</code>.
     */
    public String getNine() {
        return this.nine;
    }

    /**
     * Setter for <code>jdbi.orders.nine</code>.
     */
    public Orders setNine(String nine) {
        this.nine = nine;
        return this;
    }

    /**
     * Getter for <code>jdbi.orders.ten</code>.
     */
    public String getTen() {
        return this.ten;
    }

    /**
     * Setter for <code>jdbi.orders.ten</code>.
     */
    public Orders setTen(String ten) {
        this.ten = ten;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Orders other = (Orders) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.amount == null) {
            if (other.amount != null)
                return false;
        }
        else if (!this.amount.equals(other.amount))
            return false;
        if (this.active == null) {
            if (other.active != null)
                return false;
        }
        else if (!this.active.equals(other.active))
            return false;
        if (this.createddate == null) {
            if (other.createddate != null)
                return false;
        }
        else if (!this.createddate.equals(other.createddate))
            return false;
        if (this.createdtime == null) {
            if (other.createdtime != null)
                return false;
        }
        else if (!this.createdtime.equals(other.createdtime))
            return false;
        if (this.numberorder == null) {
            if (other.numberorder != null)
                return false;
        }
        else if (!this.numberorder.equals(other.numberorder))
            return false;
        if (this.one == null) {
            if (other.one != null)
                return false;
        }
        else if (!this.one.equals(other.one))
            return false;
        if (this.two == null) {
            if (other.two != null)
                return false;
        }
        else if (!this.two.equals(other.two))
            return false;
        if (this.tree == null) {
            if (other.tree != null)
                return false;
        }
        else if (!this.tree.equals(other.tree))
            return false;
        if (this.four == null) {
            if (other.four != null)
                return false;
        }
        else if (!this.four.equals(other.four))
            return false;
        if (this.five == null) {
            if (other.five != null)
                return false;
        }
        else if (!this.five.equals(other.five))
            return false;
        if (this.six == null) {
            if (other.six != null)
                return false;
        }
        else if (!this.six.equals(other.six))
            return false;
        if (this.seven == null) {
            if (other.seven != null)
                return false;
        }
        else if (!this.seven.equals(other.seven))
            return false;
        if (this.eight == null) {
            if (other.eight != null)
                return false;
        }
        else if (!this.eight.equals(other.eight))
            return false;
        if (this.nine == null) {
            if (other.nine != null)
                return false;
        }
        else if (!this.nine.equals(other.nine))
            return false;
        if (this.ten == null) {
            if (other.ten != null)
                return false;
        }
        else if (!this.ten.equals(other.ten))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.amount == null) ? 0 : this.amount.hashCode());
        result = prime * result + ((this.active == null) ? 0 : this.active.hashCode());
        result = prime * result + ((this.createddate == null) ? 0 : this.createddate.hashCode());
        result = prime * result + ((this.createdtime == null) ? 0 : this.createdtime.hashCode());
        result = prime * result + ((this.numberorder == null) ? 0 : this.numberorder.hashCode());
        result = prime * result + ((this.one == null) ? 0 : this.one.hashCode());
        result = prime * result + ((this.two == null) ? 0 : this.two.hashCode());
        result = prime * result + ((this.tree == null) ? 0 : this.tree.hashCode());
        result = prime * result + ((this.four == null) ? 0 : this.four.hashCode());
        result = prime * result + ((this.five == null) ? 0 : this.five.hashCode());
        result = prime * result + ((this.six == null) ? 0 : this.six.hashCode());
        result = prime * result + ((this.seven == null) ? 0 : this.seven.hashCode());
        result = prime * result + ((this.eight == null) ? 0 : this.eight.hashCode());
        result = prime * result + ((this.nine == null) ? 0 : this.nine.hashCode());
        result = prime * result + ((this.ten == null) ? 0 : this.ten.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Orders (");

        sb.append(id);
        sb.append(", ").append(amount);
        sb.append(", ").append(active);
        sb.append(", ").append(createddate);
        sb.append(", ").append(createdtime);
        sb.append(", ").append(numberorder);
        sb.append(", ").append(one);
        sb.append(", ").append(two);
        sb.append(", ").append(tree);
        sb.append(", ").append(four);
        sb.append(", ").append(five);
        sb.append(", ").append(six);
        sb.append(", ").append(seven);
        sb.append(", ").append(eight);
        sb.append(", ").append(nine);
        sb.append(", ").append(ten);

        sb.append(")");
        return sb.toString();
    }
}