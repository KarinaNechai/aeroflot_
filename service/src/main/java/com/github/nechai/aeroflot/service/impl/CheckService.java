package com.github.nechai.aeroflot.service.impl;

import com.github.nechai.aeroflot.dao.impl.CheckDao;
import com.github.nechai.aeroflot.model.Check;
import com.github.nechai.aeroflot.model.TypeOfCheck;
import com.github.nechai.aeroflot.service.ICheckService;

import java.util.Date;
import java.util.List;

public class CheckService implements ICheckService {
    CheckDao checkDao = CheckDao.getInstance().getInstance();

    private static volatile ICheckService instance;
    private void  CheckSevice(){
    }

    public static ICheckService getInstance() {
        ICheckService localInstance = instance;
        if (localInstance == null) {
            synchronized ( ICheckService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CheckService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Check insertCheck(Check check) {
        return checkDao.insert(check);
    }

    @Override
    public boolean updateCheck(Check check) {
        return checkDao.update(check);
    }

    @Override
    public boolean deleteCheck(int checkN) {
        return checkDao.delete(checkN);
    }

    @Override
    public float totalByTypeOfCheck(List<Check> checkList, TypeOfCheck type) {
        return checkDao.totalByTypeOfCheck( checkList,type);
    }


    @Override
    public List<Check> getChecks(String userLogin) {
        return checkDao.getChecks(userLogin);
    }

    @Override
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, String userLogin) {
        return checkDao.getChecks(userLogin);
    }

    @Override
    public List<Check> getChecksByPeriod(Date dateStart, Date dateEnd, TypeOfCheck type, String userLogin) {
        return checkDao.getChecksByPeriod(dateStart,dateEnd,type,userLogin);
    }
}
