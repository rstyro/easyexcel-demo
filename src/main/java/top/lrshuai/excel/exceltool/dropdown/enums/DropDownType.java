package top.lrshuai.excel.exceltool.dropdown.enums;

/**
 * 下拉类型枚举
 */
public enum DropDownType  implements DropDownEnum{
    NONE("NONE","无，空"),
    DEPT("dept","部门"),
    OCCUPATION("occupation","职业"),

    ;

    private String value;
    private String remark;

    DropDownType(String value,String remark){
        this.value=value;
        this.remark=remark;
    }

    public String getRemark() {
        return remark;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String getDropDownValue() {
        return this.remark;
    }
}
