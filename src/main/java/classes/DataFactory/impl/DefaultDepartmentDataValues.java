package classes.DataFactory.impl;

import classes.DataFactory.DepartmentDataValues;

/**
 * Created by pawelwiejkut on 13.03.2016.
 */
public class DefaultDepartmentDataValues implements DepartmentDataValues{

   String[] departmentName={"Oddział","Centrala","Filia"};

    @Override
    public String[] getDepartmentName() {
        return departmentName;
    }
}
