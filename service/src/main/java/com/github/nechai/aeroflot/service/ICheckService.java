package com.github.nechai.aeroflot.service;

import com.github.nechai.aeroflot.model.Check;
import com.github.nechai.aeroflot.model.TypeOfCheck;

import java.util.Date;
import java.util.List;

public interface ICheckService  {
    public Check insertCheck(Check check);
    public boolean updateCheck(Check check);
    public boolean deleteCheck(int checkN);
    public float totalByTypeOfCheck(List<Check> checkList,TypeOfCheck type);
    public List<Check> getChecks(String userLogin);
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, String userLogin);
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, TypeOfCheck type, String userLogin);
}
