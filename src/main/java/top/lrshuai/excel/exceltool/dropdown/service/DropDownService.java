package top.lrshuai.excel.exceltool.dropdown.service;

public class DropDownService implements IDropDownService{

    @Override
    public String[] getSource(String typeValue) {
        return new String[]{"研发部","财务部","人事部"};
    }
}
