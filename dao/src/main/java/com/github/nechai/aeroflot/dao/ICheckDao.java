package com.github.nechai.aeroflot.dao;

import com.github.nechai.aeroflot.model.Check;
import com.github.nechai.aeroflot.model.TypeOfCheck;

import java.util.Date;
import java.util.List;

public interface ICheckDao {
    public Check insert(Check check);
    public boolean update(Check check);
    public boolean delete(int checkN);
    public float totalByTypeOfCheck(List<Check> checkList,TypeOfCheck type);
    public List<Check> getChecks(String userLogin);
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, String userLogin);
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, TypeOfCheck type, String userLogin);
}
