package br.com.devleo.desafiowl.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String cpf;
    @NotNull
    private String area;
    @NotNull
    private String password;
    @NotNull
    private Boolean admin;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Coffee> coffees = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String cpf, String area, String password, Boolean admin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.area = area;
        this.password = password;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        try {
            MaskFormatter format = new MaskFormatter("###.###.###-##");
            JFormattedTextField formatf = new JFormattedTextField(format);
            formatf.setText(cpf);
            return formatf.getText();
        } catch (Exception ignored) {
        }
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
