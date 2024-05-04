package com.example.posjohonnyjavatecspring2023.Entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ingrediant_names", schema = "pos_db")
public class IngrediantNamesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ingrediant_names_id")
    private int ingrediantNamesId;
    @Basic
    @Column(name = "ingrediant_name")
    private String ingrediantName;
    @OneToMany(mappedBy = "ingrediantNamesByIngrediantNamesId")
    private Collection<IngrediantEntity> ingrediantsByIngrediantNamesId;

    public int getIngrediantNamesId() {
        return ingrediantNamesId;
    }

    public void setIngrediantNamesId(int ingrediantNamesId) {
        this.ingrediantNamesId = ingrediantNamesId;
    }

    public String getIngrediantName() {
        return ingrediantName;
    }

    public void setIngrediantName(String ingrediantName) {
        this.ingrediantName = ingrediantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngrediantNamesEntity that = (IngrediantNamesEntity) o;
        return ingrediantNamesId == that.ingrediantNamesId && Objects.equals(ingrediantName, that.ingrediantName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingrediantNamesId, ingrediantName);
    }

    public Collection<IngrediantEntity> getIngrediantsByIngrediantNamesId() {
        return ingrediantsByIngrediantNamesId;
    }

    public void setIngrediantsByIngrediantNamesId(Collection<IngrediantEntity> ingrediantsByIngrediantNamesId) {
        this.ingrediantsByIngrediantNamesId = ingrediantsByIngrediantNamesId;
    }
}
