package cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n01.S05T01N01TomasCristina.model.dto;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n01.S05T01N01TomasCristina.exceptions.BranchNotFoundException;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n01.S05T01N01TomasCristina.util.*;

import static cat.itacademy.barcelonactiva.tomas.cristina.s05.t01.n01.S05T01N01TomasCristina.util.Country.determineBranchType;

public class BranchDTO {
    private Integer id;
    private String branchName;
    private String branchCountry;
    private String branchType;

    public BranchDTO() {}

    public BranchDTO(Integer id, String branchName, String branchCountry) {
        this.id = id;
        this.branchName = branchName;
        setBranchCountry(branchCountry);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCountry() {
        return branchCountry;
    }

    public void setBranchCountry(String branchCountry) {
        if (branchCountry != null) {
            this.branchCountry = branchCountry.toLowerCase();
            this.branchType = determineBranchType(this.branchCountry);
        } else {
            this.branchCountry = null;
            this.branchType = null;
        }
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }


}
