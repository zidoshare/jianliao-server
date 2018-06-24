package site.zido.jianliao.common;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class TimeId implements IdentifierGenerator {
    public TimeId() {
    }

    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
        synchronized (TimeId.class) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return System.currentTimeMillis();
        }
    }
}
