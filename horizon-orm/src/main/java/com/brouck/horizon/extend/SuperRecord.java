package com.brouck.horizon.extend;

import com.brouck.horizon.session.HorizonSession;

/**
 * @author brouck
 * Create time 2022/3/30
 */
public class SuperRecord {

    private HorizonSession horizonSession;

    private void setHorizonSession(HorizonSession horizonSession) {
        this.horizonSession = horizonSession;
    }

    /**
     * 执行保存操作
     */
    public boolean store() {
        return horizonSession.store(this);
    }

    /**
     * 执行更新操作
     */
    public boolean update() {
        return horizonSession.update(this);
    }

    /**
     * 执行删除操作
     */
    public boolean remove() {
        return horizonSession.remove(this);
    }

}
