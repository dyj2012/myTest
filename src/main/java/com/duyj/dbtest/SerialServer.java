package com.duyj.dbtest;

import com.duyj.dbtest.mapper.SerialNumberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SerialServer {

    @Autowired
    SerialNumberMapper serialNumberMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long f1() {
        serialNumberMapper.increase(1L);
        return serialNumberMapper.getNum(1L);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long f2() {
        long l = serialNumberMapper.lockRow(2L);
        serialNumberMapper.increase(2L);
        return l + 1;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long f3() {
        serialNumberMapper.insertRow();
        return serialNumberMapper.getSequence();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long f3_orcle() {
        return serialNumberMapper.getSequence_oracle();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public long f4() {
        return serialNumberMapper.increaseAndReturn(3L);
    }
}
