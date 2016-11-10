package test.greenDAO.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import test.greenDAO.bean.Duty;

import test.greenDAO.dao.DutyDao;

public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dutyDaoConfig;

    private final DutyDao dutyDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dutyDaoConfig = daoConfigMap.get(DutyDao.class).clone();
        dutyDaoConfig.initIdentityScope(type);

        dutyDao = new DutyDao(dutyDaoConfig, this);

        registerDao(Duty.class, dutyDao);
    }
    
    public void clear() {
        dutyDaoConfig.getIdentityScope().clear();
    }

    public DutyDao getDutyDao() {
        return dutyDao;
    }

}
