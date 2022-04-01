package com.brouck.extend;

import com.brouck.session.BrouckSession;

/**
 * @author brouck
 * Create time 2022/3/30
 */
public class SuperRecord {

    private BrouckSession brouckSession;

    private void setHorizonSession(BrouckSession brouckSession) {
        this.brouckSession = brouckSession;
    }

    /**
     * 执行保存操作
     */
    public boolean store() {
        return brouckSession.store(this);
    }

    /**
     * 执行更新操作
     */
    public boolean update() {
        return brouckSession.update(this);
    }

    /**
     * 执行删除操作
     */
    public boolean remove() {
        return brouckSession.remove(this);
    }

}
